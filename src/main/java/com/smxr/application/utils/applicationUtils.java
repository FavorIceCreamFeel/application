package com.smxr.application.utils;


import com.alibaba.fastjson.JSONObject;
import com.smxr.application.pojo.PhoneCode;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author smxr
 * @date 2019/12/2
 * @time 23:02
 * 工具类
 */
public class applicationUtils {
    @Autowired
    private PhoneCode phoneCode;
    /**
     * 获取指定长度的随机字符串
     */
    public static String getRandomString(int length){
        String str="0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<length;i++) {
            int i1 = random.nextInt(str.length());
            stringBuilder.append(str.charAt(i1));
        }
        return stringBuilder.toString();
    }
    /**
     * 生成签名信息
     * @param secretKey 产品私钥
     * @param params 接口请求参数名和参数值map，不包括signature参数名
     * @return
     */
    private String genSignature(String secretKey, Map<String, String> params){
        // 1. 参数名按照ASCII码表升序排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append(params.get(key));
        }
        // 3. 将secretKey拼接到最后
        sb.append(secretKey);

        // 4. MD5是128位长度的摘要算法，转换为十六进制之后长度为32字符
        return DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }
    /**
     * 实例化HttpClient
     *
     * @param maxTotal
     * @param maxPerRoute
     * @param socketTimeout
     * @param connectTimeout
     * @param connectionRequestTimeout
     * @return
     */
    private HttpClient createHttpClient(int maxTotal, int maxPerRoute, int socketTimeout, int connectTimeout,
                                        int connectionRequestTimeout) {
        RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectionRequestTimeout).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxTotal);
        cm.setDefaultMaxPerRoute(maxPerRoute);
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm)
                .setDefaultRequestConfig(defaultRequestConfig).build();
        return httpClient;
    }

    /**
     * 发送post请求
     * @param url        请求地址
     * @param params     请求参数
     * @param encoding   编码
     * @return
     */
    private String sendPost(String url, Map<String, String> params, Charset encoding) {
        String resp = "";
        HttpPost httpPost = new HttpPost(url);
        if (params != null && params.size() > 0) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> itr = params.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, encoding);
            httpPost.setEntity(postEntity);
        }
        CloseableHttpResponse response = null;
        try {
            response = (CloseableHttpResponse) createHttpClient(100, 100, 2000, 2000, 2000).execute(httpPost);
            resp = EntityUtils.toString(response.getEntity(), encoding);
        } catch (Exception e) {
            // log
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    // log
                    e.printStackTrace();
                }
            }
        }
        return resp;
    }

    /**
     * 运行方法
     * @return
     */
    public String sendPostPhoneNum(String phone){
    // 模板参数对应的json格式数据,例如模板为您的验证码为${p1},请于${p2}时间登陆到我们的服务器
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("p1", getRandomString(6));
    jsonObject.put("p2", "60秒");
    String params = jsonObject.toJSONString();
        Map<String, String> data = null;
        try {
            data = buildParam(phone, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //发送请求
        return sendPost(phoneCode.getAPI_URl(), data, Consts.UTF_8);
}
    /**
     * 环境参数
     * @param mobile
     * @param params
     * @return
     * @throws IOException
     */
    private Map<String, String> buildParam(String mobile, String params) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
            map.put("businessId", phoneCode.getBusinessId());
            map.put("timestamp", String.valueOf(System.currentTimeMillis()));//时间戳
            map.put("version", "v2");//请求版本
            map.put("templateId",phoneCode.getTemplateId());// 此处用申请通过的模板id
            map.put("mobile", mobile);//手机号
            map.put("paramType", "json");//参数类型
            map.put("params", params);//验证码参数
            map.put("nonce", UUID.randomUUID().toString().replace("-", ""));
            map.put("secretId",phoneCode.getSecretId());//添加产品密钥ID
        String sign = genSignature(phoneCode.getSecretKey(), map);//生成签名信息
            map.put("signature", sign);
            return map;
      }
}

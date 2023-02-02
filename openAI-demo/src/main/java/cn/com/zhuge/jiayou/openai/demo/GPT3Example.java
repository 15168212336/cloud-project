package cn.com.zhuge.jiayou.openai.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;

public class GPT3Example {
  public static void main(String[] args) throws Exception {
    // 准备请求参数
    String apiKey = "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";
    String prompt = "What is the Chinese code returned by GPT-3 please apply";
    String model = "davinci";
//    String requestUrl = "https://api.openai.com/v1/completions";
    String requestUrl = "https://chat.openai.com/backend-api/conversation";

    // 创建 HttpClient
    HttpClient httpClient = HttpClientBuilder.create().build();
//    SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(
//            SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
//            NoopHostnameVerifier.INSTANCE);
//    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(scsf).build();

    SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
    RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
    httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext).setSSLHostnameVerifier((x, y) -> true).build();


    // 创建请求
    HttpPost request = new HttpPost(requestUrl);

    // 设置请求头
    request.setHeader("Content-Type", "application/json");
    request.setHeader("Authorization", "Bearer " + apiKey);
    request.setHeader("Connection", "close");

    // 设置请求参数
    JSONObject requestParams = new JSONObject();
    requestParams.put("model", model);
    requestParams.put("prompt", prompt);
    requestParams.put("max_tokens", 64);
    request.setEntity(new StringEntity(requestParams.toString()));

    // 发送请求
    HttpResponse response = httpClient.execute(request);

    // 解析响应
    HttpEntity entity = response.getEntity();
//    String responseString = EntityUtils.toString(entity, "UTF-8");
    String responseString = EntityUtils.toString(entity,"UTF-8");
    JSONObject responseJson = new JSONObject(responseString);
    System.out.println(responseJson);
    // 获取结果
    String completion = responseJson.getString("completions");
  }
}
package cn.com.zhuge.jiayou.alipay.demo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 诸葛
 * @date 2022/08/22 14:41
 **/
@Data
@Component
@ConfigurationProperties("alipay")
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private String appId;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchantPrivateKey;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipayPublicKey;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notifyUrl;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String returnUrl;

    // 签名方式
    private String signType = "RSA2";

    // 字符编码格式
    private String charset = "utf-8";
    private String format = "json";

    // 支付宝网关
    private String gatewayUrl;

    // 支付宝网关
    private String logPath = "C:\\";



}

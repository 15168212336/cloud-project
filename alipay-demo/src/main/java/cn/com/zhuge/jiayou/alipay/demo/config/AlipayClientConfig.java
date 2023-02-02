package cn.com.zhuge.jiayou.alipay.demo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 诸葛
 * @date 2022/08/22 15:46
 **/
@Component
public class AlipayClientConfig {
    @Autowired
    private AlipayConfig alipayConfig;

    @Bean
    public AlipayClient getAlipayClient() {

        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getMerchantPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType());
        return alipayClient;
    }
}

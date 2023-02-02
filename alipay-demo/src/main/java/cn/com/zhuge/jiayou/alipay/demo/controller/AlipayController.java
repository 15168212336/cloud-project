package cn.com.zhuge.jiayou.alipay.demo.controller;

import cn.com.zhuge.jiayou.alipay.demo.config.AlipayConfig;
import cn.com.zhuge.jiayou.core.utils.RandomUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayEbppBillRefundModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 诸葛
 * @date 2022/08/22 16:13
 **/
@RestController
public class AlipayController {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * pc端网站支付
     * 统一收单下单并支付页面接口
     * @throws
     * @return java.lang.String
     * @author 诸葛
     * @date 2022/8/23 15:27
     */
    @GetMapping("alipay/trade/page/pay")
    public String alipayTradePagePay() {

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = RandomUtils.getTradeNo(4);
        //付款金额，必填
        String total_amount = "0.1";
        //订单名称，必填
        String subject = "测试订单";
        //商品描述，可空
        String body = "测试订单body";

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(total_amount);
        model.setSubject(subject);
        model.setBody(body);
        //销售产品码，与支付宝签约的产品码名称。注：目前电脑支付场景下仅支持FAST_INSTANT_TRADE_PAY
        model.setProductCode("FAST_INSTANT_TRADE_PAY");


        alipayRequest.setBizModel(model);
        //请求
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        System.out.println(result);

        return result;
    }

    /**
     * 当面付 扫码支付
     * 统一收单线下交易预创建
     * @throws
     * @return java.lang.String
     * @author 诸葛
     * @date 2022/8/23 16:10
     */
    @GetMapping("alipay/trade/pre/create")
    public String alipayTradPreCreate() {

        //设置请求参数
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = RandomUtils.getTradeNo(4);
        //付款金额，必填
        String total_amount = "0.1";
        //订单名称，必填
        String subject = "测试订单";


        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(out_trade_no);
        model.setTotalAmount(total_amount);
        model.setSubject(subject);



        alipayRequest.setBizModel(model);
        //请求
        String result = null;
        try {
            AlipayTradePrecreateResponse alipayTradePrecreateResponse = alipayClient.execute(alipayRequest);
            result = alipayTradePrecreateResponse.getQrCode();
//            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        System.out.println(result);

        return result;

    }

    /**
     * 订单查询
     * @param outTradeNo 商户订单号，商户网站订单系统中唯一订单号 二选一
     * @param tradeNo 支付宝交易号 二选一
     * @throws
     * @return java.lang.String
     * @author 诸葛
     * @date 2022/8/23 9:35
     */
    @GetMapping("orderCheck")
    public String orderCheck(@RequestParam(value = "outTradeNo",required = false,defaultValue = "") String outTradeNo,
                             @RequestParam(value = "tradeNo",required = false,defaultValue = "") String tradeNo) {

        if (outTradeNo == null && tradeNo == null) {
            return "两个参数不能同时为空";
        }

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        //商户订单号，商户网站订单系统中唯一订单号
        if (outTradeNo != null) {
            model.setOutTradeNo(outTradeNo);
        }
        //支付宝交易号
        if (tradeNo != null) {
            model.setTradeNo(tradeNo);
        }

        alipayRequest.setBizModel(model);

        //请求
        String result = null;
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        System.out.println(result);
        return result;
    }

    /**
     * 支付回调接口
     * @throws UnsupportedEncodingException
     * @return java.lang.String
     * @author 诸葛
     * @date 2022/8/23 15:28
     */
    @PostMapping("alipayNotify")
    public String alipayNotify(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/

        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            System.out.println("success");
            return "success";

        }else {//验证失败
            System.out.println("fail");
            return "fail";
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }


    @GetMapping("alipayReturn")
    public String alipayReturn(HttpServletRequest request) throws UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            return "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount;
        }else {
            System.out.println("验签失败");
            return "验签失败";
        }
    }
}

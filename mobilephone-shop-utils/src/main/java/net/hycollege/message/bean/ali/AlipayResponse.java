/**
  * Copyright 2019 bejson.com 
  */
package net.hycollege.message.bean.ali;

/**
 * Auto-generated: 2019-11-24 13:21:25
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AlipayResponse {

    private Alipay_trade_page_pay_response alipay_trade_page_pay_response;
    private String sign;
    public void setAlipay_trade_page_pay_response(Alipay_trade_page_pay_response alipay_trade_page_pay_response) {
         this.alipay_trade_page_pay_response = alipay_trade_page_pay_response;
     }
     public Alipay_trade_page_pay_response getAlipay_trade_page_pay_response() {
         return alipay_trade_page_pay_response;
     }

    public void setSign(String sign) {
         this.sign = sign;
     }
     public String getSign() {
         return sign;
     }

}
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
public class Alipay_trade_page_pay_response {

    private String code;
    private String msg;
    private String trade_no;
    private String out_trade_no;
    private String seller_id;
    private int total_amount;
    private String merchant_order_no;
    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setMsg(String msg) {
         this.msg = msg;
     }
     public String getMsg() {
         return msg;
     }

    public void setTrade_no(String trade_no) {
         this.trade_no = trade_no;
     }
     public String getTrade_no() {
         return trade_no;
     }

    public void setOut_trade_no(String out_trade_no) {
         this.out_trade_no = out_trade_no;
     }
     public String getOut_trade_no() {
         return out_trade_no;
     }

    public void setSeller_id(String seller_id) {
         this.seller_id = seller_id;
     }
     public String getSeller_id() {
         return seller_id;
     }

    public void setTotal_amount(int total_amount) {
         this.total_amount = total_amount;
     }
     public int getTotal_amount() {
         return total_amount;
     }

    public void setMerchant_order_no(String merchant_order_no) {
         this.merchant_order_no = merchant_order_no;
     }
     public String getMerchant_order_no() {
         return merchant_order_no;
     }

}
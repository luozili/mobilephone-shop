package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500689452";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCLC8pGHUBH4fF2evlShF4fq0tpKvYZHXbjdyiXegvWkQVjxGAvIj84Le8zNviP6xJDm9ig8V1nyXEK6B40812ykgOyuTsDUES8jyX0Bl88VwDZqex/AED8/SrRh+1VZfuE4MTq3ouNpz8XEy6JQFexFUY4/hP5qV0DH9BNnIG/UQQGlTe03KxmcEzhRAI4g8di9nhSm2VpsJGGS0F2SZtj2UgAtn1eWr8ipzsd41+kLrgBVhlmdujudICQwDdZq5jAyLMy+ISups9VqO5+DrIYvfnntvnQavdpRWfvGqFiunKL1Ev9Eu6aBbeUw10HAC1tcXa+Tyfh8rtJBys09STjAgMBAAECggEAJh4PT3VJX+tStHY1zyc2XlwpCiWa3Xu0Udsip5Pvltyeq6Dz9TfgONhu0I1rSpjYouwgUM6aGo+gi6riTQXCU+evV9lrkhb5IO/lKaZtiS8YrpLkoFRDfJcym8o1h8mzT9QGZ5SmkSieJQc/JKUc5RC99XXBqBmDxA1+nygecYGuhOLIBln6zY1LRGDmdfuwLSIHkmNDYYmChlUkKa/tH3BjlsGfqgg2LudmY2Hj/Mf1YLOAPxEOQn9CVQZxfcSR4B5RM+ykHSfFKau7ZrOI2buMFoZCUSr87HSUSNTrPB4866ddFThlB792maxOR5q5p/ArZlsv4Uc8x0rdBjZ14QKBgQDIckSTTd7EMb2e3keT1wWKO/mQL5b3EODFlAKKgK2Yc5pM43JMDoG69AjkGFJGjta5QsCSEGq9tz5v3tFHTXdYZPhLfc335MeOHYEpOSCdmWVMYcj2Z7kbdIEGHAfKonFUWLFRAp528V6GUgQPjrqc/RUncRJe39qYnWnWjLQEKwKBgQCxlSbD5785D+fCpiov/Esq7iGEW77Xb+k+p3sXswH3fb81YIFJdHD3UECeg0TJ4ImF2GtlC2ulywnhjgsASebpg7zHiz9aaBFSWtMzb/pBVe7H4g9JjSdpzMqG91Wx41iT5i/vxSut45SbSV/c0UHtTipGt1tNsIQr7Lz83gJuKQKBgDs8AWXigi1KYbXcDBz1Uu7J0HM6jQtQEoxws9uIMUS3DOch47n0I4RC74mwYkf1mCDBSDUh4tlTmIDowMGoc9dMZ0+vyrkyA4LM10l+V1Qba6MJ1qBygHJLmIFcsTTBkmoRl7zP4FJX+84H2KlcTWttIt43/OzKjEfDZO6BQAVnAoGAAx/No8qy+Y0HFcyYl2mH5QR1gH+gtueYIesOZldW5ITI1WaSP7mVtzQpPuCuWCLkKH+rPAbcaPlOfjJijSm/r0ILEzOm9K86BsnkR1Zj/7xETC30+OVL2TeqJH+7eTq3tzpog35ji1aiqay7zAW+dC9Y3Q+iwpYlbSRoGsbJIikCgYB5m+fSK74LjfhTy9sXhTfENdEZE3fMUQMF/GUyoJ/Z5i6Ea8p8/tjOrDkaOAGHt6IjKHs67wAfHkId1O+cB8jrQGXGIuaDzw+hx0rbi/qFB+zk1f0dZbvn/mdDDfaxptulURXpvwwa483Hh2cDKr+Qypl8SdqNF8tTWnb94drvhQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiXK/svGWzs89Eh/+0X5RieSjy8uLWw3bciTQqSBHaGN16nXn+lSbcmvmxHaw61OHesd0kks6wjvnVy6dTxXNQ/tGk6Tlx1XFKb0tFDTSETooGzrUJxOUVOsffxSiMwertqXMXlCldc9DtS6I6BHOyIV4LbB+0ZLjM4E75c9huYX/9cMMOtpxB9ntZHEnemNWj2yzj5S5TDEPpUpGfozmqnMby2KOYOiTTqS5NMZQi686ObLLA+wkl9DkwzIDAd+Y9iDg0nmsoTPjlyD2YsVaMlYiBvYGsywcEslDsNjqPxfO0FRzpJIvBNWqbU92p+T1phNNzATMXw1q2Gy+Hd5vxwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://47.103.217.104:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.103.217.104:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关https://openapi.alipaydev.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



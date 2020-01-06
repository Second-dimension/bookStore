package com.bookStore.utils;

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
    public static String app_id = "2016092800616820";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYigm5rQCwRq5FfVrmlnzwT5MWY6nLLlBMQuXKFdGGCtOZmDlBAMZG6bvpDOIs2YLiWGf88xjC1G00o4Q5fPtMHIxGqLiywITOS99xrbTlA4WGG105/Ggg817rdN4UgfOQugLFAg5hACT7QRcEAudOekTQJBv2y6T1X7AaI+cLxx6K2mCKTHcbzFhgmReCXquUwah+iWM8SiFr2sss5MWheOhFxjOV8WRth6nS7gybqJdPs5YrIgSfUkrYB8EIRnk27tVChtzL4ohcgajmDphFngJTwvl6nX1bmuJ/s/zO6tAv5ptsEtIslcrWzo8xAdHM5xZ5J+/KhHvQirEuXbyhAgMBAAECggEALTnoLkQMCze5neLP28RMoNeHDcFY62P87M984BDeAXceS9DlUM+Q+h981lk6if+nncKGemn9Mm7XPh8pKf5TZ7qjIKQPtvf7qEq4ojTViH0lzqA4YOPkX7iosPkCi2QIOiXcglrctofbbx9aWpcdy5fW5Tt/iwnQC+xmAyoX69aTWC3BB2Ce9bc3uhwVOhRpuhKgrGjsMmQMUGWNArT714u3QfVCWzKK7nGt63gian2GJ+MDMqspcHkiYAeTFVz5I025hE28DQXZt1+4zxtJRjZuL3kUTVRRk4pYmWRmNs/ZL8e1wUry0pLo6ryFpgXHg0msMV0pBd1G5dgDPj6bHQKBgQDwaAJpiNJWEw8Q3a+uUhC753+4dn60l6BdAXeqKFeHgnZD9jzHUw14hvFLtZWAUW9SsDemyVp6IvwIxyi/Y2Krd/3sIZCB1+MPWcn4hKeaV5mzwWYuH3q6PzTsW54MXk2NL2/ehGCRm4cytNKhOJ62GuPQ93iQ6/v6fLs037hzFwKBgQCibvrIfzWCltqWZd0gpV4T9C+ngOZTKpGfJrx9CmAE+ZYi9u3nM+YROaWG7aOeol0zUavgjSIQUgMI4o/xXF/4RUKcL/pyryXJ3I62fvBjGxd/VUEQdzrBNzJoFI13ZmcqeaWvmTRg4KmujDu3jRKTnJnrVm1s7S3/Ta902MyBBwKBgQDrr2w9/m8HB6RLiqqDId1Z+jjSpQjPD5+dzqR8YxR8u0AqnKs1VozsTo8dBCZPKIcU78F/eAOFneqsyGOATnUbWYXNC5LaWMY39HT3eUW/IIaFHeOI8D+ne/PsjF1EDufEtG7eHbV2yp0e7vtYJ2Qhb/dMlHDU0yWzirkVuwbffQKBgANx8yO5fvsMFArIkk4U88/ch/43f6sX5grIGYtVYv/4AzUuo5EZyrFm1ZntBGFi9DMYclrK44QRGTthAsiNuwNbBLSnV9qC2HxVjzlTq+mOM4th1YVx4e8jKpCGjxGPPHTsK5Vz3GaJnU+0bbfQ6Cn1UlZpeNyskyMtFKGXz46VAoGBAIiqWtQ6SOTDlH26yq8jSTDu3T0HNciKUPlO/qUoBTo5xCXIodKQswouNsZc+Y4dFhHw7iRakbOul2ELTavQv2KSz3R6FgEEBHWVYvo9+7hByRaie3+J+SrafsEtuex6FZY2wXReTT/e3vnpukbOOpZo/vjTkazQwSfsflT3xFlF";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    //public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmIoJua0AsEauRX1a5pZ88E+TFmOpyy5QTELlyhXRhgrTmZg5QQDGRum76QziLNmC4lhn/PMYwtRtNKOEOXz7TByMRqi4ssCEzkvfca205QOFhhtdOfxoIPNe63TeFIHzkLoCxQIOYQAk+0EXBALnTnpE0CQb9suk9V+wGiPnC8ceitpgikx3G8xYYJkXgl6rlMGofoljPEoha9rLLOTFoXjoRcYzlfFkbYep0u4Mm6iXT7OWKyIEn1JK2AfBCEZ5Nu7VQobcy+KIXIGo5g6YRZ4CU8L5ep19W5rif7P8zurQL+abbBLSLJXK1s6PMQHRzOcWeSfvyoR70IqxLl28oQIDAQAB";
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/bookStore_war/client/cart/paysuccess.do";

    // 签名方式
    public static String sign_type = "RSA";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
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
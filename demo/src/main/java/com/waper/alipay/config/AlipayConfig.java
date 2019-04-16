package com.waper.alipay.config;

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
	public static String app_id = "2016091300499886";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfyi7Ua2u1q0mL1HuPmfEfKh5qqw9lWxDR2szYwE/jTketAXjK+PlRvB8ODqnheYUXAl5yPC+B0SJtfW4SjOvCy7rItQK9WAgk0Pzv0bBgwwMT8IzgAb5UWHhrNlDdnx5rG9/OmStjbts+C7kKBDhl78C7vSMFiS9gEMPgrwv4/vqPTKllepvYY3hlCAwHq1wqiwoOMjp8x5CRpoMxoRxeHjJC6VJ2NzAnJB463nEVGV/KEGRSr1PWBKCY5TtPvLV9dgog9CmRGHf/VcYQfjB4xMVPYLzE+XUiIhdFkbEsFiKU+m+iaoIJVrTZsRF/SCZcaH7vBdjtCzyLP1JUs4/HAgMBAAECggEBAI8YfuuX73NXm5tvg9/L2pUwIOEho73+2Yc1THFnd6N1/NZAFS6f3JaHw7/Q/e1/5+H9KxteLqUw5oXq5+IQ9SkTTYhkIbny1ggyZ/FUdYGR4nW+3EnXBd1/BQfuMmjmEFWXNsxLewGmyww7b1A7C06ucIkwDebtPdHn8aKK4EvwkPGKm/CuF8tSKDeE48E6NJh41YJ3f277owysQg2kVjNWg9jxwcctQRP3QOxWpNKgR6hp6ecvJCaTshxrwYlxhCOtlujs9tEKoo/OvmWFc3nzCxSPFfsKQALzGkWZJhuW7YFE8HSAEq25zxID6lgly0KQV2W3gUbgSUyNDC/NKdECgYEA15IFnsaWDk/Ie4nlag42/cHOhBpDnLMdDxwJSkIfn7mVzlvzJL1EOEHCslZybTVoxm4ctUpYZXyVpdDKUS1kTOyAGE7uMxNxWJZeVa7GAXp4LsOyRhH21jjeAQV0J9GAv78r2lVushVVpm6LSpNoozKcuvg5IXNPNQOYpoGpHsUCgYEAvcIExxn+hYTc9YnDd8kdu8SwExA8h3GqE/3mggYmrnc8VLmibUwmaVgAS6RkH64C/n/7qiR4IwHohEJyxa5OMdho6/kkMSQURtJ+IjThOYaN0fcWlrBbcSHVwGwwyFPnwpqvDnxKX629kMU3brS88UhC3yok4AfkCTf5nSftHRsCgYAWlZPFKKT75tvoJXBFhiu4th8+s8m1lwZKWPOYNgk+IKFTN0rn5HRLOIh/SpM+YcQSVeLoE1vjdgPvlUo86vaBIJ3i7hICg9zyHP7RFzhjW0NQEuyQbr1fgCGyFbIvocFNVWfwvdaVDaslfptVOtBRmLHW3L6PhJcdxLyvZRIkyQKBgCDAzHJRWk1RkZIQJV0XqbovSPUBaHE4fVLWEGOBDEww6Ij3QcH70uV7+xETXvTY5Etpa9cCVHo2ahvqHEViwLx5ziJs7oJM5/MRghQFbJqxTule7Nc1uZLddDeydPkfAnHkoTKu4WeCZaYHZHoHWFWNU32RmKvSsNmmf79QlOzhAoGBAITniVoYYIlwhWgQCBJM4EMpp7QDMN//+YnjlUCeJJ0gNpuIsGM59hrGGIhJQlSW83mSnNbXZFAsj3iXsa7dcVwaRhtO2ldEn4Ha0RVWDVH/fGsyCAGC/+VZxf3zYO09k86u4HUdgVcxk2Lnfn2umcdCZ9fg5gCpWIR0dnb8m/CB";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApyhiA6dW9Ot8liMaYX1LrypT3ZCx5a6ne5w68jXhwYFD+oEy77EHXuwXbl7q0KpKEk+5OT+wRekjQ2i5WV6RObJUO/aTsvbLS/0MV+L6RHYQVSq7phDoD76NfVoAqN5xtKqkcSdWwo8kVdJMm41i1w6e7K4qcwnlfj+YdjWHiIJAlIBw+b2rH4kzInK5+Vwp8+LUCuKDBcY429CESthndJM/M5k6AbrenCOyH7Nz/xz/x+A8i2gjurEudk9MrigT+sRsQbqHjjkYJteBSVm4tw0DAhyaxcsjJppKX50qEuR3e4Jnr6gwvD8PntC06b8cgPHVenunMWVuESOtLrkrBQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
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


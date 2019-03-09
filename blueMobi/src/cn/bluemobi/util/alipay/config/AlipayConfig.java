package cn.bluemobi.util.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig
{
    
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 合作身份者ID，以2088开头由16位纯数字组成的字符串
    public static String partner = "2088911355833145";
    
    // 商户的私钥"                       MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL/9+pSudptY7BTeSZidO2eCSjU8HhpW83878tGwMU7F01mHfdrXdYnG742EsEUlIqKX1AkdwHb7F4LBelS0VEO8KZBwa8ecB5lXkWUUcb3WmkKQRl97PzXkoooH+8qQfO/3DIj4H9+2vXe5LicFp8cn5HbWyWlB4VCQLCPf63c9AgMBAAECgYA1iLowbx0/X+IkR386AM2ppRjQEz1Jluk6lxq0AW8eSecO1crwFJf/nOr7CP6lqR8pOzCyggtCoe0CVrSnncjIxfMa58O3HhaW+WMc9QOKC+eI9uogbQqQUcu43onySSMG25z7cyKD0o7uPOtBnyNg8mH0PYS+VAKy+4SfsZYjgQJBAPdqnQPuERrO/xl0HSjdKh4EWt8aTgKBCLM4M2KMsaMpcd9OE2rBf+66pjD8J8uc8bDYbgNzvqFmnmPhyAqSGS0CQQDGpx+m5lj7zcFT95eLo9T7jehi3+y5SEk0uI4laJXtj1bGTEyWnXUIKVwqvAipyrpDUDGD1XyOB3G6iwkCZ4BRAkAjDoIs+mCGEepnauJu1deP5UIS9qEzDaFz83SkdBb/bXHrGB78M2qzItNFgZUJ7rT4P9JmvtG2ANpUgLtddBIJAkAoFYDlhIVgCdyAvOzvWFeZisDhcp2ppVRgNv03vyrBjLLNwIeTvKHoy6+1T8vPtwBupLfZCjLf1dC0+2kN+ySBAkAntM5OJvDWITif2Jskh5/MMoiTVwvg8AkBHQVAxSdszqlgjsbO8Xcp60NAUFgtzB7TTffWhyaGdtBLGjSJB/oJ";
    public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL/9+pSudptY7BTeSZidO2eCSjU8HhpW83878tGwMU7F01mHfdrXdYnG742EsEUlIqKX1AkdwHb7F4LBelS0VEO8KZBwa8ecB5lXkWUUcb3WmkKQRl97PzXkoooH+8qQfO/3DIj4H9+2vXe5LicFp8cn5HbWyWlB4VCQLCPf63c9AgMBAAECgYA1iLowbx0/X+IkR386AM2ppRjQEz1Jluk6lxq0AW8eSecO1crwFJf/nOr7CP6lqR8pOzCyggtCoe0CVrSnncjIxfMa58O3HhaW+WMc9QOKC+eI9uogbQqQUcu43onySSMG25z7cyKD0o7uPOtBnyNg8mH0PYS+VAKy+4SfsZYjgQJBAPdqnQPuERrO/xl0HSjdKh4EWt8aTgKBCLM4M2KMsaMpcd9OE2rBf+66pjD8J8uc8bDYbgNzvqFmnmPhyAqSGS0CQQDGpx+m5lj7zcFT95eLo9T7jehi3+y5SEk0uI4laJXtj1bGTEyWnXUIKVwqvAipyrpDUDGD1XyOB3G6iwkCZ4BRAkAjDoIs+mCGEepnauJu1deP5UIS9qEzDaFz83SkdBb/bXHrGB78M2qzItNFgZUJ7rT4P9JmvtG2ANpUgLtddBIJAkAoFYDlhIVgCdyAvOzvWFeZisDhcp2ppVRgNv03vyrBjLLNwIeTvKHoy6+1T8vPtwBupLfZCjLf1dC0+2kN+ySBAkAntM5OJvDWITif2Jskh5/MMoiTVwvg8AkBHQVAxSdszqlgjsbO8Xcp60NAUFgtzB7TTffWhyaGdtBLGjSJB/oJ";
    
    //                                     MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB
    // 支付宝的公钥，无需修改该值                          MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC//fqUrnabWOwU3kmYnTtngko1PB4aVvN/O/LRsDFOxdNZh33a13WJxu+NhLBFJSKil9QJHcB2+xeCwXpUtFRDvCmQcGvHnAeZV5FlFHG91ppCkEZfez815KKKB/vKkHzv9wyI+B/ftr13uS4nBafHJ+R21slpQeFQkCwj3+t3PQIDAQAB                          
    public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    
    /*	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    		// 合作身份者ID，以2088开头由16位纯数字组成的字符串
    		public static String partner = "2088901677428200";
    		// 商户的私钥
    		public static String private_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANjL/HSECHNimafyqYeqqY2CuPxQ9JMOaEw6aU0DTDLL+nXrf137AQBUE5iV2+rGy7A5mi+HX4BIlIUpDvCqeMEICgkMxtrYbPH3VC8mhN9TGoKRZetc100C5QIBpJ/K2BewjZ5AfEFoqXSaOMk482lViVs7bNyq4LhScIB6RdgjAgMBAAECgYEAv9gy/MBh4glcw7v+vSkOhUUqgUz6QK+9J8eAqz/BzCmo53SP7xBRIINGG/5w718fnEA86tY2coTHuNZOfDW+SCFg6625Ww+xZV44/ORaIpKPYPuLAIPV9pkKhHyAsGfEEVM2DduaI1Dw8gDc8jyamS8OT9qUmt3tE9OHYXJrrqECQQD39u096fRXNJKYJT3a8NmSt4BfleKqIcetJ7Q2ayX3+qdWypoWo2gTUQh62paDlSdwu0XcWe/N6dGwEf3sn/+rAkEA39J/O1WHkTlo3TerRT5fhEGM8rKgQNQA9LocLFUzwjriO42rdMrOvkVdxKso+0A7jC/Pmsmx/guLBKtQSOLxaQJAfib0H/Gsy8kFY75er8qlBo2xE/AvdxrkeWWfu6fbk0PEatV8e1P7GQi65bXbqWnyvNmp+ycOZ5Yzhf5BT2c/LQJBAMpm73m3mbXBRcwu6Gngwr22DYD3+aVGUmr81jddAUZWu3kBXxhpwlYcInSUF6ZF6tOV9NZUKOrEg33AcgTHfWkCQQDAtw0UQdbvCGjpdXj3NlZ1nXXl2Pn0zHuglHH8i9QupDJaZzwyw+MwK7elxM37lcli00XbhXgfzTlbVBMHqJMW";
    		
    		// 支付宝的公钥，无需修改该值
    		public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    		//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    */
    
    // 调试用，创建TXT日志文件夹路径
    public static String log_path = "D:\\";
    
    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "utf-8";
    
    // 签名方式 不需修改
    public static String sign_type = "RSA";
    
}

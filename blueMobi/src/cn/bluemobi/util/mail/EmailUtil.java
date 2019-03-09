package cn.bluemobi.util.mail;

import org.apache.commons.mail.HtmlEmail;


public class EmailUtil {
	
	private HtmlEmail email = null;
	private static String ACCOUNT = "zhengpinmao@sina.com";
	private static String PASSWORD = "zpmall150112";
	private static String HOST_NAME = "smtp.sina.com";
	private static String PORT  = "465";
	private static String CHARSET = "UTF-8";
	
	public EmailUtil(){
		email = new HtmlEmail();
	}
	
	public static void setAccount(String account) {
		ACCOUNT = account;
	}

	public static void setPassword(String password) {
		PASSWORD = password;
	}

	public static void setHostName(String hostName) {
		HOST_NAME = hostName;
	}

	public static void setPort(String port) {
		PORT = port;
	}

	public static void setCharset(String charset) {
		CHARSET = charset;
	}
	
	/**
	 * 描述:发送邮件方法
	 * @param	sender:	发送人
	 * @param	addressee:收件人
	 * @param	addresseeName:收件人名称
	 * @param	title:标题
	 * @param	content:内容
	 * **/
	public void sendMail(String sender,String addressee, String addresseeName, String title, String content){
		try{
			email.setHostName(HOST_NAME);
			email.setAuthentication(ACCOUNT, PASSWORD);
			email.setSSL(true);
			email.setSslSmtpPort(PORT);
			email.setCharset(CHARSET);
			email.addTo(addressee, addresseeName);
			email.setFrom(ACCOUNT, sender);
			email.setSubject(title);
			email.setHtmlMsg(content);
			
			email.send();
		}catch(Exception e){
			throw new RuntimeException(e);
		}					
	}
	public static void main(String[] args) {
		EmailUtil emailUtil=new EmailUtil();
		emailUtil.sendMail(ACCOUNT, "lvxiaohuan921229@sina.com", "happy", "邮件标题", "邮件发送内容！！！！");
		System.out.println("发送成功！！！！");
	}
}

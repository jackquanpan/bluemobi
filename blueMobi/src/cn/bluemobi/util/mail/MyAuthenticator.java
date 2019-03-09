package cn.bluemobi.util.mail;
/**
*
* @author geloin
* @date 2012-5-8 下午2:48:25
*/

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
* 
* @author geloin
* @date 2012-5-8 下午2:48:25
*/
public class MyAuthenticator extends Authenticator {
	private String username;
	private String password;

	/**
	 * 
	 * @author geloin
	 * @date 2012-5-8 下午2:48:53
	 * @param username
	 * @param password
	 */
	public MyAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}

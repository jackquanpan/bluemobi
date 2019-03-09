package cn.bluemobi.entity.reports;

/**
 * 用户登录统计实体
 * @author Chenwq
 *
 */
public class UserLogin {

	private String date;//日期
	
	private int addUserCount = 0;//新增用户数
	
	private int addStyCount = 0;//新增设计师
	
	private int loginUserCount = 0;//登陆用户数
	
	private int totalUserCount = 0;//总用户
	
	private int loginCount = 0;//登陆次数

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAddUserCount() {
		return addUserCount;
	}

	public void setAddUserCount(int addUserCount) {
		this.addUserCount = addUserCount;
	}

	public int getAddStyCount() {
		return addStyCount;
	}

	public void setAddStyCount(int addStyCount) {
		this.addStyCount = addStyCount;
	}

	public int getLoginUserCount() {
		return loginUserCount;
	}

	public void setLoginUserCount(int loginUserCount) {
		this.loginUserCount = loginUserCount;
	}

	public int getTotalUserCount() {
		return totalUserCount;
	}

	public void setTotalUserCount(int totalUserCount) {
		this.totalUserCount = totalUserCount;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
}

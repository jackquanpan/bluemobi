package cn.bluemobi.entity.reports;

/**
 * 财务报表实体
 * @author Chenwq
 *
 */
public class Finance {

	private String date;
	
	private double amount = 0.00;
	
	private double payedAmount = 0.00;
	
	private double coupon = 0.00;
	
	private double gold = 0.00;
	
	private double commission = 0.00;
	
	private double myProfit = 0.00;
	
	private double studioProfit = 0.00;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public double getCoupon() {
		return coupon;
	}

	public void setCoupon(double coupon) {
		this.coupon = coupon;
	}

	public double getGold() {
		return gold;
	}

	public void setGold(double gold) {
		this.gold = gold;
	}
	
	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getMyProfit() {
		return myProfit;
	}

	public void setMyProfit(double myProfit) {
		this.myProfit = myProfit;
	}

	public double getStudioProfit() {
		return studioProfit;
	}

	public void setStudioProfit(double studioProfit) {
		this.studioProfit = studioProfit;
	}
	
	
}

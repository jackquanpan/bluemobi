package cn.bluemobi.entity.reports;

/**
 * 订单统计实体
 * @author Chenwq
 *
 */
public class Order {

	private String date;//日期
	
	private int orderCount = 0;//订单数量
	
	private int itemCount = 0;//商品数量
	
	private double amount = 0.00;//金额合计

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}

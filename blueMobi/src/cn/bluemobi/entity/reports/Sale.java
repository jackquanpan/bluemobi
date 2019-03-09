package cn.bluemobi.entity.reports;

/**
 * 销售统计实体
 * @author Chenwq
 *
 */
public class Sale {
	
	private String itemCode;//商品编号
	
	private int saleCount = 0;//销售数量
	
	private double amount = 0.00;//销售金额

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}

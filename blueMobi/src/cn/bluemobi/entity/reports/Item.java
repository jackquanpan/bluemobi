package cn.bluemobi.entity.reports;

/**
 * 商品统计实体
 * @author Chenwq
 *
 */
public class Item {

	private String date;//日期
	
	private int newItemCount = 0;//新增商品数量
	
	private int downItemCount = 0;//下架商品数量
	
	private int itemCount = 0;//商品总数

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNewItemCount() {
		return newItemCount;
	}

	public void setNewItemCount(int newItemCount) {
		this.newItemCount = newItemCount;
	}

	public int getDownItemCount() {
		return downItemCount;
	}

	public void setDownItemCount(int downItemCount) {
		this.downItemCount = downItemCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
}

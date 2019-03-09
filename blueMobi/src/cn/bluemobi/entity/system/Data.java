package cn.bluemobi.entity.system;

import java.util.HashMap;
import java.util.Map;


/**
 * 客户端数据封装类
 * 
 * @author 雷攀
 * 
 */
public class Data {
	/**
	 * 标识
	 * 
	 * 
	 */
	private int status = 0; 
	/**
	 * 错误消息
	 * 
	 * 
	 */
	private String msg = null;
	/**
	 * 
	 * 数据
	 * 
	 */
	private Map<String,Object> data = null;
	/**
	 * 
	 * 分页参数
	 * 
	 */
	private Page page = null;
	
	
	/**
	 * 往data中添加数据
	 * 
	 * @param key
	 * @param o
	 */
	public void putInData(String key, Object o) {
		if (data == null)
			data = new HashMap<String, Object>();
		data.put(key, o);
	}

	/**
	 * 构造函数
	 */
	public Data() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}



}

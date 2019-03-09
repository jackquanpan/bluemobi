package cn.bluemobi.entity.system;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端数据封装类
 * 
 * @author 雷攀
 * 
 */
public class AppData {
	private Map<String, Object> data = null;
	private int status = 0;// 标识
	private String msg = null;
	private Page page = null;

	/**
	 * 构造函数，直接实例化data，并且把<key,o>放入data中
	 * 
	 * @param isCreateMap
	 */
	public AppData(int status, String key, Object o) {
		this.status = status;
		this.data = new HashMap<String, Object>();
		this.data.put(key, o);
	}

	/**
	 * 构造函数
	 * 
	 * @param a
	 * @param isCreateData
	 *            是否实例化data
	 */
	public AppData(int a, boolean isCreateData) {
		this.status = a;
		if (isCreateData)
			this.data = new HashMap<String, Object>();
	}

	/**
	 * 构造函数
	 */
	public AppData() {
	}

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
	 * 往data中添加数据
	 * 
	 * @param key
	 * @param o
	 */
	public void putInData(Map<String, Object> map) {
		if (data == null)
			data = new HashMap<String, Object>();
		if (map != null)
			for (String key : map.keySet()) {
				data.put(key, map.get(key));
			}

	}

	public Map<String, Object> getData() {
		return data;
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

}

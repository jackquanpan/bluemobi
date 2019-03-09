package cn.bluemobi.util.helper;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 生成短网址并返回
 * 
 * @author: Jerri
 * @date: 2014年3月22日下午9:58:54
 */
public class ShortUrlHelper {

	/**
	 * 生成短网址字符串（已经去除"http://dwz.cn/"）
	 */
	public static String createShortUrl(String longUrl) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.sina.com/"+longUrl);
		String a = JsonHelper.getJsonObjByPost("http://dwz.cn/create.php", map);
		a = (JSON.parseObject(a)).getString("tinyurl").replace("http://dwz.cn/", "");
		return a;
	}
	

	/**
	 * 生成短网址字符串
	 */
	public static String unShortUrl(String shortUrl) {
		try {
			JsonHelper.getJsonObjString("http://dwz.cn/"+shortUrl, "UTF-8");
			return "";
		} catch (Exception e) {
			String a = e.toString();
			return a.substring(a.indexOf("http://www.sina.com/") + 20);
		}
	}
}
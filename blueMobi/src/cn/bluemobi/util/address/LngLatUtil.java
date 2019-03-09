package cn.bluemobi.util.address;

import java.net.URLEncoder;

import org.json.JSONObject;

import cn.bluemobi.util.helper.JsonHelper;
import cn.bluemobi.util.helper.ValidateHelper;

/**
 * 根据地址或者坐标获取地址或者地理经纬度
 * 
 * @author 雷攀
 * 
 */
public class LngLatUtil {

	/**
	 * 对传入的地址进行地址解析
	 * 
	 * @param address
	 * @return
	 */
	public static AddressInfo getLngLatByAddress(String address,String city) {
		try {
			if (ValidateHelper.isNullOrEmpty(address)) {
				return null;
			}
			address = URLEncoder.encode(address, "UTF-8");
			String url = "http://api.map.baidu.com/geocoder?address=" + address + "&output=json";
			if(!ValidateHelper.isNullOrEmpty(city)){
				url+="&city="+URLEncoder.encode(city, "UTF-8");
			}
			// {"result":{"level":"城市","location":{"lng":114.3162,"lat":30.581084},"confidence":12,"precise":0},"status":"OK"}
			JSONObject json = JsonHelper.getJsonObj(url, "UTF-8");
			if (json != null) {
				if (json.has("status") && json.get("status").equals("OK")) {
					AddressInfo ai = JsonHelper.convertToObject(json.getJSONObject("result").toString(), AddressInfo.class);
					return ai;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

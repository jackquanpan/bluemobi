package cn.bluemobi.util.ip;

import java.util.Map;

/**
 * ip
 * 
 * @author Jack
 * 
 */
public class Ip {

	/*
	 * {"code":0,
	 * "data":{"country":"\u4e2d\u56fd","country_id":"CN","area":"\u534e\u5317"
	 * ,"area_id":"100000",
	 * "region":"\u5317\u4eac\u5e02","region_id":"110000","city"
	 * :"\u5317\u4eac\u5e02",
	 * "city_id":"110000","county":"","county_id":"-1","isp":"","isp_id":"-1",
	 * "ip":"118.186.9.131"}}
	 */
	private String code;
	private Map<String, String> data = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getCountry() {
		if (this.data != null) {
			return data.get("country");
		}
		return null;
	}

	public String getProvince() {
		if (this.data != null) {
			return data.get("region");
		}
		return null;
	}

	public String getCity() {
		if (this.data != null) {
			return data.get("city");
		}
		return null;
	}

	public String getArea() {
		if (this.data != null) {
			return data.get("area");
		}
		return null;
	}

	public String getRegion() {
		if (this.data != null) {
			return data.get("region");
		}
		return null;
	}

	public String getCounty() {
		if (this.data != null) {
			return data.get("county");
		}
		return null;
	}

	public String getIsp() {
		if (this.data != null) {
			return data.get("isp");
		}
		return null;
	}

	/*
	 * //System.out.println("国家:"+i.getCountry());
	 * //System.out.println("省份:"+i.getProvince());
	 * //System.out.println("城市:"+i.getCity());
	 * //System.out.println("区域:"+i.getDistrict());
	 * //System.out.println("ips:"+i.getIsp());
	 * //System.out.println("类型:"+i.getType());
	 */

}

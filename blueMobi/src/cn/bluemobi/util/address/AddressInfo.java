package cn.bluemobi.util.address;

import java.util.Map;

public class AddressInfo {
	
	private String level;
	private Map<String,Double> location;
	
	/**
	 * 可信度,
	 */
	private  String confidence;
	/**
	 * ’位置的附加信息，是否精确查找’（1为精确查找，0为不精确查找）,
	 */
	private String precise;
	
	
	
	
	public String getLevel() {
		return level;
	}




	public void setLevel(String level) {
		this.level = level;
	}




	public Map<String, Double> getLocation() {
		return location;
	}




	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}




	public String getConfidence() {
		return confidence;
	}




	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}




	public String getPrecise() {
		return precise;
	}




	public void setPrecise(String precise) {
		this.precise = precise;
	}



	public Double getLng(){
		if(location!=null){
			return location.get("lng");
		}
		return null;
	}
	
	public Double getLat(){
		if(location!=null){
			return location.get("lat");
		}
		return null;
	}

	
	

}

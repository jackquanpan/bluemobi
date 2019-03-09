package cn.bluemobi.util.mail;


public class DataUtil {

	public static String objToStr(String str){
		if (str==null||"".equals(str)) {
			return "";
		}
		return str.trim();
	}
}

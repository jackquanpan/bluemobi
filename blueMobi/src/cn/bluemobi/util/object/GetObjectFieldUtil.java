package cn.bluemobi.util.object;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 将一个对象的所有可get的属性转换成一个数据库查询字段的字符串，并返回这个字符串
 * 
 * 如memberId将转换成member_id as memberId 并且多个属性之间用逗号分隔开
 * 
 * 用于一个对象拥有大量属性时，返回其所有属性的对应数据库字段的字符串
 * 
 * 
 * @author wanggz
 *
 */
public class GetObjectFieldUtil {

	public static String getObjectFieldStr(Class className) {
		String str = "";
		int i=0;
		Method[] methods = className.getMethods();
		for (Method method : methods) {
			String name = method.getName();
			if (name.startsWith("get") && (!name.equals("getClass"))) {
				name = name.substring(name.indexOf("get") + 3);
				char firstWord = name.charAt(0);
				firstWord = (char) ((int) firstWord + 32);
				name = firstWord + name.substring(1);
				String oldName = name;
				Matcher m = Pattern.compile("[A-Z]").matcher(name);
				int in = 0;
				while (m.find(in)) {
					in = m.end();
					char c = name.charAt(in - 1);
					c = (char) ((int) c + 32);
					name = name.substring(0, in - 1) + "_" + c
							+ name.substring(in);
					m = Pattern.compile("[A-Z]").matcher(name);
				}
				str += name + " as " + oldName + ",";
//				str+=name+":="+oldName+",";
				i++;
			}
		}
		System.out.println(i);
		return str.substring(0, str.lastIndexOf(","));
	}

	public static void main(String[] args) {
//		System.out.println(getObjectFieldStr(TestReport.class));
	}

}

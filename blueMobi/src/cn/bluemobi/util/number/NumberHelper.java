package cn.bluemobi.util.number;


/**
 * 数字服务类
 * @author 雷攀
 *
 */
public class NumberHelper {

	/**
	 * 格式化数字
	 * 
	 * @param o
	 *            数字对象
	 * @param num
	 *            保留的小数
	 * @return
	 */
	public static String format(Object o, int num) {
		if(num==0){
			String r=o.toString();
			return r.substring(0,r.indexOf("."));
		}
		String r=o.toString();
		num=r.indexOf(".")+num+1>=r.length()?r.length():r.indexOf(".")+num+1;
		r=r.substring(0,num);
		return r;
	}
}

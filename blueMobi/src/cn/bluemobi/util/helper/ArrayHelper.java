package cn.bluemobi.util.helper;

import java.util.ArrayList;
import java.util.List;


/**
 * 数组辅助类
 * @author 雷攀
 *
 */
public class ArrayHelper {
	
	/**
	 * 数组对象转化为list对象
	 * @param array
	 * @return
	 */
	public static <T> List<T> toList(T[]  array){
		if(array==null||array.length==0){
			return null;
		}
		List<T> list=new ArrayList<T>();
		for(T o:array){
			list.add(o);
		}
		return list;
	}

	/**
	 * 数组是否包含对象
	 * @param array 对象数组
	 * @param object 包含的对象
	 * @return 是否包含
	 */
	public static <T> boolean containsInArr(T[] array, T object){
		if(array == null || array.length == 0){
			return false;
		}
		for (T t : array) {
			if(t == object || t.equals(object)){
				return true;
			}
		}
		return false;
	}
}

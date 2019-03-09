package cn.bluemobi.constant;

import java.lang.reflect.Field;



public class IntegerAtoB {

	/**
	* 2019年3月4日
	* description
	* author:panquanke
	*/
	public static void main(String[] args) {
		Integer a=1;
		Integer b=2;
		System.out.println("before:a="+a+"b="+b);
		swap(a, b);
		System.out.println("after:a="+a+"b="+b);
	}
	public static void swap(Integer num1,Integer num2){
	try {
	Field field= Integer.class.getDeclaredField("value");
	field.setAccessible(true);
	int temp=num1.intValue();
	field.set(num1, num2);
	field.set(num2, new Integer(temp));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
}

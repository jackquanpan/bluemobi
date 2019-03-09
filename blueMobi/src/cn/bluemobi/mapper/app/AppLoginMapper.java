/**
 * Project Name:ecommercePlatform
 * File Name:AppLoginMapper.java
 * Package Name:cn.bluemobi.mapper.app
 * Date:2015-8-12下午1:44:18
 * Copyright (c) 2015, oscarwang1988@126.com All Rights Reserved.
 *
*/

package cn.bluemobi.mapper.app;

import java.util.Map;


import org.apache.ibatis.annotations.Select;import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Description:	   <br/>
 * Date:     2015-8-12 下午1:44:18 <br/>
 * @author   oscar_000
 * @version  
 * @see 	 
 */
public interface AppLoginMapper {
	/**
	 * 用户登录
	 * @param phone
	 * @param password
	 * @return
	 */
     
	/*@Select("select count(*) from app_users where phone=#{arg0} and pwd=#{arg1}")*/
	Map<String,Object> LoginApp(Map<String, Object> resultmap);

}


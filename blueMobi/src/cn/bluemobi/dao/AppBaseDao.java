package cn.bluemobi.dao;

import java.util.List;
import java.util.Map;

import cn.bluemobi.entity.system.Page;


/**
 * 通用的baseDao接口服务
 * 
 * @author 雷攀
 * 
 */
public interface AppBaseDao {
	
	/***
	 * 
	*
	* findList 
	*
	*  列表查询
	*
	*  @param sql
	*  @return    设定文件 
	*
	* List<Map<String,Object>>    返回类型 
	*
	*
	 */
	public List<Map<String, Object>> findList(String sql);

	/***
	 * 
	*
	* findPageList 
	*
	*  分页查询
	*
	*  @param sql
	*  @param page
	*  @return    设定文件 
	*
	* List<Map<String,Object>>    返回类型 
	*
	*
	 */
	List<Map<String, Object>> findPageList(String sql, Page page);
	
	
    Map<String, Object> findMyMap(String sql);
}

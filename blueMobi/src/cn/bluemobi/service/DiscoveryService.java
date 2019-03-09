package cn.bluemobi.service;

import java.util.List;
import java.util.Map;

public interface DiscoveryService {
	/**
	 * 管理品台获得发现列表
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
Map<String, Object>getDiscoveryList(Integer pageNo,Integer pagesize);
    /**
     * 统计发现数据量
     * @return
     */
	int countdiscovery();
	 /**
     * 
     * Description: 添加发现<br/>
     *
     * @author panquanke
     * @param paramMap
     * @return
     */
    boolean addDisCovery(Map<String, Object> paramMap);
    /**
     * 批量修改
     * @param idStr
     * @return
     */
    boolean bachUpdateSatus(String idStr);

    /**
     * 
     * Description: 获取后台系统管理员信息<br/>
     *
     * @author panquanke
     * @return
     */
    List<Map<String, Object>> findSystemUserList();
    /**
     * 
     * Description: 带条件的发现搜索<br/>
     *
     * @author panquanke
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> findDiscoveryListByCondition(Map<String, Object> paramMap);


}

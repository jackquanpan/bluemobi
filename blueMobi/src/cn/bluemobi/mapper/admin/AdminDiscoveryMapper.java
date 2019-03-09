package cn.bluemobi.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AdminDiscoveryMapper {
	/**
	 * 管理品台获得发现列表
	 * @param pageNo
	 * @param pagesize
	 * @return
	 */
	List<Map<String, Object>>getDiscoveryList(Integer pageNo,Integer pagesize);
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
    int insertDisCovery(Map<String, Object> paramMap);
    /**
     * 批量修改
     * @param idStr
     * @return
     */
    int batchUpdate(@Param("idStr") String idStr);
    /**
     * 
     * Description: 获取后台系统管理员信息<br/>
     *
     * @author panquanke
     * @return
     */
    List<Map<String, Object>> getSystemUserList();
    /**
     * 
     * Description: 带条件的发现搜索<br/>
     *
     * @author panquanke
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getDiscoveryListByCondition(Map<String, Object> paramMap);
}

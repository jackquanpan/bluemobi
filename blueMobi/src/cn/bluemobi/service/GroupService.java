package cn.bluemobi.service;

import java.util.List;

import cn.bluemobi.entity.Group;

/**
 * 
 * @author liyl
 *
 */
public interface GroupService {
	/**
	 * @author liyl
	 * 获取所有分组信息
	 * @return
	 */
	List<Group> getAllGroups();
	
	
	/**
	 * 新增时候的名称重复
	 * @param groupName
	 * @return
	 */
	public int isExist(String groupName);
	
	/**
	 * 更新时候的名称重复
	 * @param groupName
	 * @param id
	 * @return
	 */
	public int isExist(String groupName, Long id);
	
	/**
	 * 添加分组预设
	 */
	public int addGroup(String groupName, String authCode);
	
	
	/**
	 * 编辑分组预设
	 */
	public int updateGroup(String groupName, String authCode, Long id);
	
	/**
	 * 删除预设分组
	 */
	public int deleteGroup(String id);
	
	
	/**
	 * 获取预设对象
	 * @param id
	 * @return
	 */
    public Group findGroupById(Long id);
    
    /**
     * 获得该预设角色的所有权限code
     * @param id
     * @return
     */
    public String getGroupAuthCode(Long id);
}

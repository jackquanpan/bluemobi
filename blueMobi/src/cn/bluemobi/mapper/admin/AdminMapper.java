/**
 * Project Name:ecommercePlatform
 * File Name:LoginMapper.java
 * Package Name:cn.bluemobi.mapper.admin
 * Date:2015-7-9下午2:44:42
 * Copyright (c) 2015, oscarwang1988@126.com All Rights Reserved.
 *
 */

package cn.bluemobi.mapper.admin;

import java.util.List;
import java.util.Map;

import cn.bluemobi.entity.Admin;
import cn.bluemobi.entity.Authority;

/**
 * Description: <br/>
 * Date: 2015-7-9 下午2:44:42 <br/>
 * 
 * @author oscar_000
 * @version
 * @see
 */
public interface AdminMapper {
    /**
     * 
     * Description: 登陆<br/>
     * 
     * @author oscar_000
     * @param admin
     * @return
     */
    public Admin login(Admin admin);

    /**
     * 
     * Description: 获取所有管理员信息<br/>
     * 
     * @author oscar_000
     * @param parm
     * @return
     */
    public List<Admin> getAllAdmins(Map<String, Object> parm);

    /**
     * 
     * Description: 获取所有管理员记录数<br/>
     * 
     * @author oscar_000
     * @param admin
     * @return
     */
    public int getAllAdminsForCount(Admin admin);

    /**
     * 
     * Description: 添加管理员信息<br/>
     * 
     * @author oscar_000
     * @param admin
     * @return
     */
    public int addAdmin(Admin admin);

    /**
     * 
     * Description: 是否已经存在管理员信息<br/>
     * 
     * @author oscar_000
     * @param account
     * @return
     */
    public int checkAdmin(String account);

    public Admin getAdminById(long id);

    /**
     * 
     * Description: 修改管理员信息<br/>
     * 
     * @author oscar_000
     * @param admin
     * @return
     */
    public int updateAdmin(Admin admin);

    /**
     * 
     * Description: 删除管理员信息<br/>
     * 
     * @author oscar_000
     * @param id
     * @return
     */
    public int deleteAdmin(long id);

    /**
     * 获取管理员的权限
     * 
     * @param admin
     * @return
     */
    public List<Authority> findAuthority(Admin admin);

    /**
     * 权限对应的ID
     * 
     * @param admin
     * @return
     */
    public String findToegangstoken(Admin admin);

    /**
     * 管理员更新个人密码
     * 
     * @param param
     * @return
     */
    public int updateAdminPwd(Map<String, Object> param);
    /**
     * 
     * Description: 添加日志信息<br/>
     *
     * @author panquanke
     * @return
     */
    int insertLog(Map<String, Object> paramMap);
}

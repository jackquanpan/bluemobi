package cn.bluemobi.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.bluemobi.entity.Admin;
import cn.bluemobi.entity.Authority;
import cn.bluemobi.entity.system.Page;

/**
 * 管理员接口
 * 
 * @author lvxh
 * 
 */
public interface AdminService {

    /**
     * 登录
     * 
     * @param admin
     * @return
     */
    Admin login(Admin admin);

    /**
     * by dingyl 获取所有管理员信息
     * 
     * @param admin
     * @param page
     * @return
     */
    List<Admin> getAllAdmins(Admin admin, Page page);

    /**
     * by dingyl 新增管理员
     * 
     * @param admin
     * @return
     */
    int add(Admin admin);

    /**
     * by dingyl 核查该管理员是否已存在
     * 
     * @param account
     * @return
     */
    int checkAdmin(String account);

    /**
     * by dingyl 根据管理员编号查看管理员信息
     * 
     * @param id
     * @return
     */
    Admin getAdminById(long id);

    /**
     * by dingyl 修改管理员信息
     * 
     * @param admin
     * @return
     */
    int update(Admin admin);

    /**
     * by dingyl 删除管理员信息
     * 
     * @param id
     * @return
     */
    int delete(long id);

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
     * @param id
     * @param pwd
     * @return
     */
    public int updateAdminPwd(Long id, String pwd);
    /**
     * 
     * Description: 添加日志<br/>
     *
     * @author 丁鹏
     * @return
     */
    public boolean addLog(HttpSession session, String operateDesc, String operateTime);
}

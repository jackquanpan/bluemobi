package cn.bluemobi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.entity.Admin;
import cn.bluemobi.entity.Authority;
import cn.bluemobi.entity.system.Page;
import cn.bluemobi.mapper.admin.AdminMapper;
import cn.bluemobi.service.AdminService;

/**
 * 管理员接口实现
 * 
 * @author lvxh
 * 
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(Admin admin) {

        return adminMapper.login(admin);
    }

    /**
     * by dingyl 获取所有管理员信息
     */
    @Override
    public List<Admin> getAllAdmins(Admin admin, Page page) {

        // 总记录数
        int totalCount = adminMapper.getAllAdminsForCount(admin);
        Page.countPageSum(page, totalCount);

        Map<String, Object> parm = new HashMap<String, Object>();
        parm.put("admin", admin);
        parm.put("page", page);
        int beginSize = (page.getPageNo() - 1) * page.getPageSize();
        parm.put("beginSize", beginSize);

        return adminMapper.getAllAdmins(parm);
    }

    /**
     * by dingyl 新增管理员
     */
    @Override
    public int add(Admin admin) {

        int result = adminMapper.addAdmin(admin);
        return result;
    }

    /**
     * by dingyl 核查该管理员是否已存在
     */
    @Override
    public int checkAdmin(String account) {

        int result = adminMapper.checkAdmin(account);
        return result;
    }

    /**
     * by dingyl 根据管理员编号查看管理员信息
     */
    @Override
    public Admin getAdminById(long id) {

        Admin admin = adminMapper.getAdminById(id);
        return admin;
    }

    /**
     * by dingyl 修改管理员信息
     */
    @Override
    public int update(Admin admin) {

        int result = adminMapper.updateAdmin(admin);
        return result;
    }

    @Override
    public List<Authority> findAuthority(Admin admin) {

        // Auto-generated method stub
        return adminMapper.findAuthority(admin);
    }

    @Override
    public String findToegangstoken(Admin admin) {

        // Auto-generated method stub
        return adminMapper.findToegangstoken(admin);
    }

    @Override
    public int delete(long id) {

        // Auto-generated method stub
        return adminMapper.deleteAdmin(id);
    }

    @Override
    public int updateAdminPwd(Long id, String pwd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("password", pwd);
        param.put("id", id);
        return adminMapper.updateAdminPwd(param);
    }

	@Override
	public boolean addLog(HttpSession session, String operateDesc, String operateTime) {
		   Map<String, Object> paramMap = new HashMap<String, Object>();
	        Long systemUserId = ((Admin) session.getAttribute("adminUser")).getId();
	        String loginTime = (String) session.getAttribute("loginTime");
	        paramMap.put("systemUserId", systemUserId);
	        paramMap.put("operateDesc", operateDesc);
	        paramMap.put("loginTime", loginTime);
	        paramMap.put("operateTime", operateTime);
	        return adminMapper.insertLog(paramMap) == 1;
	}

}

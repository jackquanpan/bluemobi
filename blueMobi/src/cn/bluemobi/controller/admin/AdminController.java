package cn.bluemobi.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bluemobi.controller.BaseController;
import cn.bluemobi.entity.Admin;
import cn.bluemobi.entity.Group;
import cn.bluemobi.entity.system.Page;
import cn.bluemobi.service.AdminService;
import cn.bluemobi.service.GroupService;
import cn.bluemobi.util.date.CalendarUtil;
import cn.bluemobi.util.text.TextHelper;

/**
 * 
 * Description: 管理员配置<br/>
 * date: 2015-8-12 下午3:43:22 <br/>
 * 
 * @author oscar_000
 * @version
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private GroupService groupService;

    /**
     * by dingyl 跳转authorityList
     * 
     * @return
     */
    @RequestMapping(value = "/findAuthorityList")
    public String findAuthorityList() {
        List<Group> list = groupService.getAllGroups();
        request.setAttribute("groupList", list);

        return "admin/user_manage/authorityList.jsp";
    }

    /**
     * 新增预设
     * 
     * @return
     */
    @RequestMapping(value = "/groupAdd")
    public String groupAdd() {
        return "admin/user_manage/groupAdd.jsp";
    }

    /**
     * 编辑预设
     * 
     * @return
     */
    // @RequestMapping(value = "/groupEdit")
    // public String groupEdit(@RequestParam("id") Long id) {
    // return "admin/user_manage/groupEdit.jsp";
    // }

    /**
     * 管理员角色列表
     * 
     * @author liyl
     * @return
     */
    @RequestMapping(value = "/findRoleSetPage")
    public String findRoleSetPage() {
        return "admin/user_manage/roleSetList.jsp";
    }

    /**
     * by dingyl 获取所有管理员信息
     * 
     * @param pageNo
     * @param pageSize
     * @param admin
     */
    @RequestMapping(value = "/getAllAdmins")
    public void getAllAdmins(@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize,
            @ModelAttribute Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            List<Admin> list = adminService.getAllAdmins(admin, page);

            if (list != null && list.size() > 0) {
                for (Admin item : list) {
                    if (!TextHelper.isNullOrEmpty(item.getCreateDate())) {
                        item.setCreateDate(item.getCreateDate().substring(0, 10));
                    }
                }
            } else {
                list = new ArrayList<Admin>();
            }
            map.put(STATUS, SUCCESS);
            map.put("rows", list);
            map.put("total", page.getTotalCount());
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * by dingyl 跳转authorityAdd
     * 
     * @return
     */
    @RequestMapping(value = "/findAuthorityAdd")
    public String findAuthorityAdd() {
        List<Group> list = groupService.getAllGroups();
        request.setAttribute("groupList", list);
        return "admin/user_manage/authorityAdd.jsp";
    }

    /**
     * by dingyl 新增管理员
     * 
     * @param admin
     */
    @RequestMapping(value = "/addAdmin")
    public void add(@ModelAttribute Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            admin.setCreateDate(CalendarUtil.formatDate(new Date()));
            adminService.add(admin);
            map.put(STATUS, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * by dingyl 核查该管理员是否已存在
     * 
     * @param account
     */
    @RequestMapping(value = "/checkNameOnly")
    public void checkNameOnly(@RequestParam String loginName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int num = adminService.checkAdmin(loginName);
            if (num > 0) {
                map.put("rows", "exist");
            } else {
                map.put("rows", "unexist");
            }
            map.put(STATUS, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * by dingyl 根据管理员编号查看管理员信息
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/getAdminById")
    public String getAdminById(@RequestParam long id, @RequestParam int checkStatus) {
        try {
            Admin admin = adminService.getAdminById(id);
            request.setAttribute("adminById", admin);
            if (checkStatus == 0) {
                List<Group> groupList = groupService.getAllGroups();
                request.setAttribute("groupList", groupList);
                return "admin/user_manage/authorityEdit.jsp";
            } else {
                return "admin/user_manage/authorityDetail.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_VIEW;
        }
    }

    /**
     * by dingyl 修改管理员信息
     * 
     * @param admin
     */
    @RequestMapping(value = "/updateAdmin")
    public void update(@ModelAttribute Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            adminService.update(admin);
            map.put(STATUS, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * by dingyl 删除管理员信息
     * 
     * @param id
     */
    @RequestMapping(value = "/delete")
    public void delete(@RequestParam String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                adminService.delete(Long.parseLong(id[i]));
            }
            map.put(STATUS, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * by dingyl 登录
     * 
     * @param admin
     */
    @RequestMapping(value = "/checkAdmin")
    public void checkAdmin(@ModelAttribute Admin admin) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Admin adminLogin = (Admin) session.getAttribute("adminUser");
            if (adminLogin.getAccount().equals(admin.getAccount())
                    && adminLogin.getPassword().equals(admin.getPassword())) {
                map.put("check", "exist");
            } else {
                map.put("check", "unexist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * 获取权限预设列表
     * 
     * @param pageNo
     * @param pageSize
     */
    @RequestMapping(value = "/getAllGroup")
    public void getAllGroup(@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Page page = new Page();
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            List<Group> list = groupService.getAllGroups();

            if (list != null && list.size() > 0) {

            } else {
                list = new ArrayList<Group>();
            }
            map.put(STATUS, SUCCESS);
            map.put("rows", list);
            map.put("total", page.getTotalCount());
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * @author liyl 新增预设角色
     * @param admin
     */
    @RequestMapping(value = "/addGroup")
    public void addGroup(@RequestParam String groupName, @RequestParam String authCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!TextHelper.isNullOrEmpty(groupName)) {
                int i = 0;
                i = groupService.isExist(groupName);
                if (i > 0) {
                    map.put(STATUS, EXIST);
                } else {
                    int result = 0;
                    result = groupService.addGroup(groupName, authCode);
                    if (result > 0) {
                        map.put(STATUS, SUCCESS);
                    } else {
                        map.put(STATUS, FAIL);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * @author liyl 更新预设角色
     * @param admin
     */
    @RequestMapping(value = "/updateGroup")
    public void updateGroup(@RequestParam String groupName, @RequestParam String authCode, @RequestParam Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (!TextHelper.isNullOrEmpty(groupName)) {
                int i = 0;
                i = groupService.isExist(groupName, id);
                if (i > 0) {
                    map.put(STATUS, EXIST);
                } else {
                    int result = 0;
                    result = groupService.updateGroup(groupName, authCode, id);
                    if (result > 0) {
                        map.put(STATUS, SUCCESS);
                    } else {
                        map.put(STATUS, FAIL);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, ERROR);
        } finally {
            outJSON(map);
        }
    }

    /**
     * 删除分组
     * 
     * @param id
     */
    @RequestMapping("/deleteGroup")
    public void deleteGroup(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "error");
        try {
            int result = 0;
            result = groupService.deleteGroup(id);
            if (result > 0) {
                map.put("status", SUCCESS);
            } else {
                map.put("status", FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 输出
            outJSON(map);
        }
    }

    /**
     * 编辑预设
     * 
     * @return
     */
    @RequestMapping(value = "/groupEdit")
    public String groupEdit(@RequestParam("id") Long id) {
        Group group = groupService.findGroupById(id);
        String authCode = "0";
        if (!TextHelper.isNullOrEmpty(groupService.getGroupAuthCode(id))) {
            authCode = "\"" + groupService.getGroupAuthCode(id) + "\"";
        }
        request.setAttribute("group", group);
        request.setAttribute("auchCode", authCode);
        return "admin/user_manage/groupEdit.jsp";
    }

}

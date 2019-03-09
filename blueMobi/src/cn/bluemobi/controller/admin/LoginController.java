package cn.bluemobi.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.bluemobi.constant.BlueMobiConstant;
import cn.bluemobi.controller.BaseController;
import cn.bluemobi.entity.Admin;
import cn.bluemobi.entity.Authority;
import cn.bluemobi.service.AdminService;
import cn.bluemobi.util.text.TextHelper;

/**
 * 登录管理
 * 
 * @author lvxh
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/admin")
public class LoginController extends BaseController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 进入登录页面
     * 
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "/admin/login.jsp";
    }

    /**
     * 退出登录
     * 
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        session.invalidate();
        return "/admin/login.jsp";
    }

    /**
     * 登录提交
     * 
     * @return
     */
    @RequestMapping("/login/submit")
    public String submit(Admin admin, RedirectAttributes redirectAttributes, String code) {
        try {
            if (TextHelper.isNullOrEmpty(admin.getAccount()) || TextHelper.isNullOrEmpty(admin.getPassword())) {
                redirectAttributes.addFlashAttribute("info", "exception");
                return "redirect:" + basePath + "/admin/login.htm";
            }
            if (code == null || !code.equals(session.getAttribute("authCode").toString())) {
                redirectAttributes.addFlashAttribute("info", "codeError");
                return "redirect:" + basePath + "/admin/login.htm";
            }
            admin = adminService.login(admin);
            if (admin == null) {
                redirectAttributes.addFlashAttribute("info", "error");
                return "redirect:" + basePath + "/admin/login.htm";
            }
            session.setAttribute(BlueMobiConstant.ADMIN_USER, admin);
             //记录登录时间
            session.setAttribute("loginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } catch (Exception e) {
            logger.error(" login failed:::" + e.getMessage());
            e.printStackTrace();
            return ERROR_VIEW_ADMIN;
        }
        return "redirect:" + basePath + "/admin/main.htm";
    }

    @RequestMapping("/main")
    public String left() {
        Admin admin = (Admin) session.getAttribute(BlueMobiConstant.ADMIN_USER);
        // 查找此管理员的所有权限
        List<Authority> list = adminService.findAuthority(admin);
        request.setAttribute("modules", list);
        String alist = adminService.findToegangstoken(admin);
        logger.info("admin Authority:::" + alist);
        request.getSession().setAttribute("toegangstoken", alist);
        return "admin/main.jsp";
    }

    /**
     * 密码修改
     * */
    @RequestMapping("/updatePwd")
    public void updatePwd(String current_pwd, String new_pwd, String new_pwd_sure) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "error");
        try {
            Admin admin = (Admin) session.getAttribute(BlueMobiConstant.ADMIN_USER);
            if (new_pwd_sure.equals(new_pwd)) {
                if (current_pwd.equals(admin.getPassword())) {
                    int b = 0;
                    b = adminService.updateAdminPwd(admin.getId(), new_pwd);
                    if (b > 0) {
                        map.put("status", "success");
                        // 更新session中的管理员信息
                        admin.setPassword(new_pwd);
                        session.setAttribute(BlueMobiConstant.ADMIN_USER, admin);
                    }
                } else {
                    map.put("status", "current_pwd_error");// 当前密码错误
                }
            } else {
                map.put("status", "pwd_sure_error");// 重复密码错误
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            outJSON(map);
        }
    }

    /**
     * 生成验证码
     */
    @RequestMapping("/auth/code")
    public String authCode() {
        return "/admin/auth_code/code.jsp";
    }

    @RequestMapping("admin/test")
    public String first() {
        return "admin/test.jsp";
    }

}

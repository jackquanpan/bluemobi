package cn.bluemobi.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import cn.bluemobi.util.helper.JsonHelper;

/**
 * 父类控制器，为所有子类控制器提供公共方法
 * 
 * @author 雷攀
 * 
 */
public class BussinessController {

    /**
     * 后台错误视图
     */
    protected final static String ERROR_VIEW_BUSSINESS = "/bussiness/error/404.jsp";

    // protected final static String BUSSINESS_LOGIN_URL =
    // "redirect:"+Config.get().getDomain()+"bussiness/login.htm";

    protected final static int SUCCESS = 1;// 成功

    protected final static int FAIL = 0;// 失败

    /**
     * ajax请求或者客户端请求,将返回的json数据
     * 
     * @param obj
     */
    public void outJSON(Object obj) {
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1) {
                out.print(obj);
            } else {
                out.write(JsonHelper.convertToJSON(obj, true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                new Exception(e);
            }
        }
    }

    protected HttpServletRequest request = null;

    protected HttpServletResponse response = null;

    protected HttpSession session = null;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
}

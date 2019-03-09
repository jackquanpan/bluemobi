package cn.bluemobi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.bluemobi.constant.BlueMobiConstant;
import cn.bluemobi.util.helper.TextHelper;

/**
 * 权限控制
 * 
 * @author 雷攀
 * 
 */
public class AuthenticationFilter implements Filter {
    private String mappingAdminURL;// 后台拦截

    private String mappingBusURL;// 商户拦截

    private String mappingAppUrl;// 客户端拦截

    private String mappingImageUrl;// 图片地址的拦截

    private String mappingPictrueUrl;// 图片地址的拦截

    private String excludeMappingUrl;// 不用拦截URL

    private String mappingStyURL;// 设计师拦截

    public void destroy() {
    }

    /**
     * 对系统中各种权限进行判断
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //获取用户ip
        String IP = request.getRemoteAddr();
        String URI = request.getRequestURI();
        //将ip url录入第三方容器（redis ribbonMq）
        
        try {
            String url = request.getRequestURL().toString();

            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/";

            if (url.matches(excludeMappingUrl)) {// 不用过滤权限
                chain.doFilter(request, response);
            } else if (url.matches(mappingAdminURL)) {// 后台权限
                if (request.getSession().getAttribute(BlueMobiConstant.ADMIN_USER) == null) {
                    // chain.doFilter(request, response);
                    response.sendRedirect(basePath + "admin/login.htm");
                } else {
                    chain.doFilter(request, response);
                }
            } else if (url.matches(mappingBusURL)) {// 商户权限
                if (request.getSession().getAttribute(BlueMobiConstant.BUSSINESS_USER) == null) {
                    // chain.doFilter(request, response);
                    response.sendRedirect(basePath + "bussiness/login.htm");
                } else {
                    chain.doFilter(request, response);
                }
            } else if (url.matches(mappingAppUrl)) {// 客户端接口
                chain.doFilter(request, response);
            } else if (url.matches(mappingImageUrl)) {// 宽度固定图片
                String temp = url.substring(url.lastIndexOf("_"));
                String beforeStr = url.substring(0, url.lastIndexOf("/"));
                String afterStr = url.substring(url.lastIndexOf("/")).replace(temp, "");
                response.sendRedirect(beforeStr + "/" + TextHelper.getNum(temp) + afterStr);
            } else if (url.matches(mappingPictrueUrl)) {// 宽度和高度都定
                String temp = url.substring(url.lastIndexOf("_"));
                String beforeStr = url.substring(0, url.lastIndexOf("/"));
                String afterStr = url.substring(url.lastIndexOf("/")).replace(temp, "");
                response.sendRedirect(beforeStr + "/" + temp.replace("_", "") + afterStr);
            } else if (url.matches(mappingStyURL)) {
                if (request.getSession().getAttribute(BlueMobiConstant.USER) == null) {
                    // chain.doFilter(request, response);
                    response.sendRedirect(basePath + "sty/login.htm");
                } else {
                    chain.doFilter(request, response);
                }
            } else {// 正常请求不做任何拦截
                chain.doFilter(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 过滤器初始化方法
     */
    public void init(FilterConfig config) throws ServletException {
        this.mappingAdminURL = config.getInitParameter("mappingAdminURL");
        this.mappingBusURL = config.getInitParameter("mappingBusURL");
        this.mappingAppUrl = config.getInitParameter("mappingAppUrl");
        this.mappingImageUrl = config.getInitParameter("mappingImageUrl");
        this.mappingPictrueUrl = config.getInitParameter("mappingPictrueUrl");
        this.excludeMappingUrl = config.getInitParameter("excludeMappingUrl");
        this.mappingStyURL = config.getInitParameter("mappingStyURL");
    }

}
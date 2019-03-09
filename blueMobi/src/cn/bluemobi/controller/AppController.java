package cn.bluemobi.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.bluemobi.entity.system.AppData;
import cn.bluemobi.exception.DataAccessException;
import cn.bluemobi.exception.SystemException;
import cn.bluemobi.util.helper.JsonHelper;
import cn.bluemobi.util.helper.ValidateHelper;

/**
 * 父类控制器，为所有子类控制器提供公共方法
 * 
 * @author 雷攀
 * 
 */
public class AppController extends BaseController
{
    
    public final static int FAIL = 0;// 失败
    
    public final static int SUCCESS = 1;// 成功
    
    protected final static String PARAM_ERROR = "param_error";//参数错误
    
    protected final static String PARAM_NULL = "param_null";//参数为空
    
    protected final static String PARAM_ILLEGAL = "param_illegal";//参数不符合规范
    
    protected final static String PARAM_EXIST = "param_exist";//参数已存在
    
    protected final static String PARAM_NOT_EXIST = "param_not_exist";//参数不存在
    
    protected final static String RESULT_EMPTY = "result_empty";//结果为空
    
    protected final static String USER_NOT_EXIST = "user_not_exist";//用户不存在
    
    /**
     * app端调用接口服务器异常
     */
    protected final static String SERVER_ERROR = "server_error";
    
    /**
     * app端url有误
     */
    protected final static String URL_ERROR = "url_error";
    
    /**
     * app端未知异常
     */
    protected final static String UNKNOW_ERROR = "unknow_error";
    
    /**
     * app端服务接口数据层异常
     */
    protected final static String DATA_ACCESS_ERROR = "data_access_error";
    
    /**
     * 系统运行异常
     */
    protected final static String SYSTEM_ERROR = "system_access_error";
    
    /**
     * 客户端请求异常
     */
    protected final static String APP_REQUEST_ERROR = "app_request_error";
    
    /**
     * ajax请求或者客户端请求,将返回的json数据
     * 
     * @param obj
     */
    public void outJSON(Object obj)
    {
        PrintWriter out = null;
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1)
            {
                out.print(obj);
            }
            else
            {
                out.write(JsonHelper.convertToJSON(obj, true));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.flush();
                    out.close();
                }
            }
            catch (Exception e)
            {
                new Exception(e);
            }
        }
    }
    
    /**
     * ajax请求或者客户端请求,将返回的json数据
     * 
     * @param obj
     */
    public void outJSON(Object obj, boolean flag)
    {
        PrintWriter out = null;
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1)
            {
                out.print(obj);
            }
            else
            {
                out.write(JsonHelper.convertToJSON(obj, flag));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.flush();
                    out.close();
                }
            }
            catch (Exception e)
            {
                new Exception(e);
            }
        }
    }
    
    /**
     * 获取参数
     * @param key
     * @return
     * @throws UnsupportedEncodingException 
     */
    protected String getParameter(String key) throws UnsupportedEncodingException
    {
        Object obj = null;
        String objStr = (obj = request.getAttribute(key)) == null ? request.getParameter(key) : obj.toString();
        
        if (request.getMethod().equals("POST"))
        {
            return (obj = request.getAttribute(key)) == null ? request.getParameter(key) : obj.toString();
        }
        if (!ValidateHelper.isNullOrEmpty(objStr))
            return new String(objStr.getBytes("ISO8859_1"), "UTF-8");
        else
            return null;
    }
    
    /**
     * 格式化时间
     * @param date 日期
     * @param pattern 格式
     * @return 时间字符串
     */
    public String formatDate(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 格式化时间
     * @param date 日期
     * @return 时间字符串
     */
    public String formatDate(Date date)
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    
    private Logger logger = null;
    
    @SuppressWarnings("rawtypes")
    protected void exception(Exception e, Class clazz)
    {
        AppData cr = new AppData();
        cr.setStatus(FAIL);
        logger = Logger.getLogger(clazz.getName());
        try
        {
            if (e instanceof DataAccessException)
            {// 数据库层操作异常
                logger.error(clazz.getName() + " DataAccessException error:", e);
                cr.setMsg(DATA_ACCESS_ERROR);
            }
            else if (e instanceof SystemException)
            {// 运行时异常
                logger.error(clazz.getName() + " SystemException error:", e);
                cr.setMsg(SYSTEM_ERROR);
            }
            else if (e instanceof NullPointerException)
            {
                logger.error(clazz.getName() + " NullPointerException error:", e);
                cr.setMsg(PARAM_ERROR);
            }
            else
            {// 其他异常
                logger.error(clazz.getName() + " Exceipton error:", e);
                cr.setMsg(UNKNOW_ERROR);
            }
        }
        catch (Exception e2)
        {
            logger.error(clazz.getName() + " appRequest error:", e);
            cr.setMsg(APP_REQUEST_ERROR);
        }
        finally
        {
            e.printStackTrace();
            outJSON(cr);
        }
        
    }
    
    protected HttpServletRequest request = null;
    
    protected HttpServletResponse response = null;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
    }
}

/**
 * 文件名  ：PropUtils.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.util.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件信息
 * 功能详细描述
 * @author huad
 * version [版本号，2015年4月16日]
 * see     [相关类/方法]
 * since   [产品/模块版本]
 */
public class PropUtils
{
    /** 默认配置文件路径 */
    protected final static String DEFAULTFILEPATH = "/constant.properties";
    
    public static String getProperty(String key, String filePath)
    {
        Properties p = new Properties();
        
        InputStream is = PropUtils.class.getResourceAsStream(filePath);
        try
        {
            p.load(is);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return p.getProperty(key);
    }
    
    /**
     * 
     * 用系统默认的文件路径
     * 功能详细描述
     * @param key
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getProperty(String key)
    {
        return getProperty(key, DEFAULTFILEPATH);
    }
    
}

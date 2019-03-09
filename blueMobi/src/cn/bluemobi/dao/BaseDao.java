package cn.bluemobi.dao;

import java.util.HashMap;
import java.util.List;

import cn.bluemobi.entity.system.Page;

/**
 * 通用的baseDao接口服务
 * 
 * @author 雷攀
 * 
 */
public interface BaseDao
{
    /**
     * 批量执行sql语句,只执行单一动作的批量操作
     * @param sqlAndParamsList
     * 
     * sqlAndParamsList:该对象格式为[{"sql":"","params":""},{{"sql":"","params":""}}]
     * 
     * @return
     */
    boolean batchExecuteByParams(List<HashMap<String, Object>> sqlAndParamsList);
    
    /**
     * 批量执行sql语句
     * @param sql
     * @param objects
     * @return
     */
    int batchExecuteByParams(String sql, Object[] array);
    
    /**
     * 保存一个对象并且返回主键值
     * 
     * @param sql
     *            sql语句
     * @param obj
     *            保存的对象
     * @return
     */
    public long saveGetKey(String sql, Object obj);
    
    /**
     * 通用的增加，删除，修改方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param params
     *            : 要操作的对象
     * @return 受影响的行数
     * **/
    public int executeByObject(String sql, Object object);
    
    /**
     * 通用的增加，删除，修改方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param params
     *            : 要操作参数
     * @return 受影响的行数
     * **/
    public int executeByParams(String sql, Object... params);
    
    /**
     * 描述:查询单个对象的方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param objClass
     *            :封装类
     * @param params
     *            : 参数
     * @return
     * **/
    public <T> T findForObject(String sql, Class<T> objClass, Object... params);
    
    /**
     * 描述:查询单个对象的方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param objClass
     *            :封装类
     * @param params
     *            : 参数
     * @return
     * **/
    public <T> T queryForObject(String sql, Class<T> clazz, Object... objects);
    
    /**
     * 描述:查询单个对象的方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param params
     *            : 参数
     * @return
     * **/
    public int findForInt(String sql, Object... params);
    /**
     * 获取主键Id的方法
     * @param sql
     * @param params
     * @return
     */
    public Long findForLong(String sql, Object... params);
    
    /**
     * 描述:查询数据的方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param clazz
     *            :封装类
     * @param params
     *            : 参数
     * @return
     * **/
    public <T> List<T> findForList(String sql, Class<T> clazz, Object... params);
    
    /**
     * 描述:分页查询数据的方法
     * 
     * @param page
     *            : 分页属性对象
     * @param sql
     *            : 查询SQL语句
     * @param clazz
     *            :封装类
     * @param params
     *            : 参数
     * @return
     * **/
    public <T> List<T> findByPage(String sql, Page page, Class<T> clazz, Object... params);
    
    /**
     * 描述:计算页面总记录数方法
     * 
     * @param sql
     *            : 查询SQL语句
     * @param params
     *            : 参数
     * **/
    public void findPageSum(String sql, Page page, Object... params);
    
    /**
     * 批量执行sql语句
     * @param sqls
     * @return
     */
    public int[] executeBatch(String[] sqls);
    
    /**
     * 
     * 更新语句
     * 功能详细描述
     * @param sql
     * @param param
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int update(String sql, Object... param);
}

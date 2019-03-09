package cn.bluemobi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import cn.bluemobi.dao.AppBaseDao;
import cn.bluemobi.entity.system.Page;

/**
 * 
 * 实现通用的增删改查方法
 * @author 彭涛
 *
 */
@Component
public class AppBaseDaoImpl implements AppBaseDao
{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Map<String, Object> findMyMap(String sql)
    {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> map = new HashMap<String, Object>();
        if (list.size() > 0)
        {
            map = list.get(0);
            return map;
        }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> findList(String sql)
    {
        return jdbcTemplate.queryForList(sql);
    }
    
    @Override
    public List<Map<String, Object>> findPageList(String sql, Page page)
    {
        sql = paginationSql(sql, page);
        return jdbcTemplate.queryForList(sql);
    }
    
    public int findForInt(String sql, Object... objects)
    {
        return jdbcTemplate.queryForInt(sql, objects);
    }
    
    public Map<String, Object> findMap(String sql, Object... args)
    {
        return jdbcTemplate.queryForMap(sql, args);
    }
    
    public int executeByParams(String sql, Object... objects)
    {
        return jdbcTemplate.update(sql.toString(), objects);
    }
    
    public int executeByObject(String sql, Object object)
    {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(object);
        return jdbcTemplate.update(sql, ps);
    }
    
    public long saveGetKey(String sql, Object obj)
    {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(obj);
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, ps, key);
        return key.getKey().longValue();
    }
    
    /**
     * 描述:生成分页的SQL语句方法
     * 
     * @param sql
     *            :
     * @param page
     *            :
     * **/
    private String paginationSql(String sql, Page page)
    {
        StringBuffer paginationSql = new StringBuffer(20);
        if (page.getPageNo() == 1)
        {
            paginationSql.append(sql + " limit 0," + page.getPageSize());
        }
        else
        {
            paginationSql.append(sql + " limit " + (((page.getPageNo() - 1) * page.getPageSize())) + ","
                + page.getPageSize());
        }
        return paginationSql.toString();
    }
    
}

package cn.bluemobi.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import cn.bluemobi.dao.BaseDao;
import cn.bluemobi.entity.system.Page;

/**
 * 
 * 实现通用的增删改查方法
 * 
 * @author 雷攀
 * 
 */
@Component
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SimpleJdbcTemplate simpleJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean batchExecuteByParams(List<HashMap<String, Object>> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        HashMap<String, Object> map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            executeByParams(map.get("sql").toString(), map.get("params"));
        }
        return true;
    }

    public int batchExecuteByParams(String sql, Object[] array) {
        final Object[] params = array;
        return simpleJdbcTemplate.update(sql, new BatchPreparedStatementSetter() {
            @Override
            public int getBatchSize() {
                if (params == null || params.length == 0) {
                    return 0;
                }
                return params.length;
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if (params != null && params.length != 0) {
                    ps.setObject(i, params[i]);
                }
            }
        });
    }

    public long saveGetKey(String sql, Object obj) {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(obj);
        KeyHolder key = new GeneratedKeyHolder();
        simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, ps, key);
        return key.getKey().longValue();
    }

    public int executeByParams(String sql, Object... objects) {
        return simpleJdbcTemplate.update(sql.toString(), objects);
    }

    public int executeByObject(String sql, Object object) {
        SqlParameterSource ps = new BeanPropertySqlParameterSource(object);
        return simpleJdbcTemplate.update(sql, ps);
    }

    public <T> T findForObject(String sql, Class<T> clazz, Object... objects) {
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(clazz);
        List<T> list = simpleJdbcTemplate.query(sql, mapper, objects);

        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> T queryForObject(String sql, Class<T> clazz, Object... objects) {
        try {
            return jdbcTemplate.queryForObject(sql, objects, clazz);
        } catch (DataAccessException d) {
            return null;
        }
    }

    public <T> List<T> findForList(String sql, Class<T> clazz, Object... objects) {
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(clazz);
        List<T> list = simpleJdbcTemplate.query(sql, mapper, objects);
        if (list != null && list.size() != 0) {
            return list;
        }
        return null;
    }

    public <T> List<T> findByPage(String sql, Page page, Class<T> clazz, Object... params) {
        sql = paginationSql(sql, page);
        RowMapper<T> mapper = new BeanPropertyRowMapper<T>(clazz);
        List<T> list = simpleJdbcTemplate.query(sql, mapper, params);
        if (list != null && list.size() != 0) {
            return list;
        }
        return null;
    }

    public int findForInt(String sql, Object... objects) {
        try {
            return simpleJdbcTemplate.queryForInt(sql, objects);
        } catch (Exception e) {
            return 0;
        }
    }

    public Long findForLong(String sql, Object... objects) {
        try {
            return simpleJdbcTemplate.queryForLong(sql, objects);
        } catch (Exception e) {
            return 0L;
        }
    }

    public void findPageSum(String sql, Page page, Object... params) {
        countPageSum(page, simpleJdbcTemplate.queryForInt(sql, params));
    }

    /**
     * 描述:生成分页的SQL语句方法
     * 
     * @param sql :
     * @param page :
     * **/
    private String paginationSql(String sql, Page page) {
        StringBuffer paginationSql = new StringBuffer(20);
        if (page.getPageNo() == 1) {
            paginationSql.append(sql + " limit 0," + page.getPageSize());
        } else {
            paginationSql.append(sql + " limit " + (((page.getPageNo() - 1) * page.getPageSize())) + ","
                    + page.getPageSize());
        }
        return paginationSql.toString();
    }

    private void countPageSum(Page page, int totalCount) {
        page.setTotalCount(totalCount);
        if (totalCount % page.getPageSize() != 0) {
            page.setPageCount(totalCount / page.getPageSize() + 1);
        } else {
            page.setPageCount(totalCount / page.getPageSize());
        }
        if (page.getPageNo() > page.getPageCount()) {
            page.setPageNo(1);
        }
    }

    /**
     * 
     * @description 批量执行
     * @param sqls
     * @return
     */
    public int[] executeBatch(String[] sqls) {
        return simpleJdbcTemplate.getJdbcOperations().batchUpdate(sqls);
    }

    @Override
    public int update(String sql, Object... param) {
        return simpleJdbcTemplate.update(sql, param);

    }
}

package cn.bluemobi.util.common;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 
 * Description: 公共环境常量类<br/>
 * date: 2015-7-7 下午1:37:32 <br/>
 * 
 * @author oscar_000
 * @version
 */
public class ApplicationProperties {

    private String domain;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String siteName;

    // @Autowired
    private List<MapperScannerConfigurer> mapperConfigs;

    public static Map<String, SqlSessionFactory> sqlSession = new ConcurrentHashMap<String, SqlSessionFactory>();

    public List<MapperScannerConfigurer> getMapperConfigs() {
        return mapperConfigs;
    }

    public void setMapperConfigs(List<MapperScannerConfigurer> mapperConfigs) {
        this.mapperConfigs = mapperConfigs;
    }

    public static Map<String, SqlSessionFactory> getSqlSession() {
        return sqlSession;
    }

    public static void setSqlSession(Map<String, SqlSessionFactory> sqlSession) {
        ApplicationProperties.sqlSession = sqlSession;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public SqlSessionFactory getSession(String queryID) {
        SqlSessionFactory sqlSessionFactory = null;
        Set<String> set = ApplicationProperties.sqlSession.keySet();
        if (set.size() != 0) {
            sqlSessionFactory = getSqlSession(queryID);
        } else {
            try {
                for (MapperScannerConfigurer mapperConfig : mapperConfigs) {
                    Field[] fileds = mapperConfig.getClass().getDeclaredFields();
                    String key = "";
                    SqlSessionFactory sqlSess = null;
                    for (Field field : fileds) {
                        field.setAccessible(true);
                        if (field.getName().equals("basePackage")) {
                            key = field.get(mapperConfig).toString().replace("cn.bluemobi.**", "");
                        }
                        if ("sqlSessionFactory".equals(field.getName())) {
                            sqlSess = (SqlSessionFactory) field.get(mapperConfig);
                        }

                        field.setAccessible(false);
                    }
                    if (!key.equals("") && sqlSess != null) {
                        ApplicationProperties.sqlSession.put(key, sqlSess);
                    }
                }
                sqlSessionFactory = getSqlSession(queryID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sqlSessionFactory;
    }

    public SqlSessionFactory getSqlSession(String queryID) {
        SqlSessionFactory sqlSessionFactory = null;
        Set<String> set = ApplicationProperties.sqlSession.keySet();
        if (set.size() != 0) {
            for (String key : set) {
                if (queryID.toLowerCase().indexOf(key) >= 0) {
                    sqlSessionFactory = ApplicationProperties.sqlSession.get(key);
                    break;
                }
            }
        }
        return sqlSessionFactory;
    }

}

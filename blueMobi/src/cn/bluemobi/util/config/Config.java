package cn.bluemobi.util.config;

/**
 * 系统核心配置类
 * 
 * @author 雷攀
 * 
 */
public class Config {
    private String domain;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String stmp;

    private String sendEmail;

    private String sendEmailPassword;

    private String reviceEmail;

    private String revicePassword;

    private String port;

    private String siteName;

    private static Config config = null;

    private Config() {
    }

    public static Config get() {
        if (config == null) {
            config = ConfigUtil.init(new Config());
        }
        return config;
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

    public String getStmp() {
        return stmp;
    }

    public void setStmp(String stmp) {
        this.stmp = stmp;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getSendEmailPassword() {
        return sendEmailPassword;
    }

    public void setSendEmailPassword(String sendEmailPassword) {
        this.sendEmailPassword = sendEmailPassword;
    }

    public String getReviceEmail() {
        return reviceEmail;
    }

    public void setReviceEmail(String reviceEmail) {
        this.reviceEmail = reviceEmail;
    }

    public String getRevicePassword() {
        return revicePassword;
    }

    public void setRevicePassword(String revicePassword) {
        this.revicePassword = revicePassword;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}

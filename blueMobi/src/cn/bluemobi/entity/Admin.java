/**
 * 文件名  ：Admin.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.entity;

import java.io.Serializable;

/**
 * 管理员 功能详细描述
 * 
 * @author huad version [版本号，2015年5月14日] see [相关类/方法] since [产品/模块版本]
 */
public class Admin implements Serializable {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5213683965605890502L;

    /**
     * 主键ID(管理员)
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 所属用户组
     */
    private Long groupId;

    /*
     * by dingyl 添加用户组
     */
    private String groupName;

    private Long adminCount;

    private Long lastDayCount;

    private Long lastMonthCount;

    public Long getLastDayCount() {
        return lastDayCount;
    }

    public void setLastDayCount(Long lastDayCount) {
        this.lastDayCount = lastDayCount;
    }

    public Long getLastMonthCount() {
        return lastMonthCount;
    }

    public void setLastMonthCount(Long lastMonthCount) {
        this.lastMonthCount = lastMonthCount;
    }

    public Long getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Long adminCount) {
        this.adminCount = adminCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    // ==========================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
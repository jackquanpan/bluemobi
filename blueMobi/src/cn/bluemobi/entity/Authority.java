/**
 * 文件名  ：Auth.java
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
 * 权限 功能详细描述
 * 
 * @author huad version [版本号，2015年5月14日] see [相关类/方法] since [产品/模块版本]
 */
public class Authority implements Serializable {
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 7010859370323295909L;

    /**
     * 主键ID(权限)
     */
    private Long id;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 所属父类模块id
     */
    private String parentId;

    /**
     * 对应的链接跳转地址
     */
    private String url;

    //
    // /**
    // * 模块名称
    // */
    // private String moduleName;
    //
    // /**
    // * 权限名称(以逗号隔开)
    // */
    // private String authName;
    //
    // /**
    // * 所属用户组
    // */
    // private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // public String getModuleName() {
    // return moduleName;
    // }
    //
    // public void setModuleName(String moduleName) {
    // this.moduleName = moduleName;
    // }
    //
    // public String getAuthName() {
    // return authName;
    // }
    //
    // public void setAuthName(String authName) {
    // this.authName = authName;
    // }
    //
    // public Long getGroupId() {
    // return groupId;
    // }

    // public void setGroupId(Long groupId) {
    // this.groupId = groupId;
    // }

}
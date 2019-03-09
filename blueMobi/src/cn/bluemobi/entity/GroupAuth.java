package cn.bluemobi.entity;

import java.io.Serializable;

/**
 * 
 * @author liyl
 * 
 */
public class GroupAuth implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1839444076593170122L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 模块ID
     */
    private Long authid;

    /**
     * 分组ID
     */
    private Long groupid;

    private String authCode;

    /**
     * 保留字段
     */
    private String extend1;

    private String extend2;

    private String extend3;

    // =================================
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthid() {
        return authid;
    }

    public void setAuthid(Long authid) {
        this.authid = authid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

}
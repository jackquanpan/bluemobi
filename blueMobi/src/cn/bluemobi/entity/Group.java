/**
 * 文件名  ：Group.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户组 功能详细描述
 * 
 * @author huad version [版本号，2015年5月14日] see [相关类/方法] since [产品/模块版本]
 */
public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8407230570043934463L;

	/**
	 * 主键ID(用户组)
	 */
	private Long id;

	/**
	 * 用户组名称
	 */
	private String groupName;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	// ===================================
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
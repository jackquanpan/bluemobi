package cn.bluemobi.entity.app;

import java.io.Serializable;
import java.util.List;
/**
 * 帖子信息总表
 * @author Administrator
 *
 */

public class TiziInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9184879423838605191L;

	private Long id;//帖子id

    private String status;

    private String content;//帖子文本

    private String publishtime;//发布时间

    private String uname;

    private String headicon;

    private Long userid;

    private String communityname;

    private Long cid;

    private Long praisenum;

    private List<String>urlList;//图片地址

    public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(Long praisenum) {
        this.praisenum = praisenum;
    }

  
}
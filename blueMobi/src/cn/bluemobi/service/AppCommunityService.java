package cn.bluemobi.service;

import java.util.List;
import java.util.Map;

import cn.bluemobi.entity.app.TiziInfo;

public interface AppCommunityService {
      /**
       * Description:创建圈子<br/>
       * @return
       */
	 Map<String,Object>createCommunity(Map<String,Object>parammap);
	 /**
	  * 分页获取帖子信息
	  * @return
	  */
	 List<TiziInfo> getTeiziListByFenYe();
	 /**
	  * 登录app
	  * @param phone
	  * @param pwd
	  * @return
	  */
	 Map<String, Object>LongApp( Map<String, Object>resultMa);
}

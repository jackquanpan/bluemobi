/**
 * Project Name:blueMobi
 * File Name:AppCommunityMapper.java
 * Package Name:cn.bluemobi.mapper.app
 * Date:下午5:07:31
 * Copyright (c) 2019, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.mapper.app;

import java.util.List;
import java.util.Map;

import cn.bluemobi.entity.app.TiziInfo;

/**
 * Description: Date: 下午5:07:31 <br/>
 * 
 * @author panuquanke
 * @version
 * @see
 */
public interface AppCommunityMapper {
	/**
	 * 创建圈子
	 * @param parammap
	 */

    void addCommunity(Map<String,Object>parammap);
    
    /**
     * 
     * Description: 分页获取帖子列表<br/>
     *
     * @author panquanke
     * @return
     */
    List<TiziInfo> getTeiziListByFenYe();
}

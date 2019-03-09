/**
 * Project Name:blueMobi
 * File Name:CommunityController.java
 * Package Name:cn.bluemobi.controller.app
 * Date:下午4:59:46
 * Copyright (c) 2019, bluemobi All Rights Reserved.
 *
*/

package cn.bluemobi.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.bluemobi.controller.AppController;
import cn.bluemobi.entity.app.TiziInfo;
import cn.bluemobi.service.AppCommunityService;

/**
 * Description: Date: 下午4:59:46 <br/>
 * 
 * @author panqunake
 * @version
 * @see
 */
//@Scope(value = "prototype")//多例
@Controller
@RequestMapping("/app/community")
public class CommunityController extends AppController {
      @Autowired
      private AppCommunityService appcommunityservice;
      
    /**
     * 
     * Description: 创建圈子<br/>
     *
     * @author panquanke
     * @return
     */
    @RequestMapping("/createCommunity.htm")
    public void createCommunity(String communityName, int communityType, int roler, Long userId) {
        // 首先校验数据的合理性
        // 2、调用添加圈子的业务层
        // 3、封装结果信息并且返回
    	Map<String,Object>parammap=new HashMap();
    	parammap.put("cName",communityName);
    	parammap.put("ct",communityType);
    	parammap.put("userId", userId);
    	 Map<String, Object> Communitymap= 	appcommunityservice.createCommunity(parammap);
         outJSON(Communitymap);
    }
    @RequestMapping("/getTeiziListByFenYe.htm")
    public void getTeiziByfenye(){
    	List<TiziInfo> teiziListByFenYe = appcommunityservice.getTeiziListByFenYe();
    	outJSON(teiziListByFenYe);
    }
    @RequestMapping("/test.htm")
    public String   test(){
    	return "haha";
    }

}

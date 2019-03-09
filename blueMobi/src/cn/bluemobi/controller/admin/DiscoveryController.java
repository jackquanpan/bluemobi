package cn.bluemobi.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bluemobi.controller.BaseController;
import cn.bluemobi.service.AdminService;
import cn.bluemobi.service.DiscoveryService;
@Controller()
@RequestMapping("/admin")
public class DiscoveryController extends BaseController {
  @Autowired
  private DiscoveryService  discoveryService;
  @Autowired
  private AdminService adminService;
  
	/**
	* 2019年3月4日
	* description
	* author:panquanke
	*/
	@RequestMapping("/findDiscoveryList")
	public String findDiscovery(){
		return "admin/discovery/discoveryList.jsp"; 
	}
	@RequestMapping("/getDiscoveryListByFenYe.htm")
	public void getDiscoverList(@RequestParam(value="page",defaultValue="1")Integer page,
		@RequestParam(value="rows",defaultValue="10")	Integer rows){
		Map<String, Object> discoveryList = discoveryService.getDiscoveryList(page, rows);
		outJSON(discoveryList);
	}
	 @RequestMapping("/addDiscovery")
	    public void addDiscovery(String title, String content, Long publishUserId, String status,HttpSession session) {
		  Map<String, Object> rMap = new HashMap<String, Object>();
		  boolean flag=false;
		 // 1、校验数据的合理性
		 if(title!=null ||content!=null ||status!=null){
			 Map<String, Object> resultMap = new HashMap<String, Object>();
		        resultMap.put("title", title);
		        resultMap.put("content", content);
		        resultMap.put("publishUserId", publishUserId);
		        resultMap.put("status", status);
		       flag = discoveryService.addDisCovery(resultMap);
		       adminService.addLog(session, "添加发现", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		       
		 }
		   // 3、回显结果   
		    rMap.put("msg", "有一项输入不正确");
	        rMap.put("flag", flag);
	        outJSON(rMap);   
	    }
	   /**
	     * 
	     * Description: 批量显示<br/>
	     *
	     * @author panquanke
	     * @param idStr
	     */
	    @RequestMapping("/batchShow")
	    public void batchShow(String idStr) {
	        // 取出掉最后一个",","1,2,3"
	        idStr = idStr.substring(0, idStr.lastIndexOf(","));
	        boolean flag =discoveryService.bachUpdateSatus(idStr);
	        // 回显结果
	        Map<String, Object> rMap = new HashMap<String, Object>();
	        rMap.put("flag", flag);
	        outJSON(rMap);
	    }
	    /**
	     * 
	     * Description: 获取管理员信息列表<br/>
	     *
	     * @author panquanke
	     */
	    @RequestMapping("/findSystemUserList")
	    public void findSystemUserList() {
	        List<Map<String, Object>> systemUserList =discoveryService.findSystemUserList();
	        outJSON(systemUserList);
	    }
	    /**
	     * 
	     * Description: 带条件的发现搜索<br/>
	     *
	     * @author panquanke
	     * @param paramMap
	     * @return
	     */
	    @RequestMapping("/findDiscoveryListByCondition")
	    // public void findDiscoveryListByCondition(@RequestBody Map<String, Object>
	    // paramMap) {
	    public void findDiscoveryListByCondition(String title, Long publishUserId, String status, String startTime,
	            String endTime) {
	        // 校验数据合法性
	        // 调用业务层
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("title", title);
	        paramMap.put("publishUserId", publishUserId);
	        paramMap.put("status", status);
	        paramMap.put("startTime", startTime);
	        paramMap.put("endTime", endTime);
	        System.out.println("paramMap=" + paramMap);
	        List<Map<String, Object>> discoverList = discoveryService.findDiscoveryListByCondition(paramMap);
	        outJSON(discoverList);
	    }
	
}

package cn.bluemobi.controller.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.bluemobi.controller.AppController;
import cn.bluemobi.service.AppCommunityService;
import cn.bluemobi.util.sms.SmsUtil;

/**
 * 发送短信验证码
 * @author Administrators
 *
 */
@Scope(value = "prototype")//多例
@Controller(value = "appLoginController")
@RequestMapping("/app")
public class LoginController extends AppController {
	@Autowired 
	private AppCommunityService appCommunityService;
	@RequestMapping("/getSmsCode.htm")
	public void getSmsCode(@RequestParam(value="cellphone",required=true)String cellphone){
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap=SmsUtil.sendSMS(cellphone);
		outJSON(resultMap);
		
	}
	@RequestMapping("/login.htm")
	public void longinApp(String phone,String password){
		 Map<String, Object> resultMap1 = new HashMap<String, Object>();
		 resultMap1.put("ph", phone);
		 resultMap1.put("pwd", password);
		
		Map<String, Object> resultMap=	appCommunityService.LongApp(resultMap1);
		outJSON(resultMap);
	}
  
}

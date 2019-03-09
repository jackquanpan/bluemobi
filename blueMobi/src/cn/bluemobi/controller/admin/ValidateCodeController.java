/**
 * Project Name:ecommercePlatform
 * File Name:ValidateCodeController.java
 * Package Name:cn.bluemobi.controller.admin
 * Date:2015-7-9上午11:04:34
 * Copyright (c) 2015, bluemobi.cn All Rights Reserved.
 *
 */

package cn.bluemobi.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: 登陆验证码<br/>
 * Date: 2015-7-9 上午11:04:34 <br/>
 * 
 * @author oscar_000
 * @version
 * @see
 */
@Controller
public class ValidateCodeController {

    /**
     * 
     * Description: 登录页面验证码<br/>
     * 
     * @author oscar_000
     * @return
     */
    @RequestMapping("/auth/code")
    public String authCode() {
        return "/admin/auth_code/code.jsp";
    }
}

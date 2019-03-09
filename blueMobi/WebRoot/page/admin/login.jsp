<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../basePath.jsp" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<meta http-equiv="Content-Type" contenttype="text/xml; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"><html>
<head>
<title>登陆页面</title>
<link type="text/css" href="resource/css/loginModel.css" rel="stylesheet" />
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/publicSystem.js"></script>
<link id="easyuiTheme" rel="stylesheet" href="resource/js/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css">
<link type="text/css" rel="stylesheet" href="resource/css/icon.css"></link>
<style>
</style>
<script type="text/javascript">
	$(function(){
		
		if(top.location!=self.location){
			top.location=$("base")[0].href+"admin/login.htm?uuid="+Math.random();
		}
		
		$("#code").attr("src",$("base")[0].href+"auth/code.htm?uuid="+Math.random());
		
		$("#code").bind("click",function(){
			$(this).attr("src",$("base")[0].href+"auth/code.htm?uuid="+Math.random());
		});
		
		
		if("${info}"=="exception"){
			$.messager.show({title:'',msg:'请输入账号、密码和验证码',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else if("${info}"=="error"){
			$.messager.show({title:'',msg:'账号或者密码不正确',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else if("${info}"=="codeError"){
			$("#account").val("${adminUser.account}");			
			$("#password").val("${adminUser.password}");			
			$.messager.show({title:'',msg:'验证码错误',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else{
			$("#account").val("${account}");		
			if("${flag}"!=""){
				$("#checkbox").attr("checked","checked");
			}
		}
		
	});
	
	
	
	function submitValidate(){
		var account=$("#account").val();
		var password=$("#password").val();
		var code=$("#codeStr").val();
		if(account==''){
			$.messager.show({title:'',msg:'请输入账号',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else if(password==''){
			$.messager.show({title:'',msg:'请输入用户密码',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else if(code==''){
			$.messager.show({title:'',msg:'请输入验证码',showType:'fade',timeout:1500,style:{right:'',top:180,bottom:''}});
		}else{	
			$("#loginForm").submit();
		}
	}
	
	//支持回车提交
	$(document).keyup(function(event){
	  if(event.keyCode ==13){
	    $(".login").trigger("click");
	  }
	});
	
</script>	
<style type="text/css">
</style>
</head>
<body class="easyui-layout">
<div id="container" style="margin-left: 15%;">
		<div class="logo" style="margin-left:35%;">
			<a href="#"><img src="imgs/yingwangkeji.png" alt="" style="position: relative; bottom: 50px"/></a>
		</div >
		<div id="box" style="margin-left: 15%;width:70%;overflow: auto">
			<form id="loginForm" action="admin/login/submit.htm" method="post">
			<p class="main">
				<label>账&nbsp;&nbsp;&nbsp;号: </label>
				<input id="account" name="account" maxlength="30" placeholder="请输入账号" /> 
			</p>
			<p class="main">
				<label>密&nbsp;&nbsp;&nbsp;码: </label>
				<input id="password" type="password" maxlength="30" name="password" placeholder="请输入密码">	
			</p>
			<p class="main">
				<label>验证码: </label>
				<input type="text" id="codeStr" name="code" maxlength="4" placeholder="请输入验证码" style="width:100px">&nbsp;&nbsp;
				<img id="code" alt="看不清？点一下" height="28px"">	
			</p>
			<!-- 
			<p class="space">
				<span><input id="checkbox" type="checkbox" name="remaberMe" value="1"/>记住我</span>
			</p>
			 -->
			<p class="space">
				<input type="button" onclick="submitValidate()" value="登录" class="login" style="margin-right: 200px"/>
			</p>
			</form>
		</div>
	</div>
</body>
</html>
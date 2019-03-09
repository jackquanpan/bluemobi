<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>新增发现</title>
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/publicSystem.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<link id="easyuiTheme" rel="stylesheet"
	href="resource/js/jquery-easyui-1.3.5/themes/default/easyui.css"
	type="text/css">
<link type="text/css" rel="stylesheet" href="resource/css/icon.css"></link>
<style>
form {
	margin: 0;
}

textarea {
	display: block;
}
</style>
<link rel="stylesheet" href="<%=basePath %>/editor/themes/default/default.css" />
<script charset="utf-8" src="<%=basePath %>/editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath %>/editor/lang/zh-CN.js"></script>
<script>
			var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
			
			});
		</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'north',title:'上',split:true,noheader:true" style="height:50px;padding-top:10px">
			标题：<input name="title" class="easyui-validatebox" data-options="required:true" />  
			状态:
			<select  name="status" style="width:200px;">   
			    <option value="1">显示</option>   
			    <option value="0">隐藏</option>   
			</select> 
		</div>
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden">
			内容：<form>
				<textarea name="content"
					style="width: 800px; height: 400px; visibility: hidden;">KindEditor</textarea>
				<p>
					<input type="button" name="getHtml" value="取得HTML" /> 
					<input type="button" name="setHtml" value="设置HTML" /> 
				</p>
			</form>
			发布人：${sessionScope.adminUser.account }
			<input name="publishUserId" type="hidden" value="${sessionScope.adminUser.id }">
			<br/>
			<button id="faBu">发布</button>
		</div>
		
		<script type="text/javascript">
			$(function(){
				$("#faBu").click(function(){
					$.ajax({
						url:"<%=basePath %>/admin/addDiscovery.htm",
						type:"POST",
						data:{
							"title":$("input[name=title]").val(),
							"status":$("select[name=status] option:selected").val(),
							"content":editor.html(),
							"publishUserId":$("input[name=publishUserId]").val()
							
						},
						dataType:"JSON",
						success:function(rs){
							if(rs.flag==true){
								window.history.back(-1);
							}else{
								alert(rs.msg)
							}
						}
					});
				});
				
			});
		</script>
</body>
</html>
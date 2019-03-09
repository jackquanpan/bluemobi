<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<%@ include file="../../basePath.jsp"%>
<title>查看权限</title>
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
<script type="text/javascript" src="resource/js/ajaxfileupload.js"></script>


<style>
.niubility {
	color: #444;
	background: #fafafa;
	background-repeat: repeat-x;
	border: 1px solid #bbb;
	background: -webkit-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: -moz-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: -o-linear-gradient(top, #ffffff 0, #eeeeee 100%);
	background: linear-gradient(to bottom, #ffffff 0, #eeeeee 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,
		endColorstr=#eeeeee, GradientType=0);
	-moz-border-radius: 5px 5px 5px 5px;
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
}

.add_user {
	width: 840px;
	margin: 0 auto;
}

#ff label {
	display: block;
	width: 90px;
	line-height: 24px;
	font-size: 12px;
}

#ff label i {
	color: red;
}

.txt-right {
	text-align: right;
}

.ff input {
	border: 1px solid #ccc;
	padding: 3px;
	height: 14px;
	border-radius: 2px;
	width: 180px;
	height: 18px;
}

.ff input[type="radio"] {
	width: 20px;
}

.ff label {
	text-align: right;
}

.ff select {
	height: 25px;
	border-color: #ccc;
	border-width: 1px;
	width: 187px;
}

.ff .b_submit {
	background: #fff;
	color: #000000;
	border: 1px solid #ddd;
	filter: none;
	height: 24px;
	width: 78px;
	padding: 0;
	border-radius: 3px;
	margin-top: 20px;
}

.ff .b_submit:hover {
	background: #eaf2ff;
	color: #000000;
	border: 1px solid #b7d2ff;
	filter: none;
	height: 24px;
	width: 78px;
	padding: 0;
}

table tr td {
	padding: 20px 0px 10px 0px;
}

.datebox-button table tr td {
	padding: 0px;
}

.clearfix:after {
	visibility: hidden;
	display: block;
	font-size: 0;
	content: " ";
	clear: both;
	height: 0
}

.fl {
	float: left;
}

.upload-unit {
	width: 120px;
	height: 120px;
	float: left;
	margin: 0 10px 0 0;
	position: relative;
}

.upload-unit img {
	width: 120px;
	height: 120px;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 9;
}

.add-img {
	display: block;
	position: absolute;
	width: 120px;
	height: 120px;
	line-height: 120px;
	color: #000;
	text-align: center;
}

.ff .files {
	width: 120px;
	height: 120px;
	left: 0;
	top: 0;
	opacity: 0;
	z-index: 19;
	position: absolute;
}

.txt-right {
	text-align: right;
}

.tableList tr th {
	text-align: center;
}

.tableList tr td {
	padding: 5px 0px 5px 0px;
	text-align: center;
}
</style>
</head>
<body class="easyui-layout">
	<div class="add_order" id="Product"
		data-options="region:'center',border:false"
		style="padding-left:50px;padding-right:20px;background:#ffffff;"
		align="left">
		<form id="add" method="post" class="ff"
			data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/queryAllBusiness.htm'">
			<table width="91%" border="0">
				<tr>
					<td colspan="2"><div
							style="padding:3px 3px;border-bottom:1px solid #ccc;font-size:14px;font-weight:bold;">查看权限</div></td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="70%" align="left">
							<tr>
								<td colspan="2" width="30%" class="txt-left"><label for="position">
								<span	style="font-size:14px;">姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </label>
								<input type="text" name="name" id="name" value="${requestScope.adminById.name }" style="border: none;" readonly="readonly" /></td>
							</tr>
							<tr>
								<td colspan="2" class="inputFile">
								<label for="position"><span	style="font-size:14px;">账号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </label>
								<input type="text" name="account" id="account" value="${requestScope.adminById.account }" style="border: none;" readonly="readonly" />
								</td>
							</tr>
							<tr id="titlediv">
								<td  style="text-align:left; width:120px">								
									<label><span style="font-size:14px;">用户组&nbsp;&nbsp;</span></label>
									<input type="text" id="groupName" name="groupName" value="${requestScope.adminById.groupName }"  style="border: none;" readonly="readonly"/>
									</td>
							</tr>							
						</table>						
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left;"><a
						href="javascript:viod(0)" class="easyui-linkbutton" plain="false"
						onclick="back();" data-options="iconCls:'icon-back'"> <font size="2">返回</font>
					</a></td>
				</tr>
			</table>
		</form>

	</div>
	<script type="text/javascript">




function back() {
	parent.addTab("权限列表", $("base")[0].href+ "admin/findAuthorityList.htm");
}


	</script>
</body>
</html>
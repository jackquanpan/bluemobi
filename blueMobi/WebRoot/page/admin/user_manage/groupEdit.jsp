<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<%@ include file="../../basePath.jsp"%>
<title>新增预设</title>
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
	padding: 10px 0px 10px 0px;
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
		<form id="add" method="post" class="ff">		
			<table width="91%" align="left" >
				<tr >
					<td  width="15%" colspan="2"><span style="font-size:14px;"><b>预&nbsp;设&nbsp;名&nbsp;称</b></span></td>
					<td  width="75%" colspan="5"><input id="groupName" name="groupName" type="text" value="${group.groupName}"></td>
					<input type="hidden" id = "id" name="id" value="${group.id}"/>
				</tr>
						<tr style="background:#F0F8FF;" >
						<td ><span style="font-size:14px;">序号</span></td>
						<td ><span	style="font-size:14px; width: 12%">权限列表 </span></td>
						<td colspan="5"><span	style="font-size:14px;">功能名称</span></td>
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:12px;">1</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px; " >系统管理<input id="pro" type="checkbox" name="pro1" value="1" style="display: none" /></span></td>
						<td ><span	style="font-size:12px; width: 15%">	<input id="pro1" type="checkbox" name="pro"  style="width:10%" value="10" onclick="select0(1)"/>查看权限列表</span></td>
						<td ><span	style="font-size:12px;"><input id="pro2" type="checkbox" name="pro"  style="width:10%; " value="25"  onclick="select1(2)"/>编辑权限列表</span></td>					
						<td ><span	style="font-size:12px;"><input id="pro3" type="checkbox" name="pro"  style="width:10%; " value="11" onclick="select0(3)"/>查看预设列表</span></td>
						<td  colspan="2"><span	style="font-size:12px;"><input id="pro4" type="checkbox" name="pro"  style="width:10%; " value="26" onclick="select1(4)"/>编辑预设列表</span></td>
						</tr>
						<!-- <tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">2</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">会员管理<input id="pro2" type="checkbox" name="pro1" value="2" style="display: none" /></span></td>
						<td ><span	style="font-size:12px;"><input id="pro5" type="checkbox" name="pro" style="width:10%; " value="12" onclick="select0(5)"/>查看会员列表</span></td>
						<td ><span	style="font-size:12px;"><input id="pro6" type="checkbox" name="pro"  style="width:10%; " value="27" onclick="select1(6)"/>编辑会员列表</span></td>					
						<td ><span	style="font-size:12px;"><input id="pro7" type="checkbox" name="pro"  style="width:10%;" value="13" onclick="select0(7)"/>查看企业列表</span></td>
						<td  colspan="2"><span	style="font-size:12px;"><input id="pro8" type="checkbox" name="pro"  style="width:10%; " value="28" onclick="select1(8)"/>编辑企业列表</span></td>
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">3</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">兼职管理<input id="pro3" type="checkbox" name="pro1" value="3" style="display: none" /></span></td>
						<td ><span	style="font-size:12px;"><input id="pro9" type="checkbox" name="pro"  style="width:10%; " value="14" onclick="select0(9)"/>查看兼职列表</span></td>
						<td colspan="5"><span style="font-size:12px;"><input id="pro10" type="checkbox" name="pro"  style="width:10%; " value="29" onclick="select1(10)"/>编辑兼职列表</span></td>
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">4</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">资讯管理<input id="pro4" type="checkbox" name="pro1" value="4" style="display: none" /></span></td>
						<td ><span	style="font-size:12px;"><input id="pro11" type="checkbox" name="pro" style="width:10%; " value="15" onclick="select0(11)"/>查看心得</span></td>
						<td ><span	style="font-size:12px;"><input id="pro12" type="checkbox" name="pro" style="width:10%; " value="30" onclick="select1(12)"/>编辑心得</span></td>					
						<td ><span	style="font-size:12px;"><input id="pro13" type="checkbox" name="pro" style="width:10%; " value="16" onclick="select0(13)"/>查看单页</span></td>
						<td  colspan="2"><span	style="font-size:12px;"><input id="pro14" type="checkbox" name="pro" style="width:10%; " value="31" onclick="select1(14)"/>编辑单页</span></td>						
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">5</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">WEB配置<input id="pro5" type="checkbox" name="pro1" value="5" style="display: none" /></span></td>
						<td ><span style="font-size:12px;"><input id="pro15" type="checkbox" name="pro"  style="width:10%; " value="17" />城市列表查看</span></td>
						<td ><span style="font-size:12px;"><input id="pro16" type="checkbox" name="pro"  style="width:10%; " value="18" />工作性质查看</span></td>
						<td ><span style="font-size:12px;"><input id="pro17" type="checkbox" name="pro"  style="width:10%; " value="19" />兼职类型查看</span></td>
						<td ><span style="font-size:12px;"><input id="pro18" type="checkbox" name="pro"  style="width:10%; " value="20" />行业列表查看</span></td>
						<td  colspan="2"><span style="font-size:12px;"><input id="pro" type="checkbox" name="pro"  style="width:10%; " value="32" />web配置编辑</span></td>					
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">6</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">广告管理<input id="pro6" type="checkbox" name="pro1" value="6" style="display: none" /></span></td>
						<td ><span	style="font-size:12px;"><input id="pro20" type="checkbox" name="pro"  style="width:10%; " value="21" onclick="select0(20)"/>广告列表查看</span></td>
						<td colspan="5"><span	style="font-size:12px;"><input id="pro21" type="checkbox" name="pro"  style="width:10%; " value="33" onclick="select1(21)"/>广告列表编辑</span></td>					
						
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">7</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">数据统计<input id="pro7" type="checkbox" name="pro1" value="7" style="display: none" /></span></td>
						<td ><span	style="font-size:12px;"><input id="pro22" type="checkbox" name="pro"  style="width:10%; " value="22" onclick="select0(22)"/>网站收入查看</span></td>
						<td colspan="5"><span	style="font-size:12px;"><input id="pro23" type="checkbox" name="pro"  style="width:10%; " value="34" onclick="select1(23)"/>网站收入编辑</span></td>					
						
						</tr>
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">8</span></td>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">消息管理<input id="pro8" type="checkbox" name="pro1" value="8" style="display: none" /></span></td>						
						<td ><span	style="font-size:12px;"><input id="pro24" type="checkbox" name="pro"  style="width:10%; " value="24" onclick="select0(24)"/>推送列表查看</span></td>
						<td ><span	style="font-size:12px;"><input id="pro25" type="checkbox" name="pro"  style="width:10%; " value="36" onclick="select1(25)"/>消息推送操作</span></td>					
						<td ><span	style="font-size:12px;"><input id="pro26" type="checkbox" name="pro"  style="width:10%; " value="23" onclick="select0(26)"/>消息提醒查看</span></td>
						<td colspan="2"><span	style="font-size:12px;"><input id="pro27" type="checkbox" name="pro"  style="width:10%; " value="35" onclick="select1(27)"/>消息提醒设置</span></td>
						</tr>
						
						<tr>
						<td style="background:#F0F8FF;"><span	style="font-size:14px;">9</span></td>
						<td style="background:#F0F8FF;" ><span	style="font-size:14px;">启动页管理<input id="pro9" type="checkbox" name="pro1" value="9" style="display: none" /> </span></td>						
						<td ><span	style="font-size:12px;"><input id="pro28" type="checkbox" name="pro"  style="width:10%; " value="38" onclick="select0(28)"/>启动页列表查看</span></td>
						<td  colspan="4"><span	style="font-size:12px;"><input id="pro29" type="checkbox" name="pro"  style="width:10%; " value="39" onclick="select1(29)"/>启动页操作</span></td>			
						</tr> -->
			
				<tr>
					<td colspan="7" style="text-align:left;"><a
						href="javascript:viod(0)" class="easyui-linkbutton" plain="false"
						onclick="back();" data-options="iconCls:'icon-back'"> <font
							size="2">返回</font>
					</a> <a href="javascript:;" class="easyui-linkbutton" plain="false"
						onclick="submits();"> <font size="2">确定</font></a></td>
				</tr>
			</table>
		</form>

	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		 var a = $("input[name='pro']");
		 var str = <%=request.getAttribute("auchCode").toString()%>;	
		 if(str != "0"){
		     for(var i = 0;i<a.length;i++){		    	
		      if(str.indexOf(a[i].value) >= 0)
		    	  a[i].checked = true;
		     }
		 }
		   
		});
	  
	function select0(no){
		var a = $("input[id='pro"+no+"']");
		var n1= no+1;	
		if(a[0].checked == false){
		 $("input[id='pro"+n1+"']")[0].checked = false;
		}
	}
		
	function select1(no){
			var a = $("input[id='pro"+no+"']");
			var n2 = no-1;		
			if(a[0].checked == true){
			 $("input[id='pro"+n2+"']")[0].checked = true;
			}
		}
	
function submits() {
	 var id = $("#id").val();   
     var groupName=$("#groupName").val();     
     if (groupName == "") {
		$.messager.alert("提示", "请输入预设名称");
		return false;	
	 }
     var a = $("input[name='pro']:checked").val();
     
     if(a ==null || a==undefined){
    	 $.messager.alert("提示", "预设权限不能为空，请勾选");
 		return false; 
     }
     var qx = $("input[name='pro']:checked").map(function () {
         return $(this).val();
     }).get().join(','); 
	 var qx1 = qx +"";
	 if(qx1.indexOf("10") >= 0||qx1.indexOf("11") >= 0||qx1.indexOf("25") >= 0||qx1.indexOf("26") >= 0){
		    qx = qx+",1"; 
	 }
	 if(qx1.indexOf("12") >= 0||qx1.indexOf("13") >= 0||qx1.indexOf("27") >= 0||qx1.indexOf("28") >= 0){
			qx = qx+",2"; 
	 }
	 if(qx1.indexOf("14") >= 0||qx1.indexOf("29") >= 0){
			qx = qx+",3"; 
	 } 
	 if(qx1.indexOf("15") >= 0||qx1.indexOf("16") >= 0||qx1.indexOf("30") >= 0||qx1.indexOf("31") >= 0){
			qx = qx+",4"; 
	 }
	 if(qx1.indexOf("17") >= 0||qx1.indexOf("18") >= 0||qx1.indexOf("19") >= 0||qx1.indexOf("20") >= 0||qx1.indexOf("32") >= 0){
			qx = qx+",5"; 
	 }
	 if(qx1.indexOf("21") >= 0||qx1.indexOf("33") >= 0){
			qx = qx+",6"; 
	 }
	 if(qx1.indexOf("22") >= 0||qx1.indexOf("34") >= 0){
			qx = qx+",7"; 
	 }
	 if(qx1.indexOf("23") >= 0||qx1.indexOf("35") >= 0||qx1.indexOf("24") >= 0||qx1.indexOf("36") >= 0){
			qx = qx+",8"; 
	 }
	 if(qx1.indexOf("38") >= 0||qx1.indexOf("39") >= 0){
			qx = qx+",9"; 
	 }
	 
	if ($("#add").form("validate")){
		$.messager.progress({msg:"提交中，请稍后..."});
		ajaxFunction($("base")[0].href + "admin/updateGroup.htm",{
			id:id,
			groupName:groupName,
			authCode:qx
		},function(data){
			$.messager.progress('close');
			if(data.status==1){
				$.messager.alert("提示","保存成功","info",function(){
					parent.addTab("预设列表", $("base")[0].href + "admin/findRoleSetPage.htm");					
				});
			}else if(data.status==3){
				$.messager.alert("提示","该预设名称已存在，请重新输入");				
			}else{
				$.messager.alert("提示","新增预设失败");
			}
		});
	}
}



function back() {
	parent.addTab("预设列表", $("base")[0].href+ "admin/findRoleSetPage.htm");
}

	</script>
</body>
</html>
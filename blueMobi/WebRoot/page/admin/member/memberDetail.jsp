<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<%@ include file="../../basePath.jsp"%>
<title>查看用户</title>
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/publicSystem.js"></script>
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
		endColorstr=#eeeeee, GradientType=0 );
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

#container  
        {  
            position: relative;  
            width:100%; /* width + border for IE 5.x */            
              
            border-width: 1 3px;  
            margin: auto;  
            height:100%;  
        }  
          
        #header   
        {  
            background:#ffffff;
            height: 30px; 
            border-left: 400px solid #069;             
        }  
  
          
        #navigation  
        {  
            position: absolute;  
            top: 0px;  
            left: 0px;  
            width: 400px;  
            background-color:white;  
            height:560px;  
        }  
          
        #content   
        {  
            background-color:white;  
            /*padding: 1px 20px 1em 40px;*/  
            top: 30px;  
            border-left: 400px solid #069;/*左边框位置从134px位置开始*/  
            height:500px;  
        }
       
</style>
</head>
<body>
<!--主容器开始-->  
<div id="container"> 
    <!--②左边部分开始-->  
      <div id="navigation">  
       <table border="1px" >
      <tr ><td style="height:12px; padding-top: 0px; width:200px" colspan="2"><span style="font-size:12px" ><b>头像

	</b></span></td>
      	<td style="height:12px; padding-top: 0px; width:200px" colspan="2">
      	<img style=border:0;margin-top:2px;   src="${Resume.photo}"  id="file1img"   width="100" height="100" />
      	</td></tr>
      <tr >
      <td style="height:12px;padding-top: 0px;  width:200px" colspan="2"><span style="font-size:12px"><b>注册账号

</b></span></td>
      <td style="height:12px; padding-top: 0px; width: 200px" colspan="2"><span style="font-size:12px" >

${Resume.account}</span></td>
      </tr>
      <tr ><td style="height:12px; padding-top: 0px;width:200px "  colspan="2"><span style="font-size:12px" ><b>姓名

</b></span></td>
            <td style="height:12px;padding-top: 0px; width:200px"  colspan="2"><span style="font-size:12px" >

${Resume.name}</span></td>
      </tr>
      <tr>
      <td style="height:12px;  padding-top: 0px"  colspan="2"> <span style="font-size:12px" ><b>联系方式</b></span></td>
      <td style="height:12px; padding-top: 0px;padding-left: 0px"  colspan="2"><span style="font-size:12px" >

${Resume.contact}</span></td>
      </tr>
      <tr ><td style="height:12px; width:; padding-top:0px"><span style="font-size:12px" ><b>性别</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >
      <c:if test="${Resume.sex==1}">
      	男
      </c:if>
      <c:if test="${Resume.sex==0}">
      	女
      </c:if>
       <c:if test="${Resume.sex==2}">
      	保密
      </c:if>
      </span></td>
      <td style="height:12px;  padding-top: 0px;width:100px "><span style="font-size:12px" ><b>出生年月</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.birthDay}</span></td></tr>
      <tr ><td style="height:12px;  padding-top: 0px"><span style="font-size:12px" ><b>身高</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.high}cm</span></td>
      <td style="height:12px;  padding-top: 0px" ><span style="font-size:12px" ><b>体重</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.weigth}kg</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px"colspan="2" ><span style="font-size:12px" ><b>健康状况

</b></span></td>
      <td style="height:12px; padding-top: 0px;" colspan="2"><span style="font-size:12px" >(${Resume.healthyCondition})
      <c:if test="${Resume.healthy == 1}">
      	有健康证
      </c:if>
      <c:if test="${Resume.healthy == 0}">
      	无健康证
      </c:if>
      </span></td>
      </tr>
      <tr>
      <td style="height:12px;  padding-top: 0px; width:200px"colspan="2"><span style="font-size:12px" ><b>现居地

</b></span></td>
      <td style="height:12px; padding-top: 0px;" colspan="2"><span style="font-size:12px" >${Resume.nowAddress}

</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px" ><span style="font-size:12px" ><b>专业名称</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.majorName}</span></td>
     <td style="height:12px;  padding-top: 0px" ><span style="font-size:12px" ><b>学历</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.education}</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px" ><span style="font-size:12px" ><b>工作状态</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.workStatus}</span></td>
      <td style="height:12px;  padding-top: 0px" ><span style="font-size:12px" ><b>工作地点</b></span></td>
      <td style="height:12px; padding-top: 0px;"><span style="font-size:12px" >${Resume.workCity}</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px" colspan="2"><span style="font-size:12px" ><b>从事职业

</b></span></td>
      <td style="height:12px; padding-top: 0px;" colspan="2"><span style="font-size:12px" >
   ${parttimeTypes}
</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px" colspan="2"><span style="font-size:12px" ><b>从事行业

</b></span></td>
      <td style="height:12px; padding-top: 0px;" colspan="2"><span style="font-size:12px" >
      ${industries}
</span></td></tr>
      <tr><td style="height:12px;  padding-top: 0px" colspan="2"><span style="font-size:12px" ><b>期望薪资

</b></span></td>
      <td style="height:12px; padding-top: 0px;" colspan="2"><span style="font-size:12px" >${Resume.hopeSalary}元/天

</span></td></tr>
      </table>
      </div>  
    <!--②左边部分结束--> 
      <!--①标题部分开始-->  
      <div id="header" style="overflow:hidden;"> 
      		<button class="easyui-linkbutton" onclick="addTab('查看面试记录','<%=basePath%>admin/findTab1Member.htm?id=${Resume.id}&type1=1')">查看面试记录</button>
			<button class="easyui-linkbutton" onclick="addTab('查看工作记录','<%=basePath%>admin/findTab1Member.htm?id=${Resume.id}&type1=2')">查看工作记录</button>
			<button class="easyui-linkbutton" onclick="addTab('查看评价','<%=basePath%>admin/findTab1Member.htm?id=${Resume.id}&type1=3')">查看评价</button> 
      </div>  
    <!--①标题部分结束-->  
    <!--③右边部分开始-->  
<div id="content" > 
 <input id="tabName" name="tabName" type="hidden"/>
 <div id="tt" class="easyui-tabs tabs-container" data-options="region:'center',title:''">
</div>  

</div> 

</div>  
    <!--③右边部分结束-->  
 
<!--主容器结束-->
<script type="text/javascript">
$(function(){
	addTab('查看面试记录','<%=basePath%>admin/findTab1Member.htm?id=${Resume.id}&type1=1');
  });
  
function addTab(title, url,target){
	var a=document.getElementById("tabName").value;	
    $(".easyui-linkbutton").removeClass("selected");
    $(target).addClass("selected");
   
    if ($("#tt").tabs("exists", a)) {	//判断tab是否存在，如存在就删掉，然后新开一个tab    	
   		$("#tt").tabs("close", a);
	 }
  
           var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:480px;"></iframe>';             
           $('#tt').tabs('add',{  
               title:title,  
               content:content,  
               closable:true,
               tools : [ 
/*                         {
					iconCls : 'icon-mini-refresh',
					handler : function() {
						refreshTab();
					}
				} */
                         ]
           });           
       document.getElementById("tabName").value=title;    
   }
function back() {
	parent.addTab("用户列表", $("base")[0].href+ "admin/findMemberList.htm");
}
</script>
</body>
</html>
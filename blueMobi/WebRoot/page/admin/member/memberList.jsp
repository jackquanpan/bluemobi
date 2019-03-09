<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>用户列表</title>
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/publicSystem.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<link id="easyuiTheme" rel="stylesheet"
	href="resource/js/jquery-easyui-1.3.5/themes/default/easyui.css"
	type="text/css">
<link type="text/css" rel="stylesheet" href="resource/css/icon.css"></link>


<style>
.hide {
	display: none;
}

.bg-border {
	padding: 20px 10px 20px 10px;
	background: #f2f2f4;
	border: #95B8E7 1px solid;
	border-bottom: none;
	border-top: none;
	overflow: auto;
}

#updateDialogForm table tr td {
	padding: 5px 5px 2px 0px;
}
body select {
height: 20px;
line-height: 24px;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
	<div
			data-options="region:'north',title:'查询条件',border:false,noheader:true"
			style="height:auto;padding:0px 4px 0px 4px;background:#ffffff;overflow:hidden;">
			<form class="bg-border" id="searchForm">
			<table width="100%"><Tr><td>
			<span>ID</span>
            <input type="text" name="id"/>   
			<span>账号</span>
            <input type="text" name="account"/>   
            <span>姓名</span>
            <input type="text" name="name"/>  
            <span>状态</span>
            <select name="status">
            <option value="">全部</option>
            <option value="1">启用</option>
            <option value="0">禁用</option>
            </select> 
            
            <span>地区</span>
            <select id="province" onchange="showCity()">
            <option value="">全部</option>
            <c:forEach var="item" items="${requestScope.areaList }">
            <option value="${item.id }">${item.fullName }</option>
            </c:forEach>
            </select>  
            <select name="areaId" id="city">
            <option value="">全部</option>
            </select>     
                     
            <a  href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearch()">搜索</a>	
		    </td>
		    </tr>
            </table>
			</form>
		</div>
	
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="用户列表" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getAllMembers.htm'">
				<thead>
				<th field="id" width="160" checkbox="true">序号</th>				
				<th field="account" width="160" sortable="true"  align="center">账号</th>	
			    <th field="name" width="160" sortable="true"  align="center">姓名</th>
			    <th field="fullName" width="160" sortable="true"  align="center">所在地</th>
			    <th field="status" width="160" sortable="true"  align="center" data-options="
						formatter: function(value,row,index){
				     		if(value==1){
								return '<span>——</span>'; 
							}else if(value==0){
							 	return '<span>禁用</span>'; 
							}
						}
					">状态</th>
			    <th field="regDate" width="160" sortable="true"  align="center">注册时间</th>
			    <th field="lastLoginDate" width="160" sortable="true"  align="center">上次登录时间</th>
			    <th field="isSale" width="200" sortable="true" align="center" data-options="
					formatter: function(value,row,index){
								var id = row.id;
								var status=row.status;
								var loginType=row.loginType;
								var html='';	
								if(status==0){	
									html=html+'<span><a href=javascript:void(0) onclick= doStart('+index+')>解禁</a></span>';
										if(loginType==0){
											html=html+'|<span><a href=javascript:void(0) onclick=resetPassword('+id+')>重置密码</a></span>';
										}else{
											html=html+'|<span><a href=javascript:void(0) onclick=resetPassword('+id+')>重置密码</a></span>';
										}	
										return html+'|<span><a href=javascript:void(0) onclick=detail('+id+')>查看</a></span>';						     						    					     
								}
								else{
									html=html+'<span><a href=javascript:void(0) onclick= doStart('+index+')>封禁</a></span>';
										if(loginType==0){
											html=html+'|<span><a href=javascript:void(0) onclick=resetPassword('+id+')>重置密码</a></span>';
										}else{
											html=html+'|<span><a  href=javascript:void(0) onclick=resetPassword('+id+')>重置密码</a></span>';
										}	
										return html+'|<span><a href=javascript:void(0) onclick=detail('+id+')>查看</a></span>';	
								}
					}">操作 
					</th>			 						
				</thead>
			</table>
			</div>
			
			</div>

	<script type="text/javascript">
    function doSearch(){           
   		$("#priTB").datagrid("load", serializeObject($("#searchForm")));		
	} 
	 function edit(id){	 
	 	 parent.addTab("编辑用户",$("base")[0].href+"admin/getAdminById.htm?checkStatus=0&id="+id);
		 
	 }
	 function resetPassword(id){
		$.messager.confirm('温馨提示','该用户重置密码之后，密码变为8888，您确认要重置吗？',function(r){    
		     if (r){    
		       	 $.ajax({
	 	            	type:'POST',
	 	            	dataType:'json',
	 	                async: false,
	 	            	url:$('base')[0].href+'admin/resetMemberPassword.htm?id='+id,
	 	                success:function(data){
	 	                $.messager.alert('提示','重置密码成功');    
		    	           $("#priTB").datagrid("reload");
	 	               }  
	 	             });    
		     }   
		 }); 
		 
	 }
	 function detail(id){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 	 parent.addTab("查看用户",$("base")[0].href+"admin/findMemberDetail.htm?id="+id);
		 
	 }	
				
        $(function(){
            var lastIndex;
            $('#priTB').datagrid({
                toolbar:[               
                {
                 text:'邀请记录',
                iconCls:'icon-add',
                handler:function(){
                 parent.addTab("邀请记录",$("base")[0].href+"admin/findInviteList.htm");             	 
                } 
                }
                   
                ],
                onBeforeLoad:function(){
                    $(this).datagrid('rejectChanges');
                   },
                onClickRow:function(rowIndex){
                    if (lastIndex != rowIndex){
                        $('#priTB').datagrid('endEdit', lastIndex);
                        $('#priTB').datagrid('beginEdit', rowIndex);
                    }
                    lastIndex = rowIndex;
                }
            });
          });
          
          //一级下拉联动二级下拉
		function showCity() {
			//清除二级下拉列表
			$("#city").empty();
			
			var province=$("#province").attr("value");
			
			$("#city").append(
							$("<option/>").text("全部").attr("value",
									province));
			if (province == 1 || province == 18 || province == 792
						|| province == 2240) {
					return;
				}
				
			//要请求的二级下拉JSON获取页面
			var url = "admin/findCityList.htm";
			//将选中的一级下拉列表项的id传过去
			$.getJSON(url, {
				id :province
			}, function(data) {
				//对请求返回的JSON格式进行分解加载
				$.each(data.cityList, function(i, item) {
					$("#city").append(
							$("<option/>").text(item.name).attr("value",
									item.id));
				});
			});
		}
		
		function doStart(index){
 	       var rowData =$("#priTB").datagrid("getRows")[index];
 	       var id=rowData.id;
 	       var isUsed = rowData.status;
 	       	 $.ajax({
 	            	type:'POST',
 	            	dataType:'json',
 	                async: false,
 	            	url:$('base')[0].href+'admin/member/doStart.htm?id='+id+'&status='+isUsed,
 	                success:function(data){
	    	           		 var rowData =$("#priTB").datagrid("getRows")[index];
	    	                 if(rowData.status==0){
	    	                 $('#priTB').datagrid('updateRow',{
	    		              index: index,
	    		               row: {
	    		            	   status: 1
 		               		}
 	                  });
	    	           $("#priTB").datagrid("reload");
 	               $.messager.alert("提示","该会员解禁成功");   
 	                 }else{
 	                 $('#priTB').datagrid('updateRow',{
 		              index: index,
 		               row: {
 		            	   status: 0
 		               }
 	                  });
 	               $("#priTB").datagrid("reload");
 	               		$.messager.alert("提示","该会员封禁成功");   
 	                 }
 	               }
 	       	 
 	             });

	
		}
		
    </script>
</body>
</html>
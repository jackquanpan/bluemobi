<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>权限列表</title>
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
			<form class="bg-border" id="searchForm" action ="admin/exportAdvertisementList.htm">
			<table width="100%"><Tr><td>
            <span>姓名</span>
            <input type="text" name="name"/>  
            <span>账号</span>
            <input type="text" name="account"/>       
            <select name="groupId">
            <option value="">全部</option>
            <c:forEach var="item" items="${requestScope.groupList }">
            <option value="${item.id }">${item.groupName }</option>
            </c:forEach>
            </select>       
                     
            <a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearch()">搜索</a>
           
		    </td>
		    </tr>
            </table>
			</form>
		</div>
	
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="权限列表" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getAllAdmins.htm'">
				<thead>
				<th field="id" checkbox="true">序号</th>				
			    <th field="groupName" width="160" sortable="true"  align="center">用户组</th>	
			    <th field="name" width="160" sortable="true"  align="center">姓名</th>
			    <th field="account" width="160" sortable="true"  align="center">账号</th>		
			    <th field="createDate" width="160" sortable="true"  align="center">添加时间</th>			    
			    <c:if test="${fn:contains(sessionScope.toegangstoken,'25')==true}"> 
			    <th field="status" width="200" sortable="true" align="center" data-options="
					formatter: function(value,row,index){
								var id = row.id;
								var groupid = row.groupId;								
						return '<span><a href=javascript:void(0) onclick=detail('+id+')>查看</a></span>|<span><a href=javascript:void(0) onclick=edit('+id+')>编辑</a></span>|<span><a href=javascript:void(0) onclick=dele('+id+','+groupid+')>删除</a></span>';														     						    					     
					}">操作 
					</th>
					</c:if>			 						
				</thead>
			</table>
			</div>			
			</div>
			<div id="update1" style="display: none;" title="二维码">
			<table width="100%">
				<tr>
					<td><div>
					<img style="border:0;margin-top:0px;"
											src="<%=basePath%>/imgs/zanwutupian.jpg" id="qrCodeSrc1"
											width="180px" height="150px" />										
					</div>					
					</td>
				</tr>
				<tr><td><div align="center">
					<!-- 	<input id="qrCodeSrc"  type="text"  
								style="line-height:16px; border:1px solid #ccc; width:180px; height:16px;" />	
					 -->
					 <a href="javascript:;"	class="easyui-linkbutton" plain="false" onclick="cancel()">关闭</a>
						</div>
				</td></tr>
			</table>
		</div>	

	<script type="text/javascript">
     function doSearch(){           
   		$("#priTB").datagrid("load", serializeObject($("#searchForm")));		
	 } 
    
     function detail(id){	 
    	 parent.addTab("查看权限",$("base")[0].href+"admin/getAdminById.htm?checkStatus=1&id="+id);		 
	 }
	 function edit(id){	 
	 	 parent.addTab("编辑权限",$("base")[0].href+"admin/getAdminById.htm?checkStatus=0&id="+id);
		 
	 }	 
	
	 function detailQr(id){	
		 $("#qrCodeSrc").val('');
		 $("#qrCodeSrc1").attr("src", '');	
		 var qr = '<%=basePath%>/imgs/zanwutupian.jpg';
			$.ajax({
				type :'get',
				url :$("base")[0].href+'admin/findAdminQrById.htm',
				data : {
					id:id
				},
				dataType :'json',
				success : function(result) {					
					 if(result.admin.qrCodeSrc != null){
						 qr = result.admin.qrCodeSrc ;						 
						 $("#qrCodeSrc").val(qr);
						 $("#qrCodeSrc1").attr("src", qr);	
					 }					
				}
			});			
			//弹出框的图片src赋值				 		 	
			$("#update1").show().dialog();					 
	 }	
				
     $(function(){
            var lastIndex;
            $('#priTB').datagrid({
                toolbar:[
      <c:if test="${fn:contains(sessionScope.toegangstoken,'25')==true}"> 
                {
                 text:'新增权限',
                iconCls:'icon-add',
                handler:function(){
                 parent.addTab("新增权限",$("base")[0].href+"admin/findAuthorityAdd.htm");             	 
                } 
                },          
                 </c:if>
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
     
 	function cancel() {				
		$("#update1").dialog("close");
	}
 	
 	
	function dele(id, groupid) {
		//删除者
		var adminId =${sessionScope.adminUser.id} ;		
		var adminGroupId = ${sessionScope.adminUser.groupId} ;		
		var canDel = 1;
		if(adminId == id){
			var canDel = 0;
			$.messager.alert("提示","您不能删除自己的账号");
			return false;
		}
		if(adminGroupId != null && groupid != null){
			if(adminGroupId != 1){ //删除者不是超级管理员
				if(adminGroupId == 2 || adminGroupId == 3) {//是辅助管理员
					if(groupid == 1||groupid == 2 ||groupid == 3 ){
						canDel = 0;
						$.messager.alert("提示","由于权限不足，您不能删除此管理员");
						return false;
					}
				}else{//其他自主分组的权限
					if(adminGroupId == groupid){
						canDel = 0;
						$.messager.alert("提示","由于等级相同，您不能删除此管理员");
						return false;
					}else if(groupid == 1||groupid == 2 ||groupid == 3){
						canDel = 0;
						$.messager.alert("提示","由于权限不足，您不能删除此管理员");
						return false;
					}					
				}
				if(adminGroupId == groupid) {
					canDel = 0;
					$.messager.alert("提示","由于等级相同，您不能删除此管理员");
					return false;
				}
			}else{
				 if(groupid == 1){
					 canDel = 0;
						$.messager.alert("提示","由于等级相同，您不能删除此管理员");
						return false;
				 }
				
			}
			
		}else if(adminGroupId == null || adminGroupId == ''){
			canDel = 0;
			$.messager.alert("提示","您没有管理员登记，不能删除其他管理员");
			return false;
		}
		if(canDel == 1){
			$.messager.confirm("提示", "您确认要删除吗？", function(b) {
				if (b) {
					$.ajax({
						url:$("base")[0].href+"admin/delete.htm?ids=" + id,
						type : "POST",
						async : false,
						success : function(data) {
							 $("#priTB").datagrid("reload");
						}
					});
				}
			});
		}

	} 
	
  
    </script>
</body>
</html>
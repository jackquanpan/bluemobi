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
			<span>邀请人</span>
            <input type="text" name="invitePhone"/>   
            <span>被邀请人</span>
            <input type="text" name="beinvitePhone"/>  
            <span>状态</span>
            <select name="status">
            <option value="">全部</option>
            <option value="1">邀请成功</option>
            <option value="0">邀请中</option>
            </select> 
            
            <a  href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearch()">搜索</a>	
		    </td>
		    </tr>
            </table>
			</form>
		</div>
	
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="邀请记录" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getMembersInvite.htm'">
				<thead>
				<th field="id" width="160" checkbox="true">序号</th>
				<th field="id" width="160" sortable="true"  align="center" >ID</th>				
				<th field="invitePhone" width="160" sortable="true"  align="center">邀请人</th>	
			    <th field="beinvitePhone" width="160" sortable="true"  align="center">被邀请人</th>			    
			    <th field="status" width="160" sortable="true"  align="center" data-options="
						formatter: function(value,row,index){
				     		if(value==1){
								return '<span>邀请成功</span>'; 
							}else if(value==0){
							 	return '<span>邀请中</span>'; 
							}
						}
					">状态</th>
			    <th field="rewardFlag" width="160" sortable="true"  align="center" 
			    data-options="
						formatter: function(value,row,index){
				     		if(value==1){
								return '<span>--</span>'; 
							}else if(value==0){
							 	return '<span><a href=javascript:void(0) onclick=detail('+id+')>奖励</a></span>'; 
							}
						}
						">标记是否奖励</th>
			    
			    <th field="isSale" width="200" sortable="true" align="center" data-options="
					formatter: function(value,row,index){
								var id = row.id;
								var status=row.status;
							return '<span><a href=javascript:void(0) onclick=dele('+id+')>删除</a></span>';
								
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
	
	function dele(id){	 
	 	 $.messager.confirm("提示","您确认要删除吗？",function(b){if(b){
				          		 $.ajax({
				         			url:$("base")[0].href+"admin/deleteInvite.htm",
				         			type:"post",
				         			data:{id:id},
				         			dataType:"json",
				         			async: false,
				         			success:function(data){
				         			//删除失败
				         			if(data.status=='0'){
				         				  $.messager.alert("提示","删除失败");   
				         			}else if(data.status=='1'){
				         			   $("#priTB").datagrid("reload");
				         			}
				         			}});
				         			}
				         			});
	 }
			
        $(function(){
            var lastIndex;
            $('#priTB').datagrid({
                toolbar:[               
                                
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
        
    </script>
</body>
</html>
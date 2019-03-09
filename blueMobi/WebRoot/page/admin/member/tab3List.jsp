<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>评价列表</title>
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
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="查看评价" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getMembersAppraise.htm?id=${id}'">
				<thead>
				<th field="id" width="120"  checkbox="true">編号</th>				
			    <th field="createDate" width="120" sortable="true"  align="center">评价时间</th>
			    <th field="serviceAttitude" width="100" sortable="true"  align="center">服务态度</th>
			    <th field="personalImage" width="100" sortable="true"  align="center">人员形象</th>
			    <th field="performance" width="100" sortable="true"  align="center">工作表现</th>
			    <th field="workTime" width="100" sortable="true"  align="center">工作时间</th>	
			    <th field="content" width="160" sortable="true"  align="center">描述</th>
			    <th field="operate" width="100" sortable="true"  align="center" data-options="
					formatter: function(value,row,index){
					var id = row.id;
					return '<span><a href=javascript:void(0) onclick=dele('+id+')>删除</a></span>';
					}" >操作</th>			    		 						
				</thead>
			</table>
			</div>
			
			</div>

	<script type="text/javascript">
 
		
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
          
        
	function dele(id){
	
	 $.messager.confirm("提示","您确认要删除吗？",function(b){if(b){
				          		 $.ajax({
				         			url:$("base")[0].href+"admin/deleteAppraise.htm",
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
    </script>
</body>
</html>
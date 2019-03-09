<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>预设列表</title>
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

			<table id="priTB" title="预设列表" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getAllGroup.htm'">
				<thead>
					<th field="id" checkbox="true">序号</th>
					<th field="groupName" width="160" sortable="true" align="center">用户组</th>
					<th field="updateDate" width="160" sortable="true" align="center">更新时间</th>
					<c:if test="${fn:contains(sessionScope.toegangstoken,'26')==true}">
					<th field="status" width="200" sortable="true" align="center"
						data-options="formatter: function(value,row,index){
								var id = row.id;
								if(id > 2){													     	
					return '<span><a href=javascript:void(0) onclick=edit('+id+')>编辑</a></span>|<span><a href=javascript:void(0) onclick=dele('+id+')>删除</a></span>';
					}else{
					return '<span><a href=javascript:void(0) onclick=edit('+id+')>编辑</a></span>';
					} 
					}">操作
					</th>
					</c:if>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
    function doSearch(){           
   		$("#priTB").datagrid("load", serializeObject($("#searchForm")));		
	} 
	 function edit(id){	 
	 	 parent.addTab("编辑预设",$("base")[0].href+"admin/groupEdit.htm?id="+id);
		 
	 }		
	 function dele(id){	
       	 $.messager.confirm("提示","您确认要删除吗？",function(b){if(b){
     		 $.ajax({
    			url:$("base")[0].href+"admin/deleteGroup.htm",
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
				<c:if test="${fn:contains(sessionScope.toegangstoken,'26')==true}"> 
                {
                 text:'新增预设',
                iconCls:'icon-add',
                handler:function(){
                 parent.addTab("新增预设",$("base")[0].href+"admin/groupAdd.htm");             	 
                }}
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
    </script>
</body>
</html>
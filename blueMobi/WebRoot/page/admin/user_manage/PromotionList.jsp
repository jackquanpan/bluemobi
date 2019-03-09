<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>推广统计</title>
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
                     
            <a href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" plain="false" onclick="doSearch()">搜索</a>	
            <%-- <c:if test="${fn:contains(sessionScope.toegangstoken,'93')==true}"> --%>
            <a href="javascript:void(0)" class="easyui-linkbutton"  plain="false" onclick="exportExcel()">导出</a>
		    <%-- </c:if> --%>
		    </td>
		    </tr>
            </table>
			</form>
		</div>
	
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="推广统计" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin/getAllAdminPromotions.htm'">
				<thead>
				<th field="id" checkbox="true">序号</th>				
			    <th field="name" width="160" sortable="true"  align="center">姓名</th>
			    <th field="account" width="160" sortable="true"  align="center">账号</th>		
			    <th field="lastDayCount" width="160" sortable="true"  align="center"  data-options="
						formatter: function(value,row,index){
						if(row.lastDayCount == 0 || row.lastDayCount == null){
						return 0;
						}else{
				     	 	return row.lastDayCount; 
				     	 	}
						}">昨天推广数</th>	
			    <th field="lastMonthCount" width="160" sortable="true"  align="center" data-options="
						formatter: function(value,row,index){
						if(row.lastMonthCount == 0 || row.lastMonthCount == null){
						return 0;
						}else{
				     	 	return row.lastMonthCount; 
				     	 	}
						}">上月推广数</th>	
			    <th field="adminCount" width="160" sortable="true"  align="center"  data-options="
						formatter: function(value,row,index){
						if(row.adminCount == 0 || row.adminCount == null){
						return 0;
						}else{
				     	 	return row.adminCount; 
				     	 	}
						}">总推广数</th>			    
				</thead>
			</table>
			</div>
			</div>

	<script type="text/javascript">
    function doSearch(){           
   		$("#priTB").datagrid("load", serializeObject($("#searchForm")));		
	} 
				
        $(function(){
            var lastIndex;
            $('#priTB').datagrid({
               /*  toolbar:[               
                {
                    text:'删除',
                    iconCls:'icon-remove',
                    handler:function(){	
                       var checkedItems = $('#priTB').datagrid('getChecked');
			           var idArray =new Array();
			           if (checkedItems==0) {$.messager.alert("提示","请选择要删除的记录","info");} 
			           else{
			          		 $.each(checkedItems, function(index, item) {idArray.push(item.id);});		 
				          	   $.messager.confirm("提示","您确认要删除吗？",function(b){if(b){
				          		 $.ajax({
				         			url:$("base")[0].href+"admin/promotion/delete.htm",
				         			type:"post",
				         			data:{ids:idArray.join(",")},
				         			dataType:"json",
				         			async: false,
				         			success:function(data){
				         			//删除失败
				         			if(data.status=='3'){
				         				  $.messager.alert("提示","删除失败");   
				         			}else if(data.status=='1'){
				         			   $("#priTB").datagrid("reload");
				         			}
				         			}});
			          	   }});
			            }                       
                   }
                   }                 
                ], */
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
        
        function exportExcel(){
			$("#searchForm").submit();
		}
    </script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8"><%@ include file="../../basePath.jsp"%>
<title>发现列表</title>
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
	<div data-options="region:'north',title:'上',split:true,noheader:true" style="height:120px;">
			<table>
				<tr>
					<td>发现标题</td>
					<td><input class="easyui-validatebox" data-options="required:true,validType:'length[1,50]'" /></td>
					<td>发布人</td>
					<td>
						<select name="publishUserId" style="width:100px">
							<option>--请选择--</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>发布时间<input type="text" class="easyui-datebox" required="required"/></td>
					<td>至 <input type="text" class="easyui-datebox" required="required" /></td>
					<td>发布状态</td>
					<td>
						<select class="easyui-combobox" name="status" style="width:200px;">   
						    <option value="1">显示</option>   
						    <option value="0">隐藏</option>     
						</select> 
						<a id="search" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a> 
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<a href="javascript:void(0)" onclick="addAricle()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增文章</a> 
						<a href="javascript:void(0)" onclick="xianshi()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">显示</a> 
						<a href="javascript:void(0)" onclick="yincang()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">隐藏</a> 
					</td>
				</tr>
			</table>
		</div>
		<div data-options="region:'center',border:false"
			style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">

			<table id="priTB" title="发现列表" style="width:inherit;height:auto;"
				pagination="true"
				data-options="fit:true,rownumbers:true,pagination:true,remoteSort:false,pageSize:10,pageList:[1,10,20],url:$('base')[0].href+'admin//getDiscoveryListByFenYe.htm'
			">
				<thead>
					<th field="id" checkbox="true">序号</th>
					<th field="title" width="160" sortable="true" align="center">发现标题</th>
					<th field="createTime" width="160" sortable="true" align="center">发布时间</th>
					<th field="num" width="160" sortable="true" align="center">点赞数量</th>
					<th field="name" width="160" sortable="true" align="center">发布人</th>
					<th field="status" width="160" sortable="true" align="center" data-options="formatter:
					function(value,row,index){
					if(row.status=='1'){
					return '显示';
					}else{
					return '隐藏';
					}
					
					}">状态</th>
					<th field="operate" width="160" align="center" data-options='formatter: 
						function(value,row,index){
							return "<a href=javascript:void(0)>查看</a>&nbsp;<a href=javascript:void(0)>编辑</a>&nbsp;<a href=javascript:void(0)>隐藏</a>";
						}'>操作</th>
					
					
					
					<%-- <c:if test="${fn:contains(sessionScope.toegangstoken,'26')==true}">
					<th field="status" width="200" sortable="true" align="center"
						data-options="formatter: function(value,row,index){
								var id = row.id;
								if(id > 2){													     	
					return '<span><a href=javascript:void(0) onclick=edit('+id+')>编辑</a></span>|<span><a href=javascript:void(0) onclick=dele('+id+')>删除</a></span>';
					}else{
					return '<span><a href=javascript:void(0) onclick=edit('+id+')>编辑</a></span>';
					} 
					}">操作 --%>
					<%-- </th>
					</c:if> --%>
				</thead>
			</table>
		</div>
	</div>

	<script type="text/javascript">
	/* 动态添加发布人 */
	$(function(){
		$.ajax({
			url:"<%=basePath %>/admin/findSystemUserList.htm",
			type:"POST",
			dataType:"JSON",
			success:function(rs){
				var content = "";
				for(var i in rs){
					var userId = rs[i].id;
					var account = rs[i].account;
					content +="<option value='"+userId+"'>"+account+"</option>"
				}
				$("select[name=publishUserId]").append(content);
			},
			error:function(rs){
				alert("discoveryList.jsp的ajax发生错误了...");
			}
		});
	/* 新增文章 */
	function addAricle(){
		window.location.href="/ecp/page/admin/discovery/addDiscovery.jsp"
	}
	/*显示数据 */
	function xianshi(){
		var st = $("#priTB").datagrid("getSelections");
		var len = st.length;
		if(len==0){
			$.messager.alert('警告','没有选中行'); 
		}else{
			var idStr = "";
			for(var i in st){
				idStr+=st[i].discoveryId+",";
			}
			$.ajax({
				url:"<%=basePath %>/admin/batchShow.htm",
				type:"POST",
				data:{
					"idStr":idStr
				},
				dataType:"JSON",
				success:function(rs){
					$("#priTB").datagrid("reload");
				},
				error:function(rs){
					alert("discoveryList.jsp的ajax发生错误了...");
				}
			});
		}
	}
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title>日志列表</title>
<%@ include file="../basePath.jsp" %>
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resource/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resource/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="resource/js/publicSystem.js"></script>
 <link id="easyuiTheme" rel="stylesheet" href="resource/js/jquery-easyui-1.3.5/themes/default/easyui.css" type="text/css">
<link type="text/css" rel="stylesheet" href="resource/css/icon.css"></link>

<style>
*{font-size:12px;}
body {font-family:helvetica,tahoma,verdana,sans-serif;padding:20px;font-size:13px;margin:0 auto;width:100%;}
h2 {font-size:12px;font-weight:bold;margin:10px 0 15px;border-bottom:1px solid #ccc;padding:10px 5px;}
.demo-info{background:#FFFEE6;color:#8F5700;padding:12px 10px 20px;}
.demo-tip{width:16px;height:16px;margin-right:8px;//float:left;}
.demo-info a{color:#0E2D5F;outline:none;}
.demo-info a:hover{text-decoration:underline;font-weight:bold;}
.fl{}
.brand{width:60px;padding:5px;background:#f9f9f9;border-right:1px solid #ddd;}
.brand,.tags{padding-bottom: 32767px !important;margin-bottom: -32767px !important;}
.brand:before,.tags:before{padding-top: 32767px !important;margin-bottom: -32767px !important;}
.morer{width:30px;padding-top:4px;}
.morer a{color:#666;}
.tags{width:570px;height:auto;}
ul{border-bottom:1px solid #ddd;background:#fff;}
ul li.uli{border:1px solid #ddd;border-bottom:0;overflow:hidden;}
ol{overflow:hidden;min-height:25px;}
ol li{float:left;height:23px;}
ol li a{float:left;font-size:12px;color:#666;min-width:58px;padding:3px 5px 4px;height:15px;text-align:center;}
.demo-info ol li a:hover{text-decoration:none;border:1px solid orange;}
ol li a.act{border:1px solid orange;}
.hide{display:none;}
.bg-border {
	padding: 20px 10px 20px 10px;
	background: #f2f2f4;
	border: #95B8E7 1px solid;
	border-bottom: none;
	border-top:none;
	overflow:auto;

}
#updateDialogForm table tr td{ padding:5px 5px 2px 0px;}
</style>
</head>
<body>
   <div class="easyui-layout" data-options="fit:true,border:false">
         <div data-options="region:'center',border:false" style="padding-left: 4px;padding-top: 0px;padding-right: 4px;background:#ffffff;overflow:hidden;">
        <table id="priTB" title="日志列表"  style="width:inherit;height:auto;"   pagination="true"
         data-options="fit:true,pagination:true,pageSize:10,pageList:[50,100,200,1000],sortName:'createTime',sortOrder:'desc',url:'admin/findLoginlogList.htm'"
        >
            <thead>
                  <tr>
                  	<th field="id" hidden="true"></th>
                  	<th field="opTime" width="200" align="center"  data-options="
					formatter: function(value,row,index){
						return getTimeTxt(value,19);
					}">操作时间</th>
					<th field="nikeName" width="100" align="center">操作账号</th>
					<th field="opContent" width="360"  align="center">操作内容</th>
                </tr>
            </thead>
        </table>
    </div>
   </div>
<script>
        $(function(){
            var lastIndex;
            $('#priTB').datagrid({
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
        }
        );
        
        //修改页面跳转
        function edit(id){
        	location.href=$("base")[0].href + "/admin/addProduct.htm?id="+id;
        }
        
        
      //删除操作
        function del(index){
        	$.messager.confirm("提示","您确认要删除吗？",function(b){
        		if(b){
        			var d =$("#priTB").datagrid("getRows")[index];
        			ajaxFunction($("base")[0].href+"/admin/deleteProduct.htm",{id:d.id},function(data){
        				if(data.status==1){
        					$.messager.alert("提示","恭喜，删除成功","info",function(){
        						location.href=location.href;
        				 	});  
        				}else{
        					$.messager.alert("提示","删除失败，请稍后再试");
        				}
        			});
        		}
        	});
        }
</script>
</body>
</html>
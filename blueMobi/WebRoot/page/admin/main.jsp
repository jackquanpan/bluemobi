<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="../basePath.jsp" %>
<meta charset="UTF-8"/>
<%
response.setHeader("progma","no-cache");   
response.setHeader("Cache-Control","no-cache");   
response.setDateHeader("Expires",0);
%>


<meta http-equiv="Content-Type" contenttype="text/xml; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta http-equiv="pragma" content="no-cache"/> 
<meta http-equiv="cache-control" content="no-cache"/> 
<meta http-equiv="expires" content="0"/>   
<title>后台管理系统</title>
<script src="resource/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="resource/js/jquery.easyui.min.js" type="text/javascript"></script>
<link href="resource/css/global.css" rel="stylesheet" type="text/css" />
<link href="resource/js/jquery-easyui-1.3.5/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="resource/css/icon.css" rel="stylesheet" type="text/css" />
<link href="resource/css/basic.css" rel="stylesheet" type="text/css" />
<link type="image/x-icon" href="<%=basePath%>/favicon.ico" mce_href="favicon.ico" rel="icon"> 
<link type="image/x-icon" href="<%=basePath%>/favicon.ico" mce_href="favicon.ico" rel="shortcut icon">

<style>
.layout-panel,.tabs-container,.panel-body{overflow:visible;}
.layout-panel-north .tabs-panels{position:absolute;left:-180px;top:67px;z-index:99999;width:173px;}
.tabs-header, .tabs-tool{background-color:rgba(0, 0, 0, 0);}
#rightSlide{position:absolute;}
.today {color:#333;position:absolute; right:50px; top:10px;}
.logins { color:#333;}
.login_span { padding-left:10px; border-left:1px solid #333; margin-left:8px;}
.login_span a.a_login { color:#333;}
.login_span a.a_login:hover { color:#000;}
.left_menu .easyui-linkbutton { padding-top:10px; border-top:1px solid #ccc; border-bottom:1px solid #eee;}
.left_menu .easyui-linkbutton.selected {background:#E0ECFF;font-weight:bold;border-color:#5A91D8;}
.layout-split-west { background:#f5f5f5;}
.tabs-p-tool a {vertical-align:top;}
#updatePwdDialog table tr { height:32px;line-height:32px;}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:70px;background:#95B8E7;padding:10px;">
        <p style="color:#fff;font-size:22px;font-family:'microsoft yahei';height:24px;line-height:24px;margin-top: 5px;">
        <span  style="float: left;"><img src="imgs/yingwangkeji.png"  width="40" height="40"/></span>
        <span style="float: left;margin-top: 10px;" >&nbsp;后台管理系统</span>
        </p>      
   

        <div class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:175px;position:relative;bottom:13px;left:170px;">
			<div class="easyui-accordion left_menu" style="border:0;">
                	 <input id="tabName" name="tabName" type="hidden"/>                	 
           <!-- 静态菜单         
       
			<div title="系统管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('权限列表','<%=basePath%>/admin/findAuthorityList.htm')">权限列表</button>
				<button class="easyui-linkbutton" onclick="addTab('预设列表','<%=basePath%>/admin/findRoleSetPage.htm')">预设列表</button>
			</div>
			<div title="会员管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('用户列表','<%=basePath%>/admin/findMemberList.htm')">用户列表</button>
				<button class="easyui-linkbutton" onclick="addTab('企业列表','<%=basePath%>admin/findEnterpriseList.htm')">企业列表</button>
			</div>
			<div title="兼职管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('兼职列表','admin/findParttimeList.htm')">兼职列表</button>
			</div>
			<div title="资讯管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('心得列表','<%=basePath%>admin/findInnerWordList.htm')">心得列表</button>
				<button class="easyui-linkbutton" onclick="addTab('单页列表','<%=basePath%>admin/findFrom.htm')">单页列表</button>
			</div>
			<div title="Web配置" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('城市列表','<%=basePath%>/admin/findAreaList.htm')">城市列表</button>
				<button class="easyui-linkbutton" onclick="addTab('工作性质','<%=basePath%>/admin/findWorkTypeList.htm')">工作性质</button>
				<button class="easyui-linkbutton" onclick="addTab('兼职类型','<%=basePath%>/admin/findParttimeTypeList.htm')">兼职类型</button>
				<button class="easyui-linkbutton" onclick="addTab('行业列表','<%=basePath%>/admin/findIndustryList.htm')">行业列表</button>
			</div>
			<div title="广告管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('广告列表','<%=basePath%>admin/findAdvertisement.htm')">广告列表</button>
			</div>
			<div title="数据统计" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('网站收入列表','<%=basePath%>admin/findStatisticList.htm')">网站收入列表</button>
			</div>
			<div title="消息管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('消息提醒设置','<%=basePath%>/admin/MessageRemind.htm')">消息提醒设置</button>
				<button class="easyui-linkbutton" onclick="addTab('推送列表','<%=basePath%>admin/findMessageList.htm')">推送列表</button>
			</div>
			<div title="启动页管理" iconCls="icon-ok" style="overflow:auto;padding:10px;">
				<button class="easyui-linkbutton" onclick="addTab('启动页列表','<%=basePath%>admin/findFirstPage.htm')">启动页列表</button>
			</div>
			 
			-->
			
           <!-- 动态的权限模块展现       -->              
                 
				<c:forEach var="p"  items="${modules}"> 
			       <c:if test="${p.parentId==0}">			       
						<div title="${p.module}" iconCls="icon-ok" style="overflow:auto;padding:10px;">
							<c:forEach var="s" items="${modules}">
							    <c:if test="${s.parentId==p.id}">							   
									<button class="easyui-linkbutton" onclick="addTab('${s.module}','<%=basePath%>${s.url}',this)">${s.module}</button>
							    </c:if>	
							</c:forEach>
						</div>
			       </c:if>
				</c:forEach>
				 
				
             </div> 
	</div>		 
		<p class="today">今天是：<span></span><p>
        <p class="logins" style="margin-top: 15px;">
        <c:if test="${adminUser!=null}">
        		&nbsp;${adminUser.account}
        		<span class="login_span"><a class="a_login" href="javascript:;" onclick="updatePwd();">密码修改</a></span>
        		<span class="login_span"><a class="a_login" href="admin/logout.htm">注销</a></span>
        </c:if>
        </p>
        
        
    </div>
	<div data-options="region:'west',split:true,title:'操作面板'"style="width:180px;padding:0;">
    </div>
	<div id="tt" class="easyui-tabs tabs-container" data-options="region:'center',title:''">
	   <div title="今日概览">
    <!--<div>销售总金额</div><div><a href="javascript:;">21312312</a></div>
    <div>成交量记录</div><div><a href="javascript:;">21312312</a></div>
    <div>新增工作室</div><div><a href="javascript:;">21312312</a></div>   
    <div>新增用户数</div><div><a href="javascript:;">21312312</a></div>   -->   
       <div align="center" style="padding-top: 150px ">
			 <font color="blue" size="6"><b>欢迎进入优校后台管理系统</b></font>
		</div>
       </div>
	</div>
    <div class="slideR" style="background:transparent;position:absolute;width:28px;height:100%;top:70px;left:0;z-index:99999;display:none;"></div>
    
    <div id="updatePwdDialog" class="easyui-dialog" title="密码修改" style="width:430px;height:310px;background:#eee;padding: 20px 0px 0px 20px;">
		<form id="updatePwdForm" method="post" class="updateDialogForm">
			<table width="400" cellspacing="5">
				<tr>
					<td><label for="name">用户账号:</label></td>
					<td>${sessionScope.adminUser.account }</td>
				</tr>
				<tr>
					<td><label for="name">输入当前密码:</label></td>
					<td><input class="easyui-validatebox" type="password" name="current_pwd" style="width:200px" id="current_pwd" data-options="required:true,missingMessage:'请输入当前密码'"/></td>
				</tr>
				<tr>
					<td><label for="name">输入新密码:</label></td>
					<td><input class="easyui-validatebox" type="password" name="new_pwd" style="width:200px" id="new_pwd" data-options="required:true,missingMessage:'请输入新密码'"/></td>
				</tr>
				<tr>
					<td><label for="name">重复新密码:</label></td>
					<td><input class="easyui-validatebox" type="password" name="new_pwd_sure" style="width:200px" id="new_pwd_sure" data-options="required:true,missingMessage:'请再次输入新密码'"/></td>
				</tr>
			</table>
		</form>
	</div>
    
    <script type="text/javascript">
        var date=new Date();
     	Date.prototype.Format = function(fmt)   
		{ //author: meizz   
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		} 
        $(function(){
        	//密码修改弹出层
        	updatePwdDialog = $("#updatePwdDialog").dialog({
				closed : true,
				resizable:true,
				modal:true,
				buttons:[
			        {text:'确定',handler:function(){
			        	//修改密码操作
			        	$.ajax({
							url:$('base')[0].href+'admin/updatePwd.htm',
							dataType : 'json',
							data : {
								"current_pwd":$("#current_pwd").val(),
								"new_pwd":$("#new_pwd").val(),
								"new_pwd_sure":$("#new_pwd_sure").val()
							},
							type : 'POST',
							async : false,
							success : function(data) {
								var status = data.status;
								if(status == "success"){
									$.messager.alert("提示","密码修改成功","info",function(){
										updatePwdDialog.dialog("close");
										location.href=$("base")[0].href + "admin/login.htm";
									});
								}else if(status == "pwd_sure_error"){
									$.messager.alert("提示","新密码两次输入不一致","error");
								}else if(status == "current_pwd_error"){
									$.messager.alert("提示","当前密码不正确","error");
								}else{
									$.messager.alert("提示","密码修改失败","error");
								}
							}
						});
					}},
					{text:'取消',handler:function(){
						updatePwdDialog.dialog("close");
					}}
				],
        		onClose:function(){
        			$("#current_pwd").val("");
					$("#new_pwd").val("");
					$("#new_pwd_sure").val("");
        		}
			});
        	
        	$("#menu_wrap").height( $(window).height() - 127 );
        	$(window).resize(function(){        		
        		$("#menu_wrap").height( $(window).height() - 127 );
        	});
        	$(".today span").text( new Date().Format("yyyy-MM-dd") );
            var x,i = 0,j = 0, m = $('.modules a'),d = $('.layout-panel-west'),c = $('.modules li'),le = $('.layout-button-left'),ri = $('.slideR'),nh = $('.layout-panel-north .tabs-panels'),ib = $('.accordion-body button');
            //le.remove();
            $('.panel-body-noborder').css('overflow','visible').parents().css('overflow','visible');
            $('.layout-panel-north .easyui-tabs').css('width','900px');
            $('.layout-panel-north .tabs-header').css({'width':'997px','display':'none'});
            $('.layout-panel-north .tabs-wrap').css('width','897px');
            $('.tabs-scroller-left').hide();
            $('.tabs-scroller-right').hide();
            $('#tt .tabs-header').css('width','auto');
            $('.layout-panel-north a.tabs-inner').css({
                'height':'0px',
                'line-height':'0px',
                'padding':'0 0px'
            });
            $('.layout-panel-north a.tabs-inner span').css({
                'font-size':'0px',
                'font-family':'microsoft yahei'
            });
            d.hide();
            d.first().show();
            for(;i <= m.length;i++){
                $(m[i]).click(function(){
                    //tog($(this).text());
                    x = $(this).parent().index();
                    console.log(d.eq(x).siblings('.layout-panel-west'));
                    d.eq(x).show().siblings('.layout-panel-west').hide();
                });
            }
            function tog(name){
                $('.panel-header .panel-title').eq(0).text(name)
            }
            c.each(function(){
                c.click(function(){
                    if($(this).hasClass('act')){}else{
                        $(this).addClass('act').siblings().removeClass('act')
                    }
                })
            });
            ri.on('click',function(){
                newHide(nh,'left','-180px');
                $('.layout-split-west').css('left','0').show();
                $('.layout-panel-center').width($('body').width() - $('.layout-panel-west').width() - 14).css('left',($('.layout-panel-west').width() + 5) + 'px');
                $('#tt').width($('body').width() - $('.layout-panel-west').width());
                $('#tt').prev().width($('body').width() - $('.layout-panel-west').width());
                $(this).hide();
                $('.layout-expand-west').hide();
                console.log($('body').width());
            });
            le.on('click',function(){
                $(ri).show();
                newHide(nh,'left','-360px');
                $('#tt').width($('body').width() - 28);
                $('#tt').prev().width($('body').width() - 28);
                $('.layout-panel-center').width($('body').width() - 28).css('left','28px');
                $('#tt .tabs-panels').width($('#tt').width());
                $('#tt .tabs-panels .panel').width($('#tt').width());
                $('#tt .tabs-panels .panel-body').width($('#tt').width());
                $('.tabs-wrap').width($('#tt').width());
                $('.tabs-header').width($('#tt').width());
//                $('.layout-expand-west').hide();
//                $('.layout-body').show();
            });
            function newHide(nh,param,px){
                nh.css(param,px)
            }
            ib.each(function(){
                $(this).parent().css({
                    'padding':'0',
                    'width':'173px'
                })
            });
            $('body').css('overflow','hidden');
        });
        function addTab(title, url,target){  
         var a=document.getElementById("tabName").value;
         $(".easyui-linkbutton").removeClass("selected");
         $(target).addClass("selected");
         if (parent.$("#tt").tabs("exists", a)) {	//判断tab是否存在，如存在就删掉，然后新开一个tab
        	 parent.$("#tt").tabs("close", a);
		 }
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
                $('#tt').tabs('add',{  
                    title:title,  
                    content:content,  
                    closable:true,
                    tools : [ {
    					iconCls : 'icon-mini-refresh',
    					handler : function() {
    						refreshTab();
    					}
    				} ]
                });  
            document.getElementById("tabName").value=title;    
        }
      //刷新tab
    	function refreshTab() {
    		var mainTab = $("#tt");
    		var href = mainTab.tabs("getSelected").panel("options").href;
    		if (href) {/*说明tab是以href方式引入的目标页面*/
    			var index = mainTab.tabs("getTabIndex", mainTab.tabs("getSelected"));
    			mainTab.tabs("getTab", index).panel("refresh");
    		} else {/*说明tab是以content方式引入的目标页面*/
    			var panel = mainTab.tabs("getSelected").panel("panel");
    			var frame = panel.find("iframe");
    			try {
    				if (frame.length > 0) {
    					for (var i = 0; i < frame.length; i++) {
    						frame[i].contentWindow.document.write("");
    						frame[i].contentWindow.close();
    						frame[i].src = frame[i].src;
    					}
    					if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
    						try {
    							CollectGarbage();
    						} catch (e) {
    						}
    					}
    				}
    			} catch (e) {
    			}
    		}
    	}
    	
      //密码修改
      function updatePwd(){
    	  updatePwdDialog.dialog({
				closed : false
		  });
      }
    </script>
</body>
</html>
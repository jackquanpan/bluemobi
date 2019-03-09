<%@page import="cn.bluemobi.util.config.Config"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>你访问的页面不存在或被删除！</title>
<%@ include file="../page/basePath.jsp" %>
<meta http-equiv=content-type content="text/html; charset=gb2312">
<meta http-equiv=refresh content=5;url=admin/test.htm />
<style type=text/css>
.font14 {
	font-size: 14px
}

.font12 {
	font-size: 12px
}

.font12 a {
	font-size: 12px;
	color: #cc0000;
	text-decoration: none;
}
</style>
</head>
<body>
	<table height=500 cellspacing=0 cellpadding=0 width=500 align=center
		background="resource/images/x.gif" border=0>
		<tbody>
			<tr>
				<td height=330></td>
			</tr>
			<tr>
				<td valign=top>
					<div class=font14 align=center>
						<strong>你访问的页面<font color=#0099ff>不存在</font>或被<font
							color=#ff0000>删除！<br>
						</font>
						</strong><span class=font12><font color=#666666>5秒后自动返回
						<a href="admin/main.htm"></a>后台系统........ </font>
						</span>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>


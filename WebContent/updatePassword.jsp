<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/myproject/logout">注销</a> 
	<br/>
	<a href="userInfo.jsp">个人信息</a> 	
	<br/>
	
	<form action="/myproject/update" method="post">
	<!-- 用户名:<input type="text" name="username"/><br/>
	密码：<input type="password" name="password"/><br/>
		<input type="submit" name="提交"/> -->	
		<table>
			<tr>
				<td>旧密码</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>新密码</td>
				<td><input type="password" name="newPassword"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="登录" value="修改"/>
					<input type="reset" name="重置"/>
				</td>
			</tr>				
		</table>		
	</form>
	
	
</body>
</html>
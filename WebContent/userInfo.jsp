<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/myproject/updateInfo" method="post">

		<table>
			<tr>
				<td>昵称</td>
				<td><input type="text" name="nickname"/></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input type="text" name="gender"/></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="age"/></td>
			</tr>
			<tr>
				<td>星座</td>
				<td><input type="text" name="constellation"/></td>
			</tr>
			<tr>
				<td>学校</td>
				<td><input type="text" name="school"/></td>
			</tr>
			<tr>
				<td>爱好</td>
				<td><input type="text" name="hobby"/></td>
			</tr>
			<tr>
				<td>省份</td>
				<td><input type="text" name="province"/></td>
			</tr>
			<tr>
				<td>个性签名</td>
				<td><input type="text" name="signature"/></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="保存" value="保存"/>
					<input type="reset" name="重置"/>
				</td>
			</tr>				
		</table>		
	</form>
	
</body>
</html>
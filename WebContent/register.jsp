<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<title>Insert title here</title>
</head>
<body>
        <!-- 	<a href="/myproject/login">点我</a> -->
    <form action="/myproject/register" method="post" class="form-group">	
            <div><h2 style="text-align:center">用户注册</h2></div>
            <div class="form-item">
                <label for="phone" class="i-label">手机号:</label> 
                <input type="text" name="phoneNum" id="phone"class="i-input"/>
            </div>
            <div class="form-item">
                <label for="password" class="i-label" >密码:</label>
                <input type="password" name="password" id="password" class="i-input"/>
            </div>
            <div class="form-item">
                <div class="toggle-text">已有账号。  <a href="index.jsp">点击登录</a></div>
            </div>    
            <div class="form-item btn-group">
                <button type="submit" name="注册" class="btn">注 册</button>
                <!-- <button type="reset" name="重 置" class="btn btn-reset">重 置</button>-->           
                 </div>       
    </form>
</body>
</html>
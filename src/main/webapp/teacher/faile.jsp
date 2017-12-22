<%--
  Created by IntelliJ IDEA.
  User: syl
  Date: 2017/11/25
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录失败</title>
</head>
<body>
用户名或密码错误
<script>
    setTimeout("window.parent.location.href =\"\login.jsp?faile=true\"",2000);
</script>
</body>
</html>

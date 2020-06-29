<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/8
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("button").click(function () {
                window.history.back()
            })
        })
    </script>
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container" style="text-align: center">
    <h2 class="form-signin-heading" ><i class="glyphicon glyphicon-log-in"></i> 尚筹网信息</h2>
    <%--requestScope相当于request的map--%>
    <%--requestScope.exception相当于request.getAttribute("exception")--%>
    <%--requestScope.exception.message相当于exception.getMessage()--%>
    <h3>${requestScope.exception.message}</h3>
    <button style="width: 150px;margin: 50px auto 0px auto" class="btn btn-group-lg btn-success btn-block">点我返回</button>
</div>

</body>
</html>
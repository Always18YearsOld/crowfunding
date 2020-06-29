<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<%@include file="/WEB-INF/include-head.jsp" %>

<body>

<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

                <ol class="breadcrumb">
                    <li><a href="/admin/to/main/page.html">首页</a></li>
                    <li><a href="admin/get/page.html">数据列表</a></li>
                    <li class="active">更改</li>
                </ol>
                <div class="panel panel-default">
                    <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                    <div class="panel-body">
                        <form action="admin/update.html" method="post" role="form">
                            <%--<p>${requestScope.exception.message}</p>--%>
                            <input type="hidden" name="id" value="${requestScope.admin.id}"/>
                            <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                            <input type="hidden" name="keyword" value="${param.keyword}"/>

                            <p>${requestScope.exception.message}</p>
                            <div class="form-group">
                                <label for="exampleInputPassword1">登陆账号</label>
                                <input name="loginAcct" value="${requestScope.admin.loginAcct}" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆账号">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">登陆密码</label>
                                <input name="userPswd" value="${requestScope.admin.userPswd}" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入登陆密码">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">用户昵称</label>
                                <input name="userName" value="${requestScope.admin.userName}" type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入用户名称">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">邮箱地址</label>
                                <input name="email" value="${requestScope.admin.email}" type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
                                <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                            </div>
                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                            <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>

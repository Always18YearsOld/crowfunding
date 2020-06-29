<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh_CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<script type="text/javascript">

    $(function () {
        $("#toRightBtn").click(function () {
            // 把左边的第一个未分配追加到右边的第二个
            $("select:eq(0)>option:selected").appendTo("select:eq(1)");
        })
        $("#toleftBtn").click(function () {
            // 把左边的第一个未分配追加到右边的第二个
            $("select:eq(1)>option:selected").appendTo("select:eq(0)");
        })
        //提交表单前，全选，避免选中部分才保存
        $("#submitBtn").click(function () {
            $("select:eq(1)>option").prop("selected","selected");
        })
    })
</script>
<body>

<%@include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="assign/do/role/assign.html" method="post"  role="form" class="form-inline">
                        <input type="hidden" name="adminId" value="${param.adminId}"/>
                        <input type="hidden" name="pageNum" value="${param.pageNum}"/>
                        <input type="hidden" name="keyword" value="${param.keyword}"/>

                        <div class="form-group">
                            <label for="exampleInputPassword1">未分配角色列表</label><br>
                            <select  class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.unAssignedRoleList}" var="role">
                                <option value="${role.id}">${role.id}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="toRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="toleftBtn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="exampleInputPassword1">已分配角色列表</label><br>
                            <select
                                    name="roleIdList"
                                    class="form-control" multiple="multiple" size="10" style="width:100px;overflow-y:auto;">
                                <c:forEach items="${requestScope.assignedRoleList}" var="role">
                                    <option value="${role.id}">${role.id}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button  id="submitBtn" style="width: 150px;margin: 50px auto 0px auto" type="submit" class="btn btn-lg btn-success btn-block">登录</button>

                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
</div>

</body>
</html>

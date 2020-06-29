<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

    <%--引入jquery--%>
<
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <%--物联网--%>
    <%--<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>--%>
    <script type="text/javascript">
        // 准备要发送的数组
        var array = [5, 8, 12]; // 将 JSON 数组转换成 JSON 字符串 // "[5,8,12]"
        var arrayStr = JSON.stringify(array);
        $(function() {
            $("#btn3").click(function () {
                $.ajax({
                        "url": "send/array/three.html", //请求目标地址
                        "type":"post",              //请求方式
                        "data":arrayStr,
                        "contentType":"application/json;charset=UTF-8",

                        "dataType":"text",
                        "success":function (response) { //response是响应体参数
                            alert(response)
                        },
                        "error":function (response) {
                            alert(response)
                        }
                    }
                )
            })
            $("#btn5").click(function () {
                layer.msg("layer")
            })
        })
        $(function() {
            $("#btn1").click(function () {
                    $.ajax({
                    "url": "send/array.html", //请求目标地址
                    "type":"post",              //请求方式
                    "data":{
                        "array":[5,8,12]
                    },
                    "dataType":"text",
                    "success":function (response) { //response是响应体参数
                        alert(response)
                    },
                    "error":function (response) {
                        alert(response)
                    }
                    }
                )
            })

        })
    </script>
</head>
<body>
<%--<a href="test/ssm.html">测试ssm</a>--%>
<a id="ssm" href="/test/ssm.html">Ssm</a>
<button id="btn1">Send [5,8,12] One</button>
<button id="btn3">Send [5,8,12] Three</button>
<button id="btn5">点我</button>
</body>
</html>
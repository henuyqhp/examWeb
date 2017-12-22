<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
    <link href="../css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-1.11.3.js" ></script>
    <script type="text/javascript" src="../js/bootstrap.js" ></script>
    <script>
        $(function(){
            $("body").animate({opacity:"1"},1000);
        });
    </script>
    <style>
        *{margin:0;padding:0;}
        body{opacity: 0;}
        .container .row .contenTitle{margin-top:30px; margin-bottom:15px; text-align: center; color:#261C12;}
        .ibox{border:2px solid #E7EAEC;}
        .ibox-title{border-top-color:#261C12; box-shadow:0px 5px 5px 5px #261C12;}
        .ibox-content{box-shadow:0px 5px 5px 0px #261C12;}
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-12 contenTitle">
            <img src="../img/clear.png"/>
            <h1><i>考试清理</i></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12 col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>现有考试</h5>
                </div>
                <div class="ibox-content">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>考试名称</th>
                            <th>开始时间</th>
                            <td>结束时间</td>
                            <th>创建人</th>
                            <th>删除考试</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${list != null}">
                                <c:forEach var="item" items="${list}">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td>${item.starttime}</td>
                                        <td>${item.endtime}</td>
                                        <td>${item.ecreator}</td>
                                        <td><input type="submit" class="btn btn-info" onclick="deletExam(${item.id})" value="删除"></td>

                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h2>暂无考试，请添加!</h2>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function deletExam(id) {
        $.post("/admin/DeletExam.do",{ExamId:id},function (data) {
                if (data.code == 1) {
                    alert("删除成功")
                    window.location.reload();
                } else {
                    alert("删除失败")
                }
            })
    }
</script>
</html>

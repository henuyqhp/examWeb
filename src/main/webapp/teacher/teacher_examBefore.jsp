<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title></title>
		<link rel="stylesheet" href="../../css/bootstrap.min.css" />
		<link href="../../css/style.min862f.css?v=4.1.0" rel="stylesheet">
		<script type="text/javascript" src="../../js/jquery-1.11.3.js" ></script>
		<script type="text/javascript" src="../../js/bootstrap.js" ></script>
		<script type="text/javascript" src="../../js/easyui/themes/default/easyui.css"></script>
		<script type="text/javascript" src="../js/app.js"></script>
		<style>
			*{margin:0;padding:0;}
			body{opacity: 0;}
			.container .row .contenTitle{margin-top:30px; margin-bottom:15px; text-align: center; color:#261C12;}
			.ibox{border:2px solid #E7EAEC;}
			.ibox-title{border-top-color:#261C12; box-shadow:0px 5px 5px 5px #261C12;}
			.ibox-content{box-shadow:0px 5px 5px 0px #261C12;}
		</style>
	</head>
	<body >
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 contenTitle">
					<img  src="../../img/before.png" />
					<h1><i>考前操作</i></h1>
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-12 col-sm-12 " >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>添加考试</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" action="/teacher/examBefore.do" id="addExamForm"  method="post" class="form-inline">
								<input type="hidden" name="code" id="code" value="${code}">
								<div class="form-group">
	                                <%--<label for="ename" class="sr-only">考试名称</label>--%>
	                                <input type="text" placeholder="考试名称" name="ename" id="ename" class="form-control">
	                            </div>
								<div class="form-group">
									<label for="type" class="sr-only">考试类型</label>
									<input type="text" placeholder="考试类型" name="type"  id="type" class="form-control">
								</div>
	                            <div class="form-group">
	                                <label for="startTime" class="sr-only">考试开始时间</label>
	                                <input type="datetime-local" value="2017/12/03 下午 01：58" name="startTime" id="startTime" class="form-control">
	                            </div>
								<div class="form-group">
									<label for="endTime" class="sr-only">考试结束时间</label>
									<input type="datetime-local" value="2017/12/03 下午 01：58" name="endTime" id="endTime" class="form-control">
								</div>
	                            <div class="checkbox m-l m-r-xs">
	                                <label><input type="checkbox">自动开始</label>
	                            </div>
	                            <button class="btn btn-white" type="button" onclick="addExam()">添加</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					 <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>现有考试</h5>
	                  	</div>
	                    <div class="ibox-content">
							<div class="ibox-content">

								<%--<table class="easyui-datagrid" style="width:400px;height:250px"--%>
									   <%--data-options="url:'/teacher/examList.do:'datagrid_data.json',fitColumns:true,singleSelect:false,method:'post'">--%>
									<%--<thead>--%>
									<%--<tr>--%>
										<%--<th data-options="field:'ename',width:100">ename</th>--%>
										<%--<th data-options="field:'starttime',width:100">ename</th>--%>
										<%--<th data-options="field:'endtime',width:100,align:'right'">ename</th>--%>
									<%--</tr>--%>
									<%--</thead>--%>
								<%--</table>--%>
	                        <table class="table table-bordered">
	                            <thead>
	                                <tr>
	                                    <th>考试名称</th>
	                                    <th>开始时间</th>
										<th>结束时间</th>
	                                    <th>创建人</th>
	                                    <th>上传试卷</th>
	                                    <th>自动开始</th>
	                                    <th>进行中</th>
	                                    <th>已结束</th>
	                                    <th>已归档</th>
	                                    <th>可用</th>
	                                </tr>
	                            </thead>
	                            <tbody>
								<c:choose>
									<c:when test="${list != null}">
										<c:forEach var="item" items="${list}">
											<tr>
												<td>${item.ename}</td>
												<td>${item.starttime}</td>
												<td>${item.endtime}</td>
												<td>${item.ecreator}</td>
												<td><button type="button" value="上传试卷" name="upload"/></td>
												<td><c:if test="${item.status== 1}" ><img src="../img/yes.png"/></c:if></td>
												<td><c:if test="${item.status== 1}" ><img src="../img/yes.png"/></c:if></td>
												<td><c:if test="${item.status== 2}" ><img src="../img/yes.png"/></c:if></td>
												<td><c:if test="${item.status== 3}" ><img src="../img/yes.png"/></c:if></td>
												<td><c:if test="${item.eenable== 1}" ><img src="../img/yes.png"/></c:if></td>
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
		</div>
	</body>
	<script type="text/javascript">
        $(function(){
            $("body").animate({opacity:"1"},1000);
            var err = $("#code").val();
            if(err != ""){
                alert("服务器错误")
                window.parent.top.location.href = "505.html";
            }
        });
        function addExam() {
            $.post("/teacher/newExam.do",$("#addExamForm").serialize(),function(data){
                alert(1)
                if(data.code == 1){
                  alert("添加成功");
                  $("#addExamForm").submit();
			  }else {
                  alert("添加失败")
			  }
            });
        }
	</script>
</html>

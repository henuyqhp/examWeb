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

	                        <table class="table table-bordered" id="table">
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
	                            <tbody id="show">
	                            </tbody>
	                        </table>
									<ul class="pagination">
										<c:choose>
											<c:when test="${page != null}">
												<li><a href="javascript:void(0);" onclick="divide(1)">首页</a></li>
												<c:forEach var="x" begin="1" end="${page.pages}">
													<li><a href="javascript:void(0);" onclick="divide(${x})">${x}</a></li>
												</c:forEach>
												<li><a href="javascript:void(0);" onclick="divide(${page.pages})">尾页</a></li>
											</c:when>
										</c:choose>
									</ul>
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
            divide(1)
        });
        function addExam() {
            $.post("/teacher/newExam.do",$("#addExamForm").serialize(),function(data){
                if(data.code == 1){
                  alert("添加成功");
                  $("#addExamForm").submit();
			  }else {
                  alert("添加失败")
			  }
            });
        }

        function divide(pageNum) {
            $.post('/teacher/selcetByPage.do',{pageNum:pageNum},function (data) {
				if(data.code == 1){
                    $("#show").html("")
                    for(var i in data.list){

                        var ename = data.list[i].ename
                        var startTime = ToDate2(data.list[i].starttime)
                        var endtime = ToDate2(data.list[i].endtime)
                        var ecreator =data.list[i].ecreator
                        var filename =  data.list[i].filename != null? '<img src="../img/yes.png">':""
						var status1 = data.list[i].status==3? '<img src="../img/yes.png">':""
						var status2 = data.list[i].status==1? '<img src="../img/yes.png">':""
						var status3 = data.list[i].status==2? '<img src="../img/yes.png">':""
						var status4 = data.list[i].eenable==0? '<img src="../img/yes.png">':""
                        var status5 = data.list[i].eenable==1? '<img src="../img/yes.png">':""
                        var table = '<tr><td>'+ename+'</td><td>' + startTime+ '</td><td>' +
                        endtime+'</td><td>'+ ecreator +'</td><td>'+
                        filename+ '</td><td>'+ status1+ '</td><td>'+
                        status2+ '</td><td>'+ status3+ '</td><td>'+ status4+ '</td><td>'+status5+'</td></tr>'
                        $('#show').append(table)

                    }
				}
            })
        }
	</script>
</html>

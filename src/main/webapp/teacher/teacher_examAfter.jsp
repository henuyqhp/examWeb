<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
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
		<script type="text/javascript" src="../js/app.js"></script>
		<script>
			$(function(){
				$("body").animate({opacity:"1"},1000);
				divide(1)
			});
			function stop(id,pageNum) {
			    $.post('/teacher/stop.do',{id:id},function (data) {
					if(data.code == 1){
					    alert("成功")
						divide(pageNum)
					}else{
					    alert("失败")
					}
                })

            }

            function divide(pageNum) {
                $.post('/teacher/selcetByPage.do',{pageNum:pageNum},function (data) {
                    if(data.code == 1){
                        $("#show").html("")
                        for(var i in data.list){
							var id = data.list[i].id;
                            var ename = data.list[i].ename
                            var endtime = ToDate2(data.list[i].endtime)
                            var ecreator =data.list[i].ecreator
                            var filename =  data.list[i].filename != null? '<img src="../img/yes.png">':""
                            var status1 = data.list[i].status==3? '<img src="../img/yes.png">':""
                            var status2 = data.list[i].status==1? '<img src="../img/yes.png">':""
                            var status3 = data.list[i].status==2? '<img src="../img/yes.png">':""
                            var status4 = data.list[i].eenable==0? '<img src="../img/yes.png">':""
                            var table = '<tr><td>'+ename+'</td><td>'  + endtime+
								'</td><td>'+ ecreator +'</td><td>'+
                                filename+ '</td><td>'+ status1+ '</td><td>'+
                                status2+ '</td><td>'+ status3+ '</td><td>'+ status4+
								'</td><td><button class="btn btn-info" style="margin-right: 10px" onclick="stop('+id+','+pageNum+')">停止考试</button>'+
								'<button class="btn btn-info" onclick="down('+id+')">下载试卷</button></td></tr>'
                            $('#show').append(table)

                        }
                    }
                })
            }
            function down(id) {
                $(location).attr('href', '/teacher/downExam.do?id='+id);
            }
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
					<img src="../../img/after.png" />
					<h1><i>考后操作</i></h1>
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
	                                    <th>考试结束时间</th>
	                                    <th>创建人</th>
	                                    <th>上传试卷</th>
	                                    <th>自动开始</th>
	                                    <th>进行中</th>
	                                    <th>已结束</th>
	                                    <th>已归档</th>
										<th></th>
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
	</body>
</html>

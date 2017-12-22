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
		<script>
			$(function(){
				$("body").animate({opacity:"1"},1000);
			});
            function findStudent() {
                var findkey = $("#findkey").val()
				alert(findkey + " 999")
                $.post("/teacher/findStudent.do",
                    {findkey:findkey},
                     function(data) {
                        if(data.code == 1){
                            $("#showtable").html("")
                            alert("找到")
                            var name = data.student.sname
                            var ip = data.student.ip
                            var sno = data.student.sno
							$("#sno").val(sno)
                            var classNum = data.student.classnum
                            var table = '<tr><td>'+ sno+ '</td><td>'+ name+ '</td>'+ '</td><td>'+ classNum + '</td>'
                                + '<td>'+ ip+ '</td></tr>';
                            $("#showtable").append(table)
                            $("#ename").attr("value",name)
                            $("#eip").attr("value",ip)
                            $("#eno").attr("value",sno)
                            $("#eclassNum").val(3)
							$("#btn_forbit").attr("disabled",false)
                        }else{
                            alert("没找到")
                            $("#showtable").html("暂无该学生信息")
                        }
                }
                );
            }

            function unlock() {
				var sno = $("#sno").val();
				$.post("/teacher/unlock.do",{sno:sno},function (data) {
					if (data.code == 1){
						alert("解锁成功")
					}else {
					    alert("解锁失败")
					}
                });
            }
		</script>
		<style>	
			*{margin:0;padding:0;}
			body{opacity: 0;}
			.container .row .contenTitle{margin-top:50px; margin-bottom:30px; text-align: center; color:#261C12;}
			.ibox{border:2px solid #E7EAEC;}
			.ibox-title{border-top-color:#261C12; box-shadow:0px 5px 5px 5px #261C12;}
			.ibox-content{box-shadow:0px 5px 5px 0px #261C12;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 contenTitle">
					<img src="../../img/unlock.png" />
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-8 col-sm-8">
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>按学生查找已登录信息</h5>
	                    </div>
	                    <div class="ibox-content">
							<div class="form-group">
								<input type="hidden" name="sno" id="sno" value="">
								<label for="findkey" class="sr-only">学号/姓名/ip</label>
								<input type="text" placeholder="学号/姓名/ip" name="findkey" id="findkey" class="form-control">
							</div>
							<button class="btn btn-info" onclick="findStudent()">查找</button>
	                    </div>
	                </div>
	            </div>
				<div class="col-xs-4 col-sm-4" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>禁用</h5>
	                    </div>
						<div class="ibox-content">
							<button type="button" id="btn_forbit" class="btn btn-primary btn-lg" onclick="unlock()" disabled="disabled">解锁</button>
						</div>
						</div>
	            </div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					 <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>查找结果</h5>
	                  	</div>
	                    <div class="ibox-content">
	                        <table class="table table-bordered">
	                            <thead>
	                                <tr>
	                                    <th>学号</th>
	                                    <th>姓名</th>
	                                    <th>班级</th>
	                                    <th>ip地址</th>
	                                </tr>
	                            </thead>
	                            <tbody id="showtable">
	                            </tbody>
	                        </table>
	                    </div>
	               </div>
				</div>
			</div>
		</div>
	</body>
</html>

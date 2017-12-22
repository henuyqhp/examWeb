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
		<script type="text/javascript" src="../../js/easyui/jquery.min.js" ></script>
		<script type="text/javascript" src="../../js/bootstrap.js" ></script>
		<style>
			*{margin:0;padding:0;}
			body{opacity: 0;}
			.container .row .contenTitle{margin-top:50px; margin-bottom:30px; text-align: center; color:student.png;}
			.ibox{border:2px solid #E7EAEC;}
			.ibox-title{border-top-color:#261C12; box-shadow:0px 5px 5px 5px #261C12;}
			.ibox-content{box-shadow:0px 5px 5px 0px #261C12;}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 contenTitle">
					<img src="../../img/student.png" />
					<h1><i>学生信息</i></h1>
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-8 col-sm-8 col-xs-offset-2 col-sm-offset-2" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>添加单个学生</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form  method="post" role="form" class="form-inline" id="addStudentForm">
	                            <div class="form-group">
	                                <label for="sno" class="sr-only">学号</label>
	                                <input type="text" placeholder="学号" name="sno" id="sno" class="form-control">
	                            </div>
	                            <div class="form-group">
	                                <label for="sname" class="sr-only">姓名</label>
	                                <input type="text" placeholder="姓名" name="sname" id="sname" class="form-control">
	                            </div>

								<div class="form-group">
									<select class="form-control" id="classNum" name="classNum">
										<option value="1">15-1</option>
										<option value="2">15-2</option>
										<option value="3">15-3</option>
										<option value="4">15-4</option>
									</select>
								</div>
	                            <br /><br />
	                            <button class="btn btn-info" onclick="addStudent()">添加</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
			<div class="row" >
				<div class="col-xs-8 col-sm-8 col-xs-offset-2 col-sm-offset-2" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>查找学生信息</h5>
	                    </div>
	                    <div class="ibox-content">
	                            <div class="form-group">
	                                <label for="findkey" class="sr-only">学号/姓名/ip</label>
	                                <input type="text" placeholder="学号/姓名/ip" name="findkey" id="findkey" class="form-control">
	                            </div>
	                            <button class="btn btn-info" onclick="findStudent()">查找</button>
							<div class="ibox-content">
								<table class="table table-bordered">
									<thead>
									<tr>
										<th>姓名</th>
										<th>学号</th>
										<th>ip</th>
										<th>班级</th>
										<th>是否能用</th>
										<th>编辑</th>
									</tr>
									</thead>
									<tbody id="showStudentInformation" >
									</tbody>
								</table>
							</div>
	                    </div>
	                </div>
	            </div>
			</div>
		</div>

		<div class="modal fade" id="editStudent" tabindex="-1" role="dialog" aria-labelledby="editStudentLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="editStudentLabel">修改学生信息</h3>
					</div>
					<div class="modal-body">
						<form  role="form" id="editStudentForm">
							<div class="form-group">
								<input type="text" id="ename" name="ename" class="form-control" placeholder="姓名" required="">
							</div>
							<div class="form-group">
								<input type="text" id="eip" name="eip" class="form-control" placeholder="ip" required="">
							</div>
							<div class="form-group">
								<input type="text" id="eno" name="eno" class="form-control" placeholder="学号" required="">
							</div>
							<div class="form-group">
								<select class="form-control" id="eclassNum" name="eclassNum">
									<option value="1">15-1</option>
									<option value="2">15-2</option>
									<option value="3">15-3</option>
									<option value="4">15-4</option>
								</select>
							</div>
						</form>
							<button onclick="editStudent()" id="btn_edit" class="btn btn-info block full-width m-b">修改</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script>
        $(function(){
            $("body").animate({opacity:"1"},1000);
        });
        function addStudent() {
            $.ajax({
                type : "POST",
                url : "/teacher/addStudent.do",
                async: false,
                data :$("#addStudentForm").serialize(),
                dataType : 'json',
                success : function(data) {
                    if(data.code == 1){
                        alert("添加成功")
                    }else{
                        alert("添加失败")
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
        var stinfo
        function findStudent() {
            $.ajax({
                type : "POST",
                url : "/teacher/findStudent.do",
                data :{findkey:$("#findkey").val()},
                dataType : 'json',
                success : function(data) {
                    if(data.code == 1){
                        $("#showStudentInformation").html("")
                        alert("找到")
                        stinfo = data.student
						var name = data.student.sname
						var ip = data.student.ip
						var sno = data.student.sno
						var classNum = data.student.classnum
						var table = '<tr><td>'+ name+ '</td><td>'+ sno+ '</td>'+ '</td><td>'+ ip+ '</td>'
							+ '<td>'+ classNum+ '</td>'+ '</td><td>'+ 1+ '</td>'+ '</td><td><button onclick="a(stinfo)" class="btn btn-info">修改</button></td></tr>';
						$("#showStudentInformation").append(table)
						$("#ename").attr("value",name)
						$("#eip").attr("value",ip)
						$("#eno").attr("value",sno)
						$("#eclassNum").val(3)
                    }else{
                        alert("没找到")
                        $("#showStudentInformation").html("")
                    }
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
        function a(s) {
			$("#editStudent").modal("show")
        }
        function editStudent() {
			$.post("/teacher/editStudent.do",
				$("#editStudentForm").serialize(),
			function (data) {
				if(data.code == 1){
				    alert("修改成功")
                    $("#editStudent").modal("hide")
                    $("#showStudentInformation").html("")
				}else{
				    alert("修改失败")
				}
            })
        }
	</script>
</html>

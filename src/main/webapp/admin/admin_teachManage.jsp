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

            function changeval(){
                var check = document.getElementById("checkID");
                if(check.checked == true){
                    document.getElementById("checkID").value = "1";
                }else{
                    document.getElementById("checkID").value = "0";
                }
            }
            function isCheck() {
				if($("#permission").prop("checked")){
                    $("#permission").val(1)
				}else{
                    $("#permission").val(0)
				}
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
					<img src="../img/teachManage.png" />
					<h1><i>教师管理</i></h1>
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-12 col-sm-12 " >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>添加教师</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" class="form-inline" id="addTeacherForm" name="addTeacherForm" action="/admin/teacherBefore.do" method="post">

								<input type="hidden" name="code" id="code" value="${code}">
	                            <div class="form-group">
	                                <label for="Account" class="sr-only">用户名</label>
	                                <input type="text" placeholder="请输入用户名" id="Account" name="teacherID" class="form-control">
	                            </div>
								<div class="form-group">
									<label for="BianHao" class="sr-only">工号</label>
									<input type="text" placeholder="请输入教师工号" id="BianHao" name="teacherNo" class="form-control">
								</div>
	                            <div class="form-group">
	                                <label for="Password" class="sr-only">密码</label>
	                                <input type="password" placeholder="请输入口令" id="Password" name="teacherPassword" class="form-control">
	                            </div>
	                            <div class="form-group">
	                                <label for="Name" class="sr-only">姓名</label>
	                                <input type="text" placeholder="请输入姓名" id="Name" name="teacherName" class="form-control">
	                            </div>
	                            <div class="checkbox m-l m-r-xs">
									<%--<input type="hidden" name="permission" value="0" >--%>
	                                <label><input type="checkbox" id="permission" name="permission" value="1" checked="true" onclick="isCheck()">管理员</label>
	                            </div>
	                            <button class="btn btn-white" onclick="addTeacher()">添加</button>


	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					 <div class="ibox float-e-margins">
                    	<div class="ibox-title">
	                        <h5>教师列表</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <table class="table table-striped" id="table">
	                            <thead>
	                                <tr>
	                                    <th>id</th>
	                                    <th>用户名</th>
	                                    <th>姓名</th>
	                                    <th>是否管理员</th>

	                                </tr>
	                            </thead>

								<tbody id="show">
								</tbody>
	                        </table>
							<ul class="pagination">
								<c:choose>
									<c:when test="${pageInfo != null}">
										<li><a href="javascript:void(0);" onclick="divide(1)">首页</a></li>
										<c:forEach var="x" begin="1" end="${pageInfo.pages}">
											<li><a href="javascript:void(0);" onclick="divide(${x})">${x}</a></li>
										</c:forEach>
										<li><a href="javascript:void(0);" onclick="divide(${pageInfo.pages})">尾页</a></li>
									</c:when>
								</c:choose>
							</ul>
	                    </div>
	                </div>
	  			</div>
			</div>
			<div class="row"  >
				<div class="col-xs-12 col-sm-12 " >
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>修改教师</h5>
						</div>

						<div class="ibox-content">
							<div class="form-group">
								<label for="findTeacherkey" class="sr-only">教师id</label>
								<input type="text" placeholder="教师id" name="findTeacherkey" id="findTeacherkey" class="form-control">
							</div>
							<button class="btn btn-info" onclick="findTeacher()">查找</button>

							<div class="ibox-content">
								<table class="table table-bordered" >
									<thead>
									<tr>
										<th>id</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>是否是管理员</th>
										<th>编辑</th>
										<th>删除</th>
									</tr>
									</thead>
									<tbody id="showTeacherInformation" >
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="editTeacher" tabindex="-1" role="dialog" aria-labelledby="editTeacherLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="editTeacherLabel">修改教师信息</h3>
					</div>
					<div class="modal-body">
						<form  role="form" id="editTeacherForm">
							<div class="form-group">
								<input type="text" id="Tid" name="Tid" class="form-control" placeholder="id" required="" >
							</div>
							<div class="form-group">
								<input type="text" id="Tusername" name="Tusername" class="form-control"  placeholder="用户名" required="">
							</div>
							<div class="form-group">
								<input type="text" id="Tname" name="Tname" class="form-control" placeholder="姓名" required="">
							</div>
							<div class="form-group">
								<input type="password" id="Tpassword" name="Tpassword" class="form-control" placeholder="密码" required="">
							</div>
						</form>
						<button onclick="editTeacher()" id="btn_edit" class="btn btn-info block full-width m-b">修改</button>
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
        function addTeacher() {
            alert("开始添加教师")
            $.post("/admin/addTeacher.do", $("#addTeacherForm").serialize(), function (data) {
                if (data.code == 1) {
                    alert("添加成功");
                     $("#addTeacherForm").submit();
                    window.location.reload();
                } else {
                    alert("添加失败")
                }
            });
        }
		var teinfo
		function findTeacher() {
                $.ajax({
                    type : "POST",
                    url : "/admin/findTeacher.do",
                    data :{findTeacherkey:$("#findTeacherkey").val()},
                    dataType : 'json',
                    success : function(data) {
                        if(data.code == 1){
                            $("#showTeacherInformation").html("")

                            teinfo = data.teacher
                            var id = data.teacher.id
                            var Tusername = data.teacher.tno
                            var  Tname = data.teacher.tname
                            var  admin = data.teacher.tadmin
                            var table = '<tr><td>'+ id+ '</td><td>'+ Tusername+ '</td>'+ '</td><td>'+ Tname+ '</td>'
                                + '<td>'+ admin+ '</td><td><button onclick="a(teinfo)" class="btn btn-info">修改</button></td><td><input type="submit" class="btn btn-info" onclick="delet()" value="删除"></td>/tr>';
                            $("#showTeacherInformation").append(table)
                            $("#Tid").attr("value",id)
                            $("#Tusername").attr("value",Tusername)
                            $("#Tname").attr("value",Tname)
                            $("#Tpassword").attr("value",Tpassword)
                        }else{
                            alert("没找到")
                            $("#showTeacherInformation").html("")
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
                $("#editTeacher").modal("show")
            }
		function editTeacher() {
                $.post("/admin/ReviseTeacher.do",
                    $("#editTeacherForm").serialize(),
                    function (data) {
                        if (data.code == 1) {
                            alert("修改成功")
                            $("#editTeacher").modal("hide")
                            $("#showTeacherInformation").html("")
                            $.post("/admin/teacherBefore.do")
                            window.location.reload();
                        } else {
                            alert("修改失败")
                        }
                    })



                }
        function delet() {
            $.post("/admin/DeleTeacher.do",
                $("#editTeacherForm").serialize(), function (data) {
                    if (data.code == 1) {
                        alert("删除成功")
                        window.location.reload();
                    } else {
                        alert("修改失败")
                    }
                })
        }
        function divide(pageNum) {

            $.post('/admin/selcetTeacherByPage.do',{pageNum:pageNum},function (data) {
                if(data.code == 1){
                    $("#show").html("")
                    for(var i in data.list){
                        var id = data.list[i].id
                        var Tusername = data.list[i].tno
                        var  Tname = data.list[i].tname
						var Tadmin = data.list[i].tadmin
                        var table = '<tr><td>'+ id+ '</td><td>'+ Tusername+ '</td>'+ '</td><td>'+ Tname+ '</td>'
                            + '<td>'+ Tadmin+ '</td></tr>';
                        $('#show').append(table)
                    }
                }
            })
        }
	</script>
</html>

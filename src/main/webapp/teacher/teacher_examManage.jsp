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
		<style>
			*{margin:0;padding:0;}
			body{opacity: 0;}
			.container .row .contenTitle{text-align: center; color:#261C12;}
			.ibox{border:2px solid #E7EAEC;}
			.ibox-title{border-top-color:#261C12; box-shadow:0px 5px 5px 5px #261C12;}
			.ibox-content{box-shadow:0px 5px 5px 0px #261C12;}
		</style>
	</head>
	<body >
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 contenTitle">
					<img src="../../img/teachManage.png" />
					<h1><i>考中管理</i></h1>
				</div>
			</div>
			<div class="row" id="choseExam">
				<div class="col-xs-8 col-sm-8 col-xs-offset-2 col-sm-offset-2" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>选择考试</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" class="form-inline" action="/teacher/lookExam.do" method="post">
								<input type="hidden" id="code" name="code" value="${pd.code}">
								<input type="hidden" id="efile" name="efile" value="${pd.exam.filename}"  >
	                            <div class="form-group">
	                                <label for="ename" class="sr-only">考试名称</label>
	                                <input type="text" placeholder="考试名称" id="ename" name="ename" class="form-control">
	                            </div>
	                            <button class="btn btn-white" type="submit">查询</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
			<div class="row" id="uploadExam">
				<div class="col-xs-4 col-sm-4">
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>上传试卷</h5>
	                    </div>
	                    <div class="ibox-content" >
	                        <form role="form" class="form-inline" enctype="multipart/form-data" method="post" >
								<input type="hidden" id="eid" name="eid" value="${pd.exam.id}">
								<label for="upload" class="btn btn-info">浏览</label>
	                            <input type="text" id="text" />
	                            <input id="upload" value="${pd.exam.filename != '' ? '试卷已经上传':''}" name="upload" type="file" onchange="text.value=upload.value" style="display: none;"/>
								<button class="btn btn-warning" onclick="doUpload()" type="button">上传</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
	            <div class="col-xs-4 col-sm-4 col-xs-offset-4 col-sm-offset-4" id="readExcel">
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>导入学生名单</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" class="navbar-form" enctype="multipart/form-data" method="post" >
								<label for="excel" class="btn btn-info">浏览</label>
								<input type="text" id="excel_text" />
								<input id="excel" name="excel" type="file"
									   onchange="excel_text.value=excel.value" style="display: none;"/>
								<button id="btn_uploadExcel" class="btn btn-warning" onclick="doUploadExcel()" type="button">导入</button>
							</form>
	                    </div>
	                </div>
	            </div>
			</div>
        	<div class="row" id="startExam">
				<div class="col-xs-4 col-sm-4 col-xs-offset-4 col-sm-offset-4" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>开启考试</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" class="navbar-form">
	                        	<span id="exam_file_msg">尚未上传试卷</span>
								<button id="btn_start"  class="btn btn-info btn-block">开启</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
		</div>
	</body>
	<script  type="text/javascript">
        $(function(){
            $("body").animate({opacity:"1"},1000);
            var code = $("#code").val();
            var efile = $("#efile").val();
            if(code == "undefined" || typeof(code)=="undefined" || code == "" || code == 0){
                $("#choseExam").show()
                $("#uploadExam").hide()
                $("#readExcel").hide()
                $("#startExam").hide()
            }else{
                $("#choseExam").hide()
                $("#uploadExam").show()
                $("#readExcel").show()
                $("#startExam").show()
            }
            checkExamFile(efile)

        });
        function doUpload() {
            var formData = new FormData();
            formData.append("eid", $("#eid").val())
            formData.append('upload', $('#upload')[0].files[0]);
            $.ajax({
                url: '/teacher/uploadExam.do' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
            }).done(function (data) {
                if(data.code == 1){
                    checkExamFile(data.uri)
                    alert("上传文件成功")
                }else{
                    alert("上传文件失败")
                }
            }).fail(function (data) {
                alert("上传文件失败，请联系管理员")
            });
        }

        function doUploadExcel() {
            var formData = new FormData();
            formData.append("eid", $("#eid").val())
            formData.append('file', $('#excel')[0].files[0]);
            $.ajax({
                url: '/teacher/readExcelStudent.do' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
            }).done(function (data) {
                if(data.code == 1){
                    alert("读取文件成功")
                    $("#btn_uploadExcel").attr('disabled',true)
                    $("#studentNum").html(data.count)
                }else{
                    alert("读取文件失败")
                    $("#btn_uploadExcel").attr('disabled',false)
                }
            }).fail(function (data) {
                alert("上传文件失败，请联系管理员")
            });
        }

        function checkExamFile(file) {
			if(file.length != 0){
				$("#exam_file_msg").text('可以开启考试');
                $("#btn_start").attr("disabled",false)
			}else{
                $("#btn_start").attr("disabled",true)
                $("#exam_file_msg").text('尚未上传试卷..');
			}
        }
	</script>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
			function send() {
			    var content = $("#content").val();
				$.post("/teacher/sendInfo.do",{content:content},function (data) {
					if(data.code == 1){
					    alert("发送成功")
					}else{
					    alert("发送失败，请重新发送")
					}
                })
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
					<img src="../../img/inform.png" />
					<h1><i>通知管理</i></h1>
				</div>
			</div>
			<div class="row" >
				<div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-3" >
            		<div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>新增通知消息</h5>
	                    </div>
	                    <div class="ibox-content">
	                        <form role="form" class="form-inline">
	                            <div class="form-group">
	                                <label for="content" class="sr-only">消息</label>
	                                <input type="text" placeholder="通知消息内容" id="content" name="content" class="form-control">
	                            </div>
	                            <br /><br />
	                            <button class="btn btn-info" onclick="send()">添加</button>
	                        </form>
	                    </div>
	                </div>
	            </div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-3">
					 <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>已有通知消息</h5>
	                  	</div>
	                    <div class="ibox-content">
	                        <table class="table table-bordered">
	                            <thead>
	                                <tr>
	                                    <th>通知内容</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                    <td id="tContent">咱么这组最棒</td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	               </div>
				</div>
			</div>
		</div>
	</body>
</html>

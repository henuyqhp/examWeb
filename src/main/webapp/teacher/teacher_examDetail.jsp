<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title></title>
		<link rel="stylesheet" href="../../css/bootstrap.min.css" />
		<script type="text/javascript" src="../../js/jquery-1.11.3.js" ></script>
		<script type="text/javascript" src="../../js/bootstrap.js" ></script>
		<script>
			$(function(){
				$("body").animate({opacity:"1"},1000);
			});
		</script>
		<style>	
			*{margin:0;padding:0;}
			body{opacity: 0;}
			.container .row .contenTitle{margin-top:50px; margin-bottom:30px; text-align: center; color:#261C12;}
			.thumbnail{padding-top:20px;}
			.thumbnail .caption .panel .panel-heading{background-color:#261C12; border:1px solid #261C12;}
			.thumbnail .caption .panel{border:1px solid #261C12;}
			.thumbnail .caption .panel .panel-body ul li{list-style: none;}	
		</style>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 contenTitle">
					<h1><i>考试概括</i></h1>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-xs-offset-3 col-sm-offset-3">
					<div class="thumbnail">
			            <img src="../../img/detail.png"
			             alt="通用的占位符缩略图">
			            <div class="caption">
			                <div class="panel panel-primary">
							    <div class="panel-heading">
							        <h3 class="panel-title">one进行情况</h3>
							    </div>
							    <div class="panel-body">
									<p>考试名称：<span>${pd.ename}</span></p>
							        <p>参加考试的人数：<span>${pd.sum}</span></p>
							        <p>已登录学生数量：<span >${pd.login}</span>，未登录学生数量：<span>${pd.sum - pd.login}</span></p>
							        <p>已登录学生中，提交文件学生数量：<span>${pd.submit}</span>，未提交文件学生数量：<span>${pd.sum - pd.submit}</span></p>
							    </div>
							</div>
			            </div>
			        </div>
				</div>
			</div>
		</div>
	</body>
</html>

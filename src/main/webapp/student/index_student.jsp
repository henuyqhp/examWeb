<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title></title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" />
		<!--<link rel="stylesheet" href="css/style.min862f.css" />-->
		<script type="text/javascript" src="../js/jquery-1.11.3.js" ></script>
		<script type="text/javascript" src="../js/bootstrap.js" ></script>
		<script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
		<style>
			*{margin:0;padding:0;}
			nav{font-weight: bold;}
			html,body{height:100%;}
			body{opacity: 0;}
			.contain{position:relative; height:auto !important; min-height: 100%;}
			.container-fluid{background-color:#261C12;}
			.container-fluid .navbar-header .navbar-brand{color:white; cursor:pointer;}
			.container-fluid .head_right .nav li a{color:white; cursor:pointer;}
			.container-fluid .head_right .nav li a.current{color:aqua;}
			.navbar-right{margin-right: 50px; color:white;}
			.navbar-right a{color:white; text-decoration:none;}
			.navbar-right a img{ margin:16px 50px; margin-right: 3px; width:20px;}
			
			.footer{position:absolute; bottom: 0px; width:100%; height:35px; line-height:35px; text-align: center; color:white; background-color: #261C12;}
		</style>
		<script>
			$(function(){
				$("body").animate({opacity:"1"},2000);
				$(".head_right .nav li a").click(function(){
					$(this).addClass("current");
					$(this).parent().siblings().find("a").removeClass("current");
				});
				$(".container-fluid .navbar-header .navbar-brand").click(function(){
					$("iframe").attr("src","student_firstPage.html");
					$(".head_right .nav li #firstPage").addClass("current").parent().siblings().find("a").removeClass("current");
				});
				$(".head_right .nav li #firstPage").click(function(){
					$("iframe").attr("src","student_firstPage.html");
				});
				$(".head_right .nav li #studentSubmit").click(function(){
					$("iframe").attr("src","student_submitlog.html");
				});
                var goEasy = new GoEasy({
                    appkey: "BS-bf5e8ab6ac6d4a22bc66a9aba9d8a012"
                });
                goEasy.subscribe({
                    channel: "my_channel",
                    onMessage: function (message) {
                        alert("来自老师通知:" + message.content);
                    }
                })
			});
		</script>
	</head>
	<body>
		<div class="contain">
			<nav class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand">上机考试系统</a>
					</div>
					<div class="head_right">
						<ul class="nav navbar-nav">
							<li class=""><a class="current" id="firstPage">首页</a></li>
							<li><a target="" id="studentSubmit">查看提交</a></li>
						</ul>
					</div>
					<div class="navbar-right">
						<span class="loginMessage">欢迎你，student！</span>
						<a href="/student/logout.do"><img src="../img/exit.png" />退出</a>
					</div>
				</div>
			</nav>
			<iframe src="student_firstPage.html" width="100%" height="700px" frameborder="0"></iframe>
			<div class="footer">
				<span>Copyright © 2017henu上机考试管理系统</span>
			</div>
		</div>
	</body>
</html>

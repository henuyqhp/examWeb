<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<script type="text/javascript" src="/js/jquery-1.11.3.js" ></script>
		<script>
			$(function(){
				$("body").animate({opacity:"1"},3000);
				$(".header_nav ul li").click(function(){
					$(this).addClass("current").siblings().removeClass();
					var index=$(this).index();
//					$(".nav_content form").eq(index).fadeIn().siblings().hide();
					$(".nav_content form").css({"top":"20px","opacity":"0"});
					$(".nav_content form").eq(index).show().animate({top:"45px",opacity:"1"},1000).siblings().css({"top":"20px","opacity":"0"}).hide();
				});

            })

		</script>
		<style>
			*{margin:0;padding:0;}
			body{opacity:0; margin-top:90px;}
			.header{margin:0px auto; width:800px; height:140px;}
			.header_log{margin:10px; width:400px; height:120px; float:left;}
			.header_log img{height:120px;}
			.header_log #img1{height:100px; margin:0px 10px 20px 0px;}
			.header_nav{margin:10px; width:356px; height:120px; float:right;}
			.header_nav ul{margin:10px 0px; width:350px; height:100px;}
			.header_nav ul li{
				list-style: none;
				float:left;
				margin-right:10px;
				width:100px;
				height:100px;
				border-radius: 40px;
				line-height:100px;
				font-family:宋体;
				font-size: 20px;
				font-weight: bold;
				text-align: center;
				cursor:pointer;
				overflow: hidden;
			}
			.header_nav ul li.current{
				color:white;
				/*box-shadow:0px 0px 5px 3px darkblue;*/
				background-color: #232323;
				/*background-color: darkgray;*/
				opacity: 0.9;
			}
			.nav_content{
				margin:80px auto;
				padding:20px;
				width:500px;
				height:300px;
				text-align: center;
				position:relative;
			}
			.nav_content span{
				/*font-family:Arial,Helvetica,sans-serif;*/
				font-style:italic;
				font-size:25px;
				font-weight:bold;
				color:#232323;
				/*text-shadow: 1px 1px black;*/
			}
			.nav_content form{position:absolute;}
			.xinxi{
				margin-top:30px;
				width: 500px;
				height: 40px;
				text-align:center;
			}	
			.username {
				height: 40px;
				width: 330px;
				border-radius: 5px;
				border-style: hidden;
				border: 1.5px solid #232323;
				background-color: white;
				padding-left: 8px;
			}		
			.password {
				height: 40px;
				width: 330px;
				border-radius: 5px;
				border: 1.5px solid #232323;
				background-color: white;
				padding-left: 8px;
			}
			.login {
				height: 45px;
				width: 340px;
				border-radius: 5px;
				border-style: hidden;
				background-color: #232323;
				color: white;
				cursor: pointer;
			}
			.hide{
				display:none;
			}
		</style>
	</head>
	<body >
		<div class="header">
			<div class="header_log">
				<img id="img1" src="/img/henu.png" /><img id="img2" src="/img/manage.jpg" />
			</div>
			<div class="header_nav">
				<ul>
					<li class="current">教师登陆</li>
					<li>考生登陆</li>
					<li>管理登陆</li>
				</ul>
			</div>
		</div>
		<div class="nav_content">
			<span>欢迎登陆<span>
			<form action="/teacher/login.do" method="post" >
				<div class="xinxi">
					<input name="username" id="username_teacher" class="username" type="text" placeholder="教师用户名或电子邮箱"/>
				</div>
				<div class="xinxi">
					<input name="password" type="password" id="password_teacher" class="password" placeholder="密码"/>
				</div>
				<div class="xinxi">
					<input name="login" id="login_teacher" class="login" type="submit" value="登录"/>
				</div>
			</form>
			<form action="/student/login.do" method="post" class="hide">
				<div class="xinxi">
					<input name="username" id="username_student" class="username" type="text" placeholder="学号"/>
				</div>
				<div class="xinxi">
					<input name="password" type="password_student" id="a" class="password" placeholder="密码"/>
				</div>
				<div class="xinxi">
					<input name="login" id="login_student" class="login" type="submit" value="登录"/> 
				</div>
			</form>
			<form action="/admin/login.do" method="post" class="hide">
				<div class="xinxi">
					<input name="username" id="username_admin" class="username" type="text" placeholder="管理员用户名或电子邮箱"/>
				</div>
				<div class="xinxi">
					<input name="password" type="password" id="password_admin" class="password" placeholder="密码"/>
				</div>
				<div class="xinxi">
					<input name="login" id="login_admin" class="login" type="submit" value="登录"/> 
				</div>
			</form>
		</div>
	</body>
</html>

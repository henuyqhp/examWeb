<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println(path);
	System.out.println("basepath:"+basePath);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title></title>
		<link rel="stylesheet" href="../../css/bootstrap.min.css" />
		<!--<link rel="stylesheet" href="css/style.min862f.css" />-->
		<!--<script type="text/javascript" src="js/jquery-1.11.3.js" ></script>-->
		<script type="text/javascript" src="../../js/jquery.min.js" ></script>
		<!--<script type="text/javascript" src="js/bootstrap.js" ></script>-->
		<script type="text/javascript" src="../../js/bootstrap.min.js" ></script>
		<style>
			*{margin:0;padding:0;}
			nav{font-weight: bold;}
			html,body{height:100%;}
			body{opacity: 0;}
			.contain{position:relative; height:auto !important; min-height: 100%;}
			.container-fluid{background-color:#261C12;}
			/*.container-fluid .head_right .nav{background-color:#261C12;}
			.container-fluid .navbar-header{background-color:#261C12;}*/
			.container-fluid .navbar-header .navbar-brand{color:white; cursor:pointer;}
			.container-fluid .head_right .nav li.alone a{color:white; cursor:pointer;}
			.container-fluid .head_right .nav li.dropdown a.dropdown-toggle {color:white; cursor:pointer;}
			.container-fluid .head_right .nav li.alone a.current{color:aqua; cursor:pointer;}
			.container-fluid .head_right .nav li.dropdown a.dropdown-toggle.current{color:aqua; cursor:pointer;}
			.dropdown-menu:before {
			    content:"";
			    position:absolute;
			    border: 10px solid #FFFFFF;
			    border-top: 0 solid transparent!important;
			    border-right: 10px solid transparent!important;
			    border-left: 10px solid transparent!important;
			    left:10px;
			    top:-10px;
			}
			.dropdown{
				position:absolute;
				top:1px;
			}
			.navbar-right{margin-right: 50px; color:white;}
			.navbar-right a{color:white; text-decoration:none; cursor:pointer;}
			.navbar-right a img{margin:16px 50px; margin-right: 3px; width:20px;}		
			.footer{position:absolute; bottom: 0px; width:100%; height:35px; line-height:35px; text-align: center; color:white; background-color: #261C12;}
		</style>
		<script>
			$(function(){
				$("body").animate({opacity:"1"},2000);
				$(".head_right .nav li a").click(function(){
					$(this).addClass("current");
					$(this).parent().siblings().find("a").removeClass("current");
				});
				$(".dropdown-toggle").click(function(){
					$(this).css("background-color","#261C12");
				})
				$(".container-fluid .navbar-header .navbar-brand").click(function(){
					$("iframe").attr("src","teacher_firstPage.html");
					$(".head_right .nav li #firstPage").addClass("current").parent().siblings().find("a").removeClass("current");
				});
				$(".head_right .nav li #firstPage").click(function(){
					$("iframe").attr("src","teacher_firstPage.html");
				});
				$(".head_right .nav li #examBefore").click(function(){
//					$("iframe").attr("src","teacher_examBefore.jsp")
                    window.parent.document.getElementById("iframe").contentWindow.location.href = "/teacher/examBefore.do";
				});
				$(".head_right .nav li #examManage").click(function(){
					$("iframe").attr("src","teacher_examManage.jsp");
				});
				$(".head_right .nav li #examAfter").click(function(){
					$("iframe").attr("src","teacher_examAfter.html");
				});
				$(".head_right .nav #exaMdetail").click(function(){
					$("iframe").attr("src","/teacher/examInformation.do");
				});
				$(".head_right .nav #exaMessage").click(function(){
					$("iframe").attr("src","/teacher/exaMessage.do");
				});
				$(".head_right .nav #exaMlock").click(function(){
					$("iframe").attr("src","/teacher/exaMlock.do");
				});
				$(".head_right .nav #exaMinform").click(function(){
					$("iframe").attr("src","/teacher/exaMinform.do");
				});
                function iframeChange(name) {
                    window.parent.document.getElementById("iframe").contentWindow.location.href = "/teacher/"+name+".do";
                }


                function myFunction()
                {
                    alert("ss")
                    return "您确定退出吗？";
                }
			});
		</script>
	</head>
	<body  onbeforeunload="return myFunction()">
		<div class="contain">
			<nav class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand">上机考试系统</a>
					</div>
					<div class="head_right">
						<ul class="nav navbar-nav">
							<li class="alone"><a class="current" id="firstPage">首页</a></li>
							<li class="alone"><a id="examBefore">考前操作</a></li>
							<li class="dropdown">
                                <a class="dropdown-toggle" id="examManage" data-toggle="dropdown">考中管理<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="examManage">
                                    <li id="exaMdetail"><a href="#">考试概括</a>
                                    </li>
                                    <li id="exaMessage"><a href="#">学生信息</a>
                                    </li>
                                    <li id="exaMlock"><a href="#">解除锁定</a>
                                    </li>
                                    <li id="exaMinform"><a href="#">通知管理</a>
                                    </li>
                                </ul>
                          	</li>
							<li class="alone"><a id="examAfter">考后操作</a></li>
						</ul>
					</div>
					<div class="navbar-right">
							<span class="loginMessage">欢迎你，one！</span>
							<a data-toggle="modal" data-target="#myModal"><img src="../../img/alter.png" />修改口令</a>
							<a href="/teacher/logout.do"><img src="../../img/exit.png" />退出</a>
					</div>
				</div>
			</nav>
			<%--<iframe name="abc" src="teacher_firstPage.html" width="100%" height="700px" frameborder="0"></iframe>--%>
			<iframe id="iframe" name="abc" src="<c:choose><c:when test="${iframe==null}">teacher_firstPage.html</c:when>
			<c:otherwise>${iframe}</c:otherwise></c:choose>" width="100%" height="700px" frameborder="0"></iframe>
			<div class="footer">
				<span>Copyright © 2017henu上机考试A管理系统</span>
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h3 class="modal-title" id="myModalLabel">修改口令</h3>
					</div>
					<div class="modal-body">
						<form  role="form" action="#">
			                <div class="form-group">
			                    <input type="text" class="form-control" placeholder="原口令" required="">
			                </div>
			                <div class="form-group">
			                    <input type="text" class="form-control" placeholder="新口令" required="">
			                </div>
			                <div class="form-group">
			                    <input type="text" class="form-control" placeholder="重输新口令" required="">
			                </div>
			                <button type="submit"  class="btn btn-info block full-width m-b">修改</button>
			            </form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

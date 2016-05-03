<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default nav-pills navbar-fixed-top"
	role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="${ctx}/user/user_goHome.action">随便美餐</a>
		<span class="sr-only">${currentPage}</span>
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse" />
         <span class="sr-only">切换导航</span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
	</div>
	<div class="collapse navbar-collapse" id="example-navbar-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><img style="display: inline;" height="48px" width="48px" alt="头像加载失败"
				src="${ctx}/image/head/${loginContext.headPath}"></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"> ${loginContext.nickName } <b
				class="caret"></b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<ul class="dropdown-menu">
				<li><a href="${ctx}/user/user_goSetting.action">账户设置</a></li>
				<li class="divider"></li>
				<li><a href="${ctx}/user/user_logout.action">退出登录</a></li>
			</ul></li>
		</ul>
		<ul class="nav navbar-nav ">
			<li id="home"><a href="${ctx}/user/user_goHome.action">首页</a></li>
			<li id="shopping"><a href="${ctx}/user/shopping_car.action">购物车</a></li>
			<li id="order"><a href="${ctx}/user/order_info.action">我的订单</a></li>
			<li id="person"><a href="${ctx}/user/person_info.action">个人中心</a></li>
		</ul>
	</div>
</nav>
<script>
	$(function(){
		if($(".sr-only").html()=="home"){
			$("#home").addClass("active");
		}
		if($(".sr-only").html()=="shopping"){
			$("#shopping").addClass("active");
		}
		if($(".sr-only").html()=="order"){
			$("#order").addClass("active");
		}
		if($(".sr-only").html()=="person"){
			$("#person").addClass("active");
		}
	});
</script>
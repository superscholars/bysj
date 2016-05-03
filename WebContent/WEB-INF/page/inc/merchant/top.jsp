<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default nav-pills navbar-fixed-top"
	role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="${ctx}/merchant/merchant_goHome.action">随便美餐-商户</a>
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
				<li><a href="${ctx}/merchant/merchant_goSetting.action">账户设置</a></li>
				<li class="divider"></li>
				<li><a href="${ctx}/merchant/merchant_logout.action">退出登录</a></li>
			</ul></li>
		</ul>
		<ul class="nav navbar-nav ">
			<li id="order"><a href="${ctx}/merchant/order_openPage.action">订单信息</a></li>
			<li id="merchant"><a href="${ctx}/merchant/merchantInfo_openPage.action">商铺信息</a></li>
			<li id="category"><a href="${ctx}/merchant/goodsCategory_openPage.action">分类管理</a></li>
			<li id="goods"><a href="${ctx}/merchant/goods_openPage.action">菜品管理</a></li>
			<li id="strategy"><a href="${ctx}/merchant/strategy_openPage.action">优惠策略</a></li>
			<li id="stats"><a href="#">数据统计</a> </li>
		</ul>
	</div>
</nav>
<script>
	$(function(){
		if($(".sr-only").html()=="order"){
			$("#order").addClass("active");
		}
		if($(".sr-only").html()=="merchant"){
			$("#merchant").addClass("active");
		}
		if($(".sr-only").html()=="category"){
			$("#category").addClass("active");
		}
		if($(".sr-only").html()=="goods"){
			$("#goods").addClass("active");
		}
		if($(".sr-only").html()=="strategy"){
			$("#strategy").addClass("active");
		}
		if($(".sr-only").html()=="stats"){
			$("#stats").addClass("active");
		}
	});
</script>
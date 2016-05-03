<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default nav-pills navbar-fixed-top"
	role="navigation">
	<div class="navbar-header">
		<a class="navbar-brand" href="${ctx}/admin/admin_goHome.action">随便美餐-管理后台</a>
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
				<li><a href="${ctx}/admin/admin_goSetting.action">账户设置</a></li>
				<li class="divider"></li>
				<li><a href="${ctx}/admin/admin_logout.action">退出登录</a></li>
			</ul></li>
		</ul>
		<ul class="nav navbar-nav ">
			<li id="order"><a href="${ctx}/admin/admin_goHome.action">订单中心</a></li>
			<li id="check"><a href="${ctx}/admin/operate_checkMerchant.action">审核商户</a></li>
			<li id="complaint"><a href="${ctx}/admin/operate_complaint.action">投诉中心</a></li>
			<li id="order"><a href="${ctx}/admin/admin_goHome.action">禁闭商户</a></li>
			<li id="order"><a href="${ctx}/admin/admin_goHome.action">屏蔽用户</a></li>
			<c:if test="${loginContext.adminFlag eq 1}">
				<li id="order"><a href="${ctx}/admin/admin_goHome.action">开通管理账户</a></li><!-- 仅限超管 -->
			</c:if>
		</ul>
	</div>
</nav>
<script>
	$(function(){
		if($(".sr-only").html()=="home"){
			$("#home").addClass("active");
		}
		if($(".sr-only").html()=="check"){
			$("#check").addClass("active");
		}
		if($(".sr-only").html()=="complaint"){
			$("#complaint").addClass("active");
		}
		if($(".sr-only").html()=="person"){
			$("#person").addClass("active");
		}
	});
</script>
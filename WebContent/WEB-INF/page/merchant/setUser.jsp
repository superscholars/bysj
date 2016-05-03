<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<%@ include file="../inc/merchant/top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE lang="zh-cmn">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.2, user-scalable=1">
<title>随便美餐-商户</title>
</head>
<body>
	<div class="container">
	<div class="row">
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
	</div>
	<div class="row">
		<h1> 修改账户信息  </h1>
	</div>
		<div class="form">
			<h2>修改头像</h2>
			<form name="settingHead" action="${ctx}/merchant/merchant_settingHead.action" enctype="multipart/form-data"
				method="post" role="form">
				<div class="row">
					<div class="form-group col-md-5">
						<label class="control-label" for="head">请选择头像</label>
						<input class="form-control" type="file" id="head" name="head"/>
					</div>
				</div>
				
				<div class="form-group ">
					<input class=" btn btn-primary" type="submit" value="确定">
				</div>
				
			</form>
			<h2>修改密码</h2>
			<form class="form" name="settingPassword" action="${ctx}/merchant/merchant_settingPassword.action" method="post" role="form">
				<div class="row">
					<div class="form-group col-md-5">
						<label class="control-label" for="oldPassword">请输入旧密码</label>
						<input class="form-control" id="oldPassword" name="oldPassword" type="password" placeholder="请输入旧密码"><br />
					</div>
				</div>
				
				<div class="row">
					<div class="form-group col-md-5">
						<label class="control-label" for="newPassword">请输入新密码</label>
						<input class="form-control" id="newPassword" name="newPassword" type="password" placeholder="请输入新密码"><br />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-5">
						<label class="control-label" for="newPassword2">请确认新密码</label>
						<input class="form-control" id="newPassword2" name="newPassword2" type="password" placeholder="请确认新密码"><br />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-5">
						<input class="btn btn-primary" type="submit" value="确定">
						<a class="btn btn-default" href="#" onClick=" javascript :history.back(-1);">返回</a>				
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>
</body>
</html>

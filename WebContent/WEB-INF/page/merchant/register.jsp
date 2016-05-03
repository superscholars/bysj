<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE lang="zh-cmn">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.2, user-scalable=1">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<title>随便美餐-商户</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container">
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		
		<h1> 注册商户登陆账号  </h1>
		
		<div>
			<form class="form" name="register" action="${ctx}/merchant/merchant_doRegist.action" 
				method="post" role="form">
				<div class="row">
					<div class="form_group col-md-6">
						<label  class="control-label" for="loginName">请输入登陆名</label>
						<input  class="form-control" id="loginName" name="loginName" type="text" placeholder="请输入用户名"/><br />
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form_group col-md-6">
						<label class="control-label" for="nickName">请输入昵称</label>
						<input class="form-control" id="nickName" name="nickName" type="text" placeholder="请输入昵称"/>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form_group col-md-6">
						<label class="control-label" for="password">请输入密码</label>
						<input class="form-control" id="password" name="password" type="password" placeholder="请输入密码">
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form_group col-md-6">
						<label class="control-label	" for="password2">请确认密码</label>
						<input class="form-control" id="password2" name="password2" type="password" placeholder="请确认密码">
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form_group col-md-6">
						<input class="btn btn-primary form-control" type="submit" value="注册">
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form_group col-md-6">
						<a class="btn btn-default form-control" href="#" onClick=" javascript :history.back(-1);">返回</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>

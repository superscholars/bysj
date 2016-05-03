<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE lang="zh-cmn">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.2, user-scalable=1">
<title>随便美餐-管理员</title>
<style type="text/css">
.form{
	margin:0 auto;
}

</style>
</head>
<body>
	<div class="container" >
		<div class="row">
			<c:if test="${!empty err}">
				<div class="alert alert-danger">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
						&times; </a> ${err}
				</div>
			</c:if>
		</div>
		
		<span> 管理后台登陆</span>
		
		<div class="form">
			<form name="login" action="${ctx}/admin/admin_doLogin.action" method="post">
				<div class="row">
					<div class="form_group col-md-6">
						<input class="form-control" name="loginName" type="text" placeholder="请输入登陆名"/> 
					</div>
				</div>
				
				<div class="row">
					<div class="form_group col-md-6">
						<input class="form-control" name="password" type="password"> 
					</div>
				</div>
				
				<div class="row">
					<div class="form_group col-md-6">
						<input class="form-control btn-primary" class="btn btn-default" type="submit" value="登陆">
					</div>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>
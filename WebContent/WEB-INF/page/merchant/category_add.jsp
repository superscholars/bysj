<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../base.jsp"%>
<%@ include file="../inc/merchant/top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>随便美餐商户</title>
</head>
<body>
	<div class="container">
	<div>
		<ol class="breadcrumb">
  			<li><a href="#">随便美餐</a></li>
			<li><a href="#">商户管理</a></li>
			<li><a href="${ctx}/merchant/goodsCategory_openPage">商品分类管理</a></li>
			<li class="active">添加商品分类</li>
		</ol>
	</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		<form name="register" action="${ctx}/merchant/goodsCategory_doAdd.action"
			enctype="multipart/form-data" method="post" role="form">
			<div class="form-group ">
				<label class="control-label " for="sort">分类权重</label> 
				<input
					class="form-control" id="sort" name="sort" type="number" />
			</div>
			<div class="form-group">
				<label class="control-label" for="categoryName">分类名称</label>
				<input
					class="form-control" id="categoryName" name="categoryName" type="text" />
			</div>
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<input class="btn btn-primary" type="submit" value="确定">
					<a class="btn btn-default" href="${ctx}/merchant/goodsCategory_openPage" >返回</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>
</body>
</html>
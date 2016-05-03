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
			<li><a href="${ctx}/merchant/goods_openPage.action">商品管理</a></li>
			<li class="active">修改商品</li>
		</ol>
	</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		<form name="udpateGoods" action="${ctx}/merchant/goods_doUpdate.action"
			enctype="multipart/form-data" method="post" role="form">
			<input type="hidden" name = "id" value="${goods.id}"/>
			<input type="hidden" name = "monthCount" value="${goods.monthCount}"/>
			<input type="hidden" name = "buzz" value="${goods.buzz}"/>
			<input type="hidden" name = "goodsPic" value="${goods.goodsPic}"/>
			<div class="row">
				<div class="col-xs-6 col-md-6">
					<img height="48px" width="48px" alt="头像加载失败"
						src="${ctx}/image/goods/${goods.goodsPic}">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="name">商品名称</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="name" name="name" type="text"
						value="${goods.name}"/>
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label" for="pic">更换图片</label>
					<input
						class="form-control col-xs-3 col-md-3" id="pic" name="pic" type="file" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					 <label class="control-label " for="price">价格</label>
					 <div class="input-group ">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="price" name="price" type="text" value="${goods.price}">
				      </div>
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="goodsDesc">介绍</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="goodsDesc" name="goodsDesc"
						type="text" value="${goods.goodsDesc}" /><br />
				</div>
			</div>

			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="categoryId">所属分类</label> 
					<select class="form-control" id="categoryId" name="categoryId">
			            <c:forEach items="${categoryList}" var="category">
			            	<option value="${category.id}" <c:if test="${goods.categoryId eq category.id}">selected</c:if>>${category.categoryName}</option>
			            </c:forEach>
			        </select>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<input class="btn btn-primary" type="submit" value="确定">
					<a class="btn btn-default" href="${ctx}/merchant/goods_openPage.action" >返回</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>
</body>
</html>
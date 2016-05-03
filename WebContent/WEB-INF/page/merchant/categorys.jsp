<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../base.jsp" %>
<%@ include file="../inc/merchant/top.jsp" %>
<!DOCTYPE html>
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
			<li class="active">商品分类管理</li>
		</ol>
	</div>
	<c:if test="${!empty err}">
		<div class="alert alert-danger">
			<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
				&times; </a> ${err}
		</div>
	</c:if>
	<c:if test="${!empty success}">
		<div class="alert alert-success">
			<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
				&times; </a> ${success}
		</div>
	</c:if>
		<a class="btn btn-primary" href="${ctx}/merchant/goodsCategory_addCategoryPage">添加分类</a><br />
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>排序权重</th>
		         <th>分类名称</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty categorys}">
			      <tr>
			         <td class="text-center" colspan="3"><b>目前没有分类</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty categorys}">
			      <c:forEach var="category"   items="${categorys}">
	                  <tr>
				         <td>${category.sort}</td>
				         <td>${category.categoryName}</td>
				         <td>
				         	<a class="btn btn-primary btn-sm" href="${ctx}/merchant/goodsCategory_editPage.action?id=${category.id}">编辑</a>
				         	<a class="btn btn-danger btn-sm" href="${ctx}/merchant/goodsCategory_remove.action?id=${category.id}">删除</a>
				         </td>
				      </tr>
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
	</div>
</body>
</html>
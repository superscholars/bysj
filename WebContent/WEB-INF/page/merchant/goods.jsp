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
				<li class="active">商品管理</li>
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
		
		<c:choose>
			<%-- 判断是否有分类  --%>
			<c:when test="${categoryflag}">
				<a class="btn btn-primary" href="${ctx}/merchant/goods_addPage">添加商品</a><br />
				<table class="table table-responsive table-striped table-hover">
				   <thead>
				      <tr>
				         <th>商品图片</th>
				         <th>商品名称</th>
				         <th>商品价格</th>
				         <th>商品描述</th>
				         <th>所属分类</th>
				         <th>销售量</th>
				         <th>点赞量</th>
				         <th>操作</th>
				      </tr>
				   </thead>
				   <tbody>
				   	  <c:if test="${empty goodsList}">
					      <tr>
					         <td class="text-center" rowspan="3" colspan="8"><b>目前没有任何商品</b></td>
					      </tr>
				      </c:if>
				      <c:if test="${!empty goodsList}">
					      <c:forEach var="goods"   items="${goodsList}">
			                  <tr>
						         <td>
						         	<img style="display: inline;" height="48px" width="48px" alt="头像加载失败"
											src="${ctx}/image/goods/${goods.goodsPic}">
								 </td>
						         <td>${goods.name}</td>
						         <td>${goods.price}</td>
						         <td>${goods.goodsDesc}</td>
						         <td>${goods.category}</td>
						         <td>${goods.monthCount}</td>
						         <td>${goods.buzz}</td>
						         <td>
						         	<a class="btn btn-primary btn-sm" href="${ctx}/merchant/goods_editPage.action?id=${goods.id}">编辑</a>
						         	<a class="btn btn-danger btn-sm" href="${ctx}/merchant/goods_remove.action?id=${goods.id}">删除</a>
						         </td>
						      </tr>
			              </c:forEach>
				      </c:if>
				   </tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h1 class="text-center">请创建分类</h1>
			</c:otherwise>
		</c:choose>
	
		
	
	</div>
</body>
</html>
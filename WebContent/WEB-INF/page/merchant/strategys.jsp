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
		<div class="row">
			<div>
				<ol class="breadcrumb">
		  			<li><a href="#">随便美餐</a></li>
					<li><a href="#">商户管理</a></li>
					<li class="active">优惠策略管理</li>
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
		</div>
		<div class="row">
			<div>
				<a class="btn btn-primary" href="${ctx}/merchant/strategy_addPage.action">添加优惠策略</a><br />
			</div>
		</div>
		<br />
		<div class="row">
			<%-- 新用户优惠 --%>
			<div class="panel panel-info ">
			   <div class="panel-heading ">
			      <h3 class="panel-title">新用户优惠</h3>
			   </div>
			   <div class="panel-body">
					   优惠信息
			   </div>
			   <c:choose>
			  	 <c:when test="${empty xinyonghu}">
			  	 	<table class="table"><tr><td>无新用户优惠</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table"><tr>
			  	 	<td>新用户优惠${xinyonghu.balancePrice}元</td>
			  	 	<td>
			  	 		<a class="btn btn-primary" href="${ctx}/merchant/strategy_editPage.action?id=${xinyonghu.id}">编辑</a>
			  	 		<a class="btn btn-danger" href="${ctx}/merchant/strategy_remove.action?id=${xinyonghu.id}">删除</a>
			  	 	</td>
			  	 	</tr></table>
			  	 </c:otherwise>
			   </c:choose>
			</div>
		</div>
		
		<div class="row">
			<%-- 满减优惠 --%>
			<div class="panel panel-info">
			   <div class="panel-heading">
			      <h3 class="panel-title">满减优惠</h3>
			   </div>
			   <div class="panel-body">
			    	优惠信息
			   </div>
			    <c:choose>
			  	 <c:when test="${empty manjian}">
			  	 	<table class="table"><tr><td>无满减优惠</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table table-bordered table-hover">
				      <tr><th>前提额度</th><th>减免额度</th><th>操作</th></tr>
				      <c:forEach items="${manjian}" var="item">
				      	<tr>
				      		<td>${item.premisePrice}</td>
				      		<td>${item.balancePrice}</td>
				      		<td>
					  	 		<a class="btn btn-primary" href="${ctx}/merchant/strategy_editPage.action?id=${item.id}">编辑</a>
			  	 				<a class="btn btn-danger" href="${ctx}/merchant/strategy_remove.action?id=${item.id}">删除</a>
				      		</td>
				      	</tr>
				      </c:forEach>
				   </table>
			  	 </c:otherwise>
			   </c:choose>
			</div>
		</div>
		
		<div class="row">
			<%-- 折扣优惠 --%>
			<div class="panel panel-info">
			   <div class="panel-heading">
			      <h3 class="panel-title">折扣优惠</h3>
			   </div>
			   <div class="panel-body">
			    	优惠信息
			   </div>
			    <c:choose>
			  	 <c:when test="${empty zhekou}">
			  	 	<table class="table"><tr><td>无折扣优惠</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table table-bordered table-hover">
				      <tr><th>前提额度</th><th>折扣</th><th>操作</th></tr>
				      <c:forEach items="${zhekou}" var="item">
				      	<tr>
					      	<td>${item.premisePrice}</td>
					      	<td>${item.discount}</td>
					      	<td>
					  	 		<a class="btn btn-primary" href="${ctx}/merchant/strategy_editPage.action?id=${item.id}">编辑</a>
			  	 				<a class="btn btn-danger" href="${ctx}/merchant/strategy_remove.action?id=${item.id}">删除</a>
					      	</td>
				      	</tr>
				      </c:forEach>
				   </table>
			  	 </c:otherwise>
			   </c:choose>
			   
			</div>
			
		</div>
	</div>
</body>
</html>
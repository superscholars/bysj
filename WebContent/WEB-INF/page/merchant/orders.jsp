<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<%@ include file="../base.jsp" %>
<%@ include file="../inc/merchant/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <title>随便美餐商户</title>
</head>
<body>
	<div class="container">
	<%-- 面包屑导航及提示 --%>
		<div class="row">
			<div>
				<ol class="breadcrumb">
		  			<li><a href="#">随便美餐</a></li>
					<li><a href="#">商户管理</a></li>
					<li class="active">订单管理</li>
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
		
		<%-- 处理中订单 --%>
		<div class="row">
			<div class="panel panel-primary">
			   <div class="panel-heading">
			      <h3 class="panel-title">处理中订单</h3>
			   </div>
			   <div class="panel-body">
			    	请处理
			   </div>
			    <c:choose>
			  	 <c:when test="${empty doing}">
			  	 	<table class="table"><tr><td>无处理中订单</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table table-bordered table-hover">
				      <tr><th>下单时间</th><th>订单号</th><th>配送地址</th><th>商品&数量</th><th>食用方式</th><th>状态</th><th>操作</th></tr>
				      <c:forEach items="${doing}" var="item">
				      	<tr>
				      		<td><fmt:formatDate value="${item.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				      		<td>${item.order.orderNumber}</td>
				      		<td>${item.order.userAddr}</td>
				      		<td>
								<c:forEach items="${item.goodsInfoList}" var="goodsInfo">
									${goodsInfo.goodsName} x ${goodsInfo.goodsCount} <br>
								</c:forEach>
							</td>
							<td>
								<c:if test="${item.order.eatType eq 1}">外卖</c:if><c:if test="${item.order.eatType eq 2}">堂吃</c:if>
							</td>
							<td>
								<c:if test="${item.order.status eq 10}">待接单</c:if>
								<c:if test="${item.order.status eq 20}">已接单，准备中。</c:if>
								<c:if test="${item.order.status eq 30}">已备好</c:if>
								<c:if test="${item.order.status eq 40}">在路上</c:if>
								<c:if test="${item.order.status eq 50}">完成订单</c:if>
								<c:if test="${item.order.status eq 60}">取消</c:if>
							</td>
				      		<td>
				      			<c:if test="${item.order.status eq 20}">
						  	 		<a class="btn btn-danger" href="${ctx}/merchant/order_readyOrder.action?id=${item.order.id}">准备好订单</a>
				      			</c:if>
				      			<c:if test="${item.order.status eq 30 }">
				      				<c:if test="${item.order.eatType eq 1}">
						  	 			<a class="btn btn-danger" href="${ctx}/merchant/order_sendOrder.action?id=${item.order.id}">送出外卖</a>
				      				</c:if>
				      			</c:if>
					  	 		<a class="btn btn-info" href="${ctx}/merchant/order_orderDetail.action?id=${item.order.id}">订单详情</a>
				      		</td>
				      	</tr>
				      </c:forEach>
				   </table>
			  	 </c:otherwise>
			   </c:choose>
			</div>
		</div>
	
		<%-- 待接受订单 --%>
		<div class="row">
			<div class="panel panel-warning">
			   <div class="panel-heading">
			      <h3 class="panel-title">待接收订单</h3>
			   </div>
			   <div class="panel-body">
			    	请接收
			   </div>
			    <c:choose>
			  	 <c:when test="${empty waitting}">
			  	 	<table class="table"><tr><td>无待接收中订单</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table table-bordered table-hover">
				      <tr><th>下单时间</th><th>订单号</th><th>配送地址</th><th>商品&数量</th><th>食用方式</th><th>状态</th><th>操作</th></tr>
				      <c:forEach items="${waitting}" var="item">
				      	<tr>
				      		<td><fmt:formatDate value="${item.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				      		<td>${item.order.orderNumber}</td>
				      		<td>${item.order.userAddr}</td>
				      		<td>
								<c:forEach items="${item.goodsInfoList}" var="goodsInfo">
									${goodsInfo.goodsName} x ${goodsInfo.goodsCount} <br>
								</c:forEach>
							</td>
							<td>
								<c:if test="${item.order.eatType eq 1}">外卖</c:if><c:if test="${item.order.eatType eq 2}">堂吃</c:if>
							</td>
							<td>
								<c:if test="${item.order.status eq 10}">待接单</c:if>
								<c:if test="${item.order.status eq 20}">已接单，准备中。</c:if>
								<c:if test="${item.order.status eq 30}">已备好</c:if>
								<c:if test="${item.order.status eq 40}">在路上</c:if>
								<c:if test="${item.order.status eq 50}">完成订单</c:if>
								<c:if test="${item.order.status eq 60}">取消</c:if>
							</td>
				      		<td>
					  	 		<a class="btn btn-danger" href="${ctx}/merchant/order_receiveOrder.action?id=${item.order.id}">接收订单</a>
					  	 		<a class="btn btn-info" href="${ctx}/merchant/order_orderDetail.action?id=${item.order.id}">订单详情</a>
				      		</td>
				      	</tr>
				      </c:forEach>
				   </table>
			  	 </c:otherwise>
			   </c:choose>
			</div>
		</div>
		
		<%-- 完成及关闭 --%>
		<div class="row">
			<div class="panel panel-info">
			   <div class="panel-heading">
			      <h3 class="panel-title">完成及关闭订单</h3>
			   </div>
			    <c:choose>
			  	 <c:when test="${empty finish}">
			  	 	<table class="table"><tr><td>无完成及关闭订单</td></tr></table>
			  	 </c:when>
			  	 <c:otherwise>
			  	 	<table class="table table-bordered table-hover">
				      <tr><th>下单时间</th><th>订单号</th><th>配送地址</th><th>商品&数量</th><th>食用方式</th><th>状态</th><th>操作</th></tr>
				      <c:forEach items="${finish}" var="item">
				      	<tr>
				      		<td><fmt:formatDate value="${item.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				      		<td>${item.order.orderNumber}</td>
				      		<td>${item.order.userAddr}</td>
				      		<td>
								<c:forEach items="${item.goodsInfoList}" var="goodsInfo">
									${goodsInfo.goodsName} x ${goodsInfo.goodsCount} <br>
								</c:forEach>
							</td>
							<td>
								<c:if test="${item.order.eatType eq 1}">外卖</c:if><c:if test="${item.order.eatType eq 2}">堂吃</c:if>
							</td>
							<td>
								<c:if test="${item.order.status eq 10}">待接单</c:if>
								<c:if test="${item.order.status eq 20}">已接单，准备中。</c:if>
								<c:if test="${item.order.status eq 30}">已备好</c:if>
								<c:if test="${item.order.status eq 40}">在路上</c:if>
								<c:if test="${item.order.status eq 50}">完成订单</c:if>
								<c:if test="${item.order.status eq 60}">取消</c:if>
							</td>
				      		<td>
					  	 		<a class="btn btn-info" href="${ctx}/merchant/order_orderDetail.action?id=${item.order.id}">订单详情</a>
				      		</td>
				      	</tr>
				      </c:forEach>
				   </table>
			  	 </c:otherwise>
			   </c:choose>
			</div>
		</div>	
	
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>

</body>
</html>
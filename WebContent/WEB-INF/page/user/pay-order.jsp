<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>随便美餐</title>
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
		<div class="container">
		<div class="row">
			<c:if test="${!empty err}">
				<div class="alert alert-danger">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
						&times; </a> ${err}
				</div>
			</c:if>
		</div>
			<div style="padding: 20px 100px;">
			<form action="${ctx}/user/order_createOrder.action" method="post" role="from">
				<input name="orderId" type="hidden" value="${order.id}">
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">订单信息</h3>
				   </div>
				   <table class="table  table-striped .table-hover">
				      <tr><td>订单号码 </td><td>${order.orderNumber}</td></tr>
				      <tr><td>订单时间</td><td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
				   </table>
				</div>
				
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">配送信息</h3>
				   </div>
				   <table class="table table-striped .table-hover">
				      <tr><td>预计配送时间</td><td>${order.deliveryTime}分钟</td></tr>
				      <tr><td>配送地址</td><td>${order.userAddr}</td></tr>
				   </table>
				</div>
			
			
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">确认订单</h3>
				   </div>
				   <div class="panel-body">
				      ${order.merchantName}
				   </div>
				   <table class="table table-striped .table-hover">
				   	  <c:forEach items="${goodsInfos}" var="goodsInfo">
					      <tr class="success"><td >${goodsInfo.goodsName}</td><td>x ${goodsInfo.goodsCount}</td><td>￥${goodsInfo.goodsPrice}</td></tr>
				   	  </c:forEach>
				   	  
				      <tr class="warning"><td>商品总费用</td><td>&nbsp;</td><td>&nbsp;￥${priceInfo.amount}</td></tr>
				      <tr class="warning"><td>配送费</td><td>&nbsp;</td><td>+&nbsp;￥${priceInfo.deliveryPrice}</td></tr>
				      <tr class="warning"><td>餐具费</td><td>&nbsp;</td><td>+&nbsp;￥${priceInfo.boxPrice}</td></tr>
				      <tr class="warning"><td>优惠费用</td><td>&nbsp;</td><td>-&nbsp;￥${priceInfo.balance}</td></tr>
				   </table>
				   <div class="panel-footer">
						支付费用：￥${priceInfo.payPrice}
				   </div>
				</div>
				
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">方式选择</h3>
				   </div>
				   <table class="table table-striped .table-hover">
				      <tr><td>食用方式</td><td>
						<div class="radio col-md-6">
						   <label>
							  <input type="radio" name="eatType" id="eatType1" 
								 value="1" checked> 外卖
						   </label>
						</div>
						</td>
						<td>
						<div class="radio col-md-6">
						   <label>
							  <input type="radio" name="eatType" id="eatType2" 
								 value="2"> 堂吃
						   </label>
						</div>
					  </td></tr>
					  
					  <tr><td>支付方式</td><td>
							<div class="radio col-md-6">
						   <label>
							  <input type="radio" name="payType" id="payType1" 
								 value="1" checked> 在线支付
						   </label>
						</div>
						</td>
						<c:if test="${codFlag == 1}">
							<td>
							<div class="radio col-md-6">
							   <label>
								  <input type="radio" name="payType" id="payType2" 
									 value="2"> 货到付款
							   </label>
							</div>
						  	</td>
						</c:if>
						<c:if test="${codFlag == 2}">
							<td>&nbsp;</td>
						</c:if>
					  </tr>
				   </table>
				</div>
				<div class="row">
					<div>
						<div class="col-md-2 col-md-push-8">
							<input class="form-control col-md-3 btn btn-primary" type="submit" value="下单">
						</div>
						
						<div class="col-md-2 col-md-push-8">
							<a class="form-control  btn btn-danger" href="${ctx}/user/shopping_car.action">返回购物车</a>
						</div>
					</div>
				</div>
				</form>
			</div>
			
		</div>
		<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

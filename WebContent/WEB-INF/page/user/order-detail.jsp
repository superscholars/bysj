<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
		<div class="container">
			<div style="padding: 20px 100px;">
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">订单详情</h3>
				   </div>
				   <div class="panel-body">
				      	${detailVo.order.merchantName}
				   </div>
				   <table class="table table-striped .table-hover">
				   	  <c:forEach items="${detailVo.goodsInfoList}" var="goods">
					      <tr><td>${goods.goodsName}</td><td>x ${goods.goodsCount}</td><td>￥${goods.goodsPrice}</td></tr>
				   	  </c:forEach>
				      <tr><td>商品总费用</td><td>&nbsp;</td><td>￥${detailVo.priceInfo.amount}</td></tr>
				      <tr><td>配送费</td><td>&nbsp;</td><td>￥${detailVo.priceInfo.deliveryPrice}</td></tr>
				      <tr><td>餐盒费</td><td>&nbsp;</td><td>￥${detailVo.priceInfo.boxPrice}</td></tr>
				      <tr><td>优惠费用</td><td>&nbsp;</td><td>￥${detailVo.priceInfo.balance}</td></tr>
				       
				   </table>
				   <div class="panel-footer">
							总计：￥${detailVo.priceInfo.payPrice}
				   </div>
				</div>
				
				
				
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">配送信息</h3>
				   </div>
				   <table class="table table-striped .table-hover">
				      <tr><td>预计配送时间</td><td>${detailVo.order.deliveryTime}</td></tr>
				      <tr><td>配送地址</td><td>${detailVo.order.userAddr}</td></tr>
				   </table>
				</div>
				
				
				
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">订单信息</h3>
				   </div>
				   <table class="table  table-striped .table-hover">
				      <tr><td>订单号码 </td><td>${detailVo.order.orderNumber}</td></tr>
				      <tr><td>订单时间</td><td><fmt:formatDate value="${detailVo.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td></tr>
				      <tr><td>食用方式</td><td><c:if test="${detailVo.order.eatType eq 1}">外卖</c:if><c:if test="${detailVo.order.eatType eq 2}">堂吃</c:if></td></tr>
				      <tr><td>支付方式</td><td><c:if test="${detailVo.order.payType eq 1}">在线支付</c:if><c:if test="${detailVo.order.payType eq 2}">货到付款</c:if></td></tr>
				   </table>
				</div>
				
				<div class="panel panel-primary">
				   <div class="panel-heading">
				      <h3 class="panel-title">订单进度</h3>
				   </div>
				   <table class="table  table-striped .table-hover">
				   	  <c:forEach items="${detailVo.orderProcessList}" var="orderProcess">
					      <tr><td><fmt:formatDate value="${orderProcess.processTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td><td>${orderProcess.processStatus}</td></tr>
				   	  </c:forEach>
				   </table>
				</div>
				
			</div>
			
			<div class="row">
				<div>
					<div class="col-md-1 col-md-push-9">
						<a class="form-control  btn btn-default" href="${ctx}/user/order_info.action">返回</a>
					</div>
				</div>
			</div>
			
		</div>
		<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

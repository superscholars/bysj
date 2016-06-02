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
		<link rel="stylesheet" href="${ctx}/css/order-center.css" />
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
		<div class="container">
			<c:if test="${!empty err}">
				<div class="alert alert-danger">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
						&times; </a> ${err}
				</div>
			</c:if>
			<span class="shop-title">订单中心</span>
			<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />

			<div class="order-list">
				<c:choose>
					<c:when test="${empty orderVoList}">
						您还未下订单。
					</c:when>
					<c:otherwise>
						<c:forEach items="${orderVoList}" var="orderVo" varStatus="vs">
							<div class="order-item">
								<div class="order-info col-md-7">
									<a href="${ctx}/user/user_merchant.action?id=${orderVo.order.merchantId}">
										<div class="merchant-name">
											<span>${orderVo.order.merchantName}</span>
										</div>
									</a>
									<div class="merchant-body">
										<a href="${ctx}/user/user_merchant.action?id=${orderVo.order.merchantId}">
											<div class="merchant-logo col-md-5">
												<img class="pic" src="${ctx}/image/logo/${orderVo.logoAddr}" alt="adsf"/>
											</div>
										</a>
										<div class="merchant-info col-md-7">
											<div class="order-price">
												<span style="color: red;font-size: 20px;">消费:￥${orderVo.priceInfo.payPrice}</span>
											</div>
											<div class="order-time">
												下单时间：<fmt:formatDate value="${orderVo.order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</div>
											<div class="pay-type">
												支付方式：<c:if test="${orderVo.order.payType == 1}">在线支付</c:if><c:if test="${orderVo.order.payType == 2}">货到付款</c:if>
											</div>
											<div class="user-address">
												收货信息：${orderVo.order.userAddr}
											</div>
										</div>
									</div>
								</div>
								
								<div class="order-process col-md-5">
									<div class="process-detail">
										<c:forEach items="${orderVo.processList}" var="process">
											<c:if test="${process.orderStatus ne 60}">
												<li class="list-group-item"><span style="color:green" class="glyphicon glyphicon-ok ">&nbsp;<fmt:formatDate value="${process.processTime}" pattern="yyyy-MM-dd HH:mm:ss"/>${process.processDesc}</li>
											</c:if>
											<c:if test="${process.orderStatus eq 60}">
												<li class="list-group-item"><span style="color:#600000" class="glyphicon glyphicon-remove ">&nbsp;<fmt:formatDate value="${process.processTime}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;${process.processDesc}</li>
											</c:if>
										</c:forEach>
									</div>
									<div class="process-btn">
										<c:if test="${orderVo.order.status lt 50}">
											<div class="col-md-3">
												<a href="${ctx}/user/order_cancelOrder.action?id=${orderVo.order.id}" class="btn btn-danger">取消订单</a>
											</div>
										</c:if>
										
										<c:if test="${orderVo.order.eatType eq 1}">
											<c:if test="${orderVo.order.status eq 40}">
												<div class="col-md-3">
													<button class="add-address btn btn-success" data-toggle="modal" 
													   data-target="#finish${xh.count}">
													 确认收货
													</button>
												</div>
											</c:if>
										</c:if>
										
										<c:if test="${orderVo.order.eatType eq 2}">
											<c:if test="${orderVo.order.status eq 30}">
												<div class="col-md-3">
													<a class="btn btn-success" href="${ctx}/user/order_commentOrder.action?id=${orderVo.order.id}">完成订单</a>
												</div>
											</c:if>
										</c:if>
										
										<div class="col-md-3">
											<a class="btn btn-warning" href="${ctx}/user/order_againOrder.action?id=${orderVo.order.id}">再来一单</a>
										</div>
										
										<div class="col-md-3">
											<a class="btn btn-info" href="${ctx}/user/order_orderDetail.action?id=${orderVo.order.id}">订单详情</a>
										</div>
										
										<c:if test="${(orderVo.order.status eq 50) or (orderVo.order.status eq 60)}">
											<div class="col-md-3">
												<button class="add-address btn btn-danger" data-toggle="modal" 
												   data-target="#myModal${xh.count}">
												   投诉商家
												</button>
											</div>
										</c:if>
										
									</div>
								</div>
							</div>
							
							<!-- 评价弹框 -->
							<div class="modal fade" id="finish${xh.count}" tabindex="-1" role="dialog" 
							   aria-labelledby="finishModalLabel${xh.count}" aria-hidden="true">
								   <div class="modal-dialog">
								      <div class="modal-content">
								         <div class="modal-header">
								            <button type="button" class="close" 
								               data-dismiss="modal" aria-hidden="true">
								                  &times;
								            </button>
								            <h4 class="modal-title" id="finishModalLabel${xh.count}">
								              		评价订单
								            </h4>
								         </div>
								         <div class="modal-body">
							            	<form action="${ctx}/user/order_finishOrder.action"
												 method="post" role="form">
												 <input name="id" type="hidden" value="${orderVo.order.id}">
												 <div class="row">
													<div class="form-group col-xs-6 col-md-6">
														<label class="control-label ">是否满意此单菜品</label> 
														<div class="form-group">
														   <label class="checkbox-inlin">
														      <input type="radio" name="buzz" id="optionsRadios1" 
														         value="1" checked> 满意
														   </label>&nbsp;&nbsp;&nbsp;
														   <label class="checkbox-inlin">
														      <input type="radio" name="buzz" id="optionsRadios2" 
														         value="2" >不满意
														   </label>
														</div>
													</div>
												</div>
												
												<div class="row">
													<div class="form-group">
														<div class="form-group">
														    <label for="comment">评论商家</label>
														    <textarea class="form-control" id="comment" name="comment" rows="3"></textarea>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group col-xs-6 col-md-6">
														<input class="btn btn-primary" type="submit" value="确定">
														<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
													</div>
												</div>
											</form>
								         </div>
								      </div><!-- /.modal-content -->
								</div><!-- /.modal -->
							</div>
							
							<!-- 投诉弹框 -->
							<div class="modal fade" id="myModal${xh.count}" tabindex="-1" role="dialog" 
							   aria-labelledby="myModalLabel" aria-hidden="true">
								   <div class="modal-dialog">
								      <div class="modal-content">
								         <div class="modal-header">
								            <button type="button" class="close" 
								               data-dismiss="modal" aria-hidden="true">
								                  &times;
								            </button>
								            <h4 class="modal-title" id="myModalLabel${xh.count}">
								              		投诉商家
								            </h4>
								         </div>
								         <div class="modal-body">
							            	<form action="${ctx}/user/order_complaint.action"
												 method="post" role="form">
												 <input type="hidden" name="id" value="${orderVo.order.id}">
												<div class="row">
													<div class="form-group col-xs-12 col-md-12">
														<label class="control-label " >投诉内容</label> 
														<input
															class="form-control " name="content" type="text" />
													</div>
												</div>
												
												<div class="row">
													<div class="form-group col-xs-12 col-md-12">
														<input class="btn btn-primary" type="submit" value="确定">
														<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
													</div>
												</div>
											</form>
								         </div>
								      </div><!-- /.modal-content -->
								</div><!-- /.modal -->
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			
		</div>
		<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

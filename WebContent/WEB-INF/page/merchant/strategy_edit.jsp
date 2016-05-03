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
			<li><a href="${ctx}/merchant/strategy_openPage.action">优惠策略管理</a></li>
			<li class="active">编辑优惠策略</li>
		</ol>
	</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		<form name="addStrategy" action="${ctx}/merchant/goods_doUpdate.action"
			enctype="multipart/form-data" method="post" role="form">
			<input type="hidden" name="merchantId" value="${strategy.merchantId}"/>
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="strategyType">优惠类型</label> 
					<select class="form-control" id="strategyType" name="strategyType">
			            <option value="1" <c:if test="${strategy.strategyType eq 1}">selected</c:if>>新用户优惠</option>
			            <option value="2" <c:if test="${strategy.strategyType eq 2}">selected</c:if>>满减优惠</option>
			            <option value="3" <c:if test="${strategy.strategyType eq 3}">selected</c:if>>折扣优惠</option>
			        </select>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="premisePrice">优惠前提</label> 
					<div class="input-group">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="premisePrice" name="premisePrice" type="number" value="${strategy.premisePrice}">
				     </div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					 <label class="control-label " for="balancePrice">折扣价格(如果折扣优惠，此选项无效)</label>
					 <div class="input-group">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="balancePrice" name="balancePrice" type="text" value="${strategy.balancePrice}">
				      </div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					 <label class="control-label " for="discount">折扣(如果满减优惠，此选项无效)</label>
			         <input class="form-control" id="discount" name="discount" type="number" value="${strategy.discount}">
				</div>
			</div>

			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<input class="btn btn-primary" type="submit" value="确定">
					<a class="btn btn-default" href="${ctx}/merchant/strategy_openPage.action">返回</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>
</body>
</html>
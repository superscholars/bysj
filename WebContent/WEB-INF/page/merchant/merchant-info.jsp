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
			<li class="active">商户信息管理</li>
		</ol>
	</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		<form name="updateInfo" action="${ctx}/merchant/merchantInfo_updateInfo.action"
			enctype="multipart/form-data" method="post" role="form">
			<div class="row">
				<div class="col-xs-6 col-md-6">
					<img height="48px" width="48px" alt="头像加载失败"
						src="${ctx}/image/logo/${merchant.logoAddr}">
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="name">商户名称</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="name" name="name" type="text"
						value="${merchant.name}" />
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label" for="logo">更换logo</label>
					<input
						class="form-control col-xs-3 col-md-3" id="logo" name="logo" type="file" />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="mobile">商户联系方式</label>
					 <input
						class="form-control col-xs-3 col-md-3" id="mobile" name="mobile" type="text"
						value="${merchant.mobile}" />
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="merchantAddr">商户地址</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="merchantAddr" name="merchantAddr"
						type="text" value="${merchant.merchantAddr}" /><br />
				</div>
			</div>

			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="slogen">商户广告标语</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="slogen" name="slogen" type="text"
						value="${merchant.slogen}" /><br />
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="deliveryStart">起送价格</label>
					<div class="input-group">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="deliveryStart" name="deliveryStart" type="text" value="${merchant.deliveryStart}">
				    </div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="deliveryPrice">配送费用</label> 
					<div class="input-group">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="deliveryPrice" name="deliveryPrice" type="text" value="${merchant.deliveryPrice}">
				    </div>
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="boxPrice">餐盒费用</label> 
					<div class="input-group">
				         <span class="input-group-addon">￥</span>
				         <input class="form-control" id="boxPrice" name="boxPrice" type="text" value="${merchant.boxPrice}">
				    </div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="deliveryTime">配送时间</label> 
					<div class="input-group">
				         <input class="form-control" id="deliveryTime" name="deliveryTime" type="text" value="${merchant.deliveryTime}">
				         <span class="input-group-addon">分钟</span>
				    </div>
				</div>
				<div class="form-group col-xs-6 col-md-6">
					<label class="control-label " for="workTime">营业时间</label> 
					<input
						class="form-control col-xs-3 col-md-3" id="workTime" name="workTime" type="text"
						value="${merchant.workTime}" /><br />
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
			      <label >选择商户类型，若没有自行添加</label>
			      <select name="merchantType" class="form-control">
	                  <c:forEach var="merchantType"   items="${merchantTypes}">
	                  	<option value="${merchantType.merchantType}" <c:if test="${merchantType.merchantType eq merchant.merchantType}">selected</c:if>>${merchantType.merchantType}</option>
	                  </c:forEach>
			      </select>
			     </div>
			     <label >&nbsp;</label>
			     <div class="form-group col-xs-6 col-md-6">
			     	<input class="form-control col-xs-3 col-md-3" id="newMerchantType" name="newMerchantType" type="text"
						 placeholder="下拉菜单中没有再填写"/>
			     </div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
				   <label class="checkbox-inlin">
				      <input type="radio" name="codFlag" id="optionsRadios1" 
				         value="1" <c:if test="${merchant.codFlag eq 1}">checked</c:if>> 支持货到付款
				   </label>&nbsp;&nbsp;&nbsp;
				   <label class="checkbox-inlin">
				      <input type="radio" name="codFlag" id="optionsRadios2" 
				         value="2" <c:if test="${merchant.codFlag eq 2}">checked</c:if>>不支持货到付款
				   </label>
				</div>
				
				
				<div class="form-group col-xs-6 col-md-6">
				   <label class="checkbox-inlin">
				      <input type="radio" name="openFlag" id="optionsRadios1" 
				         value="1" <c:if test="${merchant.openFlag eq 1}">checked</c:if>> 上架
				   </label>&nbsp;&nbsp;&nbsp;
				   <label class="checkbox-inlin">
				      <input type="radio" name="openFlag" id="optionsRadios2" 
				         value="2" <c:if test="${merchant.openFlag eq 2}">checked</c:if>>下架
				   </label>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-xs-6 col-md-6">
					<input class="btn btn-primary" type="submit" value="确定">
					<a class="btn btn-default" href="#" onClick=" javascript :history.back(-1);">返回</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="../inc/merchant/rightbootom.jsp"%>
</body>
</html>
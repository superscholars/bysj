<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>随便美餐</title>
		<link rel="stylesheet" href="${ctx}/css/shop-car.css" />
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
			<span class="shop-title">购物车</span>
			<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />
			
			<div class="address">
				<span>地址信息：</span>
				<c:if test="${!empty addressList}">
					<div class="address-content">${mAddress.addressAttr}&nbsp;${mAddress.houseNumber}&nbsp;${mAddress.realName}${mAddress.sex}收。&nbsp;${mAddress.mobile}</div>
				</c:if>
				<div class="address-button">
					<c:if test="${!empty addressList}">
						<button class="add-address btn btn-danger" data-toggle="modal" 
						   data-target="#myModal">
						   添加
						</button>
					</c:if>
					<c:if test="${!empty addressList}">
						<button class="add-address btn btn-info" data-toggle="modal" 
						   data-target="#myModal1">
						   更换
						</button>
					</c:if>
				</div>
			</div>
			<form class="form" role="form" action="${ctx}/user/shopping_placeOrder.action" method="post">
			<input name="addressId" type="hidden" value="${mAddress.id}">
			<div class="goods-list">
				<span class="merchant-name">${merchant.name}</span>
				<span class="strategy">
					<c:if test="${strategy != null}">
						<span style="color:red" class="glyphicon glyphicon-fire "></span>&nbsp;${strategy}
					</c:if>
				</span>
				<div class="item-head">
					<div class="col-md-2">样图</div>
					<div class="col-md-2">名字</div>
					<div class="col-md-2">描述</div>
					<div class="col-md-2">单价</div>
					<div class="col-md-2">数量</div>
					<div class="col-md-2">操作</div>
				</div>
					<c:if test="${!empty goodsList}">
						<c:forEach items="${goodsList}" var="goods">
							<div class="goods-item">
								<div class="pic col-md-2"><img class="merchant-logo" src="${ctx}/image/goods/${goods.goodsPic}" alt="adsf"/></div>
								<div class="wenzi col-md-2">${goods.name}</div>
								<div class="wenzi col-md-2">${goods.goodsDesc}</div>
								<div class="wenzi col-md-2">${goods.price}</div>
								<div class="wenzi col-md-2"><input name="counts" class="form-control" min="1" value="1" type="number"></div>
								<div class="wenzi col-md-2">
									<a class="btn btn-danger" href="${ctx}/user/shopping_removeGoods.action?id=${goods.id}">删除</a>
								</div>
							</div>
						</c:forEach>
					</c:if>
				
			</div>
			<c:if test="${!empty goodsList}">
				<div class="confirm-button">
					<input class="confirm btn btn-danger btn-lg" type="submit" value="生成订单">
				</div>
			</c:if>
			</form>
			
			
			<!-- 添加地址模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" 
			               data-dismiss="modal" aria-hidden="true">
			                  &times;
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			              		添加一条地址信息
			            </h4>
			         </div>
			         <div class="modal-body">
		            	<form name="addAddress" action="${ctx}/user/shopping_addAddress.action"
							 method="post" role="form">
							<div class="row">
								<div class="form-group col-xs-6 col-md-6">
									<label class="control-label " for="realName">真实姓名</label> 
									<input
										class="form-control col-xs-3 col-md-3" id="realName" name="realName" type="text"/>
								</div>
								<div class="form-group col-xs-6 col-md-6">
									<label class="control-label" for="mobile">联系方式</label>
									<input
										class="form-control col-xs-3 col-md-3" id="mobile" name="mobile" min="1" type="number"/>
								</div>
							</div>
							
							<div class="row">
								<div class="form-group col-xs-6 col-md-6">
								   <label class="checkbox-inlin">
								      <input type="radio" name="sex" id="optionsRadios1" 
								         value="男" > 男
								   </label>&nbsp;&nbsp;&nbsp;
								   <label class="checkbox-inlin">
								      <input type="radio" name="sex" id="optionsRadios2" 
								         value="女" > 女
								   </label>
								</div>
							</div>
							
							<div class="row">
								<div class="form-group col-xs-12 col-md-12">
									<label class="control-label " for="addressAttr">地址详情</label>
									 <input
										class="form-control" id="addressAttr" name="addressAttr" type="text"/>
								</div>
							</div>
				
							<div class="row">
								<div class="form-group col-xs-12 col-md-12">
									<label class="control-label " for="houseNumber">门牌号</label> 
									<input
										class="form-control " id="houseNumber" name="houseNumber" type="text" />
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
			
			<!-- 修改地址模态框（Modal） -->
			<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
			   aria-labelledby="myModalLabel" aria-hidden="true">
			   <div class="modal-dialog">
			      <div class="modal-content">
			         <div class="modal-header">
			            <button type="button" class="close" 
			               data-dismiss="modal" aria-hidden="true">
			                  &times;
			            </button>
			            <h4 class="modal-title" id="myModalLabel">
			              		更换地址信息
			            </h4>
			         </div>
			         <div class="modal-body">
		            	<form name="addAddress" action="${ctx}/user/shopping_chooseAddress.action"
							 method="post" role="form">
							<div class="row">
								<div class="form-group col-xs-12 col-md-12">
									<label class="control-label " for="realName">选择地址</label> 
									<select class="form-control" name="addressId">
										<c:forEach items="${addressList}" var="address">
											<option value="${address.id}">
												${address.addressAttr}&nbsp;${address.houseNumber}&nbsp;${address.realName}${address.sex}收。&nbsp;${address.mobile}
											</option>
										</c:forEach>
									</select>
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
			
		</div>
		<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

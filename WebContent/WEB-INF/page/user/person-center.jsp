<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/person-center.css" />
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
		<div style="width:100%;height: 600px; background: url(${ctx}/image/bg/pbg.jpg);background-color: #777777;"></div>
		<div class="container">
			<c:if test="${!empty err}">
				<div class="alert alert-danger">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
						&times; </a> ${err}
				</div>
			</c:if>
			<!-- 用户展示 -->
			<div class="logo-name">
				<div class="logo">
					<img class="merchant-logo" src="${ctx}/image/head/${user.headPath}" alt="adsf"/>
				</div>
				<div class="name">
					<span>${user.nickName}</span>
				</div>
			</div>
			<!-- 地址列表 -->
			<div class="address-list">
				<span class="title">地址管理</span>
				<span>
					<!-- 按钮触发模态框 -->
					<button class=" right1 btn btn-primary" data-toggle="modal" 
					   data-target="#myModal">
					   添加地址
					</button>
				</span>
				<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />
				<div class="address">
					<c:choose>
						<c:when test="${empty addressList}">
							<li href="#" class="list-group-item">没有添加地址</li>
						</c:when>
						<c:otherwise>
							<c:forEach items="${addressList}" var="address">
								<c:choose>
									<c:when test="${address.status eq 1}">
										<li class="list-group-item active">${address.addressAttr}&nbsp;${address.houseNumber}&nbsp;${address.realName}${address.sex}收。&nbsp;${address.mobile} <span><a class="right btn btn-danger" href="${ctx}/user/person_deleteAddress.action?id=${address.id}">删除地址</a></span></li>
									</c:when>
									<c:otherwise>
										<li class="list-group-item"><a href="${ctx}/user/person_changeAddress.action?id=${address.id}">${address.addressAttr}&nbsp;${address.houseNumber}&nbsp;${address.realName}${address.sex}收。&nbsp;${address.mobile}</a> <span><a class="right btn btn-danger" href="${ctx}/user/person_deleteAddress.action?id=${address.id}">删除地址</a></span></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
			
			<!-- 收藏列表 -->
			<div class="collection-list">
				<span class="title">收藏商家</span>
				<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />
				<c:choose>
					<c:when test="${empty merchantList}">
						<div class="collection-item" style="text-align: center;font-size:20px">
							<b>尚未收藏任何商户，或商户已被禁闭</b>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${merchantList}" var="merchant">
							<a href="${ctx}/user/user_merchant.action?id=${merchant.id}">
								<div class="collection-item">
									<div class="merchant-display">
										<div class="col-md-3 merchant-info">
											<div class="suibianjiao">
												<div class="pic"><img class="logo" src="${ctx}/image/logo/${merchant.logoAddr}" alt="logo加载失败"/></div>
												<div class="info">
													<div class="merchant-name"><b>${merchant.name}</b></div>
													<div class="slogen">${merchant.slogen}</div>
													<div class="detail">点击查看详情>></div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</div>
			
			
			<!-- 模态框（Modal） -->
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
		            	<form name="addAddress" action="${ctx}/user/person_addAddress.action"
							 method="post" role="form">
							<div class="row">
								<input type="hidden" name="userId" value="${user.id}">
								<div class="form-group col-xs-6 col-md-6">
									<label class="control-label " for="realName">真实姓名</label> 
									<input
										class="form-control col-xs-3 col-md-3" id="realName" name="realName" type="text"/" />
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
								         value="女" >女
								   </label>
								</div>
							</div>
							
							<div class="row">
								<div class="form-group col-xs-12 col-md-12">
									<label class="control-label " for="addressAttr">地址详情</label>
									 <input
										class="form-control" id="addressAttr" name="addressAttr" type="text" />
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
		</div>
			<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

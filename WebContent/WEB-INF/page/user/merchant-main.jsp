<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>随便美餐</title>
		<link rel="stylesheet" href="${ctx}/css/merchant-main.css" />
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
		<div style="width:100%;height: 600px; background: url(${ctx}/image/bg/bg.jpg);background-color: #f4f4f4;"></div>
		<div class="container">
			<c:if test="${!empty err}">
				<div class="alert alert-danger">
					<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
						&times; </a> ${err}
				</div>
			</c:if>
			<!-- 商户展示 -->
			<div class="show-merchant">
				<div class="show-table">
					<div >
						<img class="merchant-logo" src="${ctx}/image/logo/${merchant.logoAddr}" alt="adsf"/>
					</div>
					<div class="name-slogen">
						<div class="name"><b>${merchant.name}</b></div>
						<div class="slogen">${merchant.slogen}</div>
						<div class="slogen">
							<c:if test="${collection == false}">
								<a class="btn btn-xs btn-primary" href="${ctx}/user/user_collection.action?id=${merchant.id}">收藏商家</a>
							</c:if>
							<c:if test="${collection == true}">
								<a class="btn btn-xs btn-danger" href="${ctx}/user/user_collection.action?id=${merchant.id}">取消收藏</a>
							</c:if>
						</div>
					</div>
				</div>
				
				<div class="detail">
					<div class="about">
						<div class="address">
							商家地址：${merchant.merchantAddr}
						</div>
						<div class="mobile">
							商家电话：<b>${merchant.mobile}</b>
						</div>
					</div>
					<div class="charge">
						<div class="feiyong">
							<div class="month-count">月销量：<b>${merchant.monthCount}</b></div>
							<div class="start-charge">起送费用：<b>${merchant.deliveryStart}</b>元起送</div>
							<div class="start-charge">配送费用：<b>${merchant.deliveryPrice}</b>元</div>
							<div class="start-charge">餐具费用：<b>${merchant.boxPrice}</b>元</div>
							<div class="start-charge">预计配送时间：<b>${merchant.deliveryTime}</b>分钟</div>
							<div class="start-charge">营业时间：<b>${merchant.workTime}</b></div>
						</div>
					</div>
				</div>
			</div>
			<!-- 商品列表 -->
			
			<div class="goods-list">
				<span class="goods-title">商品列表</span>
				<span class="strategy">
					<span style="color:red" class="glyphicon glyphicon-fire "></span>&nbsp;${strategy}
				</span>
				<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />
				<c:choose>
					<c:when test="${empty goodsList}">
						<div class="row">
							<div class="col-md-12">
								<span style="font-size: 22px">该商家暂时无商品</span>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${goodsList}" var="goods">
							<div class="list-item">
								<div class="zhuyao row">
									<div class="col-md-2">
										<img style="width:121px;heiht:140px;" src="${ctx}/image/goods/${goods.goodsPic}" alt="商品图片不存在"/>
									</div>
									<div class="duiqi col-md-2">
										${goods.name}
									</div>
									<div class="duiqi col-md-2">
										${goods.category}
									</div>
									<div style="font-size: 14px;" class="duiqi col-md-2">
										${goods.goodsDesc}
									</div>
									<div style="text-align: center;" class="duiqi col-md-2">
										${goods.price}
									</div>
									<div class="duiqi col-md-2">
										<a class="btn btn-danger" href="${ctx}/user/user_addShopping.action?id=${goods.id}&merchantId=${merchant.id}">加入购物车</a>
									</div>
								</div>
								<div class="xiaodian row">
									<span class="col-md-2 col-md-push-8">月销量${goods.monthCount}</span>
									<span class="col-md-2 col-md-push-7">点赞量${goods.buzz}</span>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</div>
			<!-- 评论区 -->
			<div style="margin-top: 60px;">
			</div>
			
			<div class="comment-list">
				<span class="goods-title">评论区</span>
				<hr style="height:2px;width:98%;border:none;border-top:3px ridge #000000;" />
				<c:choose>
					<c:when test="${empty commentList}">
						<div class="row">
							<div class="col-md-12">
								<span style="font-size: 22px">暂无评论···</span>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${commentList}" var="comment">
							<div class="comment-item">
								<div class="tou-ming col-md-2">
									<div >
										<img class="touxiang" src="${ctx}/image/head/${comment.headPath}" alt="头像加载失败"/>
									</div>
									<div class="nicheng">
										${comment.createName}
									</div>	
								</div>
								<div class="wen-zhang col-md-10">
									<div class="comment-content">${comment.commentContent}</div>
									<div style="text-align: right;"><fmt:formatDate value="${comment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
		<%@ include file="../inc/user/footer.jsp" %>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <title>随便美餐</title>
   <link href="${ctx}/css/index.css" rel="stylesheet">
   <style type="text/css">
   .container{
   		padding:0px;
   }
   .banner{
   		margin-top:50px;
   }
   </style>
</head>
<body>
<%@ include file="../inc/user/top.jsp" %>
		<!-- 轮播 -->
		<div class="banner">
			<div id="myCarousel" class="carousel slide">
			   <!-- 轮播（Carousel）指标 -->
			   <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
			      <li data-target="#myCarousel" data-slide-to="3"></li>
			   </ol>   
			   <!-- 轮播（Carousel）项目 -->
			   <div class="carousel-inner">
			      <div class="item active">
			         <img src="${ctx}/image/banner/banner1.jpg" alt="First slide">
			         <div class="carousel-caption">鸡翅</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner2.jpg" alt="Second slide">
			         <div class="carousel-caption">KFC</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner3.jpg" alt="Third slide">
			         <div class="carousel-caption">山珍海味</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner4.jpg" alt="Third slide">
			         <div class="carousel-caption">鲜橙多</div>
			      </div>
			   </div>
			   <!-- 轮播（Carousel）导航 -->
			   <a class="carousel-control left" href="#myCarousel" 
			      data-slide="prev">&lsaquo;</a>
			   <a class="carousel-control right" href="#myCarousel" 
			      data-slide="next">&rsaquo;</a>
			</div> 
		</div>
	<!-- 主界面 -->
	<div class="container">
		
		<!-- 主题内容 -->
		<div class="content">
			<div class="category_search">
				<!-- 搜索框 -->
				<div class="search">
					<c:if test="${!empty searchErr}">
						<div class="alert alert-danger">
							<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
								&times; </a> ${searchErr}
						</div>
					</c:if>
					<form action="${ctx}/user/user_search.action">
						<div id="search" class="input-group">
			               <input name="condition" type="text" class="form-control" placeholder="请输入餐品名称">
			               <span class="input-group-btn">
			                  <input class="btn btn-primary" type="submit" value="Go!">
			               </span>
			            </div><!-- /input-group -->
		            </form>
				</div>
				<!-- 虚线 -->
	            <hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
	            <!-- 分类 -->
	            <div class="category">
	            	<div class="category-label">
		            	<div class="row">
		            		<c:forEach items="${typeList}" var="type">
		            			<span class="col-md-2">
			            			<a href="${ctx}/user/user_merchantType.action?type=${type.merchantType}" class="btn">${type.merchantType}</a>
			            		</span>
		            		</c:forEach>
		            	</div>
		            	
	            	</div>
	            </div>
	            
			</div>
			
			<div class="shop">
				<div class="show">
					<c:forEach items="${merchants}" var="merchant">
						<div class="shop-list-item">
							<div class="item-title"><div class="timu">${merchant.key}</div></div>
							<div class="item-content">
								<c:forEach items="${merchant.value}" var="item">
									<a href="${ctx}/user/user_merchant.action?id=${item.id}">
										<div class="list-theme col-md-4">
											<div class="tupian">
												<img style="height:140px;width:100%;" alt="Logo" src="${ctx}/image/logo/${item.logoAddr}">
											</div>
											<div class="mingzi">
												<div class="merchant-name">${item.name}</div>
												<div class="merchant-address">${item.merchantAddr}</div>
												<div class="merchant-balance">${item.workTime}</div>
											</div>
										</div>
									</a>
								</c:forEach>
							</div>
						</div>
					</c:forEach>	
					
				</div>
				
				<div class="hot">
					<div class="hot-goods hot-height">
						<div class="hot-goods-title hot-title">
							热门商品
						</div>
						<div class="hot-goods-content hot-content">
							<ul>
								<c:forEach items="${hotGoods}" var="goods">
									<li><a href="${ctx}/user/user_merchant.action?categoryId=${goods.categoryId}"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;${goods.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="hot-merchant hot-height">
						<div class="hot-merchant-title hot-title">
							热门商家
						</div>
						<div class="hot-merchant-content hot-content">
							<ul>
								<c:forEach items="${hotMerchant}" var="hMerchant">
									<li><a href="${ctx}/user/user_merchant.action?id=${hMerchant.id}"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;${hMerchant.name}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					
					<div class="big-balance hot-height">
							<div class="big-balance-title hot-title">
								大额优惠
							</div>
							<div class="big-balance-content hot-content">
								<ul>
									<c:forEach items="${hotStrategy}" var="strategy">
										<li><a href="${ctx}/user/user_merchant.action?id=${strategy.merchantId}"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;${strategy.strategy}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
				</div>
			</div><!-- /shop -->
			
		</div><!-- /content -->
		
		<!-- footer -->
		<%@ include file="../inc/user/footer.jsp" %>
	</div><!-- container -->

</body>
</html>
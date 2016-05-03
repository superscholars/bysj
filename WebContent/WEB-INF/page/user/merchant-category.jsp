<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ include file="../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>随便美餐</title>
		<link rel="stylesheet" href="${ctx}/css/merchant-category.css" /> 
	</head>
	<body>
	<%@ include file="../inc/user/top.jsp" %>
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
			         <div class="carousel-caption">标题 1</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner2.jpg" alt="Second slide">
			         <div class="carousel-caption">标题 2</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner3.jpg" alt="Third slide">
			         <div class="carousel-caption">标题 3</div>
			      </div>
			      <div class="item">
			         <img src="${ctx}/image/banner/banner4.jpg" alt="Third slide">
			         <div class="carousel-caption">标题 4</div>
			      </div>
			   </div>
			   <!-- 轮播（Carousel）导航 -->
			   <a class="carousel-control left" href="#myCarousel" 
			      data-slide="prev">&lsaquo;</a>
			   <a class="carousel-control right" href="#myCarousel" 
			      data-slide="next">&rsaquo;</a>
			</div> 
		</div>
		
		<div class="container">
			<div class="category-name">
				<ol class="breadcrumb">
					<li class="active">商家类型：${condition}</li>
				  	<li><a href="${ctx}/user/user_goHome.action">返回主页</a></li>
				</ol>
			</div>
			
			<div class="row">
			
				<c:forEach items="${merchantList}" var="merchant">
				
					<a href="${ctx}/user/user_merchant.action?id=${merchant.id}">
						<div class="merchant-display">
							<div class="col-md-3 merchant-info">
								<div class="suibianjiao">
									<div class="pic"><img class="merchant-logo" src="${ctx}/image/logo/${merchant.logoAddr}" alt="adsf"/></div>
									<div class="info">
										<div class="merchant-name"><b>${merchant.name}</b></div>
										<div class="slogen">${merchant.slogen}</div>
										<div class="detail">点击查看详情>></div>
									</div>
								</div>
							</div>
						</div>
					</a>
				
				</c:forEach>
			
			</div>
			
			<div class="paging">
				<ul class="pagination">
				  <li><a href="#">&laquo;</a></li>
				  <li class="disabled"><a href="javascript:return false;">1</a></li>
				  <li><a href="#">&raquo;</a></li>
				</ul>
			</div>
			<%@ include file="../inc/user/footer.jsp" %>
			
		</div>
		
	</body>
</html>

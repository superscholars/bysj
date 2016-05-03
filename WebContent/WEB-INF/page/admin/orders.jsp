<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../base.jsp" %>
<%@ include file="../inc/admin/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <title>随便美餐-管理后台</title>
</head>
<body>
	<div class="container">
		<div>
			<ol class="breadcrumb">
	  			<li><a href="#">随便美餐运营管理后台</a></li>
				<li class="active">订单信息</li>
			</ol>
		</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		
		<div class="row">
	        <form class="form-inline" action="${ctx}/admin/admin_goHome.action" method="POST">
	            <div class="form-group col-xs-2">
	                <label class="control-label sr-only">起始日期</label>
	                <div class="input-group date" id="st">
	                    <input id="startDate" type="text" class="form-control"  date-format="yyyy-mm-dd" placeholder="起始日期" name="startDate" value="${startDate}" />
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
	            </div>
	            <div class="form-group col-xs-2">
	                <label class="control-label sr-only">结束日期</label>
	                <div class="input-group date" id="et">
	                    <input id="endDate" type="text" class="form-control"  date-format="yyyy-mm-dd" placeholder="结束日期" name="endDate" value="${endDate}" />
	                    <span class="input-group-addon">
	                        <span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
	            </div>
            
		      <select class="form-control" name="searchType">
		         <option value="0"  <c:if test="${searchType eq '0'}">selected</c:if>>请选择查询类型</option>
		         <option value="1"  <c:if test="${searchType eq '1'}">selected</c:if>>订单号码</option>
		         <option value="2"  <c:if test="${searchType eq '2'}">selected</c:if>>收件人手机号</option>
		         <option value="3"  <c:if test="${searchType eq '3'}">selected</c:if>>商户名称</option>
		         <option value="4"  <c:if test="${searchType eq '4'}">selected</c:if>>下单人账号</option>
		      </select>
		   
	         <input class="form-control" id="searchValue" name="searchValue" type="text" 
	            placeholder="请输入查询内容" value="${searchValue}">
            
	           <input type="submit" class="btn btn-success" value="查询">
	        </form>
	    </div>
	    <hr/>
		
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>订单号码</th>
		         <th>订单时间</th>
		         <th>商户名称</th>
		         <th>当前状态</th>
		         <th>预计配送时间</th>
		         <th>食用方式</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty orderList}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="7"><b>没有数据</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty orderList}">
			      <c:forEach var="order"   items="${orderList}">
	                  <tr>
				         <td>${order.orderNumber}</td>
				         <td><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				         <td>${order.merchantName}</td>
				         <td>
				         	<c:choose>
				         		<c:when test="${order.status eq 0}">
				         			未支付
				         		</c:when>
				         		<c:when test="${order.status eq 10}">
				         			待接单
				         		</c:when>
				         		<c:when test="${order.status eq 20}">
				         			商家已接单，准备中
				         		</c:when>
				         		<c:when test="${order.status eq 30}">
				         			菜品已备好
				         		</c:when>
				         		<c:when test="${order.status eq 40}">
				         			在路上
				         		</c:when>
				         		<c:when test="${order.status eq 50}">
				         			订单已完成
				         		</c:when>
				         		<c:when test="${order.status eq 60}">
				         			订单已取消
				         		</c:when>
				         	</c:choose>
				         </td>
				         <td>${order.deliveryTime}</td>
				         <td>
				         	<c:choose>
				         		<c:when test="${order.eatType eq 1}">
				         			外卖
				         		</c:when>
				         		<c:when test="${order.eatType eq 2}">
				         			堂吃
				         		</c:when>
				         		<c:otherwise>
				         			未选择
				         		</c:otherwise>
				         	</c:choose>
				         </td>
				         <td>
				         	<a class="btn btn-primary btn-sm" target="_blank" href="${ctx}/admin/admin_orderDetail.action?id=${order.id}">查看详情</a>
				         </td>
				      </tr>
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
	</div>
	<script type="text/javascript">
	    $(function () {
	        $('#st').datetimepicker({
	            language: 'zh-CN',
	            format: 'yyyy-mm-dd',
	            minView: 'month',
	            todayBtn: true,
	            autoclose: true
	        });
	        $('#et').datetimepicker({
	            language: 'zh-CN',
	            format: 'yyyy-mm-dd',
	            minView: 'month',
	            todayBtn: true,
	            autoclose: true
	        });
	    });
	</script>
	<%@ include file="../inc/admin/footer.jsp" %>
</body>
</html>
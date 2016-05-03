<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
	        <form class="form-inline" action="?" method="POST">
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
	            
	            <div class="input-group">
			        <span class="input-group-addon">订单号码</span>
			        <input type="text" class="form-control" placeholder="twitterhandle">
			     </div>
	            
	            <button type="submit" class="btn btn-success">查询</button>
	        </form>
	    </div>
	    <hr/>
		
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>商品图片</th>
		         <th>商品名称</th>
		         <th>商品价格</th>
		         <th>商品描述</th>
		         <th>所属分类</th>
		         <th>月销售量</th>
		         <th>点赞量</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty orders}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="8"><b>没有数据</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty orders}">
			      <c:forEach var="order"   items="${orders}">
	                  <tr>
				         <td>${order.name}</td>
				         <td>${order.price}</td>
				         <td>${order.goodsDesc}</td>
				         <td>${order.category}</td>
				         <td>${order.monthCount}</td>
				         <td>${order.buzz}</td>
				         <td>
				         	<a class="btn btn-primary btn-sm" href="${ctx}/merchant/goods_editPage.action?">编辑</a>
				         	<a class="btn btn-danger btn-sm" href="${ctx}/merchant/goods_remove.action?">删除</a>
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
</body>
</html>
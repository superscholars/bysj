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
				<li class="active">投诉中心</li>
			</ol>
		</div>
		
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
            
	           <input type="submit" class="btn btn-success" value="查询">
	        </form>
	    </div>
	    <hr/>
		
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>投诉时间</th>
		         <th>用户名称</th>
		         <th>商户名称</th>
		         <th>投诉内容</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty complaintList}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="5"><b>没有数据</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty complaintList}">
			      <c:forEach var="complaint"   items="${complaintList}">
	                  <tr>
				         <td><fmt:formatDate value="${complaint.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				         <td>${complaint.userName}</td>
				         <td>${complaint.merchantName}</td>
				         <td>${complaint.content}</td>
				         <td>
				         	<a class="btn btn-primary btn-sm" target="_blank" href="${ctx}/admin/admin_orderDetail.action?id=${complaint.orderId}">查看订单</a>
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
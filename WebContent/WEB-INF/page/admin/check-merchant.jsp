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
				<li class="active">审核商户</li>
			</ol>
		</div>
		<c:if test="${!empty err}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${err}
			</div>
		</c:if>
		<c:if test="${!empty success}">
			<div class="alert alert-success">
				<a class="close" data-dismiss="alert" href="#" aria-hidden="true">
					&times; </a> ${success}
			</div>
		</c:if>
		
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>申请时间</th>
		         <th>商户账号</th>
		         <th>商户昵称</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty merchantList}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="4"><b>暂时没有待审核商户</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty merchantList}">
			      <c:forEach var="merchantUser"   items="${merchantList}">
	                  <tr>
				         <td><fmt:formatDate value="${merchantUser.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				         <td>${merchantUser.loginName}</td>
				         <td>${merchantUser.nickName}</td>
				         <td>
				         	<a class="btn btn-success btn-sm" href="${ctx}/admin/operate_doCheckMerchant.action?id=${merchantUser.id}">通过审核</a>
				         </td>
				      </tr>
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
	</div>
	<%@ include file="../inc/user/footer.jsp" %>
</body>
</html>
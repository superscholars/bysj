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
				 	<li class="active">禁闭商户</li>
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
		         <th>头像</th>
		         <th>用户账号</th>
		         <th>用户昵称</th>
		         <th>当前状态</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty userList}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="5"><b>没有用户数据</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty userList}">
			      <c:forEach var="user"   items="${userList}">
	                  <tr>
	                  	 <td>
	                  	 	<img style="display: inline;" height="48px" width="48px" alt="头像加载失败"
											src="${ctx}/image/head/${user.headPath}">
	                  	 </td>
				         <td>${user.loginName}</td>
				         <td>${user.nickName}</td>
				         <td>
				         		<c:if test="${user.loginSwitch eq 1}">
				         				正常使用中
				         		</c:if>
				         		<c:if test="${user.loginSwitch eq 2}">
				         				已被禁闭
				         		</c:if>
				         </td>
				         <td>
				         	 <c:if test="${user.loginSwitch eq 1}">
					         	 <a class="btn btn-danger btn-sm"  href="${ctx}/admin/operate_doConfineUser.action?id=${user.id}">禁闭</a>
				         	 </c:if>
				         	 <c:if test="${user.loginSwitch eq 2}">
					         	 <a class="btn btn-danger btn-sm" href="${ctx}/admin/operate_unConfineUser.action?id=${user.id}">解禁</a>
				         	 </c:if>
				         </td>
				      </tr>
				      
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
	</div>
	<%@ include file="../inc/admin/footer.jsp" %>
</body>
</html>
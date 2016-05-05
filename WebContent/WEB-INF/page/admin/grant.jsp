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
				 	<li class="active">管理员账户管理</li>
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
		
			<button class="btn btn-success" data-toggle="modal" 
							   data-target="#myModal">
							   添加管理员
			</button>
		
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
					         	 <a class="btn btn-danger btn-sm"  href="${ctx}/admin/operate_doGrant.action?id=${user.id}">禁闭</a>
				         	 </c:if>
				         	 <c:if test="${user.loginSwitch eq 2}">
					         	 <a class="btn btn-danger btn-sm" href="${ctx}/admin/operate_unGrant.action?id=${user.id}">解禁</a>
				         	 </c:if>
				         </td>
				      </tr>
				      
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
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
				              		添加管理员账户
				            </h4>
				         </div>
				         <div class="modal-body">
			            	<form action="${ctx}/admin/operate_addAdmin.action"
								 method="post" role="form">
								 
								 <div class="row">
									<div class="form-group col-xs-12 col-md-12">
										<label class="control-label " >昵称</label> 
										<input
											class="form-control " name="nickName" type="text" />
									</div>
								</div>
								 
								<div class="row">
									<div class="form-group col-xs-12 col-md-12">
										<label class="control-label " >用户名</label> 
										<input
											class="form-control " name="loginName" type="text" />
									</div>
								</div>
								
								<div class="row">
									<div class="form-group col-xs-12 col-md-12">
										<label class="control-label " >密码</label> 
										<input
											class="form-control " name="password" type="password" />
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
	<%@ include file="../inc/admin/footer.jsp" %>
</body>
</html>
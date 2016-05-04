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
	  			<c:if test="${empty merchantType}">
				 	<li class="active">禁闭商户</li>
	  			</c:if>
	  			<c:if test="${!empty merchantType}">
				 	<li >禁闭商户</li>
				 	<li class="active">${merchantType}</li>
	  			</c:if>
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
		
		<div class="row">
	        <form class="form-inline" action="${ctx}/admin/admin_goHome.action" method="POST">
	         
	         <select class="form-control" name="merchantType">
		         <option value="0"  <c:if test="${merchantType eq '0'}">selected</c:if>>请选择商户类型</option>
		         <c:forEach items="${merchantTypeList}" var="type" >
			         <option value="${type.merchantType}"  >请选择商户类型</option>
		         </c:forEach>
		      </select>
	         
	         <input class="form-control" id="merchantName" name="merchantName" type="text" 
	            placeholder="请输入商户名称" value="${merchantName}">
            
	           <input type="submit" class="btn btn-success" value="查询">
	        </form>
	    </div>
	    <hr/>
		
		<table class="table table-responsive table-striped table-hover">
		   <thead>
		      <tr>
		         <th>logo</th>
		         <th>商户名称</th>
		         <th>商户类型</th>
		         <th>商户地址</th>
		         <th>商户标语</th>
		         <th>月销量</th>
		         <th>营业时间</th>
		         <th>操作</th>
		      </tr>
		   </thead>
		   <tbody>
		   	  <c:if test="${empty merchantList}">
			      <tr>
			         <td class="text-center" rowspan="3" colspan="8"><b>没有数据</b></td>
			      </tr>
		      </c:if>
		      <c:if test="${!empty merchantList}">
			      <c:forEach var="merchant"   items="${merchantList}" varStatus="xh">
	                  <tr>
	                  	 <td>
	                  	 	<img style="display: inline;" height="48px" width="48px" alt="头像加载失败"
											src="${ctx}/image/logo/${merchant.logoAddr}">
	                  	 </td>
				         <td>${merchant.name}</td>
				         <td>${merchant.merchantType}</td>
				         <td> ${merchant.merchantAddr} </td>
				         <td>${merchant.slogen}</td>
				         <td> ${merchant.monthCount}</td>
				         <td> ${merchant.workTime}</td>
				         <td>
				         		<button class="add-address btn btn-danger" data-toggle="modal" 
								   data-target="#myModal${xh.count}">
								   禁闭商家
								</button>
				         	<a class="btn btn-primary btn-sm" target="_blank" href="${ctx}/admin/operate_merchantMain.action?id=${merchant.id}">进入商户主页</a>
				         </td>
				      </tr>
				      
				      <!-- 处罚模态框 -->
				      <div class="modal fade" id="myModal${xh.count}" tabindex="-1" role="dialog" 
							   aria-labelledby="myModalLabel" aria-hidden="true">
								   <div class="modal-dialog">
								      <div class="modal-content">
								         <div class="modal-header">
								            <button type="button" class="close" 
								               data-dismiss="modal" aria-hidden="true">
								                  &times;
								            </button>
								            <h4 class="modal-title" id="myModalLabel${xh.count}">
								              		处罚商家——${merchant.name}
								            </h4>
								         </div>
								         <div class="modal-body">
							            	<form action="${ctx}/admin/operate_doPunishMerchant.action"
												 method="post" role="form">
												 <input type="hidden" name="name" value="${merchant.name}">
												 <input type="hidden" name="id" value="${merchant.id}">
												<div class="row">
													<div class="form-group col-xs-12 col-md-12">
														<label class="control-label " >处罚原因</label> 
														<input
															class="form-control " name="reason" type="text" />
													</div>
												</div>
												
												<div class="row">
													<div class="form-group col-xs-12 col-md-12">
														<label class="control-label " >处罚天数</label> 
														<input
															class="form-control " name="days" type="number" min="1" value="1" />
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
				      
	              </c:forEach>
		      </c:if>
		   </tbody>
		</table>
	
	</div>
	<%@ include file="../inc/admin/footer.jsp" %>
</body>
</html>
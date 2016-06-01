<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商户注册</title>

<link rel="stylesheet" href="${ctx}/css/style-regist.css">

<script src="${ctx}/js/jquery-2.2.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyform.js"></script>

</head>
<body>
<br>
<div class="form-div">
    <form id="reg-form" action="${ctx}/merchant/merchant_doRegist.action" method="post">
	<input id="err" type="hidden" value="meiyou${err}"></input>
	<input id="message" type="hidden" value="${err}"></input>
        <table>
            <tr>
                <td>登录名</td>
                <td><input name="loginName" type="text" id="uid" easyform="length:4-16;char-normal;real-time;" message="用户名必须为4—16位的英文字母或数字" easytip="disappear:lost-focus;theme:blue;" ajax-message="用户名已存在!">
                </td>
            </tr>
            <tr>
                <td>昵称</td>
                <td><input name="nickName" type="text" id="nickname" easyform="length:2-16;" message="昵称必须为2—16位的中文或英文" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>            
            <tr>
                <td>密码</td>
                <td><input name="password" type="password" id="psw1" easyform="length:6-16;" message="密码必须为6—16位" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input name="password2" type="password" id="psw2" easyform="length:6-16;equal:#psw1;" message="两次密码输入要一致" easytip="disappear:lost-focus;theme:blue;"></td>
            </tr>
        </table>

		<div class="buttons">
			<input value="注 册" type="submit" style="margin-right:20px; margin-top:20px;">
			<input value="我有账号，我要登录" type="button" style="margin-right:45px; margin-top:20px;" onClick="goLogin()">
        </div>
		
        <br class="clear">
    </form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#reg-form').easyform();
	if($('#err').val() !="meiyou"){
		alert($('#message').val());
	}
});

function goLogin(){
	var url ="";
	url=window.location.href;
	url = url.replace("register", "login");
	url = url.replace("doRegist", "login");
	window.location.href=url;
}

</script>
</body>
</html>

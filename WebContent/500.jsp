<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" media="screen, projection" /> <!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all" href="${ctx}/css/tipsy.css" /> <!-- Tipsy implementation -->

<style >
	*{
	font-family:微软雅黑;
	}
</style>

<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="css/ie8.css" />
<![endif]-->

<script type="text/javascript" src="${ctx}/js/jquery-1.7.2.min.js"></script> <!-- uiToTop implementation -->
<script type="text/javascript" src="${ctx}/js/custom-scripts.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.tipsy.js"></script> <!-- Tipsy -->

<script type="text/javascript">

$(document).ready(function(){
			
	universalPreloader();
						   
});

$(window).load(function(){

	//remove Universal Preloader
	universalPreloaderRemove();
	
	rotate();
    dogRun();
	dogTalk();
	
	//Tipsy implementation
	$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
						   
});

</script>


<title>404 - Not found</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>

<body>

<!-- Universal preloader -->
<div id="universal-preloader">
    <div class="preloader">
        <img src="${ctx}/image/universal-preloader.gif" alt="universal-preloader" class="universal-preloader-preloader" />
    </div>
</div>
<!-- Universal preloader -->

<div id="wrapper">
<!-- 404 graphic -->
	<div class="graphic"></div>
<!-- 404 graphic -->
<!-- Not found text -->
	<div class="not-found-text">
    	<h1 class="not-found-text">File not found, sorry!</h1>
    </div>
<!-- Not found text -->

<!-- search form -->
<div class="search">
	<form name="search" method="get" action="${ctx}/user/user_search.action" />
        <input type="text" name="condition" value="Search ..." />
        <input class="with-tooltip" title="Search!" type="submit" name="submit" value="" />
    </form>
</div>
<!-- search form -->

<!-- top menu -->
<div class="top-menu">
	<a href="#" class="with-tooltip" title="Return to the home page">Home</a> | <a href="#" class="with-tooltip" title="Navigate through our sitemap">Sitemap</a> | <a href="#" class="with-tooltip" title="Contact us!">Contact</a> | <a href="#" class="with-tooltip" title="Request additional help">Help</a>
</div>
<!-- top menu -->

<div class="dog-wrapper">
<!-- dog running -->
	<div class="dog"></div>
<!-- dog running -->
	
<!-- dog bubble talking -->
	<div class="dog-bubble">
    	
    </div>
    
    <!-- The dog bubble rotates these -->
    <div class="bubble-options">
    	<p class="dog-bubble">
        	找不到页面了吧，哈哈
        </p>
    	<p class="dog-bubble">
	        <br />
        	好好想想！好好想想！
        </p>
        <p class="dog-bubble">
        	<br />
        	别着急！
        </p>
        <p class="dog-bubble">
        	要来一片曲奇么？<br /><img style="margin-top:8px" src="${ctx}/image/cookie.png" alt="cookie" />
        </p>
        <p class="dog-bubble">
        	<br />
        	天啊！好烦！
        </p>
        <p class="dog-bubble">
        	<br />
        	要关闭页面么？
        </p>
        <p class="dog-bubble">
        	或者，我就这么一直转？
        </p>
        <p class="dog-bubble">
        	<br />
            我找不到页面了！
        </p>
        <p class="dog-bubble">
        	我觉的我看到了一只喵~ <br /><img style="margin-top:8px" src="${ctx}/image/cat.png" alt="cat" />
        </p>
        <p class="dog-bubble">
        	我们在找什么? @_@
        </p>
    </div>
    <!-- The dog bubble rotates these -->
<!-- dog bubble talking -->
</div>

<!-- planet at the bottom -->
	<div class="planet"></div>
<!-- planet at the bottom -->
</div>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
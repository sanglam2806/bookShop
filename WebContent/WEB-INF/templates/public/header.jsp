<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Shop Japanese Book</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="${define.urlPublic}/css/images/favicon.ico" />
	<link rel="stylesheet" href="${define.urlPublic}/css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="${define.urlPublic}/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="${define.urlPublic}/js/jquery.jcarousel.min.js"></script>
	<!--[if IE 6]>
		<script type="text/javascript" src="js/png-fix.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${define.urlPublic}/js/functions.js"></script>
	<script type="text/javascript" src="${define.urlPublic}/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${define.urlPublic}/js/jquery.validate.min.js"></script>
	
</head>
<body>
	<!-- Header -->
	<div id="header" class="shell">
		<div id="logo"><h1><a href="${pageContext.request.contextPath}">Japanese Book</a></h1><span></span></div>
		<!-- Navigation -->
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath}" class="${index}">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/admincp">Quản lý</a></li>
				<li><a href="${pageContext.request.contextPath}/buy" class="${buy}" >Giỏ hàng</a></li>
				<li><a href="${pageContext.request.contextPath}/bill" class="${bill}" >Hóa đơn đã mua</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">logout</a></li>

			</ul>
		</div>

		<!-- End Navigation -->
		<div class="cl">&nbsp;</div>
		<!-- Login-details -->
		<div id="login-details">
		
			<c:if test="${objUser.fullname == null}">
				<p>Welcome, <a href="${pageContext.request.contextPath}/auth/login" id="user">Login here</a> .</p>
			</c:if>
			<c:if test="${objUser.fullname != null}">
				<p>Welcome, <a href="${pageContext.request.contextPath}/bill" id="user">${objUser.fullname}</a> .</p>
			</c:if>
			<p id="buy">
				<a href="${pageContext.request.contextPath}/buy" class="cart" >
					<img src="${define.urlPublic}/css/images/cart-icon.png" alt="" /></a>
					<c:if test="${listBook != null }">
						Shopping Cart (${book}) <a href="${pageContext.request.contextPath}/buy" class="sum">
						${tong}đ
						</a>
				</c:if>
			</p>
		</div>
		<!-- End Login-details -->
	</div>
	<!-- End Header -->
	<!-- Slider -->
	<c:if test="${type == 'public' }">
	<div id="slider">
		<div class="shell">
			<ul>
			<c:forEach var="item" items="${listSell}">
				<li>
					<div class="image">
						<img style='padding-left:40px;' src="${pageContext.request.contextPath}/files/${item.picture}" alt=""  width="250px" height="280px"  />
					</div>
					<div class="details">
						<h2>ゴック</h2><br/>
						<h3>バオ　ゴック</h3>
						<p class="title">${item.name}</p>
						<a href="${pageContext.request.contextPath}/detail/${item.id_book}" class="read-more-btn">Read More</a>
					</div>
				</li>
			</c:forEach>
				
			</ul>
			<div class="nav">
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
			</div>
		</div>
	</div>
	</c:if>
	<!-- End Slider -->
	
	<script>
         function setActive(id_book){
       		$.ajax({
       			url: '<%=request.getContextPath() %>/setActive',
       			type: 'POST',
       			cache: false,
       			data: {id:id_book},
       			success: function(data){
       				$('#buy').html(data);
       			},
       		});
       		return false;
       	}
</script>  
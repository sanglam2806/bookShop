<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- start: Header -->

<div class="container-fluid-full">
	<div class="row-fluid">
		<!-- start: Main Menu -->
		<div id="sidebar-left" class="span2">
			<div class="nav-collapse sidebar-nav">
				<ul class="nav nav-tabs nav-stacked main-menu">
					<li class="${cat}"><a
						href="${pageContext.request.contextPath}/admincp/cat"><i
							class="icon-bar-chart"></i><span class="hidden-tablet">
								Danh mục sách</span></a></li>
					<li class="${user}"><a
						href="${pageContext.request.contextPath}/admincp/user"><i
							class="icon-envelope"></i><span class="hidden-tablet">User</span></a></li>
					<li class="${book}"><a
						href="${pageContext.request.contextPath}/admincp/book"><i
							class="icon-tasks"></i><span class="hidden-tablet"> Book</span></a></li>
					<li class="${role}"><a
						href="${pageContext.request.contextPath}/admincp/role"><i
							class="icon-eye-open"></i><span class="hidden-tablet">
								Chức năng người dùng</span></a></li>
					<li class="${bill}"><a
						href="${pageContext.request.contextPath}/admincp/bill"><i
							class="icon-dashboard"></i><span class="hidden-tablet">
								Đơn hàng</span></a></li>
					<li><a onclick="return confirm('Bạn muốn logout?')"
						href="${pageContext.request.contextPath }/logout"><i
							class="icon-lock"></i><span class="hidden-tablet"> Logout
								Page</span></a></li>

				</ul>
			</div>
		</div>
		<!-- end: Main Menu -->
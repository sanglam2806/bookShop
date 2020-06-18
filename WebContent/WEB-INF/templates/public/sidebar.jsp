<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Main -->
	<div id="main" class="shell">
<!-- Sidebar -->
		<div id="sidebar">
			<ul class="categories">
				<li>
					<h4>Danh mục sách</h4>
					<ul>
					<c:forEach var="item" items="${listCat}">
						<li><a href="${pageContext.request.contextPath}/cat/${item.id}">${item.name}</a></li>
					</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<!-- End Sidebar -->
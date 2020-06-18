<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
		
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			<div class="products">
				<h3>${objcat.name}</h3>
				<ul>
				<c:forEach var="item" items="${listBook}">
					<li>
						<div class="product">
							<a href="${pageContext.request.contextPath}/detail/${item.id_book}" class="info">
								<span class="holder">
									<img src="${pageContext.request.contextPath}/files/${item.picture}" alt="" />
									<span class="book-name">${item.name}</span>
								</span>
							</a>
							<a href="javascript:void(0)" onclick="return setActive(${item.id_book}, alert('Đã thêm sản phẩm vào giỏ hàng của bạn'))" class="buy-btn">BUY<span class="price"><span class="low"></span>${item.cost}đ</span></a>
						</div>
					</li>
				</c:forEach>
					
				</ul>
			<!-- End Products -->
			</div>
			<div class="cl">&nbsp;</div>
			<!-- Best-sellers -->
			
			<!-- End Best-sellers -->
			
			<div class="phan-trang" style=" position:relative;left:300px">
			 <c:if test="${currentPage-1 >0 and currentPage <= sumPage}">
					<li><a href="${pageContext.request.contextPath}/cat/${idcat}?page=${currentPage-1}">Pre</a></li>
			</c:if>
				<c:forEach var="sheet" begin="1" end="${sumPage}">
					<c:if test="${sheet == currentPage}">
						<a class="active" href="${pageContext.request.contextPath}/cat/${idcat}?page=${sheet}" >${sheet}</a>
					</c:if>	
					<c:if test="${sheet != currentPage}">
						<a href="${pageContext.request.contextPath}/cat/${idcat}?page=${sheet}" >${sheet}</a>
					</c:if>	
				</c:forEach>	
			 <c:if test="${currentPage >0 and currentPage+1 <= sumPage}">
					<li><a href="${pageContext.request.contextPath}/cat/${idcat}?page=${currentPage+1}">Next</a></li>
			  </c:if>
			</div>
		</div>
		
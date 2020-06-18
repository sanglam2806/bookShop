<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Content -->
<div id="content">
	<!-- Products -->
	<div class="products">
		<h3>
			Thông tin sách
			<h3 />
			<span style="font-size: 14px; color: green">${msg }</span>
			<form action="${pageContext.request.contextPath }/addProduct/${objbook.id_book}"  method="post">
			<div class="trai">
				<img src="${pageContext.request.contextPath}/files/${objbook.picture}" alt="" width="350px" height="380px"/>
			</div>
			<div class="phai">
				<p>${objbook.name}</p>
				<p>
					<span style="font-size: 16px; color: black">Giá bán</span> : ${objbook.cost }
					<p />
					<p>
						<span style="font-size: 15px; color: black; top: 10px; position: relative">
						Số lượng:</span> 
						<input style="height: 20px; width: 80px; font-size: 18px; position: relative; top: 5px;"
							type="number" name="qty" value=1 class="input-text qty-input" min=1>
					<p />
						<span>
							<button style="color: white; background-color: #37CDE8; border: 1px solid #37CDE8;color: white; background-color: #37CDE8; border: 1px solid #37CDE8; border-radius: 5px; font-size: 16px; font-weight: bold; top: 20px; right: 1px; padding: 2px; position: relative; bottom: 1px; margin-right: 100px" type="submit">Thêm vào giỏ </button>
						</span>
			</div>
			</form>
			<!-- End Products -->
	</div>
	<div class="cl">&nbsp;</div>
	<!-- Best-sellers -->
	<div id="best-sellers">
		<h3>Best Sellers</h3>
		<ul>
		<c:forEach var="item" items="${listSell}">
			<li>
				<div class="product">
					<a href="${pageContext.request.contextPath}/detail/${item.id_book}"> 
						<img src="${pageContext.request.contextPath}/files/${item.picture}" alt="" />
						<span class="book-name">${item.name} </span> 
						<span class="price">
						  <span class="low"></span>${item.cost/1000}
						  <span class="high">k</span>
						</span>
					</a>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
	<!-- End Best-sellers -->
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			
			<div class="cl">&nbsp;</div>
			<!-- Best-sellers -->
			<div id="best-sellers">
				<h3>Lịch sử mua hàng:</h3>
				<form action="">
				<table id="reset" border='1' style="text-align:center;border-collapse:collapse;width: 638px">
					<tr>
						<th style='font-size:16px;height:30px;background:#37CDE8'>Số hóa đơn</th>
						<th style="font-size:16px;height:30px;background:#37CDE8">Thời gian</th>
						<th style="font-size:16px;height:30px;background:#37CDE8">Tổng tiền</th>
					</tr>
					<c:forEach var="item" items="${listBill}">
						<tr >
							<td style="font-size:14px;height:30px">
								<a href="${pageContext.request.contextPath}/detailbill/${item.id}">${item.id}</a>
							</td>
							<td style="font-size:14px;height:30px"><a href="${pageContext.request.contextPath}/detailbill/${item.id}">${item.date_create}</a></td>
							<td style="font-size:14px;height:30px"><a href="${pageContext.request.contextPath}/detailbill/${item.id}">${item.money}</a></td>
						</tr>
					</c:forEach>
				</table>
				</form>
			</div>
			<!-- End Best-sellers -->
		</div>
		<!-- End Content -->
		

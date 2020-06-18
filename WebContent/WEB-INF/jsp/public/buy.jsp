<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			
			<div class="cl">&nbsp;</div>
			<!-- Best-sellers -->
			<div id="best-sellers">
				<h3>Giỏ hàng:</h3>
				<form action="">
				<table id="reset" border='1' style="text-align:center;border-collapse:collapse">
					<tr>
						<th style='font-size:16px;height:30px;background:#37CDE8;width: 330px'>Tên sách:</th>
						<th style="font-size:16px;height:30px;background:#37CDE8;width: 90px">Giá</th>
						<th style="font-size:16px;height:30px;background:#37CDE8;width: 90px">Số lượng</th>
						<th style="font-size:16px;height:30px;background:#37CDE8;width: 130px">
							Thành tiền
						</th>
					</tr>
					<c:forEach var="item" items="${listBook}">
						<tr >
							<td style="font-size:14px;height:30px">${item.name}</td>
							<td style="font-size:14px;height:30px">${item.cost}</td>
							<td style="font-size:14px;height:30px">
								<input style="height:15px;width:60px;font-size:12px;" type="number" name="qty" id="${item.id_book}" onchange="return  resetBuy(${item.id_book})" value= '${idsach[item.id_book]}' class="input-text qty-input" min=0>
							</td>
							<td style="font-size:14px;height:30px">${item.cost* idsach[item.id_book]}</td>
						</tr>
					</c:forEach>
					<tr>
						<td style="font-size:15px;height:30px;font-weight:bold" colspan='3'>Tổng</td>
						<td style="font-size:15px;height:30px;font-weight:bold">${tong}</td>
					</tr>
				</table>
				
					<span style="font-size:16px;font-weight:bold">
						<a href="${pageContext.request.contextPath}/infor" style="float:right;top10px;position: relative"> Thanh toán </a>
					</span>
				</form>
			</div>
			<!-- End Best-sellers -->
		</div>
		<!-- End Content -->
		
<script>
         function resetBuy(id){
        	 var qty=$('#'+id).val();
        
       		$.ajax({
       			url: '<%=request.getContextPath() %>/resetBuy',
       			type: 'POST',
       			cache: false,
       			data: {
       				idBook:id,
       				soluong:qty,
       			},
       			success: function(data){
       				$('#reset').html(data);
       			},
       		});
       		return false;
       	}
</script>  
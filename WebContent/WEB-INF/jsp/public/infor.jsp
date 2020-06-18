<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
		<!-- Content -->
		<div id="content">
			<!-- Products -->
			
			<div class="cl">&nbsp;</div>
			<!-- Best-sellers -->
			<div id="best-sellers">
				<h3>Thông tin khách hàng ${msg}</h3>
				
				<form action="${pageContext.request.contextPath}/infor" method="POST" id="frmLogin">
					<table border='1' style="border-collapse:collapse">
						<tr>
							<th colspan="2" style="text-align:center;font-size:16px;height:30px;background:#37CDE8;width: 700px">Điền thông tin khách hàng</th>
						
						</tr>
						<tr>
							<td valign="center" style="font-size:14px;height:30px;font-weight:bold;padding-left:4px">Tên khách hàng</td>
							<td valign="center" style="font-size:14px;height:30px;font-weight:bold;padding-left:13px">${objUser.fullname}</td>
						
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:5px" >Địa chỉ</td>
							<td style="font-size:15px;height:40px;font-weight:bold">
								<input  style="height:25px;width:200px;margin-left:13px;font-size:14px" type="text" value="" name="address" />
								<span style="color: red;padding-left: 32px"><strong><form:errors path="objItem.address" ></form:errors></strong></span>
							</td>
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:4px" >Thành phố</td>
							<td style="font-size:15px;height:40px;font-weight:bold">
								<input style="height:25px;width:200px;margin-left:13px;font-size:14px" type="text" value="" name="city" />
								<span style="color: red;padding-left: 32px"><strong><form:errors path="objItem.city" ></form:errors></strong></span>
							</td>
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:4px" >Số điện thoại</td>
							<td style="font-size:15px;height:40px;font-weight:bold">
								<input style="height:25px;width:200px;margin-left:13px;font-size:14px" type="text" value="" name="phone" />
								<span style="color: red;padding-left: 32px"><strong><form:errors path="objItem.phone" ></form:errors></strong></span>
							</td>
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:4px" >Ghi chú</td>
							<td style="font-size:15px;height:40px;font-weight:bold">
								<input style="height:25px;width:200px;margin-left:13px;font-size:14px" type="text" value="" name="note" />
							</td>
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:4px" >Số tiền</td>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:13px">
								${tong}đ
							</td>
						</tr>
						<tr>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:4px" ></td>
							<td style="font-size:15px;height:40px;font-weight:bold;padding-left:13px">
								<input style="font-size:15px;padding:5px;background:#00D8D6;color: white;border: 1px solid #00D8D6;border-radius:5px;font-weight:bold" type="submit" value="Xác nhận" name="submit" />
							</td>
						</tr>
					</table>
				</form>

				
			</div>
			<!-- End Best-sellers -->
		</div>
		<!-- End Content -->
		
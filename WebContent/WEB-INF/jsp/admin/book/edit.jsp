<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<!-- start: Header -->
				
		<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
		</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="index.html">Home</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">Book</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white edit"></i><span class="break"></span>Thêm book</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<span style="color: red"><strong>${msg}</strong></span>
						<form class="form-horizontal" action="${pageContext.request.contextPath }/admincp/book/edit/${objBook.id_book}" method="post" enctype="multipart/form-data">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput" >Tên sách</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="name" id="focusedInput" type="text" value="${objBook.name}"  placeholder="tên" >
								<p><span style="color: red"><strong><form:errors path="objItem.name" ></form:errors></strong></span></p>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput" >Số lượng</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="quantity" id="focusedInput" type="text" value="${objBook.quantity}"  placeholder="số lượng" >
								<p><span style="color: red"><strong><form:errors path="objItem.quantity" ></form:errors></strong></span></p>
								</div>
							  </div>
							   <div class="control-group">
								<label class="control-label" for="focusedInput" >Giá bán</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="cost" id="focusedInput" type="text" value="${objBook.cost}"  placeholder="giá" >
								<p><span style="color: red"><strong><form:errors path="objItem.cost" ></form:errors></strong></span></p>
								</div>
							  </div>
							    <div class="control-group">
								<label class="control-label" for="focusedInput" >Hình ảnh</label>
								<div class="controls">
								<img src="${pageContext.request.contextPath}/files/${objBook.picture}" alt="${objBook.picture}" width="50px" height="50px" />
								  <input class="input-xlarge focused" name="hinhAnh" id="focusedInput" type="file" value=""  >
									
								</div>
							  </div>
							   <div class="control-group">
								<label class="control-label" for="focusedInput" >Danh mục</label>
								<div class="controls">
								  <select name="id_cat">
								  <c:forEach var="item" items="${listCAT}" >
								   <c:choose>
			                                <c:when  test="${objBook.id_cat == item.id }">
				                                <c:set var="slt" scope = "session" value="selected"></c:set> 
			                                </c:when>
			                                <c:otherwise ><c:set var="slt" scope = "session" value=""></c:set>  </c:otherwise>
		                               </c:choose>
	                                <option ${slt} value="${item.id}">${item.name}</option>
	                               </c:forEach>
                                </select>
								</div>
							  </div>
							  
							  <div class="form-actions">
								<button type="submit" class="btn btn-primary">Sửa</button>
								<button class="btn">Hủy</button>
							  </div>
							</fieldset>
						  </form>
					
					</div>
				</div><!--/span-->
			
			</div><!--/row-->

	
		
	


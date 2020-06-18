<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have 
					   <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> 
					   enabled to use this site.
					</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>Sách</li>
			</ul>
			<form id="frmTimKiem" action="${pageContext.request.contextPath }/admincp/book/search" method="POST">
			     Tên sách 	
				<input type="search" name="searchName" placeholder="Tên sách" size="34" value="" />
			     Danh mục:
				<select name="searchDM" style="max-width:200px">
					<option value="">Tất cả danh mục</option>
					<c:forEach var="objItemCat" items="${listc}">
						<option value="${objItemCat.id}">${objItemCat.name }</option>
					</c:forEach>
				</select>
			<input type="submit" id="btnSearch" name="search" value="Tìm kiếm" /> 
			<br /><br />
		</form>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>Sách</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<div class="float-left">
					<p><span style="color: red"><strong>${msg}</strong></span></p>
                      <a href="${pageContext.request.contextPath}/admincp/book/add" class="button">
                      	<span>Thêm sách</span>
                      </a>
                  </div>
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>ID</th>
								  <th>Tên sách</th>
								  <th>Tên danh mục </th>
								  <th>Số lượng</th>
								  <th>Đã bán</th>
								  <th>Giá</th>
								  <th>Hình ảnh</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach var="objb" items="${listCat}">
							<c:set var="urlEdit" value="${pageContext.request.contextPath}/admincp/book/edit/${objb.id_book}"></c:set>
                            <c:set var="urlDel" value="${pageContext.request.contextPath}/admincp/book/del/${objb.id_book}"></c:set>
							<tr>
								<td>${objb.id_book}</td>
								<td class="center">${objb.name}</td>
								<td class="center">${objb.cat_name}</td>
								<td class="center">${objb.quantity}</td>
								<td class="center">${objb.sell}</td>
								<td class="center">${objb.cost}</td>
								<td class="center">
									<img src="${pageContext.request.contextPath}/files/${objb.picture}" alt="${objb.picture}" width="50px" height="50px" />
								</td>
								
								<td class="center">
									<a class="btn btn-info" href="${urlEdit}">
										<i class="halflings-icon white edit"></i>  
									</a>
									<a onclick="return confirm('Bạn có chắc xóa')" class="btn btn-danger" href="${urlDel}">
										<i class="halflings-icon white trash"></i> 
									</a>
								</td>
							</tr>
							</c:forEach>
						  </tbody>
					  </table> 
					   <div class="pagination pagination-centered">
						  <ul>
							  <c:if test="${currentPage-1 >0 and currentPage <= sumPage}">
							<li><a href="${pageContext.request.contextPath}/admincp/book?page=${currentPage-1}">Pre</a></li>
						  </c:if>
							
							
							 
						<c:forEach var="sheet" begin="1" end="${sumPage}">
							<c:if test="${sheet == currentPage}">	
								<li class="active">
							 		 <a href="${pageContext.request.contextPath}/admincp/book?page=${sheet}">${sheet}</a>
								</li>
							</c:if>	
							<c:if test="${sheet != currentPage}">	
								<li>
							 		 <a href="${pageContext.request.contextPath}/admincp/book?page=${sheet}">${sheet}</a>
								</li>
							</c:if>	
						</c:forEach>	
                       
							<c:if test="${currentPage >0 and currentPage+1 <= sumPage}">
								<li><a href="${pageContext.request.contextPath}/admincp/book?page=${currentPage+1}">Next</a></li>
						  </c:if>
						  </ul>
						</div>                
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
	

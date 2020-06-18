<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> enabled to use this site.</p>
				</div>
			</noscript>
			
			<!-- start: Content -->
			<div id="content" class="span10">
			
			
			<ul class="breadcrumb">
				<li>Danh mục sách</li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>Danh mục sách</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<div class="float-left">
					<p><span style="color: red"><strong>${msg}</strong></span></p>
                      <a href="${pageContext.request.contextPath}/admincp/cat/add" class="button">
                      	<span>Thêm danh mục</span>
                      </a>
                  </div>
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>ID</th>
								  <th>Tên mục sách</th>
								  <th>Actions</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach var="objc" items="${listCat}">
							<c:set var="urlEdit" value="${pageContext.request.contextPath}/admincp/cat/edit/${objc.id}"></c:set>
                            <c:set var="urlDel" value="${pageContext.request.contextPath}/admincp/cat/del/${objc.id}"></c:set>
							<tr>
								<td>${objc.id}</td>
								<td class="center">${objc.name}</td>
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
							<li><a href="${pageContext.request.contextPath}/admincp/cat?page=${currentPage-1}">Pre</a></li>
						  </c:if>
							
							 
						<c:forEach var="sheet" begin="1" end="${sumPage}">
							<c:if test="${sheet == currentPage}">	
								<li class="active">
							 		 <a href="${pageContext.request.contextPath}/admincp/cat?page=${sheet}">${sheet}</a>
								</li>
							</c:if>	
							<c:if test="${sheet != currentPage}">	
								<li>
							 		 <a href="${pageContext.request.contextPath}/admincp/cat?page=${sheet}">${sheet}</a>
								</li>
							</c:if>	
						</c:forEach>	
                       
							
						  <c:if test="${currentPage >0 and currentPage+1 <= sumPage}">
								<li><a href="${pageContext.request.contextPath}/admincp/cat?page=${currentPage+1}">Next</a></li>
						  </c:if>
						  </ul>
						</div>                
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
	

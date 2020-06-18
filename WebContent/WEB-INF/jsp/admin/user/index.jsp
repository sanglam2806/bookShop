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
			<li>Người dùng</li>
		</ul>

		<div class="row-fluid sortable">		
			<div class="box span12">
				<div class="box-header" data-original-title>
					<h2><i class="halflings-icon white user"></i><span class="break"></span>Danh sách user</h2>
					<div class="box-icon">
					</div>
				</div>
				<div class="box-content">
				<div class="float-left">
				<p><span style="color: red"><strong>${msg}</strong></span></p>
                  <a href="${pageContext.request.contextPath}/admincp/user/add" class="button">
                  	<span>Thêm user</span>
                  </a>
              </div>
					<table class="table table-striped table-bordered bootstrap-datatable datatable">
					  <thead>
						  <tr>
							  <th>ID</th>
							  <th>username</th>
							  <th>fullname</th>
							  <th>roll</th>
							  <th>active</th>
							  <th>Actions</th>
						  </tr>
					  </thead>   
					  <tbody>
						<c:forEach var="obju" items="${listUser}">
						<c:set var="urlEdit" value="${pageContext.request.contextPath}/admincp/user/edit/${obju.id}"></c:set>
                        <c:set var="urlDel" value="${pageContext.request.contextPath}/admincp/user/del/${obju.id}"></c:set>
						<tr>
							<td>${obju.id}</td>
							<td class="center">${obju.username}</td>
							<td class="center">${obju.fullname}</td>
							<td class="center">${obju.role_name}</td>
							<td id="trangthaiTT-${obju.id }">
                                    <a href="javascript:void(0)" onclick="return setActive(${obju.id},${obju.active })">
                                    <c:choose>
                                    	<c:when test="${obju.active==1 }">
                                    		<img src="${pageContext.request.contextPath}/templates/admin/img/tick-circle.gif" alt="tick-circle" />
                                    	</c:when>
                                    	<c:otherwise>
                                    		<img src="${pageContext.request.contextPath}/templates/admin/img/minus-circle.gif" alt="minus-circle" />
                                    	</c:otherwise>
                                    </c:choose>
                                    </a>
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
							<li><a href="${pageContext.request.contextPath}/admincp/user?page=${currentPage-1}">Pre</a></li>
						  </c:if>
						
						 
					<c:forEach var="sheet" begin="1" end="${sumPage}">
						<c:if test="${sheet == currentPage}">	
							<li class="active">
						 		 <a href="${pageContext.request.contextPath}/admincp/user?page=${sheet}">${sheet}</a>
							</li>
						</c:if>	
						<c:if test="${sheet != currentPage}">	
							<li>
						 		 <a href="${pageContext.request.contextPath}/admincp/user?page=${sheet}">${sheet}</a>
							</li>
						</c:if>	
					</c:forEach>	
                   
						
							
						  <c:if test="${currentPage >0 and currentPage+1 <= sumPage}">
								<li><a href="${pageContext.request.contextPath}/admincp/user?page=${currentPage+1}">Next</a></li>
						  </c:if>
					  </ul>
					</div>                
				</div>
			</div><!--/span-->
		
		</div><!--/row-->
		
		
<script>
         function setActive(id, active){
       		$.ajax({
       			url: '<%=request.getContextPath() %>/admincp/user/setActive',
       			type: 'POST',
       			cache: false,
       			data: {aid: id, aactive: active},
       			success: function(data){
       				$('#trangthaiTT-'+id).html(data);
       			},
       		});
       		return false;
       	}
</script>  

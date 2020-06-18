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
				<li>Chi tiết hóa đơn</li>
			</ul>

			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white user"></i><span class="break"></span>Hóa đơn</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<div class="float-left">
                 	 </div>
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th>ID_Bill</th>
								  <th>Tên người mua</th>
								  <th>Tên sách</th>
								  <th>Số lượng</th>
								  <th>Tổng tiền</th>
							  </tr>
						  </thead>   
						  <tbody>
							<c:forEach var="objc" items="${listDetail}">
							<tr>
								<td>${objc.id_bill}</td>
								<td class="center">${objc.user_name}</td>
								<td class="center">${objc.book_name}</td>
								<td class="center">${objc.quantity}</td>
								<td class="center">${objc.total}</td>
							</tr>
							</c:forEach>
						  </tbody>
					  </table> 
					 
					</div>
				</div><!--/span-->
			
			</div><!--/row-->
	

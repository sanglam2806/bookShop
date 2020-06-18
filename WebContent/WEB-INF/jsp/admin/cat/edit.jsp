<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
				<li><a href="#">Dashboard</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white edit"></i><span class="break"></span>Thêm danh mục sách</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<span style="color: red"><strong>${msg}</strong></span>
						<form class="form-horizontal" action="${pageContext.request.contextPath }/admincp/cat/edit/${objc.id}" method="post">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput" >Danh mục</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="name" id="focusedInput" type="text" value="${objc.name}"  placeholder="Nhập danh mục" >
								<p><span style="color: red"><strong><form:errors path="objItem.name" ></form:errors></strong></span></p>
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

	
		
	


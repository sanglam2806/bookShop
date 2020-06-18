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
				<li><a href="#">User</a></li>
			</ul>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header" data-original-title>
						<h2><i class="halflings-icon white edit"></i><span class="break"></span>Sửa user</h2>
						<div class="box-icon">
						</div>
					</div>
					<div class="box-content">
					<span style="color: red"><strong>${msg}</strong></span>
						<form class="form-horizontal" action="${pageContext.request.contextPath }/admincp/user/edit/${obju.id}" method="post">
							<fieldset>
							  <div class="control-group">
								<label class="control-label" for="focusedInput" >Username</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="username" id="focusedInput" type="text" value="${obju.username}"  placeholder="Nhập username" >
								<p><span style="color: red"><strong><form:errors path="objItem.username" ></form:errors></strong></span></p>
								</div>
							  </div>
							  <div class="control-group">
								<label class="control-label" for="focusedInput" >Password</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="password" id="focusedInput" type="password" value="${obju.password}"  placeholder="Nhập mật khẩu" >
								<p><span style="color: red"><strong><form:errors path="objItem.password" ></form:errors></strong></span></p>
								</div>
							  </div>
							   <div class="control-group">
								<label class="control-label" for="focusedInput" >Fullname</label>
								<div class="controls">
								  <input class="input-xlarge focused" name="fullname" id="focusedInput" type="text" value="${obju.fullname}"  placeholder="Nhập fullname" >
								<p><span style="color: red"><strong><form:errors path="objItem.fullname" ></form:errors></strong></span></p>
								</div>
							  </div>
							   <div class="control-group">
								<label class="control-label" for="focusedInput" >Chức năng</label>
								<div class="controls">
								  <select name="role">
								  <c:forEach var="item" items="${listRoll}" >
								  <c:choose>
			                                <c:when  test="${obju.role == item.id }">
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

	
		
	


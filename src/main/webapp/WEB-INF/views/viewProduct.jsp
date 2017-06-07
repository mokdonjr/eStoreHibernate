<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container wrapper">

	<!-- angularjs 모듈 등록 -->
	<div class="container" ng-app="cartApp">
	
		<h2>Product Detail</h2>
		<p class="lead">Here is the detail information of the product</p>
		
		<!-- angularjs cartApp 모듈의 컨트롤러 생성 / scope에 메서드 등록 -->
		<div class="row" ng-controller="cartCtrl">
		
			<div class="col-md-6">
				<img src="<c:url value="/resources/images/${product.imageFileName }"/>" 
					style="width:80%"/>
			</div>
			
			<div class="col-md-6">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p><strong>Manufacturer : </strong> ${product.manufacturer }</p>
				<p><strong>Category : </strong> ${product.category }</p>
				<h4>${product.price} 원</h4>
				<br/>
				
				<!-- 사용자 로그인 체크 -->
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					
					<!-- 변수 설정 -->
					<c:set var="role" value="${param.role }"/>
					<c:set var="url" value="/products"/>
					
					<!-- 관리자인 경우 -->
					<c:if test="${role='admin' }">
						<c:set var="url" value="/admin/productInventory"/>
					</c:if>
					
					<p>
						<!-- 버튼1. 뒤로가기 -->
						<a href="<c:url value="${url }"/>" class="btn btn-default">Back</a>
						
						<!-- 버튼2. 장바구니에 넣기 / angularjs controller의 addToCart메서드 호출 -->
						<button class="btn btn-warning btn-large" ng-click="addToCart('${product.id}')">
							<span class="glyphicon glyphicon-shopping-cart"></span>
							Order Now
						</button>
						
						<!-- 버튼3. 장바구니 보기 -->
						<a href="<c:url value="/cart"/>" class="btn btn-default">
							<span class="glyphicon glyphicon-hand-right"></span>
							View Cart
						</a>
					</p>
					
				</c:if>
				
			</div>
			
		</div>
	</div>
</div>



<!-- <div class="container-wrapper">
	<div class="container">
		<div class="row">

			<div class="col-md-6">
			
				<p> <strong>Category</strong> : Home > ${product.category } </p>
				
				<h3>Product Details</h3>
				<p>Here is the detail information for product</p>
				
				<img src="<c:url value="/resources/images/${product.imageFileName }"/>"
					alt="image" style="width: 80%" />
			</div>

			<div class="col-md-6">
			
				<h3>${product.name }</h3>
				<p>${product.description }</p>
				<p> <strong>Price</strong> : ${product.price } </p>
				<p> <strong>Manufacturer</strong> : ${product.manufacturer } </p>
				<p> <strong>Unit In Stock</strong> : ${product.unitInStock } </p>
			</div>

		</div>
	</div>
</div>-->

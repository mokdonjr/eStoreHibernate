<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class="container-wrapper">

	<div class="container">
		
		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
		
		<section class="container" ng-app="cartApp">
		
			<!-- initCartId(cartId) 메서드 호출로 scope에 cart정보가 담겨져 있게 된다. -->
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<div class="padding">
					<!-- 누르면 clearCart() 메서드 호출 -->
					<a class="btn btn-danger pull-left" ng-click="clearCart()">
						<span class="glyphicon glyphicon-remove-sign"></span>Clear
					</a>
				</div>
				
				<table class="table table-hover">
					<tr>
						<th>Product</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
					</tr>
					<!-- angularjs 루프 -->
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td><!-- angularjs expression -->
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td>
							<!-- removeFromCart(productId) 메서드 호출 -->
							<a href="#" class="label label-danger" ng-click="removeFromCart(item.product.id)">
							<span class="glyphicon glyphicon-remove"></span>
							remove</a>
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
					</tr>
				</table>
				
				<a href="<c:url value="/products"/>" class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
	
</div>
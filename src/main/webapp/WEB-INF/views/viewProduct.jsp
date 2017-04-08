<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">
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
</div>

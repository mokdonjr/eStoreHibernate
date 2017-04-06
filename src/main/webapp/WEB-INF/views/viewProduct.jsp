<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-wrapper">
	<div class="container">
		<div class="row">

			<div class="col-md-6">
				<img
					src="<c:url value="/resources/images/${product.imageFileName }"/>"
					alt="image" style="width: 80%" />
			</div>

			<div class="col-md-6">
				<h3>${product.name }</h3>
				<p>${product.description }</p>
				<p>
					<strong>Price</strong> : ${product.price }
				</p>
				<p>
					<strong>Manufacturer</strong> : ${product.manufacturer }
				</p>
				<p>
					<strong>Category</strong> : ${product.category }
				</p>
				<p>
					<strong>Unit In Stock</strong> : ${product.category }
				</p>
			</div>

		</div>
	</div>
</div>

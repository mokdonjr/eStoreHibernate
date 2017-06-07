// 1. cartApp 모듈 생성
var cartApp = angular.module('cartApp', []);

// 2. 모듈에 cartCtrl controller 생성
cartApp.controller("cartCtrl", function($scope, $http){ // scope과 http객체 주입
	
	$scope.initCartId = function(cartId){ // method를 이용해 scope 설정
		$scope.cartId = cartId;
		$scope.refreshCart();
	};
	
	$scope.refreshCart = function(){
		
		// CartRestController의 getCartById 메서드 호출
		$http.get('/eStoreHibernate/rest/cart/' + $scope.cartId).then(
			function successCallback(response){
				$scope.cart = response.data; // response내 JSON으로 Cart가 전달된다.
			});
	};
	
	$scope.addToCart = function(productId){
		
		$scope.setCsrfToken(); // http header에 csrf token을 설정
		
		// CartRestController의 addItem 메서드 호출
		$http.put('/eStoreHibernate/rest/cart/add/' + productId).then(
			function successCallback(){ // 성공시
				alert("Product successfully added to the cart!");
			}, function errorCallback(){ // 실패시
				alert("Adding to the cart failed!");
			});
	};
	
	$scope.removeFromCart = function(productId){
		
		$scope.setCsrfToken();
		
		$http({
			method : 'DELETE',
			url : '/eStoreHibernate/rest/cart/cartitem/' + productId
		}).then(
				function successCallback(){
					$scope.refreshCart();
				}, function errorCallback(response){
					console.log(response.data);
				});
	};
	
	$scope.clearCart = function(){
		
		$scope.setCsrfToken();
		
		$http({
			method : 'DELETE',
			url : '/eStoreHibernate/rest/cart/' + $scope.cartId
		}).then(
				function successCallback(){
					$scope.refreshCart();
				}, function errorCallback(response){
					console.log(response.data);
				});
	};
	
	$scope.calGrandTotal = function(){
		var grandTotal = 0;
		
		for(var i=0; i<$scope.cart.cartItems.length; i++){
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}
		
		return grandTotal;
	};
	
	// layout.jsp 에 저장한 csrf 토큰 
	// <meta name="_csrf" content="${_csrf.token}"/>
	// <meta name="_csrf_header" content="${_csrf.headerName}"/>
	// '_csrf', '_csrf_header'값을 저장하고 http헤더에 설정
	$scope.setCsrfToken = function(){
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		
		$http.defaults.headers.common[csrfHeader] = csrfToken;
	}
	
});
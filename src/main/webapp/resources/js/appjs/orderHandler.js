roomExpApp.controller("ordersController", function($scope, $http) {

	var username = document.getElementById('loginUser').value;
	$scope.user = username;
	$scope.orders = [];
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}


	$scope.order = {
			orderId : undefined,
			tokenId : undefined,
			customerId : undefined,
			restaurantId : undefined,
			restaurantName : undefined,
			orderDetails : undefined,
			createdDate : formatDate(new Date()),
			modifiedDate : undefined,
			orderDate : undefined,
			orderAccept : false,
			activeOrder : false,
			username : username
	};

	loadingTheOrders();
	
	

	function loadingTheOrders() {
		$http({
			method : 'GET',
			url : '/order/getAllOrders'
		}).then(function successCallback(response) {
			$scope.orders = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}

	$scope.addOrder = function() {
		$http({
			method : 'POST',
			url : '/order/createOrder',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.order)
		}).then().success(function(data){
			loadingTheOrders();
			reset();
		});
	};
	
	$scope.editOrder = function(order) {
				$scope.order.orderId = order.orderId;
				$scope.order.tokenId = order.tokenId;
				$scope.order.customerId = order.customerId;
				$scope.order.restaurantId = order.restaurantId;
				$scope.order.restaurantName = order.restaurantName;
				$scope.order.orderDetails = order.orderDetails;
				$scope.order.createdDate = order.createdDate;
				$scope.order.modifiedDate = formatDate(new Date());
				$scope.order.orderDate = order.orderDate;
				$scope.order.orderAccept = order.orderAccept;
				$scope.order.activeOrder = order.activeOrder;
				$scope.order.username = order.username;
	}
	
	$scope.deleteOrder = function(order) {
		   $http({
		     method : 'POST',
		     url : '/order/deleteOrder',
		     data : angular.toJson(order),
		     headers : {
		     'Content-Type' : 'application/json'
		   }
		   }).then().success(function(data){
			   loadingTheOrders();  reset();
		   });
		  }

	function reset() {
		$scope.order.orderId = undefined;
		$scope.order.tokenId = undefined;
		$scope.order.customerId = undefined;
		$scope.order.orderDetails = undefined;
		$scope.order.createdDate = undefined;
		$scope.order.orderDate = undefined;
		$scope.order.orderAccept = undefined;
		$scope.order.activeOrder = undefined;
	}

});

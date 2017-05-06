roomExpApp.controller("menuItemsController", function($scope, $http) {

	var username = document.getElementById('loginUser').value;
	$scope.user = username;
	$scope.items = [];
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}


	$scope.item = {
		itemId : undefined,
		itemDescription : undefined,
		itemName : undefined,
		itemPrice : undefined,
		username : username,
		createdDate : formatDate(new Date()),
		active : undefined,
		available:undefined,
		updateDate : undefined,
		restaurantId : undefined,
		restaurantName : undefined
	};

	loadingTheMenuItems();
	
	

	function loadingTheMenuItems() {
		$http({
			method : 'GET',
			url : '/menu/items/getMenuItems'
		}).then(function successCallback(response) {
			$scope.items = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}

	$scope.addItem = function() {
		$http({
			method : 'POST',
			url : '/menu/items/saveMenu',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.item)
		}).then().success(function(data){
			loadingTheMenuItems();
			reset();
		});
	};

	$scope.editItem = function(item) {
				$scope.item.itemId = item.itemId;
				$scope.item.itemDescription = item.itemDescription;
				$scope.item.itemName = item.itemName;
				$scope.item.itemPrice = item.itemPrice;
				$scope.item.active = item.active;
				$scope.item.available = item.available;
				$scope.item.updateDate = formatDate(new Date());
				$scope.item.restaurantId = item.restaurantId;
	}
	
	$scope.deleteItem = function(item) {
		   $http({
		     method : 'POST',
		     url : '/menu/items/deleteMenu',
		     data : angular.toJson(item),
		     headers : {
		     'Content-Type' : 'application/json'
		   }
		   }).then().success(function(data){
			  loadingTheMenuItems();  reset();
		   });
		  }

	function reset() {
		$scope.item.itemId = undefined;
	    $scope.item.itemDescription = undefined;
		$scope.item.itemName = undefined; 
		$scope.item.itemPrice = undefined;
		$scope.item.active = undefined; 
		$scope.item.available = undefined;
	}

	
	function disableId(){
		document.getElementById("itemId").disabled = true;
	}

});

roomExpApp.controller("profileCtrl", function($scope, $http) {
	
	var username = document.getElementById('loginUser').value;

	$scope.updateUserInfoForm = undefined;
	
	
	//Button Processing Feature
	$scope.updateProfile = 'UpdateProfile';
	$scope.test = true;
	
	
	$scope.profile = {
		id : undefined,
		username : username,
		emailId : undefined,
		mobNum : undefined,
		address : undefined,
		restaurantName : undefined,
		restaurantName : undefined
	}

	$scope.updateUserInfoForm = function() {
		 $scope.enable = 'false';
	        $scope.test = 'true';
	        $scope.updateProfile = 'Updating';
		$http({
			method : 'PUT',
			url : '/updateProfile',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.profile)
		}).then(_success, _error);
	};

	loadProfile();
	
	function loadProfile() {
		console.log(username);
		$http({
			method 	: 'POST',
			url 	: '/getProfile',
			headers : {
						'Content-Type' : 'application/json'
					  },
			data 	: angular.toJson($scope.profile)
		}).then(function successCallback(response) {
			$scope.profile = response.data;
		}, function errorCallback(response) {
		});
	}
	

	function _success(response) {
		 $scope.enable = 'true';
         $scope.updateProfile = 'UpdateProfile';
		alert("success : " + response.statusText);
	}

	function _error(response) {
		 $scope.enable = 'true';
         $scope.updateProfile = 'UpdateProfile';
		alert("error : " + response.statusText);
	}

});

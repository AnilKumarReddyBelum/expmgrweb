roomExpApp.controller("notificatonManagerController", function($scope, $http) {

	var username = document.getElementById('loginUser').value;
	$scope.user = username;
	$scope.notifications = [];
	
	//Button Processing Feature
	$scope.sendNotificationText = 'UpdateNotification';
	$scope.test = true;
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return [year, month, day].join('-');
	}


	$scope.notification = {
			notificationId : undefined,
			message : undefined,
			createdDate : formatDate(new Date()),
			modifiedDate : undefined,
			estimationTime : undefined,
			active : false,
			orderId:undefined,
			username :username
	};

	loadingTheNotifications();
	
	

	function loadingTheNotifications() {
		$http({
			method : 'GET',
			url : '/notifications/getNotifications'
		}).then(function successCallback(response) {
			$scope.notifications = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}

	$scope.addNotification = function() {
		    $scope.enable = 'false';
	        $scope.test = 'true';
	        $scope.sendNotificationText = 'Updating';
		$http({
			method : 'POST',
			url : '/notifications/saveNotification',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.notification)
		}).then().success(function(data){
			 $scope.enable = 'true';
	         $scope.sendNotificationText = 'UpdateNotification';
			loadingTheNotifications();
			reset();
		});
	};
	


	$scope.editNotification = function(notification) {
				$scope.notification.notificationId = notification.notificationId;
				$scope.notification.message = notification.message;
				$scope.notification.createdDate = notification.createdDate;
				$scope.notification.modifiedDate = formatDate(new Date());
				$scope.notification.estimationTime = notification.estimationTime;
				$scope.notification.active = notification.active;
				$scope.notification.orderId = notification.orderId;
				$scope.notification.username = notification.username;
	}
	
	$scope.deleteNotification = function(notification) {
		   $http({
		     method : 'POST',
		     url : '/notifications/deleteNotification',
		     data : angular.toJson(notification),
		     headers : {
		     'Content-Type' : 'application/json'
		   }
		   }).then().success(function(data){
			   loadingTheNotifications();  reset();
		   });
		  }

	function reset() {
		$scope.notification.notificationId = undefined;
		$scope.notification.message = undefined;
		$scope.notification.createdDate = undefined;
		$scope.notification.modifiedDate = undefined;
		$scope.notification.estimationTime = undefined;
		$scope.notification.active = undefined;
		$scope.notification.orderId = undefined;
	}

	


});

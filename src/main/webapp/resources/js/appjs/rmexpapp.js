var roomExpApp = angular.module('expenseApp', [ 'ngRoute', 'ngGrid' ]);

var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

roomExpApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.withCredentials = true;
} ]);

roomExpApp.run([ '$http', function($http) {
	$http.defaults.headers.post[header] = token;
	$http.defaults.headers.put[header] = token;
	$http.defaults.headers.get[header] = token;
	$http.defaults.headers.delete[header] = token;
	$http.defaults.headers.tarce[header] = token;
	$http.defaults.headers.head[header] = token;
	$http.defaults.headers.options[header] = token;
	$http.defaults.headers.connect[header] = token;
} ]);

var appLogin = angular.module("appLogin", ["ngRoute"]);
appLogin.config(function ($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/forgot-pass", {
      templateUrl: "123.html",
    })
    .otherwise({
      templateUrl: "../view/page/form-login.html",
      controller: loginController,
    });
});

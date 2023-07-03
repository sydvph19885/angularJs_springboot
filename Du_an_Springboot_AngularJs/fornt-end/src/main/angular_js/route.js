var myApp = angular.module("myModule", ["ngRoute"]);
myApp.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/login", {
      templateUrl: "../view/page/login.html",
    })
    .when("/detail/:id", {
      templateUrl: "../view/page/detail.html",
      controller: homeController,
    })
    .otherwise({
      templateUrl: "../view/page/home.html",
      controller: homeController,
    });
});

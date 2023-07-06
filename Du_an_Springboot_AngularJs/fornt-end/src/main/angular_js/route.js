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
    .when("/delete/:id", {
      templateUrl: "../view/page/manager-product.html",
      controller: homeController,
    })
    .when("/quan-li-san-pham", {
      templateUrl: "../view/page/manager-product.html",
      controller: homeController,
    })
    .when("/cart", {
      templateUrl: "../view/page/cart.html",
      controller: cartController,
    })
    .when("/logout", {
      templateUrl: "../view/login.html",
      // controller: loginController,
    })
    .when("/login", {
      templateUrl: "../view/login.html",
    })
    .otherwise({
      templateUrl: "../view/page/home.html",
      controller: homeController,
    });
});

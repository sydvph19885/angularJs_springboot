var myApp = angular.module("myModule", ["ngRoute"])

myApp.config(function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix("");
    $routeProvider
        .when("/add", {
            templateUrl: "home.html",
            controller: homeController,
        })
        .when("/delete/:id", {
            templateUrl: "home.html",
            controller: homeController,
        })
        .when("/trang-chu", {
            templateUrl: "home.html",
            controller: homeController,
        })
        .otherwise({
            templateUrl: "home.html",
            controller: homeController,
        })
})
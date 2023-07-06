window.cartController = function ($scope, $routeParams, $http) {
  $scope.key = [];
  $scope.listCart = [];

  // if (sessionStorage.getItem("isLogin") == true) {
  $http
    .get("http://localhost:8081/api/cart/client/hien-thi")
    .then(function (response) {
      if (response.status === 200) {
        $scope.listCart = response.data;
        console.log(response.data);
        console.log("aaaaaaaaaa");
      }
    });
  // } else {
  //   $http
  //     .get("http://localhost:8081/api/cart/user/hien-thi")
  //     .then(function (response) {
  //       if (response.status === 200) {
  //         var objectCTSP = Object.entries(response.data);
  //         objectCTSP.forEach(function (entry) {
  //           $scope.key = entry[0];
  //           console.log(entry[1]);
  //         });
  //       }
  //     });
  // }
};

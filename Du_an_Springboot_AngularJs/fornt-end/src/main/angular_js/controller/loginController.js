window.loginController = function ($scope, $http, $routeParams) {
  $scope.account = {};
  $scope.email = "sydvph19885@fpt.edu.vn";
  $scope.login = function (event) {
    event.preventDefault();
    if ($scope.email == undefined || $scope.pass == undefined) {
      alert("Đang trống");
    } else {
      $http
        .get(
          "http://localhost:8081/api/login?email=" +
            $scope.email +
            "&pass=" +
            $scope.pass
        )
        .then(function (respose) {
          if (respose.status === 200) {
            $scope.account = respose.data;
            console.log("1111:" + $scope.account);
            alert("Login thành công");
            sessionStorage.setItem("isLogin", "true");
            window.location.href = "../view/trang-chu.html";
          }
        });
    }
  };
};

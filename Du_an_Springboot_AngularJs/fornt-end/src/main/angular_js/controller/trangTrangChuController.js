window.homeController = function ($scope, $http, $routeParams) {
  $scope.listSanPham = [];
  $scope.detail = {
    nhaSanXuat: "",
    mauSac: "",
    sanPham: "",
    dongSanPham: "",
    moTa: "",
    giaBan: "",
    size: "",
    voucher: "",
  };
  $http.get("http://localhost:8081/api/san-pham/hien-thi").then(
    function (reponse) {
      if (reponse.status === 200) {
        $scope.listSanPham = reponse.data.chiTietSPList;
        console.log(reponse.data);
      }
    },
    function (e) {
      console.log(e);
    }
  );
  $scope.search = function (event) {
    event.preventDefault();
    $http
      .get("http://localhost:8081/api/san-pham/search?name=" + $scope.tenSearch)
      .then(
        function (reponse) {
          $scope.listSanPham = reponse.data.chiTietSPList;
          console.log(reponse.data);
        },
        function (e) {
          console.log(e);
        }
      );
  };

  // $scope.detail = function (event) {
  //   event.preventDefault();
  //   alert("aa");
  $http
    .get("http://localhost:8081/api/san-pham/detail/" + `${$routeParams.id}`)
    .then(function (reponse) {
      if (reponse.status === 200) {
        $scope.detail = reponse.data;
      }
    });
  // };
};

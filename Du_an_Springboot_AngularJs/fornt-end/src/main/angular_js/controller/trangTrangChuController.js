window.homeController = function ($scope, $http, $routeParams, $rootScope) {
  $scope.listSanPham = [];
  $scope.listMauSac = [];
  $scope.listNSX = [];
  $scope.trangHT = 0;
  $scope.listCartUser = [];
  $scope.tongSoTrang = 0;
  $rootScope.checkLogin = sessionStorage.getItem("isLogin");
  console.log($scope.checkLogin);
  $scope.detail = {
    id: "",
    nhaSanXuat: {
      id: "",
      ma: "",
      ten: "",
    },
    mauSac: {
      id: "",
      ma: "",
      ten: "",
    },
    tenSanPham: "",
    moTa: "",
    giaBan: 0,
    giaNhap: 0,
    size: 0,
    voucher: 0,
    image: "",
    namBH: 0,
  };
  $scope.add = {
    id: "",
    nhaSanXuat: {
      id: "",
      ma: "",
      ten: "",
    },
    mauSac: {
      id: "",
      ma: "",
      ten: "",
    },
    tenSanPham: "",
    moTa: "",
    giaBan: 0,
    giaNhap: 0,
    size: 0,
    voucher: 0,
    image: "",
    namBH: 0,
    ngayNhap: new Date(),
  };

  // getALL
  $http.get("http://localhost:8081/api/san-pham/hien-thi").then(
    function (reponse) {
      if (reponse.status === 200) {
        $scope.listSanPham = reponse.data.chiTietSPList;
        $scope.trangHT = reponse.data.trangHienTai;
        $scope.tongSoTrang = reponse.data.tongSoTrang;
        $scope.listMauSac = reponse.data.mauSacList;
        $scope.listNSX = reponse.data.nhaSanXuatList;
        $scope.detail.mauSac.ten = $scope.detail.mauSac;
      }
    },
    function (e) {
      console.log(e);
    }
  );
  // search
  $scope.search = function (event) {
    event.preventDefault();
    $http
      .get("http://localhost:8081/api/san-pham/search?name=" + $scope.tenSearch)
      .then(
        function (reponse) {
          $scope.listSanPham = reponse.data.chiTietSPList;
          $scope.trangHT = reponse.data.trangHienTai;
          $scope.tongSoTrang = reponse.data.tongSoTrang;
          console.log(reponse.data);
        },
        function (e) {
          console.log(e);
        }
      );
  };
  // detail
  $http
    .get("http://localhost:8081/api/san-pham/detail/" + `${$routeParams.id}`)
    .then(function (reponse) {
      if (reponse.status === 200) {
        $scope.detail = reponse.data;
        console.log(reponse.data);
      }
    });
  // chuyen trang
  $scope.chuyenTrang = function (trangSo) {
    $http
      .get("http://localhost:8081/api/san-pham/hien-thi?page=" + trangSo)
      .then(
        function (reponse) {
          if (reponse.status === 200) {
            $scope.listSanPham = reponse.data.chiTietSPList;
            $scope.trangHT = reponse.data.trangHienTai;
            $scope.tongSoTrang = reponse.data.tongSoTrang;
            console.log(reponse.data);
          }
        },
        function (e) {
          console.log(e);
        }
      );
  };
  // next and previuos
  $scope.nextPage = function (event) {
    event.preventDefault();
    $scope.trangHT++;
    $scope.chuyenTrang($scope.trangHT);
  };

  $scope.previousPage = function (event) {
    event.preventDefault();
    $scope.trangHT--;
    $scope.chuyenTrang($scope.trangHT);
  };

  // save Nha san xuat
  $scope.nsx = {
    ma: Math.floor(Math.random() * 899999999 + 100000000),
    ten: "",
  };
  $scope.saveNsx = function (event) {
    event.preventDefault();
    $http
      .post("http://localhost:8081/api/nsx/add", $scope.nsx)
      .then(function (save) {
        $scope.listNSX.push(save.data);
        alert("Thành công");
        location.reload();
      });
  };
  // save mau sac
  $scope.saveMauSac = function (event) {
    event.preventDefault();
    $http
      .post("http://localhost:8081/api/mau-sac/add", $scope.nsx)
      .then(function (save) {
        $scope.listNSX.push(save.data);
        alert("Thành công");
        location.reload();
      });
  };
  // detail manager product
  $scope.detailManager = function (event, index) {
    $scope.detail = $scope.listSanPham[index];
    $scope.imagePath =
      "../../../../src/main/static/image/" + $scope.detail.image;
  };
  // save product

  $scope.saveProduct = function (event) {
    event.preventDefault();
    $scope.sanPham = {
      id: "",
      ma: Math.floor(Math.random() * 899999999 + 100000000),
      ten: $scope.add.sanPham,
    };
    try {
      if (
        $scope.add.nhaSanXuat === "" ||
        $scope.add.mauSac === "" ||
        $scope.add.tenSanPham === "" ||
        $scope.add.moTa === "" ||
        $scope.add.giaBan === "" ||
        $scope.add.giaNhap === "" ||
        $scope.add.size === "" ||
        $scope.add.voucher === "" ||
        $scope.add.namBH === ""
      ) {
        alert("Rỗng ở ô nào đó");
      } else {
        var imagName = document.getElementById("image").files[0].name;

        // add product
        $scope.add.image = imagName;
        console.log($scope.add);
        $scope.add.nhaSanXuat = JSON.parse($scope.add.nhaSanXuat);
        $scope.add.mauSac = JSON.parse($scope.add.mauSac);
        $http
          .post("http://localhost:8081/api/san-pham/add", $scope.add, {
            headers: { "Content-Type": "application/json" },
          })
          .then(
            function (add) {
              $scope.listSanPham.push(add.data);
              alert("Thành công!");
              location.reload();
            },
            function (e) {
              alert("Lỗi!");
              console.log(e);
            }
          );
      }
    } catch (e) {
      alert("Lỗi!");
      console.log(e);
      return false;
    }
  };
  // update
  $scope.updateProduct = function (event) {
    event.preventDefault();
    try {
      if (
        $scope.detail.nhaSanXuat === "" ||
        $scope.detail.mauSac === "" ||
        $scope.detail.tenSanPham === "" ||
        $scope.detail.moTa === "" ||
        $scope.detail.giaBan === "" ||
        $scope.detail.giaNhap === "" ||
        $scope.detail.size === "" ||
        $scope.detail.voucher === "" ||
        $scope.detail.namBH === ""
      ) {
        alert("Rỗng ở ô nào đó");
      } else {
        var imagName = document.getElementById("imgNamess").files[0].name;
        $scope.detail.image = imagName;
        $http
          .post("http://localhost:8081/api/san-pham/add", $scope.detail, {
            headers: { "Content-Type": "application/json" },
          })
          .then(
            function (add) {
              $scope.listSanPham.push(add.data);
              alert("Thành công!");
              location.reload();
            },
            function (e) {
              console.log(e);
            }
          );
      }
    } catch (e) {
      alert("Lỗi!");
      console.log(e);
      return false;
    }
  };
  // delete product
  $scope.delete = function (event, id) {
    $http
      .delete("http://localhost:8081/api/san-pham/delete/" + id)
      .then(function (reponse) {
        if (reponse.status === 200) {
          $scope.listSanPham = reponse.data.chiTietSPList;
          alert("Thành công");
          location.reload();
        }
      });
  };

  // xem mục
  $scope.pageSize = function () {
    $http
      .get("http://localhost:8081/api/san-pham/hien-thi?size=" + $scope.size)
      .then(
        function (reponse) {
          if (reponse.status === 200) {
            $scope.listSanPham = reponse.data.chiTietSPList;
            $scope.trangHT = reponse.data.trangHienTai;
            $scope.tongSoTrang = reponse.data.tongSoTrang;
            console.log(reponse.data);
          }
        },
        function (e) {
          console.log(e);
        }
      );
  };
  // add cart use
  $scope.cartUser = function (event, id) {
    event.preventDefault();
    $http
      .delete("http://localhost:8081/api/san-pham/delete/" + id)
      .then(function (reponse) {
        if (reponse.status === 200) {
          $scope.listSanPham = reponse.data;
        }
      });
  };
};

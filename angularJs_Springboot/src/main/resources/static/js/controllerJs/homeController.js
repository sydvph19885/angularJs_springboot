window.homeController = function ($scope, $http, $routeParams, $filter) {
    $scope.listProduct = [];
    $scope.trangSo = 0;
    $scope.tongTrang = 0;
    $scope.listadd = {
        productId: "",
        productName: "",
        price: 0,
        quantity: 0

    }

    $http.get("http://localhost:8081/api/home").then(
        function (response) {
            if (response.status === 200) {
                $scope.listProduct = response.data.products;
                $scope.trangSo = response.data.trangSo;
                console.log(response.data.products);
            }
        },
        function (e) {
            console.log(e)
        }
    )

    $scope.nextPage = function() {
        $scope.trangSo++;
        $http.get("http://localhost:8081/api/home"+"?page="+$scope.trangSo).then(
            function (response) {
                if (response.status === 200) {
                    $scope.listProduct = response.data.products;
                    $scope.trangSo = response.data.trangSo;
                    $scope.tongTrang = response.data.tongTrang;
                    console.log(response.data.products);
                }
            },
            function (e) {
                console.log(e)
            }
        )
        $scope.loadData();
    };

    $scope.previoutPage = function() {
        $scope.trangSo--;
        $http.get("http://localhost:8081/api/home"+"?page="+$scope.trangSo).then(
            function (response) {
                if (response.status === 200) {
                    $scope.listProduct = response.data.products;
                    $scope.trangSo = response.data.trangSo;
                    console.log(response.data.products);
                }
            },
            function (e) {
                console.log(e)
            }
        )
        $scope.loadData();
    };

    $http.delete("http://localhost:8081/api/delete/" + `${$routeParams.id}`).then(
        function (response) {
            if (response.status === 200) {
                $scope.listProduct = response.data;
                console.log(response.data);
            }
        }
    )
    $scope.addProduct = function (event) {
        event.preventDefault();
        $http.post("http://localhost:8081/api/add", $scope.listadd).then(
            function (save) {
                $scope.listProduct.push(save.data)
                alert('Thanh cong')
                location.reload();
            },
            function (e) {
                console.log(e);
            }
        )
    }
    $scope.viTri = -1;
    $scope.detail = function (event, index) {
        event.preventDefault();
        let pd = $scope.listProduct[index];
        $scope.listadd.productId = pd.productId;
        $scope.listadd.productName = pd.productName;
        $scope.listadd.price = pd.price;
        $scope.listadd.quantity = pd.quantity;
        $scope.viTri = index;
    }

    $scope.update = function (event) {
        event.preventDefault();
        if ($scope.viTri === -1) {
            alert('Chon dong')
        } else {
            $http.put("http://localhost:8081/api/update", $scope.listadd).then(
                function (response) {
                    $scope.listProduct[$scope.viTri] = response.data;
                    alert('OKE')
                    location.reload();
                }
            )

        }
    }

    $scope.search = function (event) {
        event.preventDefault();
        $http.get("http://localhost:8081/api/search/" + $scope.listadd.productName).then(
            function (response) {
                $scope.listProduct = response.data;
            }
        )
    }

}
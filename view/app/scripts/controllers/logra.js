'use strict';

app.controller('LograController', ['$scope', '$q', 'LograService', 'UFService',
    function ($scope, $q, LograService, UFService) {

        $scope.resultados;
        $scope.ufs;
        $scope.texto;

        $scope.findAuxiliar = function () {
            UFService.findAll().then(function (result) {
                console.log(result);
                $scope.ufs = result;
            });
        };

        $scope.findAuxiliar();
        $scope.get = function (uf, logra) {
            LograService.get(uf, logra).then(function () {
                $scope.resultados = data;
            });
        };
    }
]);

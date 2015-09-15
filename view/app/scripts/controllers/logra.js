'use strict';

app.controller('LograController', ['$scope', 'LograService', 'UFService',
    function($scope, LograService, UFService) {

        $scope.resultados;
        $scope.ufs;
        $scope.selected;
        $scope.texto;

        $scope.findAuxiliar = function() {
            UFService.findAll().then(function(result) {
                $scope.ufs = result;
            });
        };

        $scope.findAuxiliar();
        $scope.get = function(logra) {
            if ($scope.selected) {
                LograService.get($scope.selected, logra).then(function(data) {
                    $scope.resultados = data;
                });
            }
        };
    }
]);

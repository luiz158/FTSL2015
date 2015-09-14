'use strict';

app.controller('LograController', ['$scope', 'LograService',
    function($scope, LograService) {

        $scope.resultado;

        $scope.texto;

        $scope.get = function(texto) {
            LograService.get(texto).then(function(texto) {
                $scope.resultado = data[0];
            });
        };
    }
]);

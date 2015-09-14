'use strict';

app.controller('CEPController', ['$scope', 'CEPService',
    function($scope, CEPService) {

        $scope.resultado;

        $scope.texto;

        $scope.get = function(id) {
            CEPService.get(id).then(function(data) {
                $scope.resultado = data[0];
            });
        };
    }
]);

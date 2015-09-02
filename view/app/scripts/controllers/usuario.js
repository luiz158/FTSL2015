'use strict';

app.controller('UsuarioController', ['$q', '$scope', 'UsuarioService',
    function($q, $scope, UsuarioService) {

        $scope.membro;
        $scope.interessado;

        $scope.fasecountUtilizaveis;
        $scope.fasecountDeclinio;
        $scope.currentPageM = 1;
        $scope.numPerPageM = 10;
        $scope.currentPageI = 1;
        $scope.numPerPageI = 10;
        $scope.maxSize = 3;
        $scope.filteredMembro = [];
        $scope.filteredInteressado = [];

        $scope.bootstrap = function() {
            $q.all([UsuarioService.listaFaseInteressado(),
                UsuarioService.listaFaseMembro()])
                .then(function(result) {

                    $scope.membro = result[0];
                    $scope.interessado = result[1];

                    $scope.filteredMembro = $scope.membro.slice(0, 5);
                    $scope.filteredInteressado = $scope.interessado.slice(0, 5);

                }).then();
        };


        $scope.bootstrap();

        $scope.$watch("currentPageM + numPerPageM", function() {
            var begin = (($scope.currentPageM - 1) * $scope.numPerPageM)
                , end = begin + $scope.numPerPageM;

            $scope.filteredMembro = $scope.membro.slice(begin, end);
        });

        $scope.$watch("currentPageI + numPerPageI", function() {
            var begin = (($scope.currentPageI - 1) * $scope.numPerPageI)
                , end = begin + $scope.numPerPageI;

            $scope.filteredInteressado = $scope.interessado.slice(begin, end);
        });



    }]);

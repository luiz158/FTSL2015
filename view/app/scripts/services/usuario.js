'use strict';

app.factory('UsuarioService', ['$http', function($http) {

        var service = {};

        service.listaFaseInteressado = function() {
            return $http
                .get('api/bi/usuario/listaFaseInteressado')
                .then(function(res) {
                    return res.data;
                });
        };

        service.listaFaseMembro = function() {
            return $http
                .get('api/bi/usuario/listaFaseMembro')
                .then(function(res) {
                    return res.data;
                });
        };


        return service;
    }]);
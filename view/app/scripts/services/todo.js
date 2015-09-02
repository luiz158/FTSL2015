'use strict';

app.factory('TodoService', ['$http', function($http) {

        var service = {};

        service.getInfoUsuario = function() {
            return $http
                .get('api/todo')
                .then(function(res) {
                    return res.data;
                });
        };

        return service;
    }]);
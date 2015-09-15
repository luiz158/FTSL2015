'use strict';

app.factory('UFService', ['$http', '$q', function ($http, $q) {
        var service = {};

        service.findAll = function () {

            var deferred = $q.defer();

            $http({
                url: 'api/uf',
                method: "GET"
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        return service;
    }]);


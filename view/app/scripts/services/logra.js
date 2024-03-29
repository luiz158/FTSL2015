'use strict';

app.factory('LograService', ['$http', '$q', function ($http, $q) {
        var service = {};

        service.get = function (uf, logra) {

            var deferred = $q.defer();

            $http({
                url: 'api/log/' + uf + '/' + logra,
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


'use strict';

app.factory('CEPService', ['$http', '$q', function($http, $q) {
        var service = {};

        service.get = function(id) {

            var deferred = $q.defer();

            $http({
                url: 'api/cod/' + id,
                method: "GET"
            }).success(function(data) {
                deferred.resolve(data);
            }).error(function(data, status) {
                deferred.reject([data, status]);
            });

            return deferred.promise;
        };

        return service;
    }]);


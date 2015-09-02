'use strict';

app.factory('AuthService', ['$http', '$rootScope',
    function($http, $rootScope) {

        var authService = {};

        authService.login = function(credentials) {

            return $http
                .post('api/auth', credentials)
                .success(function(res) {

                    $rootScope.currentUser = res.data;

                    return res;
                }
                );

        };



        authService.logout = function() {
            $rootScope.currentUser = null;
        };

        authService.isAuthenticated = function() {
            return $rootScope.currentUser ? true : false;
        };

        return authService;
    }]);


'use strict';

var app = angular.module('viewApp', [
    'ngAria',
    'ngMessages',
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngAnimate',
    'ngTouch'

]).config(['$routeProvider', '$httpProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {

        $routeProvider.when('/403', {templateUrl: 'views/403.html', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/login', {templateUrl: 'views/login.html', controller: 'AuthController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});
        $routeProvider.when('/todo', {templateUrl: 'views/todo.html', controller: 'TodoController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/', {templateUrl: 'views/todo.html', controller: 'TodoController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

    }]);

app.config(['$httpProvider', function($httpProvider) {

        $httpProvider.interceptors.push(['$q', '$rootScope', function($q, $rootScope) {
                return {
                    'request': function(config) {
                        $rootScope.$broadcast('loading-started');

                        if (config.url.indexOf("api") !== -1) {
                            config.url = 'http://localhost:9090/server/' + config.url;
                        }

                        return config || $q.when(config);
                    },
                    'response': function(response) {
                        $rootScope.$broadcast('loading-complete');
                        return response || $q.when(response);
                    },
                    'responseError': function(rejection) {
                        $rootScope.$broadcast('loading-complete');
                        return $q.reject(rejection);
                    },
                    'requestError': function(rejection) {
                        $rootScope.$broadcast('loading-complete');
                        return $q.reject(rejection);
                    }
                };
            }]);

        $httpProvider.interceptors.push(['$injector', function($injector) {
                return $injector.get('AuthInterceptor');
            }]);

    }]);

app.run(['$rootScope', '$location', '$window', 'AUTH_EVENTS', 'APP_EVENTS', 'USER_ROLES', 'AuthService',
    function($rootScope, $location, $window, AUTH_EVENTS, APP_EVENTS, USER_ROLES, AuthService) {

        $rootScope.$on('$routeChangeStart', function(event, next) {

            if (next.redirectTo !== '/') {
                var authorizedRoles = next.data.authorizedRoles;

                if (authorizedRoles[0] !== undefined && authorizedRoles.indexOf(USER_ROLES.NOT_LOGGED) === -1) {

                    if (!AuthService.isAuthorized(authorizedRoles)) {
                        event.preventDefault();
                        if (AuthService.isAuthenticated()) {
                            // user is not allowed
                            $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                        }
                        else {
                            // user is not logged in
                            $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                        }
                    }
                }
            }
        });


        $rootScope.$on(AUTH_EVENTS.notAuthorized, function() {
            console.log("notAuthorized");
            $location.path("/403");
        });

        $rootScope.$on(AUTH_EVENTS.notAuthenticated, function() {
            console.log("notAuthenticated");
            $rootScope.currentUser = null;
            $location.path("/login");
        });

        $rootScope.$on(AUTH_EVENTS.sessionTimeout, function() {
            console.log("sessionTimeout");
        });

        $rootScope.$on(AUTH_EVENTS.loginFailed, function() {
            console.log("loginFailed");
            $location.path("/login");
        });

        $rootScope.$on(AUTH_EVENTS.logoutSuccess, function() {
            console.log("logoutSuccess");
            $rootScope.currentUser = null;
            $location.path("/todo");
        });

        $rootScope.$on(AUTH_EVENTS.loginSuccess, function() {
            $location.path("/todo");
        });

        $rootScope.$on(APP_EVENTS.offline, function() {
            AlertService.addWithTimeout('danger', 'Servidor esta temporariamente indisponível, tente mais tarde');
        });

        // Check if a new cache is available on page load.
        $window.addEventListener('load', function(e) {
            $window.applicationCache.addEventListener('updateready', function(e) {
                console.log($window.applicationCache.status);
                if ($window.applicationCache.status === $window.applicationCache.UPDATEREADY) {
                    // Browser downloaded a new app cache.
                    $window.location.reload();
                    alert('Uma nova versão será carregada!');
                }
            }, false);
        }, false);

    }]);

app.constant('APP_EVENTS', {
    offline: 'app-events-offline'
});

app.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});

app.constant('USER_ROLES', {
    ADMINISTRADOR: 'ADMINISTRADOR',
    NOT_LOGGED: 'NOT_LOGGED'
});

app.factory('AuthInterceptor', ['$rootScope', '$q', 'AUTH_EVENTS', 'APP_EVENTS',
    function($rootScope, $q, AUTH_EVENTS, APP_EVENTS) {

        return {
            responseError: function(response) {

                $rootScope.$broadcast({
                    0: APP_EVENTS.offline,
                    404: APP_EVENTS.offline,
                    503: APP_EVENTS.offline,
                    401: AUTH_EVENTS.notAuthenticated,
                    403: AUTH_EVENTS.notAuthorized,
                    419: AUTH_EVENTS.sessionTimeout,
                    440: AUTH_EVENTS.sessionTimeout
                }[response.status], response);

                return $q.reject(response);
            }
        };

    }]);

app.constant('config', {
    SIGNALIG_SERVER_URL: '10.200.24.50:7777'
});

app.value('version', '1.0.0');

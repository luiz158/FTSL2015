'use strict';

var app = angular.module('cep', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngAnimate',
    'ngTouch',
    'lumx',
    'angulartics',
    'angulartics.piwik'

]).config(['$routeProvider', 'USER_ROLES', function($routeProvider, USER_ROLES) {

        $routeProvider.otherwise({redirectTo: '/cep', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/403', {redirectTo: '403.html', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});
        $routeProvider.when('/404', {redirectTo: '404.html', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});
        $routeProvider.when('/500', {redirectTo: '500.html', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/cep', {templateUrl: 'views/cep/edit.html', controller: 'CEPController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});
        $routeProvider.when('/cep/:id', {templateUrl: 'views/cep/edit.html', controller: 'CEPController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/logra', {templateUrl: 'views/logra/list.html', controller: 'LograController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});

        $routeProvider.when('/especial', {templateUrl: 'views/especial/list.html', controller: 'EspecialController', data: {authorizedRoles: [USER_ROLES.NOT_LOGGED]}});


    }]);

app.config(['$httpProvider', function($httpProvider) {

        $httpProvider.interceptors.push(['$q', '$rootScope', 'AppService', 'AUTH_EVENTS', 'Session', function($q, $rootScope, AppService, AUTH_EVENTS, Session) {
                return {
                    'request': function(config) {
                        $rootScope.$broadcast('loading-started');

                        var token = AppService.getToken();

                        if (config.url.indexOf("api") !== -1) {
                            config.url = 'https://www.pgxp.com.br/cep/' + config.url;
                        }

                        if (token) {
                            config.headers['Authorization'] = "Token " + token;
                        }

                        return config || $q.when(config);
                    },
                    'response': function(response) {
                        $rootScope.$broadcast('loading-complete');
                        return response || $q.when(response);
                    },
                    'responseError': function(rejection) {

                        var status = rejection.status
                        if (status !== 401 && status !== 412 && status !== 422 && status !== 400) {
                            $rootScope.$broadcast(AUTH_EVENTS.sessionTimeout);
                        }

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

app.run(['$rootScope', '$location', 'AUTH_EVENTS', 'USER_ROLES', 'AuthService', 'AppService', 'Session',
    function($rootScope, $location, AUTH_EVENTS, USER_ROLES, AuthService, AppService, Session) {

        $rootScope.$on('$routeChangeStart', function(event, next) {
            var authorizedRoles = next.data.authorizedRoles;

            if (authorizedRoles.indexOf(USER_ROLES.NOT_LOGGED) === -1) {

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
        });


        $rootScope.$watch(function() {
            return $location.path()
        }, function(newLocation) {
            if ($rootScope.actualLocation === newLocation) {
                alert('Why did you use history back?');
            }
        });

        $rootScope.$on(AUTH_EVENTS.notAuthorized, function() {
            console.log("notAuthorized");
            $location.path("/403");
        });

        $rootScope.$on(AUTH_EVENTS.notAuthenticated, function() {
            Session.destroy();
            console.log("notAuthenticated");
            $rootScope.setCurrentUser(null);
            AppService.removeToken();
            $location.path("/cep");
        });

        $rootScope.$on(AUTH_EVENTS.sessionTimeout, function(e) {
            Session.destroy();
            console.log("sessionTimeout");
            $rootScope.setCurrentUser(null);
            AppService.removeToken();
            $location.path("/cep");
        });

        $rootScope.$on(AUTH_EVENTS.loginFailed, function() {
            Session.destroy();
            console.log("loginFailed");
            $rootScope.setCurrentUser(null);
            AppService.removeToken();
            $location.path("/cep");
        });

        $rootScope.$on(AUTH_EVENTS.logoutSuccess, function() {
            Session.destroy();
            console.log("logoutSuccess");
            $rootScope.setCurrentUser(null);
            AppService.removeToken();
            $location.path("/cep");
        });

        $rootScope.$on(AUTH_EVENTS.loginSuccess, function() {
            $location.path("/cep");
        });

    }]);

app.constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});

app.constant('USER_ROLES', {
    ADMINISTRATOR: 'ADMINISTRATOR',
    MANAGER: 'MANAGER',
    USER: 'USER',
    NOT_LOGGED: 'NOT_LOGGED'
});

app.factory('AuthInterceptor', ['$rootScope', '$q', 'AUTH_EVENTS', function($rootScope, $q, AUTH_EVENTS) {

        return {
            responseError: function(response) {

                $rootScope.$broadcast({
                    401: AUTH_EVENTS.notAuthenticated,
                    403: AUTH_EVENTS.notAuthorized,
                    419: AUTH_EVENTS.sessionTimeout,
                    440: AUTH_EVENTS.sessionTimeout
                }[response.status], response);

                return $q.reject(response);
            }
        };

    }]);

app.value('version', '0.0.0');

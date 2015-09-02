'use strict';

app.directive('uiLinhabar', ['$rootScope', '$anchorScroll', function($rootScope, $anchorScroll) {
        return {
            restrict: 'AC',
            template: '<span class="bar"></span>',
            link: function(scope, el, attrs) {
                el.addClass('linhabar hide');
                scope.$on('$routeChangeStart', function(e) {
                    $anchorScroll();
                    el.removeClass('hide').addClass('active');
                });
                scope.$on('$routeChangeSuccess', function(event, toState, toParams, fromState) {
                    event.targetScope.$watch('$viewContentLoaded', function() {
                        el.addClass('hide').removeClass('active');
                    })
                });
            }
        }
    }]);

app.directive('alerts', function() {
    return {
        restrict: 'E',
        templateUrl: 'partials/alerts.html'
    };
});

app.directive("loadingIndicator", function() {
    return {
        restrict: "A",
        templateUrl: 'partials/loading.html',
        link: function(scope, element, attrs) {

            scope.$on("loading-started", function(e) {
                element.css({"display": ""});
            });

            scope.$on("loading-complete", function(e) {
                element.css({"display": "none"});
            });

        }
    };
});

app.directive("appVersion", ["version", function(version) {
        return function(scope, elm, attrs) {
            elm.text(version);
        };
    }]);

app.directive("confirmButton", function($timeout) {
    return {
        restrict: 'A',
        scope: {
            actionOK: '&confirmAction',
            actionCancel: '&cancelAction'
        },
        link: function(scope, element, attrs) {
            var buttonId, html, message, nope, title, yep;
            buttonId = Math.floor(Math.random() * 10000000000);
            attrs.buttonId = buttonId;
            message = attrs.message || "Tem certeza?";
            yep = attrs.yes || "Sim";
            nope = attrs.no || "Não";
            title = attrs.title || "Confirm";

            element.bind('click', function(e) {

                var box = bootbox.dialog({
                    message: message,
                    title: title,
                    buttons: {
                        success: {
                            label: yep,
                            className: "btn-success",
                            callback: function() {
                                $timeout(function() {
                                    scope.$apply(scope.actionOK);
                                });
                            }
                        },
                        danger: {
                            label: nope,
                            className: "btn-danger",
                            callback: function() {
                                scope.$apply(scope.actionCancel);
                            }
                        }
                    }
                });

            });
        }
    };
});


app.directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if (event.which === 13) {
                scope.$apply(function() {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});


'use strict';

app.filter('version', function(version) {
    return function(text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    };
});


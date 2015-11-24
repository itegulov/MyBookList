'use strict';

angular.module('javaApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



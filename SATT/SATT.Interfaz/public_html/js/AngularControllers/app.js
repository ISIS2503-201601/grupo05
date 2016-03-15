/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(){var SATT= angular.module('SATT',[]);
    
    SATT.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });
    
     SATT.directive('senalInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/senal-info.html',
            controller: 'getSenales'
        };
    });
 
    SATT.controller("getSenales", function($http, $scope) {
    $http.get('http://localhost:8080/SATT.servicios/webresources/receptor/mostrar').
      success(function(data, status, headers, config) 
      {
        $scope.senales = data;
      }).
      error(function(data, status, headers, config) {
        // log error
      });
    });
    
    SATT.directive('reporteInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/reporte-info.html',
            controller: 'getReportes'
        };
    });
 
    SATT.controller("getReportes", function($http, $scope) {
    $http.get('http://localhost:8080/SATT.servicios/webresources/reportes/mostrar').
      success(function(data, status, headers, config) 
      {
        $scope.reportes = data;
      }).
      error(function(data, status, headers, config) {
        // log error
      });
    });
    
     SATT.directive('senalForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/senal-form.html',
            controller: 'senalCtrl'
        };
    });
 
    SATT.controller("senalCtrl", function($http, $scope) {
 
        $scope.addSenal=function(){
            console.log('name');
            console.log("[" + JSON.stringify($scope.senal) + "]");
            $http.post('http://localhost:8080/SATT.servicios/webresources/receptor/enviar', "[" + JSON.stringify($scope.senal) + "]").success(function(data,headers){
                $scope.senales={};
                $scope.toolbar.selectTab(2);
            });
        };
    }); 
    
    SATT.directive('reporteForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/reporte-form.html',
            controller: 'reporteCtrl'
        };
    });
 
    SATT.controller("reporteCtrl", function($http, $scope) {
 
        $scope.addReporte=function(){
            console.log('name');
            console.log("[" + JSON.stringify($scope.reporte) + "]");
            $http.post('http://localhost:8080/SATT.servicios/webresources/reportes/enviar', "[" + JSON.stringify($scope.reporte) + "]").success(function(data,headers){
               //console.log(data);
               console.log("alcanzo a pasar por acá");
                alert;
                $scope.retornos = data;
                alert;
                console.log(JSON.stringify($scope.retornos));
                $scope.reportes={};
                $scope.toolbar.selectTab(1);
            });
        };
    }); 
    
   
    
})();



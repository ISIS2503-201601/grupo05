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
            controller: function(){
                this.tab=0;
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
                
                this.isAdmin=function(){
                    return alert ($rootScope.user.test.indexOf("admin") != -1);
                };
                
                this.isRSNC=function(){
                    return $rootScope.tipoUsuario === "rsnc";
                };
                
                this.isExperto=function(){
                    return $rootScope.tipoUsuario === "experto";
                };
                
                this.isSensor=function(){
                    return $rootScope.tipoUsuario === "sensor";
                };
                
                this.init=function(){
                    $rootScope = "inicializacion";
                    this.selectTab(5);
                    return true;
                };
            }, 
          
            controllerAs:'toolbar'
            
        };
    });
    
     SATT.directive('loginForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/login-form.html',
            controller: function(){
                
                $rootScope.user = "";
                $rootScope.pass = "";
                 $rootScope.user1 = "";
                $rootScope.pass1 = "";
                
                this.loguearse=function(username, passs){
                    $rootScope.user = username;
                    $rootScope.pass = passs;
                };
                
                this.isAdmin = function()
                {
                    if (alert(this.user.test.indexOf("admin") != -1) && this.validUser())
                    {
                      $rootScope.tipoUsuario = "admin";  
                    }
                    
                    return $rootScope.tipoUsuario;
                };
                
                this.isSensor = function()
                {
                    if (alert(this.user.test.indexOf("sensor") != -1) && this.validUser())
                    {
                       $rootScope.tipoUsuario = "sensor";    
                    }
                    
                    return  $rootScope.tipoUsuario;
                };
                
                this.isRsnc = function()
                {
                    if ( alert(this.user.test.indexOf("rsnc") != -1) && this.validUser())
                    {
                         $rootScope.tipoUsuario = "rsnc"; 
                    }
                    
                    return  $rootScope.tipoUsuario ;
                };
                
                 this.isExperto = function()
                {
                    if ( alert(this.user.test.indexOf("experto") != -1) && this.validUser())
                    {
                         $rootScope.tipoUsuario = "experto"; 
                    }
                    
                     return  $rootScope.tipoUsuario;
                };
                
                this.validUser = function(){
                    return this.pass === "Sensor01";
                };
            },  controllerAs:'loginCtrl'
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

    SATT.directive('mainMenu', function(){
        return{
            restrict:'E',
            templateUrl:'partials/main-menu.html',
            controller: 'mainCtrl'
        };
    });
    
   
    
})();



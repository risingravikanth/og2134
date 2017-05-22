<!DOCTYPE html>
<html ng-app="LoginApp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OG Analysis | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="./js/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="./bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="./bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
  	<link rel="stylesheet" href="./css/OGAnalysis.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="./js/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition login-page" ng-controller="LoginCtrl" ng-init="init()">
    <div class="login-box" ng-if="showLogin">
      <div class="login-logo">
        <a href="login.html"><b>OG</b>Analysis</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
        <form id="loginForm" method="post">
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="email" ng-Model="loginObj.username" required>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="password" ng-Model="loginObj.password" required>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
               <!--  <label>
                  <input id="rememberMe" type="checkbox"> Remember Me
                </label> -->
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat" ng-click="login()">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <!-- <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
         --></div><!-- /.social-auth-links -->

        <a ng-click="forgotPassword()">I forgot my password</a><br>
        <a ng-click="showRegistrationForm()" class="text-center">Register a new membership</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    
    
    <div class="login-box" ng-if="!showLogin">
      <div class="login-logo">
        <a href="login.html"><b>OG</b>Analysis</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Register your account</p>
        <form id="loginForm">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Name" name="name" ng-Model="registerObj.name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
        
          <div class="form-group has-feedback">
            <input type="email" class="form-control" placeholder="Email" name="email" ng-Model="registerObj.username" required>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="password" ng-Model="registerObj.password" required>
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-8">
               
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat" ng-click="signup()">Register</button>
            </div><!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <!-- <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
         --></div><!-- /.social-auth-links -->

         <a ng-click="showRegistrationForm()" class="text-center">Already have membership click here to login</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    
    
    
    
    

    <!-- jQuery 2.1.4 -->
    <script src="./js/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="./js/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="./js/plugins/iCheck/icheck.min.js"></script>
    
    <script src="./js/plugins/angular/angular.min.js"></script>
    
    
      
  
    <script>
   	  $(function () {
   		   
	        $('#rememberMe').iCheck({
	          checkboxClass: 'icheckbox_square-blue',
	          radioClass: 'iradio_square-blue',
	          increaseArea: '20%' // optional
	        });
      });
      
      angular.module('LoginApp', []);
      angular.module('LoginApp')
  			.service("URL", function(){
  				this.contextPath = "http://localhost:8080";
  				this.loginUrl = this.contextPath+'/oganalysis/login';
  				this.headerRequest = {	// TODO: Once api need user token then uncomment this.
  						"Content-type": 'application/x-www-form-urlencoded',
  						'Access-Control-Allow-Origin':'*'
  					};
  			})
      angular.module('LoginApp').controller('LoginCtrl', function($scope,$rootScope,URL,$http) {
    	  
    	  $scope.login = function(){
    		 	   var request = {
    					method: 'POST',
    					url: URL.loginUrl,
    					data: $scope.loginObj,
    					headers: URL.headerRequest
    				};
    				 
    				$http(request).success(function (resp){
    	 				 if(resp == 'correct'){
    						 window.location.href = "index.html#/"
    					 }else if(resp =='incorrect'){
    						 alert("login failed!")
    					 }
    				     
    			    }).error(function (resp){
    					console.log("Error")
    		        });
    	  }
    	  
    	  $scope.signup = function(){
    		  alert("signup")
    	  }
    	  
    	  $scope.init = function(){
    		  $scope.loginObj = {
  		    		username : "",
  		    		password: ""
	    	  };
    		  
    		  $scope.registerObj = {
   				  name: "",
   				  username : "",
    		  	  password: ""
    		  };
    		  
    		  $scope.showLogin = true;
    		  
    		  $(function () {
   		        $('input').iCheck({
    		          checkboxClass: 'icheckbox_square-blue',
    		          radioClass: 'iradio_square-blue',
    		          increaseArea: '20%' // optional
    		        });
   		      });
    		  
     	  }
    	  
    	  $scope.forgotPassword = function(){
    		  
    		  
    	  }
    	  
 		  $scope.showRegistrationForm = function(){
 			 $scope.showLogin = !$scope.showLogin;
 			 $('#rememberMe').iCheck({
 		          checkboxClass: 'icheckbox_square-blue',
 		          radioClass: 'iradio_square-blue',
 		          increaseArea: '20%' // optional
 		        });
 			 
    	  }
    	  
       });
    </script>
  </body>
</html>

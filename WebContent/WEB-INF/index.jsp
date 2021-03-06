 <!DOCTYPE html>
<html ng-app="OGAnalysis">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>OG Analysis</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  
  <style type="text/css">
		  
		  	
		 .login-content-wrapper, .right-side, .main-footer {
		    margin-left: 0px !important;
			transition: none 0s ease 0s ;
			background-color :transparent;
		 }
  </style>
  
  
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="./js/plugins/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="./bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="./bower_components/Ionicons/css/ionicons.min.css">
  
  
   <!-- DataTables
  <link rel="stylesheet" href="./js/plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="./css/responsive.dataTables.min.css"> -->
  <link rel="stylesheet" href="./js/plugins/datatables/dataTables.bootstrap.css">
 <!--   <link href="bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet" type="text/css" />-->
  
  
  <!-- Theme style -->
  <link rel="stylesheet" href="./css/OGAnalysis.min.css">
  <!-- OGAnalysis Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="./css/skins/_all-skins.min.css">
  
  <link rel="stylesheet" href="bower_components/font-icons/style.css">
  <link rel="stylesheet" href="./css/custom.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
	  
	  
	<!-- jQuery 2.2.3 -->
	<script src="./js/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="./js/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="./js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="./js/plugins/fastclick/fastclick.js"></script>

	<script src="./js/plugins/angular/angular.min.js"></script>
	<script src="./js/plugins/angular-ui-router/release/angular-ui-router.min.js"></script>
	
	
	<!-- DataTables 
	<script src="./js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="./js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="./js/plugins/datatables/dataTables.responsive.js"></script>-->
	
<script src="bower_components/datatables/media/js/jquery.dataTables.js"></script>
<script src="./js/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="bower_components/datatables-responsive/js/dataTables.responsive.js"></script>
	
	
	<script type="text/javascript" src="./bower_components/lodash/dist/lodash.js"></script>
	<script type="text/javascript" src="./bower_components/angularjs-dropdown-multiselect/dist/angularjs-dropdown-multiselect.min.js"></script>

	<!-- OGAnalysis App -->
	<script src="./js/app.js"></script>
	<!-- OGAnalysis for demo purposes -->
	<script src="./js/demo.js"></script>
	
	<!-- lngApp App -->
	<script src="./js/main.js"></script>
	<script src="./js/services.js"></script>
	<script src="./js/controllers/commonCtrl.js"></script>
	<script src="./js/controllers/capacityCtrl.js"></script>
	<script src="./js/controllers/contractsCtrl.js"></script>
	<script src="./js/controllers/infrastructureCtrl.js"></script>
	<script src="./js/controllers/supplyDemandCtrl.js"></script>
	<script src="./js/controllers/refineriesCtrl.js"></script>
	<script src="./js/controllers/refineriesInfraCtrl.js"></script>
  
</head>
<body class="hold-transition skin-red sidebar-mini" ng-if="$state">
<!-- Site wrapper  ( sidebar-collapse control-sidebar-open )-->
<div class="wrapper">

  <header class="main-header" ng-controller="HeaderCtrl" ng-if="!$state.includes('login')">
    <!-- Logo -->
    <a  class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>O</b>G</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>OG</b>Analysis</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a class="sidebar-toggle" data-toggle="offcanvas" ng-click="toggleNav()" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
           
           
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="./img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="./img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar" ng-if="!$state.includes('login')">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel 
      <div class="user-panel">
        <div class="pull-left image">
          <img src="./img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>-->
      <!-- search form 
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>-->
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <!-- <li class="header text-center">MAIN NAVIGATION</li> -->
		
		<!-- EXPLORATION -->
		<li>
          <a class="side-item" href="#/exploration">
            <i class="fa fa-th"></i> <span>EXPLORATION</span>
           </a>
        </li>
		
		<!-- <li>
          <a href="#/lng">
            <i class="fa fa-th"></i> <span>LNG</span>
           </a>
        </li>  -->
        
        <li class="treeview">
          <a href="javascript:void(0)">
            <i class="icon-ship"></i> <span>LNG</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a class="side-item" href="#/lng/capacity"><i class="fa fa-circle-o"></i> Capacity</a></li>
             <li><a class="side-item" href="#/infrastructure"><i class="fa fa-circle-o"></i> Infrastructure</a></li>
              <li><a class="side-item" href="#/supplydemand"><i class="fa fa-circle-o"></i> Supply & Demand</a></li>
               <li><a class="side-item" href="#/contracts"><i class="fa fa-circle-o"></i> Contracts </a></li>
            </ul>
        </li>
		
		<li>
 		<a class="side-item" href="#/pipelines">
            <i class="fa fa-link"></i> <span>PIPELINES</span>
           </a>
        </li>
     
	 
		<li class="treeview">
          <a href="javascript:void(0)">
            <i class="fa fa-dashboard"></i> <span>PRODUCTION</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a class="side-item" href="#/crude"><i class="fa fa-circle-o"></i> Crude</a></li>
            <li><a  class="side-item" href="#/naturalgas"><i class="fa fa-circle-o"></i> Natural Gas</a></li>
          </ul>
        </li>
		<!--    -->
		
	 
        
         <li class="treeview">
          <a href="javascript:void(0)">
            <i class="fa fa-dashboard"></i> <span>REFINERIES</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a class="side-item" href="#/refineries"><i class="fa fa-circle-o"></i> Capacity</a></li> 
            <li><a class="side-item" href="#/refineries/infrastructure"><i class="fa fa-circle-o"></i> Infrastructure</a></li>
            </ul>
        </li>
		
		<li>
		<a class="side-item" href="#/storage">
            <i class="fa fa-th"></i> <span>STORAGE</span>
           </a>
        </li>
		<li>
		<a class="side-item" href="#/reports">
            <i class="fa fa-bar-chart"></i> <span>REPORTS</span>
           </a>
        </li>
		
		
		
		
		
		
		
		
		<!--
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#/dashboard"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
            <li><a href="#/dashboard22"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Layout Options</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right">4</span>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
            <li><a href="../layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
            <li><a href="../layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
            <li><a href="../layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
          </ul>
        </li>
        <li>
          <a href="../widgets.html">
            <i class="fa fa-th"></i> <span>Widgets</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">Hot</small>
            </span>
          </a>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i>
            <span>Charts</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../charts/chartjs.html"><i class="fa fa-circle-o"></i> ChartJS</a></li>
            <li><a href="../charts/morris.html"><i class="fa fa-circle-o"></i> Morris</a></li>
            <li><a href="../charts/flot.html"><i class="fa fa-circle-o"></i> Flot</a></li>
            <li><a href="../charts/inline.html"><i class="fa fa-circle-o"></i> Inline charts</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-laptop"></i>
            <span>UI Elements</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../UI/general.html"><i class="fa fa-circle-o"></i> General</a></li>
            <li><a href="../UI/icons.html"><i class="fa fa-circle-o"></i> Icons</a></li>
            <li><a href="../UI/buttons.html"><i class="fa fa-circle-o"></i> Buttons</a></li>
            <li><a href="../UI/sliders.html"><i class="fa fa-circle-o"></i> Sliders</a></li>
            <li><a href="../UI/timeline.html"><i class="fa fa-circle-o"></i> Timeline</a></li>
            <li><a href="../UI/modals.html"><i class="fa fa-circle-o"></i> Modals</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Forms</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../forms/general.html"><i class="fa fa-circle-o"></i> General Elements</a></li>
            <li><a href="../forms/advanced.html"><i class="fa fa-circle-o"></i> Advanced Elements</a></li>
            <li><a href="../forms/editors.html"><i class="fa fa-circle-o"></i> Editors</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-table"></i> <span>Tables</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../tables/simple.html"><i class="fa fa-circle-o"></i> Simple tables</a></li>
            <li><a href="../tables/data.html"><i class="fa fa-circle-o"></i> Data tables</a></li>
          </ul>
        </li>
        <li>
          <a href="../calendar.html">
            <i class="fa fa-calendar"></i> <span>Calendar</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-red">3</small>
              <small class="label pull-right bg-blue">17</small>
            </span>
          </a>
        </li>
        <li>
          <a href="../mailbox/mailbox.html">
            <i class="fa fa-envelope"></i> <span>Mailbox</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-yellow">12</small>
              <small class="label pull-right bg-green">16</small>
              <small class="label pull-right bg-red">5</small>
            </span>
          </a>
        </li>
        <li class="treeview active">
          <a href="#">
            <i class="fa fa-folder"></i> <span>Examples</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="invoice.html"><i class="fa fa-circle-o"></i> Invoice</a></li>
            <li><a href="profile.html"><i class="fa fa-circle-o"></i> Profile</a></li>
            <li><a href="login.html"><i class="fa fa-circle-o"></i> Login</a></li>
            <li><a href="register.html"><i class="fa fa-circle-o"></i> Register</a></li>
            <li><a href="lockscreen.html"><i class="fa fa-circle-o"></i> Lockscreen</a></li>
            <li><a href="404.html"><i class="fa fa-circle-o"></i> 404 Error</a></li>
            <li><a href="500.html"><i class="fa fa-circle-o"></i> 500 Error</a></li>
            <li class="active"><a href="blank.html"><i class="fa fa-circle-o"></i> Blank Page</a></li>
            <li><a href="pace.html"><i class="fa fa-circle-o"></i> Pace Page</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-share"></i> <span>Multilevel</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
            <li>
              <a href="#"><i class="fa fa-circle-o"></i> Level One
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <li><a href="#"><i class="fa fa-circle-o"></i> Level Two</a></li>
                <li>
                  <a href="#"><i class="fa fa-circle-o"></i> Level Two
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                  </a>
                  <ul class="treeview-menu">
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> Level Three</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#"><i class="fa fa-circle-o"></i> Level One</a></li>
          </ul>
        </li>
        <li><a href="../../documentation/index.html"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
        <li class="header">LABELS</li>
        <li><a href="#"><i class="fa fa-circle-o text-red"></i> <span>Important</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-yellow"></i> <span>Warning</span></a></li>
        <li><a href="#"><i class="fa fa-circle-o text-aqua"></i> <span>Information</span></a></li> -->
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" ng-class="{ 'login-content-wrapper': $state.includes('login') }">
  		
		<div ui-view></div>
   		 
    </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <!-- <b>Version</b> 2.3.6 -->
    </div>
    <strong>Copyright &copy; 2014-2016 <!-- <a href="http://almsaeedstudio.com">Almsaeed Studio</a> -->.</strong> All rights
    reserved.
  </footer>

  
  <!-- Control Sidebar (style="position: fixed; height: auto;") -->
  
  <!--
 	Region
	Country
	Locations
	Operator
	Owner
	Status
	Units
	Offshore/Onshore
	Type -->

  <aside class="control-sidebar control-sidebar-dark" ng-controller="FilterCtrl" ng-init="initFilter()">
		 
		<div class="tab-content">
			<div class="form-group" ng-show="filterObj.regionField">
				<label class="control-sidebar-subheading">
						Region
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="regionData" selected-model="regionModel"  extra-settings="regionSettings" ></div>
			</div>	
			
			<div class="form-group" ng-show="filterObj.countryField">
				<label class="control-sidebar-subheading">
						Country
				</label>
				<div ng-dropdown-multiselect="" options="countryData" class="form-control filter-control col-md-12" selected-model="countryModel" extra-settings="countrySettings"  events="countryEvents" ></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.exports">
				<label class="control-sidebar-subheading">
						Export Countries
				</label>
				<div ng-dropdown-multiselect="" options="exportedCountriesData" class="form-control filter-control col-md-12" selected-model="exportedCountriesModel" extra-settings="exportedCountriesSettings" ></div>
			</div>
			
	 		<div class="form-group" ng-show="filterObj.exports">
				<label class="control-sidebar-subheading">
						Export Companies
				</label>
				<div ng-dropdown-multiselect="" options="exportedCompaniesData" class="form-control filter-control col-md-12" selected-model="exportedCompaniesModel" extra-settings="exportedCompaniesSettings" ></div>
			</div>	
			
			<div class="form-group"  ng-show="filterObj.exports">
				<label class="control-sidebar-subheading">
						&nbsp;
				</label>
	 		</div>
			
			<div class="form-group" style="margin-top: 15px;" ng-show="filterObj.exports">
				<button type="button" class="btn btn-block btn-warning " ng-click="loadImports()">Load Imports</button>
			</div> 
			
			
			<div class="form-group" ng-show="filterObj.imports">
				<label class="control-sidebar-subheading">
						Import Countries
				</label>
				<div ng-dropdown-multiselect="" options="importedCountriesData" class="form-control filter-control col-md-12" selected-model="importedCountriesModel" extra-settings="importedCountriesSettings" ></div>
			</div>
			
	 		<div class="form-group" ng-show="filterObj.imports">
				<label class="control-sidebar-subheading">
						Import Companies
				</label>
				<div ng-dropdown-multiselect="" options="importedCompaniesData" class="form-control filter-control col-md-12" selected-model="importedCompaniesModel" extra-settings="importedCompaniesSettings" ></div>
			</div>	
			
			
			
			
			
			<div class="form-group" ng-show="filterObj.locationField">
				<label class="control-sidebar-subheading">
						Locations
				</label>
				<div ng-dropdown-multiselect="" options="locationData" class="form-control filter-control col-md-12" selected-model="locationModel" extra-settings="locationSettings"></div>
			</div>	
			
			<div class="form-group" ng-show="filterObj.operatorField">
				<label class="control-sidebar-subheading">
						Operator
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="operatorData" selected-model="operatorModel" extra-settings="operatorSettings"></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.ownerField">
				<label class="control-sidebar-subheading">
						Owner
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="ownerData" selected-model="ownerModel" extra-settings="ownerSettings" ></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.statusField">
				<label class="control-sidebar-subheading">
						Status
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="statusData" selected-model="statusModel" extra-settings="statusSettings" ></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.unitsField">
				<label class="control-sidebar-subheading">
						Units
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="unitsData" selected-model="unitsModel" extra-settings="unitsSettings"></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.offshoreField">
				<label class="control-sidebar-subheading">
						Offshore/Onshore
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="offshoreData" selected-model="offshoreModel"></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.typeField">
				<label class="control-sidebar-subheading">
						Type
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="typeData" selected-model="typeModel" extra-settings="typeSettings"></div>
			</div>
			
			<div class="form-group" ng-show="filterObj.sectorField">
				<label class="control-sidebar-subheading">
						Sector
				</label>
				<div ng-dropdown-multiselect="" class="form-control filter-control col-md-12" options="sectorData" selected-model="sectorModel" ></div>
			</div>
			
	 		
			<!-- <div class="form-group" ng-show="filterObj.typeField">
				<label>Type</label>
				<select class="form-control">
					<option>option 1</option>
					<option>option 2</option>
					<option>option 3</option>
					<option>option 4</option>
					<option>option 5</option>
				</select>
			</div> 
			
			<div class="form-group" ng-show="filterObj.sectorField">
				<label>Sector</label>
				<select class="form-control">
					<option value="Exploration">Exploration</option>
					<option value="LNG">LNG</option>
					<option value="Pipeline">Pipe line</option>
					<option value="Refinery">Refinery</option>
					<option value="Storage">Storage</option>
			 	</select>
			</div>-->
			<div class="form-group">
				<label class="control-sidebar-subheading">
						&nbsp;
				</label>
	 		</div>
			
			 
			<div class="form-group" style="margin-top: 15px;">
				<button type="button" class="btn btn-block btn-success" ng-click="filterSubmit()">Submit</button>
			</div> 
			<div class="form-group" >
				<button type="button" class="btn btn-block btn-danger" ng-click="resetFilter()">Reset</button>
			</div> 
		
		</div>
  </aside>
  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

</body>
</html>
 
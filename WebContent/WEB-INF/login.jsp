 <!DOCTYPE HTML>
<html ng-app="LoginApp">
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>OG Explorer</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="HTML5 Website Template by freehtml5.co" />
	<meta name="keywords" content="website templates, html5, template, bootstrap, website template, html5, css3, mobile first, responsive" />
	<meta name="author" content="" />

	<!-- 
 	//////////////////////////////////////////////////////
	 -->

  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="./login/css/font1.css" rel="stylesheet">
	<link href="./login/css/font2.css" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="./login/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="./login/css/icomoon.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="./login/css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="./login/css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="./login/css/owl.carousel.min.css">
	<link rel="stylesheet" href="./login/css/owl.theme.default.min.css">

	<!-- Flexslider  -->
	<link rel="stylesheet" href="./login/css/flexslider.css">

	<!-- Pricing -->
	<link rel="stylesheet" href="./login/css/pricing.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="./login/css/style.css">
	
	<!-- ogfont-awesome style  -->
	<link rel="stylesheet" href="./login/css/ogfont-awesome/ogstyle.css">
	 

	<!-- Modernizr JS -->
	<script src="./login/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body ng-controller="LoginCtrl" ng-init="init()">
		
	<div class="fh5co-loader"></div>
	
	<div id="page">
	<nav class="fh5co-nav" role="navigation">
		<!--<div class="top">
			<div class="container">
				<div class="row">
					<div class="col-xs-12 text-right">
						<p class="site">www.yourdomainname.com</p>
						<p class="num">Call: +01 123 456 7890</p>
						<ul class="fh5co-social">
							<li><a href="#"><i class="icon-facebook2"></i></a></li>
							<li><a href="#"><i class="icon-twitter2"></i></a></li>
							<li><a href="#"><i class="icon-dribbble2"></i></a></li>
							<li><a href="#"><i class="icon-github"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>-->
		<div class="top-menu">
			<div class="container">
				<div class="row">
					<div class="col-xs-2">
						<div id="fh5co-logo"><a href="index.html"><i class="icon-study"></i>OG Explorer<span>.</span></a></div>
					</div>
					<div class="col-xs-10 text-right menu-1">
						<ul>
							<li class="active"><a href="javascript:void(0);">Home</a></li>
							<!--<li><a href="about.html">About</a></li>-->
							<!--<li><a href="courses.html">Courses</a></li>
							<li><a href="teacher.html">Teacher</a></li>
						 	<li><a href="pricing.html">Pricing</a></li>
							<li class="has-dropdown">
								<a href="blog.html">Blog</a>
								<ul class="dropdown">
									<li><a href="#">Web Design</a></li>
									<li><a href="#">eCommerce</a></li>
									<li><a href="#">Branding</a></li>
									<li><a href="#">API</a></li>
								</ul>
							</li>-->
							<!--<li><a href="contact.html">Contact</a></li>-->
							<li class="btn-ctn dropdown">
								<a href="#" data-toggle="dropdown" id="loginShow"><span>Login</span></a>
						 	</li>
							<li class="btn-ctn dropdown">
								<a href="#" data-toggle="dropdown" id="contactUsShow"><span>Contact Us</span></a>
						 	</li>
							<!--<li class="btn-cta"><a href="#"><span>Create a Course</span></a></li>-->
						</ul>
					</div>
				</div>
				
			</div>
		</div>
		<div class="top-menu login-background" id="loginPage" style="display:none;">
			 <div class="login-box animate-box" >
			  <div class="login-logo">
				<a href="#"><b>&nbsp;</b></a>
			  </div><!-- /.login-logo -->
			  <div class="login-box-body">
				<p class="login-box-msg">Sign in to start your session</p>
				<p class="login-box-msg" style="color:red;" ng-if="errorMessage != ''">{{ errorMessage}} </p>
				<form id="loginForm" name="loginForm" >
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
						</label> 
						<a ng-click="forgotPassword()">I forgot my password</a><br>
						<a ng-click="showRegistrationForm()" class="text-center">Register a new membership</a>-->
						
					  </div>
					</div><!-- /.col -->
					<div class="col-xs-4">
					  <button type="submit" class="btn btn-primary btn-block btn-flat"  ng-disabled="loginForm.$invalid" ng-click="login()">Sign In</button>
					</div><!-- /.col -->
				  </div>
				</form>

				<div class="social-auth-links text-center">
				  <!--<p>- OR -</p>
				  <!-- <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
				  <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
				 --></div><!-- /.social-auth-links -->

				<!--<a ng-click="forgotPassword()">I forgot my password</a><br>
				<a ng-click="showRegistrationForm()" class="text-center">Register a new membership</a>-->

			  </div><!-- /.login-box-body -->
			</div><!-- /.login-box -->
			<div class="fh5co-contact">
				<div class="container">
					<div class="col-md-12 animate-box contact-box">
							<div class="alert alert-success" ng-if="successMessage != ''">
							  <strong>Thank you!</strong>We will get back you soon.
							</div>
							<div class="alert alert-danger" ng-if="errMessage != ''">
							  <strong>Error!</strong>{{ errMessage }}
							</div>
							<h3>Interest In Trail !</h3>
							<form id="contactForm" name="contactForm">
								<div class="row form-group">
									<div class="col-md-6">
										<!-- <label for="fname">First Name</label> -->
										<input type="text" id="fname" class="form-control" ng-Model="contactUsObj.fname"  placeholder="Your firstname" required>
									</div>
									<div class="col-md-6">
										<!-- <label for="lname">Last Name</label> -->
										<input type="text" id="lname" class="form-control" ng-Model="contactUsObj.lname" placeholder="Your lastname" required>
									</div>
								</div>
								<div class="row form-group">
									<div class="col-md-6">
										<!-- <label for="fname">First Name</label> -->
										<input type="text" id="mobile" class="form-control" ng-Model="contactUsObj.mobile" placeholder="Mobile Number">
									</div>
									<div class="col-md-6">
										<!-- <label for="lname">Last Name</label>  -->
										<input type="text" id="landline" class="form-control" ng-Model="contactUsObj.landline" placeholder="Office Number">
									</div>
								</div>

								<div class="row form-group">
									<div class="col-md-12">
										<!-- <label for="email">Email</label> -->
										<input type="text" id="email" class="form-control" ng-Model="contactUsObj.email" placeholder="Your email address" required>
									</div>
								</div>

								<!--<div class="row form-group">
									<div class="col-md-12">
										 
										<input type="text" id="subject" class="form-control" placeholder="Your subject of this message">
									</div>
								</div>-->

								<div class="row form-group">
									<div class="col-md-12">
										<!-- <label for="message">Message</label>  -->
										<textarea name="message" id="message" cols="30" rows="10"  ng-Model="contactUsObj.message"  class="form-control" placeholder="Let us know your queries"></textarea>
									</div>
								</div>
								<div class="form-group">
									<button type="submit" value="Send Message" ng-disabled="contactForm.$invalid" ng-click="sendMessage()" class="btn btn-primary">Send Message</button>
								</div>

							</form>		
					</div>
				</div>
			</div>
			
			
		</div>	
		
								 
		
		
	</nav>
	
	<aside id="fh5co-hero">
		<div class="flexslider">
			<ul class="slides">
		   	<li style="background-image: url(./login/images/slide_3.jpg);">
		   		<div class="overlay-gradient"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 text-center slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Discover Global Upstream <span class="redtextcolor boldtext">Market</span> Dynamics </h1>
									<!--<h2>Brought to you by <a href="#" target="_blank">freehtml5.co</a></h2>
									<p><a class="btn btn-danger btn-lg" href="#">Start Learning Now!</a></p>-->
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		   	<li style="background-image: url(./login/images/slide_2.jpg);">
		   		<div class="overlay-gradient"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 text-center slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Refined Downstream <span class="redtextcolor boldtext">Industry Data</span> at your finger tips </h1>
									<!--<h2>Brought to you by <a href="#" target="_blank">freehtml5.co</a></h2>
									<p><a class="btn btn-danger btn-lg btn-learn" href="#">Start Learning Now!</a></p>-->
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>
		   	<li style="background-image: url(./login/images/slide_1.jpg);">
		   		<div class="overlay-gradient"></div>
		   		<div class="container">
		   			<div class="row">
			   			<div class="col-md-8 col-md-offset-2 text-center slider-text">
			   				<div class="slider-text-inner">
			   					<h1>Explore what <span class="redtextcolor boldtext">LNG</span> Industry has to Offer </h1>
									<!--<h2>Brought to you by <a href="#" target="_blank">freehtml5.co</a></h2>
									<p><a class="btn btn-danger btn-lg btn-learn" href="#">Start Learning Now!</a></p>-->
			   				</div>
			   			</div>
			   		</div>
		   		</div>
		   	</li>		   	
		  	</ul>
	  	</div>
	</aside>

	<div id="fh5co-course-categories">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-6 col-md-offset-3 text-center fh5co-heading">
					<h2>Categories</h2>
					<!--<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-og-exploration"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Exploration & Production</a></h3>
							<!--<p> <span class="redtextcolor">4000</span>+ Block Details, <span class="redtextcolor">7000</span>+ Field Details <span class="redtextcolor">3500</span>+ Oil Field wise Production  <span class="redtextcolor">3200</span>+ Gas Field wise Production & Company and Country Oil and Gas Production.</p>-->
								<p>4000+ Block Details</p>
								<p>7000+ Field Details</p>
								<p>3500+ Oil Field wise Production</p>
								<p>3200+ Gas Field wise Production</p>
								<p>Company and Country Oil and Gas Production</p>

						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-og-lng"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Lng</a></h3>
							<!--<p><span class="redtextcolor">300</span>+ terminal details, <span class="redtextcolor">400</span>+ small and medium scale LNG terminals and <span class="redtextcolor">70</span>+ markets analysis, Capital Expenditure Forecasts, Supply Demand Balance Outlook.<!--, Trade, Prices and Market Value Datasets-></p>-->
								<p>300+ Terminal Details</p>
								<p>400+ Small and Medium Scale LNG Terminals</p>
								<p>70+ Markets Analysis</p>
								<p>Capital Expenditure Forecasts</p>
								<p>Supply- Demand Balance Outlook</p>
								<p> Trade, Prices and Market Value Datasets</p>

						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-og-pipeline"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Pipeline</a></h3>
							<!--<p><span class="redtextcolor">3000</span>+ oil, gas and product transmission pipelines, Routes, Length, Diameter, Capacity, Capex included,Cross country dynamics analyzed<span class="redtextcolor">100</span>countries included.</p>-->
								<p>3000+ oil, gas and product transmission pipelines</p>
								<p>Routes, Length, Diameter, Capacity, Capex included</p>
								<p>Cross country dynamics analyzed</p>
								<p>100+ countries included</p>
								<p>&nbsp;</p>
								<p>&nbsp;</p>

						</div>
					</div>
				</div>
		 		<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-og-refineries"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Refineries</a></h3>
							<!--<p><span class="redtextcolor">850</span>+ refinery details, <span class="redtextcolor">120</span>markets analysis, Products Supply- Demand Balance Outlook and Capital Expenditure Forecasts.</p>-->
								<p>850+ Refinery Details</p>
								<p>120 Markets Analysis</p>
								<p>Products Supply- Demand Balance Outlook</p>
								<p>Capital Expenditure Forecasts</p>

						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-og-storage"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Storage</a></h3>
							<!--<p><span class="redtextcolor">3000</span>Storage Terminal Details, <span class="redtextcolor">120</span>markets analysis, Products Supply- Demand Balance Outlook and Capital Expenditure Forecasts.</p>-->
								<p>3000 Storage Terminal Details</p>
								<p>120 Markets Analysis</p>
								<p>Products Supply- Demand Balance Outlook</p>
								<p>Capital Expenditure Forecasts</p>

						</div>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-bar-graph"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Reports</a></h3>
							<!--<p>Multi-client Studies, Consulting works, Monthly Publications and Annual Outlooks</p>-->
								<p>Multi-client Studies</p>
								<p>Consulting works</p>
								<p>Monthly Publications</p>
								<p>Annual Outlooks</p>

						</div>
					</div>
				</div>
				<!--<div class="col-md-3 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-reports"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Language</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
						</div>
					</div>
				</div>
				<div class="col-md-3 col-sm-6 text-center animate-box">
					<div class="services">
						<span class="icon">
							<i class="icon-world"></i>
						</span>
						<div class="desc">
							<h3><a href="#">Web &amp; Programming</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
						</div>
					</div>
				</div>-->
			</div>
		</div>
	</div>

	<!--<div id="fh5co-counter" class="fh5co-counters" style="background-image: url(images/img_bg_4.jpg);" data-stellar-background-ratio="0.5">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="row">
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="icon-world"></i></span>
							<span class="fh5co-counter js-counter" data-from="0" data-to="3297" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">Foreign Followers</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="icon-study"></i></span>
							<span class="fh5co-counter js-counter" data-from="0" data-to="3700" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">Students Enrolled</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="icon-bulb"></i></span>
							<span class="fh5co-counter js-counter" data-from="0" data-to="5034" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">Classes Complete</span>
						</div>
						<div class="col-md-3 col-sm-6 text-center animate-box">
							<span class="icon"><i class="icon-head"></i></span>
							<span class="fh5co-counter js-counter" data-from="0" data-to="1080" data-speed="5000" data-refresh-interval="50"></span>
							<span class="fh5co-counter-label">Certified Teachers</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>-->

	<!--<div id="fh5co-course">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-6 col-md-offset-3 text-center fh5co-heading">
					<h2>Our Course</h2>
					<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 animate-box">
					<div class="course">
						<a href="#" class="course-img" style="background-image: url(images/project-1.jpg);">
						</a>
						<div class="desc">
							<h3><a href="#">Web Master</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
							<span><a href="#" class="btn btn-danger btn-sm btn-course">Take A Course</a></span>
						</div>
					</div>
				</div>
				<div class="col-md-6 animate-box">
					<div class="course">
						<a href="#" class="course-img" style="background-image: url(images/project-2.jpg);">
						</a>
						<div class="desc">
							<h3><a href="#">Business &amp; Accounting</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
							<span><a href="#" class="btn btn-danger btn-sm btn-course">Take A Course</a></span>
						</div>
					</div>
				</div>
				<div class="col-md-6 animate-box">
					<div class="course">
						<a href="#" class="course-img" style="background-image: url(images/project-3.jpg);">
						</a>
						<div class="desc">
							<h3><a href="#">Science &amp; Technology</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
							<span><a href="#" class="btn btn-danger btn-sm btn-course">Take A Course</a></span>
						</div>
					</div>
				</div>
				<div class="col-md-6 animate-box">
					<div class="course">
						<a href="#" class="course-img" style="background-image: url(images/project-4.jpg);">
						</a>
						<div class="desc">
							<h3><a href="#">Health &amp; Psychology</a></h3>
							<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
							<span><a href="#" class="btn btn-danger btn-sm btn-course">Take A Course</a></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>-->

	<div id="fh5co-testimonial" style="background-image: url(./login/images/school.jpg);">
		<div class="overlay"></div>
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-6 col-md-offset-3 text-center fh5co-heading">
					<h2><span>Testimonials</span></h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 col-md-offset-1">
					<div class="row animate-box">
						<div class="owl-carousel owl-carousel-fullwidth">
							<div class="item">
								<div class="testimony-slide active text-center">
									<div class="user" style="background-image: url(./login/images/person1.jpg);"></div>
									<span><br><small></small></span>
									<blockquote>
										<p>&ldquo; Highly reliable and accurate forecasts - BDM, Oil and Gas Company in Asia Pacific.&rdquo;</p>
									</blockquote>
								</div>
							</div>
							<div class="item">
								<div class="testimony-slide active text-center">
									<div class="user" style="background-image: url(./login/images/person2.jpg);"></div>
									<span><br><small></small></span>
									<blockquote>
										<p>&ldquo;We love Custom Support extended by the team - Indonesia Based Construction Player.&rdquo;</p>
									</blockquote>
								</div>
							</div>
							<div class="item">
								<div class="testimony-slide active text-center">
									<div class="user" style="background-image: url(./login/images/person3.jpg);"></div>
									<span><br><small></small></span>
									<blockquote>
										<p>&ldquo;You have the most comprehensive collection of small LNG terminals - Leading FLNG Company.&rdquo;</p>
									</blockquote>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--<div id="fh5co-blog">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
					<h2>Blog &amp; Events</h2>
					<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
				</div>
			</div>
			<div class="row row-padded-mb">
				<div class="col-md-4 animate-box">
					<div class="fh5co-event">
						<div class="date text-center"><span>15<br>Mar.</span></div>
						<h3><a href="#">USA, International Triathlon Event</a></h3>
						<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						<p><a href="#">Read More</a></p>
					</div>
				</div>
				<div class="col-md-4 animate-box">
					<div class="fh5co-event">
						<div class="date text-center"><span>15<br>Mar.</span></div>
						<h3><a href="#">USA, International Triathlon Event</a></h3>
						<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						<p><a href="#">Read More</a></p>
					</div>
				</div>
				<div class="col-md-4 animate-box">
					<div class="fh5co-event">
						<div class="date text-center"><span>15<br>Mar.</span></div>
						<h3><a href="#">New Device Develope by Microsoft</a></h3>
						<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						<p><a href="#">Read More</a></p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4">
					<div class="fh5co-blog animate-box">
						<a href="#" class="blog-img-holder" style="background-image: url(images/project-1.jpg);"></a>
						<div class="blog-text">
							<h3><a href="#">Healty Lifestyle &amp; Living</a></h3>
							<span class="posted_on">March. 15th</span>
							<span class="comment"><a href="">21<i class="icon-speech-bubble"></i></a></span>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						</div> 
					</div>
				</div>
				<div class="col-lg-4 col-md-4">
					<div class="fh5co-blog animate-box">
						<a href="#" class="blog-img-holder" style="background-image: url(images/project-2.jpg);"></a>
						<div class="blog-text">
							<h3><a href="#">Healty Lifestyle &amp; Living</a></h3>
							<span class="posted_on">March. 15th</span>
							<span class="comment"><a href="">21<i class="icon-speech-bubble"></i></a></span>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						</div> 
					</div>
				</div>
				<div class="col-lg-4 col-md-4">
					<div class="fh5co-blog animate-box">
						<a href="#" class="blog-img-holder" style="background-image: url(images/project-3.jpg);"></a>
						<div class="blog-text">
							<h3><a href="#">Healty Lifestyle &amp; Living</a></h3>
							<span class="posted_on">March. 15th</span>
							<span class="comment"><a href="">21<i class="icon-speech-bubble"></i></a></span>
							<p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
						</div> 
					</div>
				</div>
			</div>
		</div>
	</div>-->

	<!--<div id="fh5co-pricing" class="fh5co-bg-section">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-6 col-md-offset-3 text-center fh5co-heading">
					<h2>Plan &amp; Pricing</h2>
					<p>Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab aliquam dolor eius.</p>
				</div>
			</div>
			<div class="row">
				<div class="pricing pricing--rabten">
					<div class="col-md-4 animate-box">
						<div class="pricing__item">
							<div class="wrap-price">
								 <!-- <div class="icon icon-user2"></div> ->
	                     <h3 class="pricing__title">Trial</h3>
	                     <!-- <p class="pricing__sentence">Single user license</p> ->
							</div>
                     <div class="pricing__price">
                        <span class="pricing__anim pricing__anim--1">
								<span class="pricing__currency">$</span>0
                        </span>
                        <span class="pricing__anim pricing__anim--2">
								<span class="pricing__period">per year</span>
                        </span>
                     </div>
                     <div class="wrap-price">
                     	<ul class="pricing__feature-list">
	                        <li class="pricing__feature">One Day Trial</li>
	                        <li class="pricing__feature">Limited Courses</li>
	                        <li class="pricing__feature">Free 3 Lessons</li>
	                        <li class="pricing__feature">No Supporter</li>
	                        <li class="pricing__feature">No Tutorial</li>
	                        <li class="pricing__feature">No Ebook</li>
	                         <li class="pricing__feature">Limited Registered User</li>
	                     </ul>
	                     <button class="pricing__action">Choose plan</button>
                     </div>
                  </div>
					</div>
					<div class="col-md-4 animate-box">
						<div class="pricing__item">
							<div class="wrap-price">
								<!-- <div class="icon icon-store"></div> ->
	                     <h3 class="pricing__title">Silver</h3>
	                     <!-- <p class="pricing__sentence">Up to 5 users</p> ->
							</div>
                     <div class="pricing__price">
                        <span class="pricing__anim pricing__anim--1">
								<span class="pricing__currency">$</span>79
                        </span>
                        <span class="pricing__anim pricing__anim--2">
								<span class="pricing__period">per year</span>
                        </span>
                     </div>
                     <div class="wrap-price">
                     	<ul class="pricing__feature-list">
	                        <li class="pricing__feature">One Year Standard Access</li>
	                        <li class="pricing__feature">Limited Courses</li>
	                        <li class="pricing__feature">300+ Lessons</li>
	                        <li class="pricing__feature">Random Supporter</li>
	                        <li class="pricing__feature">View Only Ebook</li>
	                        <li class="pricing__feature">Standard Tutorials</li>
	                         <li class="pricing__feature">Unlimited Registered User</li>
	                     </ul>
	                     <button class="pricing__action">Choose plan</button>
                     </div>
                 </div>
					</div>
					<div class="col-md-4 animate-box">
                  <div class="pricing__item">
                  	<div class="wrap-price">
                  		<!-- <div class="icon icon-home2"></div> ->
	                     <h3 class="pricing__title">Gold</h3>
	                     <!-- <p class="pricing__sentence">Unlimited users</p> ->
							</div>
                     <div class="pricing__price">
                        <span class="pricing__anim pricing__anim--1">
								<span class="pricing__currency">$</span>499
                        </span>
                        <span class="pricing__anim pricing__anim--2">
								<span class="pricing__period">per year</span>
                        </span>
                     </div>
                     <div class="wrap-price">
                     	<ul class="pricing__feature-list">
	                        <li class="pricing__feature">Life Time Access</li>
	                        <li class="pricing__feature">Unlimited All Courses</li>
	                        <li class="pricing__feature">7000+ Lessons &amp; Growing</li>
	                        <li class="pricing__feature">Free Supporter</li>
	                        <li class="pricing__feature">Free Ebook Downloads</li>
	                        <li class="pricing__feature">Premium Tutorials</li>
	                         <li class="pricing__feature">Unlimited Registered User</li>
	                     </ul>
	                     <button class="pricing__action">Choose plan</button>
                     </div>
                  </div>
               </div>
            </div>
			</div>
		</div>
	</div>-->

	<!--<div id="fh5co-register" style="background-image: url(images/img_bg_2.jpg);">
		<div class="overlay"></div>
		<div class="row">
			<div class="col-md-8 col-md-offset-2 animate-box">
				<div class="date-counter text-center">
					<h2>Get 400 of Online Courses for Free</h2>
					<h3>By Mike Smith</h3>
					<div class="simply-countdown simply-countdown-one"></div>
					<p><strong>Limited Offer, Hurry Up!</strong></p>
					<p><a href="#" class="btn btn-danger btn-lg btn-reg">Register Now!</a></p>
				</div>
			</div>
		</div>
	</div>-->

	<!--<div id="fh5co-gallery" class="fh5co-bg-section">
		<div class="row text-center">
			<h2><span>Instagram Gallery</span></h2>
		</div>
		<div class="row">
			<div class="col-md-3 col-padded">
				<a href="#" class="gallery" style="background-image: url(images/project-5.jpg);"></a>
			</div>
			<div class="col-md-3 col-padded">
				<a href="#" class="gallery" style="background-image: url(images/project-2.jpg);"></a>
			</div>
			<div class="col-md-3 col-padded">
				<a href="#" class="gallery" style="background-image: url(images/project-3.jpg);"></a>
			</div>
			<div class="col-md-3 col-padded">
				<a href="#" class="gallery" style="background-image: url(images/project-4.jpg);"></a>
			</div>
		</div>
	</div>-->

	<footer id="fh5co-footer" role="contentinfo" style="background-image: url(./login/images/img_bg_4.jpg);">
		<div class="overlay"></div>
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-3 fh5co-widget">
					<h3>About OG Explorer</h3>
					<p>OG Explorer is a leading provider of Oil and Gas research, strategic and financial analysis. It is an essential tool for companies making investment and new entry/expansion decisions.</p>
				</div>
				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1 fh5co-widget">
					<!--<h3>Learning</h3>
					<ul class="fh5co-footer-links">
						<li><a href="#">About</a></li>
						<li><a href="#">Blog</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="#">Terms</a></li>
						<!--<li><a href="#">Meetups</a></li>->
					</ul>-->
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1 fh5co-widget">
					<!--<h3>Learn &amp; Grow</h3>
					<ul class="fh5co-footer-links">
						<li><a href="#">Blog</a></li>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Testimonials</a></li>
						<li><a href="#">Handbook</a></li>
						<li><a href="#">Held Desk</a></li>
					</ul>-->
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1 fh5co-widget">
					<h3>Contact us</h3>
					<ul class="fh5co-footer-links">
						<li><a href="#">sales@oganalysis.com</a></li>
						<!--<li><a href="#">Visual Assistant</a></li>
						<li><a href="#">System Analysis</a></li>
						<li><a href="#">Advertise</a></li>-->
					</ul>
				</div>

				<!--<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1 fh5co-widget">
					<h3>Legal</h3>
					<ul class="fh5co-footer-links">
						<li><a href="#">Find Designers</a></li>
						<li><a href="#">Find Developers</a></li>
						<li><a href="#">Teams</a></li>
						<li><a href="#">Advertise</a></li>
						<li><a href="#">API</a></li>
					</ul>
				</div>-->
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<small class="block">&copy; 2017. All Rights Reserved.</small> 
						<!--<small class="block">Designed by <a href="#" target="_blank"></a>  <a href="http://unsplash.co/" target="_blank">Unsplash</a> &amp; <a href="https://www.pexels.com/" target="_blank">Pexels</a></small>-->
					</p>
				</div>
			</div>

		</div>
	</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="./login/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="./login/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="./login/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="./login/js/jquery.waypoints.min.js"></script>
	<!-- Stellar Parallax -->
	<script src="./login/js/jquery.stellar.min.js"></script>
	<!-- Carousel -->
	<script src="./login/js/owl.carousel.min.js"></script>
	<!-- Flexslider -->
	<script src="./login/js/jquery.flexslider-min.js"></script>
	<!-- countTo -->
	<script src="./login/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="./login/js/jquery.magnific-popup.min.js"></script>
	<script src="./login/js/magnific-popup-options.js"></script>
	<!-- Count Down 
	<script src="js/simplyCountdown.js"></script>-->
	<!-- Main -->
	<script src="./login/js/main.js"></script>
	
	<script src="./js/plugins/angular/angular.min.js"></script>
	
	
	<script>
			var d = new Date(new Date().getTime() + 1000 * 120 * 120 * 2000);
			$(".login-background").hide();
			// default example
			/*simplyCountdown('.simply-countdown-one', {
				year: d.getFullYear(),
				month: d.getMonth() + 1,
				day: d.getDate()
			});

			//jQuery example
			$('#simply-countdown-losange').simplyCountdown({
				year: d.getFullYear(),
				month: d.getMonth() + 1,
				day: d.getDate(),
				enableUtc: false
			});*/
			$('#loginShow').on('click',function(){
				//$("#loginPage").toggle();
				
				if($(".login-background").css('display') == 'block' && $(".login-box").css('display') == 'block'){
					$(".login-background").hide();//('display','block');
				}else{
					$(".contact-box").hide();
					$(".login-box").show();
					var displayPrpt = $(".login-background").css('display');
					if(displayPrpt == 'none'){
						$(".login-background").show();//('display','block');
					}else{
						if($(".login-box").css('display') == 'block'){
							$(".contact-box").hide();
							$(".login-box").show();
						}else{
							$(".login-background").hide();//('display','block');
						}
					}
				}
			});
			
			$('#contactUsShow').on('click',function(){
				//$("#loginPage").toggle();
				if($(".login-background").css('display') == 'block' && $(".contact-box").css('display') == 'block'){
					$(".login-background").hide();//('display','block');
				}else{
					$(".login-box").hide();
					$(".contact-box").show();
					var displayPrpt = $(".login-background").css('display');
					if(displayPrpt == 'none'){
						$(".login-background").show();//('display','block');
					}else{
						if($(".contact-box").css('display') == 'block'){
							$(".contact-box").show();
							$(".login-box").hide();
						}else{
							$(".login-background").hide();//('display','block');
						}
					}
				}
			});
			
			 $(function () {
				   
					/*$('#rememberMe').iCheck({
					  checkboxClass: 'icheckbox_square-blue',
					  radioClass: 'iradio_square-blue',
					  increaseArea: '20%' // optional
					});*/
			  });
			  
			  angular.module('LoginApp', []);
			  angular.module('LoginApp')
					.service("URL", function(){
						this.contextPath = "http://localhost:8080";
						this.loginUrl = this.contextPath+'/oganalysis/login';
						this.contactUsUrl = this.contextPath+'/oganalysis/contactus';
						this.headerRequest = {	// TODO: Once api need user token then uncomment this.
								"Content-type": 'application/x-www-form-urlencoded',
								'Access-Control-Allow-Origin':'*'
							};
					})
			  angular.module('LoginApp').controller('LoginCtrl', function($scope,$rootScope,URL,$http, $timeout) {
				  
				  $scope.login = function(){
						   var request = {
								method: 'POST',
								url: URL.loginUrl,
								data: $.param($scope.loginObj),
								headers: URL.headerRequest
							};
							 
							$http(request).success(function (resp){
								 if(resp.st == 'correct'){
									localStorage.setItem("oganalysis-ud-name",resp.user);
									window.location.href = "index.html#/"
								 }else if(resp.st =='incorrect'){
									 //alert("login failed!")
									 $scope.errorMessage = "Invalid email or password.";
									 
									 $timeout(function(){
										 $scope.errorMessage = "";
									 },3000)
								 }
								 
							}).error(function (resp){
								console.log("Error")
							});
				  }
				  
				  $scope.sendMessage = function(){
						var validFlag = true;
						if($scope.contactUsObj.mobile == "" &&  $scope.contactUsObj.landline == ""){
							validFlag = false;
							$scope.errMessage = "Please enter either mobile number or office number.";
							$timeout(function(){
								 $scope.errMessage = "";
							},3000)
							
						}
						 
						   var request = {
								method: 'POST',
								url: URL.contactUsUrl,
								data: $.param($scope.contactUsObj),
								headers: URL.headerRequest
							};
							if(validFlag){
								$http(request).success(function (resp){
								 if(resp == 'success'){
									 $scope.successMessage = "Invalid email or password.";
								 	 $timeout(function(){
										 $scope.successMessage = "";
										 $(".login-background").hide();//('display','block');
									 },3000)
									
								 }else if(resp.error =='appError'){
									 $scope.errMessage = "Unable to send message now.Please try again after some time.";
									 $timeout(function(){
										 $scope.errMessage = "";
									 },3000)
									
								 }
								 
								}).error(function (resp){
									console.log("Error")
								});
						 	}
				  }
				  
				  $scope.signup = function(){
					  //alert("signup")
				  }
				  
				  $scope.init = function(){
					  $scope.loginObj = {
							username : "",
							password: ""
					  };
					  
					  $scope.contactUsObj = {
							fname : "",
							lname: "",
							mobile:"",
							landline:"",
							email:"",
							message :""
					  };
					  
					  $scope.errorMessage = ""; 
					  $scope.successMessage = "";
					  $scope.errMessage =  "";
						  
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

 
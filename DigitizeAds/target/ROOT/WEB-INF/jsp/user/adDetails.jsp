<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>The Bakersfield Californian Business Directory: Coupons, restaurants, entertainment, and hotels in Bakersfield, CA </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <!-- External Css -->
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/themify-icons.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/et-line.css" />

    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/owl.carousel.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/slick.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/plyr.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/jquery.timepicker.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/jquery.nstSlider.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/datepicker.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/select2.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/wickedpicker.min.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/select2-bootstrap.min.css" />

    <!-- leaflet -->
    <link rel="stylesheet" href="<%=assetsPath %>/assets/leaflet/css/leaflet.css">
    <link rel="stylesheet" href="<%=assetsPath %>/assets/leaflet/css/MarkerCluster.css">
    <link rel="stylesheet" href="<%=assetsPath %>/assets/leaflet/css/MarkerCluster.Default.css">

    <!-- Custom Css --> 
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/main.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500%7CSignika:400,600,700" rel="stylesheet">

    <!-- Favicon -->
    <link rel="icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=assetsPath %>/bakers_image/favicon.png">

  </head>
  <body>

    <div class="preloader">
      <div class='pin'></div>
      <div class='pulse'></div>
    </div>
 
	
    <header class="header-bg-1">
      <jsp:include page="header.jsp" flush="true"/>
      <div class="header-banner text-center">
        <div class="container">
          <div class="row">
            <div class="col">
            <h1>FOOD AND BEVERAGE</h1>
              
            </div>
          </div>
        </div>
      </div>
	  
      
    </header>
 
   <div class="search-listing-wrap filter-sidebar">
      <div class="listing-wrap grey-bg">
	  
        <div class="container">
		<div class="row padding-bottom-30">
          <div class="col">
            <div class="section-header">
              <h2><img class="render-type-image loaded" src="<%=assetsPath %>/bakers_image/stprint.jpg" ></h2>
             </div>
          </div>
        </div>
		<div class="row lat-print back-white padding-top-20 padding-bottom-20 border-bottom">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 sm-date">
		<p ><span>Date:</span> April 26, 2020 <button class="btn btn-sm btn-green">Active Ad</button></p>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 right sm-btn-icon">
		<i class="fas fa-ellipsis-v right"></i>
		</div>
		</div>
		<div class="row lat-print back-white padding-top-20 ">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 btm-pad">
		<p class="blue font-16 line"><a href="<%=contextPath %>/business_slug/business_id/">The Palms at San Lauren by Blue Mountain</a></p >
		<span><a href="<%=contextPath %>/category/category_slug/category_u_id/">Public Services</a></span>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 right btm-10 icons-pad">
		<div class="right">
		<a class="cta center padding-right-20 " href="" target="_blank" rel="">
				<i class="fas fa-phone fa-2x icon-blue" aria-hidden="true"></i>
				<p class="blue">Call</p>
				</a>
			<a class="cta center padding-right-20" href="" target="_blank" rel="">
				<i class="material-icons icon-blue">directions</i>
				<p class="blue">Directions</p>
				</a>	
				</div>
		</div>
		</div>
		<div class="row padding-bottom-30">
          <div class="col">
            <div class="section-header">
            
             </div>
          </div>
        </div>
		
		<div class="row lat-print back-white padding-top-20 padding-bottom-20 border-bottom">
		<div class="col-lg-8">
			   <h3 class="about-cont">Text &nbsp;&nbsp;<span class="Show"> <i class="fa fa-angle-right"></i></span>
<span class="Hide"><i class="fa fa-angle-down"></i></span></h3>
			   <p class="font-16" id="target">LET OUR FAMILY CARE FOR YOURS
Bakersfield's newest senior community for assisted living and memory care
ALMON
fresh fook
5300 Hageman Road  Bakersfield, CA 93308
(661) 218-8330 PHONE
THE PALMS alysia@thepalmsatsanlauren.com EMAIL
AT SAN LAUREN www.PalmsSeniorLiving.com WEB
by Blue Mountain Senior Living
Assisted Living and Memory Care. License 157208915
 LET OUR FAMILY CARE FOR YOURS Bakersfield's newest senior community for assisted living and memory care ALMON fresh fook 5300 Hageman Road  Bakersfield, CA 93308 (661) 218-8330 PHONE THE PALMS alysia@thepalmsatsanlauren.com EMAIL AT SAN LAUREN www.PalmsSeniorLiving.com WEB by Blue Mountain Senior Living Assisted Living and Memory Care. License 157208915</p>
			    </div>
				 </div>
		<div class="row lat-print back-white padding-top-20 padding-bottom-20">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
		<h3 class="about-cont center">Other Ads</h3>
		</div>
		<%for(int i=0;i<5;i++){ %>
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card">
            <a class="promo-card-inner" href="single_print_ad.html">
               <img src="<%=assetsPath %>/bakers_image/stprint.jpg">
             <p class="with-business-name">
              <span class="business-name"></span><br>
                 Apr-03-20 |
              <span class="valid-info"><span class="translation_missing">30 Days Left</span></span>
             </p>
             </a>

            </div>
         </div>
		 </div>
		 <%} %>
		 
		 </div>
         <div class="button-container"><a class="button" href="print_ads.html">See More Ads</a></div>
		 
        </div>
      </div>
    </div>
   
    <jsp:include page="footer.jsp" flush="true"/>
   


    <!-- Optional JavaScript --> 
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=assetsPath %>/assets/js/jquery.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/popper.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/bootstrap.min.js"></script>

    <script src="<%=assetsPath %>/assets/js/owl.carousel.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/isotope.pkgd.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/imagesloaded.pkgd.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/slick.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/plyr.js"></script>
    <script src="<%=assetsPath %>/assets/js/jquery.timepicker.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/jquery.countTo.js"></script>
    <script src="<%=assetsPath %>/assets/js/visible.js"></script>
    <script src="<%=assetsPath %>/assets/js/datepicker.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/select2.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/datepicker.en.js"></script>
    <script src="<%=assetsPath %>/assets/js/tinymce.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/jquery.nstSlider.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/wickedpicker.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/jquery.ajaxchimp.min.js"></script> 

    <!-- leaflet --> 
    <script src="<%=assetsPath %>/assets/leaflet/js/leaflet-src.js"></script>
    <script src="<%=assetsPath %>/assets/leaflet/js/leaflet.markercluster-src.js"></script>
    <script src="<%=assetsPath %>/assets/leaflet/js/dummylatlng.js"></script>

    <script src="<%=assetsPath %>/js/custom.js"></script>
    <script src="<%=assetsPath %>/js/searchMap.js"></script>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCo_UiZM19FOm6-Vpl42HXNDrpYwGHCzPo"></script>
    <script src="<%=assetsPath %>/js/map.js"></script>
    
  </body>
</html>
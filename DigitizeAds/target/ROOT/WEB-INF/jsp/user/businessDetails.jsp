<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Advertiser"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
Advertiser advertiserDetails = request.getAttribute(Constants.ADVERTISER_DETAILS)!=null?(Advertiser)request.getAttribute(Constants.ADVERTISER_DETAILS):null;
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

	
	 <jsp:include page="header.jsp" flush="true"/>
   
	
		      <div class="search-listing-wrap map-top">
      <div class="listing-map-block">
        <div id="searchmap" class="searchmap"></div>
      </div>
      <div class="listing-wrap grey-bg">
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="listing-search-block">
                <div class="row">
               <div class="col-lg-8">
			  <div class="section-header padding-bottom-20 left">
             <h2><%=advertiserDetails.getName() %></h2>
			   <p class="category"><a href="<%=contextPath %>/category/<%=Constants.toSlug(advertiserDetails.getCategory().getName()) %>/<%=EncryptionUtil.encode(advertiserDetails.getCategory().getCategoryId()+"") %>/"><%=advertiserDetails.getCategory().getName() %></a></p>
			  </h2>
			   </div>
			    </div>
			    <div class="col-lg-4">
				<a class="cta center padding-right-20" href="https://maps.google.com?daddr=16019 Maricopa Hwy+Oak Glen+CA+92399" target="_blank" rel="">
				<i class="material-icons icon-blue">directions</i>
				<p class="blue">Directions</p>
				</a>
				<a class="cta center padding-right-20 " href="<%=advertiserDetails.getWebsite() %>" target="_blank" rel="">
				<i class="fa fa-desktop fa-2x icon-blue" aria-hidden="true"></i>
				<p class="blue">Website</p>
				</a>
				<a class="cta center padding-right-20" href="https://maps.google.com?daddr=16019 Maricopa Hwy+Oak Glen+CA+92399" target="_blank" rel="">
				<i class="fa fa-share-alt fa-2x icon-blue" aria-hidden="true"></i>
				<p class="blue">Share</p>
				</a>
				</div>
			   </div>
			   <div class="row border-top padding-top-20 padding-bottom-20">
               <div class="col-lg-12">
			  <p class="font-16"><span class="padding-right-20"><i class="fas fa-map-marker-alt" aria-hidden="true"></i> <%=advertiserDetails.getAddress() %></span>
			   
			   <span><a class="cta" href="tel:(800) 807-6755"><i class="fa fa-phone" aria-hidden="true"></i> <%=advertiserDetails.getPhoneNumber() %></a></span></p>
			    </div>
				 </div>
				 <div class="row border-top padding-bottom-20">
               <div class="col-lg-7">
			   <h3 class="about-cont">About</h3>
			    <p class="font-16"><%=advertiserDetails.getAboutUs() %></p>
			    </div>
				<div class="col-lg-4 padding-left-20">
				
				
				<h3 class="about-cont">QUESTION OR COMMENT?</h3>
				<form action="#">
				<div class="listing-details-section listing-write-review">
				<div class="form-group">
                      <input type="text" class="form-control" placeholder="Your Name">
                    </div>
					<div class="form-group">
                     <input placeholder="Your e-mail address" class="form-control" type="email" name="from_email">
                    </div>
					<div class="form-group">
                  <textarea class="form-control" placeholder="Your Review"></textarea>
                </div>
				<button class="button">Submit </button>
				</div>
					</form>
				</div>
				 </div>
				 <div class="row">
				 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				 <h3 class="about-cont">FOLLOW US</h3>
				 </div></div>
				 
				 <div class="row border-top padding-bottom-20">
				 
				 <div class="col-lg-4">
				 <h3 class="about-cont">Face Book</h3>
				<div class="facebook-box">
        <iframe src="https://www.facebook.com/v2.12/plugins/page.php?adapt_container_width=true&amp;
    hide_cover=true&amp;show_facepile=false&amp;small_header=true&amp;tabs=timeline&amp;width=300&amp;
    height=328&amp;href=https://facebook.com/natgeo"></iframe> </div>
		 </div>
		  <div class="col-lg-4">
				 <h3 class="about-cont">Twitter</h3>
				<div class="twitter-box">
	           <a class="twitter-timeline" href="https://twitter.com/NatGeo?ref_src=twsrc%5Etfw">Tweets by NatGeo</a> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script></div>
		 </div>
		  <div class="col-lg-4">
				 <h3 class="about-cont">Instagram</h3>
				<div class="twitter-box">
        
<div class="container">	
<div id="instagram-feed-demo" class="instagram_feed"></div>
</div>

<script src="https://www.jqueryscript.net/demo/Instagram-Photos-Without-API-instagramFeed/jquery.instagramFeed.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
 (function($){
		$(window).on('load', function(){ 
			$.instagramFeed({
				'username': 'natgeo',
				'container': "#instagram-feed-demo",
				'display_profile': true,
				'display_biography': true,
				'display_gallery': true,
				'get_raw_json': false,
				'callback': null,
				'styling': true,
				'items': 36,
				'items_per_row': 6,
				'margin': 0.3
			});
		});
	})(jQuery);
  </script>

  
  <script src='https://www.jqueryscript.net/demo/Instagram-Photos-Without-API-instagramFeed/jquery.instagramFeed.js'></script>
<script src='https://code.jquery.com/jquery-1.12.4.min.js'></script>
</div>
		 </div>
		 </div>
				 
				 <div class="row border-top ">
				  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				  <h3 class="about-cont center">ADS</h3>
				   </div></div>
				  <div class="row lat-print">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card">
            <a class="promo-card-inner" href="single_print_ad.html">
               <img src="bakers_image/stprint.jpg">
             <p class="with-business-name">
              <span class="business-name">St Jude Dream Home</span><br>
                 Apr-03-20 |
              <span class="valid-info"><span class="translation_missing">30 Days Left</span></span>
             </p>
             </a>

            </div>
         </div>
		 </div>
		 
		 <div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card">
            <a class="promo-card-inner" href="single_print_ad.html">
               <img src="bakers_image/White_Forest.jpg">
             <p class="with-business-name">
              <span class="business-name">White Forest Nursery</span><br>
                Apr-03-20 |
              <span class="valid-info"><span class="translation_missing">30 Days Left</span></span>
             </p>
             </a>

            </div>
         </div>
		 </div>
		 
		 <div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card">
            <a class="promo-card-inner" href="single_print_ad.html">
               <img src="bakers_image/Tachi_Palace.jpg">
             <p class="with-business-name">
              <span class="business-name">Tachi Palace Hotel &amp; Casino</span><br>
                  Apr-02-20 |
              <span class="valid-info"><span class="translation_missing">29 Days Left</span></span>
             </p>
             </a>

            </div>
         </div>
		 </div>
		
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card">
            <a class="promo-card-inner" href="single_print_ad.html">
               <img src="bakers_image/Geico_Bakersfield.jpg">
             <p class="with-business-name">
              <span class="business-name">Geico - Bakersfield</span><br>
                 Apr-23-20 |
              <span class="valid-info"><span class="translation_missing">29 Days Left</span></span>
             </p>
             </a>

            </div>
         </div>
		 </div>
		 
		 
		 </div>
				 
              </div>
             
            </div>
          </div>
        </div>
      </div>
    </div> 
		  
		   <!-- Modal -->
    <div class="modal fade" id="accountPopup" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-body">
            <a href="#" class="close" data-dismiss="modal" aria-label="Close"><span class="ti-close"></span></a>
            <ul class="nav nav-tabs" id="myTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="loginaccount-tab" data-toggle="tab" href="#loginaccount" role="tab" aria-controls="loginaccount" aria-selected="true">
				<span class="ti-user"></span>Administration Login <!--<span>(Marketplace Management)</span>--></a>
              </li>
             
            </ul>
            <div class="tab-content" id="myTabContent"> 
              <div class="tab-pane fade show active" id="loginaccount" role="tabpanel" aria-labelledby="loginaccount-tab">
                <div class="access-form">
				
                  <form action="#">
                    <div class="form-group">
                      <input type="email" placeholder="Email Address" class="form-control">
                    </div>
                    <div class="form-group">
                      <input type="password" placeholder="Password" class="form-control">
                    </div>
                    <div class="more-option">
                      <div class="mt-0 terms">
                        <input class="custom-radio" type="checkbox" id="radio-4" name="termsandcondition">
                        <label for="radio-4">
                          <span class="dot"></span> Remember Me
                        </label>
                      </div>
                      <a href="#" class="forget-pass">Forget Password?</a>
                    </div>
					<div class="right padding-bottom-20">
                    <button class="button btn-block">Sign In</button>
					</div>
                  </form>
                  
                </div>
              </div>
             
            </div>
          </div>
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
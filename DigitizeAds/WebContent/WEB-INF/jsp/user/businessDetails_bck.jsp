<%@page import="com.digitizeads.modal.AdvertiserService"%>
<%@page import="com.digitizeads.modal.Coupon"%>
<%@page import="com.digitizeads.modal.AdvertiserGallery"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Advertiser"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
Advertiser advertiserDetails = request.getAttribute(Constants.ADVERTISER_DETAILS)!=null?(Advertiser)request.getAttribute(Constants.ADVERTISER_DETAILS):null;

ArrayList<AdvertiserGallery> advertiserGalleryList = request.getAttribute(Constants.ADVERTISER_GALLERY_LIST)!=null?(ArrayList<AdvertiserGallery>)request.getAttribute(Constants.ADVERTISER_GALLERY_LIST):null;
ArrayList<Coupon> couponList = request.getAttribute(Constants.ADVERTISER_COUPON_LIST)!=null?(ArrayList<Coupon>)request.getAttribute(Constants.ADVERTISER_COUPON_LIST):null;
ArrayList<AdvertiserService> advertiserServiceList = request.getAttribute(Constants.ADVERTISER_SERVICE_LIST)!=null?(ArrayList<AdvertiserService>)request.getAttribute(Constants.ADVERTISER_SERVICE_LIST):null;

%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta name="author" content="Encredia" />
	<meta name="description" content="<%=advertiserDetails.getAboutUs()%>">
	<meta name="url" content="<%=advertiserDetails.getAdvertiserId()%>/" /> 
	<meta name="title" content="<%=advertiserDetails.getName()%>">
	<meta name="image" content="http://encredia.com/assets/images/logo.png">
	
	<meta property="og:site_name" content="<%=advertiserDetails.getName()%>">  
  	<meta property="og:title" content="<%=advertiserDetails.getName()%>"> 
	  <meta property="og:description" content="<%=advertiserDetails.getAboutUs()%>"> 
	  <meta property="og:type" content="website">
	   <meta property="og:url" content="<%=advertiserDetails.getAdvertiserId()%>/" />
  
		<meta property="og:image" itemprop="image" content="http://encredia.com/assets/images/logo.png">	   
 <link rel="shortcut icon" href="<%=assetsPath %>/images/favicon.png" />
 
 
    <title><%=advertiserDetails.getName() %> | Community Directory</title>

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
  
  <style>

.img-wrapper {
  position: relative;
  margin-top: 15px;
}
.img-wrapper img {
  width: 100%;
}

.img-overlay {
  background: rgba(0, 0, 0, 0.7);
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: center;
          justify-content: center;
  -webkit-box-align: center;
          align-items: center;
  opacity: 0;
}
.img-overlay i {
  color: #fff;
  font-size: 3em;
}

#overlay {
  background: rgba(0, 0, 0, 0.7);
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  display: -webkit-box;
  display: flex;
  -webkit-box-pack: center;
          justify-content: center;
  -webkit-box-align: center;
          align-items: center;
  z-index: 999;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
#overlay img {
  margin: 0;
  width: 80%;
  height: auto;
  -o-object-fit: contain;
     object-fit: contain;
  padding: 5%;
}
@media screen and (min-width: 768px) {
  #overlay img {
    width: 60%;
  }
}
@media screen and (min-width: 1200px) {
  #overlay img {
    width: 50%;
  }
}

#nextButton {
  color: #fff;
  font-size: 2em;
  -webkit-transition: opacity 0.8s;
  transition: opacity 0.8s;
}
#nextButton:hover {
  opacity: 0.7;
}
@media screen and (min-width: 768px) {
  #nextButton {
    font-size: 3em;
  }
}

#prevButton {
  color: #fff;
  font-size: 2em;
  -webkit-transition: opacity 0.8s;
  transition: opacity 0.8s;
}
#prevButton:hover {
  opacity: 0.7;
}
@media screen and (min-width: 768px) {
  #prevButton {
    font-size: 3em;
  }
}

#exitButton {
  color: #fff;
  font-size: 2em;
  -webkit-transition: opacity 0.8s;
  transition: opacity 0.8s;
  position: absolute;
  top: 15px;
  right: 15px;
}
#exitButton:hover {
  opacity: 0.7;
}
@media screen and (min-width: 768px) {
  #exitButton {
    font-size: 3em;
  }
}
.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
    display: block;
    height: 100%;
}
</style>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma-carousel@4.0.4/dist/css/bulma-carousel.min.css">
  
<style>
  .image img {
    display: block;
    height: auto;
    width: 100%;
}
  .image {
    display: block;
    position: relative;
}
  .card-image {
    display: block;
    position: relative;
}
  .card {
    background-color: #fff;
    box-shadow: 0 0.5em 1em -0.125em rgb(10 10 10 / 10%), 0 0 0 1px rgb(10 10 10 / 2%);
    color: #4a4a4a;
    max-width: 100%;
    position: relative;
}
.card-content {
    padding: 10px !important;
}
.carousel{
  overflow:hidden;
}
.m-padding a{
  margin: 5px;
  
  display: block;
  border-radius: 5px;
  overflow:hidden;
}

.slider-navigation-previous {
  left: 10px;
}
.slider-navigation-next {
    right: 10px;
}

</style>

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
				<a class="cta center padding-right-20 track_direction" href="https://maps.google.com?daddr=<%=advertiserDetails.getAddress() %>" target="_blank" rel="">
				<i class="material-icons icon-blue">directions</i>
				<p class="blue">Directions</p>
				</a>
				<a class="cta center padding-right-20 track_website" href="<%=advertiserDetails.getWebsite() %>" target="_blank" rel="">
				<i class="fa fa-desktop fa-2x icon-blue" aria-hidden="true"></i>
				<p class="blue">Website</p>
				</a>
				<a class="cta center padding-right-20 track_share" href="#" target="_blank" rel="">
				<i class="fa fa-share-alt fa-2x icon-blue" aria-hidden="true"></i>
				<p class="blue">Share</p>
				</a>
				</div> 
			   </div>
			   <div class="row border-top padding-top-20 padding-bottom-20">
               <div class="col-lg-12">
			  <p class="font-16"><span class="padding-right-20"><i class="fas fa-map-marker-alt" aria-hidden="true"></i> <%=advertiserDetails.getAddress() %></span>
			   
			   <span><a class="cta" href="tel:<%=advertiserDetails.getPhoneNumber() %>"><i class="fa fa-phone" aria-hidden="true"></i> <%=advertiserDetails.getPhoneNumber() %></a></span></p>
			    </div>
				 </div>
				 <div class="row border-top padding-bottom-20">
               <div class="col-lg-8">
			   <h3 class="about-cont">About</h3>
			    <p class="font-16"><%=advertiserDetails.getAboutUs() %></p>
			     
			     <%if(advertiserServiceList!=null && advertiserServiceList.size()>0){ %>
			     <h3 class="border-top about-cont padding-top-20 padding-bottom-20">Products and Services</h3>
			     
			    <!-- Start Carousel -->
				<div id="trend-slide" class="carousel">
				<%for(AdvertiserService advertiserService : advertiserServiceList){ %>
					<div class="item-1 m-padding">
						
            <a href="#">
              <div class="card is-shadowless is-slightly-rounded">
                <div class="card-image">
                  <figure class="image">
                      <img src="https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/-64.866200,18.348780,14/500x300?access_token=pk.eyJ1IjoiYWR3aXRnbG9iYWwiLCJhIjoiY2tpNGsweW1sMHFzejJ0c3l3dHlkbnBoaCJ9.qd3vQkywU1t49iPFjTVYFw">
                  </figure>
              </div>
              <div class="card-content">
                <div class="content">
                  
                  <p>
                    <span style="font-size:16px;font-weight: 500;"><%=advertiserService.getTitle() %></span>
                  </p>
                 
                  <p>
                    <span class="subtitle is-7"><%=advertiserService.getDescription() %></span>
                  </p>
                </div>
              </div>

            </div>

            </a>
		 
					</div>
					<%} %>

				</div>
				<!-- End Carousel -->
		
			    <%} %>
			    
			    </div> 
			    
			    
				<div class="col-lg-4 padding-left-20">
				<%if(advertiserGalleryList!=null && advertiserGalleryList.size()>0){%>
					<section id="gallery" class="padding-bottom-20">
					  
					    <div id="image-gallery">
					    	<h3 class="about-cont">Gallery</h3>
					      <div class="row">
					       <%for(AdvertiserGallery advertiserGallery : advertiserGalleryList){%>
						        <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 image" style="padding: 2px 5px">
						          <div class="img-wrapper track_imageGallery">
						            <a href="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserGallery.getImageName() %>">
						            	<img style="height:50px;width: auto;" src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserGallery.getImageName() %>" class="img-responsive">
						            </a>
						            <div class="img-overlay">
						              <i class="fa fa-plus-circle" aria-hidden="true"></i>
						            </div> 
						          </div>
						        </div>
					        <%} %>
					        
					      </div><!-- End row -->
					    </div><!-- End image gallery -->
					
					</section>
				<%} %>

				<h3 class="about-cont">Contact us</h3>
				<form method="post" action="#">
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
				<button class="button" disabled="disabled">Submit </button>
				</div>
					</form>
					
					

				</div>
				 </div>
				 <div class="row">
				 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
<!-- 				 <h3 class="about-cont">FOLLOW US</h3> -->
<!-- 				 </div></div> -->
				 
				 <div class="row padding-bottom-20">
				 <%if(!Constants.isStringNull(advertiserDetails.getFbLink())){ %>
					 <div class="col-lg-4" style="padding: 2px ">
						 <h3 class="about-cont">Face Book</h3>
						<div class="facebook-box">
							<iframe src="https://www.facebook.com/plugins/page.php?href=<%=advertiserDetails.getFbLink() %>&tabs=timeline&width=340&height=500&small_header=true&adapt_container_width=true&hide_cover=false&show_facepile=true&appId" width="340" height="500" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowfullscreen="true" allow="autoplay; clipboard-write; encrypted-media; picture-in-picture; web-share"></iframe>
						  
		    			</div>
				 </div> 
		  <%} %>
		 <%if(!Constants.isStringNull(advertiserDetails.getTwitterLink())){ %>
		  <div class="col-lg-4">
				 <h3 class="about-cont">Twitter</h3>
				<div class="twitter-box">
	           <a target="_blank" class="twitter-timeline" href="<%=advertiserDetails.getTwitterLink()%>">Tweets by <%=advertiserDetails.getName() %></a> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script></div>
		 </div>
		 <%} %>
		  <%if(!Constants.isStringNull(advertiserDetails.getInstagramHandleName())){ %>
		  
		  <div class="col-lg-4" style="padding: 2px ">
				 <h3 class="about-cont">Instagram</h3>
				<div class="twitter-box">
        
<div class="container">	
<!-- 	<div id="instagram-feed-demo" class="instagram_feed"></div> -->
<iframe width="320" height="460" src="<%=advertiserDetails.getInstagramHandleName() %>" frameborder="0"></iframe>

</div>

</div>
		 </div>
		 <%} %>
		 
		 </div>
				 
				 <%if(couponList!=null && couponList.size()>0){ %>
				 <div class="row border-top ">
				  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				  <h3 class="about-cont center">Coupons</h3>
				   </div></div>
				  <div class="row lat-print">
		
		 <%for(Coupon coupon : couponList){ %>
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">  
          <div class="promo-card" >
            <a class="promo-card-inner" href="<%=contextPath %>/coupon/<%=Constants.toSlug(coupon.getTitle()) %>/<%=EncryptionUtil.encode(coupon.getCouponId()+"") %>/">
               <img src="https://adwit-community.s3.ap-south-1.amazonaws.com/coupons/<%=coupon.getSourceLink() %>" >
             <p class="with-business-name">
              <span class="business-name"><%=coupon.getTitle() %></span><br>
                 <%=coupon.getValidFrom() %> |  <%=coupon.getValidTo() %>
              <span class="valid-info"><span class="translation_missing" ><%=coupon.getValidity() %> Days Left</span></span>
             </p>
             </a> 

            </div>
         </div>
		 </div>
		 <%} %>
		 
		 
		 
		 
		
		
		 
		 </div>
				<%} %> 
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
<%--     <script src="<%=assetsPath %>/js/searchMap.js"></script> --%>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCo_UiZM19FOm6-Vpl42HXNDrpYwGHCzPo"></script>
    <script src="<%=assetsPath %>/js/map.js"></script> 
    
    
      <script id="rendered-js" >
// Gallery image hover
$(".img-wrapper").hover(
function () {
  $(this).find(".img-overlay").animate({ opacity: 1 }, 600);
}, function () {
  $(this).find(".img-overlay").animate({ opacity: 0 }, 600);
});


// Lightbox
var $overlay = $('<div id="overlay"></div>');
var $image = $("<img>");
var $prevButton = $('<div id="prevButton"><i class="fa fa-chevron-left"></i></div>');
var $nextButton = $('<div id="nextButton"><i class="fa fa-chevron-right"></i></div>');
var $exitButton = $('<div id="exitButton"><i class="fa fa-times"></i></div>');

// Add overlay
$overlay.append($image).prepend($prevButton).append($nextButton).append($exitButton);
$("#gallery").append($overlay);

// Hide overlay on default
$overlay.hide();

// When an image is clicked
$(".img-overlay").click(function (event) {
  // Prevents default behavior
  event.preventDefault();
  // Adds href attribute to variable
  var imageLocation = $(this).prev().attr("href");
  // Add the image src to $image
  $image.attr("src", imageLocation);
  // Fade in the overlay
  $overlay.fadeIn("slow");
});

// When the overlay is clicked
$overlay.click(function () {
  // Fade out the overlay
  $(this).fadeOut("slow");
});

// When next button is clicked
$nextButton.click(function (event) {
  // Hide the current image
  $("#overlay img").hide();
  // Overlay image location
  var $currentImgSrc = $("#overlay img").attr("src");
  // Image with matching location of the overlay image
  var $currentImg = $('#image-gallery img[src="' + $currentImgSrc + '"]');
  // Finds the next image
  var $nextImg = $($currentImg.closest(".image").next().find("img"));
  // All of the images in the gallery
  var $images = $("#image-gallery img");
  // If there is a next image
  if ($nextImg.length > 0) {
    // Fade in the next image
    $("#overlay img").attr("src", $nextImg.attr("src")).fadeIn(800);
  } else {
    // Otherwise fade in the first image
    $("#overlay img").attr("src", $($images[0]).attr("src")).fadeIn(800);
  }
  // Prevents overlay from being hidden
  event.stopPropagation();
});

// When previous button is clicked
$prevButton.click(function (event) { 
  // Hide the current image
  $("#overlay img").hide();
  // Overlay image location
  var $currentImgSrc = $("#overlay img").attr("src");
  // Image with matching location of the overlay image
  var $currentImg = $('#image-gallery img[src="' + $currentImgSrc + '"]');
  // Finds the next image
  var $nextImg = $($currentImg.closest(".image").prev().find("img"));
  // Fade in the next image
  $("#overlay img").attr("src", $nextImg.attr("src")).fadeIn(800);
  // Prevents overlay from being hidden
  event.stopPropagation();
});

// When the exit button is clicked
$exitButton.click(function () {
  // Fade out the overlay
  $("#overlay").fadeOut("slow");
});
//# sourceURL=pen.js
    </script>
    
    <script> 
    
    var infoData = '<div class="listing-map-popup"><div class="thumb"><img src="<%=assetsPath %>/images/listing/map-popup.jpg" class="img-fluid" alt=""</div><div class="body"><h5><a href="#"><%=advertiserDetails.getName().replace("'", "")%></a></h5><p><i class="fas fa-map-marker-alt"></i><%=advertiserDetails.getAddress()%></p></div></div>';
	var addressPoints = [
		[<%=advertiserDetails.getLatitude()%>, <%=advertiserDetails.getLongitude()%>, infoData],
	];  
	 
	
    function liefletMapInIt() {
        if((document.getElementById('searchmap')) != null){
            var container = L.DomUtil.get('searchmap');
              if(container != null){
                  container._leaflet_id = null;
              }
            L.HtmlIcon = L.Icon.extend({
              options: {

              },

              initialize: function (options) {
                L.Util.setOptions(this, options);
              },

              createIcon: function () {
                var div = document.createElement('div');
                 div.innerHTML = this.options.html;
                if (div.classList)
                  div.classList.add('leaflet-marker-icon');
                else
                  div.className += ' ' + 'leaflet-marker-icon';
                return div;
              },

              createShadow: function () {
                return null;
              }
            });

            var tiles = L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/Canvas/World_Light_Gray_Base/MapServer/tile/{z}/{y}/{x}', {
              attribution: '<%=advertiserDetails.getName().replace("'", "")%>',
              maxZoom: 17
            }),
            latlng = L.latLng(<%=advertiserDetails.getLatitude()%>, <%=advertiserDetails.getLongitude()%>);

     

            var map = L.map('searchmap', {
              center: latlng,
              zoom: 15,
              scrollWheelZoom: false,
              layers: [tiles]
            });

            var markers = L.markerClusterGroup();
            var k = 14;
            for (var i = 0; i < addressPoints.length; i++) {
              var a = addressPoints[i];
                
               var markerHTML = new L.HtmlIcon({
                  html : "<img class='leaflet-marker-icon leaflet-zoom-animated leaflet-interactive' src='<%=assetsPath%>/images/marker/"+k+".png' alt='markericon' />", 
              });
 
              

              var title = a[2];
              var marker = L.marker(new L.LatLng(a[0], a[1]), {icon: markerHTML});
              marker.bindPopup(title, {offset: new L.Point(0, -170)});
              markers.addLayer(marker);
              k++;
            }

            map.addLayer(markers);
        }
      }
    
    
    let advertiserId = '<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"")%>';
    
    $(".track_website").click(function (event) {
    	 trackUsage(advertiserId, '0', 'WEBSITE');
    });
    
    $(".track_direction").click(function (event) {
   	 trackUsage(advertiserId, '0', 'DIRECTION');
   });
    
    $(".track_share").click(function (event) {
   	 trackUsage(advertiserId, '0', 'SHARE');
   });
    
    $(".track_imageGallery").click(function (event) {
      	 trackUsage(advertiserId, '0', 'IMAGE');
      });
    
    $(".track_video").click(function (event) {
      	 trackUsage(advertiserId, '0', 'VIDEO');
      });

     
    
    $(document).ready(function () {
    	
    	liefletMapInIt();
    });
	

   
    
    </script>
    
<!--       <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script> -->

        <script src="https://cdn.jsdelivr.net/npm/bulma-carousel@4.0.4/dist/js/bulma-carousel.min.js"></script>
            <script id="rendered-js">
      bulmaCarousel.attach('#trend-slide', {
        slidesToScroll: 2,
        slidesToShow: 3,
        pagination: false,
        loop: true,
        autoplay: true,
        autoplaySpeed: 5000 });
      //# sourceURL=pen.js
          </script>
    
  </body>
</html>
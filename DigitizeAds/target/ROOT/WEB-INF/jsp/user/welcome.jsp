<%@page import="com.digitizeads.modal.Advertiser"%>
<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%@page import="java.util.ArrayList"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";

ArrayList<Category> categoryList = request.getAttribute(Constants.CATEGORY_LIST)!=null?(ArrayList<Category>)request.getAttribute(Constants.CATEGORY_LIST):null;
ArrayList<Advertiser> featuredAdvertiserList = request.getAttribute(Constants.FEATURED_ADVERTISER_LIST)!=null?(ArrayList<Advertiser>)request.getAttribute(Constants.FEATURED_ADVERTISER_LIST):null;

%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>The TITLE </title>

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
    <div class="header-bg-1">
      
      <div class="header-banner text-center">
        <div class="container">
          <div class="row">
            <div class="col">
            <h1>Find the best places in XXX</h1>
              
              <div class="header-search-listing">
                <form class="form-inline">
                  <div class="category form-group">
                     <input type="text" class="form-control" id="autocomplete" placeholder="What are you looking for?">
                  </div>
                 
                  <button type="submit" class="btn btn-primary button"><i class="fas fa-search"></i>Search</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
	  
      <%if(categoryList!=null && categoryList.size()>0){ %>
      <div class="header-category-slider">
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="header-category-slider-wrap owl-carousel">
                <%for(Category category : categoryList){ %>
					 <a href="<%=contextPath %>/category/<%=Constants.toSlug(category.getName()) %>/<%=EncryptionUtil.encode(category.getCategoryId()+"") %>/" class="category-item">
	                  <i class="material-icons"><%=category.getIcon() %></i>
	                  <span><%=category.getName() %></span>
	                </a>
				<%} %>
				<a href="<%=contextPath %>/category/all/" class="category-item">
                  <i class="material-icons">more_horiz</i>
                  <span>More</span>
                </a>
               
              </div>
            </div>
          </div>
        </div>
      </div> 
      <%} %>
    </div>

   <div class="section-padding padding-bottom-20 listing-bg section-border-top section-border-bottom">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="section-header">
              <h2>FEATURED BUSINESSES</h2>
             </div>
          </div>
        </div>
		<div class="step-numbers">
        </div>
        <%if(featuredAdvertiserList!=null && featuredAdvertiserList.size()>0){ %>
        <div class="row">
          <%for(Advertiser advertiser : featuredAdvertiserList){ %>
          <div class="col-lg-4 col-md-6">
            <div class="lrn-listing-wrap">
              <div class="listing-thumb">
                <a href="<%=contextPath %>/<%=Constants.toSlug(advertiser.getName()) %>/<%=EncryptionUtil.encode(advertiser.getAdvertiserId()+"") %>/" class="card-image-wrapper">
				 <img src="<%=assetsPath %>/bakers_image/wildlands.png" class="img-fluid cardimg" alt="">
				</a>
              </div>
              <div class="listing-body">
                <div class="meta">  
                 </div>
                <h3><a href="<%=contextPath %>/<%=Constants.toSlug(advertiser.getName()) %>/<%=EncryptionUtil.encode(advertiser.getAdvertiserId()+"") %>/" class="headline"><%=advertiser.getName() %></a></h3>
                
                <div class="listing-location">
                  <div class="icon">
                    <i class="fas fa-map-marker-alt"></i>
                  </div>
                  <span><%=advertiser.getAddress() %></span>
                </div>
                <div class="listing-category">
                  <div class="icon"><i class="fa fa-list" ></i></div>
                  <span><a href="<%=contextPath %>/category/<%=Constants.toSlug(advertiser.getCategory().getName()) %>/<%=EncryptionUtil.encode(advertiser.getCategory().getCategoryId()+"") %>/"><%=advertiser.getCategory()!=null?advertiser.getCategory().getName():"-" %></a> </span>
                </div>
				<div class="listing-bottom side-icon" >
                  <span><a class="cta" href="tel:+1 661-858-1115"><i class="fas fa-phone single_phicon"></i></a></span>
                  <span><a class="cta" href="https://maps.google.com?daddr=16019 Maricopa Hwy+Oak Glen+CA+92399" target="_blank" rel="">
				  <i class="material-icons single_diricon">directions</i></a></span>
                </div>
               <!-- <div class="listing-bottom">
                  <span><a class="cta" href="tel:+1 661-858-1115"><i class="fas fa-phone"></i>+1 661-858-1115</a></span>
                  <span ><a class="cta" href="https://maps.google.com?daddr=16019 Maricopa Hwy+Oak Glen+CA+92399" target="_blank" rel=""><i class="material-icons">directions</i></a></span>
                </div>-->
              </div>
            </div>
          </div>
          <%} %>
          
        </div>
        <%} %>
      </div>
    </div>
	
	<div class="section-padding section-border-top section-border-bottom" style="background-color: #dfdfdf;">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="section-header">
              <h2>TRENDING BUSINESSES</h2>
             </div>
          </div>
        </div>
		<div class="step-numbers">
        </div>
		 <div class="row">
		<%for(int i=0;i<8;i++){ %>
		<div class="col-lg-4 col-md-6">
            <div class="lrn-listing-wrap">
              
              <div class="listing-body trend" >
                <a  href="<%=contextPath %>/business_slug/business_id/">
        <div >
  <img  class="trend-img" src="bakers_image/stjude.png" onerror="this.onerror=null; this.src='https://fbcdn.net/static_map.php?size=400x400&amp;zoom=15&amp;markers=35.3803%2C-119.09&amp;scale=2'; this.classList.add('centered-map');" alt="James Y Chang MD - Medical - Physicians in Bakersfield CA" class="centered-map">
</div>

      </a>
	  <a class="card-info padding-left-10" href="<%=contextPath %>/business_slug/business_id/" rel="">
        <div class="name"><span>St Jude Dream Home Giveaway</span></div>
        <div class="address">262 Danny Thomas Pl, Memphis, TN</div>
      </a>
          
              </div>
            </div>
          </div>
		 <%} %> 
		  
		  </div>
		  <div class="center padding-top-10"><a class="button" href="<%=contextPath %>/business_slug/business_id/">See More Businesses</a></div>
		 </div>
		  </div>
		  
		  <div class="section-padding padding-bottom-60">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="section-header padding-bottom-20">
              <h2>LATEST COUPONS</h2>
             </div>
          </div>
        </div>
		
		<div class="row lat-print">
		<%for(int i=0;i<8;i++){ %>
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-6">
		<div class="promo-card-wrapper">
          <div class="promo-card" >
            <a class="promo-card-inner" href="<%=contextPath %>/ad/ad-slug/ad-id/">
               <img src="<%=assetsPath %>/bakers_image/stprint.jpg" >
             <p class="with-business-name">
              <span class="business-name">St Jude Dream Home</span><br>
                 Apr-03-20 |
              <span class="valid-info"><span class="translation_missing" >30 Days Left</span></span>
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
    <script src="<%=assetsPath %>/js/auto_search.js"></script>
    
  </body>
</html>
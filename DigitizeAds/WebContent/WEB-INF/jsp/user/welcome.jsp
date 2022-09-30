<%@page import="com.digitizeads.modal.SubDomain"%>
<%@page import="com.digitizeads.modal.Coupon"%>
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
ArrayList<Advertiser> trendingAdvertiserList = request.getAttribute(Constants.TRENDING_ADVERTISER_LIST)!=null?(ArrayList<Advertiser>)request.getAttribute(Constants.TRENDING_ADVERTISER_LIST):null;
ArrayList<Advertiser> allAdvertiserList = request.getAttribute(Constants.ALL_ADVERTISER_LIST)!=null?(ArrayList<Advertiser>)request.getAttribute(Constants.ALL_ADVERTISER_LIST):null;

ArrayList<Coupon> couponList = request.getAttribute(Constants.FEATURED_COUPON_LIST)!=null?(ArrayList<Coupon>)request.getAttribute(Constants.FEATURED_COUPON_LIST):null;
SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;


String bgImage = assetsPath+"/sierra/top_background.jpg";
 
System.out.println("bgImage : "+bgImage);
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Community Directory </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <!-- External Css -->
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/themify-icons.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/et-line.css" />
 
    <!-- Custom Css --> 
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/main.css">
    
    <link rel="stylesheet" href="<%=assetsPath %>/css/index_new.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
    />
    <link
      rel="stylesheet"
      href="https://storage.googleapis.com/code.getmdl.io/1.0.1/material.teal-red.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    
 
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500%7CSignika:400,600,700" rel="stylesheet">


    <!-- Favicon -->
    <link rel="icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=assetsPath %>/bakers_image/favicon.png">

<link rel="stylesheet" href="<%=assetsPath %>/assets/css/owl.carousel.min.css" />


<style>
	.header-banner {
	    padding: 75px 0 55px;
	    color: #ffffff;
	}
	 
	.header-bg-2 {
	  background: url("<%=userSubDomain!=null && userSubDomain.getBannerImageURL()!=null?userSubDomain.getBannerImageURL():bgImage %>") no-repeat center;
	  background-size: cover;
	}
	 
	.mdl-card__title-text{ 
		font-size: 20px
	}
	
	.material-icons{
		color:rgb(0,150,136);
	}
	
	.carousel-item.active {
	  display: flex;
	  margin: auto;
	  justify-content: center;
	  height: auto;
	}
	 
	.h-200px {
	  width: 100%;
	  height: auto;
	  margin-left: auto;
	  margin-right: auto;
	  
	}
	 
	.carousel-inner{
		height: 220px
	}
	
	.header-banner .header-search-listing .button{
		font-size: 1.1rem;
	}
 
	 .header-category-slider {
	  border-top: 1px solid rgba(255, 255, 255, 0.15);
	  background-color: rgba(0,0,0,.5);
	}
	
	
	.header-banner .header-search-listing .form-control{
		font-size: 1.1rem;
	}
	
	.card_image_container {
	  padding: 1rem;
	}
	
	.owl-carousel {
    margin: 0.5rem 0;
} 

.owl-carousel .item {
    height: 10rem;
    background: #4DC7A0;
    padding: 1rem;
}

.owl-carousel .item h4 {
    color: #FFF;
    font-weight: 400;
    margin-top: 0rem;
}


@media screen and (max-width: 500px) {
	.mdl-card{
		width: 100%
	}
}
	
	.owl-item.active > div:after {
  content: 'active';
}
.owl-item.center > div:after {
  content: 'center';
}
.owl-item.active.center > div:after {
  content: 'active center';
}
.owl-item > div:after {
  font-family: sans-serif;
  font-size: 24px;
  font-weight: bold;
}

</style> 
  </head>
  <body>

    <div class="preloader">
      <div class='pin'></div>
      <div class='pulse'></div>
    </div>

	<jsp:include page="header.jsp" flush="true"/>
    <div class="header-bg-2">
      
      <div class="header-banner text-center">
        <div class="container">
          <div class="row">
            <div class="col">
            <h1 style="background-color: rgba(0,0,0,.5);"><%=userSubDomain!=null && !Constants.isStringNull(userSubDomain.getHeaderText())?userSubDomain.getHeaderText():"" %></h1>
              
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
				 
<%-- 				<a href="<%=contextPath %>/category/all/" class="category-item"> --%>
<!--                   <i class="material-icons">more_horiz</i> -->
<!--                   <span>More</span> -->
<!--                 </a> -->
               
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
              <div
                  class="mdl-card mdl-shadow--2dp card"
                  style="margin-bottom: 3%"
                >
                  <div
                    id="carouselExampleIndicators_1"
                    class="carousel slide"
                    data-ride="carousel"
                  >
                   
                   <%if(!Constants.isStringNull(advertiser.getFeaturedImage())){ %>
	                   <div class="carousel-inner card_image_container">
	                      <div class="carousel-item active">
	                        <img
	                          src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiser.getFeaturedImage()%>"
	                          class="d-block h-200px"
	                          alt="bakery_img_1"
	                        />
	                      </div>
	                       
	                    </div>
                    
                    <%}else{ %> 
	                    <div class="carousel-inner">
	                      <div class="carousel-item active">
	                        <img
	                          src="https://mindfuldesignconsulting.com/wp-content/uploads/2020/07/Rustic-Bakery-Interior-Design-720x321.jpg"
	                          class="d-block h-200px"
	                          alt="bakery_img_1"
	                        />
	                      </div>
	                      
	                    </div>
                    <%} %>
                    
                  </div>
                  <div class="mdl-card__title">
                    <h1 class="mdl-card__title-text">
                      <%=advertiser.getName() %>
                    </h1>
                  </div>
                  <div class="mdl-card__actions mdl-card--border">
                    <a href="<%=contextPath+"/"+advertiser.getUrlSlug() %>"
                      class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
                      >View</a 
                    >
                    <div class="mdl-layout-spacer"></div>
                    <a class="cta track_phone"
                     data-bu_id="<%=EncryptionUtil.encode(advertiser.getAdvertiserId()+"") %>"
                      href="tel:+1 <%=advertiser.getPhoneNumber() %>"
                      class="mdl-button mdl-button--icon mdl-button--colored"
                    >
                      <i class="material-icons">phone</i>
                    </a>
                   <a class="cta track_direction" data-bu_id="<%=EncryptionUtil.encode(advertiser.getAdvertiserId()+"") %>" href="https://maps.google.com?daddr=<%=advertiser.getAddress() %>" target="_blank" rel="" 
                      class="mdl-button mdl-button--icon mdl-button--colored"
                    >
                      <i class="material-icons">directions</i>
                    </a>
                  </div>
                </div>
          </div>
          <%} %>
          
        </div>
        <%} %>
      </div>
    </div>
	
	
	 <%if(trendingAdvertiserList!=null && trendingAdvertiserList.size()>0){ %>
	<div class="section-padding section-border-top section-border-bottom" style="background-color: #e3f4e4;">
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
		 <%for(Advertiser advertiser : trendingAdvertiserList){ %>
		<div class="col-lg-4 col-md-6">
            <div class="lrn-listing-wrap">  
               
              <div class="listing-body trend" >
                <a href="<%=contextPath+"/"+advertiser.getUrlSlug() %>" style="width: 16%;margin-right: 4%">
					  <div>
						  <img  class="trend-img" src="https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/<%=advertiser.getLongitude() %>,<%=advertiser.getLatitude() %>,14/500x300?access_token=pk.eyJ1IjoiYWR3aXRnbG9iYWwiLCJhIjoiY2tpNGsweW1sMHFzejJ0c3l3dHlkbnBoaCJ9.qd3vQkywU1t49iPFjTVYFw" onerror="this.onerror=null; this.src='https://fbcdn.net/static_map.php?size=400x400&amp;zoom=15&amp;markers=35.3803%2C-119.09&amp;scale=2'; this.classList.add('centered-map');" alt="<%=advertiser.getName() %>" class="centered-map">
					</div>
	
				</a>
			  <a href="<%=contextPath+"/"+advertiser.getUrlSlug() %>" style="width: 75%;">
		        <div class="name"><span><%=advertiser.getName() %></span></div>
		        <div class="address"><%=advertiser.getAddress() %></div>
		      </a> 
          
              </div>
            </div>
          </div>
		 <%} %> 
		    
		  </div>
<%-- 		  <div class="center padding-top-10"><a class="button" href="<%=contextPath %>/business_slug/business_id/">See More Businesses</a></div> --%>
		 </div>
		  </div>
		  
		  <%} %>
		<%for(Coupon coupon : couponList){ %>  
		  <div class="section-padding padding-bottom-60 listing-bg hide">
		      <div class="container">
		        <div class="row">
		          <div class="col">
		            <div class="section-header padding-bottom-20">
		              <h2>LATEST COUPONS</h2>
		             </div>
		          </div> 
		        </div>
				
		<div class="row lat-print">
		
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
		
		 
		 
		 </div>
		 <%} %>
		
<!-- 		<div class="button-container"><a class="button" href="print_ads.html">See More Coupons</a></div> -->
		 </div>
		  </div> 
		  
		  
		  
	
	 <%if(allAdvertiserList!=null && allAdvertiserList.size()>0 && false){ %>
	<div class="section-padding section-border-top section-border-bottom hide" style="background-color: #e3f4e4;">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="section-header">
              <h2><a href="<%=contextPath %>/business/all">ALL BUSINESSES</a></h2>
             </div>
          </div>
        </div>
		<div class="step-numbers">
        </div>
		 <div class="row">
		 <%for(Advertiser advertiser : allAdvertiserList){ %>
		<div class="col-lg-4 col-md-6">
                <a  href="<%=contextPath %>/<%=Constants.toSlug(advertiser.getName()) %>/<%=EncryptionUtil.encode(advertiser.getAdvertiserId()+"") %>/">
 <%=advertiser.getName() %></a> 
</div>  

		 <%} %> 
		   
		  </div>
		  <div class="center padding-top-10"><a class="button" href="<%=contextPath %>/business/all">See More Businesses</a></div>
		 </div>
		  </div>
		  
		  <%} %>
		  
		  
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
    
    <script src="<%=assetsPath %>/assets/js/owl.carousel.min.js"></script>
    
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=assetsPath %>/assets/js/popper.min.js"></script>
    <script src="<%=assetsPath %>/assets/js/bootstrap.min.js"></script>

    <script src="<%=assetsPath %>/js/custom_home.js"></script>


    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCo_UiZM19FOm6-Vpl42HXNDrpYwGHCzPo"></script>

    <script src="<%=assetsPath %>/js/auto_search.js"></script> 
    
    
	    <script> 
	    $(document).ready(function () {
		
// 			$('.owl-carousel').owlCarousel({
// 			    loop:true,
// 			    margin:5,
// 			    responsiveClass:true,
// 			    dots:false,
// 			    nav:true,
// 			    responsive:{ 
// 			        0:{
// 			            items:1,
// 			            nav:true
// 			        },
// 			        600:{
// 			            items:2,
// 			            nav:false
// 			        },
// 			        1000:{
// 			            items:10,
// 			            nav:true,
// 			            loop:false
// 			        }
// 			    }
// 			});
			
			$('.owl-carousel').owlCarousel({
				autoplay:true,
				autoplayTimeout:1000,
				autoplayHoverPause:true,
				center: true,
				loop:true,
				nav:true, 
				navigation:true,
				      navigationText: [
				        "<i class='icon-chevron-left icon-white'><</i>",
				        "<i class='icon-chevron-right icon-white'>></i>"
				      ],
				navText: ['<i class="fa fa-angle-left" aria-hidden="true"></i>','<i class="fa fa-angle-right" aria-hidden="true"></i>'],
				responsive:{
				    0:{
				        items:1
				    },
				    600:{
				        items:3
				    },
				    1000:{
				        items:6
				    },
				    1600:{
				        items:8
				    },
				    1900:{
				        items:10
				    }
 
				  }
				  });
				   $('.owl-carousel').trigger('owl.play',2000);
				   
	    });
	    
	    </script>

  </body>
</html>
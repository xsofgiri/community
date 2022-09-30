<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.PageHandler"%>
<%@page import="com.digitizeads.modal.Advertiser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";

Category categoryDetails = request.getAttribute(Constants.CATEGORY_DETAILS)!=null?(Category)request.getAttribute(Constants.CATEGORY_DETAILS):null;
ArrayList<Advertiser> advertiserList = request.getAttribute(Constants.ADVERTISER_LIST)!=null?(ArrayList<Advertiser>)request.getAttribute(Constants.ADVERTISER_LIST):null;

PageHandler pageHandler = request.getAttribute(Constants.ADVERTISER_PAGINATION)!=null?(PageHandler)request.getAttribute(Constants.ADVERTISER_PAGINATION):null;
String pageNo = (String) request.getParameter("pageNo");
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

     <title>Community Directory </title>

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
     
    
    <!-- Custom Css --> 
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/main.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500%7CSignika:400,600,700" rel="stylesheet">

    <!-- Favicon -->
    <link rel="icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=assetsPath %>/bakers_image/favicon.png">

<style>
.page-header-bg.listing-page-header-bg {
    background: url(<%=assetsPath %>/images/bg/listing-header-bg_v1.jpg) center;
}

.header-banner {
	    padding: 75px 0 55px;
	    color: #ffffff;
	}
	 
	.header-bg-2 {
	  background: url("<%=assetsPath %>/sierra/top_background.jpg") no-repeat center;
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
 
  
</style> 

  </head>
  <body> 

    <div class="preloader">
      <div class='pin'></div>
      <div class='pulse'></div> 
    </div>
 
	 <jsp:include page="header.jsp" flush="true"/>
	 

   <div class="search-listing-wrap map-top">
      <div class="listing-map-block">
        <div class="page-header page-header-bg listing-page-header-bg">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="page-header-block">
              <h2><%=categoryDetails.getName() %></h2>
             
            </div>
          </div>
        </div>
      </div>
    </div>
      </div>
      <div class="listing-wrap grey-bg">
        <div class="container">
          <div class="row">
            <div class="col">
              <div class="listing-search-block">
                <form action="#">
                  <div class="listing-filter-block">
                    <div class="category">
                      <select class="form-control select-category">
                        <option value="">Fiter By Categories</option>
                        <option value="">Beauty & Spa</option>
                        <option value="">Health & Medical</option>
                        <option value="">Real Estate</option>
                        <option value="">Food & Hotel</option>
                        <option value="">Travel</option>
                      </select>
                    </div>
                    <div class="category">
                      <select class="form-control select-category">
                        <option value="">Fiter By Type</option>
                        <option value="">African Food</option>
                        <option value="">Asian Food</option>
                        <option value="">Bagels</option>
                        <option value="">Bakeries</option>
                        <option value="">British Food</option>
                      </select>
                    </div>
                    <div class="sort">
                      <select class="form-control sort-by">
                        <option value="">Sort By</option>
                        <option value="">ASC</option>
                        <option value="">DESC</option>
                      </select>
                    </div>
                   
                  </div>
                </form>
              </div>
              <div class="listing-result">
                <div class="listing-result-header">
                  <h5 class="searching-for">Results For: <span><a href="category_list.html">CATEGORIES</a> / <a href="#"><%=categoryDetails.getName() %></a></span></h5>
                  
                </div>
                <div class="listing-result-block">
                  <%if(advertiserList!=null && advertiserList.size()>0){ %>
                  <div class="row">
			          <%for(Advertiser advertiser : advertiserList){ %>
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
	                   <div class="carousel-inner">
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
                      >Read More</a 
                    >
                    <div class="mdl-layout-spacer"></div>
                    <a class="cta" href="tel:+1 <%=advertiser.getPhoneNumber() %>"
                      class="mdl-button mdl-button--icon mdl-button--colored"
                    >
                      <i class="material-icons">phone</i>
                    </a> 
                   <a class="cta" href="https://maps.google.com?daddr=<%=advertiser.getAddress() %>" target="_blank" rel="" 
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
                  <div class="row">
                    <div class="col">
                      <nav class="navigation pagination mar-10 justify-content-center">
                        <div class="nav-links">
                          <a class="prev page-numbers" href="<%=pageHandler.getSurl()%>?pageNo=<%=pageHandler.getCurrentPage()-1%>"><i class="fas fa-angle-left"></i></a>
                          <a class="page-numbers  <%if(!Constants.isStringNull(pageNo) && pageHandler.getCurrentPage()==Integer.parseInt(pageNo)){%> current<%} %>" href="<%=pageHandler.getSurl()%>?pageNo=<%=pageHandler.getCurrentPage()-1%>"><%=pageHandler.getCurrentPage()%></a>
                          <a class="next page-numbers" href="<%=pageHandler.getSurl()%>?pageNo=<%=pageHandler.getCurrentPage()+1%>"><i class="fas fa-angle-right"></i></a>
                        </div>
                      </nav>
                    </div>
                  </div>
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
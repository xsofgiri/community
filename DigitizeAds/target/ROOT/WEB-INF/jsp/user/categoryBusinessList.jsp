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
			                  <div class="icon"><i class="fa fa-list"></i></div>
			                  <span><a href="<%=contextPath %>/category/<%=Constants.toSlug(advertiser.getCategory().getName()) %>/<%=EncryptionUtil.encode(advertiser.getCategory().getCategoryId()+"") %>/"><%=advertiser.getCategory()!=null?advertiser.getCategory().getName():"-" %></a> </span>
			                </div>
							<div class="listing-bottom side-icon" >
			                  <span><a class="cta" href="tel:+1 661-858-1115"><i class="fas fa-phone single_phicon"></i></a></span>
			                  <span><a class="cta" href="https://maps.google.com?daddr=16019 Maricopa Hwy+Oak Glen+CA+92399" target="_blank" rel="">
							  <i class="material-icons single_diricon">directions</i></a></span>
			                </div>
			               
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
                          <a class="prev page-numbers" href="#"><i class="fas fa-angle-left"></i></a>
                          <a class="page-numbers  <%if(!Constants.isStringNull(pageNo) && pageHandler.getCurrentPage()==Integer.parseInt(pageNo)){%> current<%} %>" href="<%=pageHandler.getSurl()%>?pageNo=<%=pageHandler.getCurrentPage()-1%>">1</a>
                          <a class="page-numbers" href="<%=pageHandler.getSurl()%>?pageNo=<%=pageHandler.getCurrentPage()-1%>">3</a>
                          <a class="next page-numbers" href="#"><i class="fas fa-angle-right"></i></a>
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
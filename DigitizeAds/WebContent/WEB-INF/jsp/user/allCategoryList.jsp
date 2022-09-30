<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%@page import="java.util.ArrayList"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";

ArrayList<Category> categoryList = request.getAttribute(Constants.CATEGORY_LIST)!=null?(ArrayList<Category>)request.getAttribute(Constants.CATEGORY_LIST):null;

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

    <!-- Custom Css --> 
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/main.css">

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500%7CSignika:400,600,700" rel="stylesheet">

    <!-- Favicon -->
    <link rel="icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="<%=assetsPath %>/bakers_image/favicon.png">
    <link rel="apple-touch-icon" sizes="114x114" href="<%=assetsPath %>/bakers_image/favicon.png">

 	<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,200;0,400;0,600;1,200;1,400;1,600&display=swap" rel="stylesheet" />
    <link  href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    
    <link rel="stylesheet" href="<%=assetsPath %>/css/card_new.css" />
    
    <style>
	   	 	
	@media screen and (max-width: 500px) {
		i {
		  font-size: 2rem !important;
		}  
		
		h2 {
		  font-size: 1.5rem;
		}
		
		p {
		  font-size: 1rem;
		}
		
		.card-header-container {
		  gap: 0rem;
		  margin-bottom: 15px;
		}
		
		.box {
		  padding: 15px;
		}
	}
    </style>
  </head>
  <body>

    <div class="preloader">
      <div class='pin'></div>
      <div class='pulse'></div>
    </div> 
 
	
	<jsp:include page="header.jsp" flush="true"/>
	

   <div class="search-listing-wrap filter-sidebar">
      <div class="listing-wrap grey-bg">
	  
		<div class="row padding-bottom-30">
          <div class="col">
            <div class="section-header">
              <h2>CATEGORIES</h2>
             </div>
          </div>
        </div>
		 
		<%if(categoryList!=null && categoryList.size()>0){ %>
		 <div class="row1-container">
     
      <div class="box cyan">   
       <a href="<%=contextPath %>/">
        <div class="card-header-container">
          <i class="material-icons">local_florist</i>
          <h2>Visitors</h2>
        </div>
        
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
        </a>
      </div>
      
      <div class="box red">
        <div class="card-header-container">
          <i class="material-icons">local_dining</i>
          <h2>Dining</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
      
      <div class="box blue">
        <div class="card-header-container">
          <i class="material-icons">local_mall</i>
          <h2>Shopping</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
    </div>
    <div class="row1-container">
      <div class="box orange">
        <div class="card-header-container">
          <i class="material-icons">attractions</i>
          <h2>Entertainment</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
      <div class="box red">
        <div class="card-header-container">
          <i class="material-icons">directions_car</i>
          <h2>Car Rentals</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
      <div class="box orange">
        <div class="card-header-container">
          <i class="material-icons">spa</i>
          <h2>Hotels</h2>
        </div>

        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
    </div>
    <div class="row1-container">
      <div class="box blue">
        <div class="card-header-container">
          <i class="material-icons">build</i>
          <h2>Utilities</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
      <div class="box row3-card2">
        <div class="card-header-container">
          <i class="material-icons">local_airport</i>
          <h2>Services</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
      <div class="box row3-card3">
        <div class="card-header-container">
          <i class="material-icons">home</i>
          <h2>Real Estate</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div>
    </div>
    <div class="row1-container">
      <div class="box row4-card1">
        <div class="card-header-container">
          <i class="material-icons">group</i>
          <h2>Religion</h2>
        </div>
        <p>
          Scans our talent network to create the optimal team for your project
        </p>
      </div> 
    </div>
          <%} %>
		   
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
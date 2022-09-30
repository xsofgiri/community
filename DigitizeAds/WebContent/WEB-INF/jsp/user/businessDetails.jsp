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
<%--     <link rel="stylesheet" href="<%=assetsPath %>/assets/css/bootstrap.min.css"> --%>
<link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
      crossorigin="anonymous"
    />
    
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
    <!-- External Css -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
      integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/themify-icons.css" />
    <link rel="stylesheet" href="<%=assetsPath %>/assets/css/et-line.css" />

    <!-- Custom Css --> 
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/main.css">
    <link rel="stylesheet" type="text/css" href="<%=assetsPath %>/css/index_business.css">
     
 
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Rubik:400,500%7CSignika:400,600,700" rel="stylesheet">
 
 <style>
 	.youtubeVideo{
 		padding-bottom:10% !important;
 		padding:10px;
 		overflow: hidden;
 		height: 300px
 	}
 	  
 	
 	.youtubeVideo iframe {
	 padding-bottom: 5%; 
	}
 	
 	
 	.respLogo{
 		width: 150px;
 		height: auto;
 	}
 	
 </style>
  </head>

  <body>

    <div class="preloader">
      <div class='pin'></div>
      <div class='pulse'></div>
    </div>

	 <jsp:include page="header.jsp" flush="true"/>
    <div class="container bis_details_page_mainContainer">
  <div class="row">
   <div class="container left_card_container col-md-9">
        <!-- left card starts -->
      <div class="card left_card">
        <ul class="list-group list-group-flush">
          <div class="card_header">
            <!-- section 1 --> 
            <div class="title">
              <h2><%=advertiserDetails.getName() %></h2>
              <p class="category"><a href="<%=contextPath %>/category/<%=Constants.toSlug(advertiserDetails.getCategory().getName()) %>/<%=EncryptionUtil.encode(advertiserDetails.getCategory().getCategoryId()+"") %>/"><%=advertiserDetails.getCategory().getName() %></a></p>
            </div>
            <div class="logo">
            	<%if(!Constants.isStringNull(advertiserDetails.getLogo())){ %>
            
	              <img class="respLogo"
	                src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserDetails.getLogo() %>"
	                alt="logo"
	              /> 
              <%} %>
            </div>
          </div>

          <div class="separator"></div>

          <!-- section 2 -->
          <div class="address_section">
            <span class="padding-right-20"><a class="track_direction" href="https://maps.google.com?daddr=<%=advertiserDetails.getAddress() %>"  data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>" target="_blank"><i class="fas fa-map-marker-alt" aria-hidden="true"></i> <%=advertiserDetails.getAddress() %></a></span>
            <span><a class="cta track_phone"  data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>" href="tel:<%=advertiserDetails.getPhoneNumber() %>">
            	<i class="fa fa-phone" aria-hidden="true"></i>
                 <%=advertiserDetails.getPhoneNumber() %></a>
               </span> 
          </div>

          <div class="separator"></div>

          <!-- section 3 -->
          <div class="about">
            <h3>About</h3>
            <p>
				<%=advertiserDetails.getAboutUs() %>
            </p>
          </div>

          <div class="separator"></div>

          <!-- section 4 -->
          <div class="container socialMedia_container">
          
          	<div class="row">
          			 <div class="col-md-6">
          			 	
          			 	<%if(advertiserGalleryList!=null && advertiserGalleryList.size()>0){%>
          <!-- gallery section --> 
    <div
      id="carouselExampleInterval"
      class="carousel slide gallery"
      data-bs-ride="carousel">
      
      <h5>Gallery</h5>
      <div class="carousel-inner">
        <%for(AdvertiserGallery advertiserGallery : advertiserGalleryList){%>
	        <div class="carousel-item active">
	          <img
	            src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserGallery.getImageName() %>"
	            class="d-block w-100 track_imageGallery"
	            data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>"
	            alt="image 1"
	            data-bs-toggle="modal" 
	            data-bs-target="#image<%=advertiserGallery.getAdvertiserGalleryId() %>"
	          />
	        </div>
        <%} %>
        
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleInterval"
        data-bs-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button 
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleInterval"
        data-bs-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    
    <!-- gallery section ends -->
    <%} %>
    
    
    <%if(advertiserGalleryList!=null && advertiserGalleryList.size()>0){%>
    <!-- modal section starts -->
    <%for(AdvertiserGallery advertiserGallery : advertiserGalleryList){%>
	    <!-- Modal 1 starts-->
	    <div
	      class="modal fade"
	      id="image<%=advertiserGallery.getAdvertiserGalleryId() %>"
	      tabindex="-1"
	      aria-labelledby="exampleModalLabel"
	      aria-hidden="true"
	    >
	      <div class="modal-dialog modal-dialog modal-dialog-centered">
	        <div class="modal-content">
	          <div class="modal-body">
	            <button
	              type="button"
	              class="btn-close"
	              data-bs-dismiss="modal"
	              aria-label="Close"
	            ></button>
	            <img
	              style="width: 100%; height: 100%"
	              src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserGallery.getImageName() %>"
	              alt="image1"
	            />
	          </div>
	        </div>
	      </div>
	    </div>
	    <!-- Modal 1 ends -->
    <%} %>
    
    <!-- modal section ends -->
    <%} %>

         
    
    
    
    
          			 </div>
          			  
          			 <div class="col-md-6">
          			 	
<!--           			 	<div class=" youtubeVideo">  -->
<!-- 				            <div class="" style="padding: 0px"> -->
<!-- 				              <h3>Video</h3> -->
<!-- 				              <iframe  -->
<!-- 				                src="https://www.youtube.com/embed/1Hwnai0LnbU" -->
<!-- 				                title="YouTube video player" -->
<!-- 				                frameborder="0" -->
<!-- 				                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" -->
<!-- 				                allowfullscreen -->
<!-- 				              ></iframe> -->
<!-- 				            </div> -->
<!-- 				          </div> -->
				          			 	
				          			 	
          			 
          			 </div>
          			 
          			
          	
          	
          	</div>
          
            
          </div>
        </ul>
      </div>
      <!-- left card ends -->
    </div>
    <div class="container right_card_container col-md-3">
        <!-- right card starts -->
      <div class="card right_card">
        <div class="card-body">
          <!-- icons section -->
          <div class="container px-0 mb-3">
           <div class="row text-center icons ">
            <div class="col">
                <a
              class="cta center padding-right-20 track_direction"
               data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>"
              href="https://maps.google.com?daddr=<%=advertiserDetails.getAddress() %>" target="_blank" rel="">
            
              <i class="fa-solid fa-diamond-turn-right fa-2x icon-blue"></i>
              <p class="blue">Directions</p>
            </a>
            </div>
            <div class="col">
                <a
              class="cta center padding-right-20 track_website"
              data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>"
              href="<%=advertiserDetails.getWebsite() %>" target="_blank" rel="">
              <i class="fa fa-desktop fa-2x icon-blue" aria-hidden="true"></i>
              <p class="blue">Website</p>
            </a> 
            </div>
            <div class="col">
                <a
              class="cta center padding-right-20 track_share"
              data-bu_id="<%=EncryptionUtil.encode(advertiserDetails.getAdvertiserId()+"") %>"
              href="#"
              target="_blank"
              rel="">
              <i class="fa fa-share-alt fa-2x icon-blue" aria-hidden="true"></i>
              <p class="blue">Share</p>
            </a>
            </div>
           </div>
           <hr/>
          </div>

    
          <!-- contactUS section -->
          <div class="contactUs" style="margin-bottom: 10px">
            <h5>Contact Us</h5>
            <div>
              <label for="exampleFormControlInput1" class="form-label"></label>
              <input
                type="text"
                class="form-control"
                id="exampleFormControlInput1"
                placeholder="Your name"
              />
            </div>
            <div>
              <label for="exampleFormControlInput2" class="form-label"></label>
              <input
                type="email"
                class="form-control"
                id="exampleFormControlInput2"
                placeholder="Your e-mail address"
              />
            </div> 
            <div>
              <label
                for="exampleFormControlTextarea1"
                class="form-label"
              ></label>
              <textarea
                class="form-control"
                id="exampleFormControlTextarea1"
                rows="3"
                placeholder="Your Review"
              ></textarea>
            </div>
            <button href="#" class="btn btn-primary">SUBMIT</button>
          </div>
          
          
          <!-- social media links -->
          
          <div class="row">
                <%if(!Constants.isStringNull(advertiserDetails.getFbLink()) || !Constants.isStringNull(advertiserDetails.getInstagramHandleName())){ %>
                	 <hr/>
                	 <h3 class="px-0" style="margin-left: 10px">Social Media</h3>
                	<%} %>
                 <%if(!Constants.isStringNull(advertiserDetails.getFbLink())){ %>
                 
                <div class="col-md-12" style="margin-top: 10px">
                    <!-- facebook -->
              <div class="card social_iframes">
                <div class="card-body socialMedia_card">
                  <div class="facebook-box">
                  <div style="width: 190px;">
					<!-- Page plugin's width will be 190px -->
<%-- 					<div class="fb-page" data-href="<%=advertiserDetails.getFbLink() %>" data-width="420"></div> --%>
					<iframe src="https://www.facebook.com/plugins/page.php?href=<%=advertiserDetails.getFbLink() %>&tabs=timeline&width=400&height=300&small_header=true&adapt_container_width=true&hide_cover=false&show_facepile=false&appId" width="500" height="200" style="border:none;overflow:hidden" scrolling="no" frameborder="0" allowfullscreen="true" allow="autoplay; clipboard-write; encrypted-media; picture-in-picture; web-share"></iframe>
					   
					</div>

	    			</div>    
               </div>
              </div>
                </div>
                <%} %> 
                
                <%if(!Constants.isStringNull(advertiserDetails.getInstagramHandleName())){ %>
                <div class="col-md-12" style="margin-top: 10px">
                    <!-- instagram -->
              <div class="card social_iframes instagram">
                <div class="card-body socialMedia_card">
                  <iframe
                    width="320"
                    height="440"
                    src="<%=advertiserDetails.getInstagramHandleName() %>"
                    frameborder="0"
                  ></iframe>
                </div>  
              </div>
                </div>
                <%} %>
                
<!--                 <div class="col-md-4"> -->
<!--                     twitter -->
<!--               <div class="card social_iframes"> -->
<!--                 <div class="card-body socialMedia_card"> -->
<!--                   <iframe src="" frameborder="0"></iframe> -->
<!--                 </div> -->
<!--               </div> -->
<!--                 </div> -->
            </div>
            
            
        </div>
      </div>
      <!-- right card ends -->
    </div>
  </div>
  </div>
    
     
    
	
		   <!-- Modal -->
    
    <jsp:include page="footer.jsp" flush="true"/>
 


    <!-- Optional JavaScript --> 
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
 
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

 
    <script src="<%=assetsPath %>/js/custom.js"></script>
<%--     <script src="<%=assetsPath %>/js/searchMap.js"></script> --%>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCo_UiZM19FOm6-Vpl42HXNDrpYwGHCzPo"></script>
    <script src="<%=assetsPath %>/js/map.js"></script> 
     
     <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
      crossorigin="anonymous"
    ></script>
    
    <script> 
    
   
    $(document).ready(function () {
    	
    	//liefletMapInIt();
    });
	
    
    </script>
    
  </body>
</html>
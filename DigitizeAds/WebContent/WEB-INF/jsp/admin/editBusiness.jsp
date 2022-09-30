<%@page import="com.digitizeads.util.CipherUtils"%>
<%@page import="com.digitizeads.modal.Advertiser"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%@page import="java.util.ArrayList"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets/admin";

ArrayList<Category> categoryList = request.getAttribute(Constants.CATEGORY_LIST)!=null?(ArrayList<Category>)request.getAttribute(Constants.CATEGORY_LIST):null;
Advertiser advertiser = request.getAttribute(Constants.ADMIN_ADVERTISER_DETAIL)!=null?(Advertiser)request.getAttribute(Constants.ADMIN_ADVERTISER_DETAIL):null;
%> 

<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.2
Version: 3.7.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Community Admin Panel</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
<link href="<%=assetsPath %>/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/fullcalendar/fullcalendar.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/jqvmap.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL PLUGIN STYLES -->
<!-- BEGIN PAGE STYLES -->
<link href="<%=assetsPath %>/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->
<link href="<%=assetsPath %>/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/admin/layout2/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="<%=assetsPath %>/admin/layout2/css/themes/grey.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=assetsPath %>/admin/layout2/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<style>

</style>
</head>

<body class="page-boxed page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-closed-hide-logo">
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<jsp:include page="header.jsp" flush="true"/>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->

	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<jsp:include page="sideMenu.jsp" flush="true"/>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
		
			<div >
				<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				
				<!-- /.modal -->
				<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
				<!-- BEGIN STYLE CUSTOMIZER -->
				
				<!-- END STYLE CUSTOMIZER -->
				<!-- BEGIN PAGE HEADER-->
			<div class="top_head_txt">
					<b><p class="padding-top-10">Edit Business</p></b>
				</div>
			</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN DASHBOARD STATS -->
		
		<div class="page-content ">
		
			<p class="black"><b> <a href="<%=contextPath %>/admin/business/viewall">Manage</a></b></p>
		
		<div class="portlet box ">
						<div class="portlet-body form ">
							<form enctype="multipart/form-data" class="form-horizontal" role="form" action="<%=contextPath %>/admin/business/edit/<%=CipherUtils.encryptId(advertiser.getAdvertiserId()+"") %>/" method="post">
								<input type="hidden" name="exImg" id="exImg" value="<%=advertiser.getLogo()%>">
								<div class="form-body">
									 
					 				<div class="form-group"> 
										<label class="col-md-2 control-label">Business Name</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="name" id="name" value="<%=advertiser.getName()%>">
										</div>
									</div>
                                    
                                    
                                     <div class="form-group">
										<label class="col-md-2 control-label">Select Category Name</label>
										<div class="col-md-4">
											<%if(categoryList!=null && categoryList.size()>0){ %>
											<select class="form-control" name="categoryId" id="categoryId">
												<option>Select</option>
												<%for(Category category : categoryList){ %>
													<option <%if(category.getCategoryId()==advertiser.getCategory().getCategoryId()){ %>selected="selected"<%} %> value="<%=category.getCategoryId() %>"><%=category.getName() %></option>
												<%} %>
											</select>
											<%} %> 
										</div>
									</div>
                                    
									<div class="form-group">
										<label class="col-md-2 control-label">Logo Image</label>
										
										<div class="col-md-5 col-xs-6">
										  <div class="input-group up_img" >
                                             <input id="input-7" name="file" accept="image/*" type="file" class="file form-control" data-allowed-file-extensions='["jpg", "jpeg", "png", "gif", "bmp", "tiff"]'/>
										  </div>
										</div>
									</div> 
									
									
									<div class="form-group hide">
										<label class="col-md-2 control-label">Logo Image</label>
										<div class="col-md-4">
										   <img class="form-control bak_img" id="blah" src="assets/images/whiteimg.jpg" alt="your image"  />
										</div>
										<div class="col-md-2 col-xs-6">
										  <div class="input-group up_img" >
                                             <span class="input-group-btn">
                                              <span class="btn btn-primary btn-file">
										          Upload Image	<input type="file" onchange="readURL(this);" class="up_img_btn" >
									       	  </span>
                                            </span>
										  </div>
										</div>
			                            <div class="col-md-2 col-xs-6">
											<button type="button" class="btn red ">Remove Image</button>
										</div>
									</div>
									
									<div class="form-group hide">
										<label class="col-md-2 control-label">Video</label>
										<div class="col-md-4">
										   <img class="form-control bak_img" id="blah" src="assets/images/whiteimg.jpg" alt="your image"  />
										</div>
										<div class="col-md-2 col-xs-6">
										  <div class="input-group up_img" >
                                             <span class="input-group-btn">
                                              <span class="btn btn-primary btn-file">
										          Upload Video	<input type="file" onchange="readURL(this);" class="up_img_btn" >
									       	  </span>
                                            </span>
										  </div>
										</div>
			                            <div class="col-md-2 col-xs-6">
											<button type="button" class="btn red ">Remove </button>
										</div>
									</div>
									
								</div>
								
								<hr>
								<div class="form-body">
									  <p class="table_head padding-bottom-20"><b>Social Media </b> </p>
									  <div class="portlet-body">
								
									
										<div class="form-group">
										<label class="col-md-2 control-label">Facebook</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="fbLink" id="fbLink" value="<%=advertiser.getFbLink()%>">
											<span>Ex : https://facebook.com/natgeo</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Twitter</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="twitterLink" id="twitterLink" value="<%=advertiser.getTwitterLink()%>">
											<span>Ex : https://twitter.com/NatGeo</span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Instagram Handle Name</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="instaLink" id="instaLink" value="<%=advertiser.getInstagramHandleName()%>">
											<span>Ex : natgeo</span>
										</div>
									</div>
									
									
							</div>
									
									
								</div>
								<hr>
								<div class="form-body">
									<div class="form-group">
										<label class="col-md-2 control-label">Add Address</label>
										<div class="col-md-4">
											<div class="input-icon right">
													<i class="fa fa-map-marker"></i>
													<input type="text" class="form-control" name="address" id="address" value="<%=advertiser.getAddress()%>">
												</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Contact Number</label>
										<div class="col-md-4">
										   <input type="text" class="form-control num_rad" name="phoneNumber" id="phoneNumber" value="<%=advertiser.getPhoneNumber()%>">
											
										</div>
										
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Email</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="email" id="email" value="<%=advertiser.getEmail()%>">
										</div>
								    </div>
									<div class="form-group">
										<label class="col-md-2 control-label">Website URL</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="website" id="website" value="<%=advertiser.getWebsite()%>">
											<span>Ex : https://adwitglobal.com</span>
										</div>
								    </div>
								</div>
						
								<hr>
								
								
								<div class="form-body">
								<div class="form-group">
										<label class="col-md-2 control-label">Latitude</label>
										<div class="col-md-4">
										   <input type="text" class="form-control num_rad" id="latitude" name="latitude" value="<%=advertiser.getLatitude()%>">
										   <span>to get lat/long, please use <a href="https://www.latlong.net/convert-address-to-lat-long.html" target="_blank">https://www.latlong.net/convert-address-to-lat-long.html</a></span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Longitude</label>
										<div class="col-md-4">
										   <input type="text" class="form-control num_rad"  id="longitude" name="longitude" value="<%=advertiser.getLongitude()%>">
										</div>
									</div>
									 <div class="form-group">
										<label class="col-md-2 control-label">About Us</label>
										<div class="col-md-4">
											<textarea class="form-control" rows="3" name="aboutUs" id="aboutUs" ><%=advertiser.getAboutUs()%></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Featured</label>
										<div class="col-md-5">
											<input type="checkbox" name="isFeatured" id="isFeatured" value="1" <%if(advertiser.isFeatured()){%>checked="checked"<%} %>>
											<span class="help-inline">check it for Featured Ads</span>
										</div>
									</div>
								</div>
								
						
						</div>
							<div class="row  portlet box " >
					<div class="col-md-12">
				
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
					
							
							<div class="portlet-body back_white" >
								
								<div class="margin-top-20">

								        <button type="submit" class="btn btn-primary col-md-2">Save Business</button>
										<a href="<%=contextPath %>/admin/business/viewall" class="btn btn-border-blue  col-md-2">Back</a>
										</div>
						
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
					</div>
			</form>
		
		<div class="portlet-body padding-top-60 padding-bottom-10">
						<p class="center external_footer">&copy; 2018-2019 Adwit Global. All rights reserved</p>
						</div>
		
		
				<!-- END DASHBOARD STATS -->
				
			</div>
		</div>
		<!-- END CONTENT -->
		<!-- BEGIN QUICK SIDEBAR -->
		<!--Cooming Soon...-->
		<!-- END QUICK SIDEBAR -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	  <jsp:include page="footer.jsp" flush="true"/>
	<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="<%=assetsPath %>/global/plugins/respond.min.js"></script>
<script src="<%=assetsPath %>/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="<%=assetsPath %>/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="<%=assetsPath %>/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/jquery.vmap.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.world.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jqvmap/jqvmap/data/jquery.vmap.sampledata.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
<!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support -->
<script src="<%=assetsPath %>/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="<%=assetsPath %>/global/scripts/metronic.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/admin/layout2/scripts/layout.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/admin/layout2/scripts/demo.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="<%=assetsPath %>/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   Demo.init(); // init demo features 
   Index.init();   
   Index.initDashboardDaterange();
   Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
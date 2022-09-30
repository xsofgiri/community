<%@page import="com.digitizeads.modal.AdvertiserGallery"%>
<%@page import="com.digitizeads.util.CipherUtils"%>
<%@page import="com.digitizeads.modal.Advertiser"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%@page import="java.util.ArrayList"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets/admin";

Advertiser advertiser = request.getAttribute(Constants.ADMIN_ADVERTISER_DETAIL)!=null?(Advertiser)request.getAttribute(Constants.ADMIN_ADVERTISER_DETAIL):null;
ArrayList<AdvertiserGallery> advertiserGalleryList = request.getAttribute(Constants.ADVERTISER_GALLERY_LIST)!=null?(ArrayList<AdvertiserGallery>)request.getAttribute(Constants.ADVERTISER_GALLERY_LIST):null;

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
.preview-images-zone {
    width: 100%;
    border: 1px solid #ddd;
    min-height: 180px;
    /* display: flex; */
    padding: 5px 5px 0px 5px;
    position: relative;
    overflow:auto;
}
.preview-images-zone > .preview-image:first-child {
  
    position: relative;
    margin-right: 5px;
}
.preview-images-zone > .preview-image {
    height: 90px;
    width: 90px;
    position: relative;
    margin-right: 5px;
    float: left;
    margin-bottom: 5px;
}
.preview-images-zone > .preview-image > .image-zone {
    width: 100%;
    height: 100%;
}
.preview-images-zone > .preview-image > .image-zone > img {
    width: 100%;
    height: 100%;
}
.preview-images-zone > .preview-image > .tools-edit-image {
    position: absolute;
    z-index: 100;
    color: #fff;
    bottom: 0;
    width: 100%;
    text-align: center;
    margin-bottom: 10px;
    display: none;
}
.preview-images-zone > .preview-image > .image-cancel {
    font-size: 18px;
    position: absolute;
    top: 0;
    right: 0;
    font-weight: bold;
    margin-right: 10px;
    cursor: pointer;
    display: none;
    z-index: 100;
}
.preview-image:hover > .image-zone {
    cursor: move;
    opacity: .5;
}
.preview-image:hover > .tools-edit-image,
.preview-image:hover > .image-cancel {
    display: block;
}
.ui-sortable-helper {
    width: 90px !important;
    height: 90px !important;
}


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
					<b><p class="padding-top-10"><%=advertiser.getName() %> - Gallery</p></b>
				</div>
			</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN DASHBOARD STATS -->
		
		<div class="page-content ">
		
			<p class="black"><b> <a href="<%=contextPath %>/admin/business/viewall">Manage</a></b></p>
		
		<div class="portlet box ">
						<div class="portlet-body form ">
							<form enctype="multipart/form-data" class="form-horizontal" role="form" action="<%=contextPath %>/admin/business/gallery/<%=CipherUtils.encryptId(advertiser.getAdvertiserId()+"") %>/" method="post">
									
									<div class="form-group">
										<div class="col-md-8">
						
										<div class="preview-images-zone"> 
									 
											 
										</div>
										</div>
										<div class="col-md-2 col-xs-6">
										   <fieldset class="form-group">
												<button type="button" class="btn btn-primary " href="javascript:void(0)" onclick="$('#gImages').click()">Browse Image</button>
												<input type="file" id="gImages" name="gImages" style="display: none;" class="form-control" multiple>
											</fieldset>
										</div>
			                            
									</div>
									
								
								<hr>
								
						</div>
							<div class="row  portlet box " >
					<div class="col-md-12">
				
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
					
							
							<div class="portlet-body back_white" >
								
								<div class="margin-top-20">

								        <button type="submit" class="btn btn-primary col-md-2">Add Images</button>
										<a href="<%=contextPath %>/admin/business/viewall" class="btn btn-border-blue  col-md-2">Back</a>
										</div>
						
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
					</div>
					
					<%if(advertiserGalleryList!=null && advertiserGalleryList.size()>0){%>
					
						<div class="portlet-body back_white" >
							<table class="table table-striped table-bordered table-hover" id="sample_1">
								<thead>
									<tr>
										
										<th>
											Image
										</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
						<%for(AdvertiserGallery advertiserGallery : advertiserGalleryList){
						%>	
							<tr>
							
								<td align="center"><img style="width:auto;height: 100px" src="https://adwit-community.s3.ap-south-1.amazonaws.com/business/<%=advertiserGallery.getImageName() %>"></td>
								<td><a href="javascript:;" onclick="removeImage('<%=advertiserGallery.getAdvertiserGalleryId() %>');">Delete</a></td>
							</tr>
							 
					<%}%>
					</tbody>
					</table>
					</div>
						<%}%>
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

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}


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


function keyfunction(){
    $(".numeric").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
             // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
    $(".numeric").keyup(function(e) {  
    	var numericvalue = $(this); 
    	var position = getCursorPosition(numericvalue);
        if (// Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
             // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
                 // let it happen, don't do anything
                 return;
        }
	        
	    var inputval = numericvalue.val();
	    digits = inputval.replace(/\D/g,'');

        if(digits.length > 2){
            res = digits.substring(0, 3);
            result = "(" + res + ") ";
            res = digits.substring(3);
        	result = result + res;
        	
            if(digits.length > 6){
                res = digits.substring(0, 3);
                result = "(" + res + ") ";
            	res = digits.substring(3,6);
            	result = result + res + "-";
            	res = digits.substring(6);
            	result = result + res;
            }
            numericvalue.val(result);
            result ="";
        }else{
        	numericvalue.val(digits);
        }
     	// Allow: backspace, delete, tab, escape, enter
    	if($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1){
    		setSelectionRange(numericvalue[0], position, position);
        }
    });
}
//Set cursor position
function setSelectionRange(input, selectionStart, selectionEnd) {
	  if (input.setSelectionRange) {
	    input.focus();
	    input.setSelectionRange(selectionStart, selectionEnd);
	  } else if (input.createTextRange) {
	    var range = input.createTextRange();
	    range.collapse(true);
	    range.moveEnd('character', selectionEnd);
	    range.moveStart('character', selectionStart);
	    range.select();
	  }
}

// Get cursor position
function getCursorPosition (numericvalue) {
        var pos = 0;
        var el = numericvalue.get(0);
        // IE Support
        if (document.selection) {
            el.focus();
            var Sel = document.selection.createRange();
            var SelLength = document.selection.createRange().text.length;
            Sel.moveStart('character', -el.value.length);
            pos = Sel.text.length - SelLength;
        }
        // Firefox support
        else if (el.selectionStart || el.selectionStart == '0')
            pos = el.selectionStart;
        return pos;
}
keyfunction();

//Multiple file upload with preview
$(document).ready(function() {
    document.getElementById('gImages').addEventListener('change', readImage, false);
    
    $( ".preview-images-zone" ).sortable();
     
    $(document).on('click', '.image-cancel', function() {
        let no = $(this).data('no');
        $(".preview-image.preview-show-"+no).remove();
    });
});



var num = 4;
function readImage() {
    if (window.File && window.FileList && window.FileReader) {
        var files = event.target.files; //FileList object
        var output = $(".preview-images-zone");

        for (let i = 0; i < files.length; i++) {
            var file = files[i];
            if (!file.type.match('image')) continue;
            
            var picReader = new FileReader();
            
            picReader.addEventListener('load', function (event) {
                var picFile = event.target;
                var html =  '<div class="preview-image preview-show-' + num + '">' +
                            '<div class="image-cancel" data-no="' + num + '">x</div>' +
                            '<div class="image-zone"><img id="pro-img-' + num + '" src="' + picFile.result + '"></div>' +
                            '<div class="tools-edit-image"><a href="javascript:void(0)" data-no="' + num + '" class="btn btn-light btn-edit-image"></a></div>' +
                            '</div>';

                output.append(html);
                num = num + 1;
            });

            picReader.readAsDataURL(file);
        }
        $("#pro-image").val('');
    } else {
        console.log('Browser not support');
    }
}


removeImage = function(advertiserGalleryId){
	var result = confirm("Are you sure want to delete the image");
	if(result){
		$.ajax({  
		       url : '<%=contextPath%>/admin/business/gallery/remove',  
		       type : 'POST',
		       dataType: 'json',
		       data: { advertiserGalleryId:advertiserGalleryId, advertiserId: '<%=advertiser.getAdvertiserId()%>'},
		       success : function(response) {
		    	   console.log('response : '+response);
		    	   location.reload();
		       }
		});
	}
	
}

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
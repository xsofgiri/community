 <%@page import="com.digitizeads.modal.SubDomain"%>
<%@page import="com.digitizeads.util.EncryptionUtil"%>
<%@page import="com.digitizeads.util.Constants"%>
<%@page import="com.digitizeads.modal.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.digitizeads.bean.CategoryBean"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";

ArrayList<Category> categoryList = session.getAttribute(Constants.FOOTER_CATEGORY_LIST)!=null?(ArrayList<Category>)session.getAttribute(Constants.FOOTER_CATEGORY_LIST):null;

if(categoryList==null || categoryList.size()==0){
	ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
	categoryList = categoryBean.getAllCategories();
	session.setAttribute(Constants.FOOTER_CATEGORY_LIST, categoryList);
}


SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;
%>

 <div class="section-padding padding-bottom-60" style="background-color: #e3f4e4;">
      <div class="container">
        <div class="row">
          <div class="col">
            <div class="section-header padding-bottom-20">
              <p style="font-size: 1.4rem"> <%=userSubDomain!=null && !Constants.isStringNull(userSubDomain.getFooterText())?userSubDomain.getFooterText():"" %></p>
             </div>
          </div> 
        </div>
        
        </div>
</div>
 <!-- Footer -->
  
    <footer class="padding-top-100 footer-bg">
      <div class="footer-widget-wrap padding-bottom-60">
        <div class="container">
          <div class="row">
 
            <div class="col-lg-3 col-sm-3">
              <div class="footer-widget footer-widget-links">
                <h5 class="footer-widget-title">Categories</h5>
                <ul>
				 <%if(categoryList!=null && categoryList.size()>0){
					 for(int i=0;i<categoryList.size()/2;i++){
						 Category category = (Category)categoryList.get(i);
						 %>
					 
				 	 <li><a href="<%=contextPath %>/category/<%=Constants.toSlug(category.getName()) %>/<%=EncryptionUtil.encode(category.getCategoryId()+"") %>/"><%=category.getName() %></a></li>
				  <%}
					 }%>
                </ul>
              </div>
            </div> 
            
            <div class="col-lg-3 col-sm-3">
              <div class="footer-widget footer-widget-links">
                <h5 class="footer-widget-title">Categories</h5>
                <ul>
				  <%if(categoryList!=null && categoryList.size()>0){
					 for(int i=categoryList.size()/2;i<categoryList.size();i++){
						 Category category = (Category)categoryList.get(i);
						 %>
					 
				 	 <li><a href="<%=contextPath %>/category/<%=Constants.toSlug(category.getName()) %>/<%=EncryptionUtil.encode(category.getCategoryId()+"") %>/"><%=category.getName() %></a></li>
				  <%}
					 }%>
				  
                </ul>
              </div>
            </div>
            
<!--              <div class="col-lg-3 col-sm-3"> -->
<!--               <div class="footer-widget footer-widget-links"> -->
<!--                 <h5 class="footer-widget-title">Subscription</h5> -->
<!--                <p> Sign up for our new and improved full color E-edition by <a href="http://www.virginislandsdailynews.com/eedition/" target="_blank"> clicking the link here</a> or visiting us at <a target="_blank" href="http://virginislandsdailynews.com">www.virginislandsdailynews.com</a>.</p> -->
<!--               </div> -->
<!--             </div>  -->
            <div class="col-lg-3 col-sm-3">
              <div class="footer-widget footer-about footer-widget-links">
                <h5 class="footer-widget-title">About</h5>
                 <ul>
<!--                   <li><a href="#" data-toggle="modal" data-target="#accountPopup">Login</a></li> -->
                  <li><a href="<%=contextPath %>/terms">Terms of Service</a></li>
                  <li><a href="<%=contextPath %>/privacy-policy">Privacy policy</a></li>
                  
                 </ul>
              </div>
            </div>
          </div> 
		  
        </div>
      </div>
	   <div class="footer-bottom">
        <div class="container">
          <div class="row align-items-center">
           <div class="col-lg-9 order-lg-1">
              2022 Powered by <a href="#" target="_blank" class="bold-link">Adwit Global</a></li>
             </div>
            <div class="col-lg-3 order-lg-3">
              <div class="backtotop">
                <a href="#">Back to Top<i class="fas fa-angle-up"></i></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    
    </footer>
    <!-- Footer End -->

<%--   <script src="<%=assetsPath %>/assets/js/jquery.min.js"></script> --%>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>

  
<script>

$(document).ready(function () {
	$(".track_website").click(function () {
		let advertiserId = $(this).data("bu_id"); 
		console.log('advertiserId : '+advertiserId);
		 trackUsage(advertiserId, '0', 'WEBSITE');
	});

	$(".track_direction").click(function () {
		let advertiserId = $(this).data("bu_id");
		console.log(advertiserId);
		
		 trackUsage(advertiserId, '0', 'DIRECTION');
	});
 
	$(".track_phone").click(function () {
		let advertiserId = $(this).data("bu_id");
		console.log(advertiserId);
		 trackUsage(advertiserId, '0', 'PHONE');
	});


	$(".track_share").click(function () {
		let advertiserId = $(this).data("bu_id");
		 trackUsage(advertiserId, '0', 'SHARE');
	});

	$(".track_imageGallery").click(function (event) {
		let advertiserId = $(this).data("bu_id");
	  	 trackUsage(advertiserId, '0', 'IMAGE');
	  });

	$(".track_video").click(function (event) {
		let advertiserId = $(this).data("bu_id");
	  	 trackUsage(advertiserId, '0', 'VIDEO');
	  });
	
	
	<%if(userSubDomain==null){%>
		setSubDomain();
	<%}%>
});



trackUsage = function(advertiserId, sourceId, sourceType){
	 $.ajax({   
	       url : '<%=contextPath%>/business/trackusage',   
	       type : 'POST',
	       data: { advertiserId: advertiserId, sourceId : sourceId, sourceType : sourceType },
	}); 
} 

 
setSubDomain = function(){
	var url = window.location.hostname;
	console.log('url : '+url);
	url = 'hyperlap.adwitglobal.com';
	 $.ajax({    
	       url : '<%=contextPath%>/setsubdomain',   
	       type : 'POST',
	       data: { reqURL : url },
	       success: function(data) {
            	console.log('success : '+data.status);
            	location.reload();
            },
            error: function(data) {
            	console.log('error : '+data.status);
            	
            } 
	 });
} 


</script>

 <%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
%>

 <!-- Footer -->
    
    <footer class="padding-top-100 footer-bg">
      <div class="footer-widget-wrap padding-bottom-60">
        <div class="container">
          <div class="row">

            <div class="col-lg-3 col-sm-12">
              <div class="footer-widget footer-about footer-widget-links">
               
                 <h5 class="footer-widget-title">DIRECTORY</h5>
                 <ul>
                  <li><a href="#" data-toggle="modal" data-target="#accountPopup">Login</a></li>
                  <li><a href="<%=contextPath %>/terms">Terms of Service</a></li>
                  <li><a href="<%=contextPath %>/privacy-policy">Privacy policy</a></li>
                  
                 </ul>
              </div>
            </div>
            <div class="col-lg-3 col-sm-4">
              <div class="footer-widget footer-widget-links">
                <h5 class="footer-widget-title">CATEGORIES</h5>
                <ul>
				  <li><a href="<%=contextPath %>/category/category_slug/category_u_id/">Shop Local</a></li>
				  
                </ul>
              </div>
            </div>
            <div class="col-lg-3 col-sm-4">
              <div class="footer-widget footer-widget-links">
                <h5 class="footer-widget-title">&nbsp;</h5>
                <ul>
				<li><a href="<%=contextPath %>/category/category_slug/category_u_id/">Food & Beverage</a></li>
 
                </ul>
              </div>
            </div>
            <div class="col-lg-3 col-sm-4">
              <div class="footer-widget footer-widget-links">
                <h5 class="footer-widget-title">&nbsp;</h5>
                <ul>
                  <li><a href="<%=contextPath %>/category/category_slug/category_u_id/">Public Services</a></li>
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
              <a href="#"> 2020 <a href="http://www.bakersfield.com/" rel="">The Bakersfield Californian</a>. Powered by <a href="" target="_blank" class="bold-link">Adwit</a><span> - helping local media with innovative <a href="" target="_blank">print to web</a> and directory software</span>.</a></li>
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


<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
%>

<header>
      <div class="cp-nav nav-3 color-bg">
        <nav class="navbar navbar-expand-lg">
          <a class="navbar-brand" href="<%=contextPath %>/">
                <img src="<%=assetsPath %>/bakers_image/original_logo.png" class="img-fluid" alt="">
              </a>
         
          
           <nav role="navigation">
			  <div id="menuToggle">
				<input type="checkbox">
				<span></span>
				<span></span>
				<span></span>
				<ul id="menu">
					  <a href="<%=contextPath %>/" style="display: block;"><li><i class="material-icons clr">business</i>Businesses</li></a>
					  <a href="<%=contextPath %>/" style="display: block;"><li><i class="material-icons clr">picture_in_picture</i>Ads</li></a>
					  <a href="<%=contextPath %>/" style="display: block;"><li><i class="material-icons clr">layers</i>Categories</li></a>
					  <a href="<%=contextPath %>/" style="display: block;"><li>Back to bakersfield.com</li></a>
					</ul>
			  </div>
			</nav>

          
        </nav>
      </div>
    </header>

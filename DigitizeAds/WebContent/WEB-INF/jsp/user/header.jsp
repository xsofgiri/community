<%@page import="com.digitizeads.modal.SubDomain"%>
<%@page import="com.digitizeads.util.Constants"%>
<%
String contextPath = request.getContextPath();
String assetsPath = contextPath+"/assets";
SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;

		 
String logoUrl = userSubDomain!=null ? assetsPath+"/"+userSubDomain.getLogoUrl():"";
 
%>

<style>
#menuToggle{
	right:80px;
}

#menu{
	margin:-100px 0 0 0;
}
</style>
<header>
      <div class="cp-nav nav-3 color-bg">
        <nav class="navbar navbar-expand-lg">
          <a class="navbar-brand" href="<%=contextPath %>/">
<%--                 <img src="<%=assetsPath %>/bakers_image/daily.png" class="img-fluid" alt=""> --%>

 				<img src="<%=logoUrl %>" class="img-fluid" alt="<%=userSubDomain!=null?userSubDomain.getName():"" %>">
              </a>   
          
           <nav role="navigation"> 
			  <div id="menuToggle">
				<input type="checkbox">
				<span></span>
				<span></span>
				<span></span>
				<ul id="menu">
					  <a href="<%=contextPath %>/business/all/" style="display: block;"><li><i class="material-icons clr">business</i>Businesses</li></a>
<%-- 					  <a href="<%=contextPath %>/" style="display: block;"><li><i class="material-icons clr">picture_in_picture</i>Coupons</li></a> --%>
					  <a href="<%=contextPath %>/category/all/" style="display: block;"><li><i class="material-icons clr">layers</i>Categories</li></a>
<%-- 					  <a href="<%=contextPath %>/" style="display: block;"><li>Back to </li></a> --%>
					</ul>
			  </div>
			</nav>

          
        </nav>
      </div>
    </header>

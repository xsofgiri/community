package com.digitizeads.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.digitizeads.bean.AdvertiserBean;
import com.digitizeads.bean.CategoryBean;
import com.digitizeads.modal.Advertiser;
import com.digitizeads.modal.AdvertiserGallery;
import com.digitizeads.modal.AdvertiserService;
import com.digitizeads.modal.AdvertiserTrack;
import com.digitizeads.modal.Category;
import com.digitizeads.modal.Coupon;
import com.digitizeads.modal.ReturnMessage;
import com.digitizeads.modal.SubDomain;
import com.digitizeads.util.Constants;
import com.digitizeads.util.EncryptionUtil;
import com.digitizeads.util.PageHandler;

@Controller
@RequestMapping("")
public class HomeAction {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView displayHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;
		
		ArrayList<Advertiser> featuredAdvertiserList = advertiserBean.getFeaturedAdvertiser(userSubDomain);
		request.setAttribute(Constants.FEATURED_ADVERTISER_LIST, featuredAdvertiserList);

		ArrayList<Advertiser> trendingAdvertiserList = advertiserBean.getTrendingAdvertiser(userSubDomain);
		request.setAttribute(Constants.TRENDING_ADVERTISER_LIST, trendingAdvertiserList);

		ArrayList<Category> categoryList = categoryBean.getAllCategories();
		request.setAttribute(Constants.CATEGORY_LIST, categoryList);

		ArrayList<Coupon> couponList = advertiserBean.getFeaturedCouponList();
		request.setAttribute(Constants.FEATURED_COUPON_LIST, couponList);

		ArrayList<Advertiser> allAdvertiserList = advertiserBean.getAllAdvertiserList(userSubDomain, 20);
		request.setAttribute(Constants.ALL_ADVERTISER_LIST, allAdvertiserList);

		ModelAndView model = new ModelAndView("/user/welcome");
		return model;
	}

	@RequestMapping(value = "/category/all/", method = RequestMethod.GET)
	public ModelAndView displayCategoryList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");

		ArrayList<Category> categoryList = categoryBean.getAllCategories();
		request.setAttribute(Constants.CATEGORY_LIST, categoryList);

		ModelAndView model = new ModelAndView("/user/allCategoryList");
		return model;
	}

	@RequestMapping(value = "/category/{category_slug}/{category_id}/", method = RequestMethod.GET)
	public ModelAndView displayCategoryHome(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("category_id") String categoryId) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/categoryBusinessList");
		String contextPath = request.getContextPath();
		SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		categoryId = EncryptionUtil.decode(categoryId);

		System.out.println("categoryId : " + categoryId);

		Category categoryDetails = categoryBean.getCategoryDetails(Integer.parseInt(categoryId));
		request.setAttribute(Constants.CATEGORY_DETAILS, categoryDetails);

		PageHandler pageHandler = new PageHandler();

		if (pageHandler.getSurl() == null) {
			pageHandler.setCurrentPage(1);
			pageHandler.setLimit(10);
			pageHandler.setOffset(0);
			pageHandler.setReqPage(1);
			pageHandler.setSurl(contextPath + "/category/" + Constants.toSlug(categoryDetails.getName()) + "/"
					+ EncryptionUtil.encode(categoryDetails.getCategoryId() + "") + "/");
		}

		String whrCondition = "";

		String pageNo = "1";
		try {
			pageNo = (String) request.getParameter("pageNo");
			int intPageNo = Integer.parseInt(pageNo);
		} catch (NumberFormatException fe) {
			pageNo = "1";
		} catch (Exception e) {
			pageNo = "1";
		}

		if (pageNo != null) {
			pageHandler.setCurrentPage(Integer.parseInt(pageNo));
			if (Integer.parseInt(pageNo) > 1)
				pageHandler.setOffset(pageHandler.getLimit() * (pageHandler.getCurrentPage() - 1));
			else
				pageHandler.setOffset(0);
		}

		whrCondition = " AND TA.CATEGORY_ID=" + categoryDetails.getCategoryId();

		ArrayList<Advertiser> advertiserList = advertiserBean.getAdvertiserList(pageHandler, whrCondition);
		request.setAttribute(Constants.ADVERTISER_LIST, advertiserList);

		request.setAttribute(Constants.ADVERTISER_PAGINATION, pageHandler);

		return model;
	}

	@RequestMapping(value = "/{business_slug}", method = RequestMethod.GET)
	public ModelAndView displayBusinessDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("business_slug") String businessSlug) {
		HttpSession session = request.getSession();

		System.out.println("businessSlug : " + businessSlug);

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		Advertiser advertiserDetails = advertiserBean.getAdvertiserDetails(businessSlug);
		request.setAttribute(Constants.ADVERTISER_DETAILS, advertiserDetails);

		ArrayList<AdvertiserGallery> advertiserGalleryList = advertiserBean.getBusinessGalleryList(advertiserDetails);
		request.setAttribute(Constants.ADVERTISER_GALLERY_LIST, advertiserGalleryList);

		ArrayList<AdvertiserService> advertiserServiceList = advertiserBean.getBusinessServiceList(advertiserDetails);
		request.setAttribute(Constants.ADVERTISER_SERVICE_LIST, advertiserServiceList);

		ArrayList<Coupon> couponList = advertiserBean.getActiveBusinessCouponList(advertiserDetails);
		request.setAttribute(Constants.ADVERTISER_COUPON_LIST, couponList);

		String ipAddress = request.getRemoteAddr();
		advertiserBean.updateAdvertiserView(ipAddress, advertiserDetails.getAdvertiserId());

		ModelAndView model = new ModelAndView("/user/businessDetails");
		return model;
	}

	@RequestMapping(value = "/coupon/{coupon_slug}/{coupon_id}/", method = RequestMethod.GET)
	public ModelAndView displayAdDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("coupon_id") String couponId) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/couponDetails");

		couponId = EncryptionUtil.decode(couponId);
		System.out.println("couponId : " + couponId);

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		Coupon couponDetails = advertiserBean.getCouponDetails(Integer.parseInt(couponId));
		request.setAttribute(Constants.COUPON_DETAIL, couponDetails);
		return model;
	}

	@RequestMapping(value = "/business/all", method = RequestMethod.GET)
	public ModelAndView displayAllBusinessHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/allBusinessList");
		String contextPath = request.getContextPath();
		SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;

		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		PageHandler pageHandler = new PageHandler();

		if (pageHandler.getSurl() == null) {
			pageHandler.setCurrentPage(1);
			pageHandler.setLimit(9);
			pageHandler.setOffset(0);
			pageHandler.setReqPage(1);
			pageHandler.setSurl(contextPath + "/business/all/");
		}

		String whrCondition = " AND SUBDOMAIN_ID="+userSubDomain.getSubDomainId();

		String name = (String) session.getAttribute(Constants.ADVERTISER_SEARCH_NAME);

		if (!Constants.isStringNull(name)) {
			whrCondition = " AND TA.NAME LIKE '%" + name + "%'";
		}

		String pageNo = "1";
		try {
			pageNo = (String) request.getParameter("pageNo");
			int intPageNo = Integer.parseInt(pageNo);
		} catch (NumberFormatException fe) {
			pageNo = "1";
		} catch (Exception e) {
			pageNo = "1";
		}

		if (pageNo != null) {
			pageHandler.setCurrentPage(Integer.parseInt(pageNo));
			if (Integer.parseInt(pageNo) > 1)
				pageHandler.setOffset(pageHandler.getLimit() * (pageHandler.getCurrentPage() - 1));
			else
				pageHandler.setOffset(0);
		}

		ArrayList<Advertiser> advertiserList = advertiserBean.getAdvertiserList(pageHandler, whrCondition);
		request.setAttribute(Constants.ADVERTISER_LIST, advertiserList);

		request.setAttribute(Constants.ADVERTISER_PAGINATION, pageHandler);

		return model;
	}

	@RequestMapping(value = "/business/all", method = RequestMethod.POST)
	public ModelAndView searchAllBusinessHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/allBusinessList");
		String contextPath = request.getContextPath();
		SubDomain userSubDomain = session.getAttribute(Constants.USER_SUBDOMAIN)!=null?(SubDomain)session.getAttribute(Constants.USER_SUBDOMAIN):null;

		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
		AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

		PageHandler pageHandler = new PageHandler();

		if (pageHandler.getSurl() == null) {
			pageHandler.setCurrentPage(1);
			pageHandler.setLimit(9);
			pageHandler.setOffset(0);
			pageHandler.setReqPage(1);
			pageHandler.setSurl(contextPath + "/business/all/");
		}

		String name = request.getParameter("title");
		String whrCondition = " AND SUBDOMAIN_ID="+userSubDomain.getSubDomainId();

		if (!Constants.isStringNull(name)) {
			whrCondition = " AND TA.NAME LIKE '%" + name + "%'";
		}

		String pageNo = "1";
		try {
			pageNo = (String) request.getParameter("pageNo");
			int intPageNo = Integer.parseInt(pageNo);
		} catch (NumberFormatException fe) {
			pageNo = "1";
		} catch (Exception e) {
			pageNo = "1";
		}

		if (pageNo != null) {
			pageHandler.setCurrentPage(Integer.parseInt(pageNo));
			if (Integer.parseInt(pageNo) > 1)
				pageHandler.setOffset(pageHandler.getLimit() * (pageHandler.getCurrentPage() - 1));
			else
				pageHandler.setOffset(0);
		}

		ArrayList<Advertiser> advertiserList = advertiserBean.getAdvertiserList(pageHandler, whrCondition);
		request.setAttribute(Constants.ADVERTISER_LIST, advertiserList);

		request.setAttribute(Constants.ADVERTISER_PAGINATION, pageHandler);

		session.setAttribute(Constants.ADVERTISER_SEARCH_NAME, name);

		return model;
	}

	@RequestMapping(value = "/business/trackusage", method = RequestMethod.POST)
	public @ResponseBody int trackBusinessUsage(HttpServletRequest request, HttpServletResponse response) {
		int result = -1;
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

			String advertiserId = request.getParameter("advertiserId");
			String sourceId = request.getParameter("sourceId");
			String sourceType = request.getParameter("sourceType");
			String ipAddress = request.getRemoteAddr();

			sourceId = !Constants.isStringNull(sourceId) ? EncryptionUtil.decode(sourceId) : null;
			advertiserId = !Constants.isStringNull(advertiserId) ? EncryptionUtil.decode(advertiserId) : "0";

			AdvertiserTrack advertiserTrack = new AdvertiserTrack();

			Advertiser advertiser = new Advertiser();
			advertiser.setAdvertiserId(Integer.parseInt(advertiserId));
			advertiserTrack.setAdvertiser(advertiser);
			advertiserTrack.setIpAddress(ipAddress);
			advertiserTrack.setSourceId(!Constants.isStringNull(sourceId) ? Integer.parseInt(sourceId) : 0);
			advertiserTrack.setSourceType(sourceType);

			if (Integer.parseInt(advertiserId) > 0 && sourceType != null)
				advertiserBean.updateAdvertiserClick(advertiserTrack);

		} catch (Exception e) {
			System.out.println("Exception trackBusinessUsage : " + e);
		}

		return result;
	}

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView displayTermsHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/terms");
		return model;
	}

	@RequestMapping(value = "/privacy-policy", method = RequestMethod.GET)
	public ModelAndView displayPrivacyPolicyHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/user/privacyPolicy");
		return model;
	}

	@RequestMapping(value = "/setsubdomain", method = RequestMethod.POST)
	public @ResponseBody ReturnMessage executeAjaxSetSubDomain(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();
		ReturnMessage returnMessage = new ReturnMessage();
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

			AdvertiserBean advertiserBean = (AdvertiserBean) context.getBean("advertiserBean");

			String currentURL = request.getParameter("reqURL");
			 System.out.println("currentURL : " + currentURL);
			String subDomain = currentURL != null && currentURL.indexOf(".") > 0
					? currentURL.substring(0, currentURL.indexOf("."))
					: null;
			 System.out.println("subDomain : " + subDomain);

			if (subDomain != null && !"".equals(subDomain) && !"www".equals(subDomain)
					&& !"adwitglobal.com".equals(subDomain)) {
				SubDomain userSubDomain = advertiserBean.findSubDomain(subDomain.trim());
				session.setAttribute(Constants.USER_SUBDOMAIN, userSubDomain);

				returnMessage.setStatus(Constants.USER_AJAX_RESPONSE_SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();

			returnMessage.setStatus(Constants.USER_AJAX_RESPONSE_ERROR);
		}

		return returnMessage;
	}

}

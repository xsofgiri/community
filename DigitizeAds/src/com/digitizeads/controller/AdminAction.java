package com.digitizeads.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.digitizeads.bean.AdminBean;
import com.digitizeads.bean.CategoryBean;
import com.digitizeads.modal.Advertiser;
import com.digitizeads.modal.AdvertiserGallery;
import com.digitizeads.modal.Category;
import com.digitizeads.modal.Coupon;
import com.digitizeads.modal.SubDomain;
import com.digitizeads.modal.User;
import com.digitizeads.util.CipherUtils;
import com.digitizeads.util.Constants;
import com.digitizeads.util.PageHandler;
import com.digitizeads.util.UploadFile;

@Controller
@RequestMapping("/admin")
public class AdminAction {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView displayAdminHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			model = new ModelAndView("redirect:/admin/coupon/viewall");
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayAdminLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			model = new ModelAndView("redirect:/admin/coupon/viewall");
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeAdminLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = null;
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String ipAddress = request.getRemoteAddr();

			User user = adminBean.validateUserLogin(username, password, ipAddress, 2);

			if (user != null && user.getUserId() > 0 && user.getStatus() == 0) {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Inactive status");
				model = new ModelAndView("/admin/login");

			} else if (user != null && user.getUserId() > 0) {
				System.out.println("Status active for user : " + user.getUserId());

				session.setAttribute(Constants.ADMIN_DTO, user);

				model = new ModelAndView("redirect:/admin/coupon/viewall");

			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Invalid login details.");

				request.setAttribute(Constants.ADMIN_LOGIN_INVALID_EMAIL, username);
				System.out.println("username : " + username);

				model = new ModelAndView("/admin/login");
			}

		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}

		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView displayLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {

			User user = session.getAttribute(Constants.ADMIN_DTO) != null
					? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
			if (user != null && user.getUserId() > 0) {
				ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

				AdminBean adminBean = (AdminBean) context.getBean("adminBean");

				// logout the session for the user.
				adminBean.updateLogoutDetails(user);

				session.removeAttribute(Constants.ADMIN_DTO);
				session.invalidate();
			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Something went wrong, please contact us.");
			}

		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}

		ModelAndView model = new ModelAndView("redirect:/admin");
		return model;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView displayAdminDashboard(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			model = new ModelAndView("redirect:/admin/coupon/viewall");
		}

		return model;
	}

	@RequestMapping(value = "/business/add", method = RequestMethod.GET)
	public ModelAndView displayAddBusiness(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");
			
			ArrayList<Category> categoryList = categoryBean.getAllCategories();
			request.setAttribute(Constants.CATEGORY_LIST, categoryList);

			ArrayList<SubDomain> subDomainList = adminBean.getSubDomainList();
			request.setAttribute(Constants.SUBDOMAIN_LIST, subDomainList);
			
			model = new ModelAndView("/admin/addBusiness");
		}

		return model;
	}

	@RequestMapping(value = "/business/add", method = RequestMethod.POST)
	public ModelAndView executeAddBusiness(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String name = request.getParameter("name");
			String logo = request.getParameter("logo");
			String categoryId = request.getParameter("categoryId");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			String website = request.getParameter("website");
			String aboutUs = request.getParameter("aboutUs");
			String subDomainId = request.getParameter("subDomainId");

			String fbLink = request.getParameter("fbLink");
			String instagramHandleName = request.getParameter("instaLink");
			String twitterLink = request.getParameter("twitterLink");

			String isFeatured = request.getParameter("isFeatured");

			Advertiser advertiser = new Advertiser();
			advertiser.setName(name);
			advertiser.setLogo(logo);
			advertiser.setCategory(new Category(Integer.parseInt(categoryId)));
			advertiser.setLatitude(latitude);
			advertiser.setLongitude(longitude);
			advertiser.setAddress(address);
			advertiser.setPhoneNumber(phoneNumber);
			advertiser.setEmail(email);
			advertiser.setWebsite(website);
			advertiser.setAboutUs(aboutUs);
			advertiser.setFeatured(isFeatured != null ? true : false);
			advertiser.setUrlSlug(Constants.toSlug(name));
			advertiser.setFbLink(fbLink);
			advertiser.setTwitterLink(twitterLink);
			advertiser.setInstagramHandleName(instagramHandleName);
			advertiser.setSubDomain(adminBean.getSubDomainDetails(Integer.parseInt(subDomainId)));
			
			if (!file.isEmpty()) {
				try {
					UploadFile s3Folder = new UploadFile();

					File convFile = new File(file.getOriginalFilename());
					System.out.println("file : " + file.getOriginalFilename());

					convFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();

					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

					s3Folder.addBusinessImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

					advertiser.setLogo(destinationFile);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			}

			int result = adminBean.addBusinessDetails(advertiser);

			if (result > 0) {
				request.setAttribute(Constants.ADMIN_SUCCESS_MESSAGE, "Business Details added.");
			} else if (result == -2) {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE,
						"Business Details already exist with the same name.");
			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Error while adding Business Details.");
			}

			ArrayList<Category> categoryList = categoryBean.getAllCategories();
			request.setAttribute(Constants.CATEGORY_LIST, categoryList);

			ArrayList<SubDomain> subDomainList = adminBean.getSubDomainList();
			request.setAttribute(Constants.SUBDOMAIN_LIST, subDomainList);
			
			model = new ModelAndView("/admin/addBusiness");
		}

		return model;
	}

	@RequestMapping(value = "/business/viewall", method = RequestMethod.GET)
	public ModelAndView displayViewAllBusiness(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");
		String contextPath = request.getContextPath();

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			PageHandler pageHandler = new PageHandler();

			if (pageHandler.getSurl() == null) {
				pageHandler.setCurrentPage(1);
				pageHandler.setLimit(200);
				pageHandler.setOffset(0);
				pageHandler.setReqPage(1);
				pageHandler.setSurl(contextPath + "/admin/business/viewall");
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

			ArrayList<Advertiser> advertiserList = adminBean.getAdvertiserList(pageHandler, whrCondition);
			request.setAttribute(Constants.ADMIN_ADVERTISER_LIST, advertiserList);

			model = new ModelAndView("/admin/viewAllBusiness");
		}

		return model;
	}

	@RequestMapping(value = "/business/edit/{advertiser_id}", method = RequestMethod.GET)
	public ModelAndView displayBusinessDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("advertiser_id") String advertiserId) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			advertiserId = CipherUtils.decryptId(advertiserId);

			Advertiser advertiserDetails = adminBean.getAdvertiserDetails(Integer.parseInt(advertiserId));
			request.setAttribute(Constants.ADMIN_ADVERTISER_DETAIL, advertiserDetails);

			ArrayList<Category> categoryList = categoryBean.getAllCategories();
			request.setAttribute(Constants.CATEGORY_LIST, categoryList);

			model = new ModelAndView("/admin/editBusiness");
		}

		return model;
	}

	@RequestMapping(value = "/business/gallery/{advertiser_id}", method = RequestMethod.GET)
	public ModelAndView displayBusinessGalleryDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("advertiser_id") String advertiserId) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			advertiserId = CipherUtils.decryptId(advertiserId);

			Advertiser advertiserDetails = adminBean.getAdvertiserDetails(Integer.parseInt(advertiserId));
			request.setAttribute(Constants.ADMIN_ADVERTISER_DETAIL, advertiserDetails);

			ArrayList<AdvertiserGallery> advertiserGalleryList = adminBean.getBusinessGalleryList(advertiserDetails);
			request.setAttribute(Constants.ADVERTISER_GALLERY_LIST, advertiserGalleryList);

			model = new ModelAndView("/admin/businessGallery");
		}

		return model;
	}

	@RequestMapping(value = "/business/gallery/{advertiser_id}", method = RequestMethod.POST)
	public ModelAndView executeBusinessGalleryDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("advertiser_id") String advertiserId, @RequestParam("gImages") MultipartFile[] files) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			advertiserId = CipherUtils.decryptId(advertiserId);

			Advertiser advertiserDetails = adminBean.getAdvertiserDetails(Integer.parseInt(advertiserId));
			request.setAttribute(Constants.ADMIN_ADVERTISER_DETAIL, advertiserDetails);

			System.out.println("files : " + files.length);

			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					try {
						UploadFile s3Folder = new UploadFile();

						File convFile = new File(file.getOriginalFilename());
						System.out.println("file : " + file.getOriginalFilename());

						convFile.createNewFile();
						FileOutputStream fos = new FileOutputStream(convFile);
						fos.write(file.getBytes());
						fos.close();

						String ext = file.getOriginalFilename().substring(
								file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

						String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

						s3Folder.addBusinessImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

						AdvertiserGallery advertiserGallery = new AdvertiserGallery();
						advertiserGallery.setImageName(destinationFile);
						advertiserGallery.setAdvertiser(advertiserDetails);

						int result = adminBean.addBusinessGallery(advertiserGallery);
						System.out.println("result : " + result);

					} catch (Exception e) {
						System.out.println("Exception : " + e);
					}
				}
			}

			ArrayList<AdvertiserGallery> advertiserGalleryList = adminBean.getBusinessGalleryList(advertiserDetails);
			request.setAttribute(Constants.ADVERTISER_GALLERY_LIST, advertiserGalleryList);

			model = new ModelAndView("/admin/businessGallery");
		}

		return model;
	}

	@RequestMapping(value = "/business/edit/{advertiser_id}", method = RequestMethod.POST)
	public ModelAndView editBusinessDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("advertiser_id") String advertiserId, @RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			advertiserId = CipherUtils.decryptId(advertiserId);

			String name = request.getParameter("name");
			String logo = request.getParameter("logo");
			String categoryId = request.getParameter("categoryId");
			String latitude = request.getParameter("latitude");
			String longitude = request.getParameter("longitude");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String email = request.getParameter("email");
			String website = request.getParameter("website");
			String aboutUs = request.getParameter("aboutUs");
			String exImg = request.getParameter("exImg");

			String fbLink = request.getParameter("fbLink");
			String instagramHandleName = request.getParameter("instaLink");
			String twitterLink = request.getParameter("twitterLink");

			String isFeatured = request.getParameter("isFeatured");

			System.out.println("isFeatured : " + isFeatured);

			Advertiser advertiser = new Advertiser();
			advertiser.setName(name);
			advertiser.setLogo(logo);
			advertiser.setCategory(new Category(Integer.parseInt(categoryId)));
			advertiser.setLatitude(latitude);
			advertiser.setLongitude(longitude);
			advertiser.setAddress(address);
			advertiser.setPhoneNumber(phoneNumber);
			advertiser.setEmail(email);
			advertiser.setWebsite(website);
			advertiser.setAboutUs(aboutUs);
			advertiser.setFeatured(isFeatured != null ? true : false);

			advertiser.setFbLink(fbLink);
			advertiser.setTwitterLink(twitterLink);
			advertiser.setInstagramHandleName(instagramHandleName);
			advertiser.setAdvertiserId(Integer.parseInt(advertiserId));

			if (!file.isEmpty()) {
				try {
					UploadFile s3Folder = new UploadFile();

					File convFile = new File(file.getOriginalFilename());
					System.out.println("file : " + file.getOriginalFilename());

					convFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();

					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

					s3Folder.addBusinessImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

					advertiser.setLogo(destinationFile);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			} else {
				advertiser.setLogo(exImg);
			}

			int result = adminBean.editBusinessDetails(advertiser);

			if (result > 0) {
				request.setAttribute(Constants.ADMIN_SUCCESS_MESSAGE, "Business Details edited.");
			} else if (result == -2) {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE,
						"Business Details already exist with the same name.");
			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Error while editing Business Details.");
			}

			Advertiser advertiserDetails = adminBean.getAdvertiserDetails(Integer.parseInt(advertiserId));
			request.setAttribute(Constants.ADMIN_ADVERTISER_DETAIL, advertiserDetails);

			ArrayList<Category> categoryList = categoryBean.getAllCategories();
			request.setAttribute(Constants.CATEGORY_LIST, categoryList);

			model = new ModelAndView("/admin/editBusiness");
		}

		return model;
	}

	@RequestMapping(value = "/coupon/add", method = RequestMethod.GET)
	public ModelAndView displayAddCoupon(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			ArrayList<Advertiser> advertiserList = adminBean.getAllAdvertiserList();
			request.setAttribute(Constants.ALL_ADVERTISER_LIST, advertiserList);

			model = new ModelAndView("/admin/addCoupon");
		}

		return model;
	}

	@RequestMapping(value = "/coupon/add", method = RequestMethod.POST)
	public ModelAndView executeAddCoupon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String title = request.getParameter("title");
			String businessId = request.getParameter("businessId");
			String content = request.getParameter("couponText");
			String validFrom = request.getParameter("validFrom");
			String validTo = request.getParameter("validTo");
			String isFeatured = request.getParameter("isFeatured");

			Advertiser advertiser = new Advertiser();
			advertiser.setAdvertiserId(Integer.parseInt(businessId));

			Coupon coupon = new Coupon();
			coupon.setTitle(title);
			coupon.setAdvertiser(advertiser);
			coupon.setContent(content);
			coupon.setValidFrom(validFrom);
			coupon.setValidTo(validTo);
			coupon.setFeatured(isFeatured != null && "1".equals(isFeatured) ? true : false);

			if (!file.isEmpty()) {
				try {
					UploadFile s3Folder = new UploadFile();

					File convFile = new File(file.getOriginalFilename());
					System.out.println("file : " + file.getOriginalFilename());

					convFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();

					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

					s3Folder.addUserCouponImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

					coupon.setSourceLink(destinationFile);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			}

			int result = adminBean.addCouponDetails(coupon);

			if (result > 0) {
				request.setAttribute(Constants.ADMIN_SUCCESS_MESSAGE, "Coupon Details added.");
			} else if (result == -2) {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "coupon Details already exist with the same name.");
			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Error while adding coupon Details.");
			}

			ArrayList<Advertiser> advertiserList = adminBean.getAllAdvertiserList();
			request.setAttribute(Constants.ALL_ADVERTISER_LIST, advertiserList);

			model = new ModelAndView("/admin/addCoupon");
		}

		return model;
	}

	@RequestMapping(value = "/coupon/viewall", method = RequestMethod.GET)
	public ModelAndView displayViewAllCoupon(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");
		String contextPath = request.getContextPath();

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			PageHandler pageHandler = new PageHandler();

			if (pageHandler.getSurl() == null) {
				pageHandler.setCurrentPage(1);
				pageHandler.setLimit(200);
				pageHandler.setOffset(0);
				pageHandler.setReqPage(1);
				pageHandler.setSurl(contextPath + "/admin/coupon/viewall");
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

			ArrayList<Coupon> couponList = adminBean.getAdvertiserCouponList(pageHandler, whrCondition);
			request.setAttribute(Constants.ADMIN_COUPON_LIST, couponList);

			model = new ModelAndView("/admin/viewAllCoupon");
		}

		return model;
	}

	@RequestMapping(value = "/coupon/edit/{coupon_id}", method = RequestMethod.GET)
	public ModelAndView displayCouponDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("coupon_id") String couponId) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			couponId = CipherUtils.decryptId(couponId);

			Coupon couponDetails = adminBean.getCouponDetails(Integer.parseInt(couponId));
			request.setAttribute(Constants.ADMIN_COUPON_DETAIL, couponDetails);

			ArrayList<Advertiser> advertiserList = adminBean.getAllAdvertiserList();
			request.setAttribute(Constants.ALL_ADVERTISER_LIST, advertiserList);

			model = new ModelAndView("/admin/editCoupon");
		}

		return model;
	}

	@RequestMapping(value = "/coupon/edit/{coupon_id}", method = RequestMethod.POST)
	public ModelAndView editCouponDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("coupon_id") String couponId, @RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			CategoryBean categoryBean = (CategoryBean) context.getBean("categoryBean");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			couponId = CipherUtils.decryptId(couponId);

			String title = request.getParameter("title");
			String businessId = request.getParameter("businessId");
			String content = request.getParameter("couponText");
			String validFrom = request.getParameter("validFrom");
			String validTo = request.getParameter("validTo");
			String isFeatured = request.getParameter("isFeatured");
			String exImg = request.getParameter("exImg");

			Advertiser advertiser = new Advertiser();
			advertiser.setAdvertiserId(Integer.parseInt(businessId));

			Coupon coupon = new Coupon();
			coupon.setTitle(title);
			coupon.setAdvertiser(advertiser);
			coupon.setContent(content);
			coupon.setValidFrom(validFrom);
			coupon.setValidTo(validTo);
			coupon.setFeatured(isFeatured != null && "1".equals(isFeatured) ? true : false);
			coupon.setCouponId(Integer.parseInt(couponId));

			if (!file.isEmpty()) {
				try {
					UploadFile s3Folder = new UploadFile();

					File convFile = new File(file.getOriginalFilename());
					System.out.println("file : " + file.getOriginalFilename());

					convFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();

					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

					s3Folder.addUserCouponImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

					coupon.setSourceLink(destinationFile);
				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			} else {
				coupon.setSourceLink(exImg);
			}

			int result = adminBean.editCouponDetails(coupon);

			if (result > 0) {
				request.setAttribute(Constants.ADMIN_SUCCESS_MESSAGE, "Coupon Details edited.");
			} else if (result == -2) {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Coupon Details already exist with the same name.");
			} else {
				request.setAttribute(Constants.ADMIN_ERROR_MESSAGE, "Error while editing Coupon Details.");
			}

			Coupon couponDetails = adminBean.getCouponDetails(Integer.parseInt(couponId));
			request.setAttribute(Constants.ADMIN_COUPON_DETAIL, couponDetails);

			ArrayList<Advertiser> advertiserList = adminBean.getAllAdvertiserList();
			request.setAttribute(Constants.ALL_ADVERTISER_LIST, advertiserList);

			model = new ModelAndView("/admin/editCoupon");
		}

		return model;
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.GET)
	public ModelAndView displayAddCategory(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			model = new ModelAndView("/admin/addCategory");
		}

		return model;
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public ModelAndView executeAddCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile file) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String name = request.getParameter("name");
			String icon = request.getParameter("icon");

			Category category = new Category();
			category.setIcon(icon);
			category.setName(name);

			if (!file.isEmpty()) {
				try {
					UploadFile s3Folder = new UploadFile();

					File convFile = new File(file.getOriginalFilename());
					System.out.println("file : " + file.getOriginalFilename());

					convFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convFile);
					fos.write(file.getBytes());
					fos.close();

					String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
							file.getOriginalFilename().length());

					String destinationFile = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(64), ext);

					s3Folder.addCategoryImage(destinationFile, convFile, s3Folder.getAmazonS3Client());

					category.setImage(destinationFile);

				} catch (Exception e) {
					System.out.println("Exception : " + e);
				}
			}

			int result = adminBean.addCategoryDetails(category);

			model = new ModelAndView("/admin/addCategory");
		}

		return model;
	}

	@RequestMapping(value = "/category/viewall", method = RequestMethod.GET)
	public ModelAndView displayViewAllCategory(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ModelAndView model = new ModelAndView("/admin/login");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			ArrayList<Category> categoryList = adminBean.getAllCategories();
			request.setAttribute(Constants.CATEGORY_LIST, categoryList);

			model = new ModelAndView("/admin/viewAllCategory");
		}

		return model;
	}

	@RequestMapping(value = "/business/gallery/remove", method = RequestMethod.POST)
	public @ResponseBody int removeBusinessGalleryImage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String advertiserGalleryId = request.getParameter("advertiserGalleryId");
			String advertiserId = request.getParameter("advertiserId");

			System.out.println("advertiserGalleryId : " + advertiserGalleryId);
			System.out.println("advertiserId : " + advertiserId);

			int result = adminBean.removeBusinessGallery(Integer.parseInt(advertiserGalleryId),
					Integer.parseInt(advertiserId));

			return result;
		} else {
			return 0;
		}

	}
	
	
	
	@RequestMapping(value = "/business/remove", method = RequestMethod.POST)
	public @ResponseBody int removeBusinessDetails(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String advertiserId = request.getParameter("advertiserId");

			System.out.println("advertiserId : " + advertiserId);

			int result = adminBean.removeBusiness(Integer.parseInt(advertiserId));

			return result;
		} else {
			return 0;
		}

	}
	
	
	@RequestMapping(value = "/business/coupon/remove", method = RequestMethod.POST)
	public @ResponseBody int removeBusinessCouponDetails(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

		User admin = session.getAttribute(Constants.ADMIN_DTO) != null
				? (User) session.getAttribute(Constants.ADMIN_DTO) : null;
		if (admin != null && admin.getUserId() > 0) {
			AdminBean adminBean = (AdminBean) context.getBean("adminBean");

			String advertiserId = request.getParameter("advertiserId");
			String couponId = request.getParameter("couponId");

			System.out.println("couponId : " + couponId);

			int result = adminBean.removeBusinessCoupon(Integer.parseInt(couponId));

			return result;
		} else {
			return 0;
		}

	}
}

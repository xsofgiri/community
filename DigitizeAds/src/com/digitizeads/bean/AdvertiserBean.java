package com.digitizeads.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.digitizeads.modal.Advertiser;
import com.digitizeads.modal.AdvertiserGallery;
import com.digitizeads.modal.AdvertiserService;
import com.digitizeads.modal.AdvertiserTrack;
import com.digitizeads.modal.Category;
import com.digitizeads.modal.Coupon;
import com.digitizeads.modal.SubDomain;
import com.digitizeads.modal.User;
import com.digitizeads.util.CalendarUtil;
import com.digitizeads.util.DBUtil;
import com.digitizeads.util.PageHandler;

public class AdvertiserBean {

	private DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<Advertiser> getFeaturedAdvertiser(SubDomain userSubDomain) {
		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON, TC.IMAGE AS CAT_IMAGE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 AND SUBDOMAIN_ID=? ORDER BY  TA.IS_FEATURED DESC, TA.NAME LIMIT 6";
		try {

			Object[] params = new Object[] { userSubDomain.getSubDomainId() };
			
			advertiserList = (ArrayList<Advertiser>) jdbcTemplate.query(query, params, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setLogo(rs.getString("LOGO"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("CAT_NAME"));
					category.setIcon(rs.getString("CAT_ICON"));
					category.setImage(rs.getString("CAT_IMAGE"));

					advertiser.setCategory(category);

					advertiser.setLatitude(rs.getString("LATITUDE"));
					advertiser.setLongitude(rs.getString("LONGITUDE"));
					advertiser.setAddress(rs.getString("ADDRESS"));
					advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
					advertiser.setEmail(rs.getString("EMAIL"));
					advertiser.setWebsite(rs.getString("WEBSITE"));
					advertiser.setAboutUs(rs.getString("ABOUT_US"));
					return advertiser;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserList;
	}
	
	
	
	public ArrayList<Advertiser> getTrendingAdvertiser(SubDomain userSubDomain) {
		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON, TC.IMAGE AS CAT_IMAGE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 AND SUBDOMAIN_ID=? ORDER BY  TA.CREATED_ON DESC, TA.NAME LIMIT 6";
		try {
			Object[] params = new Object[] { userSubDomain.getSubDomainId() };
			
			advertiserList = (ArrayList<Advertiser>) jdbcTemplate.query(query, params, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setLogo(rs.getString("LOGO"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					
					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("CAT_NAME"));
					category.setIcon(rs.getString("CAT_ICON"));
					category.setImage(rs.getString("CAT_IMAGE"));

					advertiser.setCategory(category);

					advertiser.setLatitude(rs.getString("LATITUDE"));
					advertiser.setLongitude(rs.getString("LONGITUDE"));
					advertiser.setAddress(rs.getString("ADDRESS"));
					advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
					advertiser.setEmail(rs.getString("EMAIL"));
					advertiser.setWebsite(rs.getString("WEBSITE"));
					advertiser.setAboutUs(rs.getString("ABOUT_US"));
					return advertiser;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserList;
	}
	
	
	public ArrayList<Advertiser> getAllAdvertiserList(SubDomain userSubDomain, int limit) {
		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON, TC.IMAGE AS CAT_IMAGE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 AND SUBDOMAIN_ID=? ORDER BY  TA.CREATED_ON DESC, TA.NAME LIMIT "+limit;
		try {

			Object[] params = new Object[] { userSubDomain.getSubDomainId() };
			advertiserList = (ArrayList<Advertiser>) jdbcTemplate.query(query, params, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setLogo(rs.getString("LOGO"));

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("CAT_NAME"));
					category.setIcon(rs.getString("CAT_ICON"));
					category.setImage(rs.getString("CAT_IMAGE"));

					advertiser.setCategory(category);

					advertiser.setLatitude(rs.getString("LATITUDE"));
					advertiser.setLongitude(rs.getString("LONGITUDE"));
					advertiser.setAddress(rs.getString("ADDRESS"));
					advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
					advertiser.setEmail(rs.getString("EMAIL"));
					advertiser.setWebsite(rs.getString("WEBSITE"));
					advertiser.setAboutUs(rs.getString("ABOUT_US"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					return advertiser;

				} 
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserList;
	}
	

	public ArrayList<Advertiser> getAdvertiserList(PageHandler pageHandler, String whrCondition) {
		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON, TC.IMAGE AS CAT_IMAGE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 "+whrCondition+" ORDER BY TA.NAME";
		try {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

			if (pageHandler == null || pageHandler.getResultTotal() <= 0) {
				pageHandler.setResultTotal(0);
				pageHandler.setResultTotal(dbUtil.getCountOfRows(query));
			}
			if (pageHandler.getResultTotal() >= 1) {

				int totalPages = pageHandler.getResultTotal() / pageHandler.getLimit();
				int reminder = pageHandler.getResultTotal() % pageHandler.getLimit();
				if (reminder > 0) {
					totalPages = totalPages + 1;
				}
				pageHandler.setTotalPages(totalPages);

				query += " limit " + pageHandler.getLimit()
						+ (pageHandler.getOffset() > 0 ? " offset " + pageHandler.getOffset() : "");

				advertiserList = (ArrayList<Advertiser>) jdbcTemplate.query(query, new RowMapper<Advertiser>() {
					public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

						Advertiser advertiser = new Advertiser();
						advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
						advertiser.setName(rs.getString("NAME"));
						advertiser.setLogo(rs.getString("LOGO"));
						advertiser.setUrlSlug(rs.getString("URL_SLUG"));
						advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
						
						Category category = new Category();
						category.setCategoryId(rs.getInt("CATEGORY_ID"));
						category.setName(rs.getString("CAT_NAME"));
						category.setIcon(rs.getString("CAT_ICON"));
						category.setImage(rs.getString("CAT_IMAGE"));

						advertiser.setCategory(category);

						advertiser.setLatitude(rs.getString("LATITUDE"));
						advertiser.setLongitude(rs.getString("LONGITUDE"));
						advertiser.setAddress(rs.getString("ADDRESS"));
						advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
						advertiser.setEmail(rs.getString("EMAIL"));
						advertiser.setWebsite(rs.getString("WEBSITE"));
						advertiser.setAboutUs(rs.getString("ABOUT_US"));
						return advertiser;

					}
				});
			}
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserList;
	}
	
	
	public Advertiser getAdvertiserDetails(int advertiserId) {
		Advertiser advertiserDetails = null;

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON,"
				+ " TC.IMAGE AS CAT_IMAGE, TA.FB_LINK, TA.TWITTER_LINK, TA.INSTAGRAM_HANDLE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 AND TA.ADVERTISER_ID=?";
		try {

			Object[] params = new Object[] { advertiserId };
			
			advertiserDetails = (Advertiser) jdbcTemplate.queryForObject(query, params, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setLogo(rs.getString("LOGO"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("CAT_NAME"));
					category.setIcon(rs.getString("CAT_ICON"));
					category.setImage(rs.getString("CAT_IMAGE"));

					advertiser.setCategory(category);

					advertiser.setLatitude(rs.getString("LATITUDE"));
					advertiser.setLongitude(rs.getString("LONGITUDE"));
					advertiser.setAddress(rs.getString("ADDRESS"));
					advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
					advertiser.setEmail(rs.getString("EMAIL"));
					advertiser.setWebsite(rs.getString("WEBSITE"));
					advertiser.setAboutUs(rs.getString("ABOUT_US"));
					
					advertiser.setFbLink(rs.getString("FB_LINK"));
					advertiser.setTwitterLink(rs.getString("TWITTER_LINK"));
					advertiser.setInstagramHandleName(rs.getString("INSTAGRAM_HANDLE"));
					
					return advertiser;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserDetails;
	}
	
	
	public Advertiser getAdvertiserDetails(String advertiserSlug) {
		Advertiser advertiserDetails = null;

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TA.URL_SLUG, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON,"
				+ " TC.IMAGE AS CAT_IMAGE, TA.FB_LINK, TA.TWITTER_LINK, TA.INSTAGRAM_HANDLE FROM t_advertiser TA LEFT JOIN "
				+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 AND TA.URL_SLUG=?";
		try {

			Object[] params = new Object[] { advertiserSlug };
			
			advertiserDetails = (Advertiser) jdbcTemplate.queryForObject(query, params, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setLogo(rs.getString("LOGO"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("CAT_NAME"));
					category.setIcon(rs.getString("CAT_ICON"));
					category.setImage(rs.getString("CAT_IMAGE"));

					advertiser.setCategory(category);

					advertiser.setLatitude(rs.getString("LATITUDE"));
					advertiser.setLongitude(rs.getString("LONGITUDE"));
					advertiser.setAddress(rs.getString("ADDRESS"));
					advertiser.setPhoneNumber(rs.getString("PHONE_NO"));
					advertiser.setEmail(rs.getString("EMAIL"));
					advertiser.setWebsite(rs.getString("WEBSITE"));
					advertiser.setAboutUs(rs.getString("ABOUT_US"));
					
					advertiser.setFbLink(rs.getString("FB_LINK"));
					advertiser.setTwitterLink(rs.getString("TWITTER_LINK"));
					advertiser.setInstagramHandleName(rs.getString("INSTAGRAM_HANDLE"));
					
					return advertiser;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserDetails;
	}
	
	
	
	public ArrayList<AdvertiserGallery> getBusinessGalleryList(Advertiser advertiser) {

		ArrayList<AdvertiserGallery> advertiserGalleryList = new ArrayList<AdvertiserGallery>();

		String query = "SELECT ADVERTISER_GALLERY_ID, ADVERTISER_ID, IMAGE_NAME, STATUS, CREATED_ON FROM t_advertiser_gallery WHERE"
				+ " ADVERTISER_ID=? AND STATUS=1 ORDER BY CREATED_ON DESC";
		try {

			Object[] params = new Object[] { advertiser.getAdvertiserId() };
			
			advertiserGalleryList = (ArrayList<AdvertiserGallery>) jdbcTemplate.query(query, params,
					new RowMapper<AdvertiserGallery>() {
						public AdvertiserGallery mapRow(ResultSet rs, int rowNum) throws SQLException {

							AdvertiserGallery advertiserGallery = new AdvertiserGallery();
							advertiserGallery.setAdvertiserGalleryId(rs.getInt("ADVERTISER_GALLERY_ID"));
							advertiserGallery.setImageName(rs.getString("IMAGE_NAME"));
							return advertiserGallery;

						}
					});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserGalleryList;

	}
	
	
	public ArrayList<AdvertiserService> getBusinessServiceList(Advertiser advertiser) {

		ArrayList<AdvertiserService> advertiserServiceList = new ArrayList<AdvertiserService>();

		String query = "SELECT ADVERTISER_SERVICE_ID, ADVERTISER_ID, TITLE, DESCRIPTION, IMAGE_SOURCE, STATUS, CREATED_ON, ORDER_NO FROM t_advertiser_service WHERE"
				+ " ADVERTISER_ID=? AND STATUS=1 ORDER BY CREATED_ON DESC";
		try {

			Object[] params = new Object[] { advertiser.getAdvertiserId() };
			
			advertiserServiceList = (ArrayList<AdvertiserService>) jdbcTemplate.query(query, params,
					new RowMapper<AdvertiserService>() {
						public AdvertiserService mapRow(ResultSet rs, int rowNum) throws SQLException {

							AdvertiserService advertiserService = new AdvertiserService();
							advertiserService.setAdvertiserServiceId(rs.getInt("ADVERTISER_SERVICE_ID"));
							advertiserService.setTitle(rs.getString("TITLE"));
							advertiserService.setDescription(rs.getString("DESCRIPTION"));
							advertiserService.setImageName(rs.getString("IMAGE_SOURCE"));
							advertiserService.setOrderNo(rs.getInt("ORDER_NO"));
							
							return advertiserService;

						}
					});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return advertiserServiceList;

	}

	
	public ArrayList<Coupon> getFeaturedCouponList() {
		ArrayList<Coupon> couponList = new ArrayList<Coupon>();
		final CalendarUtil calendarUtil = new CalendarUtil();
		
		String query = "SELECT TC.COUPON_ID, TC.TITLE, TC.ADVERTISER_ID, TC.SOURCE_LINK, TC.CONTENT, TC.VALID_FROM, TC.VALID_TO, TC.STATUS, TC.IS_FEATURED,"
				+ " TC.CREATED_ON, TA.NAME, TA.URL_SLUG, datediff(TC.VALID_TO, TC.VALID_FROM) AS VALIDITY FROM t_coupon TC LEFT JOIN t_advertiser TA  ON TA.ADVERTISER_ID=TC.ADVERTISER_ID WHERE"
				+ " TC.STATUS=1 AND (VALID_FROM<=DATE(NOW()) OR VALID_TO<=DATE(NOW())) AND DATE(NOW())<=VALID_TO ORDER BY TC.VALID_FROM";
		try { 
 
			
			couponList = (ArrayList<Coupon>) jdbcTemplate.query(query, new RowMapper<Coupon>() {
				public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {

					Coupon coupon = new Coupon();
					coupon.setCouponId(rs.getInt("COUPON_ID"));
					coupon.setTitle(rs.getString("TITLE"));

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					
					coupon.setAdvertiser(advertiser);

					coupon.setContent(rs.getString("CONTENT"));
					coupon.setFeatured(rs.getBoolean("IS_FEATURED"));

					coupon.setCreatedOn(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("CREATED_ON")), "dd MMM, yyyy"));
					coupon.setSourceLink(rs.getString("SOURCE_LINK"));

					coupon.setValidFrom(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("VALID_FROM")), "dd MMM, yyyy"));
					coupon.setValidTo(calendarUtil
							.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("VALID_TO")), "dd MMM, yyyy"));
					
					coupon.setValidity(rs.getInt("VALIDITY"));
					return coupon;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return couponList;
	}
	
	
	public Coupon getCouponDetails(int couponId) {
		Coupon couponDetails = null;
		final CalendarUtil calendarUtil = new CalendarUtil();
		
		String query = "SELECT TC.COUPON_ID, TC.TITLE, TC.ADVERTISER_ID, TC.SOURCE_LINK, TC.CONTENT, TC.VALID_FROM, TC.VALID_TO, TC.STATUS, TC.IS_FEATURED,"
				+ " TC.CREATED_ON, TA.NAME, TA.URL_SLUG, datediff(TC.VALID_TO, TC.VALID_FROM) AS VALIDITY FROM t_coupon TC LEFT JOIN t_advertiser TA  ON TA.ADVERTISER_ID=TC.ADVERTISER_ID WHERE"
				+ " TC.STATUS=1 AND TC.COUPON_ID=?";
		try { 
 
			Object[] params = new Object[] { couponId };
			couponDetails = (Coupon) jdbcTemplate.queryForObject(query, params, new RowMapper<Coupon>() {
				public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {

					Coupon coupon = new Coupon();
					coupon.setCouponId(rs.getInt("COUPON_ID"));
					coupon.setTitle(rs.getString("TITLE"));

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					coupon.setAdvertiser(advertiser);

					coupon.setContent(rs.getString("CONTENT"));
					coupon.setFeatured(rs.getBoolean("IS_FEATURED"));

					coupon.setCreatedOn(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("CREATED_ON")), "dd MMM, yyyy"));
					coupon.setSourceLink(rs.getString("SOURCE_LINK"));

					coupon.setValidFrom(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("VALID_FROM")), "dd MMM, yyyy"));
					coupon.setValidTo(calendarUtil
							.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("VALID_TO")), "dd MMM, yyyy"));
					
					coupon.setValidity(rs.getInt("VALIDITY"));
					return coupon;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return couponDetails;
	}
	
	
	
	public ArrayList<Coupon> getActiveBusinessCouponList(Advertiser advertiser) {
		ArrayList<Coupon> couponList = new ArrayList<Coupon>();
		final CalendarUtil calendarUtil = new CalendarUtil();
		
		String query = "SELECT TC.COUPON_ID, TC.TITLE, TC.ADVERTISER_ID, TC.SOURCE_LINK, TC.CONTENT, TC.VALID_FROM, TC.VALID_TO, TC.STATUS, TC.IS_FEATURED,"
				+ " TC.CREATED_ON, TA.NAME, TA.URL_SLUG, datediff(TC.VALID_TO, TC.VALID_FROM) AS VALIDITY FROM t_coupon TC LEFT JOIN t_advertiser TA  ON TA.ADVERTISER_ID=TC.ADVERTISER_ID WHERE"
				+ " TC.STATUS=1 AND (VALID_FROM>=DATE(NOW()) OR VALID_TO<=DATE(NOW())) AND DATE(NOW())<=VALID_TO AND TC.ADVERTISER_ID=? ORDER BY TC.VALID_FROM";
		try { 
 
			Object[] params = new Object[] { advertiser.getAdvertiserId() };
			couponList = (ArrayList<Coupon>) jdbcTemplate.query(query, params, new RowMapper<Coupon>() {
				public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {

					Coupon coupon = new Coupon();
					coupon.setCouponId(rs.getInt("COUPON_ID"));
					coupon.setTitle(rs.getString("TITLE"));

					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));
					advertiser.setUrlSlug(rs.getString("URL_SLUG"));
					advertiser.setFeaturedImage(getFeaturedBusinessSingleImage(rs.getInt("ADVERTISER_ID")));
					coupon.setAdvertiser(advertiser);

					coupon.setContent(rs.getString("CONTENT"));
					coupon.setFeatured(rs.getBoolean("IS_FEATURED"));

					coupon.setCreatedOn(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("CREATED_ON")), "dd MMM, yyyy"));
					coupon.setSourceLink(rs.getString("SOURCE_LINK"));

					coupon.setValidFrom(calendarUtil.formatDate(
							calendarUtil.convertDateSqlToJava(rs.getDate("VALID_FROM")), "dd MMM, yyyy"));
					coupon.setValidTo(calendarUtil
							.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("VALID_TO")), "dd MMM, yyyy"));
					
					coupon.setValidity(rs.getInt("VALIDITY"));
					return coupon;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return couponList;
	}

	
	public String getFeaturedBusinessSingleImage(int advertiserId) {

		String featuredImageURL = null;

		String query = "SELECT IMAGE_NAME FROM t_advertiser_gallery WHERE ADVERTISER_ID=? AND STATUS=1 ORDER BY rand() LIMIT 1";
		try {

			Object[] params = new Object[] { advertiserId };

			featuredImageURL = (String) jdbcTemplate.queryForObject(query, params, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {

					return rs.getString("IMAGE_NAME");

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return featuredImageURL;
	
	}
	
	public SubDomain findSubDomain(String subDomain) {
		SubDomain userSubDomain = null;

		String query = "SELECT SUBDOMAIN_ID, NAME, LOGO_URL, SUBDOMAIN_URL, FOOTER_TEXT, HEADER_TEXT, STATUS, BANNER_IMG_URL FROM t_subdomain"
				+ " WHERE SUBDOMAIN_URL=? AND STATUS=1 LIMIT 1";
		try {

			Object[] params = new Object[] { subDomain };

			userSubDomain = (SubDomain) jdbcTemplate.queryForObject(query, params, new RowMapper<SubDomain>() {
				public SubDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
					SubDomain subDomain = new SubDomain();
					subDomain.setSubDomainId(rs.getInt("SUBDOMAIN_ID"));
					subDomain.setName(rs.getString("NAME"));
					subDomain.setLogoUrl(rs.getString("LOGO_URL"));
					subDomain.setSubdomainURL(rs.getString("SUBDOMAIN_URL"));
					subDomain.setFooterText(rs.getString("FOOTER_TEXT"));
					subDomain.setHeaderText(rs.getString("HEADER_TEXT"));
					subDomain.setBannerImageURL(rs.getString("BANNER_IMG_URL"));
					return subDomain;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return userSubDomain;
	}
	
	
	
	
	
	public int updateAdvertiserView(String ipAddress, int advertiserId) {
		String INSERT_USER_SESSION = "INSERT INTO t_track_advertiser_view (ADVERTISER_ID, TRACKED_ON, IP_ADDRESS, STATUS) VALUES (?, ?, ?, 1)";
		CalendarUtil calendarUtil = new CalendarUtil();
		
		Object[] params = new Object[] { advertiserId, calendarUtil.getISTCurrentDateTime() ,ipAddress };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);

	}
	
	
	
	public int updateAdvertiserClick(AdvertiserTrack advertiserTrack) {
		String INSERT_USER_SESSION = "INSERT INTO t_track_advertiser_click (ADVERTISER_ID, SOURCE_ID, SOURCE_TYPE, TRACKED_ON,"
				+ " IP_ADDRESS, STATUS) VALUES (?, ?, ?, ?, ?, 1)";
		CalendarUtil calendarUtil = new CalendarUtil();
		
		Object[] params = new Object[] { advertiserTrack.getAdvertiser().getAdvertiserId(), advertiserTrack.getSourceId(), 
				advertiserTrack.getSourceType(), calendarUtil.getISTCurrentDateTime() ,advertiserTrack.getIpAddress() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);

	}
	
}

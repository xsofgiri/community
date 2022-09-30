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
import com.digitizeads.modal.Category;
import com.digitizeads.modal.Coupon;
import com.digitizeads.modal.Role;
import com.digitizeads.modal.SubDomain;
import com.digitizeads.modal.User;
import com.digitizeads.util.CalendarUtil;
import com.digitizeads.util.CipherUtils;
import com.digitizeads.util.DBUtil;
import com.digitizeads.util.PageHandler;
import com.digitizeads.util.Password;

public class AdminBean {

	private DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User validateUserLogin(String username, final String password, String ipAddress, int roleId) {

		User user = null;

		try {

			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

			int userId = 0;
			boolean isValid = false;

			ArrayList customReturn = new ArrayList();
			username = CipherUtils.encryptPhone(username);

			String query = "SELECT USER_ID, PASSWORD FROM t_user TU WHERE USERNAME=? AND ROLE_ID=? LIMIT 1";
			Object[] conditionObject = new Object[] { username, roleId };

			customReturn = jdbcTemplate.queryForObject(query, conditionObject, new RowMapper<ArrayList>() {
				public ArrayList mapRow(ResultSet rs, int rowNum) throws SQLException {
					ArrayList customReturn = new ArrayList();

					customReturn.add(rs.getInt("USER_ID"));
					String passwordHash = rs.getString("PASSWORD");

					customReturn.add(Password.checkPassword(password, passwordHash));

					return customReturn;
				}
			});

			userId = customReturn != null && customReturn.size() > 0 ? (Integer) customReturn.get(0) : 0;
			isValid = customReturn != null && customReturn.size() > 0 ? (Boolean) customReturn.get(1) : false;

			if (userId > 0 && isValid) {

				user = getUserDetails(userId, null, null, roleId);

				if (user != null && user.getStatus() == 1) {

					String INSERT_USER_SESSION = "INSERT INTO t_user_sessiondetails (USER_ID, SDATE, TIMEIN, IPADDRESS)"
							+ " VALUES (?, CURDATE(),CURTIME(), ?)";

					Object[] params = new Object[] { user.getUserId(), ipAddress };

					int result = jdbcTemplate.update(INSERT_USER_SESSION, params);

					// GET LATEST SESSION ID.

					String getLatestSessionId = "SELECT MAX(SESSIONID) FROM t_user_sessiondetails WHERE USER_ID=?";
					Object[] sessionParams = new Object[] { user.getUserId() };
					int userSessionId = dbUtil.getJDBCIntResult(getLatestSessionId, sessionParams);

					user.setSessionId(userSessionId);

				}

			}

			return user;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return user;
		} catch (Exception e) {
			System.out.println(e);
			return user;
		}

	}

	public User getUserDetails(int userId, String email, String phone, int roleId) {

		User user = null;

		String whrCondition = "";
		try {
			if (userId > 0)
				whrCondition = " AND USER_ID=" + userId;
			else if (email != null && !"".equals(email))
				whrCondition = " AND EMAIL = '" + CipherUtils.encryptEmail(email).trim() + "'";
			else if (phone != null && !"".equals(phone))
				whrCondition = " AND PHONE_NO = '" + CipherUtils.encryptPhone(phone).trim() + "'";
			else
				whrCondition = " AND FALSE";

			String query = "SELECT USER_ID, FULL_NAME, EMAIL, ROLE_ID, STATUS, PHONE_NO FROM t_user TU"
					+ " WHERE ROLE_ID=? " + whrCondition;

			Object[] params = new Object[] { roleId };

			System.out.println("query : " + query);

			user = jdbcTemplate.queryForObject(query, params, new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();

					user.setUserId(rs.getInt("USER_ID"));
					user.setEmail(CipherUtils.decryptEmail(rs.getString("EMAIL")));
					user.setFullName(rs.getString("FULL_NAME"));

					Role role = new Role();
					role.setRoleId(rs.getInt("ROLE_ID"));
					user.setRole(role);

					user.setStatus(rs.getInt("STATUS"));
					return user;
				}
			});

			return user;
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return user;
		} catch (Exception e) {
			System.out.println(e);
			return user;
		}

	}

	public int updateLogoutDetails(User user) {
		String INSERT_USER_SESSION = "UPDATE t_user_sessiondetails SET TIMEOUT=CURTIME() WHERE SESSIONID=? AND USER_ID=?";

		Object[] params = new Object[] { user.getSessionId(), user.getUserId() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);

	}

	public int verifyUserEmail(String email) {
		CalendarUtil calendarUtil = new CalendarUtil();

		String INSERT_USER_SESSION = "UPDATE t_user SET STATUS=1, ACTIVATED_ON=? WHERE EMAIL=? AND ROLE_ID=1";

		Object[] params = new Object[] { calendarUtil.getISTCurrentDateTime(), CipherUtils.encryptEmail(email) };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int resetPassword(String email, String pass) {

		try {
			String query = "UPDATE t_user SET PASSWORD=? WHERE EMAIL=?";

			String computedPassword = Password.hashPassword(pass);
			String encryptedEmail = CipherUtils.encryptEmail(email);

			Object[] params = new Object[] { computedPassword, encryptedEmail };

			return jdbcTemplate.update(query, params);
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
			return -1;
		} catch (Exception e) {
			System.out.println(e);
			return -1;
		}
	}

	public int addBusinessDetails(Advertiser advertiser) {
		CalendarUtil calendarUtil = new CalendarUtil();

		String query = "SELECT ADVERTISER_ID FROM t_advertiser TU WHERE NAME=? LIMIT 1";
		Object[] conditionObject = new Object[] { advertiser.getName() };

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

		int result = dbUtil.getJDBCIntResult(query, conditionObject);
		if (result > 0)
			return -2;

		String INSERT_USER_SESSION = "INSERT INTO  t_advertiser (NAME, LOGO, CATEGORY_ID, LATITUDE, LONGITUDE, ADDRESS, PHONE_NO,"
				+ " EMAIL, WEBSITE, ABOUT_US, STATUS, IS_FEATURED, CREATED_ON, FB_LINK, TWITTER_LINK, INSTAGRAM_HANDLE,"
				+ " SUBDOMAIN_ID, URL_SLUG)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?, ?, ?, ?, ?)";

		Object[] params = new Object[] { advertiser.getName(), advertiser.getLogo(),
				advertiser.getCategory().getCategoryId(), advertiser.getLatitude(), advertiser.getLongitude(),
				advertiser.getAddress(), advertiser.getPhoneNumber(), advertiser.getEmail(), advertiser.getWebsite(),
				advertiser.getAboutUs(), advertiser.isFeatured(), calendarUtil.getISTCurrentDateTime(),
				advertiser.getFbLink(), advertiser.getTwitterLink(), advertiser.getInstagramHandleName(),
				advertiser.getSubDomain().getSubDomainId(), advertiser.getUrlSlug() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int editBusinessDetails(Advertiser advertiser) {

		String query = "SELECT ADVERTISER_ID FROM t_advertiser TU WHERE NAME=? AND ADVERTISER_ID!=? LIMIT 1";
		Object[] conditionObject = new Object[] { advertiser.getName(), advertiser.getAdvertiserId() };

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

		int result = dbUtil.getJDBCIntResult(query, conditionObject);
		if (result > 0)
			return -2;

		String INSERT_USER_SESSION = "UPDATE t_advertiser SET NAME=?, LOGO=?, CATEGORY_ID=?, LATITUDE=?, LONGITUDE=?, ADDRESS=?, PHONE_NO=?,"
				+ " EMAIL=?, WEBSITE=?, ABOUT_US=?, IS_FEATURED=?, FB_LINK=?, TWITTER_LINK=?, INSTAGRAM_HANDLE=? WHERE ADVERTISER_ID=?";

		Object[] params = new Object[] { advertiser.getName(), advertiser.getLogo(),
				advertiser.getCategory().getCategoryId(), advertiser.getLatitude(), advertiser.getLongitude(),
				advertiser.getAddress(), advertiser.getPhoneNumber(), advertiser.getEmail(), advertiser.getWebsite(),
				advertiser.getAboutUs(), advertiser.isFeatured(), advertiser.getFbLink(), advertiser.getTwitterLink(),
				advertiser.getInstagramHandleName(), advertiser.getAdvertiserId() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public ArrayList<Advertiser> getAdvertiserList(PageHandler pageHandler, String whrCondition) {

		String query = "";

		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

			query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
					+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON, TC.IMAGE AS CAT_IMAGE FROM t_advertiser TA LEFT JOIN "
					+ "t_category TC ON TA.CATEGORY_ID=TC.CATEGORY_ID WHERE TA.STATUS=1 " + whrCondition
					+ " ORDER BY CREATED_ON DESC";

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
			return advertiserList;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return advertiserList;
		} catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return advertiserList;
		}

	}

	public Advertiser getAdvertiserDetails(int advertiserId) {
		Advertiser advertiserDetails = null;

		String query = "SELECT TA.ADVERTISER_ID, TA.NAME, TA.LOGO, TA.CATEGORY_ID, TA.LATITUDE, TA.LONGITUDE, TA.ADDRESS, TA.PHONE_NO, TA.EMAIL,"
				+ " TA.WEBSITE, TA.ABOUT_US, TA.STATUS, TA.IS_FEATURED, TA.CREATED_ON, TC.NAME AS CAT_NAME, TC.ICON AS CAT_ICON,"
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

					advertiser.setFeatured(rs.getBoolean("IS_FEATURED"));

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

	public ArrayList<Advertiser> getAllAdvertiserList() {

		String query = "";

		ArrayList<Advertiser> advertiserList = new ArrayList<Advertiser>();
		try {

			query = "SELECT TA.ADVERTISER_ID, TA.NAME FROM t_advertiser TA WHERE TA.STATUS=1 ORDER BY NAME";

			advertiserList = (ArrayList<Advertiser>) jdbcTemplate.query(query, new RowMapper<Advertiser>() {
				public Advertiser mapRow(ResultSet rs, int rowNum) throws SQLException {
					Advertiser advertiser = new Advertiser();
					advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
					advertiser.setName(rs.getString("NAME"));

					return advertiser;
				}
			});
			return advertiserList;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return advertiserList;
		} catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return advertiserList;
		}

	}

	public int addCouponDetails(Coupon coupon) {
		CalendarUtil calendarUtil = new CalendarUtil();

		String INSERT_USER_SESSION = "INSERT INTO  t_coupon (TITLE, ADVERTISER_ID, SOURCE_LINK, CONTENT, VALID_FROM, VALID_TO, STATUS,"
				+ " IS_FEATURED, CREATED_ON)" + " VALUES (?, ?, ?, ?, ?, ?, 1, ?, ?)";

		Object[] params = new Object[] { coupon.getTitle(), coupon.getAdvertiser().getAdvertiserId(),
				coupon.getSourceLink(), coupon.getContent(),
				calendarUtil.parseDate(coupon.getValidFrom(), "dd-MM-yyyy"),
				calendarUtil.parseDate(coupon.getValidTo(), "dd-MM-yyyy"), coupon.isFeatured(),
				calendarUtil.getISTCurrentDateTime() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int editCouponDetails(Coupon coupon) {

		CalendarUtil calendarUtil = new CalendarUtil();

		String INSERT_USER_SESSION = "UPDATE t_coupon SET TITLE=?, ADVERTISER_ID=?, SOURCE_LINK=?, CONTENT=?, VALID_FROM=?, VALID_TO=?, STATUS=1,"
				+ " IS_FEATURED=?  WHERE COUPON_ID=?";

		Object[] params = new Object[] { coupon.getTitle(), coupon.getAdvertiser().getAdvertiserId(),
				coupon.getSourceLink(), coupon.getContent(),
				calendarUtil.parseDate(coupon.getValidFrom(), "dd-MM-yyyy"),
				calendarUtil.parseDate(coupon.getValidTo(), "dd-MM-yyyy"), coupon.isFeatured(), coupon.getCouponId() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public ArrayList<Coupon> getAdvertiserCouponList(PageHandler pageHandler, String whrCondition) {

		String query = "";

		ArrayList<Coupon> couponList = new ArrayList<Coupon>();
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

			final CalendarUtil calendarUtil = new CalendarUtil();

			query = "SELECT TC.COUPON_ID, TC.TITLE, TC.ADVERTISER_ID, TC.SOURCE_LINK, TC.CONTENT, TC.VALID_FROM, TC.VALID_TO, TC.STATUS, TC.IS_FEATURED,"
					+ " TC.CREATED_ON, TA.NAME FROM t_coupon TC LEFT JOIN t_advertiser TA  ON TA.ADVERTISER_ID=TC.ADVERTISER_ID WHERE"
					+ " TC.STATUS=1 " + whrCondition + " ORDER BY TC.CREATED_ON DESC";

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

				couponList = (ArrayList<Coupon>) jdbcTemplate.query(query, new RowMapper<Coupon>() {
					public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
						Coupon coupon = new Coupon();
						coupon.setCouponId(rs.getInt("COUPON_ID"));
						coupon.setTitle(rs.getString("TITLE"));

						Advertiser advertiser = new Advertiser();
						advertiser.setAdvertiserId(rs.getInt("ADVERTISER_ID"));
						advertiser.setName(rs.getString("NAME"));
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
						return coupon;
					}
				});
			}
			return couponList;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return couponList;
		} catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return couponList;
		}

	}

	public Coupon getCouponDetails(int couponId) {
		Coupon couponDetails = null;
		final CalendarUtil calendarUtil = new CalendarUtil();

		String query = "SELECT TC.COUPON_ID, TC.TITLE, TC.ADVERTISER_ID, TC.SOURCE_LINK, TC.CONTENT, TC.VALID_FROM, TC.VALID_TO, TC.STATUS, TC.IS_FEATURED,"
				+ " TC.CREATED_ON, TA.NAME FROM t_coupon TC LEFT JOIN t_advertiser TA  ON TA.ADVERTISER_ID=TC.ADVERTISER_ID WHERE"
				+ " TC.COUPON_ID=?";
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
					coupon.setAdvertiser(advertiser);

					coupon.setContent(rs.getString("CONTENT"));
					coupon.setFeatured(rs.getBoolean("IS_FEATURED"));

					coupon.setCreatedOn(calendarUtil
							.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("CREATED_ON")), "dd-MM-yyyy"));
					coupon.setSourceLink(rs.getString("SOURCE_LINK"));

					coupon.setValidFrom(calendarUtil
							.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("VALID_FROM")), "dd-MM-yyyy"));
					coupon.setValidTo(calendarUtil.formatDate(calendarUtil.convertDateSqlToJava(rs.getDate("VALID_TO")),
							"dd-MM-yyyy"));
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

	public int addCategoryDetails(Category category) {

		String query = "SELECT CATEGORY_ID FROM t_category TU WHERE NAME=? LIMIT 1";
		Object[] conditionObject = new Object[] { category.getName() };

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

		int result = dbUtil.getJDBCIntResult(query, conditionObject);
		if (result > 0)
			return -2;

		String INSERT_USER_SESSION = "INSERT INTO  t_category (NAME, ICON, IMAGE, STATUS)" + " VALUES (?, ?, ?, ?)";

		Object[] params = new Object[] { category.getName(), category.getIcon(), category.getImage(), 1 };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();

		String query = "SELECT CATEGORY_ID, NAME, ICON, IMAGE, STATUS FROM " + "t_category ORDER BY NAME";
		try {

			categoryList = (ArrayList<Category>) jdbcTemplate.query(query, new RowMapper<Category>() {
				public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("NAME"));
					category.setIcon(rs.getString("ICON"));
					category.setImage(rs.getString("IMAGE"));

					return category;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return categoryList;
	}

	public Category getCategoryDetails(int categoryId) {
		Category categoryDetails = new Category();

		String query = "SELECT CATEGORY_ID, NAME, ICON, IMAGE, STATUS FROM "
				+ "t_category WHERE CATEGORY_ID=? AND STATUS=1";
		try {

			Object[] params = new Object[] { categoryId };

			categoryDetails = (Category) jdbcTemplate.queryForObject(query, params, new RowMapper<Category>() {
				public Category mapRow(ResultSet rs, int rowNum) throws SQLException {

					Category category = new Category();
					category.setCategoryId(rs.getInt("CATEGORY_ID"));
					category.setName(rs.getString("NAME"));
					category.setIcon(rs.getString("ICON"));
					category.setImage(rs.getString("IMAGE"));

					return category;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return categoryDetails;
	}

	public int editCategoryDetails(Category category) {

		String query = "SELECT CATEGORY_ID FROM t_category TU WHERE NAME=? AND CATEGORY_ID!=? LIMIT 1";
		Object[] conditionObject = new Object[] { category.getName(), category.getCategoryId() };

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		DBUtil dbUtil = (DBUtil) context.getBean("dbUtil");

		int result = dbUtil.getJDBCIntResult(query, conditionObject);
		if (result > 0)
			return -2;

		String INSERT_USER_SESSION = "UPDATE t_category SET NAME=?, ICON=?, IMAGE=? WHERE CATEGORY_ID=?";

		Object[] params = new Object[] { category.getName(), category.getIcon(), category.getImage(),
				category.getCategoryId() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
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

	public ArrayList<SubDomain> getSubDomainList() {

		ArrayList<SubDomain> subDomainList = new ArrayList<SubDomain>();

		String query = "SELECT SUBDOMAIN_ID, NAME, LOGO_URL, SUBDOMAIN_URL, FOOTER_TEXT, HEADER_TEXT, STATUS FROM t_subdomain WHERE"
				+ " STATUS=1 ORDER BY NAME";
		try {

			subDomainList = (ArrayList<SubDomain>) jdbcTemplate.query(query, new RowMapper<SubDomain>() {
				public SubDomain mapRow(ResultSet rs, int rowNum) throws SQLException {

					SubDomain subDomain = new SubDomain();
					subDomain.setSubDomainId(rs.getInt("SUBDOMAIN_ID"));
					subDomain.setName(rs.getString("NAME"));
					subDomain.setLogoUrl(rs.getString("LOGO_URL"));
					subDomain.setSubdomainURL(rs.getString("SUBDOMAIN_URL"));
					subDomain.setFooterText(rs.getString("FOOTER_TEXT"));
					subDomain.setHeaderText(rs.getString("HEADER_TEXT"));
					return subDomain;

				}
			});
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return subDomainList;

	}
	
	public SubDomain getSubDomainDetails(int subDomainId) {
		SubDomain userSubDomain = null;

		String query = "SELECT SUBDOMAIN_ID, NAME, LOGO_URL, SUBDOMAIN_URL, FOOTER_TEXT, HEADER_TEXT, STATUS FROM t_subdomain"
				+ " WHERE SUBDOMAIN_ID=? AND STATUS=1 LIMIT 1";
		try {

			Object[] params = new Object[] { subDomainId };

			userSubDomain = (SubDomain) jdbcTemplate.queryForObject(query, params, new RowMapper<SubDomain>() {
				public SubDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
					SubDomain subDomain = new SubDomain();
					subDomain.setSubDomainId(rs.getInt("SUBDOMAIN_ID"));
					subDomain.setName(rs.getString("NAME"));
					subDomain.setLogoUrl(rs.getString("LOGO_URL"));
					subDomain.setSubdomainURL(rs.getString("SUBDOMAIN_URL"));
					subDomain.setFooterText(rs.getString("FOOTER_TEXT"));
					subDomain.setHeaderText(rs.getString("HEADER_TEXT"));
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

	public int addBusinessGallery(AdvertiserGallery advertiserGallery) {
		CalendarUtil calendarUtil = new CalendarUtil();

		String INSERT_USER_SESSION = "INSERT INTO  t_advertiser_gallery (ADVERTISER_ID, IMAGE_NAME, STATUS, CREATED_ON)"
				+ " VALUES (?, ?, ?, ?)";

		Object[] params = new Object[] { advertiserGallery.getAdvertiser().getAdvertiserId(),
				advertiserGallery.getImageName(), 1, calendarUtil.getISTCurrentDateTime() };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int removeBusinessGallery(int advertiserGalleryId, int advertiserId) {

		String INSERT_USER_SESSION = "UPDATE t_advertiser_gallery SET STATUS=0 WHERE ADVERTISER_GALLERY_ID=? AND ADVERTISER_ID=?";

		Object[] params = new Object[] { advertiserGalleryId, advertiserId };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int removeBusinessCoupon(int couponId) {

		String INSERT_USER_SESSION = "UPDATE t_coupon SET STATUS=0 WHERE COUPON_ID=?";

		Object[] params = new Object[] { couponId };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

	public int removeBusiness(int advertiserId) {

		String INSERT_USER_SESSION = "UPDATE t_advertiser SET STATUS=0 WHERE ADVERTISER_ID=?";

		Object[] params = new Object[] { advertiserId };

		return jdbcTemplate.update(INSERT_USER_SESSION, params);
	}

}

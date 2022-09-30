package com.digitizeads.util;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class Constants {

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

	public static final float COMPRESSION_QUALITY = 0.2f;

	public static final String USER_AJAX_RESPONSE_INACTIVE = "INACTIVE";
	public static final String USER_AJAX_RESPONSE_INVALID = "INVALID";
	public static final String USER_AJAX_RESPONSE_SUCCESS = "SUCCESS";
	public static final String USER_AJAX_RESPONSE_ERROR = "ERROR";
	public static final String USER_AJAX_RESPONSE_WARNING = "WARNING";
	
	public static final String USER_LOGIN_AJAX_RESPONSE_INACTIVE = "INACTIVE";
	public static final String USER_LOGIN_AJAX_RESPONSE_INVALID = "INVALID";
	public static final String USER_LOGIN_AJAX_RESPONSE_SUCCESS = "SUCCESS";
	public static final String USER_LOGIN_AJAX_RESPONSE_ERROR = "ERROR";
	public static final String USER_LOGIN_AJAX_RESPONSE_CORRECT = "CORRECT";
	public static final String USER_LOGIN_AJAX_RESPONSE_INCORRECT = "INCORRECT";
	
	
	public static final String ADMIN_DTO = "admin_dto";
	
	public static final String ADMIN_ERROR_MESSAGE = "admin_error_message";
	public static final String ADMIN_SUCCESS_MESSAGE = "admin_success_message";
	public static final String ADMIN_LOGIN_INVALID_EMAIL = "admin_login_invalid_email";
	
	public static final String ADMIN_ADVERTISER_LIST = "admin_advertiser_list";
	public static final String ADMIN_COUPON_LIST = "admin_coupon_list";
	
	public static final String ADMIN_ADVERTISER_DETAIL = "admin_advertiser_detail";
	public static final String ADVERTISER_GALLERY_LIST = "advertiser_gallery_list";
	public static final String ADVERTISER_SERVICE_LIST = "advertiser_service_list";
	
	public static final String ADVERTISER_COUPON_LIST = "advertiser_coupon_list";
	
	public static final String ADMIN_COUPON_DETAIL = "admin_coupon_detail";
	
	public static final String ALL_ADVERTISER_LIST = "all_advertiser_list";
	public static final String CATEGORY_LIST = "category_list";
	public static final String CATEGORY_DETAILS = "category_details";
	
	public static final String FOOTER_CATEGORY_LIST = "footer_category_list";
	
	public static final String FEATURED_ADVERTISER_LIST = "featured_advertiser_list";
	public static final String TRENDING_ADVERTISER_LIST = "trending_advertiser_list";
	public static final String FEATURED_COUPON_LIST = "featured_coupon_list";
	
	public static final String ADVERTISER_LIST = "advertiser_list";
	public static final String ADVERTISER_DETAILS = "advertiser_details";
	
	public static final String ADVERTISER_PAGINATION = "advertiser_pagination";

	public static final String ADVERTISER_SEARCH_NAME = "advertiser_search_name";
	
	public static final String COUPON_DETAIL = "coupon_detail";
	
	public static final String USER_SUBDOMAIN = "user_subdomain";
	public static final String SUBDOMAIN_LIST = "subdomain_list";
	
	
	public static final String BUCKET = SpringProperty.getPropValue("bucketName") != null
			? SpringProperty.getPropValue("bucketName") : "evolve2.0";

	public static final String s3Link = SpringProperty.getPropValue("s3Link") != null
			? SpringProperty.getPropValue("s3Link") : "https://encredia.s3.ap-south-1.amazonaws.com";

	public static final String USER_PROFILE_AWS_IMG_FOLDER = "user_profile_pic/";

	public static final String USER_PROFILE_AWS_IMG_LINK = s3Link + "/" + USER_PROFILE_AWS_IMG_FOLDER;

	public static boolean isCrossScriptDetected(String val) {
		boolean isDetected = false;

		try {

			if (val != null && val.length() > 0) {
				if (val.toLowerCase().contains("<form"))
					isDetected = true;
				else if (val.toLowerCase().contains("%3cform"))
					isDetected = true;
				else if (val.toLowerCase().contains("<input type"))
					isDetected = true;
				else if (val.toLowerCase().contains("%3cinput type"))
					isDetected = true;
				else if (val.toLowerCase().contains("<form action"))
					isDetected = true;
				else if (val.toLowerCase().contains("%3cform action"))
					isDetected = true;
				else if (val.toLowerCase().contains("type=submit"))
					isDetected = true;
				else if (val.toLowerCase().contains("<script"))
					isDetected = true;
				else if (val.toLowerCase().contains("%3cscript"))
					isDetected = true;
				else if (val.toLowerCase().contains("document.location"))
					isDetected = true;
				else if (val.toLowerCase().contains("document.cookie"))
					isDetected = true;
				else if (val.toLowerCase().contains("<img src"))
					isDetected = true;
				else if (val.toLowerCase().contains("%3cimg src"))
					isDetected = true;
				else if (val.toLowerCase().contains("javascript"))
					isDetected = true;
			}

		} catch (Exception e) {
			isDetected = true;
		}

		return isDetected;
	}

	public static String getCrossScriptString(String val) {
		try {
			if (val != null && val.length() > 0) {
				if (isCrossScriptDetected(val)) {
					val = null;
				} else {

					val = val.replaceAll("%3c", "");
					val = val.replaceAll("%3C", "");

					val = val.replaceAll("%3e", "");
					val = val.replaceAll("%3E", "");

					val = val.replaceAll("&lt;", "");
					val = val.replaceAll("&gt;", "");

					val = val.replaceAll("x3c", "");
					val = val.replaceAll("x3C", "");

					val = val.replaceAll("x3e", "");
					val = val.replaceAll("x3E", "");

					val = val.replaceAll("getURL", "");
					// val = val.replaceAll("x3E", "");

					val = val.replaceAll("<iframe", "");
					val = val.replaceAll("<IFRAME", "");

					val = val.replaceAll("iframe", "");
					val = val.replaceAll("IFRAME", "");

					val = val.replaceAll("<img", "");
					val = val.replaceAll("<IMG", "");

					val = val.replaceAll("<script>", "");
					val = val.replaceAll("<SCRIPT>", "");

					val = val.replaceAll("<script", "");
					val = val.replaceAll("<SCRIPT", "");

					val = val.replaceAll("script", "");
					val = val.replaceAll("SCRIPT", "");

					val = val.replaceAll("<meta", "");
					val = val.replaceAll("<META", "");

					val = val.replaceAll("<body", "");
					val = val.replaceAll("<BODY", "");

					val = val.replaceAll("&lt;body", "");
					val = val.replaceAll("&lt;BODY", "");

					val = val.replaceAll("onload", "");
					val = val.replaceAll("ONLOAD", "");

					val = val.replaceAll("ALERT", "");
					val = val.replaceAll("alert", "");

					val = val.replaceAll("<embed", "");
					val = val.replaceAll("<EMBED", "");

					val = val.replaceAll("<object", "");
					val = val.replaceAll("<OBJECT", "");

					val = val.replaceAll("<applet", "");
					val = val.replaceAll("<APPLET", "");

					val = val.replaceAll("<form", "");
					val = val.replaceAll("<FORM", "");

					val = val.replaceAll("<head", "");
					val = val.replaceAll("<HEAD", "");

					// val = val.replaceAll("<", "").replaceAll(">", "");
					val = val.replaceAll("eval\\((.*)\\)", "");
					val = val.replaceAll("[\\\"\\\'][\\s]*((?i)javascript):(.*)[\\\"\\\']", "\"\"");
					val = val.replaceAll("((?i)script)", "");

					val = val.replaceAll("iframe", "");
					val = val.replaceAll("IFRAME", "");

					val = val.replaceAll("img ", "");
					val = val.replaceAll("IMG ", "");

					val = val.replaceAll("'", "\'");
					val = val.replaceAll("\n", "<br />");
					// val = val.replaceAll(">", "");

					val = val.replaceAll("document.cookie", "");

				}

				// val = val.replaceAll(">", "");

			}
		} catch (Exception e) {
			val = null;
		}

		return val;
	}

	public static int returnInt(String val) {
		try {
			if (val != null && !"".equals(val) && !"null".equals(val)) {
				int returnVal = Integer.parseInt(val);
				return returnVal;
			} else {
				return 0;
			}

		} catch (Exception e) {
			return 0;
		}
	}

	public static String checkNull(String val) {
		try {
			if (val != null && !"".equals(val) && !"null".equals(val)) {
				return val;
			} else {
				return "";
			}

		} catch (Exception e) {
			return "";
		}
	}

	public static boolean isStringNull(String val) {
		try {
			if (val != null && !"".equals(val) && !"null".equals(val)) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			return true;
		}
	}

	public static String toSlug(String input) {
		String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
		String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		slug = EDGESDHASHES.matcher(slug).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}

	public static String removeSummerNoteTags(String input) {

		String output = null;

		if (!isStringNull(input) && input.indexOf("<p>") >= 0 && input.lastIndexOf("<br></p>") > 0) {

			int endTextIndex = input.lastIndexOf("<br></p>");

			output = input.substring(input.indexOf("<p>") + 3, endTextIndex);

		} else {
			output = input;
		}

		return output;
	}

	public static String generateUniqueUrlId(int length) {
		String uniqueid = null;
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));

		uniqueid = sb.toString();

		return uniqueid.toLowerCase();

	}

	public static String shortResponse(String inputStr, int length) {
		try {
			if (inputStr.length() > length) {
				int shortIndex = inputStr.indexOf(" ", length > 0 ? length : 100);
				String shortResponse = inputStr.substring(0, shortIndex);
				return shortResponse + "...";
			} else {
				return inputStr;
			}
		} catch (Exception e) {
			return inputStr;
		}

	}

	public static String longResponse(String inputStr) {
		try {
			if (inputStr.length() > 101) {
				String shortResponse = inputStr.substring(inputStr.indexOf(" ", 101), inputStr.length());
				return shortResponse;
			} else {
				return null;
			}
		} catch (Exception e) {
			return inputStr;
		}

	}

	public static int randInt(int minimum, int maximum) {

		Random rn = new Random();
		int range = maximum - minimum + 1;
		int randomNum = rn.nextInt(range) + minimum;
		return randomNum;
	}
}

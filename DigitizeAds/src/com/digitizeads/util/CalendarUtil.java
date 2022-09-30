package com.digitizeads.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class CalendarUtil {

	/**
	 * This method formats the date and return date in the format MMM DD, YYYY
	 * 
	 * @return
	 */
	public String formatDate(java.util.Date inputDate, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = null;
		try {
			if (inputDate != null)
				formattedDate = df.format(inputDate);
		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return formattedDate;
	}

	/**
	 * This method converts input date to specified timezone datetime
	 * 
	 * @return Date
	 */
	public java.util.Date convertTimeZone(java.util.Date inputDate,
			String srcTimeZone, String destTimeZone) {
		String format = "MMM dd, yy h:mm a";
		// if(TimeZone.getTimeZone(srcTimeZone).useDaylightTime())inputDate.setTime(inputDate.getTime()+TimeZone.getTimeZone(srcTimeZone).getDSTSavings());
		String inputDateStr = formatDate(inputDate, format);
		String formattedDate = null;
		java.util.Date outputDate = null;
		SimpleDateFormat srcDF = new SimpleDateFormat(format);
		srcDF.setTimeZone(TimeZone.getTimeZone(srcTimeZone));

		try {
			// Parses the value and assumes it represents a date and time in the
			// source timezone
			java.util.Date conDate = srcDF.parse(inputDateStr);

			SimpleDateFormat destDF = new SimpleDateFormat(format);
			destDF.setTimeZone(TimeZone.getTimeZone(destTimeZone));

			// if(TimeZone.getTimeZone(destTimeZone).useDaylightTime())conDate.setTime(conDate.getTime()+TimeZone.getTimeZone(destTimeZone).getDSTSavings());
			formattedDate = destDF.format(conDate);
			outputDate = parseDate(formattedDate, format);

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return outputDate;
	}

	/**
	 * This method converts input time to specified timezone datetime
	 * 
	 * @return Date
	 */
	public String convertTimeZone(String inputDate, String srcTimeZone,
			String destTimeZone, String format) {
		// String format = "h:mm a";
		SimpleDateFormat srcDF = new SimpleDateFormat(format);
		srcDF.setTimeZone(TimeZone.getTimeZone(srcTimeZone));
		String formattedDate = null;
		try {
			// Parses the value and assumes it represents a date and time in the
			// source timezone
			java.util.Date conDate = srcDF.parse(inputDate);
			// if(TimeZone.getTimeZone(srcTimeZone).useDaylightTime())
			// conDate.setTime(conDate.getTime()+(TimeZone.getTimeZone(srcTimeZone)).getDSTSavings());

			SimpleDateFormat destDF = new SimpleDateFormat(format);
			destDF.setTimeZone(TimeZone.getTimeZone(destTimeZone));

			// if(TimeZone.getTimeZone(destTimeZone).useDaylightTime())
			// conDate.setTime(conDate.getTime()+(TimeZone.getTimeZone(destTimeZone)).getDSTSavings());
			// System.out.print("Without manual conversion");
			formattedDate = destDF.format(conDate);

		} catch (Exception pe) {
			System.out.println("Error in convertTimeZone time " + pe);
		}
		return formattedDate;
	}

	/**
	 * This method converts HE available time in calendar
	 * 
	 * @return String[]
	 */
	public String[] convertTimeLabel(String inputTimeLabel[],
			String srcTimeZone, String destTimeZone, String format) {
		String outputTimeLabel[] = new String[inputTimeLabel.length];
		try {
			for (int i = 0; i < inputTimeLabel.length; i++)
				outputTimeLabel[i] = convertTimeZone(inputTimeLabel[i],
						srcTimeZone, destTimeZone, format);

		} catch (Exception pe) {
			System.out.println("Error in convertTimeLabel  " + pe);
		}
		return outputTimeLabel;
	}

	/**
	 * This method parses the date in the format MMM DD, YYYY and returns date
	 * 
	 * @return Date
	 */
	public java.util.Date parseDate(String inputDateStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		java.util.Date outputDate = null;
		try {
			if (inputDateStr != null && !"".equals(inputDateStr))
				outputDate = df.parse(inputDateStr);
		} catch (java.text.ParseException pe) {
			System.out.println("Error in parsing date " + pe);
		}
		return outputDate;
	}

	/**
	 * The method convertDateJavaToSql convert date from java.util.date to
	 * java.sql.date which is useful when inserting date variable into sql table
	 * * @param JavaDate
	 * 
	 * @return
	 */
	public java.sql.Date convertDateJavaToSql(Date JavaDate) {
		java.sql.Date SqlDate = null;
		try {
			if (JavaDate != null)
				SqlDate = (new java.sql.Date(JavaDate.getTime()));
		} catch (Exception e) {

		}

		return SqlDate;
	}

	/**
	 * The method convertDateSqlToJava convert date from java.sql.date to
	 * java.util.date which is useful when retrieving date variable from sql
	 * table and formatting it.
	 *
	 * 
	 * @param SqlDate
	 * @return
	 */
	public java.util.Date convertDateSqlToJava(java.sql.Date SqlDate) {
		Date JavaDate = null;

		try {
			if (SqlDate != null)
				JavaDate = (new java.util.Date(SqlDate.getTime()));
		} catch (Exception e) {

		}

		return JavaDate;

	}

	/**
	 * The method convertTimeSqlToJava convert Time from java.sql.Time to
	 * java.util.date which is useful when retrieving the variable from sql
	 * table and formatting it.
	 * 
	 * @param SqlTime
	 * @return
	 */
	public java.util.Date convertTimeSqlToJava(java.sql.Time SqlTime) {
		Date JavaTime = null;

		try {
			if (SqlTime != null)
				JavaTime = (new java.util.Date(SqlTime.getTime()));
		} catch (Exception e) {

		}

		return JavaTime;

	}

	/**
	 * The method convertTimeJavaToSql converts the java time to sql time. which
	 * is usefull in inserting the time into the sql table.
	 * 
	 * @return
	 */
	public Time convertTimeJavaToSql(Date JavaTime) {
		Time SqlTime = null;

		try {
			if (JavaTime != null)
				SqlTime = (new Time(JavaTime.getTime()));
		} catch (Exception e) {

		}
		return SqlTime;

	}

	/**
	 * This method formats the sql date and return date in the specified format
	 * 
	 * @return
	 */
	public String formatSQLDate(java.sql.Date inputSQLDate, String format) {
		if (inputSQLDate != null) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			String formattedDate = null;
			java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
			try {
				formattedDate = df.format(inputDate);
			} catch (Exception pe) {
				System.out.println("Error in formatting date " + pe);
			}
			return formattedDate;
		} else {
			return "";
		}

	}

	/**
	 * This method returns the data differrence in either in secs, mins, hours,
	 * days, months or years
	 * 
	 * @return
	 */
	public String formattedDateDifferrence(java.util.Date inputDate,
			java.sql.Time sqlTime) {
		// SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = null;
		// java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
		try {
			Date start = inputDate; // JANUARY_1_2007
			Date end = new java.util.Date(); // APRIL_1_2007

			long diffInSeconds = (end.getTime() - start.getTime()) / 1000;

			long diffDays = diffInSeconds / (24 * 60 * 60);

			if (diffDays == 0 && sqlTime != null) {
				DateFormat dateFormat = new SimpleDateFormat(
						"MM/dd/yyyy hh:mm aa");
				Calendar cal = Calendar.getInstance();

				cal.setTime(dateFormat
						.parse(formatDate(start, "MM/dd/yyyy")
								+ " "
								+ formatDate(convertTimeSqlToJava(sqlTime),
										"hh:mm aa")));

				diffInSeconds = (end.getTime() - cal.getTimeInMillis()) / 1000;
			}

			long diff[] = new long[] { 0, 0, 0, 0, 0 };
			/* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60
					: diffInSeconds);
			/* min */diff[2] = (diffInSeconds = (diffInSeconds / (60))) >= 60 ? diffInSeconds % 60
					: diffInSeconds;
			/* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24
					: diffInSeconds;
			/* days */diff[0] = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30
					: diffInSeconds;
			/* months */diff[4] = (diffInSeconds = (diffInSeconds / 30));

			formattedDate = String
					.format("%d month%s, %d day%s, %d hour%s, %d minute%s, %d second%s ago",
							diff[4], diff[4] > 1 ? "s" : "", diff[0],
							diff[0] > 1 ? "s" : "", diff[1], diff[1] > 1 ? "s"
									: "", diff[2], diff[2] > 1 ? "s" : "",
							diff[3], diff[3] > 1 ? "s" : "");

			if (diff[4] > 0)
				formattedDate = diff[4] + " month" + ((diff[4] > 1) ? "s" : "");
			else if (diff[0] > 0)
				formattedDate = diff[0] + " day" + ((diff[0] > 1) ? "s" : "");
			else if (diff[1] > 0)
				formattedDate = diff[1] + " hour" + ((diff[1] > 1) ? "s" : "");
			else if (diff[2] > 0)
				formattedDate = diff[2] + " minute"
						+ ((diff[2] > 1) ? "s" : "");
			else if (diff[3] > 0)
				formattedDate = diff[3] + " second"
						+ ((diff[3] > 1) ? "s" : "");

			if (diff[4] == 0 && diff[0] == 0 && diff[1] == 0 && diff[2] == 0) {
				formattedDate = "few seconds";
			}
			formattedDate += " ago";

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return formattedDate;
	}
	
	
	
	public String formattedSocialDateDifferrence(java.util.Date inputDate,
			java.sql.Time sqlTime) {
		// SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = null;
		// java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
		try {
			Date start = inputDate; // JANUARY_1_2007
			Date end = parseDate(getISTCurrentDate() +" "+getISTCurrentTime(), "yyyy-MM-dd hh:mm a"); // APRIL_1_2007

			long diffInSeconds = (end.getTime() - start.getTime()) / 1000;

			long diffDays = diffInSeconds / (24 * 60 * 60);

			if (diffDays == 0 && sqlTime != null) {
				DateFormat dateFormat = new SimpleDateFormat(
						"MM/dd/yyyy hh:mm aa");
				Calendar cal = Calendar.getInstance();

				cal.setTime(dateFormat
						.parse(formatDate(start, "MM/dd/yyyy")
								+ " "
								+ formatDate(convertTimeSqlToJava(sqlTime),
										"hh:mm aa")));

				diffInSeconds = (end.getTime() - cal.getTimeInMillis()) / 1000;
			}

			long diff[] = new long[] { 0, 0, 0, 0, 0 };
			/* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60
					: diffInSeconds);
			/* min */diff[2] = (diffInSeconds = (diffInSeconds / (60))) >= 60 ? diffInSeconds % 60
					: diffInSeconds;
			/* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24
					: diffInSeconds;
			/* days */diff[0] = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30
					: diffInSeconds;
			/* months */diff[4] = (diffInSeconds = (diffInSeconds / 30));

			formattedDate = String
					.format("%d month%s, %d day%s, %d hour%s, %d minute%s, %d second%s ago",
							diff[4], diff[4] > 1 ? "s" : "", diff[0],
							diff[0] > 1 ? "s" : "", diff[1], diff[1] > 1 ? "s"
									: "", diff[2], diff[2] > 1 ? "s" : "",
							diff[3], diff[3] > 1 ? "s" : "");

			if (diff[4] > 0)
				formattedDate = diff[4] + " m";
			else if (diff[0] > 0)
				formattedDate = diff[0] + " d";
			else if (diff[1] > 0)
				formattedDate = diff[1] + " h";
			else if (diff[2] > 0)
				formattedDate = diff[2] + " minute"
						+ ((diff[2] > 1) ? "s" : "");
			else if (diff[3] > 0)
				formattedDate = diff[3] + " second"
						+ ((diff[3] > 1) ? "s" : "");

			if (diff[4] == 0 && diff[0] == 0 && diff[1] == 0 && diff[2] == 0) {
				formattedDate = "few seconds";
			}
//			formattedDate += " ago";

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return formattedDate;
	}

	public int getDateDifferrence(java.util.Date inputDate,
			java.sql.Time sqlTime) {
		// SimpleDateFormat df = new SimpleDateFormat(format);
		int diffDays = 0;
		// java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
		try {
			Date start = inputDate; // JANUARY_1_2007
			Date end = new java.util.Date(); // APRIL_1_2007

			long diffInSeconds = (end.getTime() - start.getTime()) / 1000;

			diffDays = (int) diffInSeconds / (24 * 60 * 60);

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return diffDays;
	}

	/**
	 * This method returns the data differrence in either in secs, mins, hours,
	 * days, months or years
	 * 
	 * @return
	 */
	public String formattedDateDifferrence(java.util.Date fromDate,
			java.util.Date toDate) {
		// SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = null;
		// java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
		try {
			Date start = fromDate; // JANUARY_1_2007
			Date end = toDate != null ? toDate : new java.util.Date(); // APRIL_1_2007

			long diffInSeconds = (end.getTime() - start.getTime()) / 1000;

			long diff[] = new long[] { 0, 0, 0, 0, 0 };
			/* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60
					: diffInSeconds);
			/* min */diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60
					: diffInSeconds;
			/* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24
					: diffInSeconds;
			/* days */diff[0] = (diffInSeconds = (diffInSeconds / 24)) >= 30 ? diffInSeconds % 30
					: diffInSeconds;
			/* months */diff[4] = (diffInSeconds = (diffInSeconds / 30));

			formattedDate = String
					.format("%d month%s, %d day%s, %d hour%s, %d minute%s, %d second%s ago",
							diff[4], diff[4] > 1 ? "s" : "", diff[0],
							diff[0] > 1 ? "s" : "", diff[1], diff[1] > 1 ? "s"
									: "", diff[2], diff[2] > 1 ? "s" : "",
							diff[3], diff[3] > 1 ? "s" : "");
			// System.out.println("date diff : "+formattedDate);

			if (diff[4] > 0)
				formattedDate = diff[4] + " month" + ((diff[4] > 1) ? "s" : "");
			else if (diff[0] > 0)
				formattedDate = diff[0] + " day" + ((diff[0] > 1) ? "s" : "");
			else if (diff[1] > 0)
				formattedDate = diff[1] + " hour" + ((diff[1] > 1) ? "s" : "");
			else if (diff[2] > 0)
				formattedDate = diff[2] + " minute"
						+ ((diff[2] > 1) ? "s" : "");
			else if (diff[3] > 0)
				formattedDate = diff[3] + " second"
						+ ((diff[3] > 1) ? "s" : "");

			if (diff[4] == 0 && diff[0] == 0 && diff[1] == 0 && diff[2] == 0) {
				formattedDate = "few seconds";
			}
			formattedDate += " ago";

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return formattedDate;
	}
	
	
	
	

	public String getISTCurrentDateTime() {
		Calendar currentdate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone obj = TimeZone.getTimeZone("IST");
		formatter.setTimeZone(obj);
		return formatter.format(currentdate.getTime());
	}

	public String convertDatetimeToUTC(String dateTime) {
		try {
			DateFormat formatterIST = new SimpleDateFormat("dd MMMMM, yyyy hh:mm aa");
	    	formatterIST.setTimeZone(TimeZone.getTimeZone("IST")); // better than using IST
	    	Date date = formatterIST.parse(dateTime);

	    	DateFormat formatterUTC = new SimpleDateFormat("dd MMMMM, yyyy hh:mm aa");
	    	formatterUTC.setTimeZone(TimeZone.getTimeZone("UTC")); // UTC timezone
	    	
	    	return formatterUTC.format(date);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	public String getISTCurrentDate() {
		Calendar currentdate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone obj = TimeZone.getTimeZone("IST");
		formatter.setTimeZone(obj);
//		System.out.println("Local:: " + currentdate.getTime());
//		System.out.println("IST:: " + formatter.format(currentdate.getTime()));
		return formatter.format(currentdate.getTime());
	}
	
	public String getISTCurrentTime() {
		Calendar currentdate = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("hh:mm a");
		TimeZone obj = TimeZone.getTimeZone("IST");
		formatter.setTimeZone(obj);
//		System.out.println("Local:: " + currentdate.getTime());
//		System.out.println("IST:: " + formatter.format(currentdate.getTime()));
		return formatter.format(currentdate.getTime());
	}

	public int getDateDifference(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);

		long diffToSend = date2.getTime() - date1.getTime();
		long diffHoursToSend = diffToSend / (60 * 60 * 1000);

		while (cal1.before(cal2)) {
			if ((Calendar.SUNDAY == cal1.get(Calendar.DAY_OF_WEEK))) {
				diffHoursToSend = diffHoursToSend - 24;
			}
			cal1.add(Calendar.DATE, 1);
		}

		return (int) diffHoursToSend;
	}

	public int getAge(String byear) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age = 0;
		if (byear != null && !"".equals(byear)) {
			try {
				int birthYear = Integer.parseInt(byear);
				age = year - birthYear;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return age;
	}

	
	public long getDateDifferrenceInSeconds(java.util.Date fromDate,
			java.util.Date toDate) {
		// SimpleDateFormat df = new SimpleDateFormat(format);
		long diffInMinutes = 0;
		// java.util.Date inputDate = convertDateSqlToJava(inputSQLDate);
		try {
			Date start = fromDate; // JANUARY_1_2007
			Date end = toDate != null ? toDate : new java.util.Date(); // APRIL_1_2007

			long diff = end.getTime() - start.getTime();//as given
			
			diffInMinutes = TimeUnit.MILLISECONDS.toSeconds(diff); 

		} catch (Exception pe) {
			System.out.println("Error in formatting date " + pe);
		}
		return diffInMinutes;
	}
	
	
	public String calculateTime(int seconds) {
		int hours = seconds / 3600;
        int minutes = (seconds%3600)/60;
        int seconds_output = (seconds% 3600)%60;

       // System.out.println(hours  + " hours :" + minutes + " minutes:" + seconds_output +" seconds");
        String timeTaken = "";
        if(hours>0){
        	timeTaken =  hours  + " hrs : " + minutes + " min : " + seconds_output +" sec";
        }else{
        	timeTaken =  minutes + " min : " + seconds_output +" sec";
        } 
        
        return timeTaken;
    }

	
}

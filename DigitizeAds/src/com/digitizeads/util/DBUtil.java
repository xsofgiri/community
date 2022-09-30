package com.digitizeads.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class DBUtil {

	private DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getCountOfRows(String sql) {
		int result = -1;
		if (sql == null || sql.trim().equals("")) {

//			System.out.println(">>getCountOfRows() recieved invalid input. >sql:");
		} else {
//			System.out.println(">>getCountOfRows() input received >Sql:" + sql);

			Connection conn = null;
			ResultSet rs = null;
			Statement stmt = null;
			try {
				conn = dataSource.getConnection();
				if (conn != null) {

					stmt = conn.createStatement();
					rs = stmt.executeQuery("select count(*) from (" + sql + ") as count");
					if (rs != null && rs.next()) {
						result = rs.getInt(1);
					}
//					System.out.println("---getCountOfRows() returns >result:" + result);
					return result;

				} else {
					System.out.println("---getCountOfRows() Failed to get DB Connection.");
				}
			} catch (SQLException e) {
				// TODO output error
				e.printStackTrace();
				System.out.println("---getCountOfRows() SQL Exception Raised : Exception :"
						+ e.getMessage()
						+ (e.getNextException() != null ? " Next Exception :"
								+ e.getNextException().getMessage() : ""));
			} catch (Exception e) {
				// TODO output error
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (stmt != null) {
						stmt.close();
						stmt = null;
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ee) {
				}
			}
		}
		return result;
	}
	
	
	public int getJDBCIntResult(String query, Object[] params) {
		int result = 0;
		try {
			result = (Integer) jdbcTemplate.queryForObject(query, params, Integer.class);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return result;
		} catch (QueryTimeoutException e) {
			System.out.println(e);
			return result;
		}  catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return result;
		}

	}
	
	
	
	public String getJDBCGroupResult(String query, Object[] params) {
		String result = null;
		try {
			ArrayList<Integer> groupResult = new ArrayList<Integer>();
			
			groupResult = (ArrayList<Integer>) jdbcTemplate.query(query, params, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
				
			if(groupResult!=null && groupResult.size()>0){
				
				System.out.println("groupResult : "+groupResult.size());
				Object[] resultArray = groupResult.toArray();
				System.out.println("resultArray : "+resultArray.length);
				
				result = Arrays.toString(resultArray).substring(1, (Arrays.toString(resultArray).length() - 1));
				
				System.out.println("result : "+result);
				
			}
			
			return result;
		} catch (EmptyResultDataAccessException e) {
			return result;
		} catch (QueryTimeoutException e) {
			System.out.println(e);
			return result;
		}  catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return result;
		}

	}
	
	
	public String getJDBCGroupResult(String query) {
		String result = null;
		try {
			ArrayList<Integer> groupResult = new ArrayList<Integer>();
			
			groupResult = (ArrayList<Integer>) jdbcTemplate.query(query, new RowMapper<Integer>() {
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getInt(1);
				}
			});
				
			if(groupResult!=null && groupResult.size()>0){
				
				System.out.println("groupResult : "+groupResult.size());
				Object[] resultArray = groupResult.toArray();
				System.out.println("resultArray : "+resultArray.length);
				
				result = Arrays.toString(resultArray).substring(1, (Arrays.toString(resultArray).length() - 1));
				
				System.out.println("result : "+result);
				
			}
			
			return result;
		} catch (EmptyResultDataAccessException e) {
			return result;
		} catch (QueryTimeoutException e) {
			System.out.println(e);
			return result;
		}  catch (Exception e) {
			System.out.println("e : " + e + " - SQL QUERY_________" + query);
			return result;
		}

	}
}
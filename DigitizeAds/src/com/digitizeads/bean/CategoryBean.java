package com.digitizeads.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.digitizeads.modal.Category;

public class CategoryBean {

	private DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();

		String query = "SELECT CATEGORY_ID, NAME, ICON, IMAGE, STATUS FROM "
				+ "t_category WHERE STATUS=1  ORDER BY ORDER_NO";
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
}

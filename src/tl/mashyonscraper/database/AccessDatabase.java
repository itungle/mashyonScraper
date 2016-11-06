package tl.mashyonscraper.database;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;

import tl.mashyonscraper.database.*;
import tl.mashyonscraper.model.*;

public class AccessDatabase {
	
	/**
	 * Return 0 if page is not existed, else return 1 if page is existed 
	 * 
	 * @param conn connection to mysql database
	 * @param date of page
	 * @return pageID  
	 */
	public static int doesPageExist(Connection conn, Date date) {
		int pageID = 0;
		String checkingPageExistQuery = "SELECT id FROM page_info WHERE date=?";
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = conn.prepareStatement(checkingPageExistQuery);
			pStatement.setDate(1, sqlDate);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				pageID = resultSet.getInt(1);
			}
			resultSet.close();
			pStatement.close();
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				
		return pageID;
	}
	/**
	 * 
	 * @param conn
	 * @param title
	 * @param date
	 * @return
	 */
	public static int insertPageInfo(Connection conn, String title, Date date){		
		String insertIntoPageInfo = "INSERT INTO page_info(title, date) VALUES (?, ?)";
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int validInsert = 0;
		int returnInt = -1;
		boolean autoCommit;
		try {

			pStatement = conn.prepareStatement(insertIntoPageInfo, Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, title);
			pStatement.setDate(2, sqlDate);
			validInsert = pStatement.executeUpdate();
			if (validInsert == 1) {
				resultSet = pStatement.getGeneratedKeys();
				
				if (resultSet.next())
				{
					returnInt = resultSet.getInt(1);
				}
			}
			resultSet.close();
			pStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return returnInt;
		
		
	}
	/**
	 * 
	 * @param conn
	 * @param page_id
	 * @param imageUrls
	 */
	public static void insertIntoImageInfo(Connection conn, int page_id, List<String> imageUrls) throws SQLException {
		String insertImages = "INSERT INTO image_info(page_id, image_url) VALUES (?, ?)";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int batchLimit = 1000;
		boolean autoCommit = conn.getAutoCommit();
		try {
			conn.setAutoCommit(false);
			pStatement = conn.prepareStatement(insertImages);
			
			for (String url : imageUrls) {
				
				pStatement.setInt(1, page_id);
				pStatement.setString(2, url);
				pStatement.addBatch();
				batchLimit--;
				if (batchLimit == 0) {
					pStatement.executeBatch();
					pStatement.clearBatch();
					batchLimit = 1000;
				}
			}
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pStatement.executeBatch();
			conn.commit();
			
		}
		conn.setAutoCommit(autoCommit);
	}
	
}

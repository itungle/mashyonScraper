package tl.mashyonscraper.database;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.util.*;

public class AccessDatabase {
	
	public List<String> getAllTitle(String table_name, Connection conn) {
		List<String> titleArr = new ArrayList<String>();
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		String query = "SELECT * from page_info WHERE id=?";
		String name = "1";
		try {
			pStatement = conn.prepareStatement(query);
			
			pStatement.setString(1, name);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				titleArr.add(rs.getString("title"));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return titleArr;
	}
	
}

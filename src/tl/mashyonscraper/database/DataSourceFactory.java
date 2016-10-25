package tl.mashyonscraper.database;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.util.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DataSourceFactory {
	
	public static DataSource getMySqlDataSource() {
		Properties dbProps = new Properties();
		FileInputStream propertiesFile = null;
		MysqlDataSource mysqlDataSource = null;
		String dbUserName ;
		String dbPassword ;
		String dbURL;
		try {
			propertiesFile = new FileInputStream("properties/database.properties");
			mysqlDataSource = new MysqlDataSource();			
			dbProps.load(propertiesFile);
			dbUserName = dbProps.getProperty("MYSQL_USERNAME");
			dbPassword = dbProps.getProperty("MYSQL_PASSWORD");
			dbURL = dbProps.getProperty("MYSQL_DB_URL");
			
			mysqlDataSource.setUser(dbUserName);
			mysqlDataSource.setPassword(dbPassword);			
			mysqlDataSource.setUrl(dbURL);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDataSource;
	}

}

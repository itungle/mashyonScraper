/**
 * 
 */
package tl.mashyonscraper;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import tl.mashyonscraper.database.*;
import tl.mashyonscraper.helper.*;
import tl.mashyonscraper.model.Page;





/**
 * MashyonScraper.java
 * Purpose: Start the scraper by calling other classes
 * @author tungle
 * @version 1.0
 * @since 10-09-2016
 *
 */
public class MashyonScraper {
	/**
	 * @param args reddit/r/malefashionadvice's WAYWT URL
	 * @return Nothing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		File fileToOpen = null;
		String currLine = "";
		List<String> urlList = new ArrayList<String>();
		if (args.length != 1) {
			System.out.println("Please restart program and enter ONE valid file (example: /home/input/links.txt");			
			System.exit(0);
		}
		else {
			fileToOpen = new File(args[0]);			
		}
		try {
			FileReader fileReader = new FileReader(fileToOpen);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			while ((currLine = bufferReader.readLine()) != null)
			{
				System.out.println(currLine);
				urlList.add(currLine);
			}
			bufferReader.close();
			fileReader.close();
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		DataSource ds = DataSourceFactory.getMySqlDataSource();
		Connection conn = null;
		List<Page> pageList = new ArrayList<Page>();
		try {
			conn = ds.getConnection();
			pageList = ScrapeHelper.extractDocument(urlList);
			int page_id = 0;
			for (Page p : pageList){
				page_id = AccessDatabase.doesPageExist(conn, p.getDate());
				System.out.println(page_id);
				if (page_id != 0) {
					AccessDatabase.insertIntoImageInfo(conn, page_id, p.getImageList());
				}
				else {
					page_id = AccessDatabase.insertPageInfo(conn, p.getTitle(), p.getDate());
					System.out.println(page_id);
					AccessDatabase.insertIntoImageInfo(conn, page_id, p.getImageList());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

		
		
	}

}

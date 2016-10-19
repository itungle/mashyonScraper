/**
 * 
 */
package tl.mashyonscraper;
import java.util.*;
import java.io.*;

import tl.mashyonscraper.controller.ScrapeController;




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
		
		ScrapeController scrapeController = new ScrapeController();
		scrapeController.extractDocument(urlList);
		
	}

}

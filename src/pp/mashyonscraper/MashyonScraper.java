/**
 * 
 */
package pp.mashyonscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import pp.mashyonscraper.controller.ScrapeController;

/**
 * MashyonScraper.java
 * Purpose: Start the scraper by calling other classes
 * @author tungle
 * @version 1.0
 * @since 10-09-2016
 *
 */
public class MashyonScraper {
	public static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
	/**
	 * @param args reddit/r/malefashionadvice's WAYWT URL
	 * @return Nothing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		File fileToOpen = null;
		if (args.length != 1) {
			System.out.println("Please restart program and enter ONE valid file (example: /home/input/links.txt");			
			System.exit(0);
		}
		else {
			fileToOpen = new File(args[0]);			
		}
		try {
			FileReader fileReader = new FileReader(fileToOpen);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		/*
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> files = new ArrayList<String>();
		System.out.println("Enter in file name (example: /inputs/links.txt) or 'quit'");
		while (!scanner.nextLine().trim().equals("quit") && scanner.hasNext()) {
			files.add(scanner.nextLine());
		}
		
		System.out.println("Currently extracting URLs from files");
		*/
	}
	

}

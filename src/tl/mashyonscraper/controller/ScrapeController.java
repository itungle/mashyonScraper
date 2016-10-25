package tl.mashyonscraper.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.logging.Logger;
import java.util.logging.Level;

import tl.mashyonscraper.model.Page;

public class ScrapeController {
	private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
	
	public static List<Page> pageList;
	
	public ScrapeController() {
		pageList = new ArrayList<Page>();
	}
	
	public void extractDocument(List<String> urlsToGet) {
		try {
			if (pageList == null) {
				pageList = new ArrayList<Page>();
			}
			String tempPageTitle = "";
			Date tempPageDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");			
			for (String url : urlsToGet) {			
				Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(0).get();
				tempPageTitle = doc.title().substring(0, doc.title().indexOf(":")).trim();
				System.out.println(tempPageTitle);
				Element dateTimeElement = doc.select("time").first();				
				tempPageDate = dateFormat.parse(dateTimeElement.text());
				System.out.println(tempPageDate);
				Elements tempPageAnchors = doc.select(".usertext-body .md a");
				Page newPage = new Page(tempPageTitle, tempPageDate, tempPageAnchors);		
				pageList.add(newPage);
			}
			
			for (Page eachPage : pageList) {
				eachPage.filterAnchors("abs:href");
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package tl.mashyonscraper.controller;

import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.logging.Logger;
import java.util.logging.Level;


public class ScrapeController {
	private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
	
	public List<String> imageSrcList = null;
	
	public ScrapeController() {
		imageSrcList = new ArrayList<String>();
	}
	
	public static void extractDocument(List<String> urlsToGet) {
		for (String url : urlsToGet) {
			try {
				Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(0).get();
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
		
	public static boolean isImage(String imageURL){
		String png = ".png";
		String jpg = ".jpg";
		String jpeg = ".jpeg";
		String imgur = "imgur";
		if (imageURL.contains(png) || imageURL.contains(jpg) || imageURL.contains(jpeg) || imageURL.contains(imgur)) {
			return true;
		}
		else {
			return false;
		}
	}
}

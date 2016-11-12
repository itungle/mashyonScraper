package tl.mashyonscraper.helper;

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

public class ScrapeHelper {
	private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
		
	
	public static List<Page> extractDocument(List<String> urlsToGet) {
		List<Page> pageList = new ArrayList<Page>();
		try {			
			String tempPageTitle = "";
			List<Elements> elementsArr = new ArrayList<Elements>();
			Date tempPageDate = new Date();
			Date mysqlFormatDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");			
			for (String url : urlsToGet) {			
				Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(0).get();
				tempPageTitle = doc.title().substring(0, doc.title().indexOf(":")).trim();
				System.out.println(tempPageTitle);
				Element dateTimeElement = doc.select("time").first();				
				tempPageDate = dateFormat.parse(dateTimeElement.text());
				System.out.println(tempPageDate);
			
				Elements pageAnchorElements = doc.select(".usertext-body .md a[href]");
				Page newPage = new Page(tempPageTitle, tempPageDate);
				elementsArr.add(pageAnchorElements);
				pageList.add(newPage);
			}
			List<List<String>> pageElementList = new ArrayList<List<String>>();
			for (Elements e : elementsArr) {
				pageElementList.add(filterUrlFromElements(e));
			}
			List<List<String>> pageImageList = new ArrayList<List<String>>();
			for (List<String> urlList : pageElementList) {
				pageImageList.add(filterImageUrls(urlList));
			}
			for (int index = 0; index < pageList.size(); index++) {
				pageList.get(index).setImageList(pageImageList.get(index));
			}
			
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageList;
	}
	
	public static List<String> filterUrlFromElements(Elements elements) {
		String elementAttr = "";
		List<String> imageList = new ArrayList<String>();
		for (Element e : elements) {
			elementAttr = e.attr("abs:href");
			imageList.add(elementAttr);			
		}
		return imageList;
	}
	public static List<String> filterImageUrls(List<String> urls) {
		List<String> imageUrls = new ArrayList<String>();
		for (String url : urls) {
			if (isImage(url)) {
				imageUrls.add(url);
				
			}
			if (isImgur(url)){
				imageUrls.add(url+".jpg");
			}
		}
		return imageUrls;
	}
	
	public static boolean isImage(String url){
		String png = ".png";
		String jpg = ".jpg";
		String jpeg = ".jpeg";
		
		if (url.contains(png) || url.contains(jpg) || url.contains(jpeg)) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isImgur(String url) {
		String imgur = "imgur";
		if (url.contains(imgur) && !url.contains(".jpg")) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

package pp.mashyonscraper.controller;

import java.util.*;


public class ScrapeController {
		
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

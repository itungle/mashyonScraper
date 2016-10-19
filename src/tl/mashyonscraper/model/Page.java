package tl.mashyonscraper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Page {
	
	private String title;
	private Date date;
	private Elements anchorElements;
	private List<String> imageList;
	
	public Page() {
		this.title = null;
		this.date = null;
		this.anchorElements = null;
		this.imageList = new ArrayList<String>();
	}
	
	public Page(String pageTitle) {
		this.title = pageTitle;
		this.date = null;
		this.imageList = new ArrayList<String>();
	}
	
	public Page(String pageTitle, Date pageDate, Elements pageAnchors) {
		this.title = pageTitle;
		this.date = pageDate;
		this.anchorElements = pageAnchors;
		this.imageList = new ArrayList<String>();
	}
	
	/*
	public Page(String pageTitle, Date pageDate, Elements pageAnchors, List<String> imageList) {
		this.title = pageTitle;
		this.date = pageDate;
		this.anchorElements = pageAnchors;
		this.imageList = new ArrayList<String>(imageList);
	}
	*/
	
	public String getTitle() {
		return this.title;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getImageList() {
		return this.imageList;
	}

	public Elements getAnchorElements() {
		return this.anchorElements;
	}
	
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
		
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void filterAnchors(String attribute) {
		String elementAttr = "";
		for (Element e : this.anchorElements) {
			elementAttr = e.attr(attribute);
			if (isImage(elementAttr)) {
				System.out.println(elementAttr);
				this.imageList.add(elementAttr);
			}
		}
	}
	
	public boolean isImage(String imageURL){
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

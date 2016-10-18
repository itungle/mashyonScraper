package tl.mashyonscraper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Page {
	
	private String title;
	private Date date;
	private List<String> imageList;
	
	public Page() {
		this.title = null;
		this.date = null;
		this.imageList = new ArrayList<String>();
	}
	
	public Page(String pageTitle) {
		this.title = pageTitle;
		this.date = null;
		this.imageList = new ArrayList<String>();
	}
	
	public Page(String pageTitle, Date pageDate) {
		this.title = pageTitle;
		this.date = pageDate;
	}
	
	public Page(String pageTitle, Date pageDate, List<String> imageList) {
		this.title = pageTitle;
		this.date = pageDate;
		this.imageList = new ArrayList<String>(imageList);
	}
	
	public String getTitle() {
		return this.title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}

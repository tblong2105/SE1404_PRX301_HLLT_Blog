package com.se1404_prx301_hllt.Blog.model;

public class Blog {
	private int id;
	private String title;
	private String category;
	private String sortDescription;
	private String longDescription;
	private String date;
	private String image;
	private String authorName;
	private String authorPosition;
	private String authorImage;
	
	public Blog() {
		super();
	}
	
	public Blog(int id, String title, String category, String sortDescription, String longDescription, String date,
			String image, String authorName, String authorPosition, String authorImage) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.sortDescription = sortDescription;
		this.longDescription = longDescription;
		this.date = date;
		this.image = image;
		this.authorName = authorName;
		this.authorPosition = authorPosition;
		this.authorImage = authorImage;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSortDescription() {
		return sortDescription;
	}
	public void setSortDescription(String sortDescription) {
		this.sortDescription = sortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorPosition() {
		return authorPosition;
	}
	public void setAuthorPosition(String authorPosition) {
		this.authorPosition = authorPosition;
	}
	public String getAuthorImage() {
		return authorImage;
	}
	public void setAuthorImage(String authorImage) {
		this.authorImage = authorImage;
	}
	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", category=" + category + ", sortDescription=" + sortDescription
				+ ", longDescription=" + longDescription + ", date=" + date + ", image=" + image + ", authorName="
				+ authorName + ", authorPosition=" + authorPosition + ", authorImage=" + authorImage + "]";
	}
}

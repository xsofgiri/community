package com.digitizeads.modal;

public class Advertiser {

	private int advertiserId;
	private String name;
	private String logo;
	private Category category;
	private String latitude;
	private String longitude;
	private String address;
	private String phoneNumber;
	private String email;
	private String website;
	private String aboutUs;
	private String urlSlug;
	private String featuredImage;
	private int status;
	private SubDomain subDomain;
	private boolean isFeatured;

	private String fbLink;
	private String twitterLink;
	private String instagramHandleName;
	
	private String createdOn;
	
	public int getAdvertiserId() {
		return advertiserId;
	}
	public void setAdvertiserId(int advertiserId) {
		this.advertiserId = advertiserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isFeatured() {
		return isFeatured;
	}
	public void setFeatured(boolean isFeatured) {
		this.isFeatured = isFeatured;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getFbLink() {
		return fbLink;
	}
	public void setFbLink(String fbLink) {
		this.fbLink = fbLink;
	}
	public String getTwitterLink() {
		return twitterLink;
	}
	public void setTwitterLink(String twitterLink) {
		this.twitterLink = twitterLink;
	}
	public String getInstagramHandleName() {
		return instagramHandleName;
	}
	public void setInstagramHandleName(String instagramHandleName) {
		this.instagramHandleName = instagramHandleName;
	}
	public String getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}
	public String getFeaturedImage() {
		return featuredImage;
	}
	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}
	public SubDomain getSubDomain() {
		return subDomain;
	}
	public void setSubDomain(SubDomain subDomain) {
		this.subDomain = subDomain;
	}
	
}

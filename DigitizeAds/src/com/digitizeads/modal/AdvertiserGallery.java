package com.digitizeads.modal;

public class AdvertiserGallery {

	private int advertiserGalleryId;
	private Advertiser advertiser;
	private String imageName;
	private int status;
	private String createdOn;
	
	public int getAdvertiserGalleryId() {
		return advertiserGalleryId;
	}
	public void setAdvertiserGalleryId(int advertiserGalleryId) {
		this.advertiserGalleryId = advertiserGalleryId;
	}
	public Advertiser getAdvertiser() {
		return advertiser;
	}
	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}

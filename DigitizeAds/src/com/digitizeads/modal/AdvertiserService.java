package com.digitizeads.modal;

public class AdvertiserService {

	private int advertiserServiceId;
	private Advertiser advertiser;
	private String title;
	private String description;
	private String imageName;
	private int status;
	private int orderNo;
	private String createdOn;
	
	
	public int getAdvertiserServiceId() {
		return advertiserServiceId;
	}
	public void setAdvertiserServiceId(int advertiserServiceId) {
		this.advertiserServiceId = advertiserServiceId;
	}
	public Advertiser getAdvertiser() {
		return advertiser;
	}
	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	
}

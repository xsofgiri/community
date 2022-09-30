package com.digitizeads.util;
/**
 * 
 * @author Giri
 *
 */
public class PageHandler {

	private int resultTotal;
	private int totalPages;
	private int currentPage;
	private int reqPage;
	private int limit;
	private int offset;
	private String surl;
	
	public PageHandler() {
		super();
	}
	
	public int getResultTotal() {
		return resultTotal;
	}
	public void setResultTotal(int resultTotal) {
		this.resultTotal = resultTotal;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getSurl() {
		return surl;
	}
	public void setSurl(String surl) {
		this.surl = surl;
	}
}

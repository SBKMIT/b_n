package com.edelweiss.app.dto;

import java.math.BigDecimal;

public class IssueDto {
	
	private String issueName;
	private BigDecimal biddingAmount;
	private BigDecimal coupon;
	private String date;
	private String time;
	
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public BigDecimal getBiddingAmount() {
		return biddingAmount;
	}
	public void setBiddingAmount(BigDecimal biddingAmount) {
		this.biddingAmount = biddingAmount;
	}
	public BigDecimal getCoupon() {
		return coupon;
	}
	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	

}

package com.java.employ;

import java.util.Date;

public class EmpLogin {

	private String loginId;
	private String username;
	private String password;
	private Date lastLoginTime;
	private String otp;
	private String otpVerifyStatus;
	private String logoutTime;
	
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtpVerifyStatus() {
		return otpVerifyStatus;
	}
	public void setOtpVerifyStatus(String otpVerifyStatus) {
		this.otpVerifyStatus = otpVerifyStatus;
	}
	@Override
	public String toString() {
		return "EmpLogin [loginId=" + loginId + ", username=" + username + ", password=" + password + ", lastLoginTime="
				+ lastLoginTime + ", otp=" + otp + ", otpVerifyStatus=" + otpVerifyStatus + "]";
	}
	public EmpLogin(String loginId, String username, String password, Date lastLoginTime, String otp,
			String otpVerifyStatus) {
		super();
		this.loginId = loginId;
		this.username = username;
		this.password = password;
		this.lastLoginTime = lastLoginTime;
		this.otp = otp;
		this.otpVerifyStatus = otpVerifyStatus;
	}
	public EmpLogin() {
		super();
		// TODO Auto-generated constructor stub
	}
}

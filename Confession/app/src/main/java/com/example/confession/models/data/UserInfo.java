package com.example.confession.models.data;

public final class UserInfo {

	public final BasicUserInfo basic_info;
	public final String email;
	public final String phone;

	public UserInfo(BasicUserInfo basic_info, String email, String phone) {

		this.basic_info = basic_info;
		this.email = email;
		this.phone = phone;
	}
}

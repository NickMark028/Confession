package com.example.confession.models.data;

public final class UserInfo {

	public final BasicUserInfo basic_info;
	public final String email;
	public final String phone;
	public final String auth_token;

	public UserInfo(BasicUserInfo basic_info, String email, String phone, String auth_token) {

		this.basic_info = basic_info;
		this.email = email;
		this.phone = phone;
		this.auth_token = auth_token;
	}
}

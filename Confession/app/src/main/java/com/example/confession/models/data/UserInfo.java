package com.example.confession.models.data;

import java.io.Serializable;

public final class UserInfo implements Serializable {

	public final BasicUserInfo basic_info;
	public final String email;
	public final String phone;
	public final String auth_token;

	public UserInfo(BasicUserInfo basic_info, String email, String phone) {

		this(basic_info, email, phone, "");
	}

	public UserInfo(BasicUserInfo basic_info, String email, String phone, String auth_token) {

		this.basic_info = basic_info;
		this.email = email;
		this.phone = phone;
		this.auth_token = auth_token;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"basic_info=" + basic_info +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", auth_token='" + auth_token + '\'' +
				'}';
	}
}

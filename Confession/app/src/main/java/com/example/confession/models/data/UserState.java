package com.example.confession.models.data;

import java.io.Serializable;

public enum UserState implements Serializable {

	NonMember(0),
	Admin(1),
	Following(2), // member
	Pending(3),
	Undefined(-1);

	private int value;

	UserState(int value)
	{
		this.value = value;
	}
}

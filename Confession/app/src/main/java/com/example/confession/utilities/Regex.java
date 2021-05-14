package com.example.confession.utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Regex {

	public static final Pattern PASSWORD_PATTERN =
			Pattern.compile("^" +
					"(?=.*[0-9])" +				//at least 1 digit
					"(?=.*[a-z])" +				//at least 1 lower case letter
					"(?=.*[A-Z])" +				//at least 1 upper case letter
					"(?=.*[@#$%^&+=])" +		//at least 1 special character
					"(?=\\S+$)" +				//no white space
					".{6,}" +					//at least 6 character
					"$");

	public static final Pattern USERNAME_PATTERN =
			Pattern.compile("^" +
					"(?!.*\\.\\.)" +
					"(?!.*\\.$)" +
					"[^\\W]" +
					"[\\w.]" +
					"{3,25}" +					//3-25 characters long
					"$");

	public static final Pattern GROUPNAME_PATTERN =
			Pattern.compile("^" +
					"(?=.{3,25}$)" +			//3-25 characters long
					"(?![_.])" +				//no _ or . at the beginning
					"(?!.*[_.]{2})" +			//no __ or _. or ._ or .. inside
					"[a-zA-Z0-9._]" +			//allowed characters
					"+" +						//
					"(?<![_.])" +				//no _ or . at the end
					"$");
}


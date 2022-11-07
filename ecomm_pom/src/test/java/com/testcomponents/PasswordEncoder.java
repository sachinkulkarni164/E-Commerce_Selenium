package com.testcomponents;

import org.apache.commons.codec.binary.Base64;

public class PasswordEncoder {
	
	public byte[] PasswordEncode() {
	String pwd = "Hayagrive@123";
	byte[] encodString = Base64.encodeBase64(pwd.getBytes());
	return encodString;
	}

}

package com.mitrais.bootcamp.model;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class User {
	private String fullName, alamat, username, password;
	private Date birthdate;
	private int pin_try, login_status, block_status;
	
	public User(String fullName, String alamat, String username, String password, Date birthdate) {
		this.fullName = fullName;
		this.alamat = alamat;
		this.username = username;
		Base64 base64Encoder = new Base64();
		this.password = base64Encoder.encodeAsString(password.getBytes());
		this.birthdate = birthdate;
		this.pin_try=0;
		this.login_status = 0;
		this.block_status = 0;
	}
	
	public User() {}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		Base64 base64Encoder = new Base64();
		return new String(base64Encoder.decode(password.getBytes()));
	}
	
	public String getPasswordWithoutDecode() {
		return password;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public int getPin_try() {
		return pin_try;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		Base64 base64Encoder = new Base64();
		this.password = base64Encoder.encodeAsString(password.getBytes());
	}
	
	public void setPasswordWithoutEncode(String password) {
		this.password = password;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setPin_try(int pin_try) {
		this.pin_try = pin_try;
	}

	public int getLogin_status() {
		return login_status;
	}

	public int getBlock_status() {
		return block_status;
	}

	public void setLogin_status(int login_status) {
		this.login_status = login_status;
	}

	public void setBlock_status(int block_status) {
		this.block_status = block_status;
	}
	
}

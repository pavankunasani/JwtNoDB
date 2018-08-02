package com.spring.jwt.model;

public class ApplicationUser {

//	private long id;
	private String username;
	private String password;

	public ApplicationUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	

	/*public ApplicationUser(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}*/

	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
	}


/*	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

*/	public String getUsername() {
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

}

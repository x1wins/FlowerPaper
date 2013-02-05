package net.changwoo.x1wins.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Signin {

	
//	@AssertFalse(message = "{location}")
	@NotEmpty
    @Size(min = 1, max = 20)
    private String userid;
    
	@NotEmpty(message = "Password must not be blank.")
    @Size(min = 1, max = 20)
    private String password;
	
	private String username;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
}

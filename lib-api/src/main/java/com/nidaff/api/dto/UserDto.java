package com.nidaff.api.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends ADto {

	private List<RoleDto> roles = new ArrayList<>();
	
	private List<HistoryDto> histories = new ArrayList<>();

	private String lastName;

	private String firstName;
	
	private String email;

	private String login;
	
	private String password;

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<HistoryDto> getHistories() {
		return histories;
	}

	public void setHistories(List<HistoryDto> histories) {
		this.histories = histories;
	}
}
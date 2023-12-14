package ru.terentyev.EffectiveMobile.entities.jwt;

import jakarta.validation.constraints.Size;

public class LoginRequest {
	private String username;
	private String email;
	@Size(min = 5, message = "Пароль должен содержать минимум 5 символов")
	private String password;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

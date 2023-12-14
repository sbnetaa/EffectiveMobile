package ru.terentyev.EffectiveMobile.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "people")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@Email
	private String email;
	@Size(min = 5, message = "Пароль должен содержать минимум 5 символов")
	private String password;
	private transient String passwordConfirm;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "author")
	private List<Task> createdTasks;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "executor")
	private List<Task> executableTasks;
	@CreatedDate
	private LocalDateTime registrationDate;

	
	public Person(){}

	
	
	
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}




	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}




	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public List<Task> getCreatedTasks() {
		return createdTasks;
	}


	public void setCreatedTasks(List<Task> createdTasks) {
		this.createdTasks = createdTasks;
	}


	public List<Task> getExecutableTasks() {
		return executableTasks;
	}


	public void setExecutableTasks(List<Task> executableTasks) {
		this.executableTasks = executableTasks;
	}
	
	
}

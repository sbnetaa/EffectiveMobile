package ru.terentyev.EffectiveMobile.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Комментарий не может быть пустым")
	private String body;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	private Task task;
	private transient long taskId;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Person author;
	@CreatedDate
	private LocalDateTime createdAt;
	private LocalDateTime editedAt;
	
	public Comment(){}
	
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public LocalDateTime getEditedAt() {
		return editedAt;
	}



	public void setEditedAt(LocalDateTime editedAt) {
		this.editedAt = editedAt;
	}



	public long getTaskId() {
		return taskId;
	}



	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
	
	
	
}

package ru.terentyev.EffectiveMobile.entities;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Заголовок не может быть пустым")
	private String title;
	@NotBlank(message = "Описание не может быть пустым")
	private String description;
	@NotNull(message = "У задачи должен быть статус")
	@Enumerated(EnumType.STRING)
	private Status status;
	@NotNull(message = "У задачи должен быть приоритет")
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Person author;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "executor_id", referencedColumnName = "id")
	private Person executor;
	@NotNull(message = "У задачи должен быть исполнитель")
	private transient long executorId;
	@OneToMany(mappedBy = "task")
	private List<Comment> comments;
	@CreatedDate
	private LocalDateTime createdAt;
	private LocalDateTime editedAt;
	
	public Task(){}
	

	public long getExecutorId() {
		return executorId;
	}

	public void setExecutorId(long executorId) {
		this.executorId = executorId;
	}


	public LocalDateTime getEditedAt() {
		return editedAt;
	}


	public void setEditedAt(LocalDateTime editedAt) {
		this.editedAt = editedAt;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Person getExecutor() {
		return executor;
	}

	public void setExecutor(Person executor) {
		this.executor = executor;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}



	public enum Status{
		AWAITING("В ожидании"),
		PROCESSING("В процессе"),
		COMPLETED("Завершено");
		
		private String translation;

		private Status(String translation) {
			this.translation = translation;
		}

		public String getTranslation() {
			return translation;
		}		
		
	}
	
	public enum Priority{
		LOW("Низкий"),
		MEDIUM("Средний"),
		HIGH("Высокий");
		
		private String translation;

		private Priority(String translation) {
			this.translation = translation;
		}

		public String getTranslation() {
			return translation;
		}	
		
	}

}

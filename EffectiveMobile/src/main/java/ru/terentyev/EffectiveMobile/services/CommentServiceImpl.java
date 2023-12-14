package ru.terentyev.EffectiveMobile.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.terentyev.EffectiveMobile.entities.Comment;
import ru.terentyev.EffectiveMobile.entities.Person;
import ru.terentyev.EffectiveMobile.entities.Task;
import ru.terentyev.EffectiveMobile.exceptions.PersonNotFoundException;
import ru.terentyev.EffectiveMobile.exceptions.TaskNotFoundException;
import ru.terentyev.EffectiveMobile.repositories.CommentRepository;
import ru.terentyev.EffectiveMobile.repositories.PersonRepository;
import ru.terentyev.EffectiveMobile.repositories.TaskRepository;
import ru.terentyev.EffectiveMobile.security.PersonDetails;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
	private CommentRepository commentRepository;
	private TaskRepository taskRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository) {
		super();
		this.commentRepository = commentRepository;
		this.taskRepository = taskRepository;
	}
	
	public Page<Comment> findByTask(long id, int page){
		return commentRepository.findByTask(id, PageRequest.of(page, 10, Sort.by("id")));
	}
	
	@Transactional(readOnly = false)
	public Comment save(Comment comment) {
		Task task = comment.getTask();
		if (task == null) {
		task = taskRepository.findById(comment.getTaskId()).orElseThrow(() -> new TaskNotFoundException("Task not found"));
		comment.setTask(task);
		} 
		if (comment.getCreatedAt() == null) comment.setCreatedAt(LocalDateTime.now());
		comment.setEditedAt(LocalDateTime.now());
		task.getComments().add(comment);
		taskRepository.save(task);
		commentRepository.save(comment);
		return comment;
		
	}
	
	
	public Comment findById(long id) {
		return commentRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(long id) {
		commentRepository.deleteById(id);
	}
	
	public Page<Comment> findByAuthor(long id, int page) {
		return commentRepository.findByAuthor_id(id, PageRequest.of(page, 10, Sort.by("id")));
	}
}

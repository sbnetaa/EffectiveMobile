package ru.terentyev.EffectiveMobile.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.terentyev.EffectiveMobile.entities.Task;
import ru.terentyev.EffectiveMobile.exceptions.PersonNotFoundException;
import ru.terentyev.EffectiveMobile.exceptions.TaskNotFoundException;
import ru.terentyev.EffectiveMobile.repositories.PersonRepository;
import ru.terentyev.EffectiveMobile.repositories.TaskRepository;

@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	
	private TaskRepository taskRepository;
	private PersonRepository personRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository, PersonRepository personRepository) {
		super();
		this.taskRepository = taskRepository;
		this.personRepository = personRepository;
	}
	
	public Page<Task> findAll(int page) {
		return taskRepository.findAll(PageRequest.of(page, 5, Sort.by("status").descending().and(Sort.by("id").descending())));
	}

	public Task findById(long id) {
		return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Задача с таким ID не найдена"));
	}
	
	@Transactional(readOnly = false)
	public Task save(Task task) {
		if (task.getCreatedAt() == null) task.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
		task.setEditedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
		long executorId = task.getExecutorId();
		if (executorId != 0) task.setExecutor(personRepository.findById(executorId)
				.orElseThrow(() -> new PersonNotFoundException("Person not found")));
		return taskRepository.save(task);
	}
	
	@Transactional(readOnly = false)
	public void deleteById(long id) {
		taskRepository.deleteById(id);
	}
	
	public Page<Task> findByExecutor(long id, int page){
		return taskRepository.findByExecutor(personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Person with this ID not found")), PageRequest.of(page, 5));
		
	}
	
	public Page<Task> findByAuthor(long id, int page){
		return taskRepository.findByAuthor(personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException("Person with this ID not found")), PageRequest.of(page, 5));
		
	}
}

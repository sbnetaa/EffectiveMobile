package ru.terentyev.EffectiveMobile.services;

import org.springframework.data.domain.Page;
import ru.terentyev.EffectiveMobile.entities.Task;


public interface TaskService {
	
	Page<Task> findAll(int page);
	Task findById(long id);
	Task save(Task task);
	void deleteById(long id);
	Page<Task> findByExecutor(long id, int page);
	Page<Task> findByAuthor(long id, int page);
}

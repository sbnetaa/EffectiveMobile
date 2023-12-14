package ru.terentyev.EffectiveMobile.repositories;

import org.springframework.stereotype.Repository;

import ru.terentyev.EffectiveMobile.entities.Person;
import ru.terentyev.EffectiveMobile.entities.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	Page<Task> findAll(Pageable pageable);
	Page<Task> findByExecutor(Person executor, Pageable pageable);
	Page<Task> findByAuthor(Person author, Pageable pageable);
}

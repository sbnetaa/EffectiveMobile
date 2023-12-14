package ru.terentyev.EffectiveMobile.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.terentyev.EffectiveMobile.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query(value = "SELECT * from comments WHERE task_id = ?1", nativeQuery = true)
	Page<Comment> findByTask(long id, Pageable pageable);
	@Query(value = "SELECT * from comments WHERE author_id = ?1", nativeQuery = true)
	List<Comment> findByAuthor(long id);
	Page<Comment> findByAuthor_id(long id, Pageable pageable);
	@Modifying
	@Query(value = "DELETE FROM comments WHERE id = ?1", nativeQuery = true)
	void deleteById(long id);
}

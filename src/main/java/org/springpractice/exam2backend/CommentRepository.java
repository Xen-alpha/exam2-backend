package org.springpractice.exam2backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springpractice.exam2backend.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

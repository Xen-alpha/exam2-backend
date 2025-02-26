package org.springpractice.exam2backend;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springpractice.exam2backend.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

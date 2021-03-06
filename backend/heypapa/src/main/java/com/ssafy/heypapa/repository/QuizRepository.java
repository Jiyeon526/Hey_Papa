package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Quiz;
import com.ssafy.heypapa.entity.QuizTypeEnum;
import com.ssafy.heypapa.entity.User;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
//	Optional<Quiz> findByIdAndImg(Long id);
//	List<Quiz> findByUser(User user);
	
}

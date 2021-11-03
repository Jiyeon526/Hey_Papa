package com.ssafy.heypapa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.heypapa.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{
	public Optional<Article> findById(Long id);
	public List<Article> findAll();
}

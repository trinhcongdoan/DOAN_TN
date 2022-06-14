package com.granduation.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granduation.Entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

}

package com.unamenteblog.post_service.repository;

import com.unamenteblog.post_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

     List<Post> findTop3ByOrderByCreateAtDesc();
     List<Post> findAllByOrderByCreateAtDesc();
}

package com.unamenteblog.post_service.service;

import com.unamenteblog.post_service.model.Post;
import com.unamenteblog.post_service.repository.IPostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    IPostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreateAtDesc();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id).or(Optional::empty);
    }

    public List<Post> recentPosts() {
        return postRepository.findTop3ByOrderByCreateAtDesc();
    }
}

package com.unamenteblog.post_service.controller;

import com.unamenteblog.post_service.domain.DataCreatePost;
import com.unamenteblog.post_service.domain.DataResponsePost;
import com.unamenteblog.post_service.model.Post;
import com.unamenteblog.post_service.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${variable.ruta}/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService =  postService;
    }

    @PostMapping
    public ResponseEntity<DataCreatePost> createPost(@RequestBody DataCreatePost dataCreatePost) {
        Post post1 = postService.save(new Post(dataCreatePost));
        var dataCreatePost1 = new DataCreatePost(post1.getTitle(), post1.getSubtitle(), post1.getContent(), post1.getAuthor());
        return ResponseEntity.ok(dataCreatePost1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponsePost> getPost(@PathVariable("id") Long id) {
        Post post = postService.findById(id).isPresent() ? postService.findById(id).get() : null;

        if (Objects.nonNull(post)) {
            var dataResponse = new DataResponsePost(post.getId(), post.getTitle(),post.getSubtitle(),
                    post.getContent(), post.getAuthor(), String.valueOf(post.getCreateAt()), String.valueOf(post.getUpdateAt()));
            return ResponseEntity.ok(dataResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/recentPosts")
    public ResponseEntity<List<Post>> getRecentPost() {
        return ResponseEntity.ok(postService.recentPosts());
    }

}

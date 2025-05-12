package com.unamenteblog.post_service.model;

import com.unamenteblog.post_service.domain.DataCreatePost;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity(name = "POSTS")
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    @Description("Reference Id to the User")
    private Long author;
    private Timestamp createAt;
    private Timestamp updateAt;

    public Post(DataCreatePost dataCreatePost) {
        this.title = dataCreatePost.title();
        this.content = dataCreatePost.content();
        this.author = dataCreatePost.author();
        this.subtitle = dataCreatePost.subtitle();
    }

    public Post(){

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getAuthor() {
        return author;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    @PrePersist
    private void setCreateAt() {

        this.createAt = new Timestamp(System.currentTimeMillis());    }

    @PreUpdate
    private void setUpdateAt(){
        this.updateAt = new Timestamp(System.currentTimeMillis());    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}

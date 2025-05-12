package com.unamenteblog.user_service.model;

import com.unamenteblog.user_service.domain.DataCreateUserGoogle;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Entity(name = "USER")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Description("Null if comes from google")
    private String password;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String bio;
    private Timestamp createAt;
    private String authProvider;
    @Column(nullable = false)
    private String googleId;
    private String picture;

    public User(DataCreateUserGoogle dataCreatePost) {
        this.name = dataCreatePost.name();
        this.email = dataCreatePost.email();
        this.password = dataCreatePost.password();
        this.authProvider = dataCreatePost.authProvider();
        this.googleId = dataCreatePost.googleId();
        this.picture = dataCreatePost.picture();
    }

    public User(){

    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }



    @PrePersist
    private void setCreateAt() {
        this.createAt = new Timestamp(System.currentTimeMillis());    }


}

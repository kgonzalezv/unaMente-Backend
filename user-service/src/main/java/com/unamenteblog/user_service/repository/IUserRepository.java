package com.unamenteblog.user_service.repository;

import com.unamenteblog.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
   Optional<User> findUserByGoogleId(String googleId);
}

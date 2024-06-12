package com.auth.jwt.repositories;

import com.auth.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}

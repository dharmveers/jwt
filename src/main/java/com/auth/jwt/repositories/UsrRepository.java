package com.auth.jwt.repositories;

import com.auth.jwt.model.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<Usr,Integer> {
    public Usr findByEmail(String email);
}

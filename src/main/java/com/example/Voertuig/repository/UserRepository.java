package com.example.Voertuig.repository;

import com.example.Voertuig.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

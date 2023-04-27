package com.technichalgarden.bloodbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technichalgarden.bloodbank.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}

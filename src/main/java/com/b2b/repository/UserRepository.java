package com.b2b.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b2b.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

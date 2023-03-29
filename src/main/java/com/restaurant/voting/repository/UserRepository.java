package com.restaurant.voting.repository;

import com.restaurant.voting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User u LEFT JOIN FETCH u.roles")
    List<User> getAll();

    User findUserByEmail(String email);
}

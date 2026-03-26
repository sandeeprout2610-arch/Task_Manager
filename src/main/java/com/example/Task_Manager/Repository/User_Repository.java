package com.example.Task_Manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Task_Manager.Model.User;

public interface User_Repository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
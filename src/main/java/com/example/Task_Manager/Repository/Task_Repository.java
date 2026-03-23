package com.example.Task_Manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Task_Manager.Model.Task;

public interface Task_Repository extends JpaRepository<Task, Long> {

    List<Task> findByUsername(String username);

}
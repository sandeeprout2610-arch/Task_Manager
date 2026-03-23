package com.example.Task_Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.Task_Manager.Model.Task;
import com.example.Task_Manager.Model.User;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Repository.User_Repository;
import com.example.Task_Manager.Token_Utill;

@RestController
@RequestMapping("/task")
public class Task_Controller {

    @Autowired
    private Task_Repository taskRepo;

    @Autowired
    private User_Repository userRepo;

    @Autowired
    private Token_Utill tokenUtil;

    @PostMapping("/add")
    public String createTask(
            @RequestBody Task task,
            @RequestHeader("Authorization") String token) {

        String username = tokenUtil.validateToken(token);

        if (username == null) {
            return "Unauthorized";
        }

        task.setUsername(username);
        taskRepo.save(task);

        return "Task Added Successfully";
    }

    @GetMapping("/all")
    public Object getTasks(
            @RequestHeader("Authorization") String token) {

        String username = tokenUtil.validateToken(token);

        if (username == null) {
            return "Unauthorized";
        }

        return taskRepo.findByUsername(username);
    }

    @GetMapping("/admin")
    public Object adminTasks(
            @RequestHeader("Authorization") String token) {

        String username = tokenUtil.validateToken(token);

        if (username == null) {
            return "Unauthorized";
        }

        User user = userRepo.findByUsername(username);

        if (user == null) {
            return "Unauthorized";
        }

        if (!user.getRole().equals("ADMIN")) {
            return "Access Denied";
        }

        return taskRepo.findAll();
    }
}
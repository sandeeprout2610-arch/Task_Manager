package com.example.Task_Manager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Task_Manager.Repository.User_Repository;
import com.example.Task_Manager.Repository.Task_Repository;
import com.example.Task_Manager.Token_Utill;
import com.example.Task_Manager.Model.User;

@RestController
@RequestMapping("/auth")
public class Auth_Controller {

    @Autowired
    private User_Repository repo;

    @Autowired
    private Task_Repository taskRepo; 

    @Autowired
    private Token_Utill tokenUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        repo.save(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = repo.findByUsername(user.getUsername());

        if (dbUser != null &&
            dbUser.getPassword().equals(user.getPassword())) {

            return tokenUtil.generateToken(user.getUsername());
        }

        return "Invalid Username or Password";
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {

        User user = repo.findByUsername(username);

        if (user == null) {
            return "User not found";
        }

        taskRepo.deleteAll(taskRepo.findByUsername(username));

        repo.delete(user);

        return "User and all tasks deleted successfully";
    }
    
}
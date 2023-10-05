package com.example.spring_data_parttwo;

import com.example.spring_data_parttwo.Models.User;
import com.example.spring_data_parttwo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
   private UserService userService;
    @Override
    public void run(String... args) throws Exception {

        User first = new User("Ivan",27);
        userService.registerUser(first);
        User second = new User("Ivan",27);
        userService.registerUser(second);
    }
}

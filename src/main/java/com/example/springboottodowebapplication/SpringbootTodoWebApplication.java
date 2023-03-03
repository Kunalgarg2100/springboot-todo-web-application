package com.example.springboottodowebapplication;

import com.example.springboottodowebapplication.app.model.Todo;
import com.example.springboottodowebapplication.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class SpringbootTodoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTodoWebApplication.class, args);
    }

}

package com.example.springboottodowebapplication.app.service;

import com.example.springboottodowebapplication.app.model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private void addTodo( Todo todo ){
        todos.add(todo);
    }

    public static List<Todo> getTodos() {
        return todos;
    }

    public static void setTodos(List<Todo> todos) {
        TodoService.todos = todos;
    }

};

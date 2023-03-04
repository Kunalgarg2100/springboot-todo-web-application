package com.example.springboottodowebapplication.app.service;

import com.example.springboottodowebapplication.app.model.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    public static int getId(){
        return todos.size()+1;
    }

    public TodoService() {
        todos.add(new Todo(getId(), "KG", "Amazon", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(getId(), "KG", "Google", LocalDate.now().plusYears(1), false));
    }

    public void addTodo( String username, String description, LocalDate targetDate, boolean done  ){
        Todo todo = new Todo(getId(),username, description, targetDate,done);
        todos.add(todo);
    }

    public void deleteTodoById( int id ){
        todos.removeIf(todo -> todo.getId() == id );
    }

    public static List<Todo> getTodos() {
        return todos;
    }

    public static void setTodos(List<Todo> todos) {
        TodoService.todos = todos;
    }

    public Todo findById(int id) {
        return todos.stream().filter(todo -> todo.getId()==id).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
};

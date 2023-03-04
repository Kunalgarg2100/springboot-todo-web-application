package com.example.springboottodowebapplication.service;

import com.example.springboottodowebapplication.model.Todo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

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

    public List<Todo> findByUsername(String username) {
        return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(username)).toList();
    }

    public void updateTodo(@Valid Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
};

package com.example.springboottodowebapplication.service;

import com.example.springboottodowebapplication.model.Todo;
import com.example.springboottodowebapplication.repository.TodoJPARepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private TodoJPARepository todoJPARepository;

    public static int getId(){
        return todos.size()+1;
    }

    public TodoService(TodoJPARepository todoJPARepository) {
        this.todoJPARepository = todoJPARepository;
    }

    public void addTodo( Todo todo  ){
        System.out.println(todo);
        todoJPARepository.save(todo);
    }

    public void deleteTodoById( int id ){
        todoJPARepository.deleteById(id);
    }

    public static List<Todo> getTodos() {
        return todos;
    }

    public static void setTodos(List<Todo> todos) {
        TodoService.todos = todos;
    }

    public Todo findById(int id) {
        return todoJPARepository.findById(id).get();
    }

    public List<Todo> findByUsername(String username) {
        return todoJPARepository.findByUsername(username);
    }

    public void updateTodo(Todo todo) {
        System.out.println(todo);
        todoJPARepository.save(todo);
    }
};

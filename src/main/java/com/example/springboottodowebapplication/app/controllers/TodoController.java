package com.example.springboottodowebapplication.app.controllers;

import com.example.springboottodowebapplication.app.model.Todo;
import com.example.springboottodowebapplication.app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listaddTodos(ModelMap model){
        todoService.setTodos(
                Arrays.asList(
                        new Todo(1, "KG", "Amazon", LocalDate.now().plusYears(1), false),
                        new Todo(2, "KG", "Google", LocalDate.now().plusYears(2), false)
                )
        );
        List<Todo> todos = todoService.getTodos();
        model.put("todos", todos);
        return "listtodos";
    }
}

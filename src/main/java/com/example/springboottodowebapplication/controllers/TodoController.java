package com.example.springboottodowebapplication.controllers;

import com.example.springboottodowebapplication.model.Todo;
import com.example.springboottodowebapplication.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listTodos(ModelMap model){
        String username = getLoggedinUsername();
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listtodos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodos(@RequestParam int id){
        todoService.deleteTodoById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo",method = RequestMethod.GET)
    public String showUpdateTodos(@RequestParam int id, ModelMap model){
        Todo todo = todoService.findById(id);
        model.put("todo",todo);
        return "add-todo";
    }

    @RequestMapping(value="update-todo",method = RequestMethod.POST)
    public String updateTodos(@ModelAttribute("todo") Todo todo ){
        String username = getLoggedinUsername();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showAddNewTodoPage(ModelMap model){
        String username = getLoggedinUsername();
        Todo todo = new Todo( username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo",todo);
        return "add-todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo( @ModelAttribute("todo") Todo todo ){
        String username = getLoggedinUsername();
        todo.setUsername(username);
        todoService.addTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

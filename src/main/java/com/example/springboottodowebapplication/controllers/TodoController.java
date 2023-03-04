package com.example.springboottodowebapplication.controllers;

import com.example.springboottodowebapplication.model.Todo;
import com.example.springboottodowebapplication.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println(username);
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
        model.addAttribute("todo",todo);
        return "add-todo";
    }

    @RequestMapping(value="update-todo",method = RequestMethod.POST)
    public String updateTodos(ModelMap model, @Valid Todo todo, BindingResult result){
        if( result.hasErrors()){
            return "add-todo";
        }
        String username = ( String ) model.get("name");
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showAddNewTodoPage(ModelMap model){
        String username = ( String ) model.get("name");
        Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo",todo);
        return "add-todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result ){
        if( result.hasErrors()){
            return "add-todo";
        }
        String username = ( String ) model.get("name");
        String description = todo.getDescription();
        LocalDate targetDate = todo.getTargetDate();
        todoService.addTodo(username , description, targetDate, false);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

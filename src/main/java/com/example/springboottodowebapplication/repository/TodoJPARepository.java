package com.example.springboottodowebapplication.repository;

import com.example.springboottodowebapplication.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoJPARepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByUsername( String username );

}

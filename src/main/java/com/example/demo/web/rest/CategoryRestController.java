package com.example.demo.web.rest;

import com.example.demo.model.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins ="https://react-181534.herokuapp.com/")
@RequestMapping("/api/categories")
public class CategoryRestController {
    @GetMapping
    public List<Category> findAllCats(){
        return Arrays.stream(Category.values()).toList();
    }
}

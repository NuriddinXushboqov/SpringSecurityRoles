/*
 * Copyright (C) 2022 ALanTech
 * Project: BasicCrudMakerByPostgresql
 * Info: This project makes basic CRUD(create, read, update, delete) application from database,
 * it helps to make basic APIs.
 * version: 1.0.1 (sept 18, 2022)
 * Author: Abdullokh Makhmudov
 * http://t.me/abdullokh_makhmudov
 * Java learned in Najot Ta'lim by Ismoil Miraliyev teacher.
 */

package uz.najot.springsecurityroles.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.najot.springsecurityroles.message.ResMessage;

import uz.najot.springsecurityroles.model.Product;
import uz.najot.springsecurityroles.service.ProductService;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResMessage saveClass(@RequestBody Product product){
        return productService.create(product);
    }
    
    @GetMapping
    public ResMessage getAll(){
        return productService.getAll();
    }

    @GetMapping("{id}")
    public ResMessage getById(@PathVariable Integer id){
        return productService.getById(id);
    }

    @PutMapping()
    public ResMessage update(@RequestBody Product product){
        return productService.edit(product);
    }

    @PutMapping("{id}")
    public ResMessage delete(@PathVariable Integer id){
        return productService.remove(id);
    }
}

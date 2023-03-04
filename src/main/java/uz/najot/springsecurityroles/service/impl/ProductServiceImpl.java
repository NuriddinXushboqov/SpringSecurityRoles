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

package uz.najot.springsecurityroles.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import uz.najot.springsecurityroles.message.ResMessage;
import uz.najot.springsecurityroles.model.Product;
import uz.najot.springsecurityroles.service.ProductService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResMessage create(Product product) {
        int update = jdbcTemplate.update("insert into product () VALUES ()" );
        return update>0 ? ResMessage.getSuccess() : new ResMessage(101,"not created",null);
    }

    @Override
    public ResMessage getAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from product where is_active = true");
        return ResMessage.getSuccess(maps);
    }

    @Override
    public ResMessage getById(Integer id) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from product where id = ? and is_active = true", id);
        return ResMessage.getSuccess(maps);
    }

    @Override
    public ResMessage edit(Product product) {
        int update = jdbcTemplate.update("update product  where id = ?", product.getId());
        return update>0 ? ResMessage.getSuccess() : new ResMessage(102,"not updated",null);
    }

    @Override
    public ResMessage remove(Integer id) {
        int update = jdbcTemplate.update("update product set is_active = false where id = ?", id);
        return update>0 ? ResMessage.getSuccess() : new ResMessage(103,"not deleted",null);
    }
}

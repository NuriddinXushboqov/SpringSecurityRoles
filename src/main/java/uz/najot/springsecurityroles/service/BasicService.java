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

package uz.najot.springsecurityroles.service;

import uz.najot.springsecurityroles.message.ResMessage;

public interface BasicService<T> {
    ResMessage create(T t);
    ResMessage getAll();
    ResMessage getById(Integer id);
    ResMessage edit(T t);
    ResMessage remove(Integer id);
}

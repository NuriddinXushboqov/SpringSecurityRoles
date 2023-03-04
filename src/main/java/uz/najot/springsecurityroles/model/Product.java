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

package uz.najot.springsecurityroles.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private Integer id;
  private String name;
  private Double price;
  private boolean isActive;
}

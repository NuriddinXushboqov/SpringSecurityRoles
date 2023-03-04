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

package uz.najot.springsecurityroles.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResMessage {
    private int statusCode;
    private String message;
    private Object data;

    public static ResMessage getSuccess(){
        return new ResMessage(0,"ok",null);
    }
    public static ResMessage getSuccess(Object data){
        return new ResMessage(0,"ok",data);
    }
    public static ResMessage errorMessage(Integer statusCode, String message){
        return new ResMessage(statusCode, message, null);
    }
}

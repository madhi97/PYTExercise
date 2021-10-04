package com.pyt.exercise.service;


import com.pyt.exercise.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    public Object getUserById(int user_Id){

       try {
           return namedParameterJdbcTemplate.queryForObject(
                   "select * from userdetails where user_id = :user_id;",
                   new MapSqlParameterSource("user_id", user_Id), new UserMapper());
       }
       catch (EmptyResultDataAccessException e){
           e.printStackTrace();
           return "No user available";
       }
       catch (Exception e){
           e.printStackTrace();
           return "unexpected Error";
       }

    }

    public List<UserModel> getUsersByTier(String tier){

        return namedParameterJdbcTemplate.query("select * from userdetails where tier = :tier;",
                new MapSqlParameterSource("tier",tier),
                     new UserMapper());
    }

    public void insertUser(UserModel user){

        namedParameterJdbcTemplate.update("Insert Into userdetails (user_id,tier,coins,referrer_id,total_amount_spent) VALUES (:user_id,:tier,:coins,:referrer_id,:total_amount_spent);",
                new MapSqlParameterSource().addValue("user_id", user.getUser_Id()).addValue("tier", user.getTier())
                        .addValue("coins", user.getCoins()).addValue("referrer_id", user.getReferrer_Id())
                        .addValue("total_amount_spent", user.getTotal_amount_spent()));
    }


}

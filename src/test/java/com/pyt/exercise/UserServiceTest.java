package com.pyt.exercise;

import com.pyt.exercise.model.UserModel;
import com.pyt.exercise.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService userObj;

    @Test
    @DisplayName("get User by Id")
    void getUserById(){


        Assertions.assertEquals( ((UserModel)userObj.getUserById(1)).getUser_Id(),1);

    }

    @Test
    @DisplayName("get User by Tier")
    void getUserByTier(){

        Assertions.assertEquals( (userObj.getUsersByTier("Gold")).get(0).getUser_Id(),1);
    }

    @Test
    @DisplayName("Insert User")
    void  insertUserTest(){

        UserModel user = new UserModel(2,"Silver",10,0,1000);
        userObj.insertUser(user);
        Assertions.assertEquals( ((UserModel)userObj.getUserById(2)).getUser_Id(),2);

    }

}

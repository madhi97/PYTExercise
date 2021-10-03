package com.pyt.exercise.service;


import java.sql.ResultSet;
import java.sql.SQLException;


import com.pyt.exercise.model.UserModel;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet rs, int arg1) throws SQLException {
        UserModel user = new UserModel();
        user.setUser_Id(rs.getInt("user_id"));
        user.setTier(rs.getString("tier"));
        user.setCoins(rs.getInt("coins"));
        user.setReferrer_Id(rs.getInt("referrer_id"));
        user.setTotal_amount_spent(rs.getInt("total_amount_spent"));
        return user;
    }
}
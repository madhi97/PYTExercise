package com.pyt.exercise.service;

import com.pyt.exercise.model.RoomBookingDetailsModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomBookingDetailsMapper implements RowMapper<RoomBookingDetailsModel> {

    @Override
    public RoomBookingDetailsModel mapRow(ResultSet rs, int arg1) throws SQLException {
        RoomBookingDetailsModel roomObj = new RoomBookingDetailsModel();
        roomObj.setRoom_id(rs.getString("room_id"));
        roomObj.setMax_adults(rs.getInt("max_adults"));
        roomObj.setMax_children(rs.getInt("max_children"));
        roomObj.setMax_child_age(rs.getInt("max_child_age"));
        roomObj.setFrom_date(rs.getDate("from_date"));
        roomObj.setTo_date(rs.getDate("to_date"));
        roomObj.setBase_room_price(rs.getFloat("base_room_price"));
        roomObj.setExtra_adult(rs.getInt("extra_adult"));
        roomObj.setExtra_child(rs.getInt("extra_child"));


        return roomObj;
    }
}

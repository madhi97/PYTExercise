package com.pyt.exercise.service;


import com.pyt.exercise.model.BookingModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingMapper implements RowMapper<BookingModel> {
    @Override
    public BookingModel mapRow(ResultSet rs, int arg1) throws SQLException {
        BookingModel booking = new BookingModel();
        booking.setTrip_Id(rs.getInt("trip_id"));
        booking.setUser_Id(rs.getInt("user_id"));
        booking.setStart_Date(rs.getString("start_date"));
        booking.setEnd_Date(rs.getString("end_date"));
        booking.setReferrer_Id(rs.getInt("referrer_id"));
        booking.setAmount_Spent(rs.getInt("amount_spent"));
        booking.setTrip_Type(rs.getString("trip_type"));
        booking.setTrip_status(rs.getString("trip_status"));
        booking.setIs_Referral_Booking(rs.getInt("is_referral_booking"));

        return booking;
    }
}

package com.pyt.exercise.model;

import java.util.Date;

public class BookingModel {

    private  int trip_Id;
    private int user_Id;
    private String start_Date;
    private String end_Date;
    private int referrer_Id;
    private String trip_Type;
    private  String trip_status;
    private  long amount_Spent;
    private int is_Referral_Booking;

    public BookingModel() {

    }

    public BookingModel(int user_Id, String start_Date, String end_Date, int referrer_Id, String trip_Type, long amount_Spent) {

        this.user_Id = user_Id;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
        this.referrer_Id = referrer_Id;
        this.trip_Type = trip_Type;

        this.amount_Spent = amount_Spent;

    }

    public int getTrip_Id() {
        return trip_Id;
    }

    public void setTrip_Id(int trip_Id) {
        this.trip_Id = trip_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(String start_Date) {
        this.start_Date = start_Date;
    }

    public String getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(String end_Date) {
        this.end_Date = end_Date;
    }

    public int getReferrer_Id() {
        return referrer_Id;
    }

    public void setReferrer_Id(int referrer_Id) {
        this.referrer_Id = referrer_Id;
    }

    public String getTrip_Type() {
        return trip_Type;
    }

    public void setTrip_Type(String trip_Type) {
        this.trip_Type = trip_Type;
    }

    public String getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(String trip_status) {
        this.trip_status = trip_status;
    }

    public long getAmount_Spent() {
        return amount_Spent;
    }

    public void setAmount_Spent(long amount_Spent) {
        this.amount_Spent = amount_Spent;
    }

    public int getIs_Referral_Booking() {
        return is_Referral_Booking;
    }

    public void setIs_Referral_Booking(int is_Referral_Booking) {
        this.is_Referral_Booking = is_Referral_Booking;
    }


}

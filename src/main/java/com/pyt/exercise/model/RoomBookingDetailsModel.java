package com.pyt.exercise.model;

import java.util.Date;

public class RoomBookingDetailsModel {

    private String room_id;
    private int max_adults;
    private  int max_children;
    private int  max_child_age;
    private Date from_date;
    private Date to_date;
    private  float base_room_price;
    private  float extra_adult;
    private float extra_child;


    public RoomBookingDetailsModel() {

    }

    public RoomBookingDetailsModel(String room_id, int max_adults, int max_children, int max_child_age, Date from_date, Date to_date, float base_room_price, float extra_adult, float extra_child) {
        this.room_id = room_id;
        this.max_adults = max_adults;
        this.max_children = max_children;
        this.max_child_age = max_child_age;
        this.from_date = from_date;
        this.to_date = to_date;
        this.base_room_price = base_room_price;
        this.extra_adult = extra_adult;
        this.extra_child = extra_child;
    }

    public String getRoom_id() {
        return room_id;
    }

    public int getMax_adults() {
        return max_adults;
    }

    public int getMax_children() {
        return max_children;
    }

    public int getMax_child_age() {
        return max_child_age;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public float getBase_room_price() {
        return base_room_price;
    }

    public float getExtra_adult() {
        return extra_adult;
    }

    public float getExtra_child() {
        return extra_child;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public void setMax_adults(int max_adults) {
        this.max_adults = max_adults;
    }

    public void setMax_children(int max_children) {
        this.max_children = max_children;
    }

    public void setMax_child_age(int max_child_age) {
        this.max_child_age = max_child_age;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public void setBase_room_price(float base_room_price) {
        this.base_room_price = base_room_price;
    }

    public void setExtra_adult(float extra_adult) {
        this.extra_adult = extra_adult;
    }

    public void setExtra_child(float extra_child) {
        this.extra_child = extra_child;
    }

    @Override
    public String toString() {
        return "roomBookingDetailsModel{" +
                "room_id='" + room_id  +
                ", max_adults=" + max_adults +
                ", max_children=" + max_children +
                ", max_child_age=" + max_child_age +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                ", base_room_price=" + base_room_price +
                ", extra_adult=" + extra_adult +
                ", extra_child=" + extra_child +
                '}';
    }
}

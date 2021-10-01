package com.pyt.exercise.model;

import java.util.Date;
import java.util.List;

public class InputRoomModel {

    private String inputRoomId;
    private String checkIn;
    private  String checkOut;
    private  int adultCount;
    private List<Integer> childAge;

    public InputRoomModel() {
    }

    public InputRoomModel(String inputRoomId, String checkIn, String checkOut, int adultCount, List<Integer> childAge) {
        this.inputRoomId = inputRoomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.adultCount = adultCount;
        this.childAge = childAge;

    }

    public String getInputRoomId() {
        return inputRoomId;
    }

    public void setInputRoomId(String inputRoomId) {
        this.inputRoomId = inputRoomId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public List<Integer> getChildAge() {
        return childAge;
    }

    public void setChildAge(List<Integer> childAge) {
        this.childAge = childAge;
    }
}

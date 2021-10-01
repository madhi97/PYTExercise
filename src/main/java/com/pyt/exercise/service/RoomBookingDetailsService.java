package com.pyt.exercise.service;

import com.pyt.exercise.model.InputRoomModel;
import com.pyt.exercise.model.RoomBookingDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;


@Repository
public class RoomBookingDetailsService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public InputRoomModel inputConverter(String input){
        InputRoomModel inputModel= new InputRoomModel();
        ArrayList<Integer> childAge = new ArrayList<Integer>();
        String[] strArr = input.split(" ");
        inputModel.setInputRoomId(strArr[0]);
        inputModel.setCheckIn(strArr[1]);
        inputModel.setCheckOut(strArr[2]);
        inputModel.setAdultCount(Integer.parseInt(strArr[3]));
        for(String str : Arrays.copyOfRange(strArr,4,strArr.length-1)){
            childAge.add(Integer.parseInt(str));
        }
        inputModel.setChildAge(childAge);

        return inputModel;
    }

    public String getRoomRate(String input){

        InputRoomModel inputModel = inputConverter(input);

        try{
            getRoomDetails(inputModel.getInputRoomId(),inputModel.getCheckIn(),inputModel.getCheckOut());
        }
        catch(DataAccessException e){
            e.printStackTrace();
        return "-1";}

        return  "1";

    }

    public RoomBookingDetailsModel getRoomDetails(String room_id, String from_Date, String to_Date) throws DataAccessException {

        System.out.println(room_id + " " + from_Date + to_Date);
        return namedParameterJdbcTemplate.queryForObject("select room_id,max_adults,max_children,max_child_age,from_date,to_date,base_room_price,extra_adult,extra_child from roombookingdetails where room_id  = :room_id and from_date <= (PARSEDATETIME('" + from_Date + "' , 'dd-MMM-yy' ,  'en')) and to_date >= (PARSEDATETIME('" + to_Date + "' , 'dd-MMM-yy' ,  'en'));", new MapSqlParameterSource("room_id", room_id), new RoomBookingDetailsMapper());

    }






}

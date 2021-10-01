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
    {this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;}



    public String getRoomRate(String input){

        RoomBookingDetailsModel bookingDetailsModel;
        InputRoomModel inputModel;
        Float roomRate = 0.0F;
        try{
            inputModel = inputConverter(input);
            bookingDetailsModel = getRoomDetails(inputModel.getInputRoomId(),inputModel.getCheckIn(),inputModel.getCheckOut());

            if(totalAdultsCount(inputModel,bookingDetailsModel) > bookingDetailsModel.getMax_adults()
                    || (totalPeopleCount(inputModel) - totalAdultsCount(inputModel,bookingDetailsModel)) > bookingDetailsModel.getMax_children()){
                return "-1";
            }


        }
        catch(DataAccessException e){
            e.printStackTrace();
            return "-1";}
        catch (Exception e){
            e.printStackTrace();
            return "Unexpected Error";
        }

        return  roomRate.toString();

    }

    public RoomBookingDetailsModel getRoomDetails(String room_id, String from_Date, String to_Date) throws DataAccessException {

        System.out.println(room_id + " " + from_Date + to_Date);
        return namedParameterJdbcTemplate.queryForObject("select room_id,max_adults,max_children,max_child_age,from_date,to_date,base_room_price,extra_adult,extra_child from roombookingdetails where room_id  = :room_id and from_date <= (PARSEDATETIME('" + from_Date + "' , 'dd-MMM-yy' ,  'en')) and to_date >= (PARSEDATETIME('" + to_Date + "' , 'dd-MMM-yy' ,  'en'));", new MapSqlParameterSource("room_id", room_id), new RoomBookingDetailsMapper());

    }

    public InputRoomModel inputConverter(String input){
        InputRoomModel inputModel= new InputRoomModel();
        ArrayList<Integer> childAge = new ArrayList<Integer>();
        String[] strArr = input.split(" ");
        inputModel.setInputRoomId(strArr[0]);
        inputModel.setCheckIn(strArr[1]);
        inputModel.setCheckOut(strArr[2]);
        inputModel.setAdultCount(Integer.parseInt(strArr[3]));

        if(strArr.length>4){
            for(String str : Arrays.copyOfRange(strArr,4,strArr.length-1)){
                childAge.add(Integer.parseInt(str));
            }
        }

        inputModel.setChildAge(childAge);

        return inputModel;
    }

    public int totalAdultsCount(InputRoomModel inputModel, RoomBookingDetailsModel bookingDetails ){
        int totalAdults = inputModel.getAdultCount();

        for( int childAge : inputModel.getChildAge()){
            if(childAge > bookingDetails.getMax_child_age())
                totalAdults+=1;
        }
        return totalAdults;
    }

    public int totalPeopleCount(InputRoomModel inputModel){

        return  inputModel.getAdultCount() + inputModel.getChildAge().size();
    }

    public Float totalRate(InputRoomModel inputModel, RoomBookingDetailsModel bookingDetails ){
        int totalPeople = totalPeopleCount(inputModel);
        int totalAdults = totalAdultsCount(inputModel,bookingDetails);
        int totalChild = totalPeople - totalAdults;
        Float price = 0.0F;

        price = price + bookingDetails.getBase_room_price();

        if(totalAdults > 2){
            price = price + ((totalAdults - 2)*bookingDetails.getExtra_adult());
        }

        price = price + (totalChild* bookingDetails.getExtra_child());

        return price;

    }







}

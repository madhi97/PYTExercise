package com.pyt.exercise;


import com.pyt.exercise.service.RoomBookingDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RoomBookingServiceTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    RoomBookingDetailsService roomObj = new RoomBookingDetailsService();

    @Test
    @DisplayName("Room Available")
    void roomAvailableTest(){

        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R124 01-OCT-2021 03-OCT-2021 2"),"4000.00");
        //Since it is Amount returning with 2 decimal and datatype is mentioned as Float in document
    }

    @Test
    @DisplayName("Room Not Available on the given data")
    void  roomNotAvailable(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2023 03-OCT-2023 2"),"-1");

    }

    @Test
    @DisplayName("Invalid Room Id")
    void  invalidRoomId(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R12345 01-OCT-2021 03-OCT-2021 2"),"-1");

    }

    @Test
    @DisplayName("Max Adults crossed")
    void  maxAdultsCrossed(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 5"),"-1");

    }

    @Test
    @DisplayName("Max Child crossed")
    void  maxChildCrossed(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 2 4 2 5"),"-1");

    }

    @Test
    @DisplayName("grt Max Child age to adult")
    void  grtMaxChildAge(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 2 15 "),"3000.00");

    }

    @Test
    @DisplayName("grt 18 Child age to adult")
    void  grtChildAge(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 2 18 "),"3000.00");

    }

    @Test
    @DisplayName("Invalid From date")
    void  invalidFromDate(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCTr-2021 03-OCT-2021 2 5 "),"-1");

    }

    @Test
    @DisplayName("Invalid To date")
    void  invalidToDate(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCTr-2021 2 5 "),"-1");

    }
    @Test
    @DisplayName("Extra Adult")
    void  extraAdult(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 3"),"3000.00");

    }

    @Test
    @DisplayName("Extra Child")
    void  extraChild(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 3 4 3"),"3800.00");

    }

    @Test
    @DisplayName("Invalid adult count")
    void  invalidAdultCount(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 % 4 3"),"-1");

    }

    @Test
    @DisplayName("Invalid child age")
    void  invalidChildAge(){
        roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        Assertions.assertEquals(roomObj.getRoomRate("R123 01-OCT-2021 03-OCT-2021 3 * 3"),"-1");

    }
}

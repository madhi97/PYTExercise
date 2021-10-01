package com.pyt.exercise;

import com.pyt.exercise.service.RoomBookingDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void roomBookingServiceTest(){

		RoomBookingDetailsService roomObj = new RoomBookingDetailsService();
		roomObj.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
		System.out.println(roomObj.getRoomDetails("R123", "01-OCT-2021" , "03-OCT-2021").toString());


	}

}

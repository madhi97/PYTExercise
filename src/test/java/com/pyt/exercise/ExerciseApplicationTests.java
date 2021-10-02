package com.pyt.exercise;

import com.pyt.exercise.service.RoomBookingDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	void contextLoads() {
	}


}

package com.pyt.exercise.controller;

import com.pyt.exercise.service.RoomBookingDetailsService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/text")
public class RoomBookingDetailsController {

    @Autowired
    RoomBookingDetailsService roomBookingDetailsService;

    @PostMapping("/getRoomRate")
    public  String getRoomRate(@RequestBody String input) {
        return roomBookingDetailsService.getRoomRate(input);

    }
    }




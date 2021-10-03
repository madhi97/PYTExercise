package com.pyt.exercise.controller;

import com.pyt.exercise.model.BookingModel;
import com.pyt.exercise.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class BookingsController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/booking")
    public Object booking(@RequestBody BookingModel booking){

        return bookingService.makeBooking(booking);

    }

    @GetMapping("/cancelBooking/{trip_id}")
    public String cancelBooking(@PathVariable int trip_id){
        return bookingService.cancelBooking(trip_id);
    }

    @GetMapping("/completeBooking/{trip_id}")
    public String completeBooking(@PathVariable int trip_id){
        return bookingService.completeBooking(trip_id);
    }

    @GetMapping("/getTopReferrers")
    public  List<HashMap<String,Object>> getTopReferrers(){
        return bookingService.getTopReferrers();
    }

}

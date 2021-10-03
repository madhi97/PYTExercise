package com.pyt.exercise;

import com.pyt.exercise.model.BookingModel;
import com.pyt.exercise.model.UserModel;
import com.pyt.exercise.service.BookingService;
import com.pyt.exercise.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BookingServiceTest {


    @Autowired
    BookingService bookingObj;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("make booking")
    void makeBooking(){
        BookingModel booking = new BookingModel(5,"2021-10-01","2021-10-02",1,"Domestic",1000);
        Assertions.assertEquals( ((BookingModel) bookingObj.makeBooking(booking)).getTrip_status(),"Booked");
    }
    @Test
    @DisplayName("make booking with 0 as user id")
    void makeBookingWithInvalidUserId(){
        BookingModel booking = new BookingModel(0,"2021-10-01","2021-10-02",1,"Domestic",1000);
        Assertions.assertEquals(bookingObj.makeBooking(booking),"UserId is 0");
    }
    @Test
    @DisplayName("make booking with referral id as user id")
    void makeBookingWithReferralId(){
        BookingModel booking = new BookingModel(41,"2021-10-01","2021-10-02",41,"Domestic",1000);
        Assertions.assertEquals( ((BookingModel) bookingObj.makeBooking(booking)).getIs_Referral_Booking(),0);
    }

    @Test
    @DisplayName("Update Tier")
    void  updateTier(){
        BookingModel booking = new BookingModel(4,"2021-10-01","2021-10-03",21,"Domestic",252000);
        bookingObj.makeBooking(booking);
        bookingObj.updateTier();

        Assertions.assertEquals(((UserModel) userService.getUserById(4)).getTier(),"Gold");

    }

    @Test
    @DisplayName("Update Coins")
    void updateCoins(){
        BookingModel booking = new BookingModel(6,"2021-10-01","2021-10-03",22,"Domestic",252000);
        bookingObj.makeBooking(booking);
        bookingObj.updateTier();
        bookingObj.updateCoins();

        Assertions.assertEquals(((UserModel) userService.getUserById(22)).getCoins(),1000);

    }

    @Test
    @DisplayName("get top 10 referrers")
    void getTopRef(){
        BookingModel booking1 = new BookingModel(100,"2021-10-01","2021-10-03",1,"Domestic",252000);
        BookingModel booking2 = new BookingModel(101,"2021-10-01","2021-10-03",100,"Domestic",252000);
        BookingModel booking3 = new BookingModel(102,"2021-10-01","2021-10-03",100,"Domestic",252000);

        bookingObj.makeBooking(booking1);
        bookingObj.makeBooking(booking2);
        bookingObj.makeBooking(booking3);
        bookingObj.updateTier();
        bookingObj.updateCoins();

        Assertions.assertEquals(bookingObj.getTopReferrers().get(0).get("Referrals"),2);

    }

    @Test
    @DisplayName("Update Tier with Cancellation")
    void  updateTierWithCancellation(){
        BookingModel booking = new BookingModel(11,"2021-10-01","2021-10-03",23,"Domestic",252000);

        bookingObj.cancelBooking(((BookingModel)bookingObj.makeBooking(booking)).getTrip_Id());
        bookingObj.updateTier();

        Assertions.assertEquals(((UserModel) userService.getUserById(11)).getTier(),"Silver");

    }

    @Test
    @DisplayName("Update Coins with cancellation")
    void updateCoinsWithCancellation(){
        BookingModel booking = new BookingModel(13,"2021-10-01","2021-10-03",24,"Domestic",252000);
        bookingObj.cancelBooking(((BookingModel)bookingObj.makeBooking(booking)).getTrip_Id());
        bookingObj.updateTier();
        bookingObj.updateCoins();

        Assertions.assertEquals(((UserModel) userService.getUserById(24)).getCoins(),0);

    }



}

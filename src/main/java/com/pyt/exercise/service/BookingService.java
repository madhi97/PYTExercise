package com.pyt.exercise.service;

import com.pyt.exercise.model.BookingModel;
import com.pyt.exercise.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookingService {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    UserService userService;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String currentDate = formatter.format(date);

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Object makeBooking(BookingModel booking){
        int trip_id;

        try {
            if(booking.getUser_Id() <= 0){
                return "UserId is 0";
            }
            booking.setTrip_status("Booked");
            booking.setIs_Referral_Booking(checkReferralBooking(booking));
            if(booking.getIs_Referral_Booking() == 1){
                UserModel user = new UserModel();
                user.setUser_Id(booking.getUser_Id());
                user.setReferrer_Id(booking.getReferrer_Id());
                user.setTier("Silver");
                userService.insertUser(user);
            }
            trip_id = insertBookings(booking);

        }
        catch (Exception e){
            e.printStackTrace();
            return "UnExpected Error";

        }


        return getBookingById(trip_id);
    }

    public Integer insertBookings(BookingModel booking){

        KeyHolder holder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update("Insert Into bookings (user_id,start_date,end_date,referrer_id,trip_type,trip_status,amount_spent,is_referral_booking) VALUES (:user_id,:start_date,:end_date,:referrer_id,:trip_type,:trip_status,:amount_spent,:is_referral_booking);",
                new MapSqlParameterSource().addValue("user_id", booking.getUser_Id())
                        .addValue("start_date", booking.getStart_Date())
                        .addValue("end_date", booking.getEnd_Date())
                        .addValue("referrer_id", booking.getReferrer_Id())
                        .addValue("trip_type", booking.getTrip_Type()).
                        addValue("trip_status",booking.getTrip_status()).
                        addValue("amount_spent",booking.getAmount_Spent())
                        .addValue("is_referral_booking",booking.getIs_Referral_Booking()),
                holder, new String[] {"trip_id"});

        return (Integer) holder.getKey();
    }

    public BookingModel getBookingById(int trip_id){
        return namedParameterJdbcTemplate.queryForObject("select * from bookings where trip_id = :trip_id;",
                new MapSqlParameterSource("trip_id",trip_id),
                new BookingMapper());
    }

    public int checkReferralBooking (BookingModel booking)  {
        if(booking.getReferrer_Id() == 0 || booking.getReferrer_Id() == booking.getUser_Id()){
            return 0;
        }
        else if( isBookingExist(booking.getUser_Id())> 0)
            {return 0;}
        else if( ((UserModel) userService.getUserById(booking.getReferrer_Id())).getUser_Id() > 0)
            {return 1;}

        else{return 0;}

    }

    public Integer isBookingExist(int user_id){

        return namedParameterJdbcTemplate.queryForObject("select count(*) from bookings where user_id =:user_id",new MapSqlParameterSource("user_id",user_id), Integer.class);
    }

    public String cancelBooking(int trip_id){

       try{
        if(getBookingById(trip_id).getTrip_status().equalsIgnoreCase("Booked")) {
            namedParameterJdbcTemplate.update("update bookings set trip_status = 'Cancelled' where trip_id = :trip_id;",
                    new MapSqlParameterSource("trip_id", trip_id));
            return "Cancelled";
        }
        else if(getBookingById(trip_id).getTrip_status().equalsIgnoreCase("Completed")){
            return  "Cannot be Cancelled";
        }
        else
        {return "Unexpected Error";}}
        catch(Exception e)
        {   e.printStackTrace();
            return "Unexpected Error";}
    }

    public String completeBooking(int trip_id){

        try{
            if(getBookingById(trip_id).getTrip_status().equalsIgnoreCase("Booked")) {
                namedParameterJdbcTemplate.update("update bookings set trip_status = 'Completed' where trip_id = :trip_id;",
                        new MapSqlParameterSource("trip_id", trip_id));
                return "Completed";
            }
            else if(getBookingById(trip_id).getTrip_status().equalsIgnoreCase("Cancelled")){
                return  "Cannot be Completed";
            }
            else
            {return "Unexpected Error";}}
        catch(Exception e)
        {   e.printStackTrace();
            return "Unexpected Error";}
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void updateTier() {

        namedParameterJdbcTemplate.update("update bookings set trip_status = 'Completed' where end_date = :date and trip_status <> 'Cancelled';",
                new MapSqlParameterSource().addValue("date", currentDate));
        List<Integer> modifiedusers = getTierModifiedUsers();
        for(int user_id : modifiedusers){
            namedParameterJdbcTemplate
                    .update("update userdetails set total_amount_spent = (total_amount_spent + (select sum(amount_spent) from bookings where end_date = :date and user_id = :user_id and trip_status = 'Completed')) where user_id = :user_id",
                    new MapSqlParameterSource().addValue("date",currentDate).addValue("user_id", user_id));

            namedParameterJdbcTemplate
                    .update("update userdetails set tier = CASE WHEN (total_amount_spent > 1000000) THEN 'Platinum' " +
                            "WHEN (total_amount_spent > 250000 AND total_amount_spent < 1000000) THEN 'Gold' " +
                            "ELSE 'Silver' END where user_id = :user_id",
                            new MapSqlParameterSource().addValue("user_id", user_id));
        }


    }

    @Scheduled(cron = "0 30 23 * * ?")
    public void updateCoins(){
        String tier;
        Integer domesticCount;
        Integer internationalCount;
        Integer domesticCoins=0;
        Integer internationalCoins=0;
        int addCoins=0;
        for(int user_id : getCoinModifiedUsers()){
            tier = namedParameterJdbcTemplate.
                    queryForObject("select tier from userdetails where user_id= :user_id",
                            new MapSqlParameterSource("user_id", user_id),String.class);

            domesticCount = namedParameterJdbcTemplate.queryForObject("select count(user_id) from bookings where referrer_id = :user_id and trip_type = 'Domestic' and end_date = :date and trip_status = 'Completed';",
                    new MapSqlParameterSource()
                            .addValue("user_id",user_id)
                            .addValue("date",currentDate),Integer.class);

            internationalCount = namedParameterJdbcTemplate.queryForObject("select count(user_id) from bookings where referrer_id = :user_id and trip_type = 'International' and end_date = :date  and trip_status = 'Completed';",
                    new MapSqlParameterSource()
                            .addValue("user_id",user_id)
                            .addValue("date",currentDate),Integer.class);

            domesticCoins = namedParameterJdbcTemplate.queryForObject("select coins_value from coins where tier = :tier and trip_type = 'Domestic'",
                    new MapSqlParameterSource("tier", tier), Integer.class);

            internationalCoins = namedParameterJdbcTemplate.queryForObject("select coins_value from coins where tier = :tier and trip_type = 'International'",
                    new MapSqlParameterSource("tier", tier), Integer.class);
            addCoins = (domesticCount*domesticCoins) + (internationalCount*internationalCoins);
            namedParameterJdbcTemplate.update("update userdetails set coins = coins+:addcoins where user_id = :user_id;",
                    new MapSqlParameterSource()
                            .addValue("addcoins",addCoins)
                            .addValue("user_id",user_id));

        }


    }




    public List<Integer> getTierModifiedUsers(){

        return  namedParameterJdbcTemplate
                .queryForList("select user_id from bookings where end_date = :date group by user_id;",
                        new MapSqlParameterSource().addValue("date",currentDate),
                        Integer.class);
    }

    public List<Integer> getCoinModifiedUsers(){

        return  namedParameterJdbcTemplate
                .queryForList("select referrer_id from bookings where end_date = :date group by referrer_id;",
                        new MapSqlParameterSource().addValue("date",currentDate),
                        Integer.class);
    }


    public   List<HashMap<String,Object>> getTopReferrers(){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String monthStart = LocalDate.now().withDayOfMonth(1).format(formatter);
        String monthLast = LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1).format(formatter);
        List<Integer> topRefId;
        List<HashMap<String,Object>> topReferrerDetails = new ArrayList<>();
        String sql = "select referrer_id from " +
                "(select referrer_id , count(referrer_id) from bookings where is_referral_booking = 1 and " +
                "start_date >= :monthStart and end_date <= :monthLast and trip_status = 'Completed' " +
                "Group by referrer_id order by count(referrer_id) desc LIMIT 10);";
        topRefId = namedParameterJdbcTemplate.queryForList(sql,
                new MapSqlParameterSource()
                .addValue("monthStart", monthStart)
                .addValue("monthLast",monthLast),Integer.class);
        System.out.println(topRefId);

        for(int user_id : topRefId){

            UserModel user = (UserModel) userService.getUserById(user_id);
            HashMap<String,Object> totMap = new HashMap<>();
            String countSql= "select count(referrer_id) from bookings where is_referral_booking = 1 and " +
            "start_date >= :monthStart and end_date <= :monthLast and trip_status = 'Completed' and referrer_id = :user_id  order by count(referrer_id) desc LIMIT 10;";
            Integer countRef = namedParameterJdbcTemplate.queryForObject(countSql, new MapSqlParameterSource().addValue("monthStart", monthStart)
                    .addValue("monthLast",monthLast).addValue("user_id",user_id),Integer.class);


            totMap.put("user_Id",user.getUser_Id());
            totMap.put("Tier",user.getTier());
            totMap.put("coins",user.getCoins());

            totMap.put("total_amount_spent",user.getTotal_amount_spent());
            totMap.put("Referrals",countRef);

            topReferrerDetails.add(totMap);


        }

        return topReferrerDetails;



    }

}

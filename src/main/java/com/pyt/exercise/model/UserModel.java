package com.pyt.exercise.model;

public class UserModel {

    private int user_Id;
    private String tier;
    private long coins;
    private int referrer_Id;
    private long total_amount_spent;

    public UserModel() {

    }

    public UserModel(int user_Id, String tier, long coins, int referrer_Id, long total_amount_spent) {
        this.user_Id = user_Id;
        this.tier = tier;
        this.coins = coins;
        this.referrer_Id = referrer_Id;
        this.total_amount_spent = total_amount_spent;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public int getReferrer_Id() {
        return referrer_Id;
    }

    public void setReferrer_Id(int referrer_Id) {
        this.referrer_Id = referrer_Id;
    }

    public long getTotal_amount_spent() {
        return total_amount_spent;
    }

    public void setTotal_amount_spent(long total_amount_spent) {
        this.total_amount_spent = total_amount_spent;
    }

    @Override
    public String toString() {
        return     "\"user_Id\" =" + user_Id +
                ", \"tier\"=" + tier   +
                ", \"coins\"=" + coins +
                ", \"referrer_Id\"=" + referrer_Id +
                ", \"total_amount_spent\"=" + total_amount_spent
                ;
    }
}

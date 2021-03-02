package com.example.donationappv2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DonationDAO {

    @Insert
    void insert(Donations donation);

    @Query("SELECT * FROM Donations")
    Donations[] getAll();

    @Query("SELECT * FROM Donations WHERE donAmount > :amount")
    Donations[] getAllDonationsWithAmountBiggerThan(int amount);

}

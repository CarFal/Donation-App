package com.example.donationappv2;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Donations.class}, version = 1)
public abstract class DonationDataBase extends RoomDatabase {
    public abstract DonationDAO donDao();

}

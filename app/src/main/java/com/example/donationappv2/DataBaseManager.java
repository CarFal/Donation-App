package com.example.donationappv2;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Room;

public class DataBaseManager {
    private static DonationDataBase dbManager;

    public static final int NUMBER_OF_THREADS = 4;
    public static ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DonationDataBase buildDbManager(Context context){
        if(dbManager == null) {
            dbManager = Room.databaseBuilder(context, DonationDataBase.class, "MyDonations").build();
        }

        return dbManager;
    }

    public static DataBaseManager getInstance(Context context){

        if (sDBInstance == null) {
            sDatabase = Room.databaseBuilder(context, AppDataBase.class, "CitiesDB").build();        }
            
        return sDBInstance;
    }

    public static DonationDataBase getManager(){
        return dbManager;
    }

    public static void insertDonation(Donations newDonation){
        databaseExecutor.execute(()->{
            getManager().donDao().insert(newDonation);
        });
    }

    public static void getAllDonations(){
        databaseExecutor.execute(()->{
            Donations[] donList = getManager().donDao().getAll();
        });
    }

    public static void getDonationsBiggerAmount(int amount){
        databaseExecutor.execute(()->{
            Donations[] donList = getManager().donDao().getAllDonationsWithAmountBiggerThan(amount);
        });
    }

}

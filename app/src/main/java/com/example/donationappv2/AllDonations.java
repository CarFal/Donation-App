package com.example.donationappv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class AllDonations extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView myList;
    //ArrayList<Donations> donationList;
    SearchView donSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donations);
        DataBaseManager.getAllDonations();
        donSearchView = (SearchView) findViewById(R.id.searchViewDonation);
        donSearchView.setInputType(InputType.TYPE_CLASS_NUMBER);
        myList = (ListView) findViewById(R.id.alldonationlist);
        DataBaseManager.databaseExecutor.execute(()->{
            Donations[] donList = DataBaseManager.getManager().donDao().getAll();
            //donationList =  getIntent().getParcelableArrayListExtra("donationList");
            DonationAdapter adapter = new DonationAdapter(getApplicationContext(), donList);
            myList.setAdapter(adapter);


        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        DataBaseManager.databaseExecutor.execute(()->{
            Donations[] donList;
            try {
                donList = DataBaseManager.getManager().donDao().getAllDonationsWithAmountBiggerThan(Integer.parseInt(s));
            } catch (Exception e){
                donList = DataBaseManager.getManager().donDao().getAllDonationsWithAmountBiggerThan(0);
            }
            DonationAdapter tempAdapter = new DonationAdapter(getApplicationContext(), donList);
            myList.setAdapter(tempAdapter);
        });

        return false;
    }
}

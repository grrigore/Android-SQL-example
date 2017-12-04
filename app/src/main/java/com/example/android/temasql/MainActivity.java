package com.example.android.temasql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private List<Country> countryList;
    private ListView countryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListView = (ListView) findViewById(R.id.country_list);
        databaseHandler = DatabaseHandler.getDatabaseHandler(this);

        int countriesCount = databaseHandler.getCountryCount();
        //TODO versions
        if (countriesCount == 0) {
            Log.d("Insert: ", "Inserting...");
            databaseHandler.addCountry(new Country(0, "Romania", R.mipmap.ic_flag1));
            databaseHandler.addCountry(new Country(1, "England", R.mipmap.ic_flag2));
            databaseHandler.addCountry(new Country(2, "France", R.mipmap.ic_flag3));
        }


        countryList = databaseHandler.getAllCountries();
        setCostumAdapterForListView();

        countryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("countryName", countryList.get(i).getCountryId());
                startActivity(intent);
            }
        });
    }

    private void setCostumAdapterForListView() {
        CountryAdapter countryAdapter = new CountryAdapter(countryList, MainActivity.this);
        countryListView.setAdapter(countryAdapter);
    }
}

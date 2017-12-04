package com.example.android.temasql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Cristi on 10-Jul-17.
 */

public class CityActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private List<City> cityList;
    private ListView cityListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);

        Bundle extras = getIntent().getExtras();
        int country = extras.getInt("countryName");
        Log.d("Country name: ", country + "");

        cityListView = (ListView) findViewById(R.id.city_list);
        databaseHandler = DatabaseHandler.getDatabaseHandler(this);

        int cityCount = databaseHandler.getCityCount();
        if (cityCount == 0) {
            Log.d("Insert: ", "Inserting...");
            databaseHandler.addCity(new City(10, "Bucharest", "Description of city.", 0));
            databaseHandler.addCity(new City(11, "Brasov", "Description of city.", 0));
            databaseHandler.addCity(new City(12, "Manchester", "Description of city.", 1));
            databaseHandler.addCity(new City(13, "London", "Description of city.", 1));
            databaseHandler.addCity(new City(14, "Liverpool", "Description of city.", 1));
            databaseHandler.addCity(new City(15, "Nisa", "Description of city.", 2));
        }

        cityList = databaseHandler.getAllCities(country);
        setCostumAdapterForListView1();

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CityActivity.this, DetailAcivity.class);
                intent.putExtra("cityId", cityList.get(i).getCityId());
                startActivity(intent);
            }
        });
    }

    private void setCostumAdapterForListView1() {
        CityAdapter cityAdapter = new CityAdapter(cityList, CityActivity.this);
        cityListView.setAdapter(cityAdapter);
    }
}

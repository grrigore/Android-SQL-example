package com.example.android.temasql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cristi on 11-Jul-17.
 */

public class DetailAcivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private City selectedCityDetail;
    private TextView cityNameDetail;
    private TextView cityDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Bundle extras = getIntent().getExtras();
        int cityId = extras.getInt("cityId");
        Log.d("City id: ", cityId + "");

        cityNameDetail = (TextView) findViewById(R.id.city_name_detail);
        cityDetail = (TextView) findViewById(R.id.city_detail);

        databaseHandler = DatabaseHandler.getDatabaseHandler(this);

        selectedCityDetail = databaseHandler.getCityDetail(cityId);

        cityNameDetail.setText(selectedCityDetail.getCityName());
        cityDetail.setText(selectedCityDetail.getCityDetail());


    }
}

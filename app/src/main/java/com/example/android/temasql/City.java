package com.example.android.temasql;

/**
 * Created by Cristi on 10-Jul-17.
 */

public class City {

    private int cityId;
    private String cityName;
    private String cityDetail;
    private int cityCountry;

    public City(int cityId, String cityName, String cityDetail, int cityCountry) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityDetail = cityDetail;
        this.cityCountry = cityCountry;
    }

    public City() {
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityDetail() {
        return cityDetail;
    }

    public void setCityDetail(String cityDetail) {
        this.cityDetail = cityDetail;
    }

    public int getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(int cityCountry) {
        this.cityCountry = cityCountry;
    }
}

package com.example.android.temasql;

/**
 * Created by Cristi on 10-Jul-17.
 */

public class Country {

    private int countryId;
    private String countryName;
    private int countryFlag;

    public Country() {}


    public Country(int countryId, String countryName, int countryFlag) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryFlag() { return countryFlag; }
}

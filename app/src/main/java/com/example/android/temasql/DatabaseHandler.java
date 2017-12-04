package com.example.android.temasql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Cristi on 10-Jul-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CountriesManager";

    private static final String TABLE_NAME_COUNTRY = "countries";
    private static final String TABLE_NAME_CITY = "cities";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_FLAG = "flag";
    private static final String KEY_COUNTRY_ID = "countryId";
    private static final String KEY_DETAIL = "detail";

    private static final String TAG = "DBHelper";

    private static DatabaseHandler databaseHandler;

    public static synchronized DatabaseHandler getDatabaseHandler(Context context) {
        if (databaseHandler == null) {
            databaseHandler = new DatabaseHandler(context.getApplicationContext());
        }
        return databaseHandler;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_NAME_COUNTRY + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT, " +
                KEY_FLAG + " INT);";

        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_NAME_CITY + " (" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_NAME + " TEXT, " +
                KEY_DETAIL + " TEXT, " +
                KEY_COUNTRY_ID + " INTEGER);";

        sqLiteDatabase.execSQL(CREATE_COUNTRY_TABLE);
        sqLiteDatabase.execSQL(CREATE_CITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COUNTRY);
        onCreate(sqLiteDatabase);
    }

    public void addCountry(Country country) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, country.getCountryId());
            contentValues.put(KEY_NAME, country.getCountryName());
            contentValues.put(KEY_FLAG, country.getCountryFlag());

            sqLiteDatabase.insert(TABLE_NAME_COUNTRY, null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add a country to database");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public void addCity(City city) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_ID, city.getCityId());
            contentValues.put(KEY_NAME, city.getCityName());
            contentValues.put(KEY_DETAIL, city.getCityDetail());
            contentValues.put(KEY_COUNTRY_ID, city.getCityCountry());

            sqLiteDatabase.insert(TABLE_NAME_CITY, null, contentValues);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add a city to database");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    public List<Country> getAllCountries() {
        List<Country> countryList = new ArrayList<Country>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME_COUNTRY;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        Country country = null;

        try {
            if (cursor.moveToFirst()) {
                do {
                    country = new Country();
                    country.setCountryId(Integer.parseInt(cursor.getString(0)));
                    country.setCountryName(cursor.getString(1));
                    country.setCountryFlag(Integer.parseInt(cursor.getString(2)));

                    countryList.add(country);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get countries from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return countryList;
    }


    public City getCityDetail(int cityId) {
        City city = null;

        String selectQuery = "SELECT * FROM " + TABLE_NAME_CITY + " WHERE " + KEY_ID + " = " + cityId;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        try {
            if (cursor.moveToFirst()) {
                city = new City();
                city.setCityId(Integer.parseInt(cursor.getString(0)));
                city.setCityName(cursor.getString(1));
                city.setCityDetail(cursor.getString(2));
                city.setCityCountry(Integer.parseInt(cursor.getString(3)));
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get cities from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return city;
    }

    public List<City> getAllCities(int countryId) {
        List<City> cityList = new ArrayList<City>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME_CITY + " WHERE " + KEY_COUNTRY_ID + " = " + countryId;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        City city = null;

        try {
            if (cursor.moveToFirst()) {
                do {
                    city = new City();
                    city.setCityId(Integer.parseInt(cursor.getString(0)));
                    city.setCityName(cursor.getString(1));
                    city.setCityDetail(cursor.getString(2));
                    city.setCityCountry(Integer.parseInt(cursor.getString(3)));

                    cityList.add(city);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get cities from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return cityList;
    }

    public int getCountryCount() {
        int count = 0;

        String countQuery = "SELECT * FROM " + TABLE_NAME_COUNTRY;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        try {
            count = cursor.getCount();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get countries from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }

    public int getCityCount() {
        int count = 0;
        String countQuery = "SELECT * FROM " + TABLE_NAME_CITY;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        try {
            count = cursor.getCount();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get cities from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return count;
    }
}

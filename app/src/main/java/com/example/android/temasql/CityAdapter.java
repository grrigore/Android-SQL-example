package com.example.android.temasql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.country;

/**
 * Created by Cristi on 10-Jul-17.
 */

public class CityAdapter extends BaseAdapter {
    private List<City> mCityList;
    private Context mContext;

    public CityAdapter(List<City> mCityList, Context mContext) {
        this.mCityList = mCityList;
        this.mContext = mContext;
    }

    @Override
    public Object getItem(int i) {
        if (mCityList == null)
            return null;
        else
            return mCityList.get(i);
    }

    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final int currentPosition = position;
        ViewHolder1 viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder1();
            int layoutId = R.layout.city_item;
            view = layoutInflater.inflate(layoutId, parent, false);
            viewHolder.mCityName = (TextView) view.findViewById(R.id.city_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder1) view.getTag();
        }

        City city = (City) getItem(currentPosition);

        viewHolder.mCityName.setText(city.getCityName());

        return view;
    }

    class ViewHolder1 {
        protected TextView mCityName;
    }
}

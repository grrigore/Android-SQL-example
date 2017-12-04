package com.example.android.temasql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cristi on 10-Jul-17.
 */

public class CountryAdapter extends BaseAdapter {
    private List<Country> mCountryList;
    private Context mContext;

    public CountryAdapter(List<Country> mCountryList, Context mContext) {
        this.mCountryList = mCountryList;
        this.mContext = mContext;
    }

    @Override
    public Object getItem(int i) {
        if (mCountryList == null)
            return null;
        else
            return mCountryList.get(i);
    }

    @Override
    public int getCount() {
        return mCountryList.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final int currentPosition = position;
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            int layoutId = R.layout.country_item;
            view = layoutInflater.inflate(layoutId, parent, false);
            viewHolder.mCountryName = (TextView) view.findViewById(R.id.country_name);
            viewHolder.mCountryFlag = (ImageView) view.findViewById(R.id.country_flag);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Country country = (Country) getItem(currentPosition);

        viewHolder.mCountryName.setText(country.getCountryName());
        viewHolder.mCountryFlag.setImageResource(country.getCountryFlag());

        return view;
    }

    class ViewHolder {
        protected TextView mCountryName;
        protected ImageView mCountryFlag;
    }
}

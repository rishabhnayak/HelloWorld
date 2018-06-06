package com.MsoftTexas.WeatherOnMyTripRoute;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.MsoftTexas.WeatherOnMyTripRoute.Models.MPlace;

import java.util.List;


class Place_name_listView extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;



    private List<MPlace> arraylist;

    public Place_name_listView(Context context, List<MPlace> PlaceList) {
        mContext = context;

        inflater = LayoutInflater.from(mContext);

        this.arraylist=PlaceList;


    }


    public class ViewHolder {
        TextView name;
        TextView number;
        TextView srcName;
        TextView dstnName;


    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public MPlace getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.place_objlist_view_items, null);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.number = (TextView) view.findViewById(R.id.number);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(arraylist.get(position).getName());
        holder.number.setText(arraylist.get(position).getAdress());

        return view;
    }
}
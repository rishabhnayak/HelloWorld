package com.MsoftTexas.WeatherOnMyTripRoute.Adapters;

/**
 * Created by sahu on 5/6/2017.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MsoftTexas.WeatherOnMyTripRoute.Models.Item;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.MStep;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.Wlist;
import com.MsoftTexas.WeatherOnMyTripRoute.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ArrrayAdapter extends BaseAdapter {

   // Declare Variables

   Context mContext;
   LayoutInflater inflater;
   private List<Wlist> itemslist = new ArrayList<>();
   Item item;
   MStep mStep;



   public ArrrayAdapter(Context context,Item item) {
       mContext = context;
       this.item=item;
       this.itemslist.add(item.getWlist());
       inflater = LayoutInflater.from(mContext);
   }


    public ArrrayAdapter(Context context,MStep step) {
        mContext = context;
        this.mStep=step;
        this.itemslist.add(step.getWlist());
        inflater = LayoutInflater.from(mContext);
    }


    public class ViewHolder {

    //    TextView time;
        TextView date ;
        TextView weather;
        TextView temp;
        ImageView img;
        TextView address;

   }

   @Override
   public int getCount() {
       return itemslist.size();
   }

   @Override
   public Wlist getItem(int position) {
       return itemslist.get(position);
   }

   @Override
   public long getItemId(int position) {
       return position;
   }

   public View getView(final int position, View view, ViewGroup parent) {
       final ViewHolder holder;
       if (view == null) {
           holder = new ViewHolder();
           view = inflater.inflate(R.layout.weatherforcast_list_item, null);
           // Locate the TextViews in listview_item.xml
//           holder.name = (TextView) view.findViewById(R.id.name);
//           holder.number = (TextView) view.findViewById(R.id.number);




       //    holder.trainName = (TextView) view.findViewById(R.id.trainName);
        //   holder.time =view.findViewById(R.id.time);
           holder.date =view.findViewById(R.id.date);
           holder.weather=view.findViewById(R.id.WeatherVal);
           holder.temp =view.findViewById(R.id.TempVal);
           holder.img =view.findViewById(R.id.weatherImg);
           holder.address =view.findViewById(R.id.address);
           view.setTag(holder);
       } else {
           holder = (ViewHolder) view.getTag();
       }
       // Set the results into TextViews
//       holder.name.setText(itemslist.get(position).getAnimalName());
//       holder.number.setText(itemslist.get(position).getAnimalNo());
         try {
           //  holder.trainNo.setText(itemslist.get(position));
//             if(position==wdatapos || position == (wdatapos-1)){
//                 System.out.println("found the item pos .....");
//                 view.setBackgroundColor(Color.WHITE);
//             }else{
//                 view.setBackgroundColor(Color.LTGRAY);
//             }
             holder.temp.setText(itemslist.get(position).getApparentTemperature()+"°F");
             // holder.startDate.setText(itemslist.get(position).);
             holder.weather.setText(itemslist.get(position).getSummary());
             if(item!=null) {
                 holder.date.setText(item.getArrtime());
             }else {
                 holder.date.setText(mStep.getArrtime());
             }
//             if(item!=null) {
//                 holder.address.setText(new Geocoder(mContext, Locale.ENGLISH).getFromLocation(item.getPoint().getLatitude(), item.getPoint().getLatitude(), 1).get(0).getAddressLine(0));
//             }else if(mStep!=null){
//                 holder.address.setText(new Geocoder(mContext, Locale.ENGLISH).getFromLocation(mStep.getStep().getStart_location().getLat(), mStep.getStep().getStart_location().getLng(), 1).get(0).getAddressLine(0));
//
//             }
             //  holder.time.setText(itemslist.get(position).gettime());
//             StorageReference storageRef = storage.getReference(itemslist.get(position).getIcon()+".png");
             Drawable icon = mContext.getResources().getDrawable( R.drawable.clear_day );


             switch (itemslist.get(position).getIcon()){
                 case "clear-day":icon = mContext.getResources().getDrawable(R.drawable.clear_day);
                     break;
                 case "cloudy":icon = mContext.getResources().getDrawable(R.drawable.cloudy);
                     break;
                 case "clear-night":icon = mContext.getResources().getDrawable(R.drawable.clear_night);
                     break;
                 case "fog":icon = mContext.getResources().getDrawable(R.drawable.fog);
                     break;
                 case "hail":icon = mContext.getResources().getDrawable(R.drawable.hail);
                     break;
                 case "partly-cloudy-day":icon = mContext.getResources().getDrawable(R.drawable.partly_cloudy_day);
                     break;
                 case "partly-cloudy-night":icon = mContext.getResources().getDrawable(R.drawable.partly_cloudy_night);
                     break;
                 case "rain":icon = mContext.getResources().getDrawable(R.drawable.rain);
                     break;
                 case "sleet":icon = mContext.getResources().getDrawable(R.drawable.sleet);
                     break;
                 case "snow":icon = mContext.getResources().getDrawable(R.drawable.snow);
                     break;
                 case "thunderstorm":icon = mContext.getResources().getDrawable(R.drawable.thunderstorm);
                     break;
                 case "tornado":icon = mContext.getResources().getDrawable(R.drawable.tornado);
                     break;
                 case "wind":icon = mContext.getResources().getDrawable(R.drawable.wind);
                     break;
             }
             Glide.with(mContext)
                   //  .load("http://openweathermap.org/img/w/"+itemslist.get(position).weather.get(0).icon+".png")
                     .load(icon)
                //     .override(100,100)
                     .into(holder.img);

         }catch (Exception e){
           e.printStackTrace();
         }


       return view;
   }

}



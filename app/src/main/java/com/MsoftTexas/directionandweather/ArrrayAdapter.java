package com.MsoftTexas.directionandweather;

/**
 * Created by sahu on 5/6/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.MsoftTexas.directionandweather.Models.Item;
import com.MsoftTexas.directionandweather.Models.Wlist;
import com.bumptech.glide.Glide;

import java.util.List;

class ArrrayAdapter extends BaseAdapter {

   // Declare Variables

   Context mContext;
   LayoutInflater inflater;
   private List<Wlist> itemslist = null;
   Item item;


   public ArrrayAdapter(Context context, List<Wlist> itemslist) {
       mContext = context;
       this.itemslist = itemslist;
       inflater = LayoutInflater.from(mContext);
   }

    public class ViewHolder {

    //    TextView time;
        TextView date ;
        TextView weather;
        TextView temp;
        ImageView img;


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
             holder.temp.setText(itemslist.get(position).getTemp()+"°F");
             // holder.startDate.setText(itemslist.get(position).);
             holder.weather.setText(itemslist.get(position).getWx());
             holder.date.setText(itemslist.get(position).getWtime());
           //  holder.time.setText(itemslist.get(position).gettime());

             Glide.with(mContext)
                  //   .load("http://openweathermap.org/img/w/"+itemslist.get(position).weather.get(0).icon+".png")
                     .load(itemslist.get(position).getImgurl())
                     .override(100,100)
                     .into(holder.img);

         }catch (Exception e){
           e.printStackTrace();
         }


       return view;
   }

}



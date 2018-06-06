package com.MsoftTexas.WeatherOnMyTripRoute.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MsoftTexas.WeatherOnMyTripRoute.Models.MStep;
import com.MsoftTexas.WeatherOnMyTripRoute.R;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;


/**
 * Created by RAJA on 18-12-2017.
 */

public class DragupListAdapter extends RecyclerView.Adapter<DragupListAdapter.PnrViewHolder>{

    private Context context;
    private List<MStep> mSteps;
    public DragupListAdapter(Context context, List<MStep> mSteps){
        this.context=context;
        this.mSteps=mSteps;
    }



    @Override
    public PnrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.dragup_list_layout,parent,false);
        return new PnrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PnrViewHolder holder, int position) {
        MStep mStep =mSteps.get(position);
       // Glide.with(holder.image.getContext()).load(passengerList.getLink()).into(holder.image);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.instr.setText(Html.fromHtml(mStep.getStep().getHtml_instructions(), Html.FROM_HTML_MODE_COMPACT));
        }else {
            holder.instr.setText(Html.fromHtml(mStep.getStep().getHtml_instructions()));
        }
        //     holder.instr.setText(mStep.getStep().getHtml_instructions());
//        holder.distance.setText("Traveled : "+mStep.getAft_distance()/(long)1000+" km");
//        holder.arrtime.setText("Start time:"+mStep.getArrtime());
        try {
            holder.distance.setText(String.format("%.2f", (float) mStep.getAft_distance() / (float) 1000 * (0.621371)) + " miles");
            holder.arrtime.setText(mStep.getArrtime());
            holder.weather.setText(mStep.getWlist().getSummary());
            holder.temp.setText(mStep.getWlist().getTemperature() + "°F");
            holder.stepLength.setText(String.format("%.2f", (float) mStep.getStep().getDistance().getValue() / (float) 1000 * (0.621371)) + " miles");
         //   StorageReference storageRef = storage.getReference(mStep.getWlist().getIcon()+".png");
            Drawable icon = context.getResources().getDrawable( R.drawable.clear_day );


            switch (mStep.getWlist().getIcon()){
                case "clear-day":icon = context.getResources().getDrawable(R.drawable.clear_day);
                    break;
                case "cloudy":icon = context.getResources().getDrawable(R.drawable.cloudy);
                    break;
                case "clear-night":icon = context.getResources().getDrawable(R.drawable.clear_night);
                    break;
                case "fog":icon = context.getResources().getDrawable(R.drawable.fog);
                    break;
                case "hail":icon = context.getResources().getDrawable(R.drawable.hail);
                    break;
                case "partly-cloudy-day":icon = context.getResources().getDrawable(R.drawable.partly_cloudy_day);
                    break;
                case "partly-cloudy-night":icon = context.getResources().getDrawable(R.drawable.partly_cloudy_night);
                    break;
                case "rain":icon = context.getResources().getDrawable(R.drawable.rain);
                    break;
                case "sleet":icon = context.getResources().getDrawable(R.drawable.sleet);
                    break;
                case "snow":icon = context.getResources().getDrawable(R.drawable.snow);
                    break;
                case "thunderstorm":icon = context.getResources().getDrawable(R.drawable.thunderstorm);
                    break;
                case "tornado":icon = context.getResources().getDrawable(R.drawable.tornado);
                    break;
                case "wind":icon = context.getResources().getDrawable(R.drawable.wind);
                    break;
            }
            Glide.with(context)
                    //  .load("http://openweathermap.org/img/w/"+itemslist.get(position).weather.get(0).icon+".png")
                    .load(icon)
                    //     .override(100,100)
                    .into(holder.weatherimg);
        //    System.out.println(mStep.getStep().getStart_location().getLat());
         //   System.out.println(mStep.getStep().getStart_location().getLng());


            String address = new Geocoder(context, Locale.ENGLISH).getFromLocation(mStep.getStep().getStart_location().getLat(), mStep.getStep().getStart_location().getLng(), 1).get(0).getAddressLine(0);
            //  System.out.println("hre is address :"+address);
            holder.address.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

   @Override
    public int getItemCount() {
      return mSteps.size();
    }

    public class PnrViewHolder extends RecyclerView.ViewHolder {



        TextView instr,distance,arrtime,temp,weather,stepLength,address;
        ImageView weatherimg;
        public PnrViewHolder(View itemView) {
            super(itemView);
            instr= (TextView) itemView.findViewById(R.id.instr);
            weather= (TextView) itemView.findViewById(R.id.weather);
            temp= (TextView) itemView.findViewById(R.id.temp);
            distance= (TextView) itemView.findViewById(R.id.distance);
            arrtime= (TextView) itemView.findViewById(R.id.arrtime);
            weatherimg=itemView.findViewById(R.id.weatherImg);
            stepLength=itemView.findViewById(R.id.stepLength);
            address=itemView.findViewById(R.id.address);
        }
    }
}

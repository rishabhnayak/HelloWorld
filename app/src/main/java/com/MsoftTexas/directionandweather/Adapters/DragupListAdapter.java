package com.MsoftTexas.directionandweather.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.MsoftTexas.directionandweather.Models.MStep;
import com.MsoftTexas.directionandweather.R;
import com.bumptech.glide.Glide;

import java.util.List;


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
        holder.distance.setText(String.format("%.2f",(float)mStep.getAft_distance()/(float)1000*(0.621371))+" miles");
        holder.arrtime.setText(mStep.getArrtime());
        holder.weather.setText(mStep.getWlist().get(0).getWx());
        holder.temp.setText(mStep.getWlist().get(0).getTemp()+"°F");
        holder.stepLength.setText(String.format("%.2f",(float)mStep.getStep().getDistance().getValue()/(float)1000*(0.621371))+ " miles");
        Glide.with(context)
                .load(mStep.getWlist().get(0).getImgurl())
                .override(100,100)
                .into(holder.weatherimg);
    }

   @Override
    public int getItemCount() {
      return mSteps.size();
    }

    public class PnrViewHolder extends RecyclerView.ViewHolder {



        TextView instr,distance,arrtime,temp,weather,stepLength;
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
        }
    }
}

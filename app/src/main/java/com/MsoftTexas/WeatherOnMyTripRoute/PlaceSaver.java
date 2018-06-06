package com.MsoftTexas.WeatherOnMyTripRoute;


import android.content.SharedPreferences;

import com.MsoftTexas.WeatherOnMyTripRoute.Models.MPlace;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PlaceSaver implements Runnable {
    ArrayList<MPlace> list =new ArrayList<MPlace>();
    MPlace item;
    SharedPreferences sd;
    public PlaceSaver(SharedPreferences sd, MPlace item) {
        this.item=item;
        this.sd=sd;
    }


    @Override
    public void run() {
        Boolean elementRemoved=false;
        Gson gson = new Gson();
            if(sd.getString("PlaceSaver", "").equals("")) {
              System.out.println("Trains Saver is not there so creating PlaceSaver and then adding");
                list.add(item);
           System.out.println("element added :"+item);
                SharedPreferences.Editor prefsEditor = sd.edit();
                String json = gson.toJson(new PlaceSaverObject(list));
                prefsEditor.putString("PlaceSaver", json);
                prefsEditor.commit();
            }else if(!sd.getString("PlaceSaver", "").equals("")){
                String json1 = sd.getString("PlaceSaver", "");
           System.out.println("here is json 1" + json1);
                PlaceSaverObject obj = gson.fromJson(json1, PlaceSaverObject.class);
                list=obj.getList();


               System.out.println("list iterator on job...");
                    for(MPlace item0:list){
                        if(item0.getId().equals(item.getId())){
                            list.remove(item0);
                            elementRemoved=true;
                       System.out.println("element removed :"+item.getId());
                            list.add(item);
                       System.out.println("element added :"+item);
                            break;
                        }
                    }



                if(!elementRemoved) {
                    if (list.size() > 4) {
                   System.out.println("list greater than 4");
                        list.remove(0);
                        list.add(item);
                   System.out.println("element added :"+item);
                    } else  {
                   System.out.println("list smaller than 4");
                        list.add(item);
                   System.out.println("element added :"+item);
                    }
                }

                SharedPreferences.Editor prefsEditor = sd.edit();
                String json = gson.toJson(new PlaceSaverObject(list));
                prefsEditor.putString("PlaceSaver", json);
                prefsEditor.commit();
           System.out.println("creating PlaceSaver in sd");
            }else{
           System.out.println("dont know what to do....");
            }

    }
}

package com.MsoftTexas.directionandweather;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;

import com.MsoftTexas.directionandweather.Adapters.DragupListAdapter;
import com.MsoftTexas.directionandweather.Models.Apidata;
import com.MsoftTexas.directionandweather.Models.Item;
import com.MsoftTexas.directionandweather.Models.MStep;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.MsoftTexas.directionandweather.MapActivity.apiData;
import static com.MsoftTexas.directionandweather.MapActivity.context;
import static com.MsoftTexas.directionandweather.MapActivity.custom_dialog;
import static com.MsoftTexas.directionandweather.MapActivity.destination;
import static com.MsoftTexas.directionandweather.MapActivity.googleMap;
import static com.MsoftTexas.directionandweather.MapActivity.interval;
import static com.MsoftTexas.directionandweather.MapActivity.jstart_date_millis;
import static com.MsoftTexas.directionandweather.MapActivity.jstart_time_millis;
import static com.MsoftTexas.directionandweather.MapActivity.link;
import static com.MsoftTexas.directionandweather.MapActivity.loading;
import static com.MsoftTexas.directionandweather.MapActivity.loading_text;
import static com.MsoftTexas.directionandweather.MapActivity.markersInterm;
import static com.MsoftTexas.directionandweather.MapActivity.markersSteps;
import static com.MsoftTexas.directionandweather.MapActivity.origin;
import static com.MsoftTexas.directionandweather.MapActivity.routeloaded;
import static com.MsoftTexas.directionandweather.MapActivity.selectedroute;
import static com.MsoftTexas.directionandweather.MapActivity.slidingUpPanelLayout;
import static com.MsoftTexas.directionandweather.MapActivity.timezone;

/**
 * Created by kamlesh on 29-03-2018.
 */

public class WeatherApi extends AsyncTask<Object,Object,String> {


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String data) {

        Apidata apidata=null;
        try {
            apidata = new Gson().fromJson(data, Apidata.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("weather data call has started........");
        MapActivity.weatherloaded=true;
        System.out.println("here is the list of intermediate Points:");
        apiData=apidata;
        int c=-1;
        if(apidata!=null && apidata.getItems()!=null){
            for(final Item item:apidata.getItems()) {
                c++;
                System.out.println(new Gson().toJson(item));
                loading.setVisibility(View.GONE);
                loading_text.setVisibility(View.GONE);
                slidingUpPanelLayout.setAlpha(1);
                //   googleMap.addMarker(new MarkerOptions().position(item.getPoint()));
                final int finalC = c;
                Glide.with(context)
                        .load(item.getWlist().get(0).getImgurl())
                        .asBitmap()
                        .fitCenter()
                        .into(new SimpleTarget<Bitmap>(90, 90) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Marker marker = googleMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromBitmap(resource))
                                        .position(new LatLng(item.getPoint().getLatitude(), item.getPoint().getLongitude())));
                                marker.setTag("I"+finalC);
                                markersInterm.add(marker);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                                googleMap.addMarker(new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_default_logo))
//                                        .position(place.getLatLng()));
                                e.printStackTrace();
                            }
                        });
            }
        }else{
            System.out.println("api data is null or api.getlist is null");
        }


        if(apidata!=null && apidata.getSteps()!=null){
            link.setAdapter(new DragupListAdapter(context, apidata.getSteps()));
            for(final MStep mStep:apidata.getSteps()) {
                c++;
                System.out.println(new Gson().toJson(mStep));
                loading.setVisibility(View.GONE);
                loading_text.setVisibility(View.GONE);
                slidingUpPanelLayout.setAlpha(1);
                //   googleMap.addMarker(new MarkerOptions().position(item.getPoint()));
                final int finalC = c;
                Glide.with(context)
                        .load(mStep.getWlist().get(0).getImgurl())
                        .asBitmap()
                        .fitCenter()
                        .into(new SimpleTarget<Bitmap>(90, 90) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                Marker marker = googleMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromBitmap(resource))
                                        .position(new LatLng(mStep.getStep().getStart_location().getLat(),mStep.getStep().getStart_location().getLng())));
                                marker.setTag("S"+finalC);
                                markersSteps.add(marker);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                                googleMap.addMarker(new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_default_logo))
//                                        .position(place.getLatLng()));
                                e.printStackTrace();
                            }
                        });
            }

        }else {
            System.out.println("api data is null or api.getlist is null");
        }

        if(routeloaded) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Do something after 100ms
                    custom_dialog.setVisibility(View.GONE);
                }
            }, 1000);

        }else{

            custom_dialog.setVisibility(View.VISIBLE);
            loading_text.setText("loading route...");
        }

    }

    @Override
    protected String doInBackground(Object[] objects) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = null;
            //nbsc-1518068960369.appspot.com
            System.out.println("http://a6c754c6.ngrok.io/_ah/api/myapi/v1/wdata?"
                    +"olat="+origin.latitude+"&olng="+origin.longitude
                    +"&dlat="+destination.latitude+"&dlng="+destination.longitude
                    +"&route="+selectedroute
                    +"&interval="+interval
                    +"&tz="+timezone
                    +"&jstime="+(jstart_date_millis+jstart_time_millis));
            HttpGet request=new HttpGet("https://nbsc-1518068960369.appspot.com/_ah/api/myapi/v1/wdata?" +
                    "olat="+origin.latitude +
                    "&olng="+origin.longitude +
                    "&dlat="+destination.latitude +
                    "&dlng="+destination.longitude +
                    "&route="+selectedroute +
                    "&interval="+interval +
                    "&tz=" +timezone.replace("/","%2F") +
                    "&jstime="+(jstart_date_millis+jstart_time_millis)
            );
            BufferedReader rd=null;

            response = client.execute(request);
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line="";
            StringBuilder sb=new StringBuilder();
            while ((line=rd.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
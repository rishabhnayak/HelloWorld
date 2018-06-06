package com.MsoftTexas.WeatherOnMyTripRoute;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;

import com.MsoftTexas.WeatherOnMyTripRoute.Adapters.DragupListAdapter;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.Apidata;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.Item;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.MStep;
import com.google.android.gms.maps.model.BitmapDescriptor;
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

import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.apiData;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.context;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.custom_dialog;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.destination;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.googleMap;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.interval;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.jstart_date_millis;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.jstart_time_millis;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.link;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.loading;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.loading_text;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.markersInterm;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.markersSteps;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.origin;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.routeloaded;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.selectedroute;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.slidingUpPanelLayout;
import static com.MsoftTexas.WeatherOnMyTripRoute.MapActivity.timezone;

/**
 * Created by kamlesh on 29-03-2018.
 */

public class WeatherApi extends AsyncTask<Object,Object,String> {

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String data) {
        try {
        Apidata apidata=null;

            apidata = new Gson().fromJson(data, Apidata.class);

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
                BitmapDescriptor icon = new bitmapfromstring(item.getWlist().getIcon()).getIcon();
                Marker marker = googleMap.addMarker(new MarkerOptions()
                                        .icon(icon)
                                        .position(new LatLng(item.getPoint().getLatitude(), item.getPoint().getLongitude())));
                                marker.setTag("I"+finalC);
                                markersInterm.add(marker);
//                Glide.with(context)
//                        .load(item.getWlist().get(0).getImgurl())
//                        .asBitmap()
//                        .fitCenter()
//                        .into(new SimpleTarget<Bitmap>(90, 90) {
//                            @Override
//                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                                Marker marker = googleMap.addMarker(new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory.fromBitmap(resource))
//                                        .position(new LatLng(item.getPoint().getLatitude(), item.getPoint().getLongitude())));
//                                marker.setTag("I"+finalC);
//                                markersInterm.add(marker);
//                            }
//
//                            @Override
//                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
////                                googleMap.addMarker(new MarkerOptions()
////                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_default_logo))
////                                        .position(place.getLatLng()));
//                                e.printStackTrace();
//                            }
//                        });
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

                BitmapDescriptor icon = new bitmapfromstring(mStep.getWlist().getIcon()).getIcon();


                Marker marker = googleMap.addMarker(new MarkerOptions()
                                            .icon(icon)
                                            .position(new LatLng(mStep.getStep().getStart_location().getLat(), mStep.getStep().getStart_location().getLng())));
                                    marker.setTag("S" + finalC);
                                    markersSteps.add(marker);
                try {

//                    System.out.println(mStep.getWlist().getIcon());
//
//                    StorageReference storageRef = storage.getReference(mStep.getWlist().getIcon());
//                    System.out.println(storageRef.toString());
//
//                    Glide.with(context)
//
//                            .load(storageRef)
//
//                            .asBitmap()
//                            .fitCenter()
//                            .into(new SimpleTarget<Bitmap>(90, 90) {
//                                @Override
//                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                                    Marker marker = googleMap.addMarker(new MarkerOptions()
//                                            .icon(BitmapDescriptorFactory.fromBitmap(resource))
//                                            .position(new LatLng(mStep.getStep().getStart_location().getLat(), mStep.getStep().getStart_location().getLng())));
//                                    marker.setTag("S" + finalC);
//                                    markersSteps.add(marker);
//                                }
//
//
//
//
//                                @Override
//                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
////                                googleMap.addMarker(new MarkerOptions()
////                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_default_logo))
////                                        .position(place.getLatLng()));
//                                    e.printStackTrace();
//                                }
//                            });

                }catch (Exception e){
                    e.printStackTrace();


                }
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

        }catch (Exception e){
            e.printStackTrace();
            custom_dialog.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            loading_text.setVisibility(View.VISIBLE);
            if(data.equals("NoInternet")){
                loading_text.setText("No Internet Connection.Please Check Your Internet Connection");
            }else {
                loading_text.setText("Error :" + e.toString());
            }
        }

    }

    @Override
    protected String doInBackground(Object[] objects) {
        try {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = null;
            //nbsc-1518068960369.appspot.com
            System.out.println("https://nbsc-1518068960369.appspot.com/_ah/api/darksky/v1/wdata?"
                    +"olat="+origin.latitude+"&olng="+origin.longitude
                    +"&dlat="+destination.latitude+"&dlng="+destination.longitude
                    +"&route="+selectedroute
                    +"&interval="+interval
                    +"&tz="+timezone
                    +"&jstime="+(jstart_date_millis+jstart_time_millis));
            HttpGet request=new HttpGet("https://nbsc-1518068960369.appspot.com/_ah/api/darksky/v1/wdata?" +
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
            }else{
                return "NoInternet";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
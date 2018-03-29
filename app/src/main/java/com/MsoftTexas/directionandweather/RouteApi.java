package com.MsoftTexas.directionandweather;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;

import com.MsoftTexas.directionandweather.DirectionApiModel.DirectionApi;
import com.MsoftTexas.directionandweather.DirectionApiModel.Route;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.maps.android.PolyUtil;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.MsoftTexas.directionandweather.MapActivity.context;
import static com.MsoftTexas.directionandweather.MapActivity.custom_dialog;
import static com.MsoftTexas.directionandweather.MapActivity.destination;
import static com.MsoftTexas.directionandweather.MapActivity.googleMap;
import static com.MsoftTexas.directionandweather.MapActivity.loading;
import static com.MsoftTexas.directionandweather.MapActivity.loading_text;
import static com.MsoftTexas.directionandweather.MapActivity.origin;
import static com.MsoftTexas.directionandweather.MapActivity.polylineOptionsList;
import static com.MsoftTexas.directionandweather.MapActivity.polylines;
import static com.MsoftTexas.directionandweather.MapActivity.setCameraWithCoordinationBounds;
import static com.MsoftTexas.directionandweather.MapActivity.slidingUpPanelLayout;
import static com.MsoftTexas.directionandweather.MapActivity.weatherloaded;

/**
 * Created by kamlesh on 29-03-2018.
 */
public class RouteApi extends AsyncTask<Object,Object,String> {


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String data) {

        try {
            DirectionApi apidata = new Gson().fromJson(data, DirectionApi.class);
            MapActivity.routeloaded = true;

            //      System.out.println("direction data : "+new Gson().toJson(apidata));
            MapActivity.directionapi = apidata;
            Route route = apidata.getRoutes().get(0);


            System.out.println("here is polyline : " + apidata.getRoutes().get(0).getOverview_polyline().getPoints());
            if (weatherloaded) {
                custom_dialog.setVisibility(View.GONE);
            } else {
                MapActivity.loading_text.setText("loading weather..");
            }

            System.out.println("here is the route data :\n" + new Gson().toJson(apidata));
            if (new Gson().toJson(apidata) != null) {

                slidingUpPanelLayout.setAlpha(1);
            }
            System.out.println("direction success.............babes.......");
            MapActivity.polylines = new ArrayList<>();
            //add route(s) to the map.

            MapActivity.distance.setText("(" + route.getLegs().get(0).getDistance().getText() + ")");
            MapActivity.duration.setText(route.getLegs().get(0).getDuration().getText());
            if (route.getLegs().get(0).getDuration().getText() != null) {
                slidingUpPanelLayout.setPanelHeight(context.getResources().getDimensionPixelSize(R.dimen.dragupsize));
            }


            polylineOptionsList = new ArrayList<>();
            System.out.println("route options : " + apidata.getRoutes().size());
            Polyline selectedPolyline = null;
            if (apidata.getRoutes().size() > 0) {
                List<LatLng> lst = PolyUtil.decode(apidata.getRoutes().get(0).getOverview_polyline().getPoints());
                PolylineOptions polyOptions = new PolylineOptions();
                polyOptions.color(context.getResources().getColor(R.color.seletedRoute));
                polyOptions.width(18);
                polyOptions.addAll(lst);

                MapActivity.polylines.add(selectedPolyline);
                polylineOptionsList.add(polyOptions);
            }

            if (apidata.getRoutes().size() > 1) {
                for (int i = 1; i < apidata.getRoutes().size(); i++) {
                    List<LatLng> lst = PolyUtil.decode(apidata.getRoutes().get(i).getOverview_polyline().getPoints());
                    //In case of more than 5 alternative routes
                    //   int colorIndex = i % COLORS.length;

                    PolylineOptions polyOptions = new PolylineOptions();

                    polyOptions.color(context.getResources().getColor(R.color.alternateRoute));
                    polyOptions.width(15);


                    polyOptions.addAll(lst);
                    Polyline polyline = MapActivity.googleMap.addPolyline(polyOptions);
                    MapActivity.polylines.add(polyline);
                    polyline.setClickable(true);
                    polylineOptionsList.add(polyOptions);
                }
            }

            if (polylineOptionsList != null && polylineOptionsList.get(0) != null) {
                selectedPolyline = googleMap.addPolyline(polylineOptionsList.get(0));
                polylines.set(0, selectedPolyline);
                selectedPolyline.setClickable(true);
            }

            setCameraWithCoordinationBounds(route);
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
                    System.out.println("https://maps.googleapis.com/maps/api/directions/json?origin="
                            + origin.latitude + "," + origin.longitude
                            + "&destination=" + destination.latitude + "," + destination.longitude
                            + "&alternatives=true"
                            + "&key=AIzaSyDi3B9R9hVpC9YTmOCCz_pCR1BKW3tIRGY");
                    HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/directions/json?origin="
                            + origin.latitude + "," + origin.longitude
                            + "&destination=" + destination.latitude + "," + destination.longitude
                            + "&alternatives=true"
                            + "&key=AIzaSyDi3B9R9hVpC9YTmOCCz_pCR1BKW3tIRGY");
                    BufferedReader rd = null;
                    try {
                        response = client.execute(request);
                        rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                        String line = "";
                        StringBuilder sb = new StringBuilder();
                        while ((line = rd.readLine()) != null) {
                            sb.append(line);
                        }
                        return sb.toString();

                    } catch (Exception e) {
                        System.out.println("error : " + e.toString());
                        String line = "";
                    }
             }else{
                return "NoInternet";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}

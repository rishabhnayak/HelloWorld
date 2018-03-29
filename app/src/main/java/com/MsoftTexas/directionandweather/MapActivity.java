package com.MsoftTexas.directionandweather;

import android.*;
import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.os.AsyncTask;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.MsoftTexas.directionandweather.DirectionApiModel.DirectionApi;
import com.MsoftTexas.directionandweather.DirectionApiModel.Route;
import com.MsoftTexas.directionandweather.Models.Apidata;
import com.MsoftTexas.directionandweather.Models.Item;
import com.MsoftTexas.directionandweather.Models.MStep;

import com.airbnb.lottie.LottieAnimationView;



import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback
        ,View.OnClickListener
        {

    static Context context;
    static RelativeLayout custom_dialog;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
   static  LottieAnimationView loading;
     static  List<PolylineOptions> polylineOptionsList;
     static List<Polyline> polylines=new ArrayList<>();
    static List<Marker> markersInterm = new ArrayList<>();
    static List<Marker> markersSteps = new ArrayList<>();

    static TextView loading_text;
    static  int selectedroute=0;
    static String timezone;
    static int mYear,mMonth,mDay, mHour, mMinute;
    static long interval=50000;
    static Boolean weatherloaded=false, routeloaded=false;
    static TextView time;
   static CardView date_holder;
   static TextView departAt;
    static ImageView go;
    static TextView src,dstn;
    static  SlidingUpPanelLayout slidingUpPanelLayout;
    static long jstart_date_millis, jstart_time_millis;
    static private Marker originMarker, dstnMarker;
    private List<Marker> markers = new ArrayList<>();
    static Apidata apiData=null;
          static GoogleMap googleMap;
    private String serverKey = "AIzaSyDi3B9R9hVpC9YTmOCCz_pCR1BKW3tIRGY";
    static DirectionApi directionapi;
    static TextView distance, duration;
    ImageView RequestDirection;
     static LatLng origin = null;

     static LatLng destination = null;

    protected GeoDataClient mGeoDataClientS, mGeoDataClientD;

    SharedPreferences sd;
    String[] month={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private FirebaseAnalytics mFirebaseAnalytics;


   static RecyclerView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "MapActivity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        sd = this.getSharedPreferences("com.MsoftTexas.directionandweather", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context=getApplicationContext();
        src =findViewById(R.id.autocomplete_source);
        dstn=findViewById(R.id.autocomplete_destination);
        go=findViewById(R.id.request_direction);
        RequestDirection=findViewById(R.id.request_direction);
//loading.................lottie
        custom_dialog=findViewById(R.id.custom_dialog);
        loading=findViewById(R.id.loading);
        loading_text=findViewById(R.id.loading_text);


        //setting title null
        getSupportActionBar().setTitle("");

//sliding up layout
        slidingUpPanelLayout=findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelHeight(0);


        link = (RecyclerView) findViewById(R.id.dragup_list_recycler);
        link.setLayoutManager(new LinearLayoutManager(this));


        distance = findViewById(R.id.distance);
        duration = findViewById(R.id.duration);

        time = findViewById(R.id.time);
        date_holder = findViewById(R.id.card_date);
        departAt=findViewById(R.id.date1);


        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        mGeoDataClientS = Places.getGeoDataClient(this, null);
        mGeoDataClientD = Places.getGeoDataClient(this, null);


        // Retrieve the AutoCompleteTextView that will display Place suggestions.


        src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this,SearchPlace.class);
                  intent.putExtra("SrcOrDstn","Src");
                    startActivity(intent);
            }
        });

        dstn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapActivity.this,SearchPlace.class);
                intent.putExtra("SrcOrDstn","Dstn");
                startActivity(intent);
            }
        });

        RequestDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDirection();
            }
        });


        final Calendar c = Calendar.getInstance();
        timezone=c.getTimeZone().getID();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        jstart_date_millis=c.getTimeInMillis()-((mHour*60+mMinute)*60*1000);
        jstart_time_millis=(mHour*60+mMinute)*60*1000;


        String sHour = mHour < 10 ? "0" + mHour : "" + mHour;
        String sMinute = mMinute < 10 ? "0" + mMinute : "" + mMinute;
        String curr_time = sHour + ":" + sMinute;
        time.setText(curr_time);
        departAt.setText(curr_time+","+mDay+" "+month[mMonth]+" "+String.valueOf(mYear).substring(2));
        
        Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });
        date_holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                datePicker();

            }
        });
        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tiemPicker();

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
        super.onBackPressed();
            super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.km20:
                item.setChecked(true);
                MapActivity.interval=20000;
                return true;
            case R.id.km30:
                 item.setChecked(true);
                MapActivity.interval=30000;
                Toast.makeText(this, "30km", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.km40:
               item.setChecked(true);
                MapActivity.interval=40000;
                return true;
            case R.id.km50:
                item.setChecked(true);
                MapActivity.interval=50000;
                return true;
            case R.id.action_retry:
                requestDirection();
                Toast.makeText(this, "Retrying...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_clr:
                Toast.makeText(this, "clear", Toast.LENGTH_SHORT).show();
                origin=null;
                destination=null;
                recreate();
                return true;
                default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View view) {

    }

            @Override
            protected void onStart() {
                super.onStart();
                checkLocationPermission();
            }

            @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

       


  

        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener()
        {
            @Override
            public void onPolylineClick(Polyline polyline)
            {


                int val=0;
                for(int k=0;k<polylines.size();k++){
                    polylines.get(k).remove();
                    if(!polylines.get(k).equals(polyline)){
                        polylineOptionsList.get(k).color(getResources().getColor(R.color.alternateRoute));
                        polylineOptionsList.get(k).width(15);
                        Polyline p=googleMap.addPolyline(polylineOptionsList.get(k));
                        p.setClickable(true);
                        polylines.set(k,p);
                    }else{
                        val=k;
                    }

                }
                selectedroute=val;

                polylineOptionsList.get(val).color(getResources().getColor(R.color.seletedRoute));
                polylineOptionsList.get(val).width(15);
                Polyline selectedPolyline=googleMap.addPolyline(polylineOptionsList.get(val));
                selectedPolyline.setClickable(true);
                polylines.set(val,selectedPolyline);

                distance.setText("("+directionapi.getRoutes().get(val).getLegs().get(0).getDistance().getText()+")");
                duration.setText(directionapi.getRoutes().get(val).getLegs().get(0).getDuration().getText());

                for(int k=0;k<markersSteps.size();k++){
                    markersSteps.get(k).remove();
                }
                for(int k=0;k<markersInterm.size();k++){
                    markersInterm.get(k).remove();
                }
                custom_dialog.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                loading_text.setVisibility(View.VISIBLE);
                loading_text.setText("loading weather...");
                new WeatherApi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                if (originMarker == null) {
                    originMarker = googleMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinb)));
                    originMarker.setDraggable(true);
                    originMarker.setTitle("Source");
                    // originMarker.setTag(0);
                    origin=point;
                } else if (dstnMarker == null) {
                    dstnMarker=googleMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.drawable.pina)));
                    //    dstnMarker.setTag(1);
                    dstnMarker.setDraggable(true);
                    dstnMarker.setTitle("Destination");
                    destination=point;
                    requestDirection();
                }

            }});

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                System.out.println("marker tag :"+marker.getTag());

                try{

                    if(marker.getTag().toString().startsWith("I")) {

                        Item item = apiData.getItems().get(Integer.parseInt(marker.getTag().toString().replace("I","")));


                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MapActivity.this);

//                        builderSingle.setTitle(
//                                item.getLname().substring(0, 20) + "...\n Arr :" + item.getArrtime() + "   Dist :" + item.getDistance()
//                        );

//                        builderSingle.setIcon(R.drawable.ic_swap_calls_black_24dp);
//                        builderSingle.setTitle(item.getLname().substring(0, 20) + "...");
                        final ArrrayAdapter Adapter = new ArrrayAdapter(MapActivity.this, item.getWlist());

                        final ListView modeList = new ListView(MapActivity.this);
                        modeList.setAdapter(Adapter);


                        builderSingle.setView(modeList);
                        builderSingle.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.show();
                    }else if(marker.getTag().toString().startsWith("S")){
                        MStep step = apiData.getSteps().get(Integer.parseInt(marker.getTag().toString().replace("S","")));
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MapActivity.this);

//                        builderSingle.setTitle(
//                                step.getStep().getManeuver() + "...\n Arr :" + step.getArrtime() + "   Dist :" + step.getAft_distance()
//                        );

//                        builderSingle.setIcon(R.drawable.ic_swap_calls_black_24dp);
//                        builderSingle.setTitle(step.getStep().getManeuver() + "...");
                       // builderSingle.setMessage("Arrival:"+step.getArrtime()+"\n"+"Distance:"+step.getAft_distance()+"\n");
                        final ArrrayAdapter Adapter = new ArrrayAdapter(MapActivity.this, step.getWlist());

                        final ListView modeList = new ListView(MapActivity.this);
                        modeList.setAdapter(Adapter);


                        builderSingle.setView(modeList);
                        builderSingle.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


                return false;
            }
        });




        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            @Override
            public void onMarkerDrag(Marker marker) {

            }
            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng newLocation = marker.getPosition();

                if(marker!=null){
                    System.out.println("tag :"+marker.getTag());
                    System.out.println("title :"+marker.getTitle());
                }else{
                    System.out.println(" marker is null babes ");
                }

                if(marker.getTitle().equals(originMarker.getTitle())) {
                    origin = newLocation;
                    //     googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 15.0f));

                }else if(marker.getTitle().equals(dstnMarker.getTitle())){
                    destination = newLocation;
                    //     googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destination, 15.0f));
                }
                requestDirection();
            }
            @Override
            public void onMarkerDragStart(Marker marker) {}

        });
    }


            public boolean checkLocationPermission(){
                if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Asking user if explanation is needed
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                        //Prompt the user once explanation has been shown
                        ActivityCompat.requestPermissions(this,
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);


                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(this,
                                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);
                    }
                    return false;
                } else {
                    return true;
                }
            }



    public void requestDirection() {
        apiData=null;
        weatherloaded=false;
        routeloaded=false;
        markersInterm.clear();
        markersSteps.clear();

        if(origin!=null && destination!=null) {
            googleMap.clear();
            custom_dialog.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
            loading_text.setVisibility(View.VISIBLE);
           // slidingUpPanelLayout.setAlpha(0.5f);
            loading.setSpeed(1f);
            loading_text.setText("Loading Route");

            originMarker=googleMap.addMarker(new MarkerOptions().position(origin).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinb)));
            originMarker.setDraggable(true);
            originMarker.setTitle("source");

            dstnMarker=googleMap.addMarker(new MarkerOptions().position(destination).icon(BitmapDescriptorFactory.fromResource(R.drawable.pina)));
            dstnMarker.setDraggable(true);
            dstnMarker.setTitle("destination");


             new RouteApi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
             new WeatherApi().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }else{
            Toast.makeText(getApplicationContext(),"origin or destination null", Toast.LENGTH_LONG).show();
        }
    }

    static void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = new LatLng(route.getBounds().getSouthwest().getLat(),route.getBounds().getSouthwest().getLng());
        LatLng northeast =  new LatLng(route.getBounds().getNortheast().getLat(),route.getBounds().getNortheast().getLng());
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200));
    }







    private void datePicker(){

        // Get Current Date


        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        departAt.setText(dayOfMonth + " " + month[monthOfYear] + " " + String.valueOf(year).substring(2));
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.YEAR, year);

                        jstart_date_millis=cal.getTimeInMillis();

                        tiemPicker();

                        //*************Call Time Picker Here ********************

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    private void tiemPicker(){
        // Get Current Time


        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        String sHour = mHour < 10 ? "0" + mHour : "" + mHour;
                        String sMinute = mMinute < 10 ? "0" + mMinute : "" + mMinute;
                        String set_time = sHour + ":" + sMinute;
                        departAt.setText(set_time+","+departAt.getText());

                        jstart_time_millis=(mHour*60+mMinute)*60*1000;



                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
 



}

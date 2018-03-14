package com.MsoftTexas.directionandweather;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.MsoftTexas.directionandweather.Adapters.DragupListAdapter;
import com.MsoftTexas.directionandweather.Models.Apidata;
import com.MsoftTexas.directionandweather.Models.Item;
import com.MsoftTexas.directionandweather.Models.MStep;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;

import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
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
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MapActivity extends AppCompatActivity implements
        OnMapReadyCallback
        ,View.OnClickListener,
        RoutingListener{


    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;

    Boolean weatherloaded=false;
    Boolean routeloaded=false;
    TextView time;
    TextView date;
    static TextView src,dstn;
    Snackbar snackbar;
    SlidingUpPanelLayout slidingUpPanelLayout;
    long jstart_date_millis;
    long jstart_time_millis;
    private Marker originMarker, dstnMarker;
    private List<Marker> markers = new ArrayList<>();
    Apidata apiData=null;
    private List<Polyline> polylines;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyDi3B9R9hVpC9YTmOCCz_pCR1BKW3tIRGY";
   // private LatLng origin = new LatLng(21.20237812824328, 81.66264429688454);
  //  private LatLng destination = new LatLng(21.093630988713727,80.70856142789125);

    String placedata;
    TextView tw, tw2;
    ImageView RequestDirection;
     static LatLng origin = null;

     static LatLng destination = null;
    private AdapterView.OnItemClickListener mAutocompleteClickListenerS, mAutocompleteClickListenerD;
    private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback;
    protected GeoDataClient mGeoDataClientS, mGeoDataClientD;

    boolean flag = false;
    private PlaceAutocompleteAdapter mAdapterS, mAdapterD;

    SharedPreferences sd;

    private static final int[] COLORS = new int[]{R.color.colorPrimaryDark,R.color.colorPrimary,R.color.place_autocomplete_prediction_primary_text,R.color.colorAccent,R.color.primary_dark_material_light};


    RecyclerView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sd = this.getSharedPreferences("com.MsoftTexas.directionandweather", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        src =findViewById(R.id.autocomplete_source);
        dstn=findViewById(R.id.autocomplete_destination);
        RequestDirection=findViewById(R.id.request_direction);


//sliding up layout
        slidingUpPanelLayout=findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelHeight(0);


        link = (RecyclerView) findViewById(R.id.dragup_list_recycler);
        link.setLayoutManager(new LinearLayoutManager(this));


        tw = findViewById(R.id.distance);
        tw2 = findViewById(R.id.duration);

        time = findViewById(R.id.time);
        date = findViewById(R.id.date);



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
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        jstart_date_millis=c.getTimeInMillis()-((mHour*60+mMinute)*60*1000);
        jstart_time_millis=(mHour*60+mMinute)*60*1000;
        date.setText(mDay+"-"+(mMonth+1)+"-"+mYear);

        String sHour = mHour < 10 ? "0" + mHour : "" + mHour;
        String sMinute = mMinute < 10 ? "0" + mMinute : "" + mMinute;
        String curr_time = sHour + ":" + sMinute;
        time.setText(curr_time);

        // Set up the 'clear text' button that clears the text in the autocomplete view
        Button clearButton = findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });




        date.setOnClickListener(new View.OnClickListener() {

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(getApplicationContext(),MapActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_retry){
            requestDirection();
            Toast.makeText(this, "retry", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {
        System.out.println("routing data has started........");
    }

    @Override
    public void onRoutingSuccess(ArrayList<com.directions.route.Route> route, int shortestRouteIndex) {

        routeloaded=true;

        if(weatherloaded){
            snackbar.dismiss();
        }else{
            snackbar.setText("loading weather...");

        }

        System.out.println("here is the route data :\n"+new Gson().toJson(route));

        System.out.println("direction success.............babes.......");
        polylines = new ArrayList<>();
        //add route(s) to the map.

        tw.setText("("+route.get(0).getDistanceText()+")");
        tw2.setText(route.get(0).getDurationText());
if (route.get(0).getDurationText()!=null){
    slidingUpPanelLayout.setPanelHeight(getApplicationContext().getResources().getDimensionPixelSize(R.dimen.dragupsize));
}


        System.out.println("route options : "+route.size());
        for (int i = 0; i < route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());


            Polyline polyline = googleMap.addPolyline(polyOptions);
            polylines.add(polyline);

        }

        setCameraWithCoordinationBounds(route.get(0));
    }

    @Override
    public void onRoutingCancelled() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;

        //ON     googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(36,41),4) );
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

                    Item item= apiData.getItems().get(Integer.parseInt(marker.getTag().toString()));



                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(MapActivity.this);

                    builderSingle.setTitle(
                            item.getLname().substring(0,20)+ "...\n Arr :"+item.getArrtime()+"   Dist :"+item.getDistance()
                    );




                    //              System.out.println("size of list : " + forcastdata.size());
                    final ArrrayAdapter Adapter = new ArrrayAdapter(MapActivity.this,item.getWlist());



                    final ListView modeList = new ListView(MapActivity.this);
                    //  String[] stringArray = new String[] { "Bright Mode", "Normal Mode" };
                    //ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
                    modeList.setAdapter(Adapter);

//             builder.setView(modeList);
//             final Dialog dialog = builder.create();
//
//             dialog.show();

                    builderSingle.setView(modeList);
                    builderSingle.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builderSingle.show();
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



    public void requestDirection() {
        apiData=null;
        weatherloaded=false;
        routeloaded=false;
       snackbar= Snackbar.make(RequestDirection, "loading...",30000);
       snackbar.show();
        if(origin!=null && destination!=null) {
            googleMap.clear();

            originMarker=googleMap.addMarker(new MarkerOptions().position(origin).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinb)));
            originMarker.setDraggable(true);
            originMarker.setTitle("source");

            dstnMarker=googleMap.addMarker(new MarkerOptions().position(destination).icon(BitmapDescriptorFactory.fromResource(R.drawable.pina)));
            dstnMarker.setDraggable(true);
            dstnMarker.setTitle("destination");


             new Routing.Builder()
                    .travelMode(Routing.TravelMode.DRIVING)
                    .withListener(MapActivity.this)
                    .key(serverKey)
                    .waypoints(origin,destination)
                    .build()
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
             new MapActivity.apidata().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }else{
            Toast.makeText(getApplicationContext(),"origin or destination null", Toast.LENGTH_LONG).show();
        }
    }

    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = new LatLng(route.getLatLgnBounds().southwest.latitude,route.getLatLgnBounds().southwest.longitude);
        LatLng northeast =  new LatLng(route.getLatLgnBounds().northeast.latitude,route.getLatLgnBounds().northeast.longitude);
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
    }







    private void datePicker(){

        // Get Current Date


        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                        time.setText(set_time);

                        jstart_time_millis=(mHour*60+mMinute)*60*1000;



                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class apidata extends AsyncTask<Object,Object,Apidata> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Apidata apidata) {
            System.out.println("weather data call has started........");
               weatherloaded=true;
            if(routeloaded){
                snackbar.dismiss();
            }else{
                snackbar.setText("loading route...");
            }
            System.out.println("here is the list of intermediate Points:");

            apiData=apidata;
            int c=-1;
            if(apidata!=null && apidata.getItems()!=null){
                for(final Item item:apidata.getItems()) {
                    c++;
                    System.out.println(new Gson().toJson(item));
                    //   googleMap.addMarker(new MarkerOptions().position(item.getPoint()));
                    final int finalC = c;
                    Glide.with(getApplicationContext())
                            .load(item.getWlist().get(0).getImgurl())
                            .asBitmap()
                            .fitCenter()
                            .into(new SimpleTarget<Bitmap>(90, 90) {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    Marker marker = googleMap.addMarker(new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromBitmap(resource))
                                            .position(new LatLng(item.getPoint().getLatitude(), item.getPoint().getLongitude())));
                                    marker.setTag(finalC);
                                    markers.add(marker);
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
                link.setAdapter(new DragupListAdapter(getApplicationContext(), apidata.getSteps()));
                for(final MStep mStep:apidata.getSteps()) {
                    c++;
                    System.out.println(new Gson().toJson(mStep));
                    //   googleMap.addMarker(new MarkerOptions().position(item.getPoint()));
                    final int finalC = c;
                    Glide.with(getApplicationContext())
                            .load(mStep.getWlist().get(0).getImgurl())
                            .asBitmap()
                            .fitCenter()
                            .into(new SimpleTarget<Bitmap>(90, 90) {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    Marker marker = googleMap.addMarker(new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromBitmap(resource))
                                            .position(new LatLng(mStep.getStep().getStart_location().getLat(),mStep.getStep().getStart_location().getLng())));
                                    marker.setTag(finalC);
                                    markers.add(marker);
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

            }

        }

        @Override
        protected Apidata doInBackground(Object[] objects) {
            try {


                HttpClient client = new DefaultHttpClient();


                HttpResponse response = null;

                //nbsc-1518068960369.appspot.com
                System.out.println("here is the fk url : "+"http://c1789636.ngrok.io/_ah/api/myapi/v1/wdata?olat="
                        +origin.latitude+"&olng="+origin.longitude
                        +"&dlat="+destination.latitude+"&dlng="+destination.longitude
                        +"&jstime="+(jstart_date_millis+jstart_time_millis));
                HttpGet request = new HttpGet("https://nbsc-1518068960369.appspot.com/_ah/api/myapi/v1/wdata?olat="
                        +origin.latitude+"&olng="+origin.longitude
                        +"&dlat="+destination.latitude+"&dlng="+destination.longitude
                        +"&jstime="+(jstart_date_millis+jstart_time_millis));
                BufferedReader rd=null;
                try {
                    response = client.execute(request);
                    rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    return new Gson().fromJson(rd,Apidata.class);
                } catch (Exception e) {
                    System.out.println("error : " + e.toString());


                    String line="";
                    while ((line=rd.readLine())!=null){
                        System.out.println(line+"\n");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }



}

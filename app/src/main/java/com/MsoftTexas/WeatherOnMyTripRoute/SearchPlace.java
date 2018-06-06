package com.MsoftTexas.WeatherOnMyTripRoute;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.MsoftTexas.WeatherOnMyTripRoute.Adapters.PlaceAutocompleteAdapter;
import com.MsoftTexas.WeatherOnMyTripRoute.Models.MPlace;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchPlace extends AppCompatActivity {
    private AdapterView.OnItemClickListener  mAutocompleteClickListenerD;
    private PlaceAutocompleteAdapter mAdapterD;
    protected GeoDataClient  mGeoDataClientD;
    private AutoCompleteTextView mAutocompleteViewD;
    private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback;
    SharedPreferences sd;
    List<MPlace> recentSearches=new ArrayList<>();
    ListView recent_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_place);
        sd = this.getSharedPreferences("com.MsoftTexas.WeatherOnMyTripRoute", Context.MODE_PRIVATE);
        mAutocompleteViewD =findViewById(R.id.as);
     //   mAutocompleteViewD.setBackground(getResources().getDrawable(R.drawable.recta));
           
        recent_listview =findViewById(R.id.listviewRecentSearch);
        recent_listview.setDividerHeight(0);
//        mAutocompleteViewS.setOnItemClickListener(mAutocompleteClickListenerS);
        mAutocompleteViewD.setOnItemClickListener(mAutocompleteClickListenerD);

        mGeoDataClientD = Places.getGeoDataClient(this, null);


        mAdapterD = new PlaceAutocompleteAdapter(this, mGeoDataClientD, null, null);

        mAutocompleteViewD.setAdapter(mAdapterD);

        final Gson gson = new Gson();
        if(!sd.getString("PlaceSaver", "").equals("")) {
            String json1 = sd.getString("PlaceSaver", "");

            PlaceSaverObject obj = gson.fromJson(json1, PlaceSaverObject.class);
            recentSearches = obj.getList();
            Collections.reverse(recentSearches);
        }

        Place_name_listView place_name_listView =new Place_name_listView(getApplicationContext(),recentSearches);
        recent_listview.setAdapter(place_name_listView);


        if(getIntent().getStringExtra("SrcOrDstn")!=null && getIntent()!=null) {
            if (getIntent().getStringExtra("SrcOrDstn").equals("Src")) {
                mAutocompleteViewD.setHint("Start Address...");
            }else if(getIntent().getStringExtra("SrcOrDstn").equals("Dstn")){
                mAutocompleteViewD.setHint("Destination Address...");
            }
        }

        recent_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Object item = arg0.getItemAtPosition(arg2);
                System.out.println(recentSearches.get(arg2).getAdress()+""+recentSearches.get(arg2).getName());

                try {
                    System.out.println("save fn executed....");
                    Thread thread =new Thread(new PlaceSaver(sd,new MPlace(recentSearches.get(arg2).getId(),recentSearches.get(arg2).getName().toString(),recentSearches.get(arg2).getAdress().toString(),recentSearches.get(arg2).getLatLng())));
                    thread.start();
                }catch (Error e){
                    System.out.println("save fn error");
                }
                Toast.makeText(getApplicationContext(),recentSearches.get(arg2).getName(),Toast.LENGTH_LONG).show();
                if(getIntent().getStringExtra("SrcOrDstn").equals("Src")){

                    MapActivity.src.setText(recentSearches.get(arg2).getAdress());

                    MapActivity.origin=recentSearches.get(arg2).getLatLng();

                    if(TextUtils.isEmpty(MapActivity.dstn.getText())){
                        Intent intent=new Intent(SearchPlace.this,SearchPlace.class);
                        intent.putExtra("SrcOrDstn","Dstn");

                        startActivity(intent);

                    }else{
                        MapActivity.go.setBackground(getResources().getDrawable(R.drawable.send_blue));
                    }

                }else{
                    MapActivity.dstn.setText(recentSearches.get(arg2).getAdress());
                    MapActivity.destination=recentSearches.get(arg2).getLatLng();

                    if(TextUtils.isEmpty(MapActivity.src.getText())){
                        Intent intent=new Intent(SearchPlace.this,SearchPlace.class);
                        intent.putExtra("SrcOrDstn","Src");
                        startActivity(intent);
                    }else{
                        MapActivity.go.setBackground(getResources().getDrawable(R.drawable.send_blue));
                    }
                }
                finish();

            }
        });

    }



    {
        mAutocompleteClickListenerD = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a AutocompletePrediction from which we
             read the place ID and title.
              */

                final AutocompletePrediction item = mAdapterD.getItem(position);
                final String placeId = item.getPlaceId();
                final CharSequence primaryText = item.getPrimaryText(null);

                Log.i("TAG", "Autocomplete item selected: " + primaryText);



            /*
             Issue a request to the Places Geo Data Client to retrieve a Place object with
             additional details about the place.
              */
                Task<PlaceBufferResponse> placeResult = mGeoDataClientD.getPlaceById(placeId);
                placeResult.addOnCompleteListener(mUpdatePlaceDetailsCallback);

                Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,
                        Toast.LENGTH_SHORT).show();
                Log.i("TAG", "Called getPlaceById to get Place details for " + placeId);
            }
        };
    }

    {
        mUpdatePlaceDetailsCallback = new OnCompleteListener<PlaceBufferResponse>() {
            @Override
            public void onComplete(Task<PlaceBufferResponse> task) {
                try {
                    PlaceBufferResponse places = task.getResult();

                    // Get the Place object from the buffer.
                    final Place place = places.get(0);
                    System.out.println("place id:"+place.getId());
                    System.out.println("locate :"+place.getLocale());
                    System.out.println("attribution :"+place.getAttributions());
                    System.out.println("place name:"+place.getName());
                    System.out.println("place address :"+place.getName());

                    try {
                        System.out.println("save fn executed....");
                        Thread thread =new Thread(new PlaceSaver(sd,new MPlace(place.getId(),place.getName().toString(),place.getAddress().toString(),place.getLatLng())));
                        thread.start();
                    }catch (Error e){
                        System.out.println("save fn error");
                    }

                    if(getIntent().getStringExtra("SrcOrDstn").equals("Src")){

                        MapActivity.src.setText(place.getAddress());
                        MapActivity.origin=place.getLatLng();
                        if(TextUtils.isEmpty(MapActivity.dstn.getText())){
                            Intent intent=new Intent(SearchPlace.this,SearchPlace.class);
                            intent.putExtra("SrcOrDstn","Dstn");
                            startActivity(intent);
                        }else{
                            MapActivity.go.setBackground(getResources().getDrawable(R.drawable.send_blue));
                        }

                    }else{

                        MapActivity.dstn.setText(place.getAddress());
                        MapActivity.destination=place.getLatLng();
                        if(TextUtils.isEmpty(MapActivity.src.getText())){
                            Intent intent=new Intent(SearchPlace.this,SearchPlace.class);
                            intent.putExtra("SrcOrDstn","Src");
                            startActivity(intent);
                        }else{
                            MapActivity.go.setBackground(getResources().getDrawable(R.drawable.send_blue));
                        }
                    }
               // Intent intent = new Intent(SearchPlace.this, MapActivity.class);
               //     MPlace mPlace =new MPlace(place.getId(),place.getName().toString(),place.getAddress().toString(),place.getLatLng());
              //      intent.putExtra("SrcOrDstn",getIntent().getStringExtra("SrcOrDstn"));
            //    intent.putExtra("PlaceData",new Gson().toJson(mPlace));
            //    startActivity(intent);
                finish();


                    System.out.println("selected place : " + place.getAddress());

                    places.release();
                } catch (RuntimeRemoteException e) {
                    // Request did not complete successfully
                    Log.e("TAG", "Place query did not complete.", e);
                    return;
                }
            }
        };

    }

}

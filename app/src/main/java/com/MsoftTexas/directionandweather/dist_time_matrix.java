package com.example.kamlesh.directionandweather;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dist_time_matrix extends AsyncTask<Object ,Integer,String>
{
    @Override
    protected void onPreExecute () {

    }

    @Override
    protected void onPostExecute (String s){
        System.out.println("here on PostExecute fn");

    }

    @Override
    protected void onProgressUpdate (Integer...values){

    }

    @Override
    protected String doInBackground (Object...params){
        System.out.println("param 0 :"+params[0]);
        System.out.println("param 1 :"+params[1]);
        System.out.println("param 2 :"+params[2]);
        System.out.println("param 3 :"+params[3]);

        try {

            String url ="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+params[0]+","+params[1]+"&destinations="+params[2]+","+params[3]+"&key="+params[4];

            System.out.println("heare is the url :\n"+url);

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);


            HttpResponse response = null;
            String result = "";
            try {
                response = client.execute(request);


                BufferedReader rd = new BufferedReader
                        (new InputStreamReader(
                                response.getEntity().getContent()));


                String line = "";
                while ((line = rd.readLine()) != null) {
                    result += line;
                    System.out.println(line);
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error : " + e.toString());
                return e.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }

    }

}
package com.example.beerapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;



import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SplashScreen extends Activity {

    LoadAllData loadAllData = new LoadAllData();
    static String url_all_data = "http://51.38.236.200/api_beer/open-beer-database.json";
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON success
    public static final String TAG_BEER = "beers";
    public static final String TAG_BREWERY = "brewery";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ALCOHOL_DEGREE = "alcohol_by_volume";
    public static final String KEY_DESCBEER = "descbeer";
    public static final String KEY_STYLE = "style_name";
    public static final String KEY_BREWERY = "address1";
    public static final String KEY_ADDRESS = "address2";
    public static final String KEY_CITY = "city";
    public static final String KEY_CODE = "code";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_WEBSITE = "website";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        // passe l'activity si il n'y a pas de connection internet
        if(!isConnectedInternet(SplashScreen.this)) {
            Toast.makeText(SplashScreen.this,"Pas de connection Internet, les données ne seront pas mises à jour !",Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(2000); // wait 2 minutes
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent1 = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }
            },400);
        } else {
            loadAllData.execute();
        }
    }

    public static boolean isConnectedInternet(Activity activity)
    {
        //test internet connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            NetworkInfo.State networkState = networkInfo.getState();
            if (networkState.compareTo(NetworkInfo.State.CONNECTED) == 0)
            {
                return true;
            }
            else return false;
        }
        else return false;
    }

    private class LoadAllData extends AsyncTask<String, String, String> {

        String  name, descbeer, style, brewery, address, city, country, phone, website;
        int lengthdata, id, alcohol_degree, code;

        JSONArray jsonbeer = null;

         beer[] BDDbeer;

        beerManager beerManager = new beerManager(SplashScreen.this); // gestionnaire de la table "collecte"

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            beerManager.open();
            beerManager.supAllbeer();
        }

        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_data, "GET", params);
            try {
                // Getting Array of partenariat
                    jsonbeer = json.getJSONArray(TAG_BEER);

                    lengthdata = jsonbeer.length();
                    BDDbeer = new beer[lengthdata];

                    // looping through All data
                    for (int i = 0; i < lengthdata; i++) {
                        //element
                        JSONObject c = jsonbeer.getJSONObject(i);
                        //data
                        id = c.getInt(KEY_ID);
                        name = c.getString(KEY_NAME);
                        alcohol_degree = c.getInt(KEY_ALCOHOL_DEGREE);
                        descbeer = c.getString(KEY_DESCBEER);
                        style = c.getString(KEY_STYLE);

                        //detail element
                        JSONObject c1 = c.getJSONObject(TAG_BREWERY);

                        brewery = c1.getString(KEY_BREWERY);
                        address = c1.getString(KEY_ADDRESS);
                        city = c1.getString(KEY_CITY);
                        code = c1.getInt(KEY_CODE);
                        country = c1.getString(KEY_COUNTRY);
                        phone = c1.getString(KEY_PHONE);
                        website = c1.getString(KEY_WEBSITE);


                        //add in BDDTAB
                        BDDbeer[i] = new beer(id, name, alcohol_degree, descbeer, style, brewery, address, city, code, country, phone, website);
                    }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //add all tab in table
            beerManager.addBeer(BDDbeer, lengthdata);
            return null;
        }

        //action after DL
        protected void onPostExecute(String file_url) {
            Intent intentMain = new Intent(SplashScreen.this, MainActivity.class);
            intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentMain);
            finish();
        }
    }
}


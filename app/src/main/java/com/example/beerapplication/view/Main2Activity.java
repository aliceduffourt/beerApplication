package com.example.beerapplication.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beerapplication.R;
import com.example.beerapplication.controlleur.beerManager;

public class Main2Activity extends AppCompatActivity {

    TextView beeralcohol_degree, beerdescbeer, beerstyle, beerbrewery, beeraddress, beercity, beerphone, beerwebsite;
    String id;
    String name;
    float alcohol_degree;
    String descbeer;
    String style;
    String brewery;
    String address;
    String city;
    int code;
    String country;
    String phone;
    String website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = getIntent().getExtras().getString("id");

        beeralcohol_degree = findViewById(R.id.beeralcohol_degree);
        beerdescbeer = findViewById(R.id.beerdescbeer);
        beerstyle = findViewById(R.id.beerstyle);
        beerbrewery = findViewById(R.id.beerbrewery);
        beeraddress = findViewById(R.id.beeraddress);
        beercity = findViewById(R.id.beercity);
        beerphone = findViewById(R.id.beerphone);
        beerwebsite = findViewById(R.id.beerwebsite);


        beerManager bm = new beerManager(Main2Activity.this);
        bm.open();

        Cursor c = bm.getinfobeer(Integer.parseInt(id));

        if (c.moveToFirst())
        {

                    name = c.getString(c.getColumnIndex(beerManager.KEY_NAME));
                    alcohol_degree = c.getInt(c.getColumnIndex(beerManager.KEY_ALCOHOL_DEGREE));
                    descbeer = c.getString(c.getColumnIndex(beerManager.KEY_DESCBEER));
                    style = c.getString(c.getColumnIndex(beerManager.KEY_STYLE));
                    brewery = c.getString(c.getColumnIndex(beerManager.KEY_BREWERY));
                    address = c.getString(c.getColumnIndex(beerManager.KEY_ADDRESS));
                    city = c.getString(c.getColumnIndex(beerManager.KEY_CITY));
                    code = c.getInt(c.getColumnIndex(beerManager.KEY_CODE));
                    country = c.getString(c.getColumnIndex(beerManager.KEY_COUNTRY));
                    phone = c.getString(c.getColumnIndex(beerManager.KEY_PHONE));
                    website = c.getString(c.getColumnIndex(beerManager.KEY_WEBSITE));
        }
        c.close();

        getSupportActionBar().setTitle(name);
        beeralcohol_degree.setText("Alcohol degree : "+String.valueOf(alcohol_degree));
        beerdescbeer.setText("Description beer :\n"+descbeer);
        beerstyle.setText("Style : "+style);
        beerbrewery.setText("Brewery : "+brewery);
        beeraddress.setText("Adress :\n"+address);
        beercity.setText(code+" "+city+", "+country+ "\n");
        beerphone.setText("Phone : "+phone);
        beerwebsite.setText("Website : "+website);
    }
}

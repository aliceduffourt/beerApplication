package com.example.beerapplication;

import android.database.Cursor;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    private List<beer> beerList = new ArrayList<>();
    beer[] test = new beer[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_beer);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(layoutManager);

        beerManager bm = new beerManager(this);
        bm.open();

        Cursor c = bm.getAllbeer();
        //Compte nbre donnÃ©e enregistrÃ©
        if (c.moveToFirst())
        {
            do {

                beerList.add(new beer(c.getInt(c.getColumnIndex(beerManager.KEY_ID)),
                        c.getString(c.getColumnIndex(beerManager.KEY_NAME)),
                        c.getInt(c.getColumnIndex(beerManager.KEY_ALCOHOL_DEGREE)),
                        c.getString(c.getColumnIndex(beerManager.KEY_DESCBEER)),
                        c.getString(c.getColumnIndex(beerManager.KEY_STYLE)),
                        c.getString(c.getColumnIndex(beerManager.KEY_BREWERY)),
                        c.getString(c.getColumnIndex(beerManager.KEY_ADDRESS)),
                        c.getString(c.getColumnIndex(beerManager.KEY_CITY)),
                        c.getInt(c.getColumnIndex(beerManager.KEY_CODE)),
                        c.getString(c.getColumnIndex(beerManager.KEY_COUNTRY)),
                        c.getString(c.getColumnIndex(beerManager.KEY_PHONE)),
                        c.getString(c.getColumnIndex(beerManager.KEY_WEBSITE))));
            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(beerList);
        recyclerView.setAdapter(mAdapter);

        Spinner spinnersort = findViewById(R.id.sort);
        ArrayAdapter<CharSequence> adaptersort = ArrayAdapter.createFromResource(this, R.array.SORT, R.layout.spinner_style);
        adaptersort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersort.setAdapter(adaptersort);
        spinnersort.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sortchoice = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), sortchoice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

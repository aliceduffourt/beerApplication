package com.example.beerapplication.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beerapplication.R;
import com.example.beerapplication.Modele.beer;
import com.example.beerapplication.controlleur.beerManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
    private List<beer> beerList = new ArrayList<>();
    private EditText recherche;
    private String word = "";
    private List<beer> beerListsearch = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_beer);
        recherche = findViewById(R.id.research);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(layoutManager);

        recherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                word = String.valueOf(charSequence);
                search();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getlistsort(0);

        Spinner spinnersort = findViewById(R.id.sort);
        ArrayAdapter<CharSequence> adaptersort = ArrayAdapter.createFromResource(MainActivity.this, R.array.SORT, R.layout.spinner_style);
        adaptersort.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersort.setAdapter(adaptersort);
        spinnersort.setOnItemSelectedListener(MainActivity.this);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getlistsort(position);
    }

    public void getlistsort(int sortchoice){

        beerList.clear();
        beerManager bm = new beerManager(MainActivity.this);
        bm.open();
        Cursor c = null;

        if (sortchoice == 0){
            c = bm.getAllbeer();
            Toast.makeText(MainActivity.this, "A->Z", Toast.LENGTH_LONG).show();

        }

        if (sortchoice == 1){
            c = bm.getAllbeer2();
            Toast.makeText(MainActivity.this, "Z->A", Toast.LENGTH_SHORT).show();

        }

        if (sortchoice == 2){
            c = bm.getAllbeer3();
            Toast.makeText(MainActivity.this, "Lowest alcohol degree", Toast.LENGTH_SHORT).show();

        }

        if (sortchoice == 3){
            c = bm.getAllbeer4();
            Toast.makeText(MainActivity.this, "Highest alcohol degree", Toast.LENGTH_SHORT).show();

        }

        if (c.moveToFirst())
        {
            do {

                beerList.add(new beer(c.getInt(c.getColumnIndex(beerManager.KEY_ID)),
                        c.getString(c.getColumnIndex(beerManager.KEY_NAME)),
                        c.getFloat(c.getColumnIndex(beerManager.KEY_ALCOHOL_DEGREE)),
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
        search();
    }

    public void search() {

        beerListsearch.clear();

        for (int i=0; i<beerList.size(); i++){
            if(word == ""){
                beerListsearch.add(beerList.get(i));
            }
            else if(beerList.get(i).getName().toLowerCase().contains(word.toLowerCase())){
                beerListsearch.add(beerList.get(i));
            }
        }

        if (beerListsearch.size()==0){
            Toast.makeText(MainActivity.this, "No beer matches your search", Toast.LENGTH_SHORT).show();
        }

        mAdapter = new MyAdapter(beerListsearch, MainActivity.this);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

package com.example.beerapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// manager de la base avec la fonction de création de la table ainsi que toutes les fonctions d'appel de la table
public class beerManager {

    // définition des variables globales utilisé dans le manager
    private static final String TABLE_NAME = "Beer";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ALCOHOL_DEGREE = "alcohol_degree";
    private static final String KEY_DESCBEER = "beer_description";
    private static final String KEY_STYLE = "style";
    private static final String KEY_BREWERY = "brewery";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_CODE = "postal_code";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_WEBSITE = "website";



    //création de la table
    public static final String CREATE_TABLE_BEER= "CREATE TABLE "+TABLE_NAME+
            " (" +
            KEY_ID + " INTEGER primary key," +
            KEY_NAME + " TEXT," +
            KEY_ALCOHOL_DEGREE + " INTEGER," +
            KEY_DESCBEER + " TEXT," +
            KEY_STYLE + " TEXT," +
            KEY_BREWERY + " TEXT," +
            KEY_ADDRESS + " TEXT," +
            KEY_CITY + " TEXT," +
            KEY_CODE + " INTEGER," +
            KEY_COUNTRY + " TEXT," +
            KEY_PHONE + " TEXT," +
            KEY_WEBSITE + " TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db; // base de données

    // Constructeur
    public beerManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        //on ouvre la table en lecture/Ã©criture
        db = maBaseSQLite.getWritableDatabase();
    }

    // fonction d'ajout dans la base avec envoi de tableau
    public void addBeer(beer[] beer, int taille) {

        ContentValues values = new ContentValues();
        int i;

        // début de transaction
        db.beginTransaction();
        for (i=0;i<taille;i++) {
            values.put(KEY_ID, beer[i].getId());
            values.put(KEY_NAME, beer[i].getName());
            values.put(KEY_ALCOHOL_DEGREE, beer[i].getAlcohol_degree());
            values.put(KEY_DESCBEER, beer[i].getDescbeer());
            values.put(KEY_STYLE, beer[i].getStyle());
            values.put(KEY_BREWERY, beer[i].getBrewery());
            values.put(KEY_ADDRESS, beer[i].getAdresse());
            values.put(KEY_CITY, beer[i].getCity());
            values.put(KEY_CODE, beer[i].getCode());
            values.put(KEY_COUNTRY, beer[i].getCountry());
            values.put(KEY_PHONE, beer[i].getPhone());
            values.put(KEY_WEBSITE, beer[i].getWebsite());

            db.insert(TABLE_NAME,null,values);
        }
        // fin de transaction
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    // fonction de supression de tous les champs de la table
    public void supAllbeer() {
        db.execSQL("DELETE from " + TABLE_NAME);
    }


    public Cursor getAllbeer() {
        // sélection de tous les collectes
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_NAME+" ASC", null);
    }

    public Cursor getAllbeer2() {
        // sélection de tous les collectes
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_NAME+" Desc", null);
    }

    public Cursor getAllbeer3() {
        // sélection de tous les collectes
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_ALCOHOL_DEGREE+" ASC", null);
    }

    public Cursor getAllbeer4() {
        // sélection de tous les collectes
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+KEY_ALCOHOL_DEGREE+" Desc", null);
    }

    public Cursor getinfobeer(int id) {
        // sélection de tous les collectes
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID+" = "+id, null);
    }

} // class CollecteManager
package com.example.valentinbemetz.orccalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.valentinbemetz.orccalculator.model.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

public class BoatDatabase extends SQLiteOpenHelper {
    public static  BoatDatabase INSTANCE = null;

    private static final String DB_NAME = "BOATS";
    private static final int VERSION = 4;


    private static final String TABLE_NAME = "boats";

    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "name";
    private static final String INLO_COLUMN = "inLo";
    private static final String INMI_COLUMN = "inMi";
    private static final String INHI_COLUMN = "inHi";
    private static final String OFLO_COLUMN = "ofLo";
    private static final String OFMI_COLUMN = "ofMi";
    private static final String OFHI_COLUMN = "ofHi";
    private static final String YARDSTICK_COLUMN = "yardstick";


    private BoatDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static BoatDatabase getInstance(final Context context){
         if (INSTANCE == null){
             INSTANCE = new BoatDatabase(context);
         }
         return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN
                + " INTEGER PRIMARY KEY, " + NAME_COLUMN + " TEXT NOT NULL, "
                + INLO_COLUMN + " REAL DEFAULT NULL, " + INMI_COLUMN+ " REAL DEFAULT NULL, "
                + INHI_COLUMN + " REAL DEFAULT NULL, " + OFLO_COLUMN+ " REAL DEFAULT NULL, "
                + OFMI_COLUMN + " REAL DEFAULT NULL, " + OFHI_COLUMN+ " REAL DEFAULT NULL, "
                + YARDSTICK_COLUMN + " INTEGER DEFAULT NULL)";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);

        onCreate(db) ;
    }

    public Boat createBoat(final Boat boat){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, boat.getName());
        values.put(INLO_COLUMN, boat.getInLo());
        values.put(INMI_COLUMN, boat.getInMi());
        values.put(INHI_COLUMN, boat.getInHi());
        values.put(OFLO_COLUMN, boat.getOfLo());
        values.put(OFMI_COLUMN, boat.getOfMi());
        values.put(OFHI_COLUMN, boat.getOfHi());
        values.put(YARDSTICK_COLUMN, boat.getYardstick());

        long newId = db.insert(TABLE_NAME, null, values);

        db.close();
        return readBoat(newId);
    }

    public Boat readBoat(final long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID_COLUMN, NAME_COLUMN, INLO_COLUMN, INMI_COLUMN, INHI_COLUMN, OFLO_COLUMN, OFMI_COLUMN, OFHI_COLUMN,YARDSTICK_COLUMN}, ID_COLUMN+ " = ?", new String[]{String.valueOf(id)},null,null,null);

        Boat boat = null;

        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            boat = new Boat(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));
            boat.setId(cursor.getLong(cursor.getColumnIndex(ID_COLUMN)));
            boat.setInLo(cursor.getDouble(cursor.getColumnIndex(INLO_COLUMN)));
            boat.setInMi(cursor.getDouble(cursor.getColumnIndex(INMI_COLUMN)));
            boat.setInHi(cursor.getDouble(cursor.getColumnIndex(INHI_COLUMN)));
            boat.setOfLo(cursor.getDouble(cursor.getColumnIndex(OFLO_COLUMN)));
            boat.setOfMi(cursor.getDouble(cursor.getColumnIndex(OFMI_COLUMN)));
            boat.setOfHi(cursor.getDouble(cursor.getColumnIndex(OFHI_COLUMN)));
            boat.setYardstick(cursor.getInt(cursor.getColumnIndex(YARDSTICK_COLUMN)));

        }

        db.close();
        return boat;
    }

    public List<Boat> readAllBoats(){

        List<Boat> boats = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        if (cursor.moveToFirst()){
            do{
                Boat boat = readBoat(cursor.getLong(cursor.getColumnIndex(ID_COLUMN)));
                if(boat != null) {
                    boats.add(boat);
                }
            }while (cursor.moveToNext());
        }
        db.close();
        return boats;
    }

    public Boat updateBoat(final Boat boat){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COLUMN, boat.getName());
        values.put(INLO_COLUMN, boat.getInLo());
        values.put(INMI_COLUMN, boat.getInMi());
        values.put(INHI_COLUMN, boat.getInHi());
        values.put(OFLO_COLUMN, boat.getOfLo());
        values.put(OFMI_COLUMN, boat.getOfMi());
        values.put(OFHI_COLUMN, boat.getOfHi());
        values.put(YARDSTICK_COLUMN, boat.getYardstick());

        db.update(TABLE_NAME, values, ID_COLUMN +" = ?", new String[]{String.valueOf(boat.getId())});

        db.close();

        return this.readBoat(boat.getId());

    }

    public void deleteBoat(final Boat boat){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COLUMN +" = ?", new String[]{String.valueOf(boat.getId())});

        db.close();

    }




}

package com.example.classactivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.text.ParseException;
import java.util.ArrayList;

public class DB_Con extends SQLiteOpenHelper {

    public DB_Con(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super( context, name, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "CREATE TABLE ClassActivity ( ID INTEGER PRIMARY KEY, Name VARCHAR(50), DOB VARCHAR(50), Email VARCHAR(50))";
        db.execSQL( Query );
    }

    @SuppressLint("SimpleDateFormat")
    public ArrayList<EmployeeData> EmployeeList() {
        String Query = "SELECT * FROM ClassActivity";
        SQLiteDatabase db = this.getReadableDatabase( );

        ArrayList<EmployeeData> EmployeeList = new ArrayList<EmployeeData>( );
        Cursor cursor = db.rawQuery( Query, null );
        if (cursor.moveToFirst( )) {
            do {
                int ID = Integer.parseInt( cursor.getString( 0 ) );
                String Name = cursor.getString( 1 );
                int Salary = Integer.parseInt( cursor.getString( 2 ) );

                EmployeeList.add( new EmployeeData( ID, Name, Salary ) );
            } while (cursor.moveToNext( ));
        }
        cursor.close( );
        return EmployeeList;
    }

    @SuppressLint("SimpleDateFormat")
    public int AddEmployee(EmployeeData Data) {
        ContentValues values = new ContentValues( );

        values.put( "ID", Data.getID( ) );
        values.put( "Name", Data.getName( ) );
        values.put( "Salary", Data.getSalary( ) );

        SQLiteDatabase db = this.getWritableDatabase( );
        return (int) db.insert( "ClassActivity", null, values );
    }

    public int UpdateEmployee(EmployeeData Data) {
        ContentValues values = new ContentValues( );
        values.put( "Name", Data.getName( ) );
        values.put( "Salary", Data.getSalary( ) );

        SQLiteDatabase db = this.getWritableDatabase( );
        return (int) db.update( "ClassActivity", values, "ID = ?", new String[]{String.valueOf( Data.getID( ) )} );
    }

    public int DeleteEmployee(int ID) {
        SQLiteDatabase db = this.getWritableDatabase( );
        return (int) db.delete( "ClassActivity", "ID = ?", new String[]{String.valueOf( ID )} );
    }

    @SuppressLint("SimpleDateFormat")
    public EmployeeData FindEmployee(int ID) throws ParseException {
        String Query = "SELECT * FROM ClassActivity WHERE ID = " + ID;
        SQLiteDatabase db = this.getWritableDatabase( );

        EmployeeData Data = null;

        Cursor cursor = db.rawQuery( Query, null );
        if (cursor.moveToFirst( )) {
            Data = new EmployeeData( Integer.parseInt( cursor.getString( 0 ) ), cursor.getString( 1 ), Integer.parseInt( cursor.getString( 2 ) ) );
        }
        cursor.close( );
        return Data;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS ClassActivity" );
        onCreate( db );
    }
}
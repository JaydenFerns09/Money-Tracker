package com.example.moneytracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ValuesDB.class}, exportSchema = false,version = 2)
public abstract class DatabaseHelper2 extends RoomDatabase {

    private static final String DB_NAME = "ValuesDB";
    private static DatabaseHelper2 instance;


    public static synchronized DatabaseHelper2 getDB(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context, DatabaseHelper2.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract ValuesDao valuesDao();

}

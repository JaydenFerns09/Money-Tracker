package com.example.moneytracker;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ValuesDao {

    @Query("select reason from `values` where `key` = :k")
    List<String> getReason(int k);

    @Query("select spending from `values` where `key` = :k")
    List<String> getSpending(int k);

    @Insert
    void addValue(ValuesDB valuesDB);
}

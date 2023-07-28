package com.example.moneytracker;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Values")
public class ValuesDB {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "key")
    private int key;

    @ColumnInfo(name = "reason")
    private String reason;

    @ColumnInfo(name = "spending")
    private String spending;

    public ValuesDB(int key, String reason, String spending) {
        this.key = key;
        this.reason = reason;
        this.spending = spending;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSpending() {
        return spending;
    }

    public void setSpending(String spending) {
        this.spending = spending;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

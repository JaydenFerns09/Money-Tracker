package com.example.moneytracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class front extends AppCompatActivity {

    Button add;
    TextView total;
    ScrollView sv;
    LinearLayout ll;



    String name;
    Integer key,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        add = findViewById(R.id.addItem);
        sv = findViewById(R.id.scroll);
        total = findViewById(R.id.totalAmount);
        ll = findViewById(R.id.linear);
        Intent i1 = getIntent();
        name = i1.getStringExtra("name");
        flag = i1.getIntExtra("flag",0);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        key = databaseHelper.userDao().getId(name);

        Integer userTotal = databaseHelper.userDao().getAmountOfUser(name);
        total.setText("Current Balance : "+userTotal);

        if(flag == 1){
            flag=0;
            DatabaseHelper2 databaseHelper2 = DatabaseHelper2.getDB(this);

            ArrayList<String> spend = (ArrayList<String>) databaseHelper2.valuesDao().getSpending(key);
            ArrayList<String> reason = (ArrayList<String>) databaseHelper2.valuesDao().getReason(key);

            Integer size = spend.size();
            for (Integer i = 0;i<size;i++){
                String s1 = spend.get(i).toString();
                String r1 = reason.get(i).toString();
                TextView textView = new TextView(this);
                textView.setText(s1 + " : " + r1);
                textView.setTextSize(20);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.WHITE);
                LayoutParams textViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(textViewLayoutParams);
                ll.addView(textView);
            }
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(front.this, addItem.class);
                i.putExtra("name",name);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){

            DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

            Integer userTotal = databaseHelper.userDao().getAmountOfUser(name);
            total.setText("Current Balance : "+userTotal);

            DatabaseHelper2 databaseHelper2 = DatabaseHelper2.getDB(this);

            ArrayList<String> spend = (ArrayList<String>) databaseHelper2.valuesDao().getSpending(key);
            ArrayList<String> reason = (ArrayList<String>) databaseHelper2.valuesDao().getReason(key);

            Integer size = spend.size();
                String s1 = spend.get(size-1).toString();
                String r1 = reason.get(size-1).toString();
                TextView textView = new TextView(this);
                textView.setText(s1 + " : " + r1);
                textView.setTextSize(20);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextColor(Color.WHITE);
                LayoutParams textViewLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(textViewLayoutParams);
                ll.addView(textView);




        }
    }
}
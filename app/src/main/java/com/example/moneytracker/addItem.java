package com.example.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addItem extends AppCompatActivity {

    EditText des,amount;
    Button add,remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        des=findViewById(R.id.description);
        amount=findViewById(R.id.newAmount);
        add=findViewById(R.id.addAmount);
        remove=findViewById(R.id.removeAmount);
        Intent i1 = getIntent();
        String name = i1.getStringExtra("name");

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
        DatabaseHelper2 databaseHelper2 = DatabaseHelper2.getDB(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d = des.getText().toString();
                String a = amount.getText().toString();
                Integer amt = Integer.parseInt(a);

                Integer am = databaseHelper.userDao().getAmountOfUser(name);

                databaseHelper.userDao().updateTotal(amt+am,name);
                Integer key = databaseHelper.userDao().getId(name);

                databaseHelper2.valuesDao().addValue(new ValuesDB(key,d,a));

                Intent i = new Intent(addItem.this,front.class);
                i.putExtra("col",0);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d = des.getText().toString();
                String a = amount.getText().toString();
                Integer amt = Integer.parseInt(a);

                Integer am = databaseHelper.userDao().getAmountOfUser(name);

                if(amt>am){
                    Toast.makeText(addItem.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                }else{
                    databaseHelper.userDao().updateTotal(am-amt,name);
                    Integer key = databaseHelper.userDao().getId(name);
                    a="-"+a;

                    databaseHelper2.valuesDao().addValue(new ValuesDB(key,d,a));
                }



                Intent i = new Intent(addItem.this,front.class);
                i.putExtra("col",1);
                setResult(RESULT_OK,i);
                finish();
            }
        });

    }
}
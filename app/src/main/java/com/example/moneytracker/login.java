package com.example.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class login extends AppCompatActivity {

    EditText Username,Password;
    Button login,revert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username=findViewById(R.id.Username_log);
        Password=findViewById(R.id.Password_log);
        login=findViewById(R.id.login_app);
        revert=findViewById(R.id.revert_log);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = Username.getText().toString();
                String pass = Password.getText().toString();
                int i;
                boolean val = false;

                ArrayList<UserDB> arrUsers = (ArrayList<UserDB>)databaseHelper.userDao().getALlUsers();

                for (i = 0; i<arrUsers.size(); i++){
                    if (Objects.equals(arrUsers.get(i).getUsername(), usr)) {
                        val=true;
                        if (Objects.equals(arrUsers.get(i).getPassword(), pass)) {
                            Intent intent = new Intent(login.this, front.class);
                            intent.putExtra("name",usr);
                            intent.putExtra("flag",1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }

                if (!val){
                    Toast.makeText(login.this,"Username doesn't exist", Toast.LENGTH_SHORT).show();
                }

            }
        });

        revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
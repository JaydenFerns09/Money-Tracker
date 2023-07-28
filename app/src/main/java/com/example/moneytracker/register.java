package com.example.moneytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class register extends AppCompatActivity {

    EditText Username,Password,ConfirmPass,total;
    Button register,revert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username=findViewById(R.id.Username_reg);
        Password=findViewById(R.id.Password_reg);
        ConfirmPass=findViewById(R.id.ConfirmPassword_reg);
        total=findViewById(R.id.total);
        register=findViewById(R.id.register_app);
        revert=findViewById(R.id.revert_reg);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usr = Username.getText().toString();
                String pass = Password.getText().toString();
                Integer tot = Integer.parseInt(total.getText().toString());
                String conpass = ConfirmPass.getText().toString();
                int i;
                boolean val = false;

                ArrayList<UserDB> arrUsers = (ArrayList)databaseHelper.userDao().getALlUsers();

                for (i=0;i<arrUsers.size();i++){
                    if(arrUsers.get(i).getUsername() == usr){
                        val = true;
                    }
                }

                if(!val){
                    if(tot>=0) {
                        if (pass.equals(conpass)) {
                            if (pass.length() >= 10) {
                                databaseHelper.userDao().addUsr(
                                        new UserDB(usr, pass, tot)
                                );

                                Intent intent = new Intent(register.this, login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(register.this, "Password should be longer than 10 characters", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(register.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(register.this, "Amount should be positive", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(register.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });



        revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(register.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
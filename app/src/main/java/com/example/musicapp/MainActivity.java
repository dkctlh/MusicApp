package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DBHelper(this);



    }
    int counter=0;
    public void logIn(View v){
        Intent hp_i =new Intent(this,home_page.class);
        Intent su_i=new Intent(this,sign_up.class);
        String u_name=((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString().toLowerCase();
        String pass=((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        EditText sunull=(EditText) findViewById(R.id.editTextTextPersonName);
        EditText spnull=(EditText) findViewById(R.id.editTextTextPassword);
         if(u_name.equals("") || pass.equals(""))
             Toast.makeText(this, "Lütfen bütün alanları doldurun!", Toast.LENGTH_LONG).show();
         else{
             Boolean checkuserpass=db.checkusernamepassword(u_name,pass);
             if (checkuserpass==true){
                 Toast.makeText(this, "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                 startActivity(hp_i);
             }else{
                 counter++;
                 if(counter == 3) {
                     Toast.makeText(this, "3 kez şifrenizi hatalı girdiniz.Lütfen tekrar kaydolun..", Toast.LENGTH_LONG).show();
                     startActivity(su_i);
                 }else {
                     Toast.makeText(this, "Kullanıcı adı ya da şifre yanlış!!", Toast.LENGTH_SHORT).show();
                     spnull.setText("");
                     sunull.setText("");
                 }


             }
         }

    }
    public void signUp(View v){
        Intent su_i=new Intent(this,sign_up.class);
        startActivity(su_i);
    }
}
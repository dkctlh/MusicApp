package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class sign_up extends AppCompatActivity {
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DBHelper(this);
    }
    public void logIn (View v) {
        Intent lo_i =new Intent(this,MainActivity.class);
        String u_name=((EditText) findViewById(R.id.editTextTextPersonName2)).getText().toString();
        String e_mail=((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String pass=((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();
        String repass=((EditText) findViewById(R.id.editTextTextPassword3)).getText().toString();
        CheckBox rules=((CheckBox) findViewById(R.id.checkBox));
        if(u_name.equals("")|| e_mail.equals("")|| pass.equals("") || repass.equals("") || !rules.isChecked() )
            Toast.makeText(this, "Lütfen bütün alanları doldurun.", Toast.LENGTH_LONG).show();
        else{
            if(pass.equals(repass)){
                Boolean checkuser=db.checkusername(u_name);
                if(checkuser==false){
                    Boolean checkemail=db.checkemail(e_mail);
                if(checkemail==false){
                    Boolean insert=db.insertData(u_name,pass,e_mail);
                    if(insert==true){
                        Toast.makeText(this, "Kayıt olma başarılı.", Toast.LENGTH_SHORT).show();
                        startActivity(lo_i);
                    }else{
                        Toast.makeText(this, "Kayıt olma işlemi başarısız.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Mail adresi sisteme kayıtlı.", Toast.LENGTH_LONG).show();
                }
            }else {
                    Toast.makeText(this, "Kullanıcı adı mevcut lütfen başka kullanıcı adı seçiniz.. ", Toast.LENGTH_LONG).show();
                }
            }
                else {
                Toast.makeText(this, "Şifreler eşleşmiyor.Lütfen tekrar deneyiniz. ", Toast.LENGTH_LONG).show();
            }
        }


    }


}
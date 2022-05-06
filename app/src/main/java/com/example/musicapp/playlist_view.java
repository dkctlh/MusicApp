package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import DB.DBHelper2;

public class playlist_view extends AppCompatActivity {
DBHelper2 db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_view);
        Intent pname=getIntent();
        String pName=pname.getStringExtra("playlistName");
        db2=new DBHelper2(this);



    }
}
package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import DB.DBHelper2;
import RecyclerViewPack.Music;
import RecyclerViewPack.MusicAdapterPlaylist;

public class playlist_make extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> playlistM;
    DBHelper2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_make);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);

        MusicAdapterPlaylist musicAdapter = new MusicAdapterPlaylist(this, Music.getData());
        recyclerView.setAdapter(musicAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



    }
    public void save(View v){
        Intent pv_i=new Intent(this,playlist_view.class);
        String pl_name=((TextView) findViewById(R.id.editTextTextPersonName3)).getText().toString();
        if(pl_name.equals("")){
            Toast.makeText(this, "lütfen playlist adı giriniz", Toast.LENGTH_LONG).show();
        }else {
            pv_i.putExtra("playlistName",pl_name);
            startActivity(pv_i);
        }




    }
    public void home_page(View view) {
        Intent hp_i=new Intent(this,home_page.class);
        startActivity(hp_i);
    }

}
//if(pl_name != null)
//else Toast.makeText(this, "lütfen playlist adı giriniz", Toast.LENGTH_LONG).show();
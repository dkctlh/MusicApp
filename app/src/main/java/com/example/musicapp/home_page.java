package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import RecyclerViewPack.Music;
import RecyclerViewPack.MusicAdapter;
import RecyclerViewPack.MusicAdapterPlaylist;

public class home_page extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Intent home_page=getIntent();
        String isim=home_page.getStringExtra("KullanıcıAdı");
        TextView tw=((TextView) findViewById(R.id.textView5));
        tw.setText("Merhaba"+ " "+isim);
        ActivityCompat.requestPermissions(home_page.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MANAGE_EXTERNAL_STORAGE},1);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0]== PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED   ) {
                    // permission granted and now can proceed
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

                    MusicAdapter musicAdapter = new MusicAdapter(this, Music.getData());
                    recyclerView.setAdapter(musicAdapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    Toast.makeText(home_page.this, "Permission granted", Toast.LENGTH_SHORT).show();

                  //  adapter= new ArrayAdapter<String>(getApplicationContext(),android)
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(home_page.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // add other cases for more permissions
        }
    }
        public void allSong(View v){
        Intent alls_i=new Intent(this,AllSongs.class);
        startActivity(alls_i);
        }
        public void playlistPage(View v) {
        Intent pl_i=new Intent(this,playlist_make.class);
        startActivity(pl_i);
        }


}
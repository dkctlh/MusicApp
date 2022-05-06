package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import RecyclerViewPack.Music;
import RecyclerViewPack.MusicAdapter;
import RecyclerViewPack.MusicAdapterPlaylist;

public class AllSongs extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);

        MusicAdapter musicAdapter = new MusicAdapter(this, Music.getData());
        recyclerView.setAdapter(musicAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

}
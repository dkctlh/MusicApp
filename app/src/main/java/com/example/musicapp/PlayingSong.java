package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import RecyclerViewPack.Music;

public class PlayingSong extends AppCompatActivity {
    String [] mp3array;
    MediaPlayer mPlayer;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    String songname="";
    Uri myUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_song);
        Intent plays_i=getIntent();
        String tf_name= (plays_i.getStringExtra("SongName"));
        ((TextView)findViewById(R.id.textView18)).setText(tf_name);

        seekBar = (SeekBar) findViewById(R.id.seekBar2);
  }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<String> mp3file=new ArrayList<String>();

        File directory = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Download")));

        File[] mp3files=directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".mp3");
            }
        });
        String [] mp3array=new String[mp3files.length] ;
        setMp3array(mp3array);
        for(int i=0; i<mp3files.length; i++){

            mp3array[i]=mp3files[i].getName();
             Log.i("pS", "For loop: Array: " + mp3array[i] + "songname:" + songname);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

                Intent plays_i=getIntent();
                String songname= (plays_i.getStringExtra("SongName"));

                MediaPlayer mPlayer = new MediaPlayer();

                myUri=(Uri.parse("file:///sdcard/download/" + songname));

                try {
                    mPlayer.setDataSource(getApplicationContext(), myUri);
                } catch (IOException e) {
                    e.printStackTrace();

                }
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                mPlayer.start();

        seekBar.setMax((int) mPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (mPlayer.isPlaying()) {
                        mPlayer.pause();
                        mPlayer.seekTo(progress);
                        mPlayer.start();
                    } else {
                        mPlayer.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (mPlayer.isPlaying()) {
                    seekBar.setProgress((int) mPlayer.getCurrentPosition());
                }
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
    }
    public void home_page(View v){
        Intent hp_i=new Intent(this,home_page.class);
        startActivity(hp_i);
    }

    public void setMyUri(Uri myUri) {
        this.myUri = myUri;
    }

    public Uri getMyUri() {
        return myUri;
    }

    public String[] getMp3array() {
        return mp3array;
    }

    public String getSongname() {
        return songname;
    }

    public void setMp3array(String[] mp3array) {

        this.mp3array = mp3array;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public void next_song(View v) {
        mp3array=getMp3array();
        songname=getSongname();

        for(int i=0; i<mp3array.length; i++){

            if(mp3array[i].equals(songname)){

                Log.i("pS", "For loop:  equal   : " + mp3array[i] + "songname:" + songname);
                setSongname(mp3array[i+1]);
                songname=getSongname();
                Log.i("pS" , "new song name:" + songname);
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    public void pauseButton(View v) {
        if (!mPlayer.isPlaying()   ) {
            songname=getSongname();
            setMyUri(Uri.parse("file:///sdcard/download/" + songname));
            myUri=getMyUri();
            try {
                mPlayer.setDataSource(getApplicationContext(), myUri);
            } catch (IOException e) {
                e.printStackTrace();

            }
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();

            }
            mPlayer.start();
            seekBar.setMax((int) mPlayer.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser) {
                        if (mPlayer.isPlaying()) {
                            mPlayer.pause();
                            mPlayer.seekTo(progress);
                            mPlayer.start();
                        } else {
                            mPlayer.seekTo(progress);
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (mPlayer.isPlaying()) {
                        seekBar.setProgress((int) mPlayer.getCurrentPosition());
                    }
                    handler.postDelayed(runnable, 1000);
                }
            };
            handler.post(runnable);


        } else
        {
            mPlayer.pause();

        }
    }



  }

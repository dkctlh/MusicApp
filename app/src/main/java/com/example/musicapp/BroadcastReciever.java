package com.example.musicapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class BroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager audioManager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        String res =intent.getStringExtra("result");
        if(res.equals("Telefon Hareketli")){
            audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
        } else if(res.equals("Telefon Masada")){
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
        }else if(res.equals("Telefon Cepte Hareketli")){
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
        }else if(res.equals("Telefon Cepte Hareketsiz")){
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_PLAY_SOUND);
        }


    }
}

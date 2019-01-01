package be.thomasmore.flinkspreken;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LuisterGoedActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    String frontingStopping;
    String finaalInitiaal;
    String doelKlank;
    String minimalePaar;
    MediaPlayer gesprokenInstructie;
    MediaPlayer reeks;
    MediaPlayer eindeSpel;
    int[] items = new int[2];
    int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luister_goed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    public void onStart()
    {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        finaalInitiaal = bundle.getString("FinaalInitiaalKeuze");
        doelKlank = bundle.getString("Doelklank");
        minimalePaar = bundle.getString("MinimalePaar");
        gesprokenInstructie = MediaPlayer.create(LuisterGoedActivity.this,R.raw.spel1);
        gesprokenInstructie.start();

    }

    @Override
    public void onPause()
    {
        if (gesprokenInstructie != null && gesprokenInstructie.isPlaying()){
            gesprokenInstructie.stop();
        }
        if (eindeSpel != null && eindeSpel.isPlaying()){
            eindeSpel.stop();
        }
        if (reeks != null && reeks.isPlaying()){
            reeks.stop();
        }
        super.onPause();
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }


    public void Luidspreker_onClick(View v) {
        gesprokenInstructie.start();
    }

    public void Oor_onClick(View v) {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audioManager.isWiredHeadsetOn()){
            SpeelAuditiefBombardementAf();
        }else {
            ShowDialog("Steek je oortjes of hoofdtelefoon in voor een betere ervaring van het spel. Druk hierna terug op het oor.", 0);
        }
    }

    private void ShowDialog(String bericht, final int doorsturenSpelPagina){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(bericht)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(doorsturenSpelPagina == 1){
                            finish();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void SpeelAuditiefBombardementAf(){
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("K-T")){
            //1
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks1);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("G-S")){
            //3
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks3);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("NG-N")){
            //5
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks5);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("K-T")){
            //2
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks2);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("G-V/F/S")){
            //4
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks4);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("S-T")){
            //6
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks6);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("CH-T")){
            //3
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks3);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("G-K")){
            //4
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks4);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("S/Z-T") && minimalePaar.equals("Sok - Tok")){
            //7
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks7);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("S/Z-T") && minimalePaar.equals("Zak - Tak")){
            //8
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks8);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("F-T")){
            //9
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks9);
        }
        currentItem=0;
        reeks.setOnCompletionListener(this);
        reeks.start();
    }

    public void onCompletion(MediaPlayer arg0) {
        //arg0.release();
        if (currentItem < items.length) {
            currentItem++;
            if(currentItem == 1){
                eindeSpel = MediaPlayer.create(LuisterGoedActivity.this,R.raw.flinkgeluisterd);
                eindeSpel.setOnCompletionListener(this);
                eindeSpel.start();
            }
            if(currentItem == 2){
                ShowDialog("Goed gedaan! Je spel is afgelopen. Je wordt automatisch terug gestuurd naar het spelletjes overzicht.", 1);
            }
        }
    }
}

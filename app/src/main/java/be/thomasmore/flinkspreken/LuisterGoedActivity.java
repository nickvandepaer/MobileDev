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
import android.widget.Toast;

public class LuisterGoedActivity extends AppCompatActivity {

    String frontingStopping;
    String finaalInitiaal;
    String doelKlank;
    String minimalePaar;
    MediaPlayer ring;
    MediaPlayer reeks;

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
        ring = MediaPlayer.create(LuisterGoedActivity.this,R.raw.ring);
        ring.start();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        ring.stop();
        reeks.stop();
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }


    public void Luidspreker_onClick(View v) {
        ring.start();
    }

    public void Start_onClick(View v) {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if(audioManager.isWiredHeadsetOn()){
            SpeelAuditiefBombardementAf();
        }else {
            ShowDialog();
        }
    }

    private void ShowDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void SpeelAuditiefBombardementAf(){
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("K-T")){
            //1
            toon("1");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks1);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("G-S")){
            //3
            toon("3");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks3);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("NG-N")){
            //5
            toon("5");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks5);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("K-T")){
            //2
            toon("2");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks2);
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("G-V/F/S")){
            //4
            toon("4");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks4);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("S-T")){
            //6
            toon("6");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks6);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("finaal")
                && doelKlank.equals("CH-T")){
            //3
            toon("3");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks3);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("G-K")){
            //4
            toon("4");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks4);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("S/Z-T") && minimalePaar.equals("Sok - Tok")){
            //7
            toon("7");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks7);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("S/Z-T") && minimalePaar.equals("Zak - Tak")){
            //8
            toon("8");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks8);
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")
                && doelKlank.equals("F-T")){
            //9
            toon("9");
            reeks = MediaPlayer.create(LuisterGoedActivity.this,R.raw.reeks9);
        }
        reeks.start();
    }


}

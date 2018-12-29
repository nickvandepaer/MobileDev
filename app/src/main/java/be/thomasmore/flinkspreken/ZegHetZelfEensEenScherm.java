package be.thomasmore.flinkspreken;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class ZegHetZelfEensEenScherm extends AppCompatActivity {


    String minimalePaar;
    String woord1;
    String woord2;
    String juisteWoord;
    MediaPlayer gesprokenInstructie;
    MediaPlayer botgeluid;
    MediaPlayer juist;
    MediaPlayer fout;
    int[] items = new int[1];
    int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeg_het_zelf_eens_een_scherm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onStart()
    {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        minimalePaar = bundle.getString("MinimalePaar");

        String[] separated = minimalePaar.split("-");
        woord1 = separated[0].trim().toLowerCase();
        woord2 = separated[1].trim().toLowerCase();

        Random rand = new Random();
        int random = rand.nextInt(2);
        random = random+1;
        if(random == 1){
            juisteWoord = woord1;
        }else {
            juisteWoord = woord2;
        }

        int soundId = getResources().getIdentifier(juisteWoord, "raw", getPackageName());
        botgeluid = MediaPlayer.create(ZegHetZelfEensEenScherm.this,soundId);

        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        int pathw1 = getResources().getIdentifier("tekening" + woord1, "drawable", getPackageName());
        w1.setTag(woord1);
        w1.setImageResource(pathw1);

        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        int pathw2 = getResources().getIdentifier("tekening" + woord2, "drawable", getPackageName());
        w2.setTag(woord2);
        w2.setImageResource(pathw2);

        gesprokenInstructie = MediaPlayer.create(ZegHetZelfEensEenScherm.this,R.raw.gesproken_instructie);
        gesprokenInstructie.start();
    }

    public void Luidspreker_onClick(View v) {
        gesprokenInstructie.start();
    }



    public void Afbeelding_onClick(View v)
    {
        Random rand = new Random();
        int random = rand.nextInt(2);
        random = random+1;
        if(random == 1){
            juisteWoord = woord1;
        }else {
            juisteWoord = woord2;
        }

        int id = v.getId();
        ImageView imgView = (ImageView) findViewById(id);
        int img = getResources().getIdentifier("tekening"+juisteWoord, "drawable", getPackageName());
        imgView.setImageResource(img);
    }





}

package be.thomasmore.flinkspreken;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class HondjeWafActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_hondje_waf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
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
        botgeluid = MediaPlayer.create(HondjeWafActivity.this,soundId);

        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        int pathw1 = getResources().getIdentifier("tekening" + woord1, "drawable", getPackageName());
        w1.setTag(woord1);
        w1.setImageResource(pathw1);

        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        int pathw2 = getResources().getIdentifier("tekening" + woord2, "drawable", getPackageName());
        w2.setTag(woord2);
        w2.setImageResource(pathw2);

        gesprokenInstructie = MediaPlayer.create(HondjeWafActivity.this,R.raw.spel2);
        gesprokenInstructie.start();
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause()
    {
        if (gesprokenInstructie != null && gesprokenInstructie.isPlaying()){
            gesprokenInstructie.stop();
        }
        if (botgeluid != null && botgeluid.isPlaying()){
            botgeluid.stop();
        }
        if (juist != null && juist.isPlaying()){
            juist.stop();
        }
        if (fout != null && fout.isPlaying()){
            fout.stop();
        }
        super.onPause();
    }

    public void Luidspreker_onClick(View v) {
        gesprokenInstructie.start();
    }

    public void Bot_onClick(View v) {
        botgeluid.start();
    }

    public void Woord1_onClick(View v) {
        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        ControleerAntwoord(w1);

    }

    public void Woord2_onClick(View v) {
        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        ControleerAntwoord(w2);
    }

    private void ControleerAntwoord(ImageView afbeelding){
        if(juisteWoord.equals(afbeelding.getTag().toString())){

            currentItem=0;
            juist = MediaPlayer.create(HondjeWafActivity.this,R.raw.waf);
            juist.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer){
                    ShowDialog("Goed gedaan! Je spel is afgelopen. Je wordt automatisch terug gestuurd naar het spelletjes overzicht.", 1);
                }
            } );
            juist.start();
        }else {
            currentItem=0;
            fout = MediaPlayer.create(HondjeWafActivity.this,R.raw.bijna_goed_nog_eens);
            fout.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer){
                    botgeluid.start();
                }
            } );
            fout.start();
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
}

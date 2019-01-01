package be.thomasmore.flinkspreken;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class ZegHetZelfEensEenScherm extends AppCompatActivity {


    String minimalePaar;
    String woord1;
    String woord2;
    String juisteWoord;
    MediaPlayer gesprokenInstructie;
    MediaPlayer afbeeldingGeluid;
    MediaPlayer juist;
    MediaPlayer fout;
    int teller;
    int tussenTeller = -1;
    int[] items = new int[1];
    ImageView imgView;
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
        teller = 0;

        String[] separated = minimalePaar.split("-");
        woord1 = separated[0].trim().toLowerCase();
        woord2 = separated[1].trim().toLowerCase();

        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        int pathw1 = getResources().getIdentifier("tekening" + woord1, "drawable", getPackageName());
        w1.setTag(woord1);
        w1.setImageResource(pathw1);
        w1.setEnabled(false);

        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        int pathw2 = getResources().getIdentifier("tekening" + woord2, "drawable", getPackageName());
        w2.setTag(woord2);
        w2.setImageResource(pathw2);
        w2.setEnabled(false);

        gesprokenInstructie = MediaPlayer.create(ZegHetZelfEensEenScherm.this,R.raw.spel3);
        gesprokenInstructie.start();
    }

    public void Luidspreker_onClick(View v) {
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
        if (afbeeldingGeluid != null && afbeeldingGeluid.isPlaying()){
            afbeeldingGeluid.stop();
        }
        if (juist != null && juist.isPlaying()){
            juist.stop();
        }
        if (fout != null && fout.isPlaying()){
            fout.stop();
        }
        super.onPause();
    }

    public void Afbeelding_onClick(View v)
    {
        if(tussenTeller != teller){
            Random rand = new Random();
            int random = rand.nextInt(2);
            random = random+1;
            if(random == 1){
                juisteWoord = woord1;
            }else {
                juisteWoord = woord2;
            }
            int soundId = getResources().getIdentifier(juisteWoord, "raw", getPackageName());
            afbeeldingGeluid = MediaPlayer.create(ZegHetZelfEensEenScherm.this,soundId);
        }

        EnableWoorden(true);

        int id = v.getId();
        imgView = (ImageView) findViewById(id);
        int img = getResources().getIdentifier("tekening"+juisteWoord, "drawable", getPackageName());
        imgView.setTag(juisteWoord);
        imgView.setImageResource(img);
        afbeeldingGeluid.start();
    }

    private void ControleerAntwoord(ImageView afbeelding)
    {
        EnableWoorden(false);
        if(juisteWoord.equals(afbeelding.getTag()))
        {
            teller++;
            juist = MediaPlayer.create(ZegHetZelfEensEenScherm.this,R.raw.goed_gedaan);
            juist.start();
        }
        else
        {
            int img = getResources().getIdentifier("varken", "drawable", getPackageName());
            imgView.setImageResource(img);
            fout = MediaPlayer.create(ZegHetZelfEensEenScherm.this,R.raw.bijna_goed_nog_eens);
            fout.start();
            tussenTeller = teller;
        }
        if(teller == 9)
        {
            ShowDialog("Goed gedaan! Je spel is afgelopen. Je wordt automatisch terug gestuurd naar het spelletjes overzicht.", 1);
        }
    }

    public void  Woord1_onClick(View v)
    {
        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        ControleerAntwoord(w1);
    }

    public void  Woord2_onClick(View v)
    {
        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        ControleerAntwoord(w2);
    }

    private void ShowDialog(String bericht, final int doorsturenSpelPagina) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(bericht)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (doorsturenSpelPagina == 1) {
                            finish();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void EnableWoorden(Boolean clickable){
        ImageView w1 = (ImageView) findViewById(R.id.woord1);
        ImageView w2 = (ImageView) findViewById(R.id.woord2);
        if(clickable == true){
            w1.setEnabled(true);
            w2.setEnabled(true);
        }else{
            w1.setEnabled(false);
            w2.setEnabled(false);
        }

    }


}

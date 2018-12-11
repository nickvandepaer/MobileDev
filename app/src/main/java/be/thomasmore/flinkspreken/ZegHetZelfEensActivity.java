package be.thomasmore.flinkspreken;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ZegHetZelfEensActivity extends AppCompatActivity {

    int KOLOM = 3;
    int RIJ = 3;
    String frontingStopping;
    String finaalInitiaal;
    String doelKlank;
    String minimalePaar;
    private DatabaseHelper db;
    public List<MinimalePaar> minimaleparen;
    public List<MinimalePaar> filteredListMinimaleparen = new ArrayList<MinimalePaar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeg_het_zelf_eens);

        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        finaalInitiaal = bundle.getString("FinaalInitiaalKeuze");
        doelKlank = bundle.getString("Doelklank");
        minimalePaar = bundle.getString("MinimalePaar");

        db = new DatabaseHelper(this);
        minimaleparen = db.getMinimaleParen();

        HaalDataOp();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        maakLayout();

    }

    private void HaalDataOp()
    {

        //haalt alles op
        for (int i=0; i<minimaleparen.size(); i++)
        {
            //controlleerd wat gelijk is aan wat we meegeven
            if(minimaleparen.get(i).getDoelKlank().equals(doelKlank) && minimaleparen.get(i).getFinaalInitiaal().equals(finaalInitiaal)){
                //aparte lijst
                filteredListMinimaleparen.add(minimaleparen.get(i));
            }
        }
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    private void maakLayout()
    {
    int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_main);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                final ImageView imageView = new ImageView(this);

                LinearLayout.LayoutParams imageLayoutParams =
                        new LinearLayout.LayoutParams(300,300);
                imageLayoutParams.leftMargin = 5;
                imageLayoutParams.topMargin = 5;
                imageView.setLayoutParams(imageLayoutParams);
                imageView.setTag("ear");

                imageView.setOnClickListener(new View.OnClickListener() {
                    public  void onClick(View v)
                    {
                        imageOnClick((ImageView)v);
                    }
                });
                imageView.setImageResource(getResources().getIdentifier("ear","drawable",getPackageName()));

               // logos[k] = imageView;
                k++;
                linearLayout.addView(imageView);
            }
        }
    }


    private void imageOnClick(ImageView imageView)
    {
        //  String naam = imageView.getTag().toString();

        Bundle bundle = new Bundle();
        bundle.putString("logo", imageView.getTag().toString());
        bundle.putString("minimalepaar", minimalePaar);

        Intent intent = new Intent(this, ZegHetZelfEensSchermAcitivity.class);
        intent.putExtras(bundle);

        // startActivity(intent);

        startActivityForResult(intent, 1);  // 1 is requestCode

    }

}

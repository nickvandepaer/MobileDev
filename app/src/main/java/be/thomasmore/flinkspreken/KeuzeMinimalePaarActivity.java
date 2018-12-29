package be.thomasmore.flinkspreken;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KeuzeMinimalePaarActivity extends AppCompatActivity {

    private DatabaseHelper db;
    public List<MinimalePaar> minimaleparen;
    public List<MinimalePaar> filteredListMinimaleparen = new ArrayList<MinimalePaar>();

    String frontingStopping;
    String finaalInitiaal;
    String doelklank;

    int KOLOM = 1;
    int RIJ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuze_minimale_paar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.Keuze);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/PWBalloon.ttf");
        textView.setTypeface(typeface);

        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        finaalInitiaal = bundle.getString("FinaalInitiaalKeuze");
        doelklank = bundle.getString("Doelklank");

        db = new DatabaseHelper(this);
        minimaleparen = db.getMinimaleParen();

        vulFilteredListMinimaleparenOp();
        maakLayout();
    }

    private void vulFilteredListMinimaleparenOp(){
        for (int i=0; i<minimaleparen.size(); i++)
        {
            if(minimaleparen.get(i).getDoelKlank().equals(doelklank) && minimaleparen.get(i).getFinaalInitiaal().equals(finaalInitiaal)){
                filteredListMinimaleparen.add(minimaleparen.get(i));
            }
        }
    }

    private void maakLayout()
    {
        int k = 0;
        RIJ = filteredListMinimaleparen.size();
        LinearLayout keuzeMinimalePaarLayout = (LinearLayout) findViewById(R.id.layout_keuze_minimale_paar);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            keuzeMinimalePaarLayout.addView(linearLayout);

            for (int j = 0; j < KOLOM; j++) {
                Button btn = new Button(this);
                btn.setTag(filteredListMinimaleparen.get(k).getWoord1() + " - " + filteredListMinimaleparen.get(k).getWoord2());
                btn.setText(filteredListMinimaleparen.get(k).getWoord1() + " - " + filteredListMinimaleparen.get(k).getWoord2());
                btn.setTextSize(25);
                btn.setBackgroundResource(R.drawable.buttonimage);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        500,
                        240
                );
                params.setMargins(0, 10, 0, 10);
                btn.setLayoutParams(params);
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        btnClick((View)v);
                    }
                });
                k++;
                linearLayout.addView(btn);
            }
        }
    }

    private void btnClick(View v)
    {
        Button btn = (Button) v;
        Bundle bundle = getIntent().getExtras();
        bundle.putString("MinimalePaar", btn.getText().toString());
        Intent intent = new Intent(this, SpelletjesActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        finaalInitiaal = bundle.getString("FinaalInitiaalKeuze");
        doelklank = bundle.getString("Doelklank");
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    public void Vraagteken_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String alert1 = "In dit scherm kan u het minimaal paar kiezen waarop u wilt oefenen.";
        builder.setMessage(alert1).setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

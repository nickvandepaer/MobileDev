package be.thomasmore.flinkspreken;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpelletjesActivity extends AppCompatActivity {


    String frontingStopping;
    String finaalInitiaal;
    String doelKlank;
    String minimalePaar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelletjes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.Keuze);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/PWBalloon.ttf");
        textView.setTypeface(typeface);
    }

    public void LuisterGoed_onClick(View v) {
        Button btn = (Button) v;
        Bundle bundle = getIntent().getExtras();
        Intent intent = new Intent(this, LuisterGoedActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void HondjeWaf_onClick(View v) {
        Button btn = (Button) v;
        Bundle bundle = getIntent().getExtras();
        Intent intent = new Intent(this, HondjeWafActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void ZegHetEensZelf_onClick(View v) {
        Button btn = (Button) v;
        Bundle bundle = getIntent().getExtras();
        Intent intent = new Intent(this, ZegHetZelfEensEenScherm.class);
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
        doelKlank = bundle.getString("Doelklank");
        minimalePaar = bundle.getString("MinimalePaar");
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    public void Vraagteken_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String alert1 = "Kies hier een spelletje waarmee u de woordjes kan oefenen.";
        builder.setMessage(alert1).setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

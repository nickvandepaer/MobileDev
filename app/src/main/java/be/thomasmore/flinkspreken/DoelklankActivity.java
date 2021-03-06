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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DoelklankActivity extends AppCompatActivity {
    String[] frontingFinaaldoelKlanken = {"K-T", "G-S", "NG-N"};
    String[] frontingInitiaaldoelKlanken = {"K-T", "G-V/F/S"};
    String[] stoppingFinaaldoelKlanken = {"S-T", "CH-T"};
    String[] stoppingInitiaaldoelKlanken = {"G-K", "S/Z-T", "F-T"};
    String frontingStopping;
    String finaalInitiaal;

    int KOLOM = 1;
    int RIJ = 0;
    int AANTAL = RIJ * KOLOM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doelklank);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.Keuze);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/PWBalloon.ttf");
        textView.setTypeface(typeface);

        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        finaalInitiaal = bundle.getString("FinaalInitiaalKeuze");

        maakLayout();
    }


    private void maakLayout()
    {
        String keuzeArray[] = new String[]{};
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("finaal")){
            RIJ = frontingFinaaldoelKlanken.length;
            keuzeArray = frontingFinaaldoelKlanken;
        }
        if (frontingStopping.equals("fronting") && finaalInitiaal.equals("initiaal")){
            RIJ = frontingInitiaaldoelKlanken.length;
            keuzeArray = frontingInitiaaldoelKlanken;
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("finaal")){
            RIJ = stoppingFinaaldoelKlanken.length;
            keuzeArray = stoppingFinaaldoelKlanken;
        }
        if (frontingStopping.equals("stopping") && finaalInitiaal.equals("initiaal")){
            RIJ = stoppingInitiaaldoelKlanken.length;
            keuzeArray = stoppingInitiaaldoelKlanken;
        }

        int k = 0;
        LinearLayout doelKlankLayout = (LinearLayout) findViewById(R.id.layout_doelKlank);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            doelKlankLayout.addView(linearLayout);

            for (int j = 0; j < KOLOM; j++) {
                Button btn = new Button(this);
                btn.setTag(keuzeArray[k]);
                btn.setText(keuzeArray[k]);
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
        bundle.putString("Doelklank", btn.getText().toString());
        Intent intent = new Intent(this, KeuzeMinimalePaarActivity.class);
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
    }

    public void Vraagteken_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String alert1 = "In dit scherm kan u de doelklank kiezen waarop u wilt oefenen.";
        builder.setMessage(alert1).setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

package be.thomasmore.flinkspreken;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class LuisterGoedActivity extends AppCompatActivity {

    String frontingStopping;
    String finaalInitiaal;
    String doelKlank;

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

        toon(frontingStopping);
        toon(finaalInitiaal);
        toon(doelKlank);

        MediaPlayer ring= MediaPlayer.create(LuisterGoedActivity.this,R.raw.ring);
        ring.start();
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

}

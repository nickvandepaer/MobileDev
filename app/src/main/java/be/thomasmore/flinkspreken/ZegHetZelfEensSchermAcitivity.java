package be.thomasmore.flinkspreken;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class ZegHetZelfEensSchermAcitivity extends AppCompatActivity {

    String logo;
    String woord;
    String displayWoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeg_het_zelf_eens_scherm_acitivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toonLogo();
        KiesWoord();

    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    private void toonLogo() {
        Bundle bundle = getIntent().getExtras();

        logo = bundle.getString("logo");

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.setImageResource(getResources().getIdentifier(logo,"drawable",getPackageName()));
    }

    private  void KiesWoord()
    {
        Bundle bundle = getIntent().getExtras();

        woord = bundle.getString("minimalepaar");
        String[] splitted = woord.split("-");

        Random rand = new Random();

        int random = rand.nextInt(100) + 1;

        if(random > 50)
        {
            displayWoord = splitted[0].trim().toLowerCase();
            toon(splitted[0]);
        }
        else
        {
            displayWoord = splitted[1].trim().toLowerCase();
            toon(splitted[1]);
        }

        TextView textView = (TextView) findViewById(R.id.woord);
        textView.setText(displayWoord);

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.setImageResource(getResources().getIdentifier("tekening"+displayWoord,"drawable",getPackageName()));

       ImageView imageView1 = (ImageView) findViewById(R.id.woord1);
        imageView1.setImageResource(getResources().getIdentifier("tekening"+splitted[0].trim().toLowerCase(),"drawable",getPackageName()));

        ImageView imageView2 = (ImageView) findViewById(R.id.woord2);
        imageView2.setImageResource(getResources().getIdentifier("tekening"+splitted[1].trim().toLowerCase(),"drawable",getPackageName()));

    }

}

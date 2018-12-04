package be.thomasmore.flinkspreken;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ZegHetZelfEensSchermAcitivity extends AppCompatActivity {

    String logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeg_het_zelf_eens_scherm_acitivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toonLogo();

    }


    private void toonLogo() {
        Bundle bundle = getIntent().getExtras();
        logo = bundle.getString("logo");

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        imageView.setImageResource(getResources().getIdentifier(logo,"drawable",getPackageName()));
    }

}

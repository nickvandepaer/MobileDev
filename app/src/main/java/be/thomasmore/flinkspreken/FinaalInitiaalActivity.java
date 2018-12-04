package be.thomasmore.flinkspreken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class FinaalInitiaalActivity extends AppCompatActivity {

    String frontingStopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaal_initiaal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
        toon(frontingStopping);
    }

    private void toon(String tekst)
    {
        Toast.makeText(getBaseContext(), tekst, Toast.LENGTH_SHORT).show();
    }

    public void Finaal_onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("FrontingStoppingKeuze", frontingStopping);
        bundle.putString("FinaalInitiaalKeuze", "finaal");
        Intent intent = new Intent(this, DoelklankActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);  // 1 is requestCode
    }

    public void Initiaal_onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("FrontingStoppingKeuze", frontingStopping);
        bundle.putString("FinaalInitiaalKeuze", "initiaal");
        Intent intent = new Intent(this, DoelklankActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);  // 1 is requestCode
    }
}

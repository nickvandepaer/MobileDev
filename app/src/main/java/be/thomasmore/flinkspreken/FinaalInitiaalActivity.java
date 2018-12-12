package be.thomasmore.flinkspreken;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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

    @Override
    public void onStart()
    {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        frontingStopping = bundle.getString("FrontingStoppingKeuze");
    }

    public void Vraagteken_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String alert1 = "Finaal: ";
        String alert2 = "Achteraan het woord." ;
        String alert3 = " " ;
        String alert4 = "Initiaal: ";
        String alert5 = "Vooraan het woord." ;
        builder.setMessage(alert1 +"\n"+ alert2 +"\n"+ alert3 +"\n"+ alert4 +"\n"+ alert5).setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

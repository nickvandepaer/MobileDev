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

public class FrontingStoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fronting_stopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void Fronting_onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("FrontingStoppingKeuze", "fronting");
        Intent intent = new Intent(this, FinaalInitiaalActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);  // 1 is requestCode
    }

    public void Stopping_onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("FrontingStoppingKeuze", "stopping");
        Intent intent = new Intent(this, FinaalInitiaalActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1);  // 1 is requestCode
    }

    public void Vraagteken_onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String alert1 = "Fronting: ";
        String alert2 = "Het kind spreekt een klank, die vanachter in de mond uitgesproken moet worden, vooraan in de mond uit. Bv: toe i.p.v. koe" ;
        String alert3 = " " ;
        String alert4 = "Stopping: ";
        String alert5 = "Het kind ‘stopt’ een klank die je normaal gezien lang moet aanhouden. Bv: kat i.p.v. gat" ;
        builder.setMessage(alert1 +"\n"+ alert2 +"\n"+ alert3 +"\n"+ alert4 +"\n"+ alert5).setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}

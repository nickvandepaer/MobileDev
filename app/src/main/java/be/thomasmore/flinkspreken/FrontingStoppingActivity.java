package be.thomasmore.flinkspreken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

}

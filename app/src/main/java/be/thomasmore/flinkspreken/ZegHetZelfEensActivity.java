package be.thomasmore.flinkspreken;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ZegHetZelfEensActivity extends AppCompatActivity {

    int KOLOM = 3;
    int RIJ = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeg_het_zelf_eens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        maakLayout();

    }

    private void maakLayout()
    {
        int k = 0;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.layout_main);
        for (int i = 0; i < RIJ; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            mainLayout.addView(linearLayout);
            for (int j = 0; j < KOLOM; j++) {
                final ImageView imageView = new ImageView(this);

                LinearLayout.LayoutParams imageLayoutParams =
                        new LinearLayout.LayoutParams(300,300);
                imageLayoutParams.leftMargin = 5;
                imageLayoutParams.topMargin = 5;
                imageView.setLayoutParams(imageLayoutParams);
                imageView.setTag("oor" + k);

                imageView.setOnClickListener(new View.OnClickListener() {
                    public  void onClick(View v)
                    {
                        imageOnClick((ImageView)v);
                    }
                });
                imageView.setImageResource(getResources().getIdentifier("oor","drawable",getPackageName()));

               // logos[k] = imageView;
                k++;
                linearLayout.addView(imageView);
            }
        }
    }


    private void imageOnClick(ImageView imageView)
    {
        //  String naam = imageView.getTag().toString();

        Bundle bundle = new Bundle();
        bundle.putString("logo", imageView.getTag().toString());

        Intent intent = new Intent(this, ZegHetZelfEensSchermAcitivity.class);
        intent.putExtras(bundle);

        // startActivity(intent);

        startActivityForResult(intent, 1);  // 1 is requestCode

    }

}

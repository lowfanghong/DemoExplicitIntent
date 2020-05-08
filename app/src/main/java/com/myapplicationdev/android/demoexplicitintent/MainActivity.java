package com.myapplicationdev.android.demoexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // These request identify who started the second activity
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmanStats = 2;

    TextView TvSuperman;
TextView TvBatman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TvSuperman = findViewById(R.id.textViewSuperman);
        TvBatman = findViewById(R.id.textViewBatman);

        TvSuperman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this,
                        HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", superman);
                startActivityForResult(i,requestCodeForSupermanStats);

            }
        });
        TvBatman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this,
                        HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", batman);
                // Start the activity
                startActivityForResult(i, requestCodeForBatmanStats);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                // If it is activity started by clicking
                //  Superman, create corresponding String
                if(requestCode == requestCodeForSupermanStats){
                    statement = "You " + like + " Superman";
                }
                // If 2nd activity started by clicking
                //  Batman, create a corresponding String
                if(requestCode == requestCodeForBatmanStats){
                    statement = "You " + like + " Batman";
                }

                Toast.makeText(MainActivity.this, statement,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}

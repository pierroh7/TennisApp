package com.example.mytennisapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout photo, score;
    private TextView match_1, match_2, match_3, match_4, match_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        /*map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });*/

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intent);

            }
        });

    }

    private void init(){
        //map = findViewById(R.id.map);
        photo = findViewById(R.id.photo);
        score = findViewById(R.id.score);

        match_1 = findViewById(R.id.match_1);
        match_2 = findViewById(R.id.match_2);
        match_3 = findViewById(R.id.match_3);
        match_4 = findViewById(R.id.match_4);
        match_5 = findViewById(R.id.match_5);

        match_1.setText("Lakers  40|60  Bulls   -   Philadelphie");
        match_2.setText("Lakers  40|60  Bulls   -   Philadelphie");
        match_3.setText("Lakers  40|60  Bulls   -   Philadelphie");
        match_4.setText("Lakers  40|60  Bulls   -   Philadelphie");
        match_5.setText("Lakers  40|60  Bulls   -   Philadelphie");
    }
}

package com.example.mytennisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.widget.GridView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    GridView gridview;
    ArrayList<Match> list;
    MatchListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        gridview = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new MatchListAdapter(this, R.layout.activity_score_items, list);
        gridview.setAdapter(adapter);

        //Là il reste à coder sa partie SQL

    }
}

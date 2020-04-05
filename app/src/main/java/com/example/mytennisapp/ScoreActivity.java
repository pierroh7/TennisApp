package com.example.mytennisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
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

        //Get data from SQL

        /*Cursor cursor = MainActivity.sqliteHelper.getData("SELECT * FROM MATCH");
        list.clear();
        while (cursor.moveToNext()){
            int matchID = cursor.getInt(0);
            String nameTeamA = cursor.getString(1);
            String nameTeamB = cursor.getString(2);
            int scoreTeamA = cursor.getInt(3);
            int scoreTeamB = cursor.getInt(4);
            byte [] picture = cursor.getBlob(5);

            list.add(new Match(matchID, nameTeamA, nameTeamB, scoreTeamA, scoreTeamB, picture));
        }

        adapter.notifyDataSetChanged();*/
    }
}

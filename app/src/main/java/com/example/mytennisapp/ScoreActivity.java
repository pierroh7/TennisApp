package com.example.mytennisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.FieldClassification;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

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

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                CharSequence[] items = {"Update", "Delete", "Details"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ScoreActivity.this);

                dialog.setTitle("Action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        if(item == 0){
                            //update
                            //utilise la fonction *update* codée vidéo 2/3 à 3:30
                        }
                        else if(item == 1){
                            //delete
                            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //details
                            Toast.makeText(getApplicationContext(), "Details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void shwoDialogUpdate(Activity activity){
        Dialog dialog = new Dialog(activity);
        dialog.setContentView();
    }
}

package com.example.mytennisapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    ImageView matchPicture;
    GridView gridview;
    ArrayList<Match> list;
    MatchListAdapter adapter = null;
    Match match = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_list);

        gridview = findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new MatchListAdapter(this, R.layout.activity_score_items, list);
        gridview.setAdapter(adapter);

        //Get data from SQL
        Cursor cursor = MainActivity.sqliteHelper.getData("SELECT * FROM MATCH");
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

        adapter.notifyDataSetChanged();

        //J'ai mis un simple Onclick en fait
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                CharSequence[] items = {"Update", "Delete", "Info"};
                final AlertDialog.Builder dialog = new AlertDialog.Builder(ScoreActivity.this);

                dialog.setTitle("Action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        if(item == 0){
                            //update
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM MATCH");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(ScoreActivity.this, arrID.get(i));
                        }
                        else if (item == 1){
                            //delete
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM MATCH");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(i));
                        }

                        else{
                            //details
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT * FROM MATCH");
                            while(c.moveToNext()){
                                int id = c.getInt(0);
                                String nameA = c.getString(1);
                                String nameB = c.getString(2);
                                int scoreA = c.getInt(3);
                                int scoreB = c.getInt(4);
                                byte [] matchPicture = c.getBlob(5);
                                String date = c.getString(6);
                                String location = c.getString(7);
                                String details = c.getString(8);

                                match = new Match(id, nameA, nameB, scoreA, scoreB, matchPicture, date, location, details);
                            }
                            showDialogDetails(ScoreActivity.this, match);
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    private void showDialogUpdate(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_match_activity);
        dialog.setTitle("Update");

        matchPicture = dialog.findViewById(R.id.match_picture);
        final EditText teamA = dialog.findViewById(R.id.team_name_a);
        final EditText teamB = dialog.findViewById(R.id.team_name_b);
        final EditText scoreA = dialog.findViewById(R.id.score_team_a);
        final EditText scoreB = dialog.findViewById(R.id.score_team_b);

        final EditText date = dialog.findViewById(R.id.date);
        final EditText details = dialog.findViewById(R.id.details);
        Button update = dialog.findViewById(R.id.update_button);

        Spinner location = dialog.findViewById(R.id.location);
        final String selectedLocation = spinnerInit(location);


        //width & height for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95); //Dimension à vérifier
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7); //Dimension à vérifier
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        matchPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        ScoreActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    MainActivity.sqLiteHelper.updateData(
                            teamA.getText().toString().trim(),
                            teamB.getText().toString().trim(),
                            scoreA.getText().toString().trim(), //à modifier si int dans la BDD
                            scoreB.getText().toString().trim(), //à modifier si int dans la BDD
                            date.getText().toString().trim(),
                            details.getText().toString().trim(),
                            selectedLocation.trim(),
                            //Vidéo 2/3  -  15:21
                            //Changement en public static byte[] du sous-programme imageViewToByte dans MainActivity, ou dans AddMatch si t'as fait ça dedans
                            MainActivity.imageViewToByte(matchPicture),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Updated !", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Log.e("Error", e.getMessage());
                }
                updateScoreActivity();
            }
        });
    }

    private void showDialogDelete(final int matchID){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ScoreActivity.this);

        dialogDelete.setTitle("Delete ?");
        dialogDelete.setPositiveButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    //fonction codée Vidéo 3/3  -  5:38
                    MainActivity.sqLiteHelper.deleteMatch(matchID);
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Log.e("Erreur", e.getMessage());
                }
                updateScoreActivity();
            }
        });

        dialogDelete.setNegativeButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialogDelete.show();
    }

    private void showDialogDetails(Activity activity, final Match match){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.match_detailed_activity);
        dialog.setTitle("Details");

        matchPicture = dialog.findViewById(R.id.match_picture);
        final TextView teamA = dialog.findViewById(R.id.team_name_a);
        final TextView teamB = dialog.findViewById(R.id.team_name_b);
        final TextView scoreA = dialog.findViewById(R.id.score_team_a);
        final TextView scoreB = dialog.findViewById(R.id.score_team_b);
        final TextView date = dialog.findViewById(R.id.date);
        final TextView location = dialog.findViewById(R.id.location);
        final TextView details = dialog.findViewById(R.id.details);
        Button map = dialog.findViewById(R.id.map_button);

        byte [] match_picture = match.getMatchPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(match_picture, 0, match_picture.length);
        matchPicture.setImageBitmap(bitmap);

        teamA.setText(match.getTeamNameA());
        teamB.setText(match.getTeamNameB());
        scoreA.setText(match.getScoreA());
        scoreB.setText(match.getScoreB());
        date.setText(match.getDate());
        location.setText(match.getLocation());
        details.setText(match.getDetails());

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, MapsActivity.class);
                //Passage de la localisation du match
                Bundle b = new Bundle();
                b.putString("matchLocation", match.getLocation());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //width & height for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95); //Dimension à vérifier
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7); //Dimension à vérifier
        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }

    private void updateScoreActivity(){
        //get all data from sqlite
        Cursor cursor = MainActivity.sqliteHelper.getData("SELECT * FROM MATCH");
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

        adapter.notifyDataSetChanged();
    }

    private String spinnerInit(Spinner location){

        List<String> locationList = new ArrayList<>();
        final String[] toReturn = new String[1];
        locationList.add(0, "Location");
        //À changer suivant les stades
        locationList.add("Place 1");
        locationList.add("Place 2");
        locationList.add("Place 3");
        locationList.add("Place 4");
        locationList.add("Place 5");

        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locationList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(dataAdapter);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!adapterView.getItemAtPosition(i).equals("Location")){
                    toReturn[0] = adapterView.getItemAtPosition(i).toString();
                }
                else{
                    toReturn[0] = "error";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return toReturn[0];

    }

    //COPIER-COLLER onRequestPermissionResult ET onActivityResult du MainActivity
    //12:27  -  Vidéo 2/3
    //REQUEST_CODE_GALLERY -> à remplacer par 888
    //imageView -> à remplacer par matchPicture
}

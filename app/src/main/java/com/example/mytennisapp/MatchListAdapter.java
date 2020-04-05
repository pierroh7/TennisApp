package com.example.mytennisapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Match> matchList;

    public MatchListAdapter(Context context, int layout, ArrayList<Match> matchList) {
        this.context = context;
        this.layout = layout;
        this.matchList = matchList;
    }

    @Override
    public int getCount() {
        return matchList.size();
    }

    @Override
    public Object getItem(int position) {
        return matchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView match_picture;
        TextView team_names, team_scores;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.team_names = row.findViewById(R.id.team_names);
            holder.team_scores = row.findViewById(R.id.team_scores);
            holder.match_picture = row.findViewById(R.id.match_picture);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }

        Match match = matchList.get(position);
        holder.team_names.setText(match.getTeamNameA() + " | " + match.getTeamNameB());
        holder.team_scores.setText(match.getScoreA() + " - " + match.getScoreB());

        byte [] matchPicture = match.getMatchPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(matchPicture, 0, matchPicture.length);
        holder.match_picture.setImageBitmap(bitmap);
        return row;
    }
}

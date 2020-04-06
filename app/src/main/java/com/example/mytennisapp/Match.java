package com.example.mytennisapp;

public class Match {
    private String teamA, teamB, date, location, details;
    private int scoreA, scoreB;
    private int matchID;
    private byte[] matchPicture;

    public Match(int matchID, String teamA, String teamB, int scoreA, int scoreB, byte[] matchPicture) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.matchID = matchID;
        this.matchPicture = matchPicture;
    }

    public Match(int matchID, String teamA, String teamB, int scoreA, int scoreB, byte[] matchPicture, String date, String location, String details) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.matchID = matchID;
        this.matchPicture = matchPicture;
        this.date = date;
        this .location = location;
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTeamNameA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamNameB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public byte[] getMatchPicture() {
        return matchPicture;
    }

    public void setMatchPicture(byte[] image) {
        this.matchPicture = image;
    }
}

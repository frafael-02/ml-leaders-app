package dev.us3r.mlleadersapp.domain;

public enum PLAYER_POSITION {

    GK(1, "Goalkeeper"),
    DF(2, "Defender"),
    MF(3, "Midfielder"),
    FW(4, "Forward");

    private final int id;
    private final String fullName;


    PLAYER_POSITION(int i, String fullName) {
        this.id=i;
        this.fullName=fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

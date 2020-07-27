package com.genius.geniusassistant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sentences {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "sentences")
    private String sentences;
    @ColumnInfo(name = "save_time")
    private String time;

    public Sentences(String sentences, String time) {
        this.sentences = sentences;
        this.time = time;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

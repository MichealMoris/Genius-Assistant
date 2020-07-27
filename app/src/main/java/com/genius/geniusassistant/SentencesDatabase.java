package com.genius.geniusassistant;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database( entities = {Sentences.class}, version = 1)
public abstract class SentencesDatabase extends RoomDatabase {

    public abstract DAO dao();

}

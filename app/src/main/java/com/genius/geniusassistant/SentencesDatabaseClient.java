package com.genius.geniusassistant;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class SentencesDatabaseClient {

    public Context context;
    private static SentencesDatabaseClient sentencesDatabaseClientInstance;
    private SentencesDatabase sentencesDatabase;

    private SentencesDatabaseClient(Context context) {
        this.context = context;
        sentencesDatabase = Room.databaseBuilder(context, SentencesDatabase.class, "SentencesSaver").build();
    }

    public static synchronized SentencesDatabaseClient getInstance(Context context){

        if (sentencesDatabaseClientInstance == null){

            sentencesDatabaseClientInstance = new SentencesDatabaseClient(context);

        }
        return sentencesDatabaseClientInstance;

    }


    public SentencesDatabase getSentencesDatabase() {
        return sentencesDatabase;
    }
}

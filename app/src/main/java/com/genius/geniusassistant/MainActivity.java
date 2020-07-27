package com.genius.geniusassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView sentences_recyclerview;
    private SentencesAdapter sentencesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRecyclerView();
        accessMicWork();
        checkPermission();

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        1);

            }
        }
    }

    private void accessMicWork(){

        ComponentName componentName = new ComponentName(MainActivity.this, AccessMicBackground.class);
        JobInfo jobInfo = new JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);

    }

    private void setRecyclerView(){

        class AddToRecyclerView extends AsyncTask<Void, Void, List<Sentences>>{


            @Override
            protected List<Sentences> doInBackground(Void... voids) {

                List<Sentences> sentencesList = SentencesDatabaseClient.getInstance(getApplicationContext()).getSentencesDatabase().dao().getAll();
                return sentencesList;

            }

            @Override
            protected void onPostExecute(List<Sentences> sentences) {
                super.onPostExecute(sentences);

                sentences_recyclerview = findViewById(R.id.sentences_recyclerview);
                sentences_recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                sentencesAdapter = new SentencesAdapter(sentences);
                sentences_recyclerview.setAdapter(sentencesAdapter);

            }
        }

        new AddToRecyclerView().execute();

    }

}
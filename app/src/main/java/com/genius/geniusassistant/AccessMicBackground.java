package com.genius.geniusassistant;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AccessMicBackground extends JobService {

    private boolean jobCanceled = false;
    private boolean speechCanceled = false;
    private String speech;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("isMicAccessed", "True!");
        final SpeechRecognizer mSpeechRecognizer  = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        doInBackground(params, mSpeechRecognizer, mSpeechRecognizerIntent);
        return true;
    }

    public void doInBackground(final JobParameters params, final SpeechRecognizer mSpeechRecognizer, final Intent mSpeechRecognizerIntent){


                if (speechCanceled){

                    mSpeechRecognizer.stopListening();

                }else{

                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

                }

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {

                final ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null) {
                    class AddSentences extends AsyncTask<Void, Void, Void> {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            SentencesDatabaseClient.getInstance(getApplicationContext()).getSentencesDatabase().dao().insert(new Sentences(matches.get(0), String.valueOf(Calendar.getInstance(Locale.getDefault()).get(Calendar.HOUR_OF_DAY))));
                            return null;
                        }
                    }
                    new AddSentences().execute();
                }

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        Toast.makeText(getApplicationContext(), "Mic Accessed!", Toast.LENGTH_SHORT).show();

        jobFinished(params, false);

    }


    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("isMicAccessed", "False!");
        jobCanceled = true;
        speechCanceled = true;
        speech = "";
        return false;
    }
}

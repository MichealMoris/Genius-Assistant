package com.genius.geniusassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SentencesAdapter extends RecyclerView.Adapter<SentencesAdapter.SentencesViewHolder> {

    List<Sentences> sentencesList;

    public SentencesAdapter(List<Sentences> sentencesList) {
        this.sentencesList = sentencesList;
    }

    @NonNull
    @Override
    public SentencesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sentences, parent, false);
        return new SentencesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SentencesViewHolder holder, int position) {


        holder.listening_preview.setText(sentencesList.get(position).getSentences());
        holder.save_time.setText(String.valueOf(sentencesList.get(position).getTime()));

    }

    @Override
    public int getItemCount() {
        return sentencesList.size();
    }

    class SentencesViewHolder extends RecyclerView.ViewHolder{

        TextView listening_preview, save_time;

        public SentencesViewHolder(@NonNull View itemView) {
            super(itemView);

            listening_preview = itemView.findViewById(R.id.listening_preview);
            save_time = itemView.findViewById(R.id.save_time);

        }
    }

}

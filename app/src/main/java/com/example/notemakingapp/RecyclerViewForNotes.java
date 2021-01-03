package com.example.notemakingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemakingapp.Room.Note;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewForNotes extends RecyclerView.Adapter<RecyclerViewForNotes.RecyclerViewHolder> {
    List<Note> noteList = new ArrayList<Note>();
    Context co;
    public RecyclerViewForNotes(Activity mainActivity) {
        co = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(co).inflate(R.layout.card_for_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textViewTitle.setText(noteList.get(position).getPrice());
        holder.textViewDescrip.setText(noteList.get(position).getDescription());
        holder.textViewPrior.setText(String.valueOf(noteList.get(position).getPriority()));
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public Note getNote(int adapterPosition) {
        return noteList.get(adapterPosition);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrior, textViewDescrip;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewPrior = itemView.findViewById(R.id.Prio);
            textViewDescrip = itemView.findViewById(R.id.descrip);

        }
    }
}

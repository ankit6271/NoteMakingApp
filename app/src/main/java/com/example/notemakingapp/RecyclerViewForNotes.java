package com.example.notemakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    static List<Note> noteList = new ArrayList<Note>();
    private static String nameAdd="EDIT";
   static Context co;
    public RecyclerViewForNotes(Activity mainActivity) {
        co = mainActivity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textViewTitle.setText(noteList.get(position).getPrice());
        holder.textViewDescrip.setText(noteList.get(position).getDescription());
        holder.textViewPrior.setText(String.valueOf(noteList.get(position).getPriority()));
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
       /*   Why should we not use notifyDataSetChanged
        1-)this method notifies data change if occurred by first deleting all the list items by telling that list is invalid than adding list items(modified one) again on recycler view.hence making a lot of overhead
        2-)We dont get any animations when a new card is added,card is removed ,data is changed

        So we have certain ways to solve the above problem:-
        so we have to use DiffUtil class which checks for the difference in in the list items before and after data change occured and calls certain methods such as notifyItemChanged/Inserted/Removed which have perimeter which tells us about position where data changed.
         This class is added to adapter of recycler view but convinient way is to use ListAdapter which is extended when adapter class created by user as this class extends RecyclerView.ViewHolder
        And this class done all the work of comparing lists on background thread
        so we here are using ListAdapter class to make changes    */
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

        public RecyclerViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewPrior = itemView.findViewById(R.id.Prio);
            textViewDescrip = itemView.findViewById(R.id.descrip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(itemView.getContext(),AddActivity.class);
                    i.putExtra("nameAdd",nameAdd);
                    i.putExtra("Title",noteList.get(getAdapterPosition()).getPrice());
                    i.putExtra("Prior",noteList.get(getAdapterPosition()).getPriority());
                    i.putExtra("Desc",noteList.get(getAdapterPosition()).getDescription());
                    i.putExtra("Id",noteList.get(getAdapterPosition()).getId());
                    itemView.getContext().startActivity(i);

                }
            });

        }
    }
}

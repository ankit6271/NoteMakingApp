package com.example.notemakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notemakingapp.Room.Note;

/*   Why should we not use notifyDataSetChanged
        1-)this method notifies data change if occurred by first deleting all the list items by telling that list is invalid than adding list items(modified one) again on recycler view.hence making a lot of overhead
        2-)We dont get any animations when a new card is added,card is removed ,data is changed

        So we have certain ways to solve the above problem:-
        so we have to use DiffUtil class which checks for the difference in in the list items before and after data change occured and calls certain methods such as notifyItemChanged/Inserted/Removed which have perimeter which tells us about position where data changed.
         This class is added to adapter of recycler view but convinient way is to use ListAdapter which is extended when adapter class created by user as this class extends RecyclerView.ViewHolder
        And this class done all the work of comparing lists on background thread
        so we here are using ListAdapter class to make changes and DiffUtil and LIstAdapter works on different thread  */


public class RecyclerViewForNotes extends ListAdapter<Note, RecyclerViewForNotes.RecyclerViewHolder> {
    //    for initial set up of list
//    List<Note> noteList =new ArrayList<>();
    private static String nameAdd = "EDIT";

    protected RecyclerViewForNotes() {
        super(diffCallback);
    }

    public static DiffUtil.ItemCallback<Note> diffCallback = new DiffUtil.ItemCallback<Note>() {

        //to check wheather the item which is comapared is same or not
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        //to compare the contents inside the list are same or not
        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getPrice().equals(newItem.getPrice()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_for_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        initial one without ListAdapter class
//        holder.textViewPrior.setText(String.valueOf(noteList.get(position).getPriority()));

        holder.textViewPrior.setText(String.valueOf(getItem(position).getPriority()));
        holder.textViewTitle.setText(getItem(position).getPrice());
        holder.textViewDescrip.setText(getItem(position).getDescription());
    }

//    For setting noteList changed or initial one
//    public void setNoteList(List<Note> noteList) {
//        this.noteList = noteList;
//        notifyDataSetChanged();
//    }

//    Not req as ListAdapter kept the record of this
//    @Override
//    public int getItemCount() {
//        return noteList.size();
//    }

    public Note getNote(int adapterPosition) {
        return getItem(adapterPosition);
    }

    public  class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewPrior, textViewDescrip;

        public RecyclerViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewPrior = itemView.findViewById(R.id.Prio);
            textViewDescrip = itemView.findViewById(R.id.descrip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(), AddActivity.class);
                    i.putExtra("nameAdd", nameAdd);
//                    before ListAdapter
//                    i.putExtra("Title", noteList.get(getAdapterPosition()).getPrice());
//                    i.putExtra("Prior", noteList.get(getAdapterPosition()).getPriority());
//                    i.putExtra("Desc", noteList.get(getAdapterPosition()).getDescription());
//                    i.putExtra("Id", noteList.get(getAdapterPosition()).getId());

                    i.putExtra("Title", getItem(getAdapterPosition()).getPrice());
                    i.putExtra("Prior", getItem(getAdapterPosition()).getPriority());
                    i.putExtra("Desc", getItem(getAdapterPosition()).getDescription());
                    i.putExtra("Id", getItem(getAdapterPosition()).getId());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}

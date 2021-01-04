package com.example.notemakingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.notemakingapp.Room.Note;
import com.example.notemakingapp.Room.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    NoteViewModel viewModel;
    private static String nameAdd="Add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        floatingActionButton.setOnClickListener(this);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        final RecyclerViewForNotes recyclerViewForNotes = new RecyclerViewForNotes();
        recyclerView.setAdapter(recyclerViewForNotes);

        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //As initially recycler view sets null data when data
                // fetched or after change in data then after that data
                // is set in recyclerview as we call dataSetChanged

                /* To set this without ListAdapter Extend this classs
                recyclerViewForNotes.setNoteList(notes);*/

                //This is the method in ListAdapter class which submits the list which is changed or initial one
                recyclerViewForNotes.submitList(notes);
            }
        });

        //To set swipe left right to delete a note
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //as we have to send a note which we wanted to be deleted sohave to create a method to get note at that adapter possition.
                viewModel.delete(recyclerViewForNotes.getNote(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        viewModel.deleteAll();
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this, AddActivity.class);
        i.putExtra("nameAdd",nameAdd);
        startActivity(i);
    }
}
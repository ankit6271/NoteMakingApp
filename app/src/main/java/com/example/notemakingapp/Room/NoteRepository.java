package com.example.notemakingapp.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    NoteDatabase noteDatabase;
    static NoteDAO noteDAO;
    LiveData<List<Note>> liveData;

    public NoteRepository(Application application) {
        noteDatabase = NoteDatabase.getInstance(application);
        noteDAO = noteDatabase.noteDAO();
        liveData = noteDAO.selectAll();
    }

    public LiveData<List<Note>> getLiveData() {
        return liveData;
    }

    public void insert(Note note) {
        new InsertAsync().execute(note);
    }

    public void deleteAll() {
        new DeleteAllAsync().execute();
    }

    private static class DeleteAllAsync extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.deleteAll();
            return null;
        }
    }

    private static class InsertAsync extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.insert(notes[0]);
            return null;
        }
    }

    public void update(Note note) {
        new UpdateAsync().execute(note);
    }

    private static class UpdateAsync extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.update(notes[0]);
            return null;
        }
    }

    public void delete(Note note) {
        new DeleteAsync().execute(note);
    }

    private static class DeleteAsync extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            noteDAO.delete(notes[0]);
            return null;
        }
    }
}

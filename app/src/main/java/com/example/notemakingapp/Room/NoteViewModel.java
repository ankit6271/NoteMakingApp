package com.example.notemakingapp.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    LiveData<List<Note>> liveData;
    NoteRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        liveData = repository.getLiveData();
    }

    public LiveData<List<Note>> getLiveData() {
        return liveData;
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }
    public void deleteAll() {
        repository.deleteAll();
    }
}

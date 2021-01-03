package com.example.notemakingapp.Room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();

    public static NoteDatabase instance;

    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "Note")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }
    public static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InsertAsync(instance).execute();
        }
        class InsertAsync extends AsyncTask<Void,Void,Void>{
            NoteDatabase instance;

            public InsertAsync(NoteDatabase instance) {
                this.instance=instance;
            }
            @Override
            protected Void doInBackground(Void... voids) {
                instance.noteDAO().insert(new Note("College Note","This note is about notes",1));
                return null;
            }
        }
    };

}

package com.saikat.mynotes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;


import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private String TAG = "XXXNoteViewModel";
    private NoteDao noteDao;
    private NoteRoomDatabase noteDB;
    private LiveData<List<Note>> mAllNotes;
    private String user_id;

    public NoteViewModel(@NonNull Application application) {
        super(application);


        noteDB = NoteRoomDatabase.getDatabase(application);
        noteDao = noteDB.noteDao();
        mAllNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        new InsertAsyncTask(noteDao).execute(note);
    }

    public LiveData<List<Note>> getMyAllNotes() {
        return mAllNotes;
    }


    public void delete(Note note) {
        new DeleteAsyncTask(noteDao).execute(note);
    }


    public void deleteAll() {
        new DeleteAllAsyncTask(noteDao).execute();
    }


    private class InsertAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {

        NoteDao noteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplication(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        NoteDao noteDao;

        public DeleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecute: Deleted all notes");
        }
    }

}

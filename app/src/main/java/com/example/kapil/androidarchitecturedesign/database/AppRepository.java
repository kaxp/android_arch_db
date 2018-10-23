package com.example.kapil.androidarchitecturedesign.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.kapil.androidarchitecturedesign.util.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<NoteEntity>> mNotes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static final String TAG = "AppRepository";

    public static AppRepository getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
            
        }

        return ourInstance;
    }

    private AppRepository(Context context) {

        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNodes();
    }

    public void  addSampleData(final int size) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                long[] i = mDb.noteDao().insertAll(SampleData.getNotesDusra(size));

                List<NoteEntity> mList = mDb.noteDao().getAllNotesOnly();

                Log.e(TAG, "run: "+ mList);
            }
        });
    }

    //this decide where the data to be fetched, from local Db or cloud.
    private LiveData<List<NoteEntity>> getAllNodes(){
        return mDb.noteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }
}

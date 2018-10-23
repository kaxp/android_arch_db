package com.example.kapil.androidarchitecturedesign.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.kapil.androidarchitecturedesign.database.AppRepository;
import com.example.kapil.androidarchitecturedesign.database.NoteEntity;


import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotes;
    public AppRepository mRepo;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepo = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepo.mNotes;
    }

    public void addSampleData(int size) {
        mRepo.addSampleData(size);
    }

    public void deleteAllNotes() {
        mRepo.deleteAllNotes();
    }
}

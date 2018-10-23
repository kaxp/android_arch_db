package com.example.kapil.androidarchitecturedesign;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kapil.androidarchitecturedesign.adapter.NotesAdapter;
import com.example.kapil.androidarchitecturedesign.database.NoteEntity;
import com.example.kapil.androidarchitecturedesign.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv)
    RecyclerView mRv;

    @OnClick(R.id.fab)
    void fabClickHandler(){
        Intent i = new Intent(MainActivity.this,EditorActivity.class);
        startActivity(i);
    }


    List<NoteEntity> notesData = new ArrayList<>();

    private NotesAdapter mAdapter;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);

        initRecyclerView();
        initViewModel();


    }

    private void initViewModel() {

         final Observer<List<NoteEntity>> notesObserver =
                 new Observer<List<NoteEntity>>() {
                     @Override
                     public void onChanged(@Nullable List<NoteEntity> noteEntities) {

                         notesData.clear();
                         notesData.addAll(noteEntities);

                         if (mAdapter == null) {
                             mAdapter = new NotesAdapter(notesData,MainActivity.this);
                             mRv.setAdapter(mAdapter);
                         }else {
                             mAdapter.notifyDataSetChanged();
                         }
                     }
                 };


        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mViewModel.mNotes.observe(this,notesObserver);
    }

    private void initRecyclerView() {
            mRv.setHasFixedSize(true);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        mRv.setLayoutManager(lm);

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {

              addSampleData();
            return true;
        }else if (id == R.id.action_delete_all){
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mViewModel.addSampleData(notesData.size());
    }
}

package com.example.tugastts.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tugastts.Adapter.KrsAdapter;
import com.example.tugastts.Model.Krs;
import com.example.tugastts.R;

import java.util.ArrayList;

public class KrsRecylerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private KrsAdapter krsAdapter;
    private ArrayList<Krs> krsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs_recyler_view);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
        addData();

        recyclerView = findViewById(R.id.rvKrs);
        krsAdapter = new KrsAdapter(krsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KrsRecylerViewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(krsAdapter);
    }
    private  void addData(){
        krsArrayList =  new ArrayList<>();
        krsArrayList.add(new Krs("123","BI","Senin","3","3","Hendra","20"));
        krsArrayList.add(new Krs("456","Mobile Bahaya","Selasa .","4","3","Argo Wibowo","30"));
    }
    }

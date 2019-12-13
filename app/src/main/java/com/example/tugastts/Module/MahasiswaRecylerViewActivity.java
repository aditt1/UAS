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

import com.example.tugastts.Adapter.MahasiswaAdapter;
import com.example.tugastts.Model.Mahasiswa;
import com.example.tugastts.R;

import java.util.ArrayList;

public class MahasiswaRecylerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter mhsAdapter;
    private ArrayList<Mahasiswa> mhsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_recyler_view);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
        addData();

        recyclerView = findViewById(R.id.rvMahasiswa);
        mhsAdapter = new MahasiswaAdapter(mhsArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MahasiswaRecylerViewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);
    }
    private  void addData(){
        mhsArrayList =  new ArrayList<>();
        mhsArrayList.add(new Mahasiswa("Tjong Surya","72160088","Rumah Bill Gate","coding@gmail.com",R.drawable.ic_launcher_background));
        mhsArrayList.add(new Mahasiswa("Aditya Halimawan","72160032","Jalan .","adit@gmail.com",R.drawable.ic_launcher_background));
    }
    }


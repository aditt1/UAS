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

import com.example.tugastts.Adapter.MatkulAdapter;
import com.example.tugastts.Model.Matkul;
import com.example.tugastts.R;

import java.util.ArrayList;

public class MatkulRecylerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MatkulAdapter mhsSIAdapter;
    private ArrayList<Matkul> mhsSIArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul_recyler_view);
        this.setTitle("SI KRS - Hai {Nama Mhs}");

        addData();

        recyclerView = findViewById(R.id.rvMatkul);
        mhsSIAdapter = new MatkulAdapter(mhsSIArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MatkulRecylerViewActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsSIAdapter);
    }
    private  void addData(){
        mhsSIArrayList =  new ArrayList<>();
        mhsSIArrayList.add(new Matkul("123","BI","Senin","3","3"));
        mhsSIArrayList.add(new Matkul("456","Mobile Bahaya","Rabu ","3","3"));
    }
}

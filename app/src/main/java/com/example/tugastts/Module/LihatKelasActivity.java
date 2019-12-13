package com.example.tugastts.Module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tugastts.R;

public class LihatKelasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kelas);
        this.setTitle("SI KRS - Hai {Nama Mahasiswa}");
    }
}

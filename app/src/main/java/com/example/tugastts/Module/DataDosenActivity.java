package com.example.tugastts.Module;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tugastts.R;

public class DataDosenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_dosen);

        Button simpanButton = (Button) findViewById(R.id.btnSimpan);
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DataDosenActivity.this);
                builder.setMessage("Apakah anda yakin untuk simpan nilai mata kuliah ini?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DataDosenActivity.this, "Tidak jadi simpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DataDosenActivity.this, "Melakukan SIMPAN !!",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(DataDosenActivity.this,DosenActivity.class);
                                startActivity(i);
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }}


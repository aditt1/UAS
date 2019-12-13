package com.example.tugastts.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tugastts.R;

public class DosenActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(DosenActivity.this);
                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DosenActivity.this, "Tidak jadi Log Out",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(DosenActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        ImageButton krsBtn = findViewById(R.id.btnDaftarKrs);
        ImageButton dataDiriBtn = findViewById(R.id.btnData);
        ImageButton dataKelasBtn = findViewById(R.id.btnLihat);

        krsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,LihatKelasActivity.class);
                startActivity(i);
            }
        });

        dataDiriBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,DataDosenActivity.class);
                startActivity(i);
            }
        });

        dataKelasBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(DosenActivity.this,LihatKelasActivity.class);
                startActivity(i);
            }
        });



            }
        }

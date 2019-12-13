package com.example.tugastts.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tugastts.R;


public class AdminActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
                builder.setMessage("Apakah anda yakin untuk Log Out?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AdminActivity.this, "Tidak jadi Log Out",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(AdminActivity.this, LoginActivity.class);
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
        setContentView(R.layout.activity_admin);


        ImageButton dosenBtn = findViewById(R.id.btnDosen);
        ImageButton dataBtn = findViewById(R.id.btnData);
        ImageButton matkulBtn = findViewById(R.id.btnMatkul);
        ImageButton krsBtn = findViewById(R.id.btnKRS);
        ImageButton mhsBtn = findViewById(R.id.btnMhs);
        ImageButton tambahmhsBtn = findViewById(R.id.btnTambahMhs);

        dosenBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, DosenRecylerViewActivity.class);
                startActivity(i);
            }
        });

        dataBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, CreateDosenActivity.class);
                startActivity(i);
            }
        });

        matkulBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, MatkulRecylerViewActivity.class);
                startActivity(i);
            }
        });

        krsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, KrsRecylerViewActivity.class);
                startActivity(i);
            }
        });

        mhsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, MahasiswaRecylerViewActivity.class);
                startActivity(i);
            }
        });
        tambahmhsBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent(AdminActivity.this, CreateMahasiswaActivity.class);
                startActivity(i);
            }
        });
            }
        }

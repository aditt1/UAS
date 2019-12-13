package com.example.tugastts.Module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tugastts.R;

public class MainActivity extends AppCompatActivity {

    TextView Splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Splash = findViewById(R.id.splash);
        this.getSupportActionBar().hide();

        SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin", null);
        if (statusLogin != null) {
            if (statusLogin.equals("Admin")) {
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            } else if (statusLogin.equals("Dosen")) {
                Intent intent = new Intent(MainActivity.this, DosenActivity.class);
                startActivity(intent);
            }
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }, 3000L); //3000L = 3 detik
        }
    }
}
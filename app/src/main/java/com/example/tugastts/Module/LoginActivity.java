package com.example.tugastts.Module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tugastts.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs =
                LoginActivity.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin", null);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(myButtonLoginClick);


        }
    private View.OnClickListener myButtonLoginClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            SharedPreferences prefs = LoginActivity.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin", null);
            SharedPreferences.Editor edit = prefs.edit();
            TextView username = findViewById(R.id.txtEmail);

if(username.getText().toString().contains("@staff.ukdw.ac.id")){
    edit.putString("isLogin","Admin");
    edit.commit();
    Intent intent = new Intent(LoginActivity.this, AdminActivity.class );
    startActivity(intent);
}else if(username.getText().toString().contains("@si.ukdw.ac.id")){
    edit.putString("isLogin","Dosen");
    edit.commit();
    Intent intent = new Intent(LoginActivity.this, DosenActivity.class );
    startActivity(intent);
}

       }
    };
    }



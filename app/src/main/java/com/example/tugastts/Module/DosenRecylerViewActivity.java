package com.example.tugastts.Module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tugastts.Adapter.DosenAdapter;
import com.example.tugastts.Model.Dosen;
import com.example.tugastts.Network.GetDataService;
import com.example.tugastts.Network.RetrofitClientInstance;
import com.example.tugastts.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DosenRecylerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DosenAdapter dosenAdapter;
    private ArrayList<Dosen> dosenList;
    private List<Dosen> dsn;
    ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences prefs = DosenRecylerViewActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);
        String statusLogin = prefs.getString("isLogin",null);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("isLogin",null);
        edit.commit();
        Intent i = new Intent(DosenRecylerViewActivity.this,CreateDosenActivity.class);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_recyler_view);
        this.setTitle("SI KRS - Hai Admin");
        recyclerView = findViewById(R.id.rvDosen);
        /* tambahData();
         */
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu bentar");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ArrayList<Dosen>> call = service.getDosenAll("72160032");
        call.enqueue(new Callback<ArrayList<Dosen>>() {
            @Override
            public void onResponse(Call<ArrayList<Dosen>> call, Response<ArrayList<Dosen>> response) {
                progressDialog.dismiss();
                dosenList = response.body();
                dosenAdapter = new DosenAdapter(response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DosenRecylerViewActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(dosenAdapter);
            }
            @Override
            public void onFailure(Call<ArrayList<Dosen>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DosenRecylerViewActivity.this,"Login gagal,coba lagi",Toast.LENGTH_LONG);

            }
        });
        registerForContextMenu(recyclerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Dosen dosen = dosenList.get(item.getGroupId());
        if(item.getTitle() == "Ubah Data Dosen"){
            Intent i = new Intent(DosenRecylerViewActivity.this, CreateDosenActivity.class);
            i.putExtra("id_dosen",dosen.getId());
            i.putExtra("nama_dosen", dosen.getNamaDosen());
            i.putExtra("nidn",dosen.getNidn());
            i.putExtra("alamat_dosen",dosen.getAlamat());
            i.putExtra("email_dosen",dosen.getEmail());
            i.putExtra("foto",dosen.getFoto());
            i.putExtra("gelar", dosen.getGelar());
            i.putExtra("is_update",true);
            startActivity(i);
        }
        else if(item.getTitle() == "Hapus Data Dosen"){
            progressDialog = new ProgressDialog(DosenRecylerViewActivity.this);
            progressDialog.show();

            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Dosen> call = service.delete_dosen(dosen.getId(),"72160032");
            call.enqueue(new Callback<Dosen>() {
                @Override
                public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                    progressDialog.dismiss();
                    Toast.makeText(DosenRecylerViewActivity.this, "Berhasil Delete", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DosenRecylerViewActivity.this, DosenRecylerViewActivity.class);
                    startActivity(intent);
                }
                @Override
                public void onFailure(Call<Dosen> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(DosenRecylerViewActivity.this,"Gagal Delete,Coba Lagi",Toast.LENGTH_LONG);
                }
            });

        };
        return super.onContextItemSelected(item);
    }
}

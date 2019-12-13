package com.example.tugastts.Module;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugastts.Model.Dosen;
import com.example.tugastts.Model.Mahasiswa;
import com.example.tugastts.Network.GetDataService;
import com.example.tugastts.Network.RetrofitClientInstance;
import com.example.tugastts.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMahasiswaActivity extends AppCompatActivity {
    private EditText txtNama, txtNim, txtAlamat, txtEmail, foto;
    private ImageView imgMahasiswa;
    private ProgressDialog progressDialog;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;
    private String stringImg;
    boolean update = false;
    int id;
    private static int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mahasiswa);

        Button simpanButton = (Button) findViewById(R.id.btnSimpan);
        txtNama = (EditText) findViewById(R.id.txtNama);
        txtNim = (EditText) findViewById(R.id.txtNim);
        txtAlamat = (EditText) findViewById(R.id.txtAlamat);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        foto = (EditText) findViewById(R.id.txtFoto);
        cek_update();
        final Button btnUpload = findViewById(R.id.btnCari);

        if (txtNim.getText().toString().length() == 0) {
            txtNim.setError("Harap isi Nim Anda");
        }
        if (txtNama.getText().toString().length() == 0) {
            txtNama.setError("Harap isi Nama Anda");
        }
        if (txtAlamat.getText().toString().length() == 0) {
            txtAlamat.setError("Harap isi Alamat Anda");
        }
        if (txtEmail.getText().toString().length() == 0) {
            txtEmail.setError("Harap isi Email Anda");
        }
        else {
            Toast.makeText(CreateMahasiswaActivity.this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();
        }

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String mimeTypes = ("image/jpeg");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
            }
        });

        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateMahasiswaActivity.this);
                builder.setMessage("Apakah anda yakin untuk mereset nilai protein?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateMahasiswaActivity.this, "Tidak jadi reset",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateMahasiswaActivity.this, "Melakukan RESET !!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void insert_data() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();


            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Mahasiswa> call;
            if (update) {
                call = service.update_mahasiswa(
                        id,
                        txtNama.getText().toString(),
                        txtAlamat.getText().toString(),
                        txtNim.getText().toString(),
                        txtEmail.getText().toString(),
                        foto.getText().toString(),
                        "72160032");
            } else {
                call = service.insert_mahasiswa(
                        txtNama.getText().toString(),
                        txtAlamat.getText().toString(),
                        txtNim.getText().toString(),
                        txtEmail.getText().toString(),
                        foto.getText().toString(),
                        "72160032"
                );
            }
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    progressDialog.dismiss();
                    Toast.makeText(CreateMahasiswaActivity.this, "Berhasil Save", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateMahasiswaActivity.this, MahasiswaRecylerViewActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(CreateMahasiswaActivity.this, "Gagal Save,Coba Lagi", Toast.LENGTH_LONG);

                }
            });
        }


    void cek_update() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        update = extras.getBoolean("is_update");
        txtNama.setText(extras.getString("nama_mahasiswa"));
        id = extras.getInt("id_mahasiswa");
        txtNim.setText(extras.getString("nim"));
        txtAlamat.setText(extras.getString("alamat_mahasiswa"));
        txtEmail.setText(extras.getString("email_mahasiswa"));
        foto.setText(extras.getString("foto"));


        if (txtNim.getText().toString().length() == 0) {
            txtNim.setError("Harap isi Nim Anda");
        }
        if (txtNama.getText().toString().length() == 0) {
            txtNama.setError("Harap isi Nama Anda");
        }
        if (txtAlamat.getText().toString().length() == 0) {
            txtAlamat.setError("Harap isi Alamat Anda");
        }
        if (txtEmail.getText().toString().length() == 0) {
            txtEmail.setError("Harap isi Email Anda");
        }
        else {
            Toast.makeText(CreateMahasiswaActivity.this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();

        }
    }
    public void loadImagefromGallery (View view){
        // buat intentnya
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
}


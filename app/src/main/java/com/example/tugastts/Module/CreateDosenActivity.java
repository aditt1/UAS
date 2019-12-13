package com.example.tugastts.Module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugastts.Model.Dosen;
import com.example.tugastts.Network.GetDataService;
import com.example.tugastts.Network.RetrofitClientInstance;
import com.example.tugastts.R;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CreateDosenActivity extends AppCompatActivity {
    private EditText txtNamaDosen, txtNidn, txtAlamat, txtGelar, txtEmail, foto;
    private ImageView imgDosen;
    private ProgressDialog progressDialog;
    private static final int GALLERY_REQUEST_CODE = 58;
    private static final int FILE_ACCESS_REQUEST_CODE = 58;
    private String stringImg;
    boolean update = false;
    int id;
    private static int RESULT_LOAD_IMG = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case FILE_ACCESS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    //Permission Granted
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData return the content URI
                    Uri selectedImage = data.getData();
                    //imgDosen tu imageView
                    imgDosen.setImageURI(selectedImage);

                    //pakai string bitmap
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    //Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    //Move to first row
                    cursor.moveToFirst();
                    //Get the column of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Get string value in the column
                    String imgDecodableString = cursor.getString(columnIndex);
                    foto.setText(imgDecodableString);
                    cursor.close();

                    Bitmap bm = BitmapFactory.decodeFile(imgDecodableString);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] b = baos.toByteArray();


                    stringImg = Base64.encodeToString(b, Base64.DEFAULT);
                    break;
            }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dosen);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, FILE_ACCESS_REQUEST_CODE);
        }
        Button simpanButton = (Button) findViewById(R.id.btnSimpan);
        txtNamaDosen = (EditText) findViewById(R.id.txtNama);
        txtNidn = (EditText) findViewById(R.id.txtNIDN);
        txtGelar = (EditText) findViewById(R.id.txtGelar);
        txtAlamat = (EditText) findViewById(R.id.txtAlamat);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        foto = (EditText) findViewById(R.id.txtFoto);
        cek_update();
        final Button btnUpload = findViewById(R.id.btnCari);

        if (txtNamaDosen.getText().toString().length() == 0) {
            txtNamaDosen.setError("Harap isi Nama Anda");
        }
        if (txtNidn.getText().toString().length() == 0) {
            txtNidn.setError("Harap isi Nim Anda");
        }
        if (txtGelar.getText().toString().length() == 0) {
            txtGelar.setError("Harap isi Gelar Anda");
        }
        if (txtAlamat.getText().toString().length() == 0) {
            txtAlamat.setError("Harap isi Alamat Anda");
        }
        if (txtEmail.getText().toString().length() == 0) {
            txtEmail.setError("Harap isi Email Anda");
        } else {
            Toast.makeText(CreateDosenActivity.this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();

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
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateDosenActivity.this);
                builder.setMessage("Apakah anda yakin untuk simpan nilai mata kuliah ini?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateDosenActivity.this, "Tidak jadi simpan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CreateDosenActivity.this, "Melakukan SIMPAN !!",
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

            if (txtNamaDosen.getText().toString().length() == 0) {
                txtNidn.setError("Harap isi Nidn Anda");
            }
            if (txtNamaDosen.getText().toString().length() == 0) {
                txtNamaDosen.setError("Harap isi Nama Anda");
            } else {
                Toast.makeText(CreateDosenActivity.this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();


                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Dosen> call;
                if (update) {
                    call = service.update_dosen(
                            id,
                            txtNamaDosen.getText().toString(),
                            txtAlamat.getText().toString(),
                            txtNidn.getText().toString(),
                            txtGelar.getText().toString(),
                            txtEmail.getText().toString(),
                            foto.getText().toString(),
                            "72160032");
                } else {
                    call = service.insert_dosen(
                            txtNamaDosen.getText().toString(),
                            txtAlamat.getText().toString(),
                            txtNidn.getText().toString(),
                            txtGelar.getText().toString(),
                            txtEmail.getText().toString(),
                            foto.getText().toString(),
                            "72160032"
                    );
                }
                call.enqueue(new Callback<Dosen>() {
                    @Override
                    public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateDosenActivity.this, "Berhasil Save", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateDosenActivity.this, DosenRecylerViewActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Dosen> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(CreateDosenActivity.this, "Gagal Save,Coba Lagi", Toast.LENGTH_LONG);

                    }
                });
            }
        }
            void cek_update () {
                Bundle extras = getIntent().getExtras();
                if (extras == null) {
                    return;
                }
                update = extras.getBoolean("is_update");
                txtNamaDosen.setText(extras.getString("nama_dosen"));
                id = extras.getInt("id_dosen");
                txtNidn.setText(extras.getString("nidn"));
                txtAlamat.setText(extras.getString("alamat_dosen"));
                txtEmail.setText(extras.getString("email_dosen"));
                foto.setText(extras.getString("foto"));
                txtGelar.setText(extras.getString("gelar"));

                if (txtNamaDosen.getText().toString().length() == 0) {
                    txtNidn.setError("Harap isi Nidn Anda");
                }
                if (txtNamaDosen.getText().toString().length() == 0) {
                    txtNamaDosen.setError("Harap isi Nama Anda");
                } else {
                    Toast.makeText(CreateDosenActivity.this, "Data berhasil diinput", Toast.LENGTH_SHORT).show();

                }
            }
                public void loadImagefromGallery (View view){
                // buat intentnya
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }

            }

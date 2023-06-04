package com.example.profolio.add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.profolio.modelfragment.OrganisasiModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddOrganisasiActivity extends AppCompatActivity {
    EditText edtNamaOrganisasi, edtDeskripsiOrganisasi, edtJabatanOrganisasi, edtTahunMulaiOrganisasi, edtTahunSelesaiOrganisasi;
    AppCompatButton btnAddOrganisasi, btnUploadSertif;
    ImageView ivOrganisasi;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    Uri imageUri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organisasi);

        edtNamaOrganisasi = findViewById(R.id.edtNamaOrganisasi);
        edtDeskripsiOrganisasi = findViewById(R.id.edtDeskripsiOrganisasi);
        edtJabatanOrganisasi = findViewById(R.id.edtJabatanOrganisasi);
        edtTahunMulaiOrganisasi = findViewById(R.id.edtTahunMulaiOrganisasi);
        edtTahunSelesaiOrganisasi = findViewById(R.id.edtTahunSelesaiOrganisasi);

        btnAddOrganisasi = findViewById(R.id.btnAddOrganisasi);
        btnUploadSertif = findViewById(R.id.btn_UploadSertif);
        ivOrganisasi = findViewById(R.id.iv_Organisasi);

        btnUploadSertif.setOnClickListener(v -> {
            selectImage();
        });

        btnAddOrganisasi.setOnClickListener(v -> {
            uploadImage();
            String getNamaOrganisasi = edtNamaOrganisasi.getText().toString();
            String getJabatanOrganisasi = edtJabatanOrganisasi.getText().toString();
            String getDeskripsiOrganisasi = edtDeskripsiOrganisasi.getText().toString();
            String getTahunMulaiOrganisasi = edtTahunMulaiOrganisasi.getText().toString();
            String getTahunSelesaiOrganisasi = edtTahunSelesaiOrganisasi.getText().toString();

            if (getNamaOrganisasi.isEmpty()) {
                edtNamaOrganisasi.setError("Entry Organisasi Name");
            } else if (getDeskripsiOrganisasi.isEmpty()) {
                edtDeskripsiOrganisasi.setError("Entry Organisasi Description");
            } else if (getJabatanOrganisasi.isEmpty()) {
                edtJabatanOrganisasi.setError("Entry Jabatan Status");
            } else if (getTahunMulaiOrganisasi.isEmpty()) {
                edtTahunMulaiOrganisasi.setError("Entry Tahun Mulai");
            } else if (getTahunSelesaiOrganisasi.isEmpty()) {
                edtTahunSelesaiOrganisasi.setError("Entry Tahun Selesai");
            } else {
                database.child("Organisasi").push().setValue(new OrganisasiModel(getNamaOrganisasi, getJabatanOrganisasi,
                        getTahunMulaiOrganisasi, getTahunSelesaiOrganisasi, getDeskripsiOrganisasi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddOrganisasiActivity.this, "Data has been added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddOrganisasiActivity.this, HomePageActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddOrganisasiActivity.this, "Data failed to add", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void selectImage(){
        Intent selectImg = new Intent();
        selectImg.setType("image/*");
        selectImg.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(selectImg, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){
            imageUri = data.getData();
            ivOrganisasi.setImageURI(imageUri);
        }
    }

    private void uploadImage(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference("images/*"+fileName);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ivOrganisasi.setImageURI(null);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddOrganisasiActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
package com.example.profolio.add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profolio.modelfragment.PrestasiModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPrestasiActivity extends AppCompatActivity {
    EditText edtNamaPrestasi, edtDeskripsiPrestasi, edtJabatanPrestasi, edtTahunPrestasi;
    AppCompatButton btnAddPrestasi;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prestasi);

        edtNamaPrestasi = findViewById(R.id.edtNamaPrestasi);
        edtDeskripsiPrestasi = findViewById(R.id.edtDeskripsiPrestasi);
        edtJabatanPrestasi = findViewById(R.id.edtJabatanPrestasi);
        edtTahunPrestasi = findViewById(R.id.edtTahunPrestasi);

        btnAddPrestasi = findViewById(R.id.btnAddPrestasi);

        btnAddPrestasi.setOnClickListener(v -> {
            String getNamaPrestasi = edtNamaPrestasi.getText().toString();
            String getJabatanPrestasi = edtDeskripsiPrestasi.getText().toString();
            String getDeskripsiPrestasi = edtJabatanPrestasi.getText().toString();
            String getTahunPrestasi = edtTahunPrestasi.getText().toString();


            if (getNamaPrestasi.isEmpty()) {
                edtNamaPrestasi.setError("Entry Prestasi Name");
            } else if (getDeskripsiPrestasi.isEmpty()) {
                edtDeskripsiPrestasi.setError("Entry Prestasi Description");
            } else if (getJabatanPrestasi.isEmpty()) {
                edtJabatanPrestasi.setError("Entry Jabatan Status");
            } else if (getTahunPrestasi.isEmpty()) {
                edtTahunPrestasi.setError("Entry Tahun Mulai");
            } else {
                database.child("Prestasi").push().setValue(new PrestasiModel(getNamaPrestasi,
                        getJabatanPrestasi, getDeskripsiPrestasi, getTahunPrestasi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddPrestasiActivity.this, "Data has been added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddPrestasiActivity.this, HomePageActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPrestasiActivity.this, "Data failed to add", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
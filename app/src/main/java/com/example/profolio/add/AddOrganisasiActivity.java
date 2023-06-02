package com.example.profolio.add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profolio.ModelFragment.OrganisasiModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class AddOrganisasiActivity extends AppCompatActivity {
    EditText edtNamaOrganisasi, edtDeskripsiOrganisasi, edtJabatanOrganisasi, edtTahunMulaiOrganisasi, edtTahunSelesaiOrganisasi;
    AppCompatButton btnAddOrganisasi;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

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

        btnAddOrganisasi.setOnClickListener(v -> {
            String getNamaOrganisasi = edtNamaOrganisasi.getText().toString();
            String getJabatanOrganisasi = edtJabatanOrganisasi.getText().toString();
            String getDeskripsiOrganisasi = edtDeskripsiOrganisasi.getText().toString();
            String getTahunMulaiOrganisasi = edtTahunMulaiOrganisasi.getText().toString();
            String getTahunSelesaiOrganisasi = edtTahunSelesaiOrganisasi.getText().toString();

            if (getNamaOrganisasi.isEmpty()) {
                edtNamaOrganisasi.setError("Entry Organisasi Name");
            } else if (getDeskripsiOrganisasi.isEmpty()) {
                edtDeskripsiOrganisasi.setError("Entry Organisasi Description");
            } else if (getDeskripsiOrganisasi.isEmpty()) {
                edtJabatanOrganisasi.setError("Entry Organisasi Description");
            } else if (getDeskripsiOrganisasi.isEmpty()) {
                edtTahunMulaiOrganisasi.setError("Entry Organisasi Description");
            } else if (getDeskripsiOrganisasi.isEmpty()) {
                edtTahunSelesaiOrganisasi.setError("Entry Organisasi Description");
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
}
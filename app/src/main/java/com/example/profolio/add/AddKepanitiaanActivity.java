package com.example.profolio.add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profolio.modelfragment.KepanitiaanModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddKepanitiaanActivity extends AppCompatActivity {
    EditText edtNamaKepanitiaan, edtDeskripsiKepanitiaan, edtJabatanKepanitiaan, edtTahunKepanitiaan;
    AppCompatButton btnAddKepanitiaan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kepanitiaan);

        edtNamaKepanitiaan = findViewById(R.id.edtNamaKepanitiaan);
        edtDeskripsiKepanitiaan = findViewById(R.id.edtDeskripsiKepanitiaan);
        edtJabatanKepanitiaan = findViewById(R.id.edtJabatanKepanitiaan);
        edtTahunKepanitiaan = findViewById(R.id.edtTahunKepanitiaan);

        btnAddKepanitiaan = findViewById(R.id.btnAddKepanitiaan);

        btnAddKepanitiaan.setOnClickListener(v -> {
            String getNamaKepanitiaan = edtNamaKepanitiaan.getText().toString();
            String getJabatanKepanitiaan = edtDeskripsiKepanitiaan.getText().toString();
            String getDeskripsiKepanitiaan = edtJabatanKepanitiaan.getText().toString();
            String getTahunKepanitiaan = edtTahunKepanitiaan.getText().toString();


            if (getNamaKepanitiaan.isEmpty()) {
                edtNamaKepanitiaan.setError("Entry Kepanitiaan Name");
            } else if (getDeskripsiKepanitiaan.isEmpty()) {
                edtDeskripsiKepanitiaan.setError("Entry Kepanitiaan Description");
            } else if (getJabatanKepanitiaan.isEmpty()) {
                edtJabatanKepanitiaan.setError("Entry Jabatan Status");
            } else if (getTahunKepanitiaan.isEmpty()) {
                edtTahunKepanitiaan.setError("Entry Tahun Mulai");
            } else {
                database.child("Kepanitiaan").push().setValue(new KepanitiaanModel(getNamaKepanitiaan,
                        getJabatanKepanitiaan, getDeskripsiKepanitiaan, getTahunKepanitiaan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddKepanitiaanActivity.this, "Data has been added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddKepanitiaanActivity.this, HomePageActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddKepanitiaanActivity.this, "Data failed to add", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
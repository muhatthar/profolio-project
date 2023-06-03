package com.example.profolio.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profolio.AdapterFragment.AdapterPrestasi;
import com.example.profolio.ModelFragment.PrestasiModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditPrestasiActivity extends AppCompatActivity {
    TextView edtNamaPrestasi2, edtDeskripsiPrestasi2,
            edtJabatanPrestasi2, edtTahunPrestasi2;

    AppCompatButton btnEditPrestasi;

    AdapterPrestasi adapterPrestasi;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prestasi);

        edtNamaPrestasi2 = findViewById(R.id.edtNamaPrestasi2);
        edtDeskripsiPrestasi2 = findViewById(R.id.edtDeskripsiPrestasi2);
        edtJabatanPrestasi2 = findViewById(R.id.edtJabatanPrestasi2);
        edtTahunPrestasi2 = findViewById(R.id.edtTahunPrestasi2);


        btnEditPrestasi = findViewById(R.id.btnEditPrestasi);

        Intent getData = getIntent();
        String key = getData.getStringExtra("key");
        String nama = getData.getStringExtra("nama");
        String deskripsi = getData.getStringExtra("deskripsi");
        String jabatan = getData.getStringExtra("jabatan");
        String tahun = getData.getStringExtra("tahun");;

        edtNamaPrestasi2.setText(nama);
        edtDeskripsiPrestasi2.setText(deskripsi);
        edtJabatanPrestasi2.setText(jabatan);
        edtTahunPrestasi2.setText(tahun);

        btnEditPrestasi.setOnClickListener(v -> {
            String getNamaPrestasi = edtNamaPrestasi2.getText().toString();
            String getDeskripsiPrestasi = edtDeskripsiPrestasi2.getText().toString();
            String getJabatanPrestasi = edtJabatanPrestasi2.getText().toString();
            String getTahunPrestasi = edtTahunPrestasi2.getText().toString();

            if (getNamaPrestasi.isEmpty()) {
                edtNamaPrestasi2.setError("Entry Organisasi Name");
            } else if (getDeskripsiPrestasi.isEmpty()) {
                edtDeskripsiPrestasi2.setError("Entry Organisasi Description");
            } else if (getJabatanPrestasi.isEmpty()) {
                edtJabatanPrestasi2.setError("Entry Jabatan Status");
            } else if (getTahunPrestasi.isEmpty()) {
                edtTahunPrestasi2.setError("Entry Tahun Mulai");
            } else  {
                Dialog popUp = new Dialog(EditPrestasiActivity.this);
                popUp.setContentView(R.layout.popup1_edit);

                AppCompatButton confirm = popUp.findViewById(R.id.btnEditConfirm);
                AppCompatButton cancel = popUp.findViewById(R.id.btnEditCancel);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUp.dismiss();
                    }
                });

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.child("Prestasi").child(key).setValue(new PrestasiModel(getNamaPrestasi, getJabatanPrestasi,
                                getDeskripsiPrestasi, getTahunPrestasi)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditPrestasiActivity.this, "Update Data Succesfully", Toast.LENGTH_SHORT).show();
                                Intent back = new Intent(EditPrestasiActivity.this, HomePageActivity.class);
                                startActivity(back);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditPrestasiActivity.this, "Update Data Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        popUp.dismiss();
                    }
                });
                popUp.show();
            }
        });
    }
}
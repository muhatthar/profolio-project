package com.example.profolio.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profolio.adapterfragment.AdapterKepanitiaan;
import com.example.profolio.modelfragment.KepanitiaanModel;
import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditKepanitiaanActivity extends AppCompatActivity {
    TextView edtNamaKepanitiaan2, edtDeskripsiKepanitiaan2,
            edtJabatanKepanitiaan2, edtTahunKepanitiaan2;

    AppCompatButton btnEditKepanitiaan;

    AdapterKepanitiaan adapterKepanitiaan;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kepanitiaan);

        edtNamaKepanitiaan2 = findViewById(R.id.edtNamaKepanitiaan2);
        edtDeskripsiKepanitiaan2 = findViewById(R.id.edtDeskripsiKepanitiaan2);
        edtJabatanKepanitiaan2 = findViewById(R.id.edtJabatanKepanitiaan2);
        edtTahunKepanitiaan2 = findViewById(R.id.edtTahunKepanitiaan2);


        btnEditKepanitiaan = findViewById(R.id.btnEditKepanitiaan);

        Intent getData = getIntent();
        String key = getData.getStringExtra("key");
        String nama = getData.getStringExtra("nama");
        String deskripsi = getData.getStringExtra("deskripsi");
        String jabatan = getData.getStringExtra("jabatan");
        String tahun = getData.getStringExtra("tahun");;

        edtNamaKepanitiaan2.setText(nama);
        edtDeskripsiKepanitiaan2.setText(deskripsi);
        edtJabatanKepanitiaan2.setText(jabatan);
        edtTahunKepanitiaan2.setText(tahun);

        btnEditKepanitiaan.setOnClickListener(v -> {
            String getNamaKepanitiaan = edtNamaKepanitiaan2.getText().toString();
            String getDeskripsiKepanitiaan = edtDeskripsiKepanitiaan2.getText().toString();
            String getJabatanKepanitiaan = edtJabatanKepanitiaan2.getText().toString();
            String getTahunKepanitiaan = edtTahunKepanitiaan2.getText().toString();

            if (getNamaKepanitiaan.isEmpty()) {
                edtNamaKepanitiaan2.setError("Entry Organisasi Name");
            } else if (getDeskripsiKepanitiaan.isEmpty()) {
                edtDeskripsiKepanitiaan2.setError("Entry Organisasi Description");
            } else if (getJabatanKepanitiaan.isEmpty()) {
                edtJabatanKepanitiaan2.setError("Entry Jabatan Status");
            } else if (getTahunKepanitiaan.isEmpty()) {
                edtTahunKepanitiaan2.setError("Entry Tahun Mulai");
            } else  {
                Dialog popUp = new Dialog(EditKepanitiaanActivity.this);
                popUp.setContentView(R.layout.popup1_edit);
                Window window = popUp.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;

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
                        database.child("Kepanitiaan").child(key).setValue(new KepanitiaanModel(getNamaKepanitiaan, getJabatanKepanitiaan,
                                getDeskripsiKepanitiaan, getTahunKepanitiaan)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditKepanitiaanActivity.this, "Update Data Succesfully", Toast.LENGTH_SHORT).show();
                                Intent back = new Intent(EditKepanitiaanActivity.this, HomePageActivity.class);
                                startActivity(back);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditKepanitiaanActivity.this, "Update Data Failed", Toast.LENGTH_SHORT).show();
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
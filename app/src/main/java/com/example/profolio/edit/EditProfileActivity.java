package com.example.profolio.edit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profolio.R;
import com.example.profolio.modelfragment.UserModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText edtProfileUsername, edtProfileFirstName, edtProfileLastName, edtProfilePhone, edtProfileEmail,
            edtProfileSMA, edtProfileSMAPeriod, edtProfileUniversity, edtProfileUniversityPeriod, edtProfileSkills, edtProfileDeskripsi;

    AppCompatButton btn_save;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtProfileUsername = findViewById(R.id.edtProfileUsername);
        edtProfileFirstName = findViewById(R.id.edtProfileFirstName);
        edtProfileLastName = findViewById(R.id.edtProfileLastName);
        edtProfilePhone = findViewById(R.id.edtProfilePhone);
        edtProfileEmail = findViewById(R.id.edtProfileEmail);
        edtProfileSMA = findViewById(R.id.edtProfileSMA);
        edtProfileSMAPeriod = findViewById(R.id.edtProfileSMAPeriod);
        edtProfileUniversity = findViewById(R.id.edtProfileUniversity);
        edtProfileUniversityPeriod = findViewById(R.id.edtProfileUniversityPeriod);
        edtProfileSkills = findViewById(R.id.edtProfileSkills);
        edtProfileDeskripsi = findViewById(R.id.edtProfileDeskripsi);

        btn_save.setOnClickListener(v -> {
            saveUserData();
        });


    }

    private void saveUserData() {
        // Retrieve the values from EditText fields
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        String username = edtProfileUsername.getText().toString();
        String firstName = edtProfileFirstName.getText().toString();
        String lastName = edtProfileLastName.getText().toString();
        String phone = edtProfilePhone.getText().toString();
        String email = edtProfileEmail.getText().toString();
        String seniorHighSchool = edtProfileSMA.getText().toString();
        String seniorHighSchoolPeriod = edtProfileSMAPeriod.getText().toString();
        String university = edtProfileUniversity.getText().toString();
        String universityPeriod = edtProfileUniversityPeriod.getText().toString();
        String skills = edtProfileSkills.getText().toString();
        String selfDescription = edtProfileDeskripsi.getText().toString();

        if (email == "") {
            String emailAuth = user.getEmail();
            edtProfileEmail.setText(emailAuth);
        }

        String userId = database.getKey();

        database.child("Users").child(userId).setValue(new UserModel(username, firstName, lastName, phone,
                email, seniorHighSchool, seniorHighSchoolPeriod, university, universityPeriod,skills, selfDescription)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditProfileActivity.this, "Update Data Succesfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileActivity.this, "Update Data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
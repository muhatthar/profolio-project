package com.example.profolio.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.profolio.R;
import com.example.profolio.adapterfragment.AdapterKepanitiaan;
import com.example.profolio.adapterfragment.AdapterOrganisasi;
import com.example.profolio.adapterfragment.AdapterPrestasi;
import com.example.profolio.edit.EditOrganisasiActivity;
import com.example.profolio.edit.EditProfileActivity;
import com.example.profolio.login.LoginPageActivity;
import com.example.profolio.modelfragment.KepanitiaanModel;
import com.example.profolio.modelfragment.OrganisasiModel;
import com.example.profolio.modelfragment.PrestasiModel;
import com.example.profolio.modelfragment.UserModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mAuth;

    List<UserModel> userItems;

    ExtendedFloatingActionButton btn_edit_profile;
    TextView tvProfileUsername, tvProfileFirstName, tvProfileLastName, tvProfilePhone,
    tvProfileEmail, tvProfileSMA, tvProfileSMAPeriod, tvProfileUniversity, tvProfileUniversityPeriod,
            tvProfileSkills, tvProfileDeskripsi;

    TextView btnLogout;

    TextView jumlahOrganisasi, jumlahKepanitiaan, jumlahPrestasi;


    private String mParam1;
    private String mParam2;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilePageFragment newInstance(String param1, String param2) {
        ProfilePageFragment fragment = new ProfilePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_page, container, false);

        userItems = new ArrayList<>();

        tvProfileUsername = view.findViewById(R.id.tvProfileUsername);
        tvProfileFirstName = view.findViewById(R.id.tvProfileFirstName);
        tvProfileLastName = view.findViewById(R.id.tvProfileLastName);
        tvProfilePhone = view.findViewById(R.id.tvProfilePhone);
        tvProfileEmail = view.findViewById(R.id.tvProfileEmail);
        tvProfileSMA = view.findViewById(R.id.tvProfileSMA);
        tvProfileSMAPeriod = view.findViewById(R.id.tvProfileSMAPeriod);
        tvProfileUniversity = view.findViewById(R.id.tvProfileUniversity);
        tvProfileUniversityPeriod = view.findViewById(R.id.tvProfileUniversityPeriod);
        tvProfileSkills = view.findViewById(R.id.tvProfileSkills);
        tvProfileDeskripsi = view.findViewById(R.id.tvProfileDeskripsi);

        jumlahOrganisasi = view.findViewById(R.id.jmlhOrganisasi);
        jumlahKepanitiaan = view.findViewById(R.id.jmlhKepanitiaan);
        jumlahPrestasi = view.findViewById(R.id.jmlhPrestasi);

        btn_edit_profile = view.findViewById(R.id.btn_edit_profile);
        btnLogout = view.findViewById(R.id.btn_logout);

        String userKey = FirebaseAuth.getInstance().getCurrentUser().getUid();

        btnLogout.setOnClickListener(v -> {
//            logOut();
        });


        database.child("Users").child(userKey).child("Organisasi").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long childOrganisasi = snapshot.getChildrenCount();
                jumlahOrganisasi.setText(String.valueOf(childOrganisasi));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.child("Users").child(userKey).child("Kepanitiaan").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long chidKepanitiaan = snapshot.getChildrenCount();
                jumlahKepanitiaan.setText(String.valueOf(chidKepanitiaan));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.child("Users").child(userKey).child("Prestasi").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long childPrestasi = snapshot.getChildrenCount();
                jumlahPrestasi.setText(String.valueOf(childPrestasi));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        showData();

        btn_edit_profile.setOnClickListener(v -> {
            if (userItems.size() > 0) {
                UserModel user = userItems.get(0);
                Intent sendData = new Intent(getContext(), EditProfileActivity.class);
                sendData.putExtra("username", user.getUsername());
                sendData.putExtra("firstname", user.getFirstName());
                sendData.putExtra("lastname", user.getLastName());
                sendData.putExtra("phone", user.getPhone());
                sendData.putExtra("email", user.getEmail());
                sendData.putExtra("sma", user.getSeniorHighSchool());
                sendData.putExtra("smaperiod", user.getSeniorHighSchoolPeriod());
                sendData.putExtra("university", user.getUniversity());
                sendData.putExtra("universityperiod", user.getUniversityPeriod());
                sendData.putExtra("skills", user.getSkills());
                sendData.putExtra("deskripsi", user.getSelfDescription());
                sendData.putExtra("jumlahorganisasi", jumlahOrganisasi.getText().toString());
                sendData.putExtra("jumlahkepanitiaan", jumlahKepanitiaan.getText().toString());
                sendData.putExtra("jumlahprestasi", jumlahPrestasi.getText().toString());
                startActivity(sendData);
//            } else if (userItems.size() == 0){
//                Intent addUser = new Intent(getContext(), EditOrganisasiActivity.class);
//
            }
        });

        return view;
    }

//    public void logOut(){
//        mAuth.signOut();
//        Intent intent = new Intent(getContext(), LoginPageActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//    }

    private void showData() {
        database.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userItems = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    UserModel user = item.getValue(UserModel.class);
                    user.setKey(item.getKey());
                    userItems.add(user);
                }

                if (userItems.size() > 0) {
                    UserModel user = userItems.get(0);
                    tvProfileUsername.setText(user.getUsername());
                    tvProfileFirstName.setText(user.getFirstName());
                    tvProfileLastName.setText(user.getLastName());
                    tvProfilePhone.setText(user.getPhone());
                    tvProfileEmail.setText(user.getEmail());
                    tvProfileSMA.setText(user.getSeniorHighSchool());
                    tvProfileSMAPeriod.setText(user.getSeniorHighSchoolPeriod());
                    tvProfileUniversity.setText(user.getUniversity());
                    tvProfileUniversityPeriod.setText(user.getUniversityPeriod());
                    tvProfileSkills.setText(user.getSkills());
                    tvProfileDeskripsi.setText(user.getSelfDescription());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
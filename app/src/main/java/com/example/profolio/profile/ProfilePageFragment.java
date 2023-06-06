package com.example.profolio.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.profolio.R;
import com.example.profolio.adapterfragment.AdapterOrganisasi;
import com.example.profolio.modelfragment.OrganisasiModel;
import com.example.profolio.modelfragment.UserModel;
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

    List<UserModel> userItems;

    TextView tvProfileUsername, tvProfileFirstName, tvProfileLastName, tvProfilePhone,
    tvProfileEmail, tvProfileSMA, tvProfileSMAPeriod, tvProfileUniversity, tvProfileUniversityPeriod,
            tvProfileSkills, tvProfileDeskripsi;


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

        showData();

        return view;
    }

    private void showData() {
        database.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    userItems = new ArrayList<>();
                    for (DataSnapshot item : snapshot.getChildren()) {
                        UserModel user = item.getValue(UserModel.class);
                        user.setKey(item.getKey());
                        userItems.add(user);
                    }

                    // Display the retrieved data
                    if (!userItems.isEmpty()) {
                        UserModel userData = userItems.get(0);
                        tvProfileUsername.setText(userData.getUsername());
                        tvProfileFirstName.setText(userData.getFirstName());
                        tvProfileLastName.setText(userData.getLastName());
                        tvProfilePhone.setText(userData.getPhone());
                        tvProfileEmail.setText(userData.getEmail());
                        tvProfileSMA.setText(userData.getSeniorHighSchool());
                        tvProfileSMAPeriod.setText(userData.getSeniorHighSchoolPeriod());
                        tvProfileUniversity.setText(userData.getUniversity());
                        tvProfileUniversityPeriod.setText(userData.getUniversityPeriod());
                        tvProfileSkills.setText(userData.getSkills());
                        tvProfileDeskripsi.setText(userData.getSelfDescription());
                    }
                } else {
                    // Data does not exist or is empty
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
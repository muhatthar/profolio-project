package com.example.profolio.homepage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.profolio.AdapterFragment.AdapterOrganisasi;
import com.example.profolio.ModelFragment.OrganisasiModel;
import com.example.profolio.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganisasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisasiFragment extends Fragment {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<OrganisasiModel> organisasiItems;
    RecyclerView rvOrganisasi;
    AdapterOrganisasi adapterOrganisasi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrganisasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganisasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrganisasiFragment newInstance(String param1, String param2) {
        OrganisasiFragment fragment = new OrganisasiFragment();
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
        View organisasiView =  inflater.inflate(R.layout.fragment_organisasi, container, false);

        rvOrganisasi = organisasiView.findViewById(R.id.rvOrganisasi);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(getContext());
        rvOrganisasi.setLayoutManager(mLayout);
        rvOrganisasi.setHasFixedSize(true);
        rvOrganisasi.setItemAnimator(new DefaultItemAnimator());

        showData();
        return organisasiView;
    }

    private void showData() {
        database.child("Organisasi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                organisasiItems = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    OrganisasiModel organisasi = item.getValue(OrganisasiModel.class);
                    organisasi.setKey(item.getKey());
                    organisasiItems.add(organisasi);
                }
                adapterOrganisasi = new AdapterOrganisasi(organisasiItems, getContext());
                rvOrganisasi.setAdapter(adapterOrganisasi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
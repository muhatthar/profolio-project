package com.example.profolio.AdapterFragment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profolio.ModelFragment.OrganisasiModel;
import com.example.profolio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdapterOrganisasi extends RecyclerView.Adapter<AdapterOrganisasi.OrganisasiViewHolder> {
    private List<OrganisasiModel> organisasiItems;
    private Context context;

    public AdapterOrganisasi(List<OrganisasiModel> organisasiItems, Context context) {
        this.organisasiItems = organisasiItems;
        this.context = context;
    }
    @NonNull
    @Override
    public AdapterOrganisasi.OrganisasiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View organisasiView =inflater.inflate(R.layout.organisasi_cardview_item, parent, false);
        return new OrganisasiViewHolder(organisasiView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOrganisasi.OrganisasiViewHolder holder, int position) {
        //Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        OrganisasiModel organisasiData = organisasiItems.get(position);
        holder.tvTitleOrganisasi.setText(organisasiData.getNamaOrganisasi());
        holder.tvJabatanOrganisas.setText(organisasiData.getJabatanOrganisasi());
        holder.tvDeskripsiOrganisasi.setText(organisasiData.getDeskripsiOrganisasi());
        holder.tvPeriodeOrganisasi.setText(organisasiData.getTahunMulaiOrganisasi() + " - " + organisasiData.getTahunSelesaiOrganisasi());
        //holder.itemView.startAnimation(animation);

        holder.itemView.setAlpha(0f);
        holder.itemView.animate()
                .alpha(1f)
                .setDuration(300)
                .setStartDelay(300 * position)  // Add a delay to stagger the animations
                .start();

    }

    @Override
    public int getItemCount() {
        return organisasiItems.size();
    }

    public class OrganisasiViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleOrganisasi, tvJabatanOrganisas, tvPeriodeOrganisasi, tvDeskripsiOrganisasi;
        FloatingActionButton btn_delete_organisasi, btn_edit_organisasi;
        public OrganisasiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitleOrganisasi = itemView.findViewById(R.id.tvTitleOrganisasi);
            tvJabatanOrganisas = itemView.findViewById(R.id.tvJabatanOrganisas);
            tvPeriodeOrganisasi = itemView.findViewById(R.id.tvPeriodeOrganisasi);
            tvDeskripsiOrganisasi = itemView.findViewById(R.id.tvDeskripsiOrganisasi);

            btn_delete_organisasi = itemView.findViewById(R.id.btn_delete_organisasi);
            btn_edit_organisasi = itemView.findViewById(R.id.btn_edit_organisasi);
        }
    }
}

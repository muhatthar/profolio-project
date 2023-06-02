package com.example.profolio.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.profolio.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePageActivity extends AppCompatActivity {
    private int selectedtab = 1;
    private int defaultTextColor;
    private LinearLayout linearLayout;

    FloatingActionButton btn_add_organisasi, btn_add_kepanitiaan, btn_add_prestasi;
    ExtendedFloatingActionButton btn_add;
    TextView organisasiTv, kepanitiaanTv, prestasiTv;
    Boolean isAllFABVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout documentLayout = findViewById(R.id.documentLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);

        final LinearLayout homeNavbar = findViewById(R.id.homeNavbar);
        final LinearLayout documentNavbar = findViewById(R.id.documentNavbar);
        final LinearLayout profileNavbar = findViewById(R.id.profileNavbar);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView documentImage = findViewById(R.id.documentImage);
        final ImageView profileImage = findViewById(R.id.profileImage);

        final TextView homeText = findViewById(R.id.homeText);
        final TextView documentText = findViewById(R.id.documentText);
        final TextView profileText = findViewById(R.id.profileText);

        linearLayout = findViewById(R.id.navbarLayout);

        btn_add = findViewById(R.id.btn_add);
        btn_add_organisasi = findViewById(R.id.btn_add_organisasi);
        btn_add_kepanitiaan = findViewById(R.id.btn_add_kepanitiaan);
        btn_add_prestasi = findViewById(R.id.btn_add_prestasi);

        organisasiTv = findViewById(R.id.organisasiTv);
        kepanitiaanTv = findViewById(R.id.kepanitiaanTv);
        prestasiTv = findViewById(R.id.prestasiTv);

        defaultTextColor = homeText.getCurrentTextColor();
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                float startPosition = linearLayout.getY() + linearLayout.getHeight();
                float endPosition = linearLayout.getY();
                ValueAnimator animator = ValueAnimator.ofFloat(startPosition, endPosition);
                animator.setDuration(500);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        linearLayout.setY(animatedValue);
                    }
                });
                animator.start();
            }
        });

        btn_add_organisasi.setVisibility(View.GONE);
        btn_add_kepanitiaan.setVisibility(View.GONE);
        btn_add_prestasi.setVisibility(View.GONE);

        organisasiTv.setVisibility(View.GONE);
        kepanitiaanTv.setVisibility(View.GONE);
        prestasiTv.setVisibility(View.GONE);

        isAllFABVisible = false;

        btn_add.shrink();

        btn_add.setOnClickListener(v -> {
            if (!isAllFABVisible) {
                btn_add_organisasi.show();
                btn_add_kepanitiaan.show();
                btn_add_prestasi.show();

                organisasiTv.setVisibility(View.VISIBLE);
                kepanitiaanTv.setVisibility(View.VISIBLE);
                prestasiTv.setVisibility(View.VISIBLE);

                btn_add.extend();
                isAllFABVisible = true;
            } else {
                btn_add_organisasi.hide();
                btn_add_kepanitiaan.hide();
                btn_add_prestasi.hide();

                organisasiTv.setVisibility(View.GONE);
                kepanitiaanTv.setVisibility(View.GONE);
                prestasiTv.setVisibility(View.GONE);

                btn_add.shrink();
                isAllFABVisible = false;
            }
        });

        btn_add_organisasi.setOnClickListener(v -> {
            Toast.makeText(this, "Adding Organisasi", Toast.LENGTH_SHORT).show();
        });

        btn_add_kepanitiaan.setOnClickListener(v -> {
            Toast.makeText(this, "Adding Kepanitiaan", Toast.LENGTH_SHORT).show();
        });

        btn_add_prestasi.setOnClickListener(v -> {
            Toast.makeText(this, "Adding Prestasi", Toast.LENGTH_SHORT).show();
        });

        homeLayout.setOnClickListener(v -> {
            if (selectedtab != 1) {
//                documentText.setVisibility(View.GONE);
//                profileText.setVisibility(View.GONE);

                documentImage.setImageResource(R.drawable.ic_document);
                profileImage.setImageResource(R.drawable.ic_profile);

                documentNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                profileNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                documentText.setTextColor(defaultTextColor);
                profileText.setTextColor(defaultTextColor);

                homeText.setTextColor(getResources().getColor(R.color.colorSecondary));
                homeImage.setImageResource(R.drawable.ic_homepage_selected);
                homeNavbar.setBackgroundResource(R.drawable.navbar_selected);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                homeNavbar.startAnimation(scaleAnimation);

                selectedtab = 1;
            }
        });

        documentLayout.setOnClickListener(v -> {
            if (selectedtab != 2) {
//                homeText.setVisibility(View.GONE);
//                profileText.setVisibility(View.GONE);

                homeImage.setImageResource(R.drawable.ic_homepage);
                profileImage.setImageResource(R.drawable.ic_profile);

                homeNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                profileNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                homeText.setTextColor(defaultTextColor);
                profileText.setTextColor(defaultTextColor);

                documentText.setTextColor(getResources().getColor(R.color.colorSecondary));
                documentImage.setImageResource(R.drawable.ic_document_selected);
                documentNavbar.setBackgroundResource(R.drawable.navbar_selected);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                documentNavbar.startAnimation(scaleAnimation);

                selectedtab = 2;
            }
        });

        profileLayout.setOnClickListener(v -> {
            if (selectedtab != 3) {
//                homeText.setVisibility(View.GONE);
//                documentText.setVisibility(View.GONE);

                homeImage.setImageResource(R.drawable.ic_homepage);
                documentImage.setImageResource(R.drawable.ic_document);

                homeNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                documentNavbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                homeText.setTextColor(defaultTextColor);
                documentText.setTextColor(defaultTextColor);

                profileText.setTextColor(getResources().getColor(R.color.colorSecondary));
                profileImage.setImageResource(R.drawable.ic_profile_selected);
                profileNavbar.setBackgroundResource(R.drawable.navbar_selected);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                profileNavbar.startAnimation(scaleAnimation);

                selectedtab = 3;
            }
        });


    }
}
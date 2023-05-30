package com.example.profolio.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.profolio.R;

public class HomePageActivity extends AppCompatActivity {
    private int selectedtab = 1;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout documentLayout = findViewById(R.id.documentLayout);
        final LinearLayout profileLayout = findViewById(R.id.profileLayout);

        final ImageView homeImage = findViewById(R.id.homeImage);
        final ImageView documentImage = findViewById(R.id.documentImage);
        final ImageView profileImage = findViewById(R.id.profileImage);

        final TextView homeText = findViewById(R.id.homeText);
        final TextView documentText = findViewById(R.id.documentText);
        final TextView profileText = findViewById(R.id.profileText);

        linearLayout = findViewById(R.id.navbarLayout);

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

        homeLayout.setOnClickListener(v -> {
            if (selectedtab != 1) {
                documentText.setVisibility(View.GONE);
                profileText.setVisibility(View.GONE);

                documentImage.setImageResource(R.drawable.ic_document);
                profileImage.setImageResource(R.drawable.ic_profile);

                documentLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                homeText.setVisibility(View.VISIBLE);
                homeImage.setImageResource(R.drawable.ic_homepage_selected);
                homeLayout.setBackgroundResource(R.drawable.round_back);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                homeLayout.startAnimation(scaleAnimation);

                selectedtab = 1;
            }
        });

        documentLayout.setOnClickListener(v -> {
            if (selectedtab != 2) {
                homeText.setVisibility(View.GONE);
                profileText.setVisibility(View.GONE);

                homeImage.setImageResource(R.drawable.ic_homepage);
                profileImage.setImageResource(R.drawable.ic_profile);

                homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                documentText.setVisibility(View.VISIBLE);
                documentImage.setImageResource(R.drawable.ic_document_selected);
                documentLayout.setBackgroundResource(R.drawable.round_back);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                documentLayout.startAnimation(scaleAnimation);

                selectedtab = 2;
            }
        });

        profileLayout.setOnClickListener(v -> {
            if (selectedtab != 3) {
                homeText.setVisibility(View.GONE);
                documentText.setVisibility(View.GONE);

                homeImage.setImageResource(R.drawable.ic_homepage);
                documentImage.setImageResource(R.drawable.ic_document);

                homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                documentLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                profileText.setVisibility(View.VISIBLE);
                profileImage.setImageResource(R.drawable.ic_profile_selected);
                profileLayout.setBackgroundResource(R.drawable.round_back);

                ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                scaleAnimation.setDuration(200);
                scaleAnimation.setFillAfter(true);
                profileLayout.startAnimation(scaleAnimation);

                selectedtab = 3;
            }
        });


    }
}
package com.example.profolio.login;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.profolio.R;
import com.example.profolio.homepage.HomePageActivity;
import com.example.profolio.register.RegisterPageActivity;

public class LoginPageActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private TextView signUp;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signUp = findViewById(R.id.signUp);
        linearLayout = findViewById(R.id.loginPage);
        btnLogin = findViewById(R.id.btn_login);
        linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                float startPosition = linearLayout.getY() + linearLayout.getHeight();
                float endPosition = linearLayout.getY();
                ValueAnimator animator = ValueAnimator.ofFloat(startPosition, endPosition);
                animator.setDuration(750);
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

        signUp.setOnClickListener(v -> {
            Intent next = new Intent(this, RegisterPageActivity.class);
            startActivity(next);
        });

        btnLogin.setOnClickListener(v ->{
            Intent login = new Intent(this, HomePageActivity.class);
            startActivity(login);
        });
    }
}
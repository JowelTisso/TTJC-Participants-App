package com.example.android.participantsClient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AdminPanelHomeActivity extends AppCompatActivity {


    private Button btnAbout;
    private Button btnPolicy;
    private Button btnAdminPanel;
    private LinearLayout adminLayout;
    private LinearLayout policyLayout;
    private LinearLayout aboutLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel_home);

        btnAdminPanel = findViewById(R.id.btnAdminPanel);
        btnPolicy = findViewById(R.id.btnPrivacyPolicy);
        btnAbout = findViewById(R.id.btnAbout);
        adminLayout = findViewById(R.id.adminPanelContainer2);
        policyLayout = findViewById(R.id.privacyPolicyContainer);
        aboutLayout = findViewById(R.id.aboutContainer);


        btnAdminPanel.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, adminLayout, AdminPanel.class, 1);
                return false;
            }
        });

        btnPolicy.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, policyLayout, WebViewParticipant.class, 2);
                return false;
            }
        });

        btnAbout.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, aboutLayout, AboutActivity.class, 1);
                return false;
            }
        });

    }

    public void buttonShadowAnimationIntent(MotionEvent event, LinearLayout layout, Class activity, int counter) {
        //For button click animation
        //removing the elevation on click
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.setElevation(0);
            }
            //set the elevation value on release
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.setElevation(15);
            }
            if (counter == 1) {
                Intent i = new Intent(AdminPanelHomeActivity.this, activity);
                startActivity(i);
            } else if (counter == 2) {
                Intent i = new Intent(AdminPanelHomeActivity.this, activity);
                i.putExtra("url", "https://privacy-policy-forapp.netlify.com/privacy-policy/");
                i.putExtra("counter", 4);
                startActivity(i);
            }
        }
    }
}

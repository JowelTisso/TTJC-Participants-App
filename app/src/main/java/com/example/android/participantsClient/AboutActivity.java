package com.example.android.participantsClient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button btnMyLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnMyLink = findViewById(R.id.mylink);

        btnMyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutActivity.this, WebViewParticipant.class);
                String url ="https://2020.teamtanay.jobchallenge.dev/ttjc@JowelTisso/";
                i.putExtra("url", url);
                i.putExtra("counter",5);
                startActivity(i);
            }
        });
    }
}

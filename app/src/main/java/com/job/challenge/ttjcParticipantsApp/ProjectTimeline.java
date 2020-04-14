package com.job.challenge.ttjcParticipantsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ProjectTimeline extends AppCompatActivity {

    ViewPager2 viewPager2;
    ProjectTimelineAdapter adapter;
    ProgressBar progressBar;
    TextView title, category, language , totalDays, remainingDays, startDate, endDate;
    ExpandableTextView desc, feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_timeline);

        title = findViewById(R.id.titleSlotproject);
        category = findViewById(R.id.categorySlotproject);
        language = findViewById(R.id.languageSlotproject);
        feature = findViewById(R.id.featuresSlotproject);
        totalDays = findViewById(R.id.totalDaysSlotX);
        remainingDays = findViewById(R.id.remainingDaysSlot);
        startDate = findViewById(R.id.startDateSlot);
        endDate = findViewById(R.id.endDateSlot);
        progressBar = findViewById(R.id.progressBarProjectTimeline);
        desc = findViewById(R.id.descSlotproject);

        viewPager2 = findViewById(R.id.viewPagerTimeline);
        adapter = new ProjectTimelineAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        Intent receiveItem = getIntent();
        ProjectItem newItem = receiveItem.getParcelableExtra("projectObject");

        title.setText(newItem.getTitle());
        desc.setText(newItem.getDesc());
        category.setText(newItem.getCategory());
        language.setText(newItem.getLanguage());
        feature.setText(newItem.getFeatures());
        startDate.setText(newItem.getStartDate());
        endDate.setText(newItem.getEndDate());
        totalDays.setText(String.valueOf(newItem.getTotalDays()));
        remainingDays.setText(String.valueOf(newItem.getDaysGone()));

        progressBar.setMax(newItem.getTotalDays());
        progressBar.setProgress(newItem.getDaysGone());

    }

}

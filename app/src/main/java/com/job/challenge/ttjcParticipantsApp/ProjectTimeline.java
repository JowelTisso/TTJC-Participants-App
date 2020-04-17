package com.job.challenge.ttjcParticipantsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ProjectTimeline extends AppCompatActivity {

    ViewPager2 viewPager2;
    ProjectTimelineAdapter adapter;
    ProgressBar progressBar;
    TextView title, category, language , totalDays, remainingDays, startDate, endDate;
    ExpandableTextView desc, feature;
    Button mBtnEditProject;

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
        mBtnEditProject = findViewById(R.id.btnEditProject);

        viewPager2 = findViewById(R.id.viewPagerTimeline);
        adapter = new ProjectTimelineAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        Intent receiveItem = getIntent();
        final ProjectItem newItem = receiveItem.getParcelableExtra("projectObject");
//        String modifyingKey = newItem.getProjectKey();
        int remainingDaysValue = newItem.getTotalDays() - newItem.getDaysGone();

        title.setText(newItem.getTitle());
        desc.setText(newItem.getDesc());
        category.setText(newItem.getCategory());
        language.setText(newItem.getLanguage());
        feature.setText(newItem.getFeatures());
        startDate.setText(newItem.getStartDate());
        endDate.setText(newItem.getEndDate());
        totalDays.setText(String.valueOf(newItem.getTotalDays()));
        remainingDays.setText(String.valueOf(remainingDaysValue));

        progressBar.setMax(newItem.getTotalDays());
        progressBar.setProgress(newItem.getDaysGone());

        mBtnEditProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendProject = new Intent(ProjectTimeline.this,AddProject.class);
                sendProject.putExtra("projectToEdit",newItem);
                sendProject.putExtra("editMode",true);
                sendProject.putExtra("counter",2);
                startActivity(sendProject);
            }
        });
    }

}

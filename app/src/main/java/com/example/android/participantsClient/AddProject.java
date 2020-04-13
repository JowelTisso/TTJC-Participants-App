package com.example.android.participantsClient;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class AddProject extends AppCompatActivity {

    Spinner mStartDatePicker, mStartMonthPicker;
    Spinner mEndDatePicker, mEndMonthPicker;
    Button mBtnDone;
    EditText ProjectTitleEd, ProjectDescEd, ProjectCategoryEd, ProjectLanguageEd, ProjectFeaturesEd;
    String ProjectTitleS, ProjectDescS, ProjectCategoryS, ProjectLanguageS, ProjectStartDateS, ProjectStartMonthS, ProjectEndDateS, ProjectEndMonthS, ProjectFeatureS;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private String android_id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        //To get device id for unique database for saved project list
        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        mStartDatePicker = findViewById(R.id.startDatePicker);
        mStartMonthPicker = findViewById(R.id.startMonthPicker);
        mEndDatePicker = findViewById(R.id.endDatePicker);
        mEndMonthPicker = findViewById(R.id.endMonthPicker);
        mBtnDone = findViewById(R.id.btnDone);
        ProjectTitleEd = findViewById(R.id.ProjectTitle);
        ProjectDescEd = findViewById(R.id.ProjectDesc);
        ProjectCategoryEd = findViewById(R.id.ProjectCategory);
        ProjectLanguageEd = findViewById(R.id.ProjectLanguage);
        ProjectFeaturesEd = findViewById(R.id.ProjectFeatures);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //To create a child with unique device id for separating database for each user
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("projects").child(android_id);

        String[] dateList = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        final ArrayAdapter<String> startDateAdapter = getAdapter(dateList);

        String[] monthList = {"Month", "Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        final ArrayAdapter<String> startMonthAdapter = getAdapter(monthList);

        mStartDatePicker.setAdapter(startDateAdapter);
        mStartMonthPicker.setAdapter(startMonthAdapter);
        mEndDatePicker.setAdapter(startDateAdapter);
        mEndMonthPicker.setAdapter(startMonthAdapter);

        mStartDatePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProjectStartDateS = startDateAdapter.getItem(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mStartMonthPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProjectStartMonthS = startMonthAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mEndDatePicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProjectEndDateS = startDateAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mEndMonthPicker.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProjectEndMonthS = startMonthAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //To send data to the firebase database for the project list
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProjectTitleEd.length() != 0 && ProjectDescEd.length() != 0 && ProjectCategoryEd.length() != 0 && ProjectLanguageEd.length() != 0 && ProjectFeaturesEd.length() != 0 && !ProjectStartDateS.equals("00") && !ProjectStartMonthS.equals("Month") && !ProjectEndDateS.equals("00") && !ProjectEndMonthS.equals("Month")) {

                    ProjectTitleS = ProjectTitleEd.getText().toString();
                    ProjectDescS = ProjectDescEd.getText().toString();
                    ProjectCategoryS = ProjectCategoryEd.getText().toString();
                    ProjectLanguageS = ProjectLanguageEd.getText().toString();
                    ProjectFeatureS = ProjectFeaturesEd.getText().toString();

                    //checking if the total days set for the project is less than 21
                    //if true sending the data to database
                    int checkDays = getTotalDays();
                    if (checkDays < 22) {
                        DatabaseReference ref = mMessageDatabaseReference.push();
                        ProjectItem projectItem = new ProjectItem(ProjectTitleS, ProjectDescS, ProjectCategoryS, ProjectLanguageS, ProjectStartDateS + "-" + ProjectStartMonthS, ProjectEndDateS + "-" + ProjectEndMonthS, ProjectFeatureS, 20, ref.getKey());
                        ref.setValue(projectItem);
                        Toast.makeText(AddProject.this, "Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddProject.this, "Limit exceeds for total days/project i.e. 21days/project", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddProject.this, "One Of The Field Is Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public ArrayAdapter<String> getAdapter(String[] list) {
        return new ArrayAdapter<String>(AddProject.this, android.R.layout.simple_spinner_dropdown_item, list) {
            //To make the first item in the spinner not selectable
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            //To change the color of the first item in the spinner
            @Override
            public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView txtView = (TextView) view;
                if (position == 0) {
                    txtView.setTextColor(Color.GRAY);
                } else {
                    txtView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
    }

    //To calacute the total days set for the project according to the start date and end date
    private int getTotalDays() {
        final int daysInMarch = 31;
        final int daysInApril = 30;
        final int daysInMay = 31;
        final int daysInJune = 30;
        int startDigit = 0;
        int totalDays;

        int startD = Integer.parseInt(ProjectStartDateS);
        String startM = ProjectStartMonthS;
        int endD = Integer.parseInt(ProjectEndDateS);
        String endM = ProjectEndMonthS;

        if (startM.equals(endM)) {
            totalDays = endD - startD + 1;

        } else {

            switch (startM) {
                case "March":
                    startDigit = daysInMarch - startD + 1;
                    break;
                case "April":
                    startDigit = daysInApril - startD + 1;
                    break;
                case "May":
                    startDigit = daysInMay - startD + 1;
                    break;
                case "June":
                    startDigit = daysInJune - startD + 1;
                    break;
            }

            totalDays = startDigit + endD;

        }

        return totalDays;
    }

}

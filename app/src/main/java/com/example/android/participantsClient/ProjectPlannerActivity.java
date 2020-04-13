package com.example.android.participantsClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProjectPlannerActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private ProjectPagerAdapter mPagerAdapter;
    private Spinner mSpinnerMenu;
    private Spinner mSpinnerMenuRemaining;
    private TextView totalDaysView;
    private TextView totalMonthView;
    private TextView remainingDaysView;
    private TextView remainingMonthView;
    private TextView monthTitlex;
    private TextView remainingMonthTitle;
    private Calendar calendar = Calendar.getInstance();
    private Calendar marchCalendar;
    private Calendar aprilCalendar;
    private Calendar mayCalendar;
    private Calendar juneCalendar;
    private int monthInt;
    private String monthString;
    private int date;
    private int dayinmarch;
    private int dayinmonthapril;
    private int dayinmonthmay;
    private int dayinmonthjune;
    private int totalDaysGone;
    private int totalDays = 110;
    private int totalremainingMonthGlobal;
    private ProgressBar mProgressBar;
    Button btnNewProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_planner);

        totalDaysView = findViewById(R.id.totalDaysSlotx);
        totalMonthView = findViewById(R.id.totalMonthSlotx);
        monthTitlex = findViewById(R.id.monthTitlex);
        mSpinnerMenu = findViewById(R.id.spinnerMenu);
        mSpinnerMenuRemaining = findViewById(R.id.spinnerMenuRemaining);
        remainingDaysView = findViewById(R.id.remainingDaysSlot);
        remainingMonthView = findViewById(R.id.remainingMonthSlot);
        remainingMonthTitle = findViewById(R.id.monthTitle);
        viewPager2 = findViewById(R.id.viewPager);
        mProgressBar = findViewById(R.id.progressBar);
        btnNewProject = findViewById(R.id.btnMyProject);

        marchCalendar = new GregorianCalendar(2020, 2, 13);
        aprilCalendar = new GregorianCalendar(2020, 3, 1);
        mayCalendar = new GregorianCalendar(2020, 4, 1);
        juneCalendar = new GregorianCalendar(2020, 5, 1);
        date = calendar.get(Calendar.DAY_OF_MONTH);
        dayinmarch = marchCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 12;
        dayinmonthapril = aprilCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayinmonthmay = mayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayinmonthjune = juneCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        btnNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent project = new Intent(ProjectPlannerActivity.this, ProjectList.class);
                startActivity(project);
            }
        });
        //To set the progress bar according to the dates passed
        totalDaysGone = getTotalDaysGone();
        mProgressBar.setMax(110);
        mProgressBar.setProgress(totalDaysGone);

        monthInt = calendar.get(Calendar.MONTH);
        monthString = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());


        mPagerAdapter = new ProjectPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(mPagerAdapter);
        //For animation in transaction between fragments
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

        //To show the current month
        switch (calendar.get(Calendar.MONTH)) {
            case Calendar.MARCH:
                viewPager2.setCurrentItem(0);
                break;
            case Calendar.APRIL:
                viewPager2.setCurrentItem(1);
                break;
            case Calendar.MAY:
                viewPager2.setCurrentItem(2);
                break;
            case Calendar.JUNE:
                viewPager2.setCurrentItem(3);
                break;
        }

        String[] dayOrMonth = new String[]{"Day", "Month"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dayOrMonth);
        mSpinnerMenu.setAdapter(adapter);
        //To show total days/month accordingly
        mSpinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    totalDaysView.setText("110");
                    totalMonthView.setVisibility(View.GONE);
                    monthTitlex.setVisibility(View.GONE);
                } else {
                    totalMonthView.setText("3");
                    totalDaysView.setText("19");
                    totalMonthView.setVisibility(View.VISIBLE);
                    monthTitlex.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //To show remaining days/month accordingly
        mSpinnerMenuRemaining.setAdapter(adapter);
        mSpinnerMenuRemaining.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    remainingMonthView.setVisibility(View.GONE);
                    remainingMonthTitle.setVisibility(View.GONE);
                    int remainingDays = totalDays - totalDaysGone;
                    remainingDaysView.setText(String.valueOf(remainingDays));
                } else {
                    remainingMonthView.setVisibility(View.VISIBLE);
                    remainingMonthTitle.setVisibility(View.VISIBLE);
                    remainingMonthView.setText(String.valueOf(totalremainingMonthGlobal));
                    remainingDaysView.setText(String.valueOf(getRemainingDaysBymonth()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    //To show animation while transitioning between fragments
    public static class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }

    //To get total days for the progress bar
    private int getTotalDaysGone() {
        int Total;
        int marchcount = 0;
        int aprilcount = 0;
        int maycount = 0;
        int junecount = 0;
        int totalremainingmonth = 3;
        int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);

        if (Calendar.MARCH < currentMonth) {
            marchcount = dayinmarch;
            totalremainingmonth = totalremainingmonth - 1;
        } else if (Calendar.MARCH == currentMonth) {
            if (currentDate > 12) {
                marchcount = currentDate - 12;
                totalremainingmonth = 3;
            }else {
                Toast.makeText(this, "Start date out of range", Toast.LENGTH_SHORT).show();
            }
        }

        if (Calendar.APRIL < currentMonth) {
            aprilcount = dayinmonthapril;
            totalremainingmonth = totalremainingmonth - 2;
        } else if (Calendar.APRIL == currentMonth) {
            aprilcount = currentDate;
            totalremainingmonth = 2;
        }

        if (Calendar.MAY < currentMonth) {
            maycount = dayinmonthmay;
            totalremainingmonth = totalremainingmonth - 3;
        } else if (Calendar.MAY == currentMonth) {
            maycount = currentDate;
            totalremainingmonth = 1;
        }

        if (Calendar.JUNE < currentMonth) {
            junecount = dayinmonthjune;
        } else if (Calendar.JUNE == currentMonth) {
            junecount = currentDate;
            totalremainingmonth = 0;
        }
        totalremainingMonthGlobal = totalremainingmonth;
        Total = marchcount + aprilcount + maycount + junecount;

        return Total;
    }

    //To get remaining days to be use for calaculating with total days to get days gone result
    private int getRemainingDaysBymonth() {
        int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int daysLeftInMonth = 0;

        if (currentMonth == Calendar.MARCH) {
            if (currentDate > 12) {
                int date = currentDate - 12;
                daysLeftInMonth = dayinmarch - date;
            } else {
                Toast.makeText(this, "Start date out of range", Toast.LENGTH_SHORT).show();
            }
        } else if (currentMonth == Calendar.APRIL) {
            daysLeftInMonth = dayinmonthapril - currentDate;
        } else if (currentMonth == Calendar.MAY) {
            daysLeftInMonth = dayinmonthmay - currentDate;
        } else if (currentMonth == Calendar.JUNE) {
            daysLeftInMonth = dayinmonthjune - currentDate;
        }

        return daysLeftInMonth;
    }
}

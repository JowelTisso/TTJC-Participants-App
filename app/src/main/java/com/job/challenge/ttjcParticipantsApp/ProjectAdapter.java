package com.job.challenge.ttjcParticipantsApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProjectAdapter extends ArrayAdapter<ProjectItem> {

    private final Context mContext;
    private final int mResource;
    private ProjectItem projectItem;
    private final Calendar calendar = Calendar.getInstance();
    ArrayList<ProjectItem> listItem;

    public ProjectAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ProjectItem> projectItemList) {
        super(context, resource, projectItemList);
        this.mContext = context;
        this.mResource = resource;
        this.listItem = projectItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        projectItem = getItem(position);
        String mProjectTitleString = projectItem.getTitle();
        String mProjectDescString = projectItem.getDesc();
        String mProjectCategoryString = projectItem.getCategory();
        String mProjectLanguageString = projectItem.getLanguage();
        String mProjectStartDateString = projectItem.getStartDate();
        String mProjectEndDateString = projectItem.getEndDate();
        String mProjectFeaturesString = projectItem.getFeatures();
        int mTotalDays = getTotalDays();
        int mDaysGone = getDaysGone();
        projectItem.setTotalDays(mTotalDays);
        projectItem.setDaysGone(mDaysGone);

        LayoutInflater inflator = LayoutInflater.from(mContext);
        convertView = inflator.inflate(mResource, parent, false);

        TextView mProjectTitle = convertView.findViewById(R.id.projectTitle);
        TextView mProjectDesc = convertView.findViewById(R.id.projectDesc);
        TextView mProjectCategory = convertView.findViewById(R.id.projectCategory);
        TextView mProjectLanguage = convertView.findViewById(R.id.projectLanguage);
        TextView mProjectStartDate = convertView.findViewById(R.id.projectStartDate);
        TextView mProjectEndDate = convertView.findViewById(R.id.projectEndDate);
        TextView mProjectFeatures = convertView.findViewById(R.id.projectFeatures);
        ProgressBar progressBar = convertView.findViewById(R.id.progressBarProject);

        mProjectTitle.setText(mProjectTitleString);
        mProjectDesc.setText(mProjectDescString);
        mProjectCategory.setText(mProjectCategoryString);
        mProjectLanguage.setText(mProjectLanguageString);
        mProjectStartDate.setText(mProjectStartDateString);
        mProjectEndDate.setText(mProjectEndDateString);
        mProjectFeatures.setText(mProjectFeaturesString);

        progressBar.setMax(getTotalDays());
        progressBar.setProgress(getDaysGone());

        return convertView;

    }

    private final int daysInMarch = 31;
    private final int daysInApril = 30;
    private final int daysInMay = 31;
    private final int daysInJune = 30;
    private int startDigit = 0;

    private int getTotalDays() {

        int totalDays;

        String startDate = projectItem.getStartDate();
        String[] split = startDate.split("-");
        int startD = Integer.parseInt(split[0]);
        String startM = split[1];

        String endDate = projectItem.getEndDate();
        String[] splitend = endDate.split("-");
        int endD = Integer.parseInt(splitend[0]);
        String endM = splitend[1];


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

    private int getDaysGone() {
        int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        int daysGone;

        String startDate = projectItem.getStartDate();
        String[] split = startDate.split("-");
        int startD = Integer.parseInt(split[0]);
        String startM = split[1];

        String endDate = projectItem.getEndDate();
        String[] splitend = endDate.split("-");
        String endM = splitend[1];

        if (startM.equals(endM)) {
            daysGone = currentDate - startD;

        } else {

            switch (startM) {
                case "March":
                    startDigit = daysInMarch - startD;
                    break;
                case "April":
                    startDigit = daysInApril - startD;
                    break;
                case "May":
                    startDigit = daysInMay - startD;
                    break;
                case "June":
                    startDigit = daysInJune - startD;
                    break;
            }

            daysGone = startDigit + currentDate;

        }
        return daysGone;
    }
}

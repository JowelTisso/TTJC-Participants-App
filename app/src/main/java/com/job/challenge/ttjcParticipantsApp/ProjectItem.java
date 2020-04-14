package com.job.challenge.ttjcParticipantsApp;

import android.os.Parcel;
import android.os.Parcelable;

public class ProjectItem implements Parcelable {
    private String title;
    private String desc;
    private String category;
    private String language;
    private String startDate;
    private String endDate;
    private String features;
    private String projectKey;
    private int totalDays;
    private int daysGone;

    public ProjectItem() {
    }

    public ProjectItem(String title, String desc, String category, String language, String startDate, String endDate, String features, int totalDays, String projectKey) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.language = language;
        this.startDate = startDate;
        this.endDate = endDate;
        this.features = features;
        this.totalDays = totalDays;
        this.projectKey = projectKey;
    }

    private ProjectItem(Parcel in) {
        title = in.readString();
        desc = in.readString();
        category = in.readString();
        language = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        features = in.readString();
        projectKey = in.readString();
        totalDays = in.readInt();
        daysGone = in.readInt();
    }

    public static final Creator<ProjectItem> CREATOR = new Creator<ProjectItem>() {
        @Override
        public ProjectItem createFromParcel(Parcel in) {
            return new ProjectItem(in);
        }

        @Override
        public ProjectItem[] newArray(int size) {
            return new ProjectItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getFeatures() {
        return features;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getDaysGone() {
        return daysGone;
    }

    public void setDaysGone(int daysGone) {
        this.daysGone = daysGone;
    }

    public String getProjectKey() {
        return projectKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(category);
        dest.writeString(language);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(features);
        dest.writeString(projectKey);
        dest.writeInt(totalDays);
        dest.writeInt(daysGone);
    }
}

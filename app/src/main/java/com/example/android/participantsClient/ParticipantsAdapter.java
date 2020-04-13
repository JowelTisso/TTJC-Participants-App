package com.example.android.participantsClient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;

class ParticipantsAdapter extends ArrayAdapter<Participants> implements Filterable {

    private String title, desc,twitterPicUrl;
    private TextView titleView;
    private TextView descView;
    private CircleImageView profileImageView;
    private Context mContext;
    private int mResource;
    ArrayList<Participants> mParticipantsList;
    String TAG = "ParticipantsAdapter";

    public ParticipantsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Participants> participantsList) {
        super(context, resource, participantsList);
        this.mContext = context;
        this.mResource = resource;
        this.mParticipantsList = participantsList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Participants participants = getItem(position);
        title = getItem(position).getTitle();
        desc = getItem(position).getDescription();
        twitterPicUrl = getItem(position).getTwitterProfilePicUrl();

        LayoutInflater inflator = LayoutInflater.from(mContext);
        convertView = inflator.inflate(mResource,parent,false);

        titleView = convertView.findViewById(R.id.titleSlot);
        descView = convertView.findViewById(R.id.descriptionSlot);
        profileImageView = convertView.findViewById(R.id.twitterProfilePic);

        titleView.setText(title);
        descView.setText(desc);

        boolean isPhoto = participants.getTwitterProfilePicUrl() != null;
        if (isPhoto)
        {
            Glide.with(profileImageView.getContext()).load(twitterPicUrl).into(profileImageView);
        }

        return convertView;
    }
}

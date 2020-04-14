package com.job.challenge.ttjcParticipantsApp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import androidx.annotation.NonNull;

class ChatAdapter extends ArrayAdapter<Chats> {

    private ImageView photoImageView;
    private TextView messageTextView;
    private TextView authorTextView;
    private ImageView profilePicView;

    public ChatAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Chats> objects) {
        super(context, resource, objects);
    }


    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chat_model, parent, false);
        }

        photoImageView = convertView.findViewById(R.id.photoImageView);
        messageTextView = convertView.findViewById(R.id.messageTextView);
        authorTextView = convertView.findViewById(R.id.nameTextView);
        profilePicView = convertView.findViewById(R.id.profileImage);

        Chats message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            //To load image using glide
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }
        authorTextView.setText(message.getName());
        boolean isProfilePhoto = message.getmProfilePicUrl() != null;
        if (isProfilePhoto){
            Glide.with(profilePicView.getContext())
                    .load(message.getmProfilePicUrl())
                    .into(profilePicView);
        }
        return convertView;
    }

}

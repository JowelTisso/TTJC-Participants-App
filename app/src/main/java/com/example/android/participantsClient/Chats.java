package com.example.android.participantsClient;

class Chats {

    private String text;
    private String name;
    private String photoUrl;
    private String mProfilePicUrl;
    private String mRandomId;

    public Chats() {
    }

    public Chats(String text, String name, String photoUrl, String profilePicUrl, String randomId) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.mProfilePicUrl = profilePicUrl;
        this.mRandomId = randomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getmProfilePicUrl() {
        return mProfilePicUrl;
    }

    public String getmRandomId() {
        return mRandomId;
    }

}

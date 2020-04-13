package com.example.android.participantsClient;

class Participants {
    private String title;
    private String description;
    String participantId;
    private String pageLink;
    private String twitterProfilePicUrl;
    private int count;

    public Participants() {
    }

    public Participants(int count) {
        this.count = count;
    }

    public Participants(String title, String desc, String pageLink, String twitterPicUrl, String id) {
        this.title = title;
        this.description = desc;
        this.pageLink = pageLink;
        this.twitterProfilePicUrl = twitterPicUrl;
        this.participantId = id;
    }


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getParticipantId() { return participantId; }
    public String getPageLink() { return pageLink; }
    public int getCount() {
        return count;
    }
    public String getTwitterProfilePicUrl() {
        return twitterProfilePicUrl;
    }
}

package com.job.challenge.ttjcParticipantsApp;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private String jsonResponse;
    private ProgressBar progressBar;
    private TextView participantsCount;
    private final ArrayList<Participants> reportItemsList = new ArrayList<>();
    private Button btnParticipantAnalysis;
    private Button btnParticipantsttjc;
    private Button btnLinkTree;
    private Button btnDisussion;
    private Button btnProjectPlanner;
    private Button mAdminPanel;
    LinearLayout countLayout, analysisLayout, participantsLayout, treeLayout, discussionLayout, timelineLayout, adminLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_circularX);
        btnParticipantAnalysis = findViewById(R.id.btnParticipantAnalysis);
        participantsCount = findViewById(R.id.countParticipants);
        btnParticipantsttjc = findViewById(R.id.btnParticipants);
        btnLinkTree = findViewById(R.id.btnLinkTree);
        btnDisussion = findViewById(R.id.btnDiscussion);
        btnProjectPlanner = findViewById(R.id.btnProjectPlanner);
        mAdminPanel = findViewById(R.id.adminPanel);
        countLayout = findViewById(R.id.countContainer);
        analysisLayout = findViewById(R.id.analysisContainer);
        participantsLayout = findViewById(R.id.participantsContainer);
        treeLayout = findViewById(R.id.LinkTreeContainer);
        discussionLayout = findViewById(R.id.chatContainer);
        timelineLayout = findViewById(R.id.timelineContainer);
        adminLayout = findViewById(R.id.adminPanelContainer);

        //To check if internet connection is available or not
        if (isConnectingToInternet(this)) {
            //To fetch the number total participants in the challenge
            startfetchingdata();
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        btnParticipantAnalysis.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, analysisLayout, ParticipantsDetailActivity.class, 1);
                return false;
            }
        });

        btnParticipantsttjc.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, participantsLayout, WebViewParticipant.class, 3);
                return false;
            }
        });

        btnLinkTree.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, treeLayout, WebViewParticipant.class, 2);
                return false;
            }
        });

        btnDisussion.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, discussionLayout, DiscussionActivity.class, 1);
                return false;
            }
        });

        participantsCount.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, countLayout, null, 4);
                return false;
            }
        });

        btnProjectPlanner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, timelineLayout, ProjectPlannerActivity.class, 1);
                return false;
            }
        });

        mAdminPanel.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                buttonShadowAnimationIntent(event, adminLayout, AdminPanelHomeActivity.class, 1);
                return false;
            }
        });

    }

    public void buttonShadowAnimationIntent(MotionEvent event, LinearLayout layout, Class activity, int counter) {
        //For button click animation
        //removing the elevation on click
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.setElevation(0);
            }
            //set the elevation value on release
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                layout.setElevation(15);
            }
            if (counter == 1) {
                Intent i = new Intent(MainActivity.this, activity);
                startActivity(i);
            } else if (counter == 2) {
                Intent i = new Intent(MainActivity.this, activity);
                i.putExtra("url", "https://linktr.ee/ttjclearningpath");
                i.putExtra("counter", 2);
                startActivity(i);
            } else if (counter == 3) {
                Intent i = new Intent(MainActivity.this, activity);
                i.putExtra("url", "https://2020.teamtanay.jobchallenge.dev/participants");
                i.putExtra("counter", 1);
                startActivity(i);
            }else if (counter == 4){
                if (isConnectingToInternet(MainActivity.this)) {
                    progressBar.setVisibility(View.VISIBLE);
                    startfetchingdata();
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }
    }
    //To make an http request to get the total number of participants from the url
    private void startfetchingdata() {

        String totalUrl = "https://2020.teamtanay.jobchallenge.dev/page-data/participants/page-data.json";

        OkHttpClientHelper.get(totalUrl, "null", "null", new OkHttpClientHelper.HttpCallback() {
            @Override
            public void onFailure(Response response, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Response response) {

                if (response.isSuccessful()) {

                    try {
                        jsonResponse = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONObject rootObject;
                    try {
                        rootObject = new JSONObject(jsonResponse);
                        JSONObject resultObject = rootObject.getJSONObject("result");
                        JSONObject dataObject = resultObject.getJSONObject("data");
                        JSONObject allMarkdownRemarkObject = dataObject.getJSONObject("allMarkdownRemark");
                        JSONArray edgesArray = allMarkdownRemarkObject.getJSONArray("edges");
                        //Storing the total number of participants in a variable and storing it in a global arrayList
                        int Count = edgesArray.length();
                        Participants participants = new Participants(Count);
                        reportItemsList.add(participants);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            progressBar.setVisibility(View.GONE);
                            if (!reportItemsList.isEmpty()) {
                                //accessing the arrayLsit and displaying the count on a text view
                                Participants part = reportItemsList.get(0);
                                participantsCount.setText(String.valueOf(part.getCount()));

                            } else {
                                Toast.makeText(MainActivity.this, "empty", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    //Method for checking if the internet connection is active or not
    private static boolean isConnectingToInternet(Context mContext) {
        if (mContext == null) return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            } else {
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                for (NetworkInfo tempNetworkInfo : networkInfos) {
                    if (tempNetworkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}

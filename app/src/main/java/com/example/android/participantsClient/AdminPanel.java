package com.example.android.participantsClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Response;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class AdminPanel extends AppCompatActivity {

    private String participantId = "4k45hv3rm4";
    private String jsonResponse;
    private ProgressBar progressBar, progressBarList;
    private String profileUrl;
    private final ArrayList<Participants> reportItemsList = new ArrayList<>();
    private TextView listCount;
    private EditText inputforparticipantsId;
    private EditText inputforloopCount;
    private int inputloopCount = 1;
    private String twitterPicUrl;
    private String twitterIdName2;
    private String twitterIdName3 = "";
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ParticipantsAdapter mAdapter;
    private ImagePopupX imagePopup;
    private Spinner mSpinner;
    private EditText mSearchBar;
    private final String twitterAccessToken ="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        progressBar = findViewById(R.id.progress_circularZ);
        progressBarList = findViewById(R.id.progressBarAdmin);
        ListView listView = findViewById(R.id.list_view);
        Button btnAdd = findViewById(R.id.btnAdd);
        listCount = findViewById(R.id.listCount);
        Button btnforloop = findViewById(R.id.btnLoop);
        inputforparticipantsId = findViewById(R.id.inputParticipantId);
        inputforloopCount = findViewById(R.id.loopCountText);
        imagePopup = new ImagePopupX(this);
        mSpinner = findViewById(R.id.spinner);
        mSearchBar = findViewById(R.id.searchBar);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("participants");
        mAdapter = new ParticipantsAdapter(AdminPanel.this, R.layout.participants_list_model, reportItemsList);
        listView.setAdapter(mAdapter);

        //This button is for getting the loop count and initiating the loop for the list of participants, to add data to the list in a large number.
        btnforloop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twitterAccessToken.length() != 0) {
                    if (inputforloopCount != null && inputforloopCount.length() != 0) {
                        inputloopCount = Integer.parseInt(inputforloopCount.getText().toString());
                        new loopingTasks().execute();
                    } else {
                        Toast.makeText(AdminPanel.this, " loop count Field Empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminPanel.this, " Access Token Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //This code is meant to get participant id and add one partipants at a time to the list.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twitterAccessToken.length() != 0) {
                    if (inputforparticipantsId.length() != 0 && twitterAccessToken.length() != 0) {
                        participantId = inputforparticipantsId.getText().toString();
                        new loopingTasks().execute();
                    } else {
                        Toast.makeText(AdminPanel.this, "Participant  id Field Empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AdminPanel.this, " Access Token Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //To view the Profile picture in a large size and get the direct link to the official participants profile.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String url = mAdapter.getItem(position).getTwitterProfilePicUrl();
                imagePopup.initiatePopupWithGlide(url, R.layout.popup_x, R.id.popupX, R.id.imageView);
                imagePopup.viewPopup(R.id.closeBtn);
                Button btn = imagePopup.layout.findViewById(R.id.btnttjcPage);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(AdminPanel.this, WebViewParticipant.class);
                        String url = mAdapter.getItem(position).getPageLink();
                        i.putExtra("url", url);
                        startActivity(i);
                    }
                });

            }
        });

        //To delete an item from the list
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String randomId = mAdapter.getItem(position).getParticipantId();
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AdminPanel.this);
                alertBuilder.setTitle("Delete?");
                alertBuilder.setMessage("Are You Sure You Wanna Delete?");
                alertBuilder.setNegativeButton("Cancel", null);
                alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mMessageDatabaseReference.child(randomId).removeValue();

                    }
                });
                alertBuilder.show();
                return true;
            }
        });

        attachDatabaseReadListener(null, 1);

        String[] items = new String[]{"Select Skill", "All", "React", "ReactJs", "ReactNative", "JavaScript", "HTML", "CSS", "Android", "Java", "Flutter", "Bootstrap", "NodeJs",};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) {

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

        mSpinner.setAdapter(adapter);

        //To perform an action when an item is selected from the spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1) {
                    mAdapter.clear();
                    mChildEventListener = null;
                    attachDatabaseReadListener(null, 1);
                } else if (position != 0) {
                    mAdapter.clear();
                    mChildEventListener = null;
                    //To get the selected item from the spinner and send it to database listener for filtering the list
                    attachDatabaseReadListener(mSpinner.getSelectedItem().toString().toLowerCase(), 3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSearchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    mAdapter.clear();
                    mChildEventListener = null;
                    //To get the text from the seacrh bar and send it to database listener for filtering the list
                    attachDatabaseReadListener(mSearchBar.getText().toString().toLowerCase(), 2);
                    return true;
                }
                return false;
            }
        });

    }

    //To perform http request in the background thread and get json response
    class loopingTasks extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(inputloopCount);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < inputloopCount; i++) {

                publishProgress(i);
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                //method call to initiate the http request
                getParticipantsList(countDownLatch);
                try {
                    //To make the loop wait for the http request to get result and send an update to the database(firebase)
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

            listCount.setText(String.valueOf(reportItemsList.size()));
            inputloopCount = 1;
        }
    }

    private void getParticipantsList(final CountDownLatch countDownLatch) {
        //url format for the json link
        String url = "https://2020.teamtanay.jobchallenge.dev/page-data/ttjc@" + participantId + "/page-data.json";
        final String pageLink = "https://2020.teamtanay.jobchallenge.dev/ttjc@" + participantId;

        OkHttpClientHelper.get(url, "null", "null", new OkHttpClientHelper.HttpCallback() {
            @Override
            public void onFailure(Response response, IOException e) {
                e.printStackTrace();
                countDownLatch.countDown();
            }

            @Override
            public void onSuccess(Response response) {

                if (response.isSuccessful()) {

                    try {
                        //saving the json response to a string
                        jsonResponse = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        //extracting the json response
                        JSONObject rootObject = new JSONObject(jsonResponse);
                        JSONObject resultObject = rootObject.getJSONObject("result");
                        JSONObject dataObject = resultObject.getJSONObject("data");
                        JSONObject markdownRemarkObject = dataObject.getJSONObject("markdownRemark");
                        JSONObject frontmatterObject = markdownRemarkObject.getJSONObject("frontmatter");
                        String title = frontmatterObject.getString("title");
                        String desc = frontmatterObject.getString("description");
                        JSONObject pageContextObject = resultObject.getJSONObject("pageContext");
                        String htmlBody = markdownRemarkObject.getString("html");
                        //To get the twitter id from the json response v
                        if (htmlBody.contains("twitter.com")) {
                            try {
                                int startIndex = htmlBody.indexOf("twitter.com/");
                                int endIndex = htmlBody.indexOf(">Twitter");
                                String twitterId = htmlBody.substring(startIndex, endIndex);
                                String[] twitterIdName = twitterId.split("/");
                                twitterIdName2 = twitterIdName[1];
                                twitterIdName3 = twitterIdName2.replace("\"", "");
                            } catch (StringIndexOutOfBoundsException s) {
                                twitterIdName3 = "null";
                            }
                        } else {
                            twitterIdName3 = "null";
                        }
                        //To get the twitter id from the json response ^

                        if (!twitterIdName3.contains("null")) {
                            //To send twitter id and to make an http request and get the twitter profile picture
                            //Also sending the fetched data to merged it together along with the profile picture in an object
                            getTwitterProfileUrl(twitterIdName3, countDownLatch, title, desc, pageLink);

                        } else {
                            //If twitter id is not found then send default twitter id
                            getTwitterProfileUrl("twitterdev", countDownLatch, title, desc, pageLink);
                        }
                        //To get the next participant id for countinuing the loop and fetching data if participant is available
                        if (pageContextObject.getJSONObject("previous") != null) {
                            JSONObject previousObject = pageContextObject.getJSONObject("previous");
                            JSONObject fieldsObject = previousObject.getJSONObject("fields");
                            String participantIdMix = fieldsObject.getString("slug");
                            String[] split = participantIdMix.split("@");
                            String participantId2 = split[1];
                            participantId = participantId2;

                        } else {
                            participantId = null;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    //Method to get twitter profile picture
    private void getTwitterProfileUrl(String twitterIdX, final CountDownLatch countDownLatch, final String title, final String desc, final String pageLink) {
        //url and data to make twitter api call
        String twitterUrl = " https://api.twitter.com/1.1/users/show.json?screen_name=" + twitterIdX;
        String headerName = "Authorization";
        String headerValue = "Bearer " + twitterAccessToken;
        final String[] s2 = new String[1];
        OkHttpClientHelper.get(twitterUrl, headerName, headerValue, new OkHttpClientHelper.HttpCallback() {
            @Override
            public void onFailure(Response response, IOException e) {
                e.printStackTrace();
                Log.i("AdminPanel", "TEST : response is Failed");
                Toast.makeText(AdminPanel.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Response response) {
                if (response.isSuccessful()) {
                    try {
                        jsonResponse = response.body().string();
                        JSONObject rootObject = new JSONObject(jsonResponse);
                        profileUrl = rootObject.getString("profile_image_url_https");
                        String[] s = profileUrl.split("_normal");
                        s2[0] = s[0] + s[1];
                        twitterPicUrl = s2[0];
                        //To generate an unique id
                        DatabaseReference participantsReference = mMessageDatabaseReference.push();
                        Participants twitterUrlObject = new Participants(title, desc, pageLink, twitterPicUrl, participantsReference.getKey());
                        //To send data as an object to the firebase
                        participantsReference.setValue(twitterUrlObject);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    //This code block is called when "twitter.com" string is present in the json response but the twitter id is not in a proper format
                    // to be fetched
                    DatabaseReference participantsReference = mMessageDatabaseReference.push();
                    Participants twitterUrlObject = new Participants(title, desc, pageLink, twitterPicUrl, participantsReference.getKey());
                    participantsReference.setValue(twitterUrlObject);

                }
                countDownLatch.countDown();
            }
        });

    }


    private void attachDatabaseReadListener(final String searchTerm, final int counter) {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    progressBarList.setVisibility(View.GONE);
                    final Participants participants = dataSnapshot.getValue(Participants.class);

                    if (counter == 1) {
                        mAdapter.add(participants);
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    } else if (counter == 2) {
                        if (participants.getTitle().toLowerCase().contains(searchTerm) || participants.getDescription().toLowerCase().contains(searchTerm)) {
                            //To filter the participants list according to the search term and add to adapter to display
                            mAdapter.addAll(participants);
                        }
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    } else if (counter == 3) {
                        //To filter the participants list according to the item selected in the spinner and add to adapter to display
                        if (participants.getDescription().toLowerCase().contains(searchTerm)) {

                            mAdapter.addAll(participants);
                        }
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    }
                }

                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    final Participants participants = dataSnapshot.getValue(Participants.class);
                    mAdapter.remove(participants);
                    listCount.setText(String.valueOf(reportItemsList.size()));
                    //To update the list by restarting the activity(workaround)
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                }

                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        }

    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mMessageDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        detachDatabaseReadListener();
        mAdapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachDatabaseReadListener(null, 1);
    }
}



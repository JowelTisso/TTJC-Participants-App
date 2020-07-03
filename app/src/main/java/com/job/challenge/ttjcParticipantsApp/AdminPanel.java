package com.job.challenge.ttjcParticipantsApp;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class AdminPanel extends AppCompatActivity {

    private final String twitterAccessToken = "";
    private String jsonResponse;
    private ProgressBar progressBar, progressBarList;
    private String profileUrl;
    private final ArrayList<Participants> reportItemsList = new ArrayList<>();
    boolean check = false;
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
    //Initial id for starting a loop
    private String participantId = "2KAbhishek";
    int buttonChecker = 0;
    String TAG = "TEST";
    private ArrayList<String> participantIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        progressBar = findViewById(R.id.progress_horizontalZ);
        progressBarList = findViewById(R.id.progressBarAdmin);
        ListView listView = findViewById(R.id.list_view);
        Button btnAdd = findViewById(R.id.btnAdd);
        listCount = findViewById(R.id.listCount);
        Button btnforloop = findViewById(R.id.btnLoop);
        Button btnforstop = findViewById(R.id.btnStop);
        inputforparticipantsId = findViewById(R.id.inputParticipantId);
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
                    check = true;
                    buttonChecker = 1;
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setIndeterminate(false);
                    new loopingTasks().execute();

                } else {
                    Toast.makeText(AdminPanel.this, " Access Token Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnforstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
            }
        });

        //This code is meant to get participant id and add one partipants at a time to the list.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (twitterAccessToken.length() != 0) {
                    if (inputforparticipantsId.length() != 0 && twitterAccessToken.length() != 0) {
                        buttonChecker = 2;
                        check = true;

                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setIndeterminate(true);
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
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());

                    }
                });
                alertBuilder.show();
                return true;
            }
        });

        //To check internet connection and if available attach database
        if (MainActivity.isConnectingToInternet(this)) {
            attachDatabaseReadListener(null, 1);
        } else {
            progressBarList.setVisibility(View.GONE);
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


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

    private void getParticipantIdList(final CountDownLatch countDownLatch) {
        //Url for the overall participants data from where ids will be collected
        String Url = "https://2020.teamtanay.jobchallenge.dev/page-data/participants/page-data.json";


        OkHttpClientHelper.get(Url, "null", "null", new OkHttpClientHelper.HttpCallback() {
            @Override
            public void onFailure(Response response, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Response response) {

                if (response.isSuccessful()) {

                    try {
                        jsonResponse = response.body().string();

                    } catch (IOException | NullPointerException e) {

                        e.printStackTrace();
                    }
                    JSONObject rootObject;
                    try {
                        if (jsonResponse != null) {

                            rootObject = new JSONObject(jsonResponse);
                            JSONObject resultObject = rootObject.getJSONObject("result");
                            JSONObject dataObject = resultObject.getJSONObject("data");
                            JSONObject allMarkdownRemarkObject = dataObject.getJSONObject("allMarkdownRemark");
                            JSONArray edgesArray = allMarkdownRemarkObject.getJSONArray("edges");
                            //Storing the total number of participants in a variable and storing it in a global arrayList

                            inputloopCount = edgesArray.length();

                            for (int i = 0; i < edgesArray.length(); i++) {
                                JSONObject edgeRootObject = edgesArray.getJSONObject(i);
                                JSONObject nodeObject = edgeRootObject.getJSONObject("node");
                                JSONObject fieldObject = nodeObject.getJSONObject("fields");
                                String slug = fieldObject.getString("slug");
                                String[] split = slug.split("@");
                                participantIdList.add(split[1]);
                            }
                        }

                        countDownLatch.countDown();
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }


                }
            }
        });

    }

    private void getParticipantsList(final CountDownLatch countDownLatch, String participantIdx) {
        //url format for the json link
        String url = "https://2020.teamtanay.jobchallenge.dev/page-data/ttjc@" + participantIdx + "/page-data.json";
        final String pageLink = "https://2020.teamtanay.jobchallenge.dev/ttjc@" + participantIdx;


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
                                //Sample link in the json response
                                //<a href=\"https://twitter.com/joweltisso\">Twitter</a>
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

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    //To perform http request in the background thread and get json response
    class loopingTasks extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(388);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                //To fetch the ids of the participants and store it in array list
                if (buttonChecker == 1) { //To check if button for loop has been pressed or add button has been pressed

                    getParticipantIdList(countDownLatch);
                    //To wait until the fetching of ids are finished

                    countDownLatch.await();

                } else if (buttonChecker == 2) {

                    inputloopCount = 1;
                }
                //To loop through the id list and fetch the participant details
                for (int i = 0; i < inputloopCount; i++) {
                    //Force Sleeping the thread to be able to cancel the loop
                    Thread.sleep(500);
                    if (check) {
                        String id = "joweltisso";
                        if (buttonChecker == 1) {
                            id = participantIdList.get(i);
                        } else if (buttonChecker == 2) {
                            id = participantId;
                        }
                        //method call to initiate the http request to get the participant details
                        getParticipantsList(countDownLatch, id);
                        //To make the loop wait for the http request to get result and send an update to the database(firebase)
                        publishProgress(i);
                    } else {

                    }


                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    //Method to get twitter profile picture
    private void getTwitterProfileUrl(String twitterIdX, final CountDownLatch countDownLatch, final String title, final String desc, final String pageLink) {
        //url and data to make twitter api call
        String twitterUrl = " https://api.twitter.com/1.1/users/show.json?screen_name=" + twitterIdX;
        String headerName = "Authorization";
        String headerValue = "Bearer " + twitterAccessToken;
        final String[] combinedUrl = new String[1];
        OkHttpClientHelper.get(twitterUrl, headerName, headerValue, new OkHttpClientHelper.HttpCallback() {
            @Override
            public void onFailure(Response response, IOException e) {
                e.printStackTrace();
                Toast.makeText(AdminPanel.this, "Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(Response response) {
                if (response.isSuccessful()) {
                    try {
                        jsonResponse = response.body().string();
                        JSONObject rootObject = new JSONObject(jsonResponse);
                        profileUrl = rootObject.getString("profile_image_url_https");
                        String[] separatedUrl = profileUrl.split("_normal");
                        // example of url "profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/1214620727774629888\/TalschUb_normal.jpg",
                        combinedUrl[0] = separatedUrl[0] + separatedUrl[1];
                        twitterPicUrl = combinedUrl[0];
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
                    //This line of code is added for inappropriate twitter id e.g: Aravind20539684?s=03 ( ?s=03 <- is inappropriate)
                    //So to fetch default twitter profile picture
                    twitterPicUrl = "https://pbs.twimg.com/profile_images/880136122604507136/xHrnqf1T.jpg";
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




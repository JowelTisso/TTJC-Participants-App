package com.job.challenge.ttjcParticipantsApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ParticipantsDetailActivity extends AppCompatActivity {

    private final ArrayList<Participants> reportItemsList = new ArrayList<>();
    private ListView listView;
    private TextView listCount;
    int loopCount;
    private ParticipantsAdapter mAdapter;
    private EditText mSearchBar;
    private Spinner mSpinner;
    private ImagePopupX imagePopup;
    private Button btnNotePopup;
    TextView mNotetext;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paraticipants_detail);
        listView = findViewById(R.id.list_view);
        listCount = findViewById(R.id.listCount);
        mSearchBar = findViewById(R.id.searchBar);
        mSpinner = findViewById(R.id.spinner);
        imagePopup = new ImagePopupX(this);
        btnNotePopup = findViewById(R.id.btnNotePopup);
        mNotetext = findViewById(R.id.notetext);
        progressBar = findViewById(R.id.progressBarP);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("participants");
        mAdapter = new ParticipantsAdapter(ParticipantsDetailActivity.this, R.layout.participants_list_model, reportItemsList);
        listView.setAdapter(mAdapter);

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
                        Intent i = new Intent(ParticipantsDetailActivity.this, WebViewParticipant.class);
                        String url = mAdapter.getItem(position).getPageLink();
                        i.putExtra("url", url);
                        i.putExtra("counter",3);
                        startActivity(i);
                    }
                });

            }
        });
        Intent i = getIntent();
        loopCount = i.getIntExtra("count", 0);

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

        attachDatabaseReadListener(null, 1);

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

        btnNotePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v);
            }
        });

    }

    private void attachDatabaseReadListener(final String searchTerm, final int counter) {

        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    progressBar.setVisibility(View.GONE);
                    final Participants friendlyMessage = dataSnapshot.getValue(Participants.class);

                    if (counter == 1) {
                        mAdapter.add(friendlyMessage);
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    } else if (counter == 2) {
                        if (friendlyMessage.getTitle().toLowerCase().contains(searchTerm) || friendlyMessage.getDescription().toLowerCase().contains(searchTerm)) {
                            //To filter the participants list according to the search term and add to adapter to display
                            mAdapter.addAll(friendlyMessage);
                        }
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    } else if (counter == 3) {
                        if (friendlyMessage.getDescription().toLowerCase().contains(searchTerm)) {
                            //To filter the participants list according to the item selected in the spinner and add to adapter to display
                            mAdapter.addAll(friendlyMessage);
                        }
                        listCount.setText(String.valueOf(reportItemsList.size()));
                    }

                }

                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    final Participants friendlyMessage = dataSnapshot.getValue(Participants.class);
                    mAdapter.remove(friendlyMessage);
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

    private void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        boolean focusable = true; // lets taps outside the popup and dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, (int) (width * .9), (int) (height * .6), focusable);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        //Calling method from class ImagePopupX to dim the background of the popup window
        ImagePopupX.dimBehind(popupWindow);
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

package com.example.android.participantsClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProjectList extends AppCompatActivity {

    FloatingActionButton addProjectBtn;
    ListView mProjectListView;
    ArrayList<ProjectItem> projectList;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDatabaseReference;
    private ChildEventListener mChildEventListener;
    String TAG = "ProjectList";
    ProjectAdapter adapter;
    private String android_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDatabaseReference = mFirebaseDatabase.getReference().child("projects").child(android_id);
        projectList = new ArrayList<>();

        mProjectListView = findViewById(R.id.projectListView);
        adapter = new ProjectAdapter(this,R.layout.project_list_model,projectList);
        mProjectListView.setAdapter(adapter);

        mProjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listObject = new Intent(ProjectList.this,ProjectTimeline.class);
                listObject.putExtra("projectObject",projectList.get(position));
                startActivity(listObject);
            }
        });
        mProjectListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String randomId = adapter.getItem(position).getProjectKey();
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ProjectList.this);
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

        addProjectBtn = findViewById(R.id.addProjectBtn);
        addProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addProjectIntent = new Intent(ProjectList.this, com.example.android.participantsClient.AddProject.class);
                startActivity(addProjectIntent);
            }
        });

        attachDatabaseReadListener();
    }


    private void attachDatabaseReadListener() {
        Log.i(TAG,"TEST : attachDatabaseReadListener() start called");
        if (mChildEventListener == null) {
            Log.i(TAG,"TEST : mChildEventListener() = NULL");
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    final ProjectItem projectItem = dataSnapshot.getValue(ProjectItem.class);
                    adapter.add(projectItem);

                }

                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.i(TAG,"TEST : onChildRemoved() called");
                    final ProjectItem projectItem = dataSnapshot.getValue(ProjectItem.class);
                    adapter.remove(projectItem);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                }

                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            };
            Log.i(TAG,"TEST : attachDatabaseReadListener() end called");
            mMessageDatabaseReference.addChildEventListener(mChildEventListener);
        } else {
            Log.i(TAG,"TEST : mChildEventListener != null");
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
        adapter.clear();
        detachDatabaseReadListener();
        Log.i(TAG,"TEST : onPause() = called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachDatabaseReadListener();
        Log.i(TAG,"TEST : onResume() = called");
    }
}

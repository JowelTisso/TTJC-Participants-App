package com.job.challenge.ttjcParticipantsApp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class ProjectTimeLineFragment extends Fragment {


    private boolean checkedBool = false;

    public ProjectTimeLineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_time_line, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CheckBox cb1 = view.findViewById(R.id.CB1);
        final CheckBox cb2 = view.findViewById(R.id.CB2);
        final CheckBox cb3 = view.findViewById(R.id.CB3);
        final CheckBox cb4 = view.findViewById(R.id.CB4);
        final CheckBox cb5 = view.findViewById(R.id.CB5);
        final CheckBox cb6 = view.findViewById(R.id.CB6);
        final CheckBox cb7 = view.findViewById(R.id.CB7);
        final CheckBox cb8 = view.findViewById(R.id.CB8);
        final CheckBox cb9 = view.findViewById(R.id.CB9);
        final CheckBox cb10 = view.findViewById(R.id.CB10);
        final CheckBox cb11 = view.findViewById(R.id.CB11);
        final CheckBox cb12 = view.findViewById(R.id.CB12);
        final CheckBox cb13 = view.findViewById(R.id.CB13);
        final CheckBox cb14 = view.findViewById(R.id.CB14);
        final CheckBox cb15 = view.findViewById(R.id.CB15);
        final CheckBox cb16 = view.findViewById(R.id.CB16);
        final CheckBox cb17 = view.findViewById(R.id.CB17);
        final CheckBox cb18 = view.findViewById(R.id.CB18);
        final CheckBox cb19 = view.findViewById(R.id.CB19);
        final CheckBox cb20 = view.findViewById(R.id.CB20);
        final CheckBox cb21 = view.findViewById(R.id.CB21);

        cb1.setChecked(getState("cb1"));
        cb2.setChecked(getState("cb2"));
        cb3.setChecked(getState("cb3"));
        cb4.setChecked(getState("cb4"));
        cb5.setChecked(getState("cb5"));
        cb6.setChecked(getState("cb6"));
        cb7.setChecked(getState("cb7"));
        cb8.setChecked(getState("cb8"));
        cb9.setChecked(getState("cb9"));
        cb10.setChecked(getState("cb10"));
        cb11.setChecked(getState("cb11"));
        cb12.setChecked(getState("cb12"));
        cb13.setChecked(getState("cb13"));
        cb14.setChecked(getState("cb14"));
        cb15.setChecked(getState("cb15"));
        cb16.setChecked(getState("cb16"));
        cb17.setChecked(getState("cb17"));
        cb18.setChecked(getState("cb18"));
        cb19.setChecked(getState("cb19"));
        cb20.setChecked(getState("cb20"));
        cb21.setChecked(getState("cb21"));

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked()) {
                    saveState(true, "cb1");
                } else {
                    saveState(false, "cb1");
                }
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb2.isChecked()) {
                    saveState(true, "cb2");
                } else {
                    saveState(false, "cb2");
                }
            }
        });
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb3.isChecked()) {
                    saveState(true, "cb3");
                } else {
                    saveState(false, "cb3");
                }
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb4.isChecked()) {
                    saveState(true, "cb4");
                } else {
                    saveState(false, "cb4");
                }
            }
        });
        cb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb5.isChecked()) {
                    saveState(true, "cb5");
                } else {
                    saveState(false, "cb5");
                }
            }
        });
        cb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb6.isChecked()) {
                    saveState(true, "cb6");
                } else {
                    saveState(false, "cb6");
                }
            }
        });
        cb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb7.isChecked()) {
                    saveState(true, "cb7");
                } else {
                    saveState(false, "cb7");
                }
            }
        });
        cb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb8.isChecked()) {
                    saveState(true, "cb8");
                } else {
                    saveState(false, "cb8");
                }
            }
        });
        cb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb9.isChecked()) {
                    saveState(true, "cb9");
                } else {
                    saveState(false, "cb9");
                }
            }
        });
        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb10.isChecked()) {
                    saveState(true, "cb10");
                } else {
                    saveState(false, "cb10");
                }
            }
        });
        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb11.isChecked()) {
                    saveState(true, "cb11");
                } else {
                    saveState(false, "cb11");
                }
            }
        });
        cb12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb12.isChecked()) {
                    saveState(true, "cb12");
                } else {
                    saveState(false, "cb12");
                }
            }
        });
        cb13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb13.isChecked()) {
                    saveState(true, "cb13");
                } else {
                    saveState(false, "cb13");
                }
            }
        });
        cb14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb14.isChecked()) {
                    saveState(true, "cb14");
                } else {
                    saveState(false, "cb14");
                }
            }
        });
        cb15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb15.isChecked()) {
                    saveState(true, "cb15");
                } else {
                    saveState(false, "cb15");
                }
            }
        });
        cb16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb16.isChecked()) {
                    saveState(true, "cb16");
                } else {
                    saveState(false, "cb16");
                }
            }
        });
        cb17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb17.isChecked()) {
                    saveState(true, "cb17");
                } else {
                    saveState(false, "cb17");
                }
            }
        });
        cb18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb18.isChecked()) {
                    saveState(true, "cb18");
                } else {
                    saveState(false, "cb18");
                }
            }
        });
        cb19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb19.isChecked()) {
                    saveState(true, "cb19");
                } else {
                    saveState(false, "cb19");
                }
            }
        });
        cb20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb20.isChecked()) {
                    saveState(true, "cb20");
                } else {
                    saveState(false, "cb20");
                }
            }
        });
        cb21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb21.isChecked()) {
                    saveState(true, "cb21");
                } else {
                    saveState(false, "cb21");
                }
            }
        });

    }

    //To save the checkbox state using sharedPreferences
    private void saveState(Boolean bool, String cb) {
        //To check which item fom the list has accessed to keep save state separate for all items
        //Adding item title along with the key to keep the save state unique for each item
        ProjectTimeline activity = (ProjectTimeline) getActivity();
        final String check = activity.title.getText().toString();

        checkedBool = bool;
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit()
                .putBoolean(check + cb, checkedBool).apply();
    }

    //To get the checkbox state using sharedPreferences in onCreate
    private boolean getState(String cb) {
        //Adding the title along with the key to get the respective state for the checkbox
        ProjectTimeline activity = (ProjectTimeline) getActivity();
        final String check = activity.title.getText().toString();

        checkedBool = PreferenceManager.getDefaultSharedPreferences(getContext())
                .getBoolean(check + cb, false);

        return checkedBool;
    }

}

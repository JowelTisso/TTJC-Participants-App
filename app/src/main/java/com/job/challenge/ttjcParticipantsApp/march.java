package com.job.challenge.ttjcParticipantsApp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.Calendar;

public class march extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private final Calendar calendar = Calendar.getInstance();
    private final int currentMonth = calendar.get(Calendar.MONTH);
    private final int currentDate = calendar.get(Calendar.DAY_OF_MONTH);
    public march() {
    }
    // TODO: Rename and change types and number of parameters
    public static march newInstance(String param1, String param2) {
        march fragment = new march();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_march, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CheckBox cb1 = view.findViewById(R.id.marchCB1);
        CheckBox cb2 = view.findViewById(R.id.marchCB2);
        CheckBox cb3 = view.findViewById(R.id.marchCB3);
        CheckBox cb4 = view.findViewById(R.id.marchCB4);
        CheckBox cb5 = view.findViewById(R.id.marchCB5);
        CheckBox cb6 = view.findViewById(R.id.marchCB6);
        CheckBox cb7 = view.findViewById(R.id.marchCB7);
        CheckBox cb8 = view.findViewById(R.id.marchCB8);
        CheckBox cb9 = view.findViewById(R.id.marchCB9);
        CheckBox cb10 = view.findViewById(R.id.marchCB10);
        CheckBox cb11 = view.findViewById(R.id.marchCB11);
        CheckBox cb12 = view.findViewById(R.id.marchCB12);
        CheckBox cb13 = view.findViewById(R.id.marchCB13);
        CheckBox cb14 = view.findViewById(R.id.marchCB14);
        CheckBox cb15 = view.findViewById(R.id.marchCB15);
        CheckBox cb16 = view.findViewById(R.id.marchCB16);
        CheckBox cb17 = view.findViewById(R.id.marchCB17);
        CheckBox cb18 = view.findViewById(R.id.marchCB18);
        CheckBox cb19 = view.findViewById(R.id.marchCB19);

        if (currentMonth == Calendar.MARCH) {

            if (currentDate == 13) {
                cb1.setChecked(true);
            } else if (currentDate == 14) {
                cb1.setChecked(true);
                cb2.setChecked(true);
            } else if (currentDate == 15) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
            } else if (currentDate == 16) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
            } else if (currentDate == 17) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
            } else if (currentDate == 18) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
            } else if (currentDate == 19) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
            } else if (currentDate == 20) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
            } else if (currentDate == 21) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
            } else if (currentDate == 22) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
            } else if (currentDate == 23) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
            } else if (currentDate == 24) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
            } else if (currentDate == 25) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
            } else if (currentDate == 26) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
            } else if (currentDate == 27) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
            } else if (currentDate == 28) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
            } else if (currentDate == 29) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
            } else if (currentDate == 30) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
            } else if (currentDate == 31) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
                cb10.setChecked(true);
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
            }

        }else if (currentMonth > Calendar.MARCH){
            cb1.setChecked(true);
            cb2.setChecked(true);
            cb3.setChecked(true);
            cb4.setChecked(true);
            cb5.setChecked(true);
            cb6.setChecked(true);
            cb7.setChecked(true);
            cb8.setChecked(true);
            cb9.setChecked(true);
            cb10.setChecked(true);
            cb11.setChecked(true);
            cb12.setChecked(true);
            cb13.setChecked(true);
            cb14.setChecked(true);
            cb15.setChecked(true);
            cb16.setChecked(true);
            cb17.setChecked(true);
            cb18.setChecked(true);
            cb19.setChecked(true);
        }
    }
}

package com.example.android.participantsClient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import java.util.Calendar;


public class may extends Fragment {

    private final Calendar calendar = Calendar.getInstance();
    private final int currentMonth = calendar.get(Calendar.MONTH);
    private final int currentDate = calendar.get(Calendar.DAY_OF_MONTH);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public may() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static may newInstance(String param1, String param2) {
        may fragment = new may();
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
        return inflater.inflate(R.layout.fragment_may, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CheckBox cb1 = view.findViewById(R.id.mayCB1);
        CheckBox cb2 = view.findViewById(R.id.mayCB2);
        CheckBox cb3 = view.findViewById(R.id.mayCB3);
        CheckBox cb4 = view.findViewById(R.id.mayCB4);
        CheckBox cb5 = view.findViewById(R.id.mayCB5);
        CheckBox cb6 = view.findViewById(R.id.mayCB6);
        CheckBox cb7 = view.findViewById(R.id.mayCB7);
        CheckBox cb8 = view.findViewById(R.id.mayCB8);
        CheckBox cb9 = view.findViewById(R.id.mayCB9);
        CheckBox cb10 = view.findViewById(R.id.mayCB10);
        CheckBox cb11 = view.findViewById(R.id.mayCB11);
        CheckBox cb12 = view.findViewById(R.id.mayCB12);
        CheckBox cb13 = view.findViewById(R.id.mayCB13);
        CheckBox cb14 = view.findViewById(R.id.mayCB14);
        CheckBox cb15 = view.findViewById(R.id.mayCB15);
        CheckBox cb16 = view.findViewById(R.id.mayCB16);
        CheckBox cb17 = view.findViewById(R.id.mayCB17);
        CheckBox cb18 = view.findViewById(R.id.mayCB18);
        CheckBox cb19 = view.findViewById(R.id.mayCB19);
        CheckBox cb20 = view.findViewById(R.id.mayCB20);
        CheckBox cb21 = view.findViewById(R.id.mayCB21);
        CheckBox cb22 = view.findViewById(R.id.mayCB22);
        CheckBox cb23 = view.findViewById(R.id.mayCB23);
        CheckBox cb24 = view.findViewById(R.id.mayCB24);
        CheckBox cb25 = view.findViewById(R.id.mayCB25);
        CheckBox cb26 = view.findViewById(R.id.mayCB26);
        CheckBox cb27 = view.findViewById(R.id.mayCB27);
        CheckBox cb28 = view.findViewById(R.id.mayCB28);
        CheckBox cb29 = view.findViewById(R.id.mayCB29);
        CheckBox cb30 = view.findViewById(R.id.mayCB30);
        CheckBox cb31 = view.findViewById(R.id.mayCB31);


        if (currentMonth == Calendar.MAY) {


            if (currentDate == 1) {
                cb1.setChecked(true);
            } else if (currentDate == 2) {
                cb1.setChecked(true);
                cb2.setChecked(true);
            } else if (currentDate == 3) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
            } else if (currentDate == 4) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
            } else if (currentDate == 5) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
            } else if (currentDate == 6) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
            } else if (currentDate == 7) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
            } else if (currentDate == 8) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
            } else if (currentDate == 9) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
                cb8.setChecked(true);
                cb9.setChecked(true);
            } else if (currentDate == 10) {
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
            } else if (currentDate == 11) {
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
            } else if (currentDate == 12) {
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
            } else if (currentDate == 13) {
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
            } else if (currentDate == 14) {
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
            } else if (currentDate == 15) {
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
            } else if (currentDate == 16) {
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
            } else if (currentDate == 17) {
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
            } else if (currentDate == 18) {
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
            } else if (currentDate == 19) {
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
            }if (currentDate == 20) {
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
                cb20.setChecked(true);
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
                cb20.setChecked(true);
                cb21.setChecked(true);
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
                cb11.setChecked(true);
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
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
                cb12.setChecked(true);
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
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
                cb13.setChecked(true);
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
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
                cb14.setChecked(true);
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
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
                cb15.setChecked(true);
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
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
                cb16.setChecked(true);
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
                cb27.setChecked(true);
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
                cb17.setChecked(true);
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
                cb27.setChecked(true);
                cb28.setChecked(true);
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
                cb18.setChecked(true);
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
                cb27.setChecked(true);
                cb28.setChecked(true);
                cb29.setChecked(true);
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
                cb19.setChecked(true);
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
                cb27.setChecked(true);
                cb28.setChecked(true);
                cb29.setChecked(true);
                cb30.setChecked(true);
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
                cb20.setChecked(true);
                cb21.setChecked(true);
                cb22.setChecked(true);
                cb23.setChecked(true);
                cb24.setChecked(true);
                cb25.setChecked(true);
                cb26.setChecked(true);
                cb27.setChecked(true);
                cb28.setChecked(true);
                cb29.setChecked(true);
                cb30.setChecked(true);
                cb31.setChecked(true);
            }

        }else if (currentMonth > Calendar.MAY){
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
            cb20.setChecked(true);
            cb21.setChecked(true);
            cb22.setChecked(true);
            cb23.setChecked(true);
            cb24.setChecked(true);
            cb25.setChecked(true);
            cb26.setChecked(true);
            cb27.setChecked(true);
            cb28.setChecked(true);
            cb29.setChecked(true);
            cb30.setChecked(true);
            cb31.setChecked(true);
        }

    }
}

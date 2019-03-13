package com.ocra.dwarbi.ocra.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocra.dwarbi.ocra.Formulier;
import com.ocra.dwarbi.ocra.R;

public class FormulierInfoFragment extends Fragment {

    /**
     * A simple {@link Fragment} subclass.
     */
        public FormulierInfoFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            Bundle bundle= getArguments();

            View view= inflater.inflate(R.layout.fragment_formulierinfo, container, false);

            String phone= (String) bundle.get("phone");
            String birthday= (String) bundle.get("birthday");
            String gender= (String) bundle.get("gender");
            String birthplace=(String) bundle.get("birthplace");
            String email=(String) bundle.get("email");
            String previouseducation=(String) bundle.get("previouseducation");
            String major=(String) bundle.get("major");

            ((TextView)view.findViewById(R.id.phone)).setText(phone);
            ((TextView)view.findViewById(R.id.birthday)).setText(birthday);
            ((TextView)view.findViewById(R.id.gender)).setText(gender);
            ((TextView)view.findViewById(R.id.birthplace)).setText(birthplace);
            ((TextView)view.findViewById(R.id.email)).setText(email);
            ((TextView)view.findViewById(R.id.previouseducation)).setText(previouseducation);
            ((TextView)view.findViewById(R.id.major)).setText(major);


            return view;
        }

    }


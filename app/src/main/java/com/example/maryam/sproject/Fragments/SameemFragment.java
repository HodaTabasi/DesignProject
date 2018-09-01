package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SameemFragment extends Fragment {


    public SameemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sameem, container, false);

        TextView tv_sammem_bio = view.findViewById(R.id.tv_sammem_bio);
        TextView tv_ques = view.findViewById(R.id.tv_ques);
        TextView tv_rights = view.findViewById(R.id.tv_rights);
        TextView tv_conditions = view.findViewById(R.id.tv_conditions);
        TextView tv_call_us = view.findViewById(R.id.tv_call_us);

        tv_sammem_bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AboutUsFragment(), true);

            }
        });

        tv_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AskFragment(), true);

            }
        });

        tv_rights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AboutUsFragment(), true);

            }
        });

        tv_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AboutUsFragment(), true);

            }
        });

        tv_call_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new CallUsFragment(), true);

            }
        });


        return view;
    }

}

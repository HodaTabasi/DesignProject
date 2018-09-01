package com.example.maryam.sproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Activities.ContainerActivity;
import com.example.maryam.sproject.R;


public class ConfirmationFragment extends Fragment {


    public ConfirmationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

}

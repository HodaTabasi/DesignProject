package com.example.maryam.sproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDitailsPaintingWallFragment extends Fragment implements View.OnClickListener {


    public ProjectDitailsPaintingWallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_painting_wall, container, false);
    }

    @Override
    public void onClick(View v) {

    }
}

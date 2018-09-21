package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProjectFragment extends Fragment implements View.OnClickListener {


    private View view;
    private RelativeLayout mInSide;
    private RelativeLayout mArch;
    private RelativeLayout mGraphic;
    private RelativeLayout mDrawWall;
    private RelativeLayout mSearchDesigner;
    private RelativeLayout mMostion;

    public AddProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_project, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        addListeners();
    }

    private void initView() {
        mInSide = getView().findViewById(R.id.in_side);
        mArch = getView().findViewById(R.id.arch);
        mGraphic = getView().findViewById(R.id.graphic);
        mMostion = getView().findViewById(R.id.mostion);
        mDrawWall = getView().findViewById(R.id.draw_wall);
        mSearchDesigner = getView().findViewById(R.id.search_designer);
    }

    private void addListeners() {
        mInSide.setOnClickListener(this);
        mArch.setOnClickListener(this);
        mGraphic.setOnClickListener(this);
        mMostion.setOnClickListener(this);
        mDrawWall.setOnClickListener(this);
        mSearchDesigner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.in_side:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProjectDetailsInterFragment(), true);
                break;
            case R.id.mostion:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProjectDitailsMotionFragment(), true);
                break;
            case R.id.draw_wall:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProjectDitailsPaintingWallFragment(), true);
                break;
            case R.id.graphic:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProjectDitailesGraphicsFragment(), true);
                break;
            case R.id.arch:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProjectDetailsArchFragment(), true);
                break;
            case R.id.search_designer:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SearchFragment(), true);
                break;
        }
    }
}
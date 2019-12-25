package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.smm.sapp.sproject.ConstantInterFace;
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
    ImageView ic_back;
    private String type;

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
        setBottomBarShap();
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });


        if (!getArguments().isEmpty()) {
            Bundle bundle = getArguments();
            type = bundle.getString("type");

            if (type.equals("wall")) {
                ProjectDitailsPaintingWallFragment fragment = new ProjectDitailsPaintingWallFragment();
                Bundle bundle1 = new Bundle();
                fragment.setArguments(bundle1);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment);
            } else if (type.equals("arch")) {
                ProjectDetailsArchFragment fragment = new ProjectDetailsArchFragment();
                Bundle bundle1 = new Bundle();
                fragment.setArguments(bundle1);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment);
            } else if (type.equals("graphic")) {
                ProjectDitailesGraphicsFragment fragment = new ProjectDitailesGraphicsFragment();
                Bundle bundle1 = new Bundle();
                fragment.setArguments(bundle1);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment);
            } else if (type.equals("inter")) {
                ProjectDetailsInterFragment fragment = new ProjectDetailsInterFragment();
                Bundle bundle1 = new Bundle();
                fragment.setArguments(bundle1);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment);
            } else if (type.equals("moshen")) {
                ProjectDitailsMotionFragment fragment = new ProjectDitailsMotionFragment();
                Bundle bundle1 = new Bundle();
                fragment.setArguments(bundle1);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment);
            }
        }
    }

    private void initView() {
        mInSide = getView().findViewById(R.id.in_side);
        mArch = getView().findViewById(R.id.arch);
        mGraphic = getView().findViewById(R.id.graphic);
        mMostion = getView().findViewById(R.id.mostion);
        mDrawWall = getView().findViewById(R.id.draw_wall);
        mSearchDesigner = getView().findViewById(R.id.search_designer);
        ic_back = getView().findViewById(R.id.ic_back);
    }

    private void addListeners() {
        mInSide.setOnClickListener(this);
        mArch.setOnClickListener(this);
        mGraphic.setOnClickListener(this);
        mMostion.setOnClickListener(this);
        mDrawWall.setOnClickListener(this);
        mSearchDesigner.setOnClickListener(this);
    }

    private void setBottomBarShap() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Bundle bundle = new Bundle();
        switch (id) {
            case R.id.in_side:
                ProjectDetailsInterFragment fragment1 = new ProjectDetailsInterFragment();
                fragment1.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment1, true);
                break;
            case R.id.mostion:
                ProjectDitailsMotionFragment fragment2 = new ProjectDitailsMotionFragment();
                fragment2.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment2, true);
                break;
            case R.id.draw_wall:
                ProjectDitailsPaintingWallFragment fragment = new ProjectDitailsPaintingWallFragment();
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                break;
            case R.id.graphic:
                ProjectDitailesGraphicsFragment fragment3 = new ProjectDitailesGraphicsFragment();
                fragment3.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment3, true);
                break;
            case R.id.arch:
                ProjectDetailsArchFragment fragment4 = new ProjectDetailsArchFragment();
                fragment4.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment4, true);
                break;
            case R.id.search_designer:
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SearchFragment(), true);
                break;
        }
    }
}

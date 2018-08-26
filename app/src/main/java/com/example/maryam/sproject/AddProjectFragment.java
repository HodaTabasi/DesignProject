package com.example.maryam.sproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProjectFragment extends Fragment implements View.OnClickListener{


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
        initView();
    }

    private void initView() {
        mInSide = getView().findViewById(R.id.in_side);
        mArch = getView().findViewById(R.id.arch);
        mGraphic = getView().findViewById(R.id.graphic);
        mMostion = getView().findViewById(R.id.mostion);
        mDrawWall = getView().findViewById(R.id.draw_wall);
        mSearchDesigner = getView().findViewById(R.id.search_designer);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.in_side:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_project_ditails_one,new ProjectDitailsOneFragment());
                break;
            case R.id.mostion:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_project_ditails_motion,new ProjectDitailsMotionFragment());
                break;
            case R.id.draw_wall:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_project_ditails_painting_wall,new ProjectDitailsPaintingWallFragment());
                break;
            case R.id.graphic:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_project_ditailes_graphics,new ProjectDitailesGraphicsFragment());
                break;
            case R.id.arch:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_project_ditails_one,new ProjectDitailsOneFragment());
                break;
            case R.id.search_designer:
                FragmentsUtil.replaceFragment(getActivity(),R.layout.fragment_search,new SearchFragment());
                break;
        }
    }
}

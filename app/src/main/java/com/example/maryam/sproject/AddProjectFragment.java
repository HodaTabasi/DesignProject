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

    private void addListeners(){
        mInSide.setOnClickListener(this);
        mArch.setOnClickListener(this);
        mGraphic.setOnClickListener(this);
        mMostion.setOnClickListener(this);
        mDrawWall.setOnClickListener(this);
        mSearchDesigner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ProjectDitailsOneFragment ditailsOneFragment = new ProjectDitailsOneFragment();
        Bundle bundle = new Bundle();
        int id = v.getId();
        switch (id) {
            case R.id.in_side:
                bundle.putString("address", "تصميم داخلي");
                bundle.putString("button_type", "شقق سكنية - تصميم داخلي");
                ditailsOneFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new ProjectDitailsOneFragment());
                break;
            case R.id.mostion:
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new ProjectDitailsMotionFragment());
                break;
            case R.id.draw_wall:
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new ProjectDitailsPaintingWallFragment());
                break;
            case R.id.graphic:
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new ProjectDitailesGraphicsFragment());
                break;
            case R.id.arch:
                bundle.putString("address", "تصميم معماري");
                bundle.putString("button_type", "رسومات هندسية - تصميم معماري");
                ditailsOneFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new ProjectDitailsOneFragment());
                break;
            case R.id.search_designer:
                FragmentsUtil.replaceFragment(getActivity(), R.layout.activity_container, new SearchFragment());
                break;
        }
    }
}

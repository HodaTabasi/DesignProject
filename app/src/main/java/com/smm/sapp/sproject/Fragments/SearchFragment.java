package com.smm.sapp.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.Adapters.DesignProfileAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Models.DesignProfile;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


public class SearchFragment extends Fragment {
    RecyclerView resSearch;
    GridLayoutManager gridLayoutManager;
    private View view;
    /**
     * بحث عن مصمم
     */
    private EditText mTxtSearch;
    /**
     * موشن
     */
    private TextView mMotionButton;
    /**
     * تصاميم جرافيكس
     */
    private TextView mGhButton;
    /**
     * رسم جداري
     */
    private TextView mWallButton;
    /**
     * تصميم معماري
     */
    private TextView mArchButton;
    /**
     * تصميم داخلي
     */
    private TextView mInButton;
    private RatingBar mRattingDesigner;
    ImageView ic_back;

    List<DesignProfile> profiles;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void initView() {
        mTxtSearch = getView().findViewById(R.id.txt_search);
        mMotionButton = getView().findViewById(R.id.motion_button);
        mGhButton = getView().findViewById(R.id.gh_button);
        mWallButton = getView().findViewById(R.id.wall_button);
        mArchButton = getView().findViewById(R.id.arch_button);
        mInButton = getView().findViewById(R.id.in_button);
        mRattingDesigner = getView().findViewById(R.id.ratting_designer);
        resSearch = getView().findViewById(R.id.res_search);

        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);

        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        resSearch.setLayoutManager(gridLayoutManager);
        profiles = new ArrayList<>();
        resSearch.setAdapter(new DesignProfileAdapter(getContext(), R.layout.item_layout_profile, profiles));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }
}

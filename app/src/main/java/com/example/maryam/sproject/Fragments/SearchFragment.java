package com.example.maryam.sproject.Fragments;

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
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.maryam.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


public class SearchFragment extends Fragment {
    RecyclerView resSearch;
    GridLayoutManager gridLayoutManager;
    private View view;
    /** بحث عن مصمم */
    private EditText mTxtSearch;
    /** موشن */
    private TextView mMotionButton;
    /** تصاميم جرافيكس */
    private TextView mGhButton;
    /** رسم جداري */
    private TextView mWallButton;
    /** تصميم معماري */
    private TextView mArchButton;
    /** تصميم داخلي */
    private TextView mInButton;
    private RatingBar mRattingDesigner;

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
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        resSearch.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
    }
}

package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maryam.sproject.R;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnderwayFragment extends Fragment {



    /** 29/08/2017 */
    private TextView mProjectStartDate;
    /** 1200 ريال سعودى */
    private TextView mProjectMoney;
    /** 29/08/2017 */
    private TextView mProjectEndDate;
    /**   50 ايام */
    private TextView mProjectTime;
    private CircleImageView mProfileImage1;
    /** حسام اليماني  */
    private TextView mName;
    /** مصمم */
    private TextView mItsType;
    private CircleImageView mProfileImage2;
    /** حسام اليماني  */
    private TextView mName1;
    /** صاحب مشروع */
    private TextView mItsType1;
    private LinearLayout mWw;
    private RecyclerView mMres;
    private ImageView mOther;
    private EditText mMessageEx;
    private ImageView mSendMessg;
    private LinearLayout mFf;

    public UnderwayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_underway, container, false);
    }

    private void initView(){
        mProjectStartDate = getView().findViewById(R.id.project_start_date);
        mProjectMoney = getView().findViewById(R.id.project_money);
        mProjectEndDate = getView().findViewById(R.id.project_end_date);
        mProjectTime = getView().findViewById(R.id.project_time);
        mProfileImage1 = getView().findViewById(R.id.profile_image1);
        mName = getView().findViewById(R.id.name);
        mItsType = getView().findViewById(R.id.its_type);
        mProfileImage2 = getView().findViewById(R.id.profile_image2);
        mName1 = getView().findViewById(R.id.name1);
        mItsType1 = getView().findViewById(R.id.its_type1);
        mMres = getView().findViewById(R.id.mres);
        mOther = getView().findViewById(R.id.other);
        mMessageEx = getView().findViewById(R.id.message_ex);
        mSendMessg = getView().findViewById(R.id.send_messg);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
}

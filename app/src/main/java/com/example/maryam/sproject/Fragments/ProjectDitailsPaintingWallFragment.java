package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maryam.sproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDitailsPaintingWallFragment extends Fragment implements View.OnClickListener {

    /**
     * الرسم الجداري
     */
    private TextView mNameWall;
    /**
     * ثلاثي ابعاد - رسم جداري
     */
    private TextView mWallType;
    private ImageView mWallUploadImage;
    /**
     * المساحة م2
     */
    private TextView mWallArea;
    private ImageView mWallLikeUploadImage;
    /**
     * المدينة
     */
    private TextView mWallCity;
    /**
     * خرائط - موقع المشروع
     */
    private TextView mWallMap;
    /**
     * الميزانية
     */
    private TextView mWallBalance;
    /**
     * تفاصيل عن المشروع
     */
    private TextView mWallProjectDietails;
    /**
     * المرفقات
     */
    private TextView mWallAttachment;
    /**
     * ارسل الطلب
     */
    private Button mWallSend;

    public ProjectDitailsPaintingWallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_painting_wall, container, false);
    }

    private void initView() {
        mWallSend = getView().findViewById(R.id.wall_send);
    }
    private void addListener(){
        mWallSend.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        addListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.wall_send:
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

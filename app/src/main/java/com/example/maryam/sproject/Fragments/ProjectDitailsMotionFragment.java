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
public class ProjectDitailsMotionFragment extends Fragment {



    /** تصاميم الموشن */
    private TextView mNameM;
    /** انفوجرافيك - تصميم فيديو */
    private TextView mMotionType;
    /** اسم المشروع */
    private TextView mProjectName;
    /** مدة المشروع  */
    private TextView mProjectTime;
    /** عن النشاط والخدمات  */
    private TextView mAboutActivity;
    private ImageView mMotionLikeImage;
    /** الميزانية */
    private TextView mMotionBalance;
    /** تفاصيل عن المشروع */
    private TextView mProjectDetiailsMotion;
    /** المرفقات */
    private TextView mAttachmentMotion;
    /** ارسل الطلب */
    private Button mSendMotion;

    public ProjectDitailsMotionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_motion, container, false);
    }

    private void initView() {
        mSendMotion = getView().findViewById(R.id.send_motion);
    }

    private void onClickMethod(){

        mSendMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        onClickMethod();
    }
}

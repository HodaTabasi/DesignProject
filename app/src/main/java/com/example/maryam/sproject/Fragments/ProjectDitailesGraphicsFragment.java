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
public class ProjectDitailesGraphicsFragment extends Fragment {



    /** تصاميم الجرافيكس */
    private TextView mNameGraphics;
    /** تصميم هوية بصرية - تصميم شعار */
    private TextView mGhType;
    /** اسم المشروع */
    private TextView mProjectNameGh;
    /** عن النشاط  */
    private TextView mAboutActivity;
    private ImageView mGhUploadImageLike;
    /** ابتكار */
    private TextView mInnovation;
    /** تطوير */
    private TextView mDevelop;
    /** طلب تصميم */
    private TextView mAskForDesign;
    /** لا */
    private TextView mNo;
    /** نعم */
    private TextView mYes;
    /** المشروع جديد */
    private TextView mNewProject;
    /** الميزانية */
    private TextView mGhBalance;
    /** تفاصيل عن المشروع */
    private TextView mProjectDeitailsGh;
    /** المرفقات */
    private TextView mGhAttachment;
    /** ارسل الطلب */
    private Button mSendGh;

    public ProjectDitailesGraphicsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditailes_graphics, container, false);
    }

    private void initView() {
        mSendGh = getView().findViewById(R.id.send_gh);
    }

    private void onClickMethod(){

        mSendGh.setOnClickListener(new View.OnClickListener() {
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

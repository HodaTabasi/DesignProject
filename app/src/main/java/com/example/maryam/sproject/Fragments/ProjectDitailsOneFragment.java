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

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDitailsOneFragment extends Fragment implements View.OnClickListener {



    /** التصميم الداخلي  */
    private TextView mName;
    /** شقة سكنية - تصميم داخلي */
    private TextView mInType;
    /** اختر الاستايل */
    private TextView mChooeseStyle;
    /** ادخل الالوان التى ترغبها في التصميم */
    private TextView mDesignColor;
    private ImageView mUploadImage;
    /** المساحة م2 */
    private TextView mArea2;
    private ImageView mUploadLikeImage;
    /** المدينة */
    private TextView mCity;
    /** خرائط - موقع المشروع  */
    private TextView mMap;
    /** الميزانية */
    private TextView mBalance;
    /** تفاصيل عن المشروع */
    private TextView mProjectDetailes;
    /** المرفقات */
    private TextView mAttachmentIn;
    /** ارسل الطلب */
    private Button mSendIn;

    Bundle bundle = getArguments();

    public ProjectDitailsOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        //fragmentType();
    }

    private void initView() {
        mName = getView().findViewById(R.id.name);
        mInType = getView().findViewById(R.id.in_type);
        mChooeseStyle = getView().findViewById(R.id.chooese_style);
        mDesignColor = getView().findViewById(R.id.design_color);
        mUploadImage = getView().findViewById(R.id.upload_image);
        mArea2 = getView().findViewById(R.id.area2);
        mUploadLikeImage = getView().findViewById(R.id.upload_like_image);
        mCity = getView().findViewById(R.id.city);
        mMap = getView().findViewById(R.id.map);
        mBalance = getView().findViewById(R.id.balance);
        mProjectDetailes = getView().findViewById(R.id.project_detailes);
        mAttachmentIn = getView().findViewById(R.id.attachment_in);
        mSendIn = getView().findViewById(R.id.send_in);
    }

//    private void fragmentType() {
//        if (!bundle.isEmpty()){
//          mName.setText(bundle.getString("address"));
//          mInType.setText(bundle.getString("button_type"));
//        }else {
//            Toast.makeText(getContext(), "no data arrived", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onClick(View v) {
        int id = getId();

        switch (id){
            case R.id.send_in:
                if (bundle.getInt("flag") == 0){

                }else {

                }
                break;
        }
    }
}

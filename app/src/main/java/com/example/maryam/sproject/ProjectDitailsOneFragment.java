package com.example.maryam.sproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDitailsOneFragment extends Fragment implements View.OnClickListener {


    private View view;
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


    }

    @Override
    public void onClick(View v) {
        int id = getId();

        switch (id){
            case R.id.send_in:
                break;
        }
    }
}

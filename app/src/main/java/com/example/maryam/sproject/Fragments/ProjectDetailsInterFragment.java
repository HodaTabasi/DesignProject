package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailsInterFragment extends Fragment {

    private EditText mInType;
    private EditText mChooeseStyle;
    private EditText mDesignColor;
    private ImageView mUploadImage;
    private EditText mArea2;
    private ImageView mUploadLikeImage;
    private EditText mCity;
    private EditText mMap;
    private EditText mBalance;
    private EditText mProjectDetailes;
    private TextView mAttachmentIn;
    private Button mSendIn;

    public ProjectDetailsInterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_inter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        mSendIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendInterDesignRequest();
            }
        });

    }

    private void sendInterDesignRequest() {

        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NTYyNjExLCJleHAiOjQ4MDgxNzYwNDU5MzIyODc0MTEsIm5iZiI6MTUzNjU2MjYxMSwianRpIjoiQ2NHRFlQOW4wcno4cjJCMCJ9.8fOb9OQliz0Z63t-SiZcTnRdExskt_Xtx68AWYy4hWU");
        map.put("name", mInType.getText().toString());
        map.put("style", mChooeseStyle.getText().toString());
        map.put("colors", mDesignColor.getText().toString());
        map.put("city", mCity.getText().toString());
        map.put("area", mArea2.getText().toString());
        map.put("lng", "");
        map.put("lat", "");
        map.put("balance", mBalance.getText().toString());
        map.put("descr", mProjectDetailes.getText().toString());

        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/projectmakeinter", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {

            }
        });

    }

    private void initView() {
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


}

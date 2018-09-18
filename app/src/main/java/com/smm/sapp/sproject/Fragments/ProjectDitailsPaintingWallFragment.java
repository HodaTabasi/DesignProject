package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

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
public class ProjectDitailsPaintingWallFragment extends Fragment {

    private EditText mWallType;
    private ImageView mWallUploadImage;
    private EditText mWallArea;
    private ImageView mWallLikeUploadImage;
    private EditText mWallCity;
    private EditText mWallMap;
    private EditText mWallBalance;
    private EditText mWallProjectDietails;
    private TextView mWallAttachment;
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
        mWallType = getView().findViewById(R.id.wall_type);
        mWallUploadImage = getView().findViewById(R.id.wall_upload_image);
        mWallLikeUploadImage = getView().findViewById(R.id.wall_like_upload_image);
        mWallCity = getView().findViewById(R.id.wall_city);
        mWallArea = getView().findViewById(R.id.wall_area);
        mWallMap = getView().findViewById(R.id.wall_map);
        mWallBalance = getView().findViewById(R.id.wall_balance);
        mWallProjectDietails = getView().findViewById(R.id.wall_project_dietails);
        mWallAttachment = getView().findViewById(R.id.wall_attachment);
        mWallSend = getView().findViewById(R.id.wall_send);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        mWallSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendWallRequest();

            }
        });
    }

    private void sendWallRequest() {

        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NTYyNjExLCJleHAiOjQ4MDgxNzYwNDU5MzIyODc0MTEsIm5iZiI6MTUzNjU2MjYxMSwianRpIjoiQ2NHRFlQOW4wcno4cjJCMCJ9.8fOb9OQliz0Z63t-SiZcTnRdExskt_Xtx68AWYy4hWU");
//        map.put("name", mMotionType.getText().toString());
        map.put("city", mWallCity.getText().toString());
        map.put("area", mWallArea.getText().toString());
//        map.put("lng", "");
//        map.put("lat", "");
//        attachment
        map.put("balance", mWallBalance.getText().toString());
        map.put("descr", mWallProjectDietails.getText().toString());

        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/projectmakewall", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {

            }
        });
    }
}


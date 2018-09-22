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

import com.smm.sapp.sproject.ConstantInterFace;
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
public class ProjectDitailsMotionFragment extends Fragment {


    private EditText mMotionType;
    private EditText mProjectName;
    private EditText mProjectTime;
    private EditText mAboutActivity;
    private ImageView mMotionLikeImage;
    private EditText mMotionBalance;
    private EditText mProjectDetiailsMotion;
    private TextView mAttachmentMotion;
    private Button mSendMotion;

    public ProjectDitailsMotionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_ditails_motion, container, false);
    }

    private void initView() {
        mMotionType = getView().findViewById(R.id.motion_type);
        mProjectName = getView().findViewById(R.id.project_name);
        mProjectTime = getView().findViewById(R.id.project_time);
        mAboutActivity = getView().findViewById(R.id.about_activity);
        mMotionLikeImage = getView().findViewById(R.id.motion_like_image);
        mMotionBalance = getView().findViewById(R.id.motion_balance);
        mProjectDetiailsMotion = getView().findViewById(R.id.project_detiails_motion);
        mAttachmentMotion = getView().findViewById(R.id.attachment_motion);
        mSendMotion = getView().findViewById(R.id.send_motion);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        mSendMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendMotionRequest();
            }
        });
    }

    private void sendMotionRequest() {

        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        //map.put("name", mMotionType.getText().toString());
        map.put("name", mProjectName.getText().toString());
        map.put("dur", mProjectTime.getText().toString());
        map.put("about", mAboutActivity.getText().toString());
        map.put("balance", mMotionBalance.getText().toString());
        map.put("descr", mProjectDetiailsMotion.getText().toString());

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/projectmakemoshen", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {

            }
        });
    }
}

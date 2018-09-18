package com.smm.sapp.sproject.Fragments;


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
public class ProjectDitailesGraphicsFragment extends Fragment {

    private EditText mGhType;
    private EditText mProjectNameGh;
    private EditText mAboutActivity;
    private ImageView mGhUploadImageLike;
    private TextView mInnovation;
    private TextView mDevelop;
    private TextView mAskForDesign;
    private TextView mNo;
    private TextView mYes;
    private TextView mNewProject;
    private EditText mGhBalance;
    private EditText mProjectDeitailsGh;
    private TextView mGhAttachment;
    private Button mSendGh;

    String savedValue1, savedValue2;


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
        mGhType = getView().findViewById(R.id.gh_type);
        mProjectNameGh = getView().findViewById(R.id.project_name_gh);
        mAboutActivity = getView().findViewById(R.id.about_activity);
        mGhUploadImageLike = getView().findViewById(R.id.gh_upload_image_like);
        mInnovation = getView().findViewById(R.id.innovation);
        mDevelop = getView().findViewById(R.id.develop);
        mAskForDesign = getView().findViewById(R.id.ask_for_design);
        mNo = getView().findViewById(R.id.no);
        mYes = getView().findViewById(R.id.yes);
        mNewProject = getView().findViewById(R.id.new_project);
        mGhBalance = getView().findViewById(R.id.gh_balance);
        mProjectDeitailsGh = getView().findViewById(R.id.project_deitails_gh);
        mGhAttachment = getView().findViewById(R.id.gh_attachment);
        mSendGh = getView().findViewById(R.id.send_gh);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();


        mSendGh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGraphicRequest();

            }
        });

//        mNo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedValue1 = "no";
//            }
//        });
//
//        mYes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedValue1 = "yes";
//            }
//        });
//
//        mInnovation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedValue2 = "innovation";
//            }
//        });
//
//        mDevelop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savedValue2 = "develop";
//            }
//        });

    }

    private void sendGraphicRequest() {

        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NTYyNjExLCJleHAiOjQ4MDgxNzYwNDU5MzIyODc0MTEsIm5iZiI6MTUzNjU2MjYxMSwianRpIjoiQ2NHRFlQOW4wcno4cjJCMCJ9.8fOb9OQliz0Z63t-SiZcTnRdExskt_Xtx68AWYy4hWU");
        //map.put("name", mMotionType.getText().toString());
        map.put("name", mProjectNameGh.getText().toString());
        //map.put("about", mAboutActivity.getText().toString());
//        map.put("newp", );
//        map.put("d_type", );
        map.put("balance", mGhBalance.getText().toString());
        map.put("descr", mProjectDeitailsGh.getText().toString());

        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/projectmakegraphic", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {

            }
        });

    }


}

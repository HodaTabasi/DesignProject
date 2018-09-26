package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.MyMessageDetailAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MessageDetails;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


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
    ImageView ic_back;
    User user;
    OfferModel model;

    private List<MessageDetails> details;
    MyMessageDetailAdapter adapter;
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
        mMres.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mOther = getView().findViewById(R.id.other);
        mMessageEx = getView().findViewById(R.id.message_ex);
        mSendMessg = getView().findViewById(R.id.send_messg);
        details = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        mSendMessg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewMessageRequest();
            }
        });
        Bundle bundle = getArguments();
         user = bundle.getParcelable("user");
         model = bundle.getParcelable("offer");

         mName.setText(user.getName());
         mName1.setText(ConstantInterFace.USER.getName());
         mProjectMoney.setText(model.getBalance());
         mProjectTime.setText(model.getDur());
         mProjectStartDate.setText(model.getCreated_at());
         mProjectEndDate.setText("");

        getAConversationRequest();
    }

    private void getAConversationRequest(){
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("user_id",user.getId()+"");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/getAconversation", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")){
                                Gson gson = new Gson();
                                TypeToken<List<MessageDetails>> token = new TypeToken<List<MessageDetails>>() {};
                                details = gson.fromJson(object.getJSONArray("msgs").toString(),token.getType());
                                adapter = new MyMessageDetailAdapter(getContext(),details);
                                mMres.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void sendNewMessageRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token",ConstantInterFace.USER.getToken());
        stringMap.put("msg",mMessageEx.getText().toString());
        stringMap.put("seconed_id",user.getId()+"");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/sendmsg", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                final JSONObject object = new JSONObject(s);
                final JSONObject object1 = object.getJSONObject("status");
                Log.e("ddf",s);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")){
                                Gson gson = new Gson();
                                MessageDetails messageDetails = gson.fromJson(object.getJSONObject("msg").toString(),MessageDetails.class);
                                details.add(0,messageDetails);
                                adapter.notifyDataSetChanged();
                                mMessageEx.setText("");
                            }else {
                                Toast.makeText(getContext(), "لم يتم الارسال", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}

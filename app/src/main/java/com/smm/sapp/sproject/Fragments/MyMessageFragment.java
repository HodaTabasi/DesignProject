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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.MyMessageAdapter;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MyMessageModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class MyMessageFragment extends Fragment {



    private RecyclerView mMyMessage;
    MyMessageAdapter adapter;
    List<MyMessageModel> myMessageModelList;

    public MyMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_masseages, container, false);
    }

    private void initView(View view){
        mMyMessage = view.findViewById(R.id.my_message);
        mMyMessage.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }
    private void getMyConversationsRequest(){
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODU4NS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM0NzcwMTA0LCJleHAiOjIxNDc0ODM2NDcsIm5iZiI6MTUzNDc3MDEwNCwianRpIjoiRnByN1h6aEI3SWtHb0xpVyJ9.HC4LMZ1_wioWsUfEeKOUa2RlkTkBh98bHYbT-RYHy5o");
        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/getmyconversations", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");


                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")){
                                Gson gson = new Gson();
                                TypeToken<List<MyMessageModel>> token = new TypeToken<List<MyMessageModel>>() {};
                                myMessageModelList = gson.fromJson(object.getJSONArray("convs").toString(),token.getType());
                                adapter = new MyMessageAdapter(getContext(),R.layout.layout_item_notification,myMessageModelList);
                                mMyMessage.setAdapter(adapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
        getMyConversationsRequest();
    }
}

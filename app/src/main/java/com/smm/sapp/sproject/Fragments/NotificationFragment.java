package com.smm.sapp.sproject.Fragments;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.Activities.SplashActivity;
import com.smm.sapp.sproject.Adapters.NotificationAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Models.Notifications;
import com.smm.sapp.sproject.Models.NotificationsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class NotificationFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mNotificationAttention;
    private LinearLayoutManager layoutManager;
    private NotificationAdapter adapter;
    ImageView ic_back;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ConstantInterFace.NOTIFICATION_NUMBER  = 0;
        return view;
    }

    private void initView() {
        mNotificationAttention = getView().findViewById(R.id.notification_attention);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mNotificationAttention.setLayoutManager(layoutManager);
        adapter = new NotificationAdapter(getContext(), R.layout.layout_item_notification, ConstantInterFace.notificationsModels);
        mNotificationAttention.setAdapter(adapter);
        ic_back = getView().findViewById(R.id.ic_back);

        mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        setBottomBar();

//        mSwipeRefreshLayout.post(new Runnable() {
//
//            @Override
//            public void run() {
//
//                mSwipeRefreshLayout.setRefreshing(true);
//
//                // Fetching data from server
//                getNotifications(ConstantInterFace.USER.getToken());
//            }
//        });

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeRefreshLayout.setRefreshing(true);
                getFragmentManager().popBackStack();
            }
        });

    }

    private void setBottomBar() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
    }

    @Override
    public void onRefresh() {
        getNotifications(ConstantInterFace.USER.getToken());
    }

    private void getNotifications(String token) {
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/mynotifications?token="+token, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                String s = response.body().string();
                Log.e("okHttpClient",""+s);

                final JSONObject jsonObject = new JSONObject(s);
                final JSONObject object = jsonObject.getJSONObject("status");
                final Gson gson = new Gson();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                ConstantInterFace.notificationsModels = gson.fromJson(jsonObject.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationsModels>>(){}.getType());
                                adapter.notifyDataSetChanged();
                                mSwipeRefreshLayout.setRefreshing(false);
                            } else {
                                Toast.makeText(getContext(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
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

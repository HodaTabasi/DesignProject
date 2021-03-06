package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.NotificationAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
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

    int current_page, total_pages, flag;

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

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                // Fetching data from server
                getNotifications(ConstantInterFace.USER.getToken(),1);
            }
        });

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

        if (total_pages == current_page && current_page != 1){
            mSwipeRefreshLayout.setRefreshing(false);
        }else {
            getNotifications(ConstantInterFace.USER.getToken(),current_page + 1);
        }
    }

    private void getNotifications(String token,int current) {
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/mynotifications?token="+token+ "&i_current_page=" + current, new OkHttpCallback() {
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
                final JSONObject paginationObj = jsonObject.getJSONObject("pagination");
                final Gson gson = new Gson();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                current_page = Integer.valueOf(paginationObj.getString("i_current_page"));
                                total_pages = Integer.valueOf(paginationObj.getString("i_total_pages"));
                                if (current_page <= 1){
                                    ConstantInterFace.notificationsModels = gson.fromJson(jsonObject.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationsModels>>(){}.getType());
                                    adapter = new NotificationAdapter(getContext(), R.layout.layout_item_notification, ConstantInterFace.notificationsModels);
                                    mNotificationAttention.setAdapter(adapter);
                                }else {
                                    List<NotificationsModels> notificationsModels = gson.fromJson(jsonObject.getJSONArray("notifications").toString(), new TypeToken<ArrayList<NotificationsModels>>(){}.getType());
                                    ConstantInterFace.notificationsModels.addAll(notificationsModels);
                                    adapter.notifyDataSetChanged();
                                }

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

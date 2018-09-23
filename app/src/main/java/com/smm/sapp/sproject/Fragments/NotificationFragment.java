package com.smm.sapp.sproject.Fragments;


import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smm.sapp.sproject.Adapters.NotificationAdapter;
import com.smm.sapp.sproject.Models.Notifications;
import com.smm.sapp.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;

public class NotificationFragment extends Fragment {



    private RecyclerView mNotificationAttention;
    private LinearLayoutManager layoutManager;
    List<Notifications> notifications;
    ImageView ic_back;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        return view;
    }

    private void initView() {
        mNotificationAttention = getView().findViewById(R.id.notification_attention);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mNotificationAttention.setLayoutManager(layoutManager);
        notifications = new ArrayList<>();
        mNotificationAttention.setAdapter(new NotificationAdapter(getContext(), R.layout.layout_item_notification, notifications));

        ic_back = getView().findViewById(R.id.ic_back);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }
}

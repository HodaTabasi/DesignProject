package com.example.maryam.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.R;

import me.anwarshahriar.calligrapher.Calligrapher;


public class AccountFragment extends Fragment {

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView tv_profile = view.findViewById(R.id.tv_profile);
        TextView tv_skills = view.findViewById(R.id.tv_skills);
        TextView tv_fav = view.findViewById(R.id.tv_fav);
        TextView tv_notification = view.findViewById(R.id.tv_notification);
        TextView tv_bankAccount = view.findViewById(R.id.tv_bankAccount);

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity, new ProfileFragment(),true);
            }
        });

        tv_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity, new SkillsFragment(),true);

            }
        });
        tv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity, new FavoriteFragment(),true);

            }
        });
        tv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity, new AccountNotificationFragment(),true);

            }
        });

        tv_bankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(),R.id.container_activity, new BankFragment(),true);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}

package com.example.maryam.sproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FrameLayout profile_frame_layout = view.findViewById(R.id.profile_frame_layout);
        TextView tv_portfolio = view.findViewById(R.id.tv_portfolio);
        TextView tv_budget = view.findViewById(R.id.tv_budget);
        TextView tv_addProject = view.findViewById(R.id.tv_addProject);
        TextView tv_proposals = view.findViewById(R.id.tv_proposals);
        TextView tv_about = view.findViewById(R.id.tv_about);
        TextView tv_search = view.findViewById(R.id.tv_search);


        profile_frame_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AccountFragment());

            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new PortfolioFragment());

            }
        });

        tv_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyAccountFragment());

            }
        });

        tv_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddProjectFragment());

            }
        });

        tv_proposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyOffersFragment());


            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }
}

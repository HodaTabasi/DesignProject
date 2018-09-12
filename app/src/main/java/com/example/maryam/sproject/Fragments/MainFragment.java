package com.example.maryam.sproject.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        CircleImageView img_user = view.findViewById(R.id.img_user);
        TextView tv_portfolio = view.findViewById(R.id.tv_portfolio_main);
        TextView tv_budget = view.findViewById(R.id.tv_budget);
        TextView tv_addProject = view.findViewById(R.id.tv_addProject);
        TextView tv_proposals = view.findViewById(R.id.tv_proposals);
        TextView tv_about = view.findViewById(R.id.tv_about);
        TextView tv_search = view.findViewById(R.id.tv_search);
        ImageView img_power = view.findViewById(R.id.img_power);
        ImageView img_notification = view.findViewById(R.id.img_notification);


        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://mustafa.smmim.com/waell/public/api/myprojects?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvb" +
                "S93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NzM5Njg2LC" +
                "JleHAiOjQ4MDgxNzYwNDU5MzI0NjQ0ODYsIm5iZiI6MTUzNjczOTY4NiwianRp" +
                "IjoiNFZqQlRaaGRDVFpXRGlmayJ9.CWnsMw0g" +
                "R4YBuz6IR5JiWYkcccgHanRNxlh8UWVSg2Q", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("tagg", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("tag", response.body().string());
            }
        });


        img_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AccountFragment(), true);

            }
        });

        tv_portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddNewWork2Fragment(), true);

            }
        });

        tv_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyAccountFragment(), true);

            }
        });

        tv_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddProjectFragment(), true);

            }
        });

        tv_proposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyOffersFragment(), true);


            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SameemFragment(), true);

            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SearchFragment(), true);

            }
        });

        img_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getActivity())
                        .setMessage("هل تريد الخروج من التطبيق؟").setCancelable(false)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("لا", null)
                        .show();

            }
        });

        img_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new NotificationFragment(), true);

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

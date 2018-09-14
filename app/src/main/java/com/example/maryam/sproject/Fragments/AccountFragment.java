package com.example.maryam.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.HelperClass.FragmentsUtil;
import com.example.maryam.sproject.Models.UserModel;
import com.example.maryam.sproject.MyRequest;
import com.example.maryam.sproject.OkHttpCallback;
import com.example.maryam.sproject.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


public class AccountFragment extends Fragment {
    UserModel userModel;
    MyRequest myRequest;

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
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new ProfileFragment(), true);
                getProfile();
            }

        });

        tv_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SkillsFragment(), true);

            }
        });
        tv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new FavoriteFragment(), true);

            }
        });
        tv_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AccountNotificationFragment(), true);

            }
        });

        tv_bankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new BankFragment(), true);

            }
        });

        return view;
    }

    private void getProfile() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        myRequest = new MyRequest();
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM2NzQzMDE4LCJleHAiOjQ4MDgxNzYwNDU5MzI0Njc4MTgsIm5iZiI6MTUzNjc0MzAxOCwianRpIjoiV0txVVNXbEpoTGJxWExjTSJ9.ZYuUC1GeUACff3noDtr_dY51LIAO5R5hrQ1s6VcFM7I");
        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/myprofile", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {

                JSONObject jsonObject = new JSONObject(response.body().string());
                Gson gson = new Gson();
                userModel = gson.fromJson(jsonObject.getString("user"), UserModel.class);
                Log.e("tag1", userModel.getBio() + " ");
            }
        });

    }
}

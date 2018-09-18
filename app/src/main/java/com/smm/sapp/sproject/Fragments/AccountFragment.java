package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.Models.UserModel;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
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

    TextView tv_profile;
    TextView tv_skills;
    TextView tv_fav;
    TextView tv_notification;
    TextView tv_bankAccount;
    TextView tv_name;
    TextView tv_title;
    RatingBar ratingBar;

    String name, title;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        init();
        onClickMethod();
        getProfileDataRequest();
    }

    private void init() {
        tv_profile = getView().findViewById(R.id.tv_profile);
        tv_skills = getView().findViewById(R.id.tv_skills);
        tv_fav = getView().findViewById(R.id.tv_fav);
        tv_notification = getView().findViewById(R.id.tv_notification);
        tv_bankAccount = getView().findViewById(R.id.tv_bankAccount);
        tv_name = getView().findViewById(R.id.name);
        tv_title = getView().findViewById(R.id.title);
        ratingBar = getView().findViewById(R.id.account_rate);

    }

    private void onClickMethod() {
        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", userModel.getType());
                bundle.putString("job_type", userModel.getJob_type());
                bundle.putString("busniess_type", userModel.getBusniess_type());
                bundle.putString("name", userModel.getName());
                bundle.putString("bio", userModel.getBio());
                bundle.putString("mobile", userModel.getPhone());
                bundle.putString("email", userModel.getEmail());
                bundle.putString("gender", userModel.getGender());
                bundle.putString("dob", userModel.getDob());

                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });

        tv_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SkillsFragment skillsFragment = new SkillsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("skillsInfo", userModel.getSkills());
                skillsFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, skillsFragment, true);


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
                BankFragment fragment = new BankFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("bankInfo", userModel.getBanks());
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });

    }

    private void getProfileDataRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjMsImlzcyI6Imh0dHA6Ly9tdXN0YWZhLnNtbWltLmNvbS93YWVsbC9wdWJsaWMvYXBpL0xvZ2luIiwiaWF0IjoxNTM3MDQzNjEzLCJleHAiOjQ4MDgxNzYwNDU5MzI3Njg0MTMsIm5iZiI6MTUzNzA0MzYxMywianRpIjoiNXdSR0t3RXZnVUhNNFRadyJ9.cA0Xkr3RaQjEFQK7e48DyLGWYrMVwKWkfvelnIs_aM8");
        myRequest.PostCall("http://mustafa.smmim.com/waell/public/api/myprofile", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                Gson gson = new Gson();
                userModel = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserModel.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_name.setText(userModel.getName());

                        if (userModel.getJob_type().equals("arch")) {
                            tv_title.setText("تصميم معماري");
                        } else if (userModel.getJob_type().equals("graphic")) {
                            tv_title.setText("تصميم جرافيكس");
                        } else if (userModel.getJob_type().equals("inter")) {
                            tv_title.setText("تصميم داخلي");
                        } else if (userModel.getJob_type().equals("moshen")) {
                            tv_title.setText("تصاميم موشن");
                        } else if (userModel.getJob_type().equals("wall")) {
                            tv_title.setText("الرسم الجداري");
                        }

                        Log.e("rate", userModel.getRate());

                        ratingBar.setRating(Float.valueOf(userModel.getRate()));
                    }
                });
            }
        });
    }
}

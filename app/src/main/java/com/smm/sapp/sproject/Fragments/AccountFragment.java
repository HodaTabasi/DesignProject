package com.smm.sapp.sproject.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
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
    LinearLayout linear_rate;

    String name, title;
    String s_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjIsImlzcyI6Imh0dHA6Ly9zbW0uc21taW0uY29tL3dhZWxsL3B1YmxpYy9hcGkvTG9naW4iLCJpYXQiOjE1Mzc2MTI1MzEsImV4cCI6NDgwODE3NjA0NTkzMzMzNzMzMSwibmJmIjoxNTM3NjEyNTMxLCJqdGkiOiJjYVZDSHRmUW9WOVhsalBwIn0.3f7a7F9sDyow1ZV90dec235qiXQNiUcKwU71LCMvF3k";

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
        linear_rate = getView().findViewById(R.id.linear_rate);
    }

    private void onClickMethod() {
        linear_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackFragment commentsFragment = new FeedbackFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("commentsInfo", userModel.getComments());
                commentsFragment.setArguments(bundle);
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, commentsFragment, true);
            }
        });

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
        stringMap.put("token", ConstantInterFace.USER.getToken());
        Log.e("12",ConstantInterFace.USER.getToken() + " ");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/myprofile", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyProgressDialog.dismissDialog();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();

                    }
                });

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

                        if (userModel.getType().equals("worker")) {
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
                            //ratingBar.setRating(Float.valueOf(userModel.getRate()));


                        } else if (userModel.getType().equals("client")) {

                            tv_name.setText(userModel.getName());
                            tv_title.setText("صاحب مشاريع");
                            ratingBar.setRating(Float.valueOf(userModel.getRate()));

                        }

                    }
                });
            }
        });
    }
}

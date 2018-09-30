package com.smm.sapp.sproject.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.Activities.RegistrationActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class MainFragment extends Fragment {
    CircleImageView img_user;
    TextView _name;
    TextView _specialization;
    TextView tv_portfolio;
    TextView tv_budget;
    TextView tv_addProject;
    TextView tv_proposals;
    TextView tv_about;
    TextView tv_search;
    ImageView img_power;
    ImageView img_notification;
    String refreshedToken;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    private void init(View view) {
        img_user = view.findViewById(R.id.img_user);
        _name = view.findViewById(R.id.tv_name);
        _specialization = view.findViewById(R.id.tv_specialization);
        tv_portfolio = view.findViewById(R.id.tv_portfolio_main);
        tv_budget = view.findViewById(R.id.tv_budget);
        tv_addProject = view.findViewById(R.id.tv_addProject);
        tv_proposals = view.findViewById(R.id.tv_proposals);
        tv_about = view.findViewById(R.id.tv_about);
        tv_search = view.findViewById(R.id.tv_search);
        img_power = view.findViewById(R.id.img_power);
        img_notification = view.findViewById(R.id.img_notification);
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
    }

    private void onClickMethod() {
        _name.setText(ConstantInterFace.USER.getName());
        Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(img_user);

        if (ConstantInterFace.USER.getType().equals("worker")) {
            if (ConstantInterFace.USER.getJob_type().equals("arch")) {
                _specialization.setText("مصمم معماري");
            } else if (ConstantInterFace.USER.getJob_type().equals("wall")) {
                _specialization.setText("مصمم جداري");
            } else if (ConstantInterFace.USER.getJob_type().equals("graphic")) {
                _specialization.setText("مصمم جرافكس");
            } else if (ConstantInterFace.USER.getJob_type().equals("inter")) {
                _specialization.setText("مصمم داخلي");
            } else if (ConstantInterFace.USER.getJob_type().equals("moshen")) {
                _specialization.setText("مصمم موشن");
            }
        } else {
            _specialization.setText("صاحب مشاريع");
        }

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
//                if (ConstantInterFace.USER.getType().equals("client"))
//                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddProjectFragment(), true);
//                else
//                    Toast.makeText(getContext(), "غير مخول لك بالدخول هذه الواجهة خاصة بصاحب المشاريع ", Toast.LENGTH_SHORT).show();
//
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new AddProjectFragment(), true);

            }
        });

        tv_proposals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("client"))
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new MyProjectFragment(), true);
                else{
                    MyOffersFragment fragment = new MyOffersFragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isUpdated",true);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
                }



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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        init(getView());
        //changeToken();
        //registered user
        if (!ConstantInterFace.IS_REGISTER) {
            onClickMethod();
        }
        //unregistered user
        else {

            _name.setText("");
            _specialization.setText("");

            Snackbar snackbar = Snackbar.make(getView(), "أنت غير مسجل في صمم!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("تسجيل", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });

            snackbar.show();

            TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
            TextView tv2 = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_action);
            tv.setTextSize(12f);
            tv2.setTextSize(12f);
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "JFFlatregular.ttf");
            tv.setTypeface(font);
            tv2.setTypeface(font);
        }

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, new SameemFragment(), true);

            }
        });

    }

    private void changeToken() {
        MyProgressDialog.showDialog(getActivity());
        MyRequest request = new MyRequest();
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("token",ConstantInterFace.USER.getToken());
        stringMap.put("fcm_token",refreshedToken);
//        Log.e("dd",refreshedToken);
        request.PostCall("http://smm.smmim.com/waell/public/api/changeuserfcm",stringMap , new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("fffd", "dsgsgew");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                Log.e("okHttpClient",""+s);
                JSONObject object = new JSONObject(s);
                JSONObject object1 = object.getJSONObject("status");
                Log.e("fffds", "dsgsgew");
                if (object1.getBoolean("success")){
                    Log.e("fff",object1.getString("message"));
                }else {
                    Log.e("fff",object1.getString("message") + "dsgsgew");
                }
            }
        });
    }
}

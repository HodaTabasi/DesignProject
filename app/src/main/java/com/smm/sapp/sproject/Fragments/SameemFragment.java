package com.smm.sapp.sproject.Fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Activities.RegistrationActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;

public class SameemFragment extends Fragment {

    ImageView ic_back;
    TextView tv_sammem_bio, about, tv_ques, tv_rights, rights, tv_conditions, conditions, tv_call_us, tv_send;
    LinearLayout linear_call;
    EditText subject, et_writehere;
    RecyclerView recyclerView;

    String s_about, s_condition, s_rights, s_ques;

    public SameemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sameem, container, false);


        tv_sammem_bio = view.findViewById(R.id.tv_sammem_bio);
        about = view.findViewById(R.id.about);

        tv_ques = view.findViewById(R.id.tv_ques);
        recyclerView = view.findViewById(R.id.ask_us);

        tv_rights = view.findViewById(R.id.tv_rights);
        rights = view.findViewById(R.id.rights);

        tv_conditions = view.findViewById(R.id.tv_conditions);
        conditions = view.findViewById(R.id.conditions);

        tv_call_us = view.findViewById(R.id.tv_call_us);
        linear_call = view.findViewById(R.id.linear_call);
        subject = view.findViewById(R.id.subject);
        et_writehere = view.findViewById(R.id.et_writehere);
        tv_send = view.findViewById(R.id.tv_send);

        setBottomBar();

        tv_sammem_bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ConstantInterFace.IS_ABOUT_OPENED) {
                    tv_sammem_bio.setBackgroundResource(R.drawable.blue_shape);
                    tv_sammem_bio.setTextColor(getResources().getColor(R.color.white));
                    about.setVisibility(View.VISIBLE);
                    about.setText(s_about);
                    ConstantInterFace.IS_ABOUT_OPENED = true;

                } else if (ConstantInterFace.IS_ABOUT_OPENED) {
                    tv_sammem_bio.setBackgroundResource(R.drawable.account_shape);
                    tv_sammem_bio.setTextColor(getResources().getColor(R.color.textGray));
                    about.setVisibility(View.GONE);
                    ConstantInterFace.IS_ABOUT_OPENED = false;

                }

            }
        });

        tv_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ConstantInterFace.IS_QUES_OPENED) {
                    tv_ques.setBackgroundResource(R.drawable.blue_shape);
                    tv_ques.setTextColor(getResources().getColor(R.color.white));
                    recyclerView.setVisibility(View.VISIBLE);
                    ConstantInterFace.IS_QUES_OPENED = true;

                } else if (ConstantInterFace.IS_QUES_OPENED) {
                    tv_ques.setBackgroundResource(R.drawable.account_shape);
                    tv_ques.setTextColor(getResources().getColor(R.color.textGray));
                    recyclerView.setVisibility(View.GONE);
                    ConstantInterFace.IS_QUES_OPENED = false;

                }
            }
        });

        tv_rights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ConstantInterFace.IS_RIGHTS_OPENED) {
                    tv_rights.setBackgroundResource(R.drawable.blue_shape);
                    tv_rights.setTextColor(getResources().getColor(R.color.white));
                    rights.setVisibility(View.VISIBLE);
                    ConstantInterFace.IS_RIGHTS_OPENED = true;
                    rights.setText(s_rights);

                } else if (ConstantInterFace.IS_RIGHTS_OPENED) {
                    tv_rights.setBackgroundResource(R.drawable.account_shape);
                    tv_rights.setTextColor(getResources().getColor(R.color.textGray));
                    rights.setVisibility(View.GONE);
                    ConstantInterFace.IS_RIGHTS_OPENED = false;

                }
            }
        });

        tv_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ConstantInterFace.IS_CONDITIONS_OPENED) {
                    tv_conditions.setBackgroundResource(R.drawable.blue_shape);
                    tv_conditions.setTextColor(getResources().getColor(R.color.white));
                    conditions.setVisibility(View.VISIBLE);
                    ConstantInterFace.IS_CONDITIONS_OPENED = true;
                    conditions.setText(s_condition);

                } else if (ConstantInterFace.IS_CONDITIONS_OPENED) {
                    tv_conditions.setBackgroundResource(R.drawable.account_shape);
                    tv_conditions.setTextColor(getResources().getColor(R.color.textGray));
                    conditions.setVisibility(View.GONE);
                    ConstantInterFace.IS_CONDITIONS_OPENED = false;

                }

            }
        });

        tv_call_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!ConstantInterFace.IS_CALLUS_OPENED) {
                    tv_call_us.setBackgroundResource(R.drawable.blue_shape);
                    tv_call_us.setTextColor(getResources().getColor(R.color.white));
                    linear_call.setVisibility(View.VISIBLE);
                    ConstantInterFace.IS_CALLUS_OPENED = true;
                    tv_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (!ConstantInterFace.IS_REGISTER) {
                                if (subject.getText().toString().matches("")
                                        || et_writehere.getText().toString().matches("")) {
                                    Toast.makeText(getContext(), "يجب تعبئة جميع الحقول", Toast.LENGTH_LONG).show();
                                } else {
                                    callUs();

                                }
                            } else {
                                Snackbar snackbar = Snackbar.make(getView(), "أنت غير مسجل في صمم!", Snackbar.LENGTH_LONG)
                                        .setAction("تسجيل", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                ConstantInterFace.IS_REGISTER = false;
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


                        }
                    });

                } else if (ConstantInterFace.IS_CALLUS_OPENED) {
                    tv_call_us.setBackgroundResource(R.drawable.account_shape);
                    tv_call_us.setTextColor(getResources().getColor(R.color.textGray));
                    linear_call.setVisibility(View.GONE);
                    ConstantInterFace.IS_CALLUS_OPENED = false;

                }
            }
        });


        return view;
    }

    private void setBottomBar() {
        ConstantInterFace.tv_home.setBackgroundResource(0);
        ConstantInterFace.tv_msgs.setBackgroundResource(0);
        ConstantInterFace.tv_profile.setBackgroundResource(0);
        ConstantInterFace.tv_projects.setBackgroundResource(0);
        ConstantInterFace.tv_portfolio.setBackgroundResource(0);

    }

    private void callUs() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("topic", subject.getText().toString());
        map.put("msg", et_writehere.getText().toString());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/contactus", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                JSONObject status = jsonObject.getJSONObject("status");
                if (status.getString("success").equals("true")) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            subject.setText("");
                            et_writehere.setText("");
                            Toast.makeText(getActivity(), "تم ارسال الطلب بنجاح", Toast.LENGTH_LONG).show();

                        }
                    });
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "لم يتم الارسال، تأكد من اتصالك بالانترنت", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);

        getData();

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }


    private void getData() {
        MyProgressDialog.showDialog(getContext());
        MyRequest myRequest = new MyRequest();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/sets", new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());

                final JSONObject aboutObject = jsonObject.getJSONObject("about");
                final String s1 = aboutObject.getString("value");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s_about = Html.fromHtml(s1).toString();
                        about.setText(s_about);

                    }
                });


                final JSONObject quesObject = jsonObject.getJSONObject("Questions");
                final String s2 = quesObject.getString("value");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        s_ques = Html.fromHtml(s2).toString();
//                        tv_ques.setText(s2);
                    }
                });

                final JSONObject rightsObject = jsonObject.getJSONObject("rights");
                final String s3 = rightsObject.getString("value");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s_rights = Html.fromHtml(s3).toString();
                        rights.setText(s3);


                    }
                });

                final JSONObject policyObject = jsonObject.getJSONObject("policy");
                final String s4 = policyObject.getString("value");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        s_condition = Html.fromHtml(s4).toString();
                        conditions.setText(s4);

                    }
                });
            }
        });
    }
}

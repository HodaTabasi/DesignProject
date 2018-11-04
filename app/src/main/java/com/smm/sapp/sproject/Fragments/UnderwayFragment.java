package com.smm.sapp.sproject.Fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.smm.sapp.sproject.Adapters.MyMessageDetailAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.MessageDetails;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnderwayFragment extends Fragment {


    /**
     * 29/08/2017
     */
    private TextView mProjectStartDate;
    /**
     * 1200 ريال سعودى
     */
    private TextView mProjectMoney;
    /**
     * 29/08/2017
     */
    private TextView mProjectEndDate;
    /**
     * 50 ايام
     */
    private TextView mProjectTime;
    private CircleImageView mProfileImage1;
    /**
     * حسام اليماني
     */
    private TextView mName;
    /**
     * مصمم
     */
    private TextView mItsType;
    private CircleImageView mProfileImage2;
    /**
     * حسام اليماني
     */
    private TextView mName1;
    /**
     * صاحب مشروع
     */
    private TextView mItsType1;
    private LinearLayout mWw;
    private RecyclerView mMres;
    private ImageView mOther;
    private EditText mMessageEx;
    private ImageView mSendMessg;
    private LinearLayout mFf;
    ImageView ic_back, ic_dots;
    User user;
    OfferModel model;

    private List<MessageDetails> details;
    MyMessageDetailAdapter adapter;
    PopupWindow mypopupWindow;

    public UnderwayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_underway, container, false);
    }

    private void initView() {
        mProjectStartDate = getView().findViewById(R.id.project_start_date);
        mProjectMoney = getView().findViewById(R.id.project_money);
        mProjectEndDate = getView().findViewById(R.id.project_end_date);
        mProjectTime = getView().findViewById(R.id.project_time);
        mProfileImage1 = getView().findViewById(R.id.profile_image1);
        mName = getView().findViewById(R.id.name);
        mItsType = getView().findViewById(R.id.its_type);
        mProfileImage2 = getView().findViewById(R.id.profile_image2);
        mName1 = getView().findViewById(R.id.name1);
        mItsType1 = getView().findViewById(R.id.its_type1);
        mMres = getView().findViewById(R.id.mres);
        mMres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mOther = getView().findViewById(R.id.other);
        mMessageEx = getView().findViewById(R.id.message_ex);
        mSendMessg = getView().findViewById(R.id.send_messg);
        ic_dots = getView().findViewById(R.id.ic_dots);

        details = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

//        if (ConstantInterFace.USER.getType().equals("worker")) {
//            ic_dots.setVisibility(View.GONE);
//        } else {
//            ic_dots.setVisibility(View.VISIBLE);
//        }

        ic_dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu(view);
            }
        });

        mSendMessg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNewMessageRequest();
            }
        });
        Bundle bundle = getArguments();
        user = bundle.getParcelable("user");
        model = bundle.getParcelable("offer");

        StringBuilder s_name = new StringBuilder(user.getName());
        for (int i = 1; i < s_name.length() - 1; i++) {
            s_name.setCharAt(i, '*');
        }
        mName.setText(s_name);
        Picasso.get().load(user.getPhoto_link()).into(mProfileImage1);

        StringBuilder s_name1 = new StringBuilder(ConstantInterFace.USER.getName());
        for (int i = 1; i < s_name1.length() - 1; i++) {
            s_name1.setCharAt(i, '*');
        }
        mName1.setText(s_name1);
        Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(mProfileImage2);

        mProjectMoney.setText(model.getBalance());
        mProjectTime.setText(model.getDur());
        String created_at = model.getCreated_at();
        String[] s = created_at.split(" ");
        mProjectStartDate.setText(s[0]);
        mProjectEndDate.setText("");

        getAConversationRequest();
    }


    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(View v) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.project_menu, null);

        TextView deliever_proj = view.findViewById(R.id.deliever_proj);
        TextView report = view.findViewById(R.id.report);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "JFFlatregular.ttf");
        deliever_proj.setTypeface(custom_font);
        report.setTypeface(custom_font);

        mypopupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mypopupWindow.showAsDropDown(v);

        deliever_proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                builder.setTitle("تسليم المشروع")
                        .setMessage("هل أنت متأكد من رغبتك بارسال طلب تسليم المشروع؟")
                        .setPositiveButton(R.string.deliever, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                delieverProjectRequest();
                            }
                        })
                        .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
                reportRequest();

            }
        });

    }

    private void reportRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getActivity());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("target_type", "offer");
        stringMap.put("target_id", model.getId() + "");
        stringMap.put("message", "هذا المحتوى غير ملائم للنشر في المشروع");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/report", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(getActivity(), "تم ارسال التبليغ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), " " + object.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void delieverProjectRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("offer_id", model.getId() + "");
        stringMap.put("project_id", model.getProject_id());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/finishanoffer", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                TypeToken<List<MessageDetails>> token = new TypeToken<List<MessageDetails>>() {
                                };
                                details = gson.fromJson(object.getJSONArray("msgs").toString(), token.getType());
                                adapter = new MyMessageDetailAdapter(getContext(), details);
                                mMres.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private void getAConversationRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("user_id", user.getId() + "");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/getAconversation", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject object1 = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                TypeToken<List<MessageDetails>> token = new TypeToken<List<MessageDetails>>() {
                                };
                                details = gson.fromJson(object.getJSONArray("msgs").toString(), token.getType());
                                adapter = new MyMessageDetailAdapter(getContext(), details);
                                mMres.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void sendNewMessageRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("msg", mMessageEx.getText().toString());
        stringMap.put("seconed_id", user.getId() + "");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/sendmsg", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                String s = response.body().string();
                final JSONObject object = new JSONObject(s);
                final JSONObject object1 = object.getJSONObject("status");
                Log.e("ddf", s);
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (object1.getBoolean("success")) {
                                Gson gson = new Gson();
                                MessageDetails messageDetails = gson.fromJson(object.getJSONObject("msg").toString(), MessageDetails.class);
                                details.add(0, messageDetails);
                                adapter.notifyDataSetChanged();
                                mMessageEx.setText("");
                            } else {
                                Toast.makeText(getContext(), "لم يتم الارسال", Toast.LENGTH_SHORT).show();
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

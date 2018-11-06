package com.smm.sapp.sproject.Fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
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
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


    private TextView mProjectStartDate;
    private TextView mProjectMoney;
    private TextView mProjectEndDate;
    private TextView mProjectTime;
    private CircleImageView mProfileImage1;
    private TextView mName;
    private TextView mItsType;
    private CircleImageView mProfileImage2;
    private TextView mName1;
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

        if (ConstantInterFace.USER.getType().equals("client")) {
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
        } else {
            StringBuilder s_name = new StringBuilder(model.getProject().getUser().getName());
            for (int i = 1; i < s_name.length() - 1; i++) {
                s_name.setCharAt(i, '*');
            }
            mName.setText(s_name);
            Picasso.get().load(model.getProject().getUser().getPhoto_link()).into(mProfileImage2);

            StringBuilder s_name1 = new StringBuilder(ConstantInterFace.USER.getName());
            for (int i = 1; i < s_name1.length() - 1; i++) {
                s_name1.setCharAt(i, '*');
            }
            mName1.setText(s_name1);
            Picasso.get().load(ConstantInterFace.USER.getPhoto_link()).into(mProfileImage1);
        }


        mProjectMoney.setText(model.getBalance());
        mProjectTime.setText(model.getDur());
        String created_at = model.getCreated_at();
        String[] s = created_at.split(" ");
        mProjectStartDate.setText(s[0]);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(s[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, Integer.parseInt(model.getDur()));  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        String output = sdf1.format(c.getTime());

        mProjectEndDate.setText(output);

        getAConversationRequest();
    }


    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(View v) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.project_menu, null);

        TextView deliever_proj = view.findViewById(R.id.deliever_proj);
        TextView report = view.findViewById(R.id.report);
        TextView edit_offer = view.findViewById(R.id.edit_offer);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "JFFlatregular.ttf");
        deliever_proj.setTypeface(custom_font);
        report.setTypeface(custom_font);
        edit_offer.setTypeface(custom_font);

        if (ConstantInterFace.USER.getType().equals("client")) {
            edit_offer.setVisibility(View.GONE);
            deliever_proj.setText("استلام المشروع");
        } else {
            edit_offer.setVisibility(View.VISIBLE);
            deliever_proj.setText("تسليم المشروع");

        }

        mypopupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mypopupWindow.showAsDropDown(v);

        deliever_proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();

                if (ConstantInterFace.USER.getType().equals("client")) {
                    delieverClientProject();
//                    if (ConstantInterFace.DELEIVER_PROJECT == 1) {
////                        delieverClientProject();
//                    } else {
//                        Toast.makeText(getActivity(), "لم يتم تسليم المشروع من قبل المصمم", Toast.LENGTH_LONG).show();
//                    }

//                    if (ConstantInterFace.DELEIVER_CLIENT_PROJECT == 1) {
//                        Toast.makeText(getActivity(), "تم استلامك للمشروع سابقا", Toast.LENGTH_LONG).show();
//                    } else {
//                        delieverClientProject();
//                    }

                } else {
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


                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setTitle("الرجاء إدخال رابط المشروع");

                                    final EditText input = new EditText(getActivity());
                                    builder.setView(input);
                                    builder.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String s_projLink = input.getText().toString();
                                            delieverWorkerProject(s_projLink);
                                        }
                                    });
                                    builder.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.show();

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
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
                reportRequest();

            }
        });

        edit_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProposalFragment fragment = new EditProposalFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", true);
                bundle.putParcelable("offer", model);
                fragment.setArguments(bundle);
                mypopupWindow.dismiss();
                FragmentsUtil.replaceFragment(getActivity(), R.id.container_activity, fragment, true);
            }
        });

    }

    private void delieverWorkerProject(String s_projLink) {
        Log.e("eeee", s_projLink);
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getActivity());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("offer_id", model.getId() + "");
        stringMap.put("project_link", s_projLink);
        stringMap.put("final", "1");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/editanoffer", stringMap, new OkHttpCallback() {
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
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                ConstantInterFace.DELEIVER_PROJECT = 1;
                                Toast.makeText(getActivity(), "تم تسليم المشروع بنجاح", Toast.LENGTH_SHORT).show();
                            } else {
                                ConstantInterFace.DELEIVER_PROJECT = 0;
                                Log.e("eeeeee", object.getString("error"));
                                Toast.makeText(getActivity(), "لم يتم التسليم", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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

    private void delieverClientProject() {
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject statusObj = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (statusObj.getBoolean("success")) {

                                //ConstantInterFace.DELEIVER_CLIENT_PROJECT = 1;

                                Toast.makeText(getActivity(), "تم استلام المشروع، يمكنك تقييم العامل", Toast.LENGTH_LONG).show();
                                final Dialog rate_dialog = new Dialog(getContext());
                                rate_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                                rate_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                rate_dialog.setContentView(R.layout.feedback_dialog);

                                final EditText et_comment = rate_dialog.findViewById(R.id.et_comment);
                                final RatingBar rate1 = rate_dialog.findViewById(R.id.rate1);
                                final RatingBar rate2 = rate_dialog.findViewById(R.id.rate2);
                                final RatingBar rate3 = rate_dialog.findViewById(R.id.rate3);
                                final RatingBar rate4 = rate_dialog.findViewById(R.id.rate4);
                                final RatingBar rate5 = rate_dialog.findViewById(R.id.rate5);
                                TextView tv1 = rate_dialog.findViewById(R.id.tv1);
                                TextView tv2 = rate_dialog.findViewById(R.id.tv2);
                                TextView tv3 = rate_dialog.findViewById(R.id.tv3);
                                TextView tv4 = rate_dialog.findViewById(R.id.tv4);
                                TextView tv5 = rate_dialog.findViewById(R.id.tv5);
                                TextView tv6 = rate_dialog.findViewById(R.id.tv6);
                                TextView cancel_rate = rate_dialog.findViewById(R.id.cancel_rate);
                                TextView send_rate = rate_dialog.findViewById(R.id.send_rate);

                                Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "JFFlatregular.ttf");
                                et_comment.setTypeface(custom_font);
                                tv1.setTypeface(custom_font);
                                tv2.setTypeface(custom_font);
                                tv3.setTypeface(custom_font);
                                tv4.setTypeface(custom_font);
                                tv5.setTypeface(custom_font);
                                tv6.setTypeface(custom_font);
                                cancel_rate.setTypeface(custom_font);
                                send_rate.setTypeface(custom_font);

                                cancel_rate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        rate_dialog.dismiss();
                                    }
                                });

                                send_rate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final String s_comment = et_comment.getText().toString();
                                        final Float f_rate = rate1.getRating();
                                        final Float f_rate2 = rate2.getRating();
                                        final Float f_rate3 = rate3.getRating();
                                        final Float f_rate4 = rate4.getRating();
                                        final Float f_rate5 = rate5.getRating();
                                        rate_dialog.dismiss();
                                        sendRateRequest(s_comment, f_rate, f_rate2, f_rate3, f_rate4, f_rate5);
                                    }
                                });

                                rate_dialog.show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void sendRateRequest(String comment, Float rate, Float rate2, Float rate3, Float rate4, Float rate5) {
        Log.e("yyyy", comment + rate + rate2 + rate3 + rate4 + rate5 + "");
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("comment", comment);
        stringMap.put("rate", String.valueOf(rate));
        stringMap.put("rate2", String.valueOf(rate2));
        stringMap.put("rate3", String.valueOf(rate3));
        stringMap.put("rate4", String.valueOf(rate4));
        stringMap.put("rate5", String.valueOf(rate5));
        stringMap.put("target_id", user.getId() + "");
        stringMap.put("target_type", "user");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/comment", stringMap, new OkHttpCallback() {
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
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject statusObj = object.getJSONObject("status");

                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            if (statusObj.getBoolean("success")) {
                                Toast.makeText(getActivity(), "تم التقييم بنجاح", Toast.LENGTH_LONG).show();
                            } else {
                                Log.e("eeeeee", object.getString("error"));
                                Toast.makeText(getActivity(), "لم يتم ارسال التقييم", Toast.LENGTH_LONG).show();
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

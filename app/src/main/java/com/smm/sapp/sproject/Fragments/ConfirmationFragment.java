package com.smm.sapp.sproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigbangbutton.editcodeview.EditCodeView;
import com.google.gson.Gson;
import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.HelperClass.SharedPreferencesApp;
import com.smm.sapp.sproject.Models.User;
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


public class ConfirmationFragment extends Fragment {

    EditCodeView editCodeView;
    String phone;
    String verify;

    public ConfirmationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        editCodeView = (EditCodeView) view.findViewById(R.id.edit_code);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView resend = view.findViewById(R.id.resend);

        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhoneVerifyRequest();
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCodeView.clearCode();
                regetPhoneVerifyRequest();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        Bundle bundle = getArguments();
        verify = bundle.getString("verify");
        phone = bundle.getString("phone");
        editCodeView.setCode(verify);
    }

    private void getPhoneVerifyRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("phone", phone);
        stringMap.put("code", editCodeView.getCode());
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/loginwithsms", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
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
                String s = response.body().string();
                Log.e("okHttpClient", " " + s);
                final JSONObject jsonObject = new JSONObject(s);
                final JSONObject object = jsonObject.getJSONObject("status");
                final Gson gson = new Gson();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                ConstantInterFace.USER = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                                SharedPreferencesApp.getInstance(getContext()).saveObject(ConstantInterFace.USER);
                                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                                startActivity(intent);
                                getActivity().finish();

                            } else {
                                Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    private void regetPhoneVerifyRequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("phone", phone);
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/loginwithsms", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
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
                String s = response.body().string();
                Log.e("Ffd", s);
                final JSONObject jsonObject = new JSONObject(s);
                final JSONObject object = jsonObject.getJSONObject("status");
                final Gson gson = new Gson();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                User user = gson.fromJson(jsonObject.getJSONObject("user").toString(), User.class);
                                editCodeView.setCode(user.getVerify());
                            } else {
                                Toast.makeText(getActivity(), "لم يتم الارسال بشكل صحيح", Toast.LENGTH_SHORT).show();
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

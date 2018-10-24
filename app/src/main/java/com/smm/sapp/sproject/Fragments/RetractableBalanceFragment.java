package com.smm.sapp.sproject.Fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class RetractableBalanceFragment extends Fragment {


    private View view;
    /** 0.00 */
    private TextView mRetractableRetBalance;
    /** إرسال إلى حسابك البنكي */
    private TextView mSendRetractableRetBalance;

    EditText et_uName,et_bNumber,et_bName,et_balance;
    ImageView ic_back;

    public RetractableBalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_retractable_balance, container, false);
    }

    private void initView() {
        mRetractableRetBalance = getView().findViewById(R.id.retractable_ret_balance);
        mSendRetractableRetBalance = getView().findViewById(R.id.send_retractable_ret_balance);
    }

    private void getRetractableRetBalance() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/mycredit", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
//                Log.e("tagr", response.body().string());
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject statusObject = object.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (statusObject.getBoolean("success")) {
                                mRetractableRetBalance.setText(object.getString("drawable"));
//                                Toast.makeText(getContext(), " " + statusObject.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), " " + statusObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        getRetractableRetBalance();

        mSendRetractableRetBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.fragment_withdraw_balance);
                 et_uName =  dialog.findViewById(R.id.user_name_w);
                 et_bNumber =  dialog.findViewById(R.id.bank_number_w);
                 et_bName = dialog.findViewById(R.id.bank_name_w);
                 et_balance = dialog.findViewById(R.id.balance_w);
                TextView cancel = dialog.findViewById(R.id.send_bank1);
                TextView send  = dialog.findViewById(R.id.cancl_bank1);

                et_balance.setText(mRetractableRetBalance.getText().toString());
//                et_uName.setText(ConstantInterFace.USER.getName());

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendWithDraowBalancerequest();
                    }
                });
                dialog.show();
            }
        });

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void sendWithDraowBalancerequest() {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("userbank_id",et_bNumber.getText().toString());
        stringMap.put("total", et_balance.getText().toString());

        myRequest.PostCall("http://smm.smmim.com/waell/public/api/withdrawmycredit", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
                Log.e("tag", e.getMessage());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
//                Log.e("tagr", response.body().string());
                final JSONObject object = new JSONObject(response.body().string());
                final JSONObject statusObject = object.getJSONObject("status");
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            if (statusObject.getBoolean("success")) {
                                MyProgressDialog.DoneDialog(getContext()," الى حسابك  " ,  "  تم ارسال " + et_balance.getText().toString() );

                            } else {
                                Toast.makeText(getContext(), " " + statusObject.getString("error"), Toast.LENGTH_SHORT).show();
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

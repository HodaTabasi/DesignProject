package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class CurrentAccountFragment extends Fragment {

    /**
     * 0.00
     */
    private TextView mHangingCuBalance;
    /**
     * 0.00
     */
    private TextView mTotalCuBalance;
    /**
     * 0.00
     */
    private TextView mRetractableCuBalance;
    /**
     * 0.00
     */
    private TextView mAvailableCuBalance;

    ImageView ic_back;

    public CurrentAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_account, container, false);
    }

    private void initView() {
        mHangingCuBalance = getView().findViewById(R.id.hanging_cu_balance);
        mTotalCuBalance = getView().findViewById(R.id.total_cu_balance);
        mRetractableCuBalance = getView().findViewById(R.id.retractable_cu_balance);
        mAvailableCuBalance = getView().findViewById(R.id.available_cu_balance);
        ic_back = getView().findViewById(R.id.ic_back);

    }

    private void addCurrentAccountRequest() {
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
                                mHangingCuBalance.setText(object.getString("hanged"));
                                mTotalCuBalance.setText(object.getString("all"));
                                mRetractableCuBalance.setText(object.getString("drawable"));
                                mAvailableCuBalance.setText(object.getString("avaliable"));
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
        addCurrentAccountRequest();

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }


}

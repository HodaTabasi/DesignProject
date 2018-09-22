package com.smm.sapp.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Adapters.FinancialMovementReportAdapter;
import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.FinancialMovementReports;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment implements View.OnClickListener {

    /**
     * المدفوعات
     */
    private TextView mPayments;
    /**
     * الايداعات
     */
    private TextView mRevenue;
    /**
     * كل المعاملات
     */
    private TextView mAllTransactions;
    private RecyclerView mReportRes;
    LinearLayoutManager layoutManager;
    List<FinancialMovementReports> reports;
    FinancialMovementReportAdapter adapter;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    private void initView() {
        mPayments = getView().findViewById(R.id.payments);
        mRevenue = getView().findViewById(R.id.revenue);
        mAllTransactions = getView().findViewById(R.id.all_transactions);

        mPayments.setOnClickListener(this);
        mRevenue.setOnClickListener(this);
        mAllTransactions.setOnClickListener(this);

        mReportRes = getView().findViewById(R.id.report_res);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mReportRes.setLayoutManager(layoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
        addNewBankRequest("all");
    }

    private void addNewBankRequest(String type) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        String token = ConstantInterFace.USER.getToken();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/myreports" + "?token=" + token + "&type=" + type, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final Gson gson = new Gson();
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            JSONObject object = jsonObject.getJSONObject("status");
                            if (object.getBoolean("success")) {
                                reports = gson.fromJson(jsonObject.getJSONArray("reports").toString(), new TypeToken<List<FinancialMovementReports>>() {
                                }.getType());
                                adapter = new FinancialMovementReportAdapter(getContext(), R.layout.layout_item_report, reports);
                                mReportRes.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "" + object.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyProgressDialog.dismissDialog();


                    }
                });


            }
        });
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        reports.clear();
        adapter.notifyDataSetChanged();
        switch (id) {
            case R.id.payments:
                mAllTransactions.setBackgroundResource(R.drawable.gray_shap);
                mPayments.setBackgroundResource(R.drawable.dark_blue_shap);
                mRevenue.setBackgroundResource(R.drawable.gray_shap);
                addNewBankRequest("payment");
                break;
            case R.id.revenue:
                mAllTransactions.setBackgroundResource(R.drawable.gray_shap);
                mPayments.setBackgroundResource(R.drawable.gray_shap);
                mRevenue.setBackgroundResource(R.drawable.dark_blue_shap);
                addNewBankRequest("charge");
                break;
            case R.id.all_transactions:
                mAllTransactions.setBackgroundResource(R.drawable.dark_blue_shap);
                mPayments.setBackgroundResource(R.drawable.gray_shap);
                mRevenue.setBackgroundResource(R.drawable.gray_shap);
                addNewBankRequest("all");
                break;
        }
    }
}

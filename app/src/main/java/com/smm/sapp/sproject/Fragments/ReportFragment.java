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
import android.widget.ImageView;
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

    private TextView mPayments;
    private TextView mRevenue;
    private TextView mAllTransactions;
    private RecyclerView mReportRes;
    LinearLayoutManager layoutManager;
    List<FinancialMovementReports> reports;
    FinancialMovementReportAdapter adapter;
    ImageView ic_back;
    int current_page, total_pages, flag;
    private TextView tv_next, tv_back;


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
        tv_next = getView().findViewById(R.id.tv_next);
        tv_back = getView().findViewById(R.id.tv_back);

        mPayments.setOnClickListener(this);
        mRevenue.setOnClickListener(this);
        mAllTransactions.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);


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
        addNewBankRequest("all", 1);

        ic_back = getView().findViewById(R.id.ic_back);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

    }

    private void addNewBankRequest(String type, int current) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(getContext());
        String token = ConstantInterFace.USER.getToken();
        myRequest.GetCall("http://smm.smmim.com/waell/public/api/myreports" + "?token=" + token + "&type=" + type + "&i_current_page=" + current, new OkHttpCallback() {
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
                final JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject paginationObj = jsonObject.getJSONObject("pagination");
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

                                current_page = Integer.valueOf(paginationObj.getString("i_current_page"));
                                total_pages = Integer.valueOf(paginationObj.getString("i_total_pages"));

                                if (total_pages > current_page && current_page != 1) {
                                    //two are visible
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages == current_page && current_page != 1) {
                                    //back visible, next gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.VISIBLE);
                                } else if (total_pages > current_page && current_page == 1) {
                                    //next visible, back gone
                                    tv_next.setVisibility(View.VISIBLE);
                                    tv_back.setVisibility(View.GONE);
                                } else if (total_pages == 1 || total_pages == 0) {
                                    //two are gone
                                    tv_next.setVisibility(View.GONE);
                                    tv_back.setVisibility(View.GONE);
                                }

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
                flag = 1;
                mAllTransactions.setBackgroundResource(R.drawable.gray_shap);
                mPayments.setBackgroundResource(R.drawable.dark_blue_shap);
                mRevenue.setBackgroundResource(R.drawable.gray_shap);
                addNewBankRequest("payment", 1);
                break;
            case R.id.revenue:
                flag = 2;
                mAllTransactions.setBackgroundResource(R.drawable.gray_shap);
                mPayments.setBackgroundResource(R.drawable.gray_shap);
                mRevenue.setBackgroundResource(R.drawable.dark_blue_shap);
                addNewBankRequest("charge", 1);
                break;
            case R.id.all_transactions:
                flag = 3;
                mAllTransactions.setBackgroundResource(R.drawable.dark_blue_shap);
                mPayments.setBackgroundResource(R.drawable.gray_shap);
                mRevenue.setBackgroundResource(R.drawable.gray_shap);
                addNewBankRequest("all", 1);
                break;
            case R.id.tv_next:
                current_page++;
                if (flag == 1) {
                    addNewBankRequest("payment", current_page);
                } else if (flag == 2) {
                    addNewBankRequest("charge", current_page);
                } else if (flag == 3) {
                    addNewBankRequest("all", current_page);
                }
                break;

            case R.id.tv_back:
                current_page--;
                if (flag == 1) {
                    addNewBankRequest("payment", current_page);
                } else if (flag == 2) {
                    addNewBankRequest("charge", current_page);
                } else if (flag == 3) {
                    addNewBankRequest("all", current_page);
                }
                break;
        }
    }
}

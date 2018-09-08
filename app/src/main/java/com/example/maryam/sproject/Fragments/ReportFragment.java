package com.example.maryam.sproject.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maryam.sproject.Adapters.FinancialMovementReportAdapter;
import com.example.maryam.sproject.Models.FinancialMovementReports;
import com.example.maryam.sproject.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


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
        mReportRes = getView().findViewById(R.id.report_res);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mReportRes.setLayoutManager(layoutManager);
        reports = new ArrayList<>();
        mReportRes.setAdapter(new FinancialMovementReportAdapter(getContext(),R.layout.fav_row,reports));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
        initView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.payments:
                Toast.makeText(getContext(), "payments", Toast.LENGTH_SHORT).show();
                break;
            case R.id.revenue:
                Toast.makeText(getContext(), "revenue", Toast.LENGTH_SHORT).show();
                break;
            case R.id.all_transactions:
                Toast.makeText(getContext(), "all_transactions", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

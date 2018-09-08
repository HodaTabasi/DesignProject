package com.example.maryam.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Models.FinancialMovementReports;
import com.example.maryam.sproject.R;

import java.util.List;

public class FinancialMovementReportAdapter extends RecyclerView.Adapter<FinancialMovementReportAdapter.FinancialMovementReportVH> {

    Context context;
    int layout;
    List<FinancialMovementReports> reports;

    public FinancialMovementReportAdapter(Context context, int layout, List<FinancialMovementReports> reports) {
        this.context = context;
        this.layout = layout;
        this.reports = reports;
    }

    @NonNull
    @Override
    public FinancialMovementReportVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new FinancialMovementReportVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialMovementReportVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class FinancialMovementReportVH extends RecyclerView.ViewHolder{
        TextView operationNumber,operationValue,operationDesc,operationDate;
        public FinancialMovementReportVH(View itemView) {
            super(itemView);

            operationNumber = itemView.findViewById(R.id.operation_number);
            operationValue = itemView.findViewById(R.id.operation_value);
            operationDesc = itemView.findViewById(R.id.operation_desc);
            operationDate = itemView.findViewById(R.id.operation_date);
        }
    }
}

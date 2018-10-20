package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.Models.FinancialMovementReports;
import com.smm.sapp.sproject.R;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new FinancialMovementReportVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FinancialMovementReportVH holder, int position) {
        if (reports.size() == 0) {
            Toast.makeText(context, "لا يوجد بيانات لعرضها", Toast.LENGTH_SHORT).show();
        } else {
            String[] separated = reports.get(position).getCreated_at().split(" ");
            holder.operationDate.setText(separated[0]);
            holder.operationDesc.setText(reports.get(position).getDescr());
            holder.operationValue.setText(reports.get(position).getTotal());
            holder.operationNumber.setText(reports.get(position).getId() + "");
        }
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class FinancialMovementReportVH extends RecyclerView.ViewHolder {
        TextView operationNumber, operationValue, operationDesc, operationDate, tv_date, tv_details, tv_value, tv_opNO;

        public FinancialMovementReportVH(View itemView) {
            super(itemView);

            operationNumber = itemView.findViewById(R.id.operation_number);
            operationValue = itemView.findViewById(R.id.operation_value);
            operationDesc = itemView.findViewById(R.id.operation_desc);
            operationDate = itemView.findViewById(R.id.operation_date);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_details = itemView.findViewById(R.id.tv_details);
            tv_value = itemView.findViewById(R.id.tv_value);
            tv_opNO = itemView.findViewById(R.id.tv_opNO);


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            operationNumber.setTypeface(custom_font);
            operationValue.setTypeface(custom_font);
            operationDesc.setTypeface(custom_font);
            operationDate.setTypeface(custom_font);
            tv_date.setTypeface(custom_font);
            tv_details.setTypeface(custom_font);
            tv_value.setTypeface(custom_font);
            tv_opNO.setTypeface(custom_font);

        }
    }
}

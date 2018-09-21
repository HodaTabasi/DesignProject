package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.MyProjectsProposals;
import com.smm.sapp.sproject.R;

import java.util.List;

public class MyProjectsProposalsAdapter extends RecyclerView.Adapter<MyProjectsProposalsAdapter.MyProjectsProposalVH> {

    Context context;
    int layout;
    List<MyProjectsProposals> proposals;

    public MyProjectsProposalsAdapter(Context context, int layout, List<MyProjectsProposals> proposals) {
        this.context = context;
        this.layout = layout;
        this.proposals = proposals;
    }

    @NonNull
    @Override
    public MyProjectsProposalVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MyProjectsProposalVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProjectsProposalVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    public class MyProjectsProposalVH extends RecyclerView.ViewHolder {
        TextView tvName,tvMoney,tvCalender,tv1,tv2;
        public MyProjectsProposalVH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvMoney = itemView.findViewById(R.id.tv_money);
            tvCalender = itemView.findViewById(R.id.tv_calender);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
        }
    }
}

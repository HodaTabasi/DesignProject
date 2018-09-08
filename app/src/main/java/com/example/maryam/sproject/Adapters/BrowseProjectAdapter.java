package com.example.maryam.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maryam.sproject.Models.BankModel;
import com.example.maryam.sproject.Models.BrowseProjectsModel;
import com.example.maryam.sproject.R;

import java.util.List;

public class BrowseProjectAdapter extends RecyclerView.Adapter<BrowseProjectAdapter.BrowseProjectHolder> {

    private Context context;
    private List<BrowseProjectsModel> projectsList;

    public BrowseProjectAdapter(Context context, List<BrowseProjectsModel> projectsList) {
        this.context = context;
        this.projectsList = projectsList;
    }

    @NonNull
    @Override
    public BrowseProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_row, parent, false);
        return new BrowseProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseProjectHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public class BrowseProjectHolder extends RecyclerView.ViewHolder {

        TextView tv_description, tv_proposals, tv_time, tv_name;
        ImageView img_arrow, img_setting;


        public BrowseProjectHolder(View itemView) {
            super(itemView);

            tv_description = itemView.findViewById(R.id.tv_desc);
            tv_proposals = itemView.findViewById(R.id.tv_proposals);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_arrow = itemView.findViewById(R.id.img_arrow);
            img_setting = itemView.findViewById(R.id.img_setting);

        }
    }
}

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.R;

import java.util.List;

public class BrowseProjectAdapter extends RecyclerView.Adapter<BrowseProjectAdapter.BrowseProjectHolder> {

    private Context context;
    private List<ProjectsModels> projectsList;

    public BrowseProjectAdapter(Context context, List<ProjectsModels> projectsList) {
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
    public void onBindViewHolder(@NonNull final BrowseProjectHolder holder, final int position) {
        holder.tv_description.setText(projectsList.get(position).getName());
        holder.tv_name.setText(projectsList.get(position).getUser().getName());
        holder.tv_proposals.setText(projectsList.get(position).getOffers().size() + " ");
//        holder.tv_time.setText();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewProjectFragment fragment = new ViewProjectFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("theProject",projectsList.get(position));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context,R.id.container_activity,fragment);
            }
        });
        holder.img_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(holder.img_arrow);
            }
        });
        holder.img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(holder.img_setting);
            }
        });
    }

    private void showPopUpMenu(ImageView img) {
        PopupMenu popup = new PopupMenu(context,img);

        popup.inflate(R.menu.custom_menu);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.new_one:
                        FragmentsUtil.replaceFragment((FragmentActivity) context,R.id.container_activity,new AddProjectFragment());
                        return true;
                    case R.id.new_fav:

                        return true;
                    case R.id.report:
                        return true;
                }
                return false;
            }
        });

        popup.show();
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

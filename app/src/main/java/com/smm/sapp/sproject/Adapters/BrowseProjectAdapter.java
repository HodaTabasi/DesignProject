package com.smm.sapp.sproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.R;

import java.util.List;

public class BrowseProjectAdapter extends RecyclerView.Adapter<BrowseProjectAdapter.BrowseProjectHolder> {

    private Context context;
    private List<ProjectsModels> projectsList;
    PopupWindow mypopupWindow;

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
        holder.tv_proposals.setText(projectsList.get(position).getOffers().size() + " عرض ");
//        holder.tv_time.setText();

        if (!ConstantInterFace.IS_REGISTER) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewProjectFragment fragment = new ViewProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("theProject", projectsList.get(position));
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment,true);
                }
            });
            holder.linear_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopUpMenu(v);
                }
            });
        }

        //to test if we in the last item in list
        if (position == projectsList.size() - 1) {
            holder.view.setVisibility(View.GONE);

        }



    }

    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(View v) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_menu, null);

        TextView new_project = view.findViewById(R.id.new_project);
        TextView add_fav = view.findViewById(R.id.add_fav);
        TextView report = view.findViewById(R.id.report);

        mypopupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mypopupWindow.showAsDropDown(v);

        new_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AddProjectFragment(), true);
                ConstantInterFace.tv_home.setBackgroundResource(0);
                ConstantInterFace.tv_msgs.setBackgroundResource(0);
                ConstantInterFace.tv_profile.setBackgroundResource(0);
                ConstantInterFace.tv_projects.setBackgroundResource(0);
                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
            }
        });

        add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
//                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AddProjectFragment(), true);
//                ConstantInterFace.tv_home.setBackgroundResource(0);
//                ConstantInterFace.tv_msgs.setBackgroundResource(0);
//                ConstantInterFace.tv_profile.setBackgroundResource(0);
//                ConstantInterFace.tv_projects.setBackgroundResource(0);
//                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
//                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AddProjectFragment(), true);
//                ConstantInterFace.tv_home.setBackgroundResource(0);
//                ConstantInterFace.tv_msgs.setBackgroundResource(0);
//                ConstantInterFace.tv_profile.setBackgroundResource(0);
//                ConstantInterFace.tv_projects.setBackgroundResource(0);
//                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public class BrowseProjectHolder extends RecyclerView.ViewHolder {

        TextView tv_description, tv_proposals, tv_time, tv_name;
        ImageView img_arrow, img_setting;
        LinearLayout linear_setting;
        View view;


        public BrowseProjectHolder(View itemView) {
            super(itemView);

            tv_description = itemView.findViewById(R.id.tv_desc);
            tv_proposals = itemView.findViewById(R.id.tv_proposals);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_arrow = itemView.findViewById(R.id.img_arrow);
            img_setting = itemView.findViewById(R.id.img_setting);
            linear_setting = itemView.findViewById(R.id.linear_setting);
            view = itemView.findViewById(R.id.projects_view);

        }
    }
}

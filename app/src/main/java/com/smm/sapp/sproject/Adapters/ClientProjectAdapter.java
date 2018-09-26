package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.MyOffersFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.R;

import java.util.List;

public class ClientProjectAdapter extends RecyclerView.Adapter<ClientProjectAdapter.ClientProjectHolder> {

    private Context context;
    private List<ProjectsModels> projectsList;

    public ClientProjectAdapter(Context context, List<ProjectsModels> projectsList) {
        this.context = context;
        this.projectsList = projectsList;
    }

    @NonNull
    @Override
    public ClientProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_row, parent, false);
        return new ClientProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClientProjectHolder holder, final int position) {
        final ProjectsModels projectsModels = projectsList.get(position);
        holder.body.setText(projectsModels.getName());
        holder.calender.setText(projectsModels.getCreated_at());
        holder.name.setText(projectsModels.getUser().getName());
        holder.money.setText(projectsModels.getBalance());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOffersFragment fragment = new MyOffersFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("object",projectsModels);
                bundle.putBoolean("flag",false);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context,R.id.container_activity,fragment,true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public class ClientProjectHolder extends RecyclerView.ViewHolder {
        TextView calender, name, money, body;

        public ClientProjectHolder(View itemView) {
            super(itemView);

            calender = itemView.findViewById(R.id.tv_calender);
            name = itemView.findViewById(R.id.tv_name);
            money = itemView.findViewById(R.id.tv_money);
            body = itemView.findViewById(R.id.tv2);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            calender.setTypeface(custom_font);
            name.setTypeface(custom_font);
            money.setTypeface(custom_font);
            body.setTypeface(custom_font);
        }
    }
}
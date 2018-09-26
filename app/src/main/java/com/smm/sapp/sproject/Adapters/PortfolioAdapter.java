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
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.Models.PortfolioModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.PortfolioHolder> {

    Context context;
    List<PortfolioModel> list;

    public PortfolioAdapter(Context context, List<PortfolioModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PortfolioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav2_row, parent, false);
        return new PortfolioAdapter.PortfolioHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioHolder holder, final int position) {

        holder.tv_name.setText(list.get(position).getUser().getName());
        holder.tv_show.setText(list.get(position).getViews());
        holder.tv_like.setText(list.get(position).getLikes());
        holder.tv_specialization.setText(list.get(position).getUser().getJob_type());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioDescFragment fragment = new PortfolioDescFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(list.get(position).getId()));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PortfolioHolder extends RecyclerView.ViewHolder {


        ImageView img, fav;
        TextView tv_name, tv_like, tv_show, tv_specialization, tv_addProject;

        public PortfolioHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            fav = itemView.findViewById(R.id.tv_fav);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_specialization = itemView.findViewById(R.id.tv_specialization);
            tv_addProject = itemView.findViewById(R.id.tv_addProject);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            tv_name.setTypeface(custom_font);
            tv_like.setTypeface(custom_font);
            tv_show.setTypeface(custom_font);
            tv_specialization.setTypeface(custom_font);
            tv_addProject.setTypeface(custom_font);
        }
    }
}

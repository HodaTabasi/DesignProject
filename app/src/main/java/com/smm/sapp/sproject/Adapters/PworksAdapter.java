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

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PworksAdapter extends RecyclerView.Adapter<PworksAdapter.PworksHolder> {

    Context context;
    List<PWorks> list;
    String name;
    String projectType;


    public PworksAdapter(Context context, List<PWorks> pWorks, String name) {
        this.context = context;
        this.list = pWorks;
        this.name = name;
    }

    @NonNull
    @Override
    public PworksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav2_row, parent, false);
        return new PworksAdapter.PworksHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PworksHolder holder, final int position) {

        if (name != null) {
            StringBuilder s_name = new StringBuilder(name);
            for (int i = 1; i < s_name.length() - 1; i++) {
                s_name.setCharAt(i, '*');
            }
            holder.tv_name.setText(s_name);
        } else {
//            StringBuilder s_name = new StringBuilder(list.get(position).getUser().getName());
//            for (int i = 1; i < s_name.length() - 1; i++) {
//                s_name.setCharAt(i, '*');
//            }
//            holder.tv_name.setText(s_name);
        }

        holder.tv_show.setText(list.get(position).getViews());
        holder.tv_like.setText(String.valueOf(list.get(position).getLikes()));
        if (list.get(position).getType().equals("wall")) {
            holder.tv_specialization.setText("تصميم جداري");
        } else if (list.get(position).getType().equals("arch")) {
            holder.tv_specialization.setText("تصميم معماري");
        } else if (list.get(position).getType().equals("graphic")) {
            holder.tv_specialization.setText("تصميم جرافيكس");
        } else if (list.get(position).getType().equals("inter")) {
            holder.tv_specialization.setText("تصميم داخلي");
        } else if (list.get(position).getType().equals("moshen")) {
            holder.tv_specialization.setText("تصميم موشن");
        }
        Picasso.get().load(list.get(position).getPhoto_link()).into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioDescFragment fragment = new PortfolioDescFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", true);
                bundle.putParcelable("pworklist", list.get(position));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
            }
        });


        holder.tv_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                projectType = (String) list.get(position).getType();
                AddProjectFragment fragment = new AddProjectFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", projectType);
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PworksHolder extends RecyclerView.ViewHolder {

        ImageView img, fav;
        TextView tv_name, tv_like, tv_show, tv_specialization, tv_addProject;

        public PworksHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            fav = itemView.findViewById(R.id.tv_fav);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_specialization = itemView.findViewById(R.id.tv_specialization);
            tv_addProject = itemView.findViewById(R.id.tv_addProject);

            fav.setVisibility(View.GONE);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            tv_name.setTypeface(custom_font);
            tv_like.setTypeface(custom_font);
            tv_show.setTypeface(custom_font);
            tv_specialization.setTypeface(custom_font);
            tv_addProject.setTypeface(custom_font);
        }
    }
}

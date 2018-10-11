package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AddNewWork2Fragment;
import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LikesPWorkAdapter extends RecyclerView.Adapter<LikesPWorkAdapter.LikesPWorkVH> {
    Context context;
    int Layout;
    List<Likes> PworksLikes;
    String projectType;


    public LikesPWorkAdapter(Context context, int layout, List<Likes> pworksLikes) {
        this.context = context;
        Layout = layout;
        PworksLikes = pworksLikes;
    }

    @NonNull
    @Override
    public LikesPWorkVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Layout, parent, false);
        return new LikesPWorkVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesPWorkVH holder, final int position) {
        Log.e("gfrvd","d ck[rg " + PworksLikes.size());
            final Likes likes = PworksLikes.get(position);

//            holder.like.setText(likes.getpWork().getLikes());
            holder.show.setText(likes.getpWork().getViews());
            holder.name.setText(likes.getpWork().getName());
            Picasso.get().load(likes.getpWork().getPhoto_link()).into(holder.view);

            if (likes.getpWork().getType().equals("inter")) {
                holder.specialization.setText("مصمم داخلي");
            } else if (likes.getpWork().getType().equals("arch")) {
                holder.specialization.setText("مصمم معماري");
            } else if (likes.getpWork().getType().equals("wall")) {
                holder.specialization.setText("مصمم جداري");
            } else if (likes.getpWork().getType().equals("moshen")) {
                holder.specialization.setText("مصمم موشن");
            } else if (likes.getpWork().getType().equals("graphic")) {
                holder.specialization.setText("مصمم جرافيكس");
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
                }
            });




            holder.addProject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ConstantInterFace.USER.getType().equals("worker")) {
                        AddNewWork2Fragment fragment = new AddNewWork2Fragment();
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("flag", false);
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                    } else {
                        projectType = likes.getpWork().getType();
                        AddProjectFragment fragment = new AddProjectFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("type", projectType);
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, false);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return PworksLikes.size();
    }

    public class LikesPWorkVH extends RecyclerView.ViewHolder {
        ImageView view, fav;
        TextView show, like, specialization, addProject, name;

        public LikesPWorkVH(View itemView) {
            super(itemView);

            show = itemView.findViewById(R.id.tv_show);
            name = itemView.findViewById(R.id.tv_name);
            like = itemView.findViewById(R.id.tv_like);
            specialization = itemView.findViewById(R.id.tv_specialization);
            addProject = itemView.findViewById(R.id.tv_addProject);
            fav = itemView.findViewById(R.id.tv_fav);
            view = itemView.findViewById(R.id.img);

            fav.setVisibility(View.GONE);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            show.setTypeface(custom_font);
            name.setTypeface(custom_font);
            like.setTypeface(custom_font);
            specialization.setTypeface(custom_font);
            addProject.setTypeface(custom_font);
        }
    }
}

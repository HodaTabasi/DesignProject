package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.R;

import java.util.List;

public class LikesPWorkAdapter extends RecyclerView.Adapter<LikesPWorkAdapter.LikesPWorkVH> {
    Context context;
    int Layout;
    List<Likes> PworksLikes;

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
    public void onBindViewHolder(@NonNull LikesPWorkVH holder, int position) {
        Likes likes = PworksLikes.get(position);
        holder.like.setText(likes.getpWork().getLikes());
        holder.show.setText(likes.getpWork().getViews());
//        holder.specialization.setText(likes.getpWork());
        holder.name.setText(likes.getpWork().getName());

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return PworksLikes.size();
    }

    public class LikesPWorkVH extends RecyclerView.ViewHolder {
        ImageView view;
        TextView show, like, specialization, addProject, fav, name;

        public LikesPWorkVH(View itemView) {
            super(itemView);
            show = itemView.findViewById(R.id.tv_show);
            name = itemView.findViewById(R.id.tv_name);
            like = itemView.findViewById(R.id.tv_like);
            specialization = itemView.findViewById(R.id.tv_specialization);
            addProject = itemView.findViewById(R.id.tv_addProject);
            fav = itemView.findViewById(R.id.tv_fav);
            view = itemView.findViewById(R.id.img);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            show.setTypeface(custom_font);
            name.setTypeface(custom_font);
            like.setTypeface(custom_font);
            specialization.setTypeface(custom_font);
            addProject.setTypeface(custom_font);
            fav.setTypeface(custom_font);

        }
    }
}

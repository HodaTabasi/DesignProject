package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LikesDesignAdapter extends RecyclerView.Adapter<LikesDesignAdapter.LikesDesignHV> {

    Context context;
    int Layout;
    List<Likes> designLikes;

    public LikesDesignAdapter(Context context, int layout, List<Likes> designLikes) {
        this.context = context;
        Layout = layout;
        this.designLikes = designLikes;
    }

    @NonNull
    @Override
    public LikesDesignHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Layout, parent, false);
        return new LikesDesignHV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesDesignHV holder, int position) {
        Likes likes = designLikes.get(position);

        if (Layout == R.layout.fav2_row) {

            Log.e("qqqqq", "qqqqq");
            try {
                Picasso.get().load(likes.getpWork().getPhoto_link()).into(holder.img);
                holder.tv_name.setText(likes.getUser().getName());
                holder.tv_like.setText(likes.getpWork().getLikes());
                holder.tv_show.setText(likes.getpWork().getViews());

                if (likes.getpWork().getType().equals("inter")) {
                    holder.tv_specialization.setText("تصميم داخلي");
                } else if (likes.getpWork().getType().equals("arch")) {
                    holder.tv_specialization.setText("تصميم معماري");
                } else if (likes.getpWork().getType().equals("wall")) {
                    holder.tv_specialization.setText("تصميم جداري");
                } else if (likes.getpWork().getType().equals("moshen")) {
                    holder.tv_specialization.setText("تصميم موشن");
                } else if (likes.getpWork().getType().equals("graphic")) {
                    holder.tv_specialization.setText("تصميم جرافيكس");
                }

                holder.tv_addProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                holder.tv_fav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            } catch (Exception e) {

            }

        } else if (Layout == R.layout.fav_row) {
            Log.e("qqqqq", "qqqqq");
            try {
                holder.tv2.setText(likes.getProject().getName());
                //holder.tv_name.setText(likes.getProject().get);
                holder.tv_money.setText(likes.getProject().getBalance());
                //holder.tv_calender.setText(likes.getProject().getName());

            } catch (Exception e) {

            }
        } else if (Layout == R.layout.item_layout_profile) {
            try {
                Picasso.get().load(likes.getUser().getPhoto_link()).into(holder.imageView);
                holder.d_layout_name.setText(likes.getUser().getName());
                if (likes.getUser().getJob_type().equals("inter")) {
                    holder.d_layout_specialty.setText("مصمم داخلي");
                } else if (likes.getUser().getJob_type().equals("arch")) {
                    holder.d_layout_specialty.setText("مصمم معماري");
                } else if (likes.getUser().getJob_type().equals("wall")) {
                    holder.d_layout_specialty.setText("مصمم جداري");
                } else if (likes.getUser().getJob_type().equals("moshen")) {
                    holder.d_layout_specialty.setText("مصمم موشن");
                } else if (likes.getUser().getJob_type().equals("graphic")) {
                    holder.d_layout_specialty.setText("مصمم جرافيكس");
                }

//        holder.d_layout_rate.setRating(Float.valueOf(likes.getUser().getRate()));


                holder.chooses_me.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


            } catch (Exception e) {

            }
        }


    }

    @Override
    public int getItemCount() {
        return designLikes.size();
    }

    public class LikesDesignHV extends RecyclerView.ViewHolder {

        //item_layout_profile
        CircleImageView imageView;
        TextView d_layout_name, d_layout_specialty, chooses_me, d_layout_fav;
        RatingBar d_layout_rate;

        //fav_row
        TextView tv_name, tv_money, tv_day, tv_calender, tv1, tv2;

        //fav_row2
        ImageView img, tv_fav;
        TextView tv_like, tv_show, tv_specialization, tv_addProject;


        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");


        public LikesDesignHV(View itemView) {
            super(itemView);
            if (Layout == R.layout.fav2_row) {

                Log.e("qqqqq", "qqqqq");
                img = itemView.findViewById(R.id.img);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_fav = itemView.findViewById(R.id.tv_fav);
                tv_like = itemView.findViewById(R.id.tv_like);
                tv_show = itemView.findViewById(R.id.tv_show);
                tv_specialization = itemView.findViewById(R.id.tv_specialization);
                tv_addProject = itemView.findViewById(R.id.tv_addProject);

                tv_like.setTypeface(custom_font);
                tv_show.setTypeface(custom_font);
                tv_specialization.setTypeface(custom_font);
                tv_addProject.setTypeface(custom_font);

            } else if (Layout == R.layout.fav_row) {
                Log.e("qqqqq", "wwwww");
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_money = itemView.findViewById(R.id.tv_money);
                tv_day = itemView.findViewById(R.id.tv_day);
                tv_calender = itemView.findViewById(R.id.tv_calender);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);

                tv_name.setTypeface(custom_font);
                tv_money.setTypeface(custom_font);
                tv_day.setTypeface(custom_font);
                tv_calender.setTypeface(custom_font);
                tv1.setTypeface(custom_font);
                tv2.setTypeface(custom_font);

            } else if (Layout == R.layout.item_layout_profile) {
                imageView = itemView.findViewById(R.id.d_layout_profile_image);
                d_layout_name = itemView.findViewById(R.id.d_layout_name);
                d_layout_specialty = itemView.findViewById(R.id.d_layout_specialty);
                d_layout_rate = itemView.findViewById(R.id.d_layout_rate);
                chooses_me = itemView.findViewById(R.id.chooses_me);
                d_layout_fav = itemView.findViewById(R.id.d_layout_fav);
                d_layout_fav.setVisibility(View.GONE);

                d_layout_name.setTypeface(custom_font);
                d_layout_specialty.setTypeface(custom_font);
                chooses_me.setTypeface(custom_font);
                d_layout_fav.setTypeface(custom_font);
            }


            //item_layout_profile



            //fav_row


            //fav2_row


        }
    }
}

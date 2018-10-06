package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

            holder.d_layout_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        } catch (Exception e) {

        }
    }

    @Override
    public int getItemCount() {
        return designLikes.size();
    }

    public class LikesDesignHV extends RecyclerView.ViewHolder {
        CircleImageView imageView;
        TextView d_layout_name, d_layout_specialty, chooses_me, d_layout_fav;
        RatingBar d_layout_rate;

        public LikesDesignHV(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.d_layout_profile_image);
            d_layout_name = itemView.findViewById(R.id.d_layout_name);
            d_layout_specialty = itemView.findViewById(R.id.d_layout_specialty);
            d_layout_rate = itemView.findViewById(R.id.d_layout_rate);
            chooses_me = itemView.findViewById(R.id.chooses_me);
            d_layout_fav = itemView.findViewById(R.id.d_layout_fav);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            d_layout_name.setTypeface(custom_font);
            d_layout_specialty.setTypeface(custom_font);
            chooses_me.setTypeface(custom_font);
            d_layout_fav.setTypeface(custom_font);
        }
    }
}

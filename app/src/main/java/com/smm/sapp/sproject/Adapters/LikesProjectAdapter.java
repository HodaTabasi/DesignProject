package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.R;

import java.util.List;

public class LikesProjectAdapter extends RecyclerView.Adapter<LikesProjectAdapter.LikesProjectVH> {
    Context context;
    int Layout;
    List<Likes> projectLikes;


    public LikesProjectAdapter(Context context, int layout, List<Likes> projectLikes) {
        this.context = context;
        Layout = layout;
        this.projectLikes = projectLikes;

    }

    @NonNull
    @Override
    public LikesProjectVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Layout, parent, false);
        return new LikesProjectVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesProjectVH holder, int position) {
        Likes likes = projectLikes.get(position);
        holder.body.setText(likes.getProject().getName());
        holder.calender.setText(likes.getProject().getCreated_at());
        holder.name.setText(likes.getProject().getUser_id());
        holder.money.setText(" $ " + likes.getProject().getBalance());
    }

    @Override
    public int getItemCount() {
        return projectLikes.size();
    }

    public class LikesProjectVH extends RecyclerView.ViewHolder {
        TextView calender, name, money, body, tv1, tv_day;

        public LikesProjectVH(View itemView) {
            super(itemView);
            calender = itemView.findViewById(R.id.tv_calender);
            name = itemView.findViewById(R.id.tv_name);
            money = itemView.findViewById(R.id.tv_money);
            body = itemView.findViewById(R.id.tv2);
            tv1 = itemView.findViewById(R.id.tv1);
            tv_day = itemView.findViewById(R.id.tv_day);


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            calender.setTypeface(custom_font);
            name.setTypeface(custom_font);
            money.setTypeface(custom_font);
            body.setTypeface(custom_font);
            tv1.setTypeface(custom_font);
            tv_day.setTypeface(custom_font);


        }
    }
}

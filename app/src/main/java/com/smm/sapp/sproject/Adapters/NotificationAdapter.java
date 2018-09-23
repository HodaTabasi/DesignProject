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

import com.smm.sapp.sproject.Models.Notifications;
import com.smm.sapp.sproject.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationVH> {

    Context context;
    int layout;
    List<Notifications> notifications;

    public NotificationAdapter(Context context, int layout, List<Notifications> notifications) {
        this.context = context;
        this.layout = layout;
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new NotificationVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationVH extends RecyclerView.ViewHolder {
        TextView time, title;
        ImageView img;

        public NotificationVH(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            title = itemView.findViewById(R.id.q_title);
            img = itemView.findViewById(R.id.profile_image1);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            time.setTypeface(custom_font);
            title.setTypeface(custom_font);
        }
    }
}

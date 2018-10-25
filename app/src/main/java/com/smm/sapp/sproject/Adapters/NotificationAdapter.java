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
import android.widget.Toast;

import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.Fragments.MessageDitailsFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.Notifications;
import com.smm.sapp.sproject.Models.NotificationsModels;
import com.smm.sapp.sproject.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationVH> {

    Context context;
    int layout;
    List<NotificationsModels> notifications;

    public NotificationAdapter(Context context, int layout, List<NotificationsModels> notifications) {
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
    public void onBindViewHolder(@NonNull NotificationVH holder, final int position) {
        holder.title.setText(notifications.get(position).getNoti_ar());
        String[] separated = notifications.get(position).getCreated_at().split(" ");
        holder.time.setText(separated[0]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = notifications.get(position).getTarget_type();
                switch (s){
                    case "message":
                        Bundle bundle = new Bundle();
                        MessageDitailsFragment fragment = new MessageDitailsFragment();
                        bundle.putString("userId",notifications.get(position).getMessage().getSender_id());
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                        break;
                    case  "project":
                        Toast.makeText(context, "project", Toast.LENGTH_SHORT).show();
                        break;
                    case  "User":
                        Toast.makeText(context, "User", Toast.LENGTH_SHORT).show();
                        break;
                    case  "offer":
                        Toast.makeText(context, "offer", Toast.LENGTH_SHORT).show();
                        break;
                    case  "Like":
                        Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
                        break;
                    case  "Comment":
                        Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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

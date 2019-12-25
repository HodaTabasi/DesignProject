package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountFragment;
import com.smm.sapp.sproject.Fragments.MessageDitailsFragment;
import com.smm.sapp.sproject.Fragments.MyOffersFragment;
import com.smm.sapp.sproject.Fragments.MyProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
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
                switch (s) {
                    case "message":
                        Bundle bundle = new Bundle();
                        MessageDitailsFragment fragment = new MessageDitailsFragment();
                        bundle.putString("userId", notifications.get(position).getMessage().getSender_id());
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                        break;
                    case "project":
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new MyProjectFragment(), true);
                        break;
                    case "User":
                        Toast.makeText(context, "User", Toast.LENGTH_SHORT).show();
                        break;
                    case "offer":
                        if (ConstantInterFace.USER.getType().equals("client")) {
                            FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new MyProjectFragment(), true);
                        } else {
                            MyOffersFragment fragment1 = new MyOffersFragment();
                            Bundle bundle1 = new Bundle();
                            bundle1.putBoolean("isUpdated", true);
                            fragment1.setArguments(bundle1);
                            FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment1, true);
                        }
                        break;
                    case "Like":
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AccountFragment(), true);
                        break;
                    case "Comment":
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AccountFragment(), true);
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

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Fragments.MessageDitailsFragment;
import com.smm.sapp.sproject.Models.MyMessageModel;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.MyMessageVH> {

    Context context;
    int layout;
    List<MyMessageModel> messageModelList;

    public MyMessageAdapter(Context context, int layout, List<MyMessageModel> messageModelList) {
        this.context = context;
        this.layout = layout;
        this.messageModelList = messageModelList;
    }

    @NonNull
    @Override
    public MyMessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MyMessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyMessageVH holder, final int position) {
        String name = messageModelList.get(position).getUser().getName();
        String[] s1 = name.split(" ");
        StringBuilder s_name = new StringBuilder(s1[0]);
        for (int i = 2; i < s_name.length() - 1; i++) {
            s_name.setCharAt(i, '*');
        }

        if (messageModelList.get(position).getUser().getType().equals("client"))
            holder.address.setText(" صاحب المشروع -  " + s_name);
        else
            holder.address.setText(" المصمم -  " + s_name);


        try {
            int minuts = putDateTime(messageModelList.get(position).getLast_message().getCreated_at());
            Log.e("qqq", minuts * (1.0 / 60) + "" + messageModelList.get(position).getLast_message().getCreated_at());
            int hours = (int) ((1.0 / 60) * minuts);
            int days = (int) ((1.0 / 1440) * minuts);

            if (minuts == 0) {
                //seconds
                holder.time.setText("قبل ثواني");
            } else if (minuts > 0 && minuts <= 59) {
                //minuts
                holder.time.setText("قبل " + minuts + " دقيقة ");
            } else if (minuts >= 60 && hours < 24) {
                //hours
                //Float f = Float.intBitsToFloat((1 / 60) * minuts);
                //holder.time.setText(Math.round(f) + " ساعة ");
                holder.time.setText(hours + " ساعة ");
            } else if (hours >= 24) {
                //days
//                Float f = Float.intBitsToFloat((1 / 1440) * minuts);
//                holder.time.setText(Math.round(f) + " يوم ");
                holder.time.setText(days + " يوم ");

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get().load(messageModelList.get(position).getUser().getPhoto_link()).into(holder.clientImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDitailsFragment fragment = new MessageDitailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userId", messageModelList.get(position).getUser().getId() + "");
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
            }
        });

    }

    private int putDateTime(String created_at) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("en"));
        Date date = dt.parse(created_at);
        long mills = System.currentTimeMillis() - date.getTime();


        int hours = (int) (mills / (1000 * 60 * 60));
//        int mins = (int) (mills / (1000 * 60)) % 60;
        int mins = hours * 60;

        int days = (int) (mills / (1000 * 60 * 60 * 24));
//        String diff = hours + ":" + mins; // updated value every1 second

        Log.e("qqqqqqq", hours + "" + mins + "" + days + "");


//        hours = (hours < 0 ? -hours : hours);
//        Log.i("======= Hours"," :: "+hours);
        return mins;
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class MyMessageVH extends RecyclerView.ViewHolder {
        CircleImageView clientImage;
        TextView time, address;

        public MyMessageVH(View itemView) {
            super(itemView);
            clientImage = itemView.findViewById(R.id.profile_image1);
            address = itemView.findViewById(R.id.q_title);
            time = itemView.findViewById(R.id.time);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            time.setTypeface(custom_font);
            address.setTypeface(custom_font);

        }

    }
}

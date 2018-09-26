package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.content.Intent;
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

import com.smm.sapp.sproject.Fragments.BusinessFairFragment;
import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.AskUs;
import com.smm.sapp.sproject.Models.PWorks;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddNewPworkAdapter extends RecyclerView.Adapter<AddNewPworkAdapter.AddNewPworkHolder> {

    Context context;
    List<PWorks> pWorksList;

    public AddNewPworkAdapter(Context context, List<PWorks> pWorksList) {
        this.context = context;
        this.pWorksList = pWorksList;
    }

    @NonNull
    @Override
    public AddNewPworkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_new_work2, parent, false);
        return new AddNewPworkAdapter.AddNewPworkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddNewPworkHolder holder, final int position) {
        holder.project_title.setText(pWorksList.get(position).getName());
        holder.num_like.setText(pWorksList.get(position).getLikes());
        holder.num_seen.setText(pWorksList.get(position).getViews());
        Picasso.get().load(pWorksList.get(position).getPhoto_link()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusinessFairFragment fragment = new BusinessFairFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("work", pWorksList.get(position));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment,true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pWorksList.size();
    }

    public class AddNewPworkHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView project_title, num_seen, num_like, update_work, delete_work;

        public AddNewPworkHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            project_title = itemView.findViewById(R.id.project_title);
            num_seen = itemView.findViewById(R.id.num_seen);
            num_like = itemView.findViewById(R.id.num_like);
            update_work = itemView.findViewById(R.id.update_work);
            delete_work = itemView.findViewById(R.id.delete_work);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            project_title.setTypeface(custom_font);
            num_seen.setTypeface(custom_font);
            num_like.setTypeface(custom_font);
            num_seen.setTypeface(custom_font);
            update_work.setTypeface(custom_font);
            delete_work.setTypeface(custom_font);

        }
    }
}

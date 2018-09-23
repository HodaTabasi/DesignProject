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

import com.smm.sapp.sproject.Models.ClientJobs;
import com.smm.sapp.sproject.R;

import java.util.List;

public class ClientJobAdapter extends RecyclerView.Adapter<ClientJobAdapter.ClientJobVH> {
    Context context;
    int layout;
    List<ClientJobs> jobs;

    public ClientJobAdapter(Context context, int layout, List<ClientJobs> jobs) {
        this.context = context;
        this.layout = layout;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ClientJobVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ClientJobVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientJobVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ClientJobVH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView projectTitle, numSeen, numLike, updateWork, deleteWork;

        public ClientJobVH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            projectTitle = itemView.findViewById(R.id.project_title);
            numSeen = itemView.findViewById(R.id.num_seen);
            numLike = itemView.findViewById(R.id.num_like);
            updateWork = itemView.findViewById(R.id.update_work);
            deleteWork = itemView.findViewById(R.id.delete_work);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            projectTitle.setTypeface(custom_font);
            numSeen.setTypeface(custom_font);
            numLike.setTypeface(custom_font);
            updateWork.setTypeface(custom_font);
            deleteWork.setTypeface(custom_font);


        }
    }
}

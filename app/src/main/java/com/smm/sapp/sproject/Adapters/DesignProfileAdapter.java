package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.DesignProfile;
import com.smm.sapp.sproject.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DesignProfileAdapter extends RecyclerView.Adapter<DesignProfileAdapter.DesignProfileVH>{
    Context context;
    int layout;
    List<DesignProfile> profiles;

    public DesignProfileAdapter(Context context, int layout, List<DesignProfile> profiles) {
        this.context = context;
        this.layout = layout;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public DesignProfileVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new DesignProfileVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DesignProfileVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class DesignProfileVH extends RecyclerView.ViewHolder {
        TextView name ,specialty, addToFav, ChooeseMe;
        RatingBar rate;
        CircleImageView profileImg;

        public DesignProfileVH(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.d_layout_name);
            specialty = itemView.findViewById(R.id.d_layout_specialty);
            addToFav = itemView.findViewById(R.id.d_layout_fav);
            ChooeseMe = itemView.findViewById(R.id.chooses_me);
            rate = itemView.findViewById(R.id.d_layout_rate);
            profileImg = itemView.findViewById(R.id.d_layout_profile_image);
        }
    }
}

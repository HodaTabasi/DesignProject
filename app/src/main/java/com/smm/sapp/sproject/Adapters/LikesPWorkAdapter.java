package com.smm.sapp.sproject.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class LikesPWorkAdapter extends RecyclerView.Adapter<LikesPWorkAdapter.LikesPWorkVH> {
    @NonNull
    @Override
    public LikesPWorkVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LikesPWorkVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LikesPWorkVH extends RecyclerView.ViewHolder {
        public LikesPWorkVH(View itemView) {
            super(itemView);
        }
    }
}

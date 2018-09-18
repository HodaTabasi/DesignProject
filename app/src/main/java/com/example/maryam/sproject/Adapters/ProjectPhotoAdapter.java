package com.example.maryam.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.maryam.sproject.Models.PhotoModel;
import com.example.maryam.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProjectPhotoAdapter extends RecyclerView.Adapter<ProjectPhotoAdapter.ProjectPhotoVH> {
    Context context;
    int layout;
    List<PhotoModel> photoModels;

    public ProjectPhotoAdapter(Context context, int layout, List<PhotoModel> photoModels) {
        this.context = context;
        this.layout = layout;
        this.photoModels = photoModels;
    }

    @NonNull
    @Override
    public ProjectPhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ProjectPhotoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectPhotoVH holder, int position) {
        Picasso.get().load(photoModels.get(position).getPhoto_link()).into(holder.imageView);
        Log.e("ff",photoModels.get(position).getPhoto_link());
    }

    @Override
    public int getItemCount() {
        return photoModels.size();
    }

    public class ProjectPhotoVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ProjectPhotoVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
        }
    }
}

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smm.sapp.sproject.Models.PhotoModel;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProjectPhotoAdapter extends RecyclerView.Adapter<ProjectPhotoAdapter.ProjectPhotoVH> {
    Context context;
    int layout;
    List<PhotoModel> photoModels;
    ArrayList<Bitmap> photoModelsBitmap;
    Boolean flag;

    public ProjectPhotoAdapter(Context context, int layout, List<PhotoModel> photoModels) {
        this.context = context;
        this.layout = layout;
        this.photoModels = photoModels;
    }

    public ProjectPhotoAdapter(Context context, int layout, ArrayList<Bitmap> photoModels,Boolean aBoolean) {
        this.context = context;
        this.layout = layout;
        this.photoModelsBitmap = photoModels;
        flag = aBoolean;
    }

    @NonNull
    @Override
    public ProjectPhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ProjectPhotoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectPhotoVH holder, int position) {
        if (flag){
            if (photoModelsBitmap.size() != 0)
                holder.imageView.setImageBitmap(photoModelsBitmap.get(position));
        }else {
            Picasso.get().load(photoModels.get(position).getPhoto_link()).into(holder.imageView);
            Log.e("ff", photoModels.get(position).getPhoto_link());
        }

    }

    @Override
    public int getItemCount() {
        if (flag)
            return photoModelsBitmap.size();
        else
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

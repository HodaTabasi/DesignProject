package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.Models.PhotoModel;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    Context context;
    List<PhotoModel> photoModels;

    public ImageAdapter(Context context, List<PhotoModel> photoModels) {
        this.context = context;
        this.photoModels = photoModels;
    }

    @Override
    public int getCount() {
        if (photoModels.isEmpty())
            return 1;
        else
            return photoModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Toast.makeText(context, photoModels.isEmpty() + " ", Toast.LENGTH_SHORT).show();
        if (photoModels.isEmpty()) {
            imageView.setImageResource(R.drawable.noimage);
            Toast.makeText(context, "لا يوجد صور لعرضها في هذا المشروع", Toast.LENGTH_SHORT).show();
        } else {
            Picasso.get().load(photoModels.get(position).getPhoto_link()).into(imageView);
        }
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}

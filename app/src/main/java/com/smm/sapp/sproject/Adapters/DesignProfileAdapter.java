package com.smm.sapp.sproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountSearchFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.User;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Response;

public class DesignProfileAdapter extends RecyclerView.Adapter<DesignProfileAdapter.DesignProfileVH> {
    Context context;
    List<User> profiles;
    int worker_id;


    public DesignProfileAdapter(Context context, List<User> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public DesignProfileVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_profile, parent, false);
        return new DesignProfileVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DesignProfileVH holder, final int position) {

        worker_id = profiles.get(position).getId();

        if (profiles.get(position).getName() != null) {
            holder.name.setText(profiles.get(position).getName());
        }
        if (profiles.get(position).getJob_type() != null) {
            if (profiles.get(position).getJob_type().equals("wall")) {
                holder.specialty.setText("مصمم جداري");
            } else if (profiles.get(position).getJob_type().equals("arch")) {
                holder.specialty.setText("مصمم معماري");
            } else if (profiles.get(position).getJob_type().equals("graphic")) {
                holder.specialty.setText("مصمم جرافيكس");
            } else if (profiles.get(position).getJob_type().equals("inter")) {
                holder.specialty.setText("مصمم داخلي");
            } else if (profiles.get(position).getJob_type().equals("moshen")) {
                holder.specialty.setText("مصمم موشن");
            }
        }
        holder.rate.setRating(Float.valueOf(profiles.get(position).getRate()));

        if (profiles.get(position).getPhoto_link() != null) {
            Picasso.get().load(profiles.get(position).getPhoto_link()).into(holder.profileImg);
        }


        if (profiles.get(position).getLiked().equals("0")) {
            Drawable img = context.getResources().getDrawable(R.drawable.ic_favorite_white);
            holder.addToFav.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        } else {
            Drawable img = context.getResources().getDrawable(R.drawable.ic_favorite_red);
            holder.addToFav.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
        }


        holder.addToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTofav(profiles.get(position).getId(),holder.addToFav);
            }
        });


        holder.ChooeseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("ppppp", profiles.get(position).getId() + "");
                AccountSearchFragment fragment = new AccountSearchFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("worker", profiles.get(position));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
            }
        });


    }

    private void addTofav(int id, final TextView addToFav) {
        MyProgressDialog.showDialog(context);
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("target_id", String.valueOf(id));
        map.put("target_type", "user");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    public void run() {
                        MyProgressDialog.dismissDialog();
                        Toast.makeText(context, "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                JSONObject statusObj = object.getJSONObject("status");
                final Boolean success = statusObj.getBoolean("success");
                final String message = statusObj.getString("message");
                ((Activity)context).runOnUiThread(new Runnable() {
                    public void run() {
                        MyProgressDialog.dismissDialog();
                        if (success){
                            if (message.equals("like Returned")) {
                                Toast.makeText(context, "تمت الاضافة للمفضلة" , Toast.LENGTH_LONG).show();
                                Drawable img1 = context.getResources().getDrawable(R.drawable.ic_favorite_red);
                                addToFav.setCompoundDrawablesWithIntrinsicBounds(null, null, img1, null);
                            } else if (message.equals("dislike Returned")) {
                                Toast.makeText(context, "تم الحذف من المفضلة" , Toast.LENGTH_LONG).show();
                                Drawable img2 = context.getResources().getDrawable(R.drawable.ic_favorite_white);
                                addToFav.setCompoundDrawablesWithIntrinsicBounds(null, null, img2, null);
                            }
                        }else {
                            Toast.makeText(context, "حصل خطا ما", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class DesignProfileVH extends RecyclerView.ViewHolder {

        TextView name, specialty, addToFav, ChooeseMe;
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

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            name.setTypeface(custom_font);
            addToFav.setTypeface(custom_font);
            specialty.setTypeface(custom_font);
            ChooeseMe.setTypeface(custom_font);

        }
    }

    public void removeAt(int position) {
        profiles.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, profiles.size());
    }
}

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountSearchFragment;
import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.DesignProfile;
import com.smm.sapp.sproject.Models.SearchWorkersModel;
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
    List<SearchWorkersModel> profiles;
    int worker_id;

    public DesignProfileAdapter(Context context, List<SearchWorkersModel> profiles) {
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
    public void onBindViewHolder(@NonNull DesignProfileVH holder, final int position) {

        if(profiles.get(position).getName() == null){
            removeAt(position);
        }

        Log.e("zzzz",profiles.size()+"");
        worker_id = profiles.get(position).getId();
        holder.name.setText(profiles.get(position).getName());
        holder.rate.setRating(Float.valueOf(profiles.get(position).getRate()));
        Log.e("fffds", profiles.get(position).getRate() + " ");
        try {
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

            Picasso.get().load(profiles.get(position).getPhoto_link()).into(holder.profileImg);
            holder.ChooeseMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            holder.addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                if (ConstantInterFace.IS_USER_FAVORITE = false) {
                    addTofav();
//                }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (profiles.get(position).getName() == null && profiles.get(position).getName().isEmpty()) {
                        Toast.makeText(context, "هذا المصمم غير مكتمل ملفه الشخصي", Toast.LENGTH_LONG).show();
                    } else {
                        AccountSearchFragment fragment = new AccountSearchFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("worker", profiles.get(position));
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                    }

                }
            });

        } catch (Exception e) {

        }


    }

    private void addTofav() {
        MyProgressDialog.showDialog(context);
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("target_id", String.valueOf(worker_id));
        map.put("target_type", "user");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", map, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MyProgressDialog.dismissDialog();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Toast.makeText(context, "تأكد من اتصالك بشبكة الانترنت", Toast.LENGTH_LONG).show();
                    }
                }.execute();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                final JSONObject object = new JSONObject(response.body().string());
                JSONObject statusObj = object.getJSONObject("status");
                final String success = statusObj.getString("success");
                final String message = statusObj.getString("message");


                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if (success.equals("true") && message.equals("like Returned")) {
//                            ConstantInterFace.IS_USER_FAVORITE = true;
                            Toast.makeText(context, "تمت الاضافة للمفضلة", Toast.LENGTH_LONG).show();

                        } else if (success.equals("true") && message.equals("dislike Returned")) {
//                            ConstantInterFace.IS_USER_FAVORITE = false;
                            Toast.makeText(context, "تم الحذف من المفضلة", Toast.LENGTH_LONG).show();

                        }
                    }
                }.execute();

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

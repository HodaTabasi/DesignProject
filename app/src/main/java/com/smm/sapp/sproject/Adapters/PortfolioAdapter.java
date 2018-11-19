package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
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

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AddNewWork2Fragment;
import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.PortfolioModel;
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

import okhttp3.Call;
import okhttp3.Response;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.PortfolioHolder> {

    Context context;
    List<PortfolioModel> list;
    int pwork_id;
    String name;
    String projectType;

    public PortfolioAdapter(Context context, List<PortfolioModel> list) {
        this.context = context;
        this.list = list;
    }

    public PortfolioAdapter(Context context, List<PortfolioModel> list, String name) {
        this.context = context;
        this.list = list;
        this.name = name;
    }

    @NonNull
    @Override
    public PortfolioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav2_row, parent, false);
        return new PortfolioAdapter.PortfolioHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioHolder holder, final int position) {

        pwork_id = list.get(position).getId();

        if (name != null) {
            StringBuilder s_name = new StringBuilder(name);
            for (int i = 1; i < s_name.length() - 1; i++) {
                s_name.setCharAt(i, '*');
            }
            holder.tv_name.setText(s_name);
        } else {
            StringBuilder s_name = new StringBuilder(list.get(position).getUser().getName());
            for (int i = 1; i < s_name.length() - 1; i++) {
                s_name.setCharAt(i, '*');
            }
            holder.tv_name.setText(s_name);
        }

        holder.tv_show.setText(list.get(position).getViews());
        holder.tv_like.setText(String.valueOf(list.get(position).getLikes()));
        if (list.get(position).getType().equals("wall")) {
            holder.tv_specialization.setText("تصميم جداري");
        } else if (list.get(position).getType().equals("arch")) {
            holder.tv_specialization.setText("تصميم معماري");
        } else if (list.get(position).getType().equals("graphic")) {
            holder.tv_specialization.setText("تصميم جرافيكس");
        } else if (list.get(position).getType().equals("inter")) {
            holder.tv_specialization.setText("تصميم داخلي");
        } else if (list.get(position).getType().equals("moshen")) {
            holder.tv_specialization.setText("تصميم موشن");
        }
        Picasso.get().load(list.get(position).getPhoto_link()).into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PortfolioDescFragment fragment = new PortfolioDescFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("pwork", list.get(position));
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
            }
        });


        holder.tv_addProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantInterFace.USER.getType().equals("worker")) {
                    AddNewWork2Fragment fragment = new AddNewWork2Fragment();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("flag", false);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                } else {
                    projectType = (String) list.get(position).getType();
                    AddProjectFragment fragment = new AddProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("type", projectType);
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                }


            }
        });

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if (ConstantInterFace.IS_USER_FAVORITE = false) {
                addTofav();
                // }
            }
        });

    }

    private void addTofav() {
        MyProgressDialog.showDialog(context);
        MyRequest myRequest = new MyRequest();
        Map<String, String> map = new HashMap<>();
        map.put("token", ConstantInterFace.USER.getToken());
        map.put("target_id", String.valueOf(pwork_id));
        map.put("target_type", "pwork");
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
                            ConstantInterFace.IS_USER_FAVORITE = true;
                            Toast.makeText(context, "تمت الاضافة للمفضلة", Toast.LENGTH_LONG).show();

                        } else if (success.equals("true") && message.equals("dislike Returned")) {
                            ConstantInterFace.IS_USER_FAVORITE = false;
                            Toast.makeText(context, "تم الحذف من المفضلة", Toast.LENGTH_LONG).show();

                        }
                    }
                }.execute();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PortfolioHolder extends RecyclerView.ViewHolder {


        ImageView img, fav;
        TextView tv_name, tv_like, tv_show, tv_specialization, tv_addProject;

        public PortfolioHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            fav = itemView.findViewById(R.id.tv_fav);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_specialization = itemView.findViewById(R.id.tv_specialization);
            tv_addProject = itemView.findViewById(R.id.tv_addProject);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            tv_name.setTypeface(custom_font);
            tv_like.setTypeface(custom_font);
            tv_show.setTypeface(custom_font);
            tv_specialization.setTypeface(custom_font);
            tv_addProject.setTypeface(custom_font);
        }
    }
}

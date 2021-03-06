package com.smm.sapp.sproject.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AddProjectFragment;
import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.HelperClass.MyProgressDialog;
import com.smm.sapp.sproject.Models.ProjectsModels;
import com.smm.sapp.sproject.MyRequest;
import com.smm.sapp.sproject.OkHttpCallback;
import com.smm.sapp.sproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static android.text.format.DateUtils.getRelativeTimeSpanString;

public class BrowseProjectAdapter extends RecyclerView.Adapter<BrowseProjectAdapter.BrowseProjectHolder> {

    private Context context;
    private List<ProjectsModels> projectsList;
    PopupWindow mypopupWindow;
    View view;
    ImageView img_fav;
    TextView add_fav;
    String isLiked ;

    public BrowseProjectAdapter(Context context, List<ProjectsModels> projectsList) {
        this.context = context;
        this.projectsList = projectsList;
    }

    @NonNull
    @Override
    public BrowseProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_row, parent, false);
        return new BrowseProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BrowseProjectHolder holder, final int position) {
        holder.tv_description.setText(projectsList.get(position).getName());
        holder.tv_name.setText(projectsList.get(position).getUser().getName());
        holder.tv_proposals.setText(projectsList.get(position).getOffers().size() + " عرض ");
        try {
            int minuts = putDateTime(projectsList.get(position).getCreated_at());
            int hours = (int) ((1.0 / 60) * minuts);
            int days = (int) ((1.0 / 1440) * minuts);

            Log.e("wwwwww", minuts + "ppp" + hours + "ppp" + days);

            if (minuts == 0) {
                //seconds
                holder.tv_time.setText("قبل ثواني");
            } else if (minuts > 0 && minuts <= 59) {
                //minuts
                holder.tv_time.setText("قبل " + minuts + " دقيقة ");
            } else if (minuts >= 60 && hours < 24) {
                //hours
                holder.tv_time.setText(hours + " ساعة ");
            } else if (hours >= 24) {
                //days
                holder.tv_time.setText(days + " يوم ");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (!ConstantInterFace.IS_REGISTER) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewProjectFragment fragment = new ViewProjectFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("theProject", projectsList.get(position));
                    fragment.setArguments(bundle);
                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                }
            });
            holder.linear_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   showPopUpMenu(v, projectsList.get(position),position);
                }
            });
        }

        //to test if we in the last item in list
        if (position == projectsList.size() - 1) {
            holder.view.setVisibility(View.GONE);
            //first item
        } else if (position == 0) {
            holder.view.setVisibility(View.VISIBLE);
        }
    }

    private int putDateTime(String created_at) throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("en"));
        Date date = dt.parse(created_at);
        long mills = System.currentTimeMillis() - date.getTime();

        int hours = (int) (mills / (1000 * 60 * 60));
//        int mins = (int) (mills / (1000 * 60)) % 60;
        int mins = hours * 60;
        int days = (int) (mills / (1000 * 60 * 60 * 24));

        Log.e("qqqqqqq", hours + " , " + mins + " , " + days + " , ");

        return mins;
    }

    @SuppressLint("RestrictedApi")
    private void showPopUpMenu(final View v, final ProjectsModels projectsModels, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_menu, null);

        TextView new_project = view.findViewById(R.id.new_project);
        ImageView img_addProject = view.findViewById(R.id.img_addProject);
        add_fav = view.findViewById(R.id.add_fav);
        img_fav = view.findViewById(R.id.img_fav);
        TextView report = view.findViewById(R.id.report);

        if (ConstantInterFace.USER.getType().equals("worker")) {
            new_project.setVisibility(View.GONE);
            img_addProject.setVisibility(View.GONE);
        }

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
        new_project.setTypeface(custom_font);
        add_fav.setTypeface(custom_font);
        report.setTypeface(custom_font);

        mypopupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mypopupWindow.showAsDropDown(v);

        new_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
//                if (ConstantInterFace.USER.getType().equals("worker")){
//                    AddNewWork2Fragment fragment = new AddNewWork2Fragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id",String.valueOf(ConstantInterFace.USER.getId()));
//                    fragment.setArguments(bundle);
//                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment,true);
////                    FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, new AddNewWork2Fragment(),true);
//                }else {
                AddProjectFragment fragment = new AddProjectFragment();
                Bundle bundle = new Bundle();
                bundle.putString("type", projectsModels.getType());
                Log.e("ffd", projectsModels.getType());
                fragment.setArguments(bundle);
                FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                //}
                ConstantInterFace.tv_home.setBackgroundResource(0);
                ConstantInterFace.tv_msgs.setBackgroundResource(0);
                ConstantInterFace.tv_profile.setBackgroundResource(0);
                ConstantInterFace.tv_projects.setBackgroundResource(0);
                ConstantInterFace.tv_portfolio.setBackgroundResource(0);
            }
        });

        if (projectsModels.getLiked().equals("0")) {
            img_fav.setImageResource(R.drawable.ic_favorite_black);
            add_fav.setText("أضف الى المفضلة");
        } else {
            img_fav.setImageResource(R.drawable.ic_favorite_red);
            add_fav.setText("احذف من المفضلة");
        }

        add_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                mypopupWindow.dismiss();
                 addToFav(projectsModels.getId(),position);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mypopupWindow.dismiss();
                Reportcontent(projectsModels.getId());
            }
        });

    }

    private void Reportcontent(int id) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(context);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("target_type", "project");
        stringMap.put("target_id", id + "");
        stringMap.put("message", "هذا المحتوى غير ملائم للنشر في المشروع");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/report", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")) {
                                Toast.makeText(context, "تم ارسال التبليغ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, " " + object.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void addToFav(int id, final int position) {
        MyRequest myRequest = new MyRequest();
        MyProgressDialog.showDialog(context);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("token", ConstantInterFace.USER.getToken());
        stringMap.put("target_id", id + "");
        stringMap.put("target_type", "project");
        myRequest.PostCall("http://smm.smmim.com/waell/public/api/likedislike", stringMap, new OkHttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.dismissDialog();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException, JSONException {
                MyProgressDialog.dismissDialog();
                JSONObject jsonObject = new JSONObject(response.body().string());
                final JSONObject object = jsonObject.getJSONObject("status");
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (object.getBoolean("success")){
                                if (object.getString("message").equals("like Returned")) {
                                    Toast.makeText(context, "تمت الاضافة للمفضلة" , Toast.LENGTH_LONG).show();
                                    projectsList.get(position).setLiked("1");
                                } else if (object.getString("message").equals("dislike Returned")) {
                                    Toast.makeText(context, "تم الحذف من المفضلة" , Toast.LENGTH_LONG).show();
                                    projectsList.get(position).setLiked("0");
                                }
                            }else {
                                Toast.makeText(context, "حصل خطا ما", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mypopupWindow.update();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    public class BrowseProjectHolder extends RecyclerView.ViewHolder {

        TextView tv_description, tv_proposals, tv_time, tv_name;
        ImageView img_arrow, img_setting;
        LinearLayout linear_setting;
        View view;


        public BrowseProjectHolder(View itemView) {
            super(itemView);

            tv_description = itemView.findViewById(R.id.tv_desc);
            tv_proposals = itemView.findViewById(R.id.tv_proposals);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name = itemView.findViewById(R.id.tv_name);
            img_arrow = itemView.findViewById(R.id.img_arrow);
            img_setting = itemView.findViewById(R.id.img_setting);
            linear_setting = itemView.findViewById(R.id.linear_setting);
            view = itemView.findViewById(R.id.projects_view);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            tv_description.setTypeface(custom_font);
            tv_proposals.setTypeface(custom_font);
            tv_time.setTypeface(custom_font);
            tv_name.setTypeface(custom_font);

        }
    }
}

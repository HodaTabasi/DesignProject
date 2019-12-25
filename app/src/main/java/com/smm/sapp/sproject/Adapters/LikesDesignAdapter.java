package com.smm.sapp.sproject.Adapters;

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
import android.widget.RatingBar;
import android.widget.TextView;

import com.smm.sapp.sproject.ConstantInterFace;
import com.smm.sapp.sproject.Fragments.AccountSearchFragment;
import com.smm.sapp.sproject.Fragments.PortfolioDescFragment;
import com.smm.sapp.sproject.Fragments.ViewProjectFragment;
import com.smm.sapp.sproject.HelperClass.FragmentsUtil;
import com.smm.sapp.sproject.Models.Likes;
import com.smm.sapp.sproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LikesDesignAdapter extends RecyclerView.Adapter<LikesDesignAdapter.LikesDesignHV> {

    Context context;
    int Layout;
    List<Likes> designLikes;

    public LikesDesignAdapter(Context context, int layout, List<Likes> designLikes) {
        this.context = context;
        Layout = layout;
        this.designLikes = designLikes;
    }

    @NonNull
    @Override
    public LikesDesignHV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(Layout, parent, false);
        return new LikesDesignHV(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikesDesignHV holder, final int position) {
        final Likes likes = designLikes.get(position);

        if (Layout == R.layout.fav2_row) {
            try {
                Picasso.get().load(likes.getpWork().getPhoto_link()).into(holder.img);
                holder.tv_like.setText(likes.getpWork().getLikes());
                holder.tv_show.setText(likes.getpWork().getViews());

                if (likes.getpWork().getUser().getName() != null) {
                    StringBuilder s_name = new StringBuilder(likes.getpWork().getUser().getName());
                    for (int i = 1; i < s_name.length() - 1; i++) {
                        s_name.setCharAt(i, '*');
                    }
                    holder.tv_name.setText(s_name);
                }

                if (likes.getpWork().getType().equals("inter")) {
                    holder.tv_specialization.setText("تصميم داخلي");
                } else if (likes.getpWork().getType().equals("arch")) {
                    holder.tv_specialization.setText("تصميم معماري");
                } else if (likes.getpWork().getType().equals("wall")) {
                    holder.tv_specialization.setText("تصميم جداري");
                } else if (likes.getpWork().getType().equals("moshen")) {
                    holder.tv_specialization.setText("تصميم موشن");
                } else if (likes.getpWork().getType().equals("graphic")) {
                    holder.tv_specialization.setText("تصميم جرافيكس");
                }

                holder.tv_addProject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });


            } catch (Exception e) {

            }

        } else if (Layout == R.layout.fav_row) {
            try {
                holder.tv2.setText(likes.getProject().getName());
                holder.tv_name.setText(likes.getProject().getUser().getName());
                holder.tv_money.setText(ConstantInterFace.array[Integer.parseInt(likes.getProject().getBalance())]);
                //holder.tv_calender.setText(likes.getProject().getName());
                String created_at = likes.getCreated_at();
                String[] s = created_at.split(" ");
                holder.tv_calender.setText(s[0]);
                holder.tv_day.setVisibility(View.INVISIBLE);

            } catch (Exception e) {

            }
        } else if (Layout == R.layout.item_layout_profile) {
            try {
                Picasso.get().load(likes.getUser().getPhoto_link()).into(holder.imageView);
                holder.d_layout_name.setText(likes.getUser().getName());
                if (likes.getUser().getJob_type().equals("inter")) {
                    holder.d_layout_specialty.setText("مصمم داخلي");
                } else if (likes.getUser().getJob_type().equals("arch")) {
                    holder.d_layout_specialty.setText("مصمم معماري");
                } else if (likes.getUser().getJob_type().equals("wall")) {
                    holder.d_layout_specialty.setText("مصمم جداري");
                } else if (likes.getUser().getJob_type().equals("moshen")) {
                    holder.d_layout_specialty.setText("مصمم موشن");
                } else if (likes.getUser().getJob_type().equals("graphic")) {
                    holder.d_layout_specialty.setText("مصمم جرافيكس");
                }

                holder.d_layout_rate.setRating(Float.valueOf(likes.getUser().getRate()));


                holder.chooses_me.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });


            } catch (Exception e) {

            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (Layout){
                    case R.layout.item_layout_profile:
                        Log.e("ffffffff","vdsvcv");
                        AccountSearchFragment fragment = new AccountSearchFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("worker", likes.getUser());
                        fragment.setArguments(bundle);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment, true);
                        break;
                    case R.layout.fav2_row:
                        PortfolioDescFragment fragment1 = new PortfolioDescFragment();
                        Bundle bundle1 = new Bundle();
                        bundle1.putParcelable("pworklist", likes.getpWork());
                        bundle1.putBoolean("flag",true);
                        fragment1.setArguments(bundle1);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment1, true);
                        break;
                    case R.layout.fav_row:
                        ViewProjectFragment fragment2 = new ViewProjectFragment();
                        Bundle bundle2 = new Bundle();
                        bundle2.putParcelable("project", likes.getProject());
                        bundle2.putInt("flage",1);
                        fragment2.setArguments(bundle2);
                        FragmentsUtil.replaceFragment((FragmentActivity) context, R.id.container_activity, fragment2, true);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return designLikes.size();
    }

    public class LikesDesignHV extends RecyclerView.ViewHolder {

        //item_layout_profile
        CircleImageView imageView;
        TextView d_layout_name, d_layout_specialty, chooses_me, d_layout_fav;
        RatingBar d_layout_rate;

        //fav_row
        TextView tv_name, tv_money, tv_day, tv_calender, tv1, tv2;

        //fav_row2
        ImageView img, tv_fav;
        TextView tv_like, tv_show, tv_specialization, tv_addProject;


        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");

        public LikesDesignHV(View itemView) {
            super(itemView);
            if (Layout == R.layout.fav2_row) {
                img = itemView.findViewById(R.id.img);
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_fav = itemView.findViewById(R.id.tv_fav);
                tv_like = itemView.findViewById(R.id.tv_like);
                tv_show = itemView.findViewById(R.id.tv_show);
                tv_specialization = itemView.findViewById(R.id.tv_specialization);
                tv_addProject = itemView.findViewById(R.id.tv_addProject);
                tv_fav.setVisibility(View.GONE);
                if (ConstantInterFace.USER.getType().equals("worker"))
                    tv_addProject.setVisibility(View.GONE);

                tv_name.setTypeface(custom_font);
                tv_like.setTypeface(custom_font);
                tv_show.setTypeface(custom_font);
                tv_specialization.setTypeface(custom_font);
                tv_addProject.setTypeface(custom_font);

            } else if (Layout == R.layout.fav_row) {
                tv_name = itemView.findViewById(R.id.tv_name);
                tv_money = itemView.findViewById(R.id.tv_money);
                tv_day = itemView.findViewById(R.id.tv_day);
                tv_calender = itemView.findViewById(R.id.tv_calender);
                tv1 = itemView.findViewById(R.id.tv1);
                tv2 = itemView.findViewById(R.id.tv2);

                tv_name.setTypeface(custom_font);
                tv_money.setTypeface(custom_font);
                tv_day.setTypeface(custom_font);
                tv_calender.setTypeface(custom_font);
                tv1.setTypeface(custom_font);
                tv2.setTypeface(custom_font);

            } else if (Layout == R.layout.item_layout_profile) {
                imageView = itemView.findViewById(R.id.d_layout_profile_image);
                d_layout_name = itemView.findViewById(R.id.d_layout_name);
                d_layout_specialty = itemView.findViewById(R.id.d_layout_specialty);
                d_layout_rate = itemView.findViewById(R.id.d_layout_rate);
                chooses_me = itemView.findViewById(R.id.chooses_me);
                d_layout_fav = itemView.findViewById(R.id.d_layout_fav);
                d_layout_fav.setVisibility(View.GONE);

//                if (ConstantInterFace.USER.getType().equals("worker"))
//                    Toast.makeText(context, "انت مصمم غير مخول لك هذا الخيار", Toast.LENGTH_SHORT).show();

                d_layout_name.setTypeface(custom_font);
                d_layout_specialty.setTypeface(custom_font);
                chooses_me.setTypeface(custom_font);
                d_layout_fav.setTypeface(custom_font);
            }

        }
    }
}

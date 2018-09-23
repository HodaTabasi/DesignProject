package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.FavoritePortfolioModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class FavoritePortfolioAdapter extends RecyclerView.Adapter<FavoritePortfolioAdapter.FavoritePortfolioHolder> {

    private Context context;
    private List<FavoritePortfolioModel> myList;

    public FavoritePortfolioAdapter(Context context, List<FavoritePortfolioModel> myList) {
        this.context = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public FavoritePortfolioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav2_row, parent, false);
        return new FavoritePortfolioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePortfolioHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class FavoritePortfolioHolder extends RecyclerView.ViewHolder {

        ImageView img, tv_fav;
        TextView tv_name, tv_like, tv_show, tv_specialization, tv_addProject;

        public FavoritePortfolioHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_like = itemView.findViewById(R.id.tv_like);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_specialization = itemView.findViewById(R.id.tv_specialization);
            tv_fav = itemView.findViewById(R.id.tv_fav);
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

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class SkillsSearchAdapter extends RecyclerView.Adapter<SkillsSearchAdapter.SkillsSearchHolder> {

    private Context context;
    private List<SkillsModel> skillsList;

    public SkillsSearchAdapter(Context context, List<SkillsModel> skillsList) {
        this.context = context;
        this.skillsList = skillsList;
    }

    @NonNull
    @Override
    public SkillsSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_search_row, parent, false);
        return new SkillsSearchAdapter.SkillsSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsSearchHolder holder, int position) {

        holder.tv_skill1.setText(skillsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }

    public class SkillsSearchHolder extends RecyclerView.ViewHolder {

        TextView tv_skill1, tv_skill2, tv_skill3;

        public SkillsSearchHolder(View itemView) {
            super(itemView);

            tv_skill1 = itemView.findViewById(R.id.tv_skill1);
//            tv_skill2 = itemView.findViewById(R.id.tv_skill2);
//            tv_skill3 = itemView.findViewById(R.id.tv_skill3);

            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            tv_skill1.setTypeface(custom_font);
//            tv_skill2.setTypeface(custom_font);
//            tv_skill3.setTypeface(custom_font);

        }
    }
}

package com.smm.sapp.sproject.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.smm.sapp.sproject.Models.SkillsModel;
import com.smm.sapp.sproject.R;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillsHolder> {

    private Context context;
    private List<SkillsModel> skillsList;

    public SkillsAdapter(Context context, List<SkillsModel> skillsList) {
        this.context = context;
        this.skillsList = skillsList;
    }

    @NonNull
    @Override
    public SkillsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_row, parent, false);
        return new SkillsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsHolder holder, int position) {
        holder.et_skill.setText(skillsList.get(position).getName());
        holder.et_experience.setText(skillsList.get(position).getYears());
    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }


    public class SkillsHolder extends RecyclerView.ViewHolder {

        public EditText et_experience;
        public EditText et_skill;
        TextView tv_experience, tv_skill;

        public SkillsHolder(View itemView) {
            super(itemView);

            et_experience = itemView.findViewById(R.id.et_experience);
            et_skill = itemView.findViewById(R.id.et_skill);
            tv_experience = itemView.findViewById(R.id.tv_experience);
            tv_skill = itemView.findViewById(R.id.tv_skill);


            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "JFFlatregular.ttf");
            et_experience.setTypeface(custom_font);
            et_skill.setTypeface(custom_font);
            tv_experience.setTypeface(custom_font);
            tv_skill.setTypeface(custom_font);


        }
    }

}

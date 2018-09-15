package com.example.maryam.sproject.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.maryam.sproject.Models.SkillsModel;
import com.example.maryam.sproject.R;

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


    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }


    public class SkillsHolder extends RecyclerView.ViewHolder {

        public EditText et_experience;
        public EditText et_skill;

        public SkillsHolder(View itemView) {
            super(itemView);

            et_experience = itemView.findViewById(R.id.et_experience);
            et_skill = itemView.findViewById(R.id.et_skill);

        }
    }

}

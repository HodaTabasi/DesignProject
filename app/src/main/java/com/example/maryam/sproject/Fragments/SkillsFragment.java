package com.example.maryam.sproject.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maryam.sproject.Adapters.SkillsAdapter;
import com.example.maryam.sproject.Models.SkillsModel;
import com.example.maryam.sproject.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;


public class SkillsFragment extends Fragment {

    RecyclerView recyclerView;
    TextView tv_add_new, tv_save;
    ArrayList<SkillsModel> arrayList = new ArrayList<>();
    SkillsAdapter adapter;

    public SkillsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        recyclerView = view.findViewById(R.id.recycler_skills);
        tv_add_new = view.findViewById(R.id.tv_add_new);
        tv_save = view.findViewById(R.id.tv_save);


        arrayList.add(new SkillsModel("برمجة مواقع الويب", "خمس سنوات "));
        arrayList.add(new SkillsModel("برمجة ", "سبع سنوات "));
        arrayList.add(new SkillsModel("برمجة مواقع ", "خمس سنوات "));

        adapter = new SkillsAdapter(getActivity(), arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        tv_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}

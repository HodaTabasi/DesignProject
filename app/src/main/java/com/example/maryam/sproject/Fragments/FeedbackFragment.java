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

import com.example.maryam.sproject.Adapters.FeedbackAdapter;
import com.example.maryam.sproject.Adapters.SkillsAdapter;
import com.example.maryam.sproject.Models.FeedbackModel;
import com.example.maryam.sproject.Models.SkillsModel;
import com.example.maryam.sproject.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;


public class FeedbackFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FeedbackModel> arrayList = new ArrayList<>();
    FeedbackAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        recyclerView = view.findViewById(R.id.recycel_feedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FeedbackAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JFFlatregular.ttf", true);
    }
}